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

package exchange.controller.impl;

import com.google.inject.Inject;
import exchange.consumer.StockOptionMessageConsumer;
import exchange.controller.IGlobalController;
import exchange.gui.controller.IAdminController;
import exchange.gui.controller.IClientController;
import exchange.message.StockOptionMessage;
import exchange.message.impl.AddMessage;
import exchange.message.impl.DeleteMessage;
import exchange.message.impl.UpdateMessage;

import java.util.Observable;
import java.util.Observer;

/**
 * Global controlller for the client
 */
public class GlobalController implements IGlobalController, Observer
{
    private IClientController clientController;
    private IAdminController adminController;

    @Inject
    public GlobalController(IClientController clientController, IAdminController adminController, StockOptionMessageConsumer topicReader)
    {
        this.clientController = clientController;
        this.adminController = adminController;
        topicReader.addObserver(this);
    }

    public void receiveMessage(StockOptionMessage stockOptionMessage)
    {
        switch (stockOptionMessage.getMessageType())
        {
            case ADD:
                clientController.messageReceived((AddMessage) stockOptionMessage);
                adminController.messageReceived((AddMessage) stockOptionMessage);
                break;
            case DELETE:
                clientController.messageReceived((DeleteMessage) stockOptionMessage);
                adminController.messageReceived((DeleteMessage) stockOptionMessage);
                break;
            case UPDATE:
                clientController.messageReceived((UpdateMessage) stockOptionMessage);
                clientController.warnSubscribed(stockOptionMessage.getStockOption());
                break;
        }
    }

    public void update(Observable o, Object arg)
    {
        receiveMessage((StockOptionMessage) arg);
    }
}
