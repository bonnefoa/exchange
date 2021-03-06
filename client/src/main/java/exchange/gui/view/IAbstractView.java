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

package exchange.gui.view;

import exchange.model.StockOption;

import java.util.List;

/**
 * Common methods shared by both views
 */
public interface IAbstractView {

    /**
     * Show or hide the window
     *
     * @param show Control the appareance of the window
     */
    void setVisible(boolean show);

    /**
     * Display the list of stockOption
     *
     * @param stockOptionList List to display
     */
    void displayStockOptions(List<StockOption> stockOptionList);

    /**
     * Get the selected stocks options
     *
     * @return List of selected stock option
     */
    List<StockOption> getSelectedStocksOptions();

    /**
     * Display a window error for the user
     *
     * @param error Message to display
     */
    void displayError(String error);
}
