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
import exchange.gui.controller.IClientController;
import exchange.gui.model.IClientModel;
import exchange.gui.view.IClientView;
import exchange.gui.view.impl.SwitchView;
import exchange.model.StockOption;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Presenter linking the view and the controller
 */
public class ClientController extends Observable implements IClientController {
    /**
     * Client model
     */
    private IClientModel clientModel;
    /**
     * Client gui
     */
    private IClientView clientView;

    @Inject
    public ClientController(IClientModel clientModel, IClientView clientView) {
        this.clientModel = clientModel;
        this.clientView = clientView;
        initListener();
    }

    private void subscribeHandler() {
        clientModel.subscribe(clientView.getSelectedStocksOptions());
        //TODO Modifier la vue (mettre en gras?)
    }

    private void connectHandler() {
        if (clientModel.isConnected()) {
            clientModel.disconnect();
            clientView.displayStockOptions(new ArrayList<StockOption>());
            clientView.setLoginFieldEditable(true);
            clientView.setButtonsSubscribeEnable(false);
            clientView.setTextButtonConnect("Connect");
        } else {
            String name = clientView.getLoginName();
            clientModel.connect(name);
            clientView.displayStockOptions(clientModel.getStockOptionDisplayed());
            clientView.setLoginFieldEditable(false);
            clientView.setButtonsSubscribeEnable(true);
            clientView.setTextButtonConnect("Disconnect");
        }
    }

    private void unsubscribeHandler() {
        clientModel.unsubscribe(clientView.getSelectedStocksOptions());
        //TODO Modifier la vue (mettre en maigre?)
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
                },
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        adminAccesHandler();
                    }
                });
    }

    private void adminAccesHandler() {
        if (clientView.getPassword().equals(IClientController.PASSWORD)) {
            notifyObservers(SwitchView.ACTIVATE_ADMIN);
        } else {
            clientView.displayError(IClientController.INCORRECT_PASSWORD);
        }
    }

    public void warnSubscribed(StockOption stockOption) {
        clientView.displayMessageQuote(stockOption.toString() + ':' + stockOption.getQuote());
    }

    public void deleteStockOptions(StockOption stockOption) {
        clientModel.getStockOptionDisplayed().remove(stockOption);
        clientView.displayStockOptions(clientModel.getStockOptionDisplayed());
    }

    public void addStockOption(StockOption stockOption) {
        clientModel.getStockOptionDisplayed().add(stockOption);
        clientView.displayStockOptions(clientModel.getStockOptionDisplayed());
    }

    public void setVisibility(boolean show) {
        clientView.setVisible(show);
    }
}
