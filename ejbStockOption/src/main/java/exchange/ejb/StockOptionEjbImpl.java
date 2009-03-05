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

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the stock option ejb
 */
@Singleton
@Lock(LockType.READ)
@Startup
public class StockOptionEjbImpl implements StockOptionEjbLocal
{
    private List<StockOption> stockOptionList;

    public StockOptionEjbImpl()
    {
        stockOptionList = new ArrayList<StockOption>();
    }

    @Lock(LockType.WRITE)
    public void createNewStockOption(StockOption stockOption)
    {
        stockOptionList.add(stockOption);
    }

    @Lock(LockType.WRITE)
    public void deleteStockOption(List<StockOption> stockOption)
    {
        stockOptionList.removeAll(stockOption);
    }

    public List<StockOption> getStockOptionList()
    {
        return stockOptionList;
    }

    @Lock(LockType.WRITE)
    public void changesQuotes()
    {
        for (StockOption stockOption : stockOptionList)
        {
            int rand = (int) ((Math.random() * 3) % 3);
            float variation = (float) (Math.random() * 10);
            float quote = stockOption.getQuote();
            if (rand == 0)
            {
                stockOption.setQuote(quote - variation);
            } else if (rand == 2)
            {
                stockOption.setQuote(quote + variation);
            }
        }
    }
}