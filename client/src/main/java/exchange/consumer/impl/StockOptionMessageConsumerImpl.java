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

package exchange.consumer.impl;

import com.google.inject.Inject;
import exchange.consumer.StockOptionMessageConsumer;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Observable;
import java.util.Properties;

/**
 * Consumer of messages
 */
public class StockOptionMessageConsumerImpl extends Observable implements StockOptionMessageConsumer
{
    private Topic topic;
    private InitialContext initialContext;
    private Session session;
    private MessageConsumer messageConsumer;
    private ConnectionFactory connectionFactory;
    private Connection connection;

    @Inject
    public StockOptionMessageConsumerImpl(Topic topic, InitialContext initialContext)
    {
        this.topic = topic;
        Properties p2 = new Properties();
        p2.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        p2.put("brokerURL", "vm://localhost");
        try
        {
            initialContext = new InitialContext(p2);
            connectionFactory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(topic);
            connection.start();
            messageConsumer.setMessageListener(new MessageListener()
            {
                public void onMessage(Message message)
                {
                    setChanged();
                    notifyObservers(message);
                }
            });
        } catch (NamingException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JMSException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
//        initListener();
    }

    private void initListener() throws JMSException, NamingException
    {

    }
}
