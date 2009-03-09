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

package exchange.ejb;

import exchange.model.StockOption;
import exchange.message.StockOptionMessage;
import exchange.gui.controller.IAbstractController;

import javax.ejb.Local;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MessageListener;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Mar 8, 2009
 * Time: 5:32:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface StockOptionTopicReaderLocal
{
    static String JNDI_NAME = "StockOptionTopicReaderBeanLocal";

    void messageReceived(StockOptionMessage stockOptionMessage);

    void addListener(IAbstractController listener);
    void removeListener(IAbstractController listener);
}
