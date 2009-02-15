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

import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of the admin model
 */
public class AdminModel implements IAdminModel {

    /**
     * List of stock options
     */
    private List<StockOption> stockOptionList;

    public AdminModel() {
        stockOptionList = new ArrayList<StockOption>();
    }

    public void createNewStockOption(StockOption stockOption) {
        stockOptionList.add(stockOption);
    }

    public void deleteStockOption(StockOption stockOption) {
        stockOptionList.remove(stockOption);
    }

    public List<StockOption> getStockOptionList() {
        return stockOptionList;
    }
}
