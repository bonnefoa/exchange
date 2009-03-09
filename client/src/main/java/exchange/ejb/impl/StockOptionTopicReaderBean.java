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

import exchange.ejb.StockOptionTopicReaderLocal;
import exchange.message.StockOptionMessage;
import exchange.gui.controller.IAbstractController;

import javax.ejb.Stateful;
import javax.ejb.Singleton;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Mar 8, 2009
 * Time: 5:34:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class StockOptionTopicReaderBean implements StockOptionTopicReaderLocal
{
    List<IAbstractController> listeners;

    public StockOptionTopicReaderBean()
    {
        listeners = new ArrayList<IAbstractController>();
    }

    public void messageReceived(StockOptionMessage stockOptionMessage)
    {
        System.out.println("MESSAGE RECEIVED");
        for(IAbstractController listener : listeners) {
            listener.messageReceived(stockOptionMessage);
        }
    }

    public void addListener(IAbstractController listener)
    {
        listeners.add(listener);
    }

    public void removeListener(IAbstractController listener)
    {
        listeners.remove(listener);
    }
}
