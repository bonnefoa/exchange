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

import exchange.consumer.StockOptionMessageConsumer;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Observable;

import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 10 mars 2009
 * Time: 15:08:28
 * To change this template use File | Settings | File Templates.
 */
public class StockOptionMessageConsumerImpl extends Observable implements StockOptionMessageConsumer
{
    private Topic topic;
    private Thread thread;
    private StockOptionMessageConsumerImpl self;
    private InitialContext initialContext;
    private Session session;
    private MessageConsumer messageConsumer;
    private ConnectionFactory connectionFactory;
    private Connection connection;

    @Inject
    public StockOptionMessageConsumerImpl(Topic topic, InitialContext initialContext)
    {
        self = this;
        this.topic = topic;
        this.initialContext = initialContext;

        connect();
        startThread();
        //connection.close();

    }

    private void startThread()
    {
        this.thread = new Thread()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        Object message = (ObjectMessage) messageConsumer.receive();
                        setChanged();
                        notifyObservers(message);
                    }
                    catch (JMSException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();
    }

    private void connect()
    {

        try
        {
            connectionFactory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(topic);
            connection.start();
        }
        catch (JMSException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch (NamingException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
