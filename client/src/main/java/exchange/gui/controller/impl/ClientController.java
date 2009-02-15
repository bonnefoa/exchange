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

package exchange.gui.controller.impl;

import com.google.inject.Inject;
import exchange.gui.model.IClientModel;
import exchange.gui.view.IClientView;
import exchange.gui.controller.IClientController;
import exchange.model.StockOption;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Presenter linking the view and the controller
 */
public class ClientController implements IClientController {
    private IClientModel clientModel;

    private IClientView clientView;

    @Inject
    public ClientController(IClientModel clientModel, IClientView clientView) {
        this.clientModel = clientModel;
        this.clientView = clientView;
        initListener();
    }

    private void subscribeHandler() {
        clientModel.subscribe(clientView.getSelectedStocksOptions());
    }

    private void connectHandler() {
        if (clientModel.isConnected()) {
            clientModel.disconnect();
            clientView.displayStockOptions(new ArrayList<StockOption>());
            clientView.setLoginFieldEditable(true);
            clientView.setTextButtonConnect("Connect");
        } else {
            String name = clientView.getLoginName();
            clientModel.connect(name);
            clientView.displayStockOptions(clientModel.getStockOptionsFromServer());
            clientView.setLoginFieldEditable(false);
            clientView.setTextButtonConnect("Disconnect");
        }
    }

    private void unsubscribeHandler() {
        clientModel.unsubscribe(clientView.getSelectedStocksOptions());
    }

    private void initListener() {
        clientView.initListeners(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        connectHandler();
                    }
                }
                ,
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        subscribeHandler();
                    }
                },
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        unsubscribeHandler();
                    }
                });
    }

    public void warnSubscribed(StockOption stockOption) {
        clientView.displayMessageQuote(stockOption.toString() + ':' + stockOption.getQuote());
    }

    public void deleteStockOptions(StockOption stockOption) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addStockOption(StockOption stockOption) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
