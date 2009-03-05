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


import exchange.ejb.IClientMessageSender;
import exchange.model.StockOption;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

@Stateless
public class ClientMessageSender implements IClientMessageSender
{

    private static final Logger logger = Logger.getLogger(ClientMessageSender.class.getCanonicalName());

    @Resource(mappedName = "jms/TestConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/TestQueue")
    private Queue queue;

    public void postLetter(String content)
    {
        try
        {
            Connection connection = connectionFactory.createConnection();
            // false -> pas de transaction
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            producer.send(session.createTextMessage(content));
            session.close();
            connection.close();
        } catch (JMSException e)
        {
            logger.log(Level.SEVERE, "Error sending message");
        }
    }

    public void createNewStockOption(StockOption stockOption)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteStockOption(List<StockOption> stockOption)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<StockOption> requestStockOptionList()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}