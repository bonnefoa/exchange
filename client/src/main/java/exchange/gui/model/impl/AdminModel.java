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

import exchange.gui.model.IAdminModel;
import exchange.model.StockOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the admin model
 */
public class AdminModel implements IAdminModel {

    /**
     * List of stock options
     */
    private List<StockOption> stockOptionList;
    public static final String ALREADY_EXISTE = "Title already exists";

    public AdminModel() {
        stockOptionList = new ArrayList<StockOption>();
    }

    public void createNewStockOption(StockOption stockOption) {
        if (stockOptionList.contains(stockOption)){
            throw new IllegalArgumentException(ALREADY_EXISTE);
        }
        stockOptionList.add(stockOption);
        //TODO Contact serveur for add
    }

    public void deleteStockOption(List<StockOption> stockOption) {
        stockOptionList.removeAll(stockOption);
        //TODO Contact serveur for deletion
        //TODO Notify to the subscribers
    }

    public List<StockOption> getStockOptionList() {
        return stockOptionList;
    }
}
