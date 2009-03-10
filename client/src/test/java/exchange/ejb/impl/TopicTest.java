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
import exchange.gui.controller.IAbstractController;
import exchange.gui.view.IGlobalFrame;
import exchange.guiceBinding.ModuleTestGuice;
import exchange.message.StockOptionMessage;
import exchange.message.impl.AddMessage;
import exchange.message.impl.DeleteMessage;
import exchange.message.impl.UpdateMessage;
import exchange.model.StockOption;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 5 mars 2009
 * Time: 20:33:11
 * To change this template use File | Settings | File Templates.
 */
public class TopicTest
{
    private SONotifierLocal notifier;
    private InitialContext jndiContext;

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

        assertEquals(1, observer.getReceivedMessages().size());


        for (StockOptionMessage receivedMessage : observer.getReceivedMessages())
        {

            assertTrue(receivedMessage instanceof UpdateMessage);
        }

        assertEquals(stockOption, observer.getReceivedMessages().get(0).getStockOption());
        assertEquals(stockOption.getQuote(), observer.getReceivedMessages().get(0).getStockOption().getQuote(), 0.01);
    }

    @Test
    @Ignore
    public void testDelete() throws NamingException, JMSException, InterruptedException
    {
        final List<StockOptionMessage> receivedMessages = new ArrayList<StockOptionMessage>();
        IAbstractController listener = new IAbstractController()
        {

            public void setParent(IGlobalFrame parent)
            {
            }

            public void setVisibility(boolean show)
            {
            }

            public void update(Observable o, Object arg)
            {
                StockOptionMessage stockOptionMessage = (StockOptionMessage) o;
                receivedMessages.add(stockOptionMessage);
            }
        };

        updateReader.addObserver(listener);


        StockOption stockOption = new StockOption("title", "compagny", (float) 123.456);
        notifier.delete(stockOption);

        Thread.sleep(100);

        assertEquals(receivedMessages.size(), 1);


        for (StockOptionMessage receivedMessage : receivedMessages)
        {
            assertTrue(receivedMessage instanceof DeleteMessage);
        }

        assertEquals(stockOption, receivedMessages.get(0).getStockOption());
        assertEquals(stockOption.getQuote(), receivedMessages.get(0).getStockOption().getQuote(), 0.01);
    }

    @Test
    @Ignore
    public void testAdd() throws NamingException, JMSException, InterruptedException
    {
        final List<StockOptionMessage> receivedMessages = new ArrayList<StockOptionMessage>();
        IAbstractController listener = new IAbstractController()
        {

            public void setParent(IGlobalFrame parent)
            {
            }

            public void setVisibility(boolean show)
            {
            }

            public void update(Observable o, Object arg)
            {
                StockOptionMessage stockOptionMessage = (StockOptionMessage) o;
                receivedMessages.add(stockOptionMessage);
            }
        };

        updateReader.addObserver(listener);


        StockOption stockOption = new StockOption("title", "compagny", (float) 123.456);
        notifier.add(stockOption);

        Thread.sleep(100);

        assertEquals(receivedMessages.size(), 1);


        for (StockOptionMessage receivedMessage : receivedMessages)
        {
            assertTrue(receivedMessage instanceof AddMessage);
        }

        assertEquals(stockOption, receivedMessages.get(0).getStockOption());
        assertEquals(stockOption.getQuote(), receivedMessages.get(0).getStockOption().getQuote(), 0.01);
    }

    @Inject
    public void setNotifier(SONotifierLocal notifier)
    {
        this.notifier = notifier;
    }

    @Inject
    public void setUpdateReader(StockOptionMessageConsumer updateReader)
    {
        this.updateReader = updateReader;
    }
}
