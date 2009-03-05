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

package exchange.gui.model.impl;

import exchange.ejb.StockOptionEjbLocal;
import exchange.gui.model.IAdminModel;
import exchange.model.StockOption;

import java.util.List;

/**
 * Implementation of the admin model
 */
public class AdminModel implements IAdminModel
{

    private StockOptionEjbLocal stockOptionEjb;


    public static final String ALREADY_EXISTE = "Title already exists";

    public AdminModel(StockOptionEjbLocal stockOptionEjb)
    {
        this.stockOptionEjb = stockOptionEjb;
    }

    public void createNewStockOption(StockOption stockOption)
    {
        if (stockOptionEjb.getStockOptionList().contains(stockOption))
        {
            throw new IllegalArgumentException(ALREADY_EXISTE);
        }
        stockOptionEjb.createNewStockOption(stockOption);
    }

    public void deleteStockOption(List<StockOption> stockOption)
    {
        stockOptionEjb.deleteStockOption(stockOption);
        //TODO Notify to the subscribers
    }

    public List<StockOption> getStockOptionList()
    {
        return stockOptionEjb.getStockOptionList();
    }
}
