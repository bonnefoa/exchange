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

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import exchange.consumer.StockOptionMessageConsumer;
import exchange.ejb.SONotifierLocal;
import exchange.guiceBinding.ModuleTestGuice;
import exchange.message.MessageType;
import exchange.message.StockOptionMessage;
import exchange.message.impl.AddMessage;
import exchange.message.impl.DeleteMessage;
import exchange.message.impl.UpdateMessage;
import exchange.model.StockOption;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 5 mars 2009
 * Time: 20:33:11
 * To change this template use File | Settings | File Templates.
 */
public class TopicTest
{
    @Inject
    private SONotifierLocal notifier;
    @Inject
    private StockOptionMessageConsumer updateReader;

    @Before
    public void before() throws NamingException, JMSException
    {
        Injector injector = Guice.createInjector(new ModuleTestGuice());
        injector.injectMembers(this);
    }


    @Test
    public void testUpdate() throws NamingException, JMSException, InterruptedException
    {
        MockMessageObserver observer = new MockMessageObserver();

        updateReader.addObserver(observer);

        StockOption stockOption = new StockOption("title", "compagny", (float) 123.456);
        notifier.update(stockOption);

        Thread.sleep(100);

        checkMessage(stockOption, observer.getReceivedMessages(), MessageType.UPDATE);

    }

    @Test
    public void testAdd() throws NamingException, JMSException, InterruptedException
    {
        MockMessageObserver observer = new MockMessageObserver();
        updateReader.addObserver(observer);
        StockOption stockOption = new StockOption("title", "compagny", (float) 123.456);
        notifier.add(stockOption);
        Thread.sleep(100);
        checkMessage(stockOption, observer.getReceivedMessages(), MessageType.ADD);
    }

    @Test
    public void testDelete() throws NamingException, JMSException, InterruptedException
    {
        MockMessageObserver observer = new MockMessageObserver();

        updateReader.addObserver(observer);

        StockOption stockOption = new StockOption("title", "compagny", (float) 123.456);
        notifier.delete(stockOption);

        Thread.sleep(100);

        checkMessage(stockOption, observer.getReceivedMessages(), MessageType.DELETE);
    }

    private void checkMessage(StockOption stockOption, List<StockOptionMessage> receivedMessages, MessageType updateMessageType)
    {
        assertEquals(1, receivedMessages.size());
        switch (updateMessageType)
        {
            case ADD:
                assertTrue(receivedMessages.get(0) instanceof AddMessage);
                break;
            case DELETE:
                assertTrue(receivedMessages.get(0) instanceof DeleteMessage);
                break;
            case UPDATE:
                assertTrue(receivedMessages.get(0) instanceof UpdateMessage);
                break;
        }
        assertEquals(stockOption, receivedMessages.get(0).getStockOption());
        assertEquals(stockOption.getQuote(), receivedMessages.get(0).getStockOption().getQuote(), 0.01);
    }
}
