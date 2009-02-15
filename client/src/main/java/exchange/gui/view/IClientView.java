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

import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * Interface for the view
 */
public interface IClientView {
    final static String newline = "\n";
    static final String LOGIN_FIED = "loginField";
    static final String STOCK_LIST = "stockList";
    static final String TEXT_AREA = "textArea";
    static final String BUTTON_SUBSCRIBE = "buttonSubscribe";
    static final String BUTTON_UNSUBSCRIBE = "buttonUnsubscribe";
    static final String LABEL_LOGIN = "labelLogin";
    static final String BUTTON_CONNECT = "buttonConnect";

    /**
     * Display a message to the text area
     *
     * @param message Message to display
     */
    void displayMessageQuote(String message);

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
     * Get the list of stock options displayed by the Jlist
     *
     * @return List of stock options displayed
     */
    List<StockOption> getStockOptions();

    /**
     * Get the login entered
     *
     * @return Login entered
     */
    String getLoginName();

    /**
     * Set the login field editable
     *
     * @param editable boolean to set the login editable or not
     */
    void setLoginFieldEditable(boolean editable);

    /**
     * Change the text on the connect button
     *
     * @param text Text to display
     */
    void setTextButtonConnect(String text);

    /**
     * Init the listeners for buttons
     *
     * @param connectListener
     * @param subscribeListener
     * @param unsubscribeListener
     */
    void initListeners(MouseListener connectListener, MouseListener subscribeListener, MouseListener unsubscribeListener);
}