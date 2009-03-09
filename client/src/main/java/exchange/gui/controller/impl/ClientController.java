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
import exchange.message.StockOptionMessage;
import exchange.message.impl.AddMessage;
import exchange.message.impl.DeleteMessage;
import exchange.message.impl.UpdateMessage;
import exchange.model.StockOption;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Presenter linking the view and the controller
 */
public class ClientController extends AbstractController implements IClientController
{
    /**
     * Client model
     */
    private IClientModel clientModel;
    /**
     * Client gui
     */
    private IClientView clientView;

    @Inject
    public ClientController(IClientModel clientModel, IClientView clientView)
    {
        this.clientModel = clientModel;
        this.clientView = clientView;
        initListener();
    }

    private void subscribeHandler()
    {
        clientModel.subscribe(clientView.getSelectedStocksOptions());
        //TODO Modifier la vue (mettre en gras?)
    }

    private void connectHandler()
    {
        if (clientModel.isConnected())
        {
            clientModel.disconnect();
            clientView.setAdminAccesEnable(true);
            clientView.displayStockOptions(new ArrayList<StockOption>());
            clientView.setLoginFieldEditable(true);
            clientView.setButtonsSubscribeEnable(false);
            clientView.setTextButtonConnect("Connect");
        }
        else
        {
            String name = clientView.getLoginName();
            clientModel.connect(name);
            clientView.displayStockOptions(clientModel.getStockOptionDisplayed());
            clientView.setLoginFieldEditable(false);
            clientView.setButtonsSubscribeEnable(true);
            clientView.setAdminAccesEnable(false);
            clientView.setTextButtonConnect("Disconnect");
        }
    }

    private void unsubscribeHandler()
    {
        clientModel.unsubscribe(clientView.getSelectedStocksOptions());
        //TODO Modifier la vue (mettre en maigre?)
    }

    private void initListener()
    {
        clientView.initListeners(
                new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        connectHandler();
                    }
                }
                ,
                new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        subscribeHandler();
                    }
                },
                new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        unsubscribeHandler();
                    }
                },
                new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        adminAccesHandler();
                    }
                });
    }

    private boolean isPasswordCorrect(char[] input)
    {
        boolean isCorrect;
        char[] correctPassword = IClientController.PASSWORD.toCharArray();
        isCorrect = input.length == correctPassword.length && Arrays.equals(input, correctPassword);
        //Zero out the password.
        Arrays.fill(correctPassword, '0');
        return isCorrect;
    }

    private void adminAccesHandler()
    {
        if (isPasswordCorrect(clientView.getPassword()))
        {
            parent.switchToAdmin();
        }
        else
        {
            clientView.displayError(IClientController.INCORRECT_PASSWORD);
        }
    }

    public void warnSubscribed(StockOption stockOption)
    {
        clientView.displayMessageQuote(stockOption.toString() + ':' + stockOption.getQuote());
    }

    public void deleteStockOptions(StockOption stockOption)
    {
        clientModel.getStockOptionDisplayed().remove(stockOption);
        clientView.displayStockOptions(clientModel.getStockOptionDisplayed());
    }

    public void addStockOption(StockOption stockOption)
    {
        clientModel.getStockOptionDisplayed().add(stockOption);
        clientView.displayStockOptions(clientModel.getStockOptionDisplayed());
    }

    public void setVisibility(boolean show)
    {
        clientView.setVisible(show);
    }

    public void messageReceived(StockOptionMessage stockOptionMessage)
    {
        switch (stockOptionMessage.getMessageType())
        {
            case ADD:
                messageReceived((AddMessage) stockOptionMessage);
                break;
            case DELETE:
                messageReceived((DeleteMessage) stockOptionMessage);
                break;
            case UPDATE:
                messageReceived((UpdateMessage) stockOptionMessage);
                break;
        }
    }

    private void messageReceived(AddMessage stockOptionMessage)
    {
        if (clientModel.isConnected())
        {
            addStockOption(stockOptionMessage.getStockOption());
        }

    }

    private void messageReceived(DeleteMessage stockOptionMessage)
    {
        if (clientModel.isConnected())
        {
            deleteStockOptions(stockOptionMessage.getStockOption());
        }
    }

    private void messageReceived(UpdateMessage stockOptionMessage)
    {
        if (clientModel.isConnected() && clientModel.getSubscribed().contains(stockOptionMessage.getStockOption()))
        {
            warnSubscribed(stockOptionMessage.getStockOption());
        }
    }
}
