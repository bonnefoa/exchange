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

import java.awt.event.MouseListener;

/**
 * Interface for the view
 */
public interface IClientView extends CommonView {
    final static String newline = "\n";
    static final String LOGIN_FIED = "loginField";
    static final String ADMIN_FIED = "adminField";
    static final String STOCK_LIST = "stockList";
    static final String TEXT_AREA_MESSAGES = "textAreaMessages";
    static final String BUTTON_SUBSCRIBE = "buttonSubscribe";
    static final String BUTTON_UNSUBSCRIBE = "buttonUnsubscribe";
    static final String LABEL_LOGIN = "labelLogin";
    static final String LABEL_ADMIN = "labelAdmin";
    static final String BUTTON_CONNECT = "buttonConnect";
    static final String BUTTON_CONNECT_ADMIN = "buttonConnectAdmin";


    /**
     * Display a message to the text area
     *
     * @param message Message to display
     */
    void displayMessageQuote(String message);

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
     * Set the subscribe and unsubscribe buttons editable
     *
     * @param editable State to put the button
     */
    void setButtonsSubscribeEnable(boolean editable);

    /**
     * Change the text on the connect button
     *
     * @param text Text to display
     */
    void setTextButtonConnect(String text);

    /**
     * Get the entered password
     *
     * @return Password entered by the user
     */
    String getPassword();

    /**
     * Set the accessibitlity of the admin part
     *
     * @param accessibility Enable if true
     */
    void setAdminAccesEnable(boolean accessibility);

    /**
     * Init the listeners for buttons
     *
     * @param connectListener     listener for connect button
     * @param subscribeListener   Listener for subscribe button
     * @param unsubscribeListener Listener for unsubscribe button
     * @param adminAccessListener Listener for admin acces button
     */
    void initListeners(MouseListener connectListener, MouseListener subscribeListener, MouseListener unsubscribeListener, MouseListener adminAccessListener);
}
