/*
 * Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package exchange.manager;

import exchange.ejb.SONotifierLocal;
import exchange.ejb.StockOptionEjbLocal;
import exchange.model.StockOption;
import exchange.model.Variation;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the stock option ejb
 */
@Singleton
public class StockOptionEjbImpl implements StockOptionEjbLocal
{
    private List<StockOption> stockOptionList;

    @EJB
    private SONotifierLocal notifier;

    private Thread updateThread;
    private StockOptionEjbImpl self;

    public StockOptionEjbImpl()
    {
        self = this;
        stockOptionList = Collections.synchronizedList(new ArrayList<StockOption>());
        updateThread = new Thread()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        Thread.sleep(1000 * 10);
                        self.changesQuotes();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        updateThread.start();
    }

    @PreDestroy
    public void die()
    {
        updateThread.stop();
    }

    public void createNewStockOption(StockOption stockOption)
    {
        synchronized (stockOptionList)
        {
            stockOptionList.add(stockOption);
        }
        notifier.add(stockOption);
    }

    public void deleteStockOption(List<StockOption> stockOptions)
    {
        for (StockOption stockOption : stockOptions)
        {
            notifier.delete(stockOption);
        }
        synchronized (stockOptionList)
        {
            stockOptionList.removeAll(stockOptions);
        }
    }

    public void deleteStockOption(StockOption stockOption)
    {
        synchronized (stockOptionList)
        {
            stockOptionList.remove(stockOption);
        }
        notifier.delete(stockOption);
    }


    public List<StockOption> getStockOptionList()
    {
        return stockOptionList;
    }

    public void changesQuotes()
    {
        System.out.println(">> " + stockOptionList.size() + " stock options updated");
        ArrayList<StockOption> toDelete = new ArrayList<StockOption>();
        synchronized (stockOptionList)
        {
            for (StockOption stockOption : stockOptionList)
            {
                int rand = (int) ((Math.random() * 3) % 3);
                float variation = (float) (Math.random() * 10);
                float quote = stockOption.getQuote();
                if (rand == 0)
                {
                    stockOption.setQuote(quote - variation);
                    stockOption.setVariation(Variation.DOWN);
                } else if (rand == 1)
                {
                    stockOption.setVariation(Variation.STALLED);
                } else if (rand == 2)
                {
                    stockOption.setQuote(quote + variation);
                    stockOption.setVariation(Variation.UP);
                }
                if (stockOption.getQuote() <= 0)
                {
                    toDelete.add(stockOption);
                }
                notifier.update(stockOption);
            }
        }
        for (StockOption stockOption : toDelete)
        {
            deleteStockOption(stockOption);
        }
    }
}