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

package exchange.notifier;

import exchange.ejb.SONotifierLocal;
import exchange.model.StockOption;
import exchange.message.StockOptionMessage;
import exchange.message.impl.UpdateMessage;
import exchange.message.impl.DeleteMessage;
import exchange.message.impl.AddMessage;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
public class SONotifierBean implements SONotifierLocal
{

    @Resource
    private ConnectionFactory connectionFactory;

    @Resource(name = "StockOptionTopic")
    private Topic topic;

    private void send(StockOptionMessage message)
    {
        try
        {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(topic);
            producer.send(session.createObjectMessage(message));
            session.close();
            connection.close();
        }
        catch (JMSException e)
        {
            e.printStackTrace();
        }
    }

    public void update(StockOption stockOption)
    {
        StockOptionMessage message = new UpdateMessage();
        message.setStockOption(stockOption);
        send(message);
    }

    public void delete(StockOption stockOption)
    {
        StockOptionMessage message = new DeleteMessage();
        message.setStockOption(stockOption);
        send(message);
    }

    public void add(StockOption stockOption)
    {
        StockOptionMessage message = new AddMessage();
        message.setStockOption(stockOption);
        send(message);
    }

    public Topic getTopic()
    {
        return topic;
    }
}
