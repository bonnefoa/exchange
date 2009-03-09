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

package exchange.ejb;

import exchange.model.StockOption;
import exchange.model.Variation;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the stock option ejb
 */
@Singleton
@Lock(LockType.READ)
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
        stockOptionList = new ArrayList<StockOption>();

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

    @Lock(LockType.WRITE)
    public void createNewStockOption(StockOption stockOption)
    {
        stockOptionList.add(stockOption);
        notifier.add(stockOption);
    }

    @Lock(LockType.WRITE)
    public void deleteStockOption(List<StockOption> stockOptions)
    {
        stockOptionList.removeAll(stockOptions);
        for (StockOption stockOption : stockOptions)
        {
            notifier.delete(stockOption);
        }
    }

    public List<StockOption> getStockOptionList()
    {
        return stockOptionList;
    }

    @Lock(LockType.WRITE)
    public void changesQuotes()
    {
        System.out.println(">> " + stockOptionList.size() + " stock options updated");
        for (StockOption stockOption : stockOptionList)
        {
            int rand = (int) ((Math.random() * 3) % 3);
            float variation = (float) (Math.random() * 10);
            float quote = stockOption.getQuote();
            if (rand == 0)
            {
                stockOption.setQuote(quote - variation);
                stockOption.setVariation(Variation.DOWN);
            }
            else if (rand == 1)
            {
                stockOption.setVariation(Variation.STALLED);
            }
            else if (rand == 2)
            {
                stockOption.setQuote(quote + variation);
                stockOption.setVariation(Variation.UP);
            }

            notifier.update(stockOption);
        }
    }
}