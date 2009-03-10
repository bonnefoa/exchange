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

package exchange.ejb.impl;

import exchange.gui.controller.IAbstractController;
import exchange.gui.view.IGlobalFrame;
import exchange.message.StockOptionMessage;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 10 mars 2009
 * Time: 14:55:10
 * To change this template use File | Settings | File Templates.
 */
public class MockMessageObserver implements Observer
{

    List<StockOptionMessage> receivedMessages;

    MockMessageObserver()
    {
        receivedMessages = new ArrayList<StockOptionMessage>();
    }

    public void setParent(IGlobalFrame parent)
    {
    }

    public void setVisibility(boolean show)
    {
    }

    public void update(Observable o, Object arg)
    {
        System.out.println("MockMessageObserver : observer notified");
        StockOptionMessage stockOptionMessage = (StockOptionMessage) arg;
        receivedMessages.add(stockOptionMessage);
    }

    public List<StockOptionMessage> getReceivedMessages()
    {
        return receivedMessages;
    }
}
