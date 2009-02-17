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
 * Interface for the admin view.
 * Present a special gui for the administrator
 */
public interface IAdminView extends StockOptionListManager {
    final static String newline = "\n";
    static final String LOGIN_FIED = "loginField";
    static final String STOCK_LIST = "stockList";
    static final String TEXT_AREA_COMPANY_NAME = "textAreaCompany";
    static final String TEXT_AREA_TITLE_NAME = "textAreaTitle";
    static final String TEXT_AREA_QUOTE = "textAreaQuote";
    static final String BUTTON_CREATE = "buttonSubscribe";
    static final String BUTTON_DELETE = "buttonUnsubscribe";
    static final String LABEL_LOGIN = "labelLogin";
    static final String BUTTON_DISCONNECT = "buttonConnect";

    /**
     * Init the listeners for buttons
     *
     * @param disconnectListener listener for disconnect button
     * @param createListener     Listener for create button
     * @param deleteListener     Listener for delete button
     */
    void initListeners(MouseListener disconnectListener, MouseListener createListener, MouseListener deleteListener);
}
