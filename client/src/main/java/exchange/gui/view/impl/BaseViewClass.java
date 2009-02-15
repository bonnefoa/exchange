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

package exchange.gui.view.impl;

import exchange.model.StockOption;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Base classe for view.
 * It regoups the logic for the treatement of the JList.
 */
public abstract class BaseViewClass extends JFrame {
    /**
     * List of stock option
     */
    protected JList stockList;

    public BaseViewClass(String title) throws java.awt.HeadlessException {
        super(title);
    }

    public void displayStockOptions(java.util.List<StockOption> stockOptionList) {
        stockList.setListData(stockOptionList.toArray());
    }

    public java.util.List<StockOption> getSelectedStocksOptions() {
        java.util.List<StockOption> res = new ArrayList<StockOption>();
        for (Object o : stockList.getSelectedValues()) {
            res.add((StockOption) o);
        }
        return res;
    }

    public List<StockOption> getStockOptions() {
        List<StockOption> res = new ArrayList<StockOption>();
        ListModel listModel = stockList.getModel();
        for (int i = 0; i < listModel.getSize(); i++) {
            Object o = listModel.getElementAt(i);
            res.add((StockOption) o);
        }
        return res;
    }

}
