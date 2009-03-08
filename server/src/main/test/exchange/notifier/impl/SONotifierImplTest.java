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

package exchange.notifier.impl;

import exchange.ejb.SONotifierLocal;
import exchange.ejb.UpdateReaderLocal;
import exchange.model.StockOption;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 5 mars 2009
 * Time: 20:33:11
 * To change this template use File | Settings | File Templates.
 */
public class SONotifierImplTest
{
    private SONotifierLocal notifier;
    private InitialContext jndiContext;

    @Before
    public void before() throws NamingException, JMSException
    {
        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
        jndiContext = new InitialContext(properties);

        notifier = (SONotifierLocal) jndiContext.lookup("SONotifierBeanLocal");
    }

    @Test
    public void testUpdate() throws NamingException, JMSException
    {

        UpdateReaderLocal updateReader1 = (UpdateReaderLocal) jndiContext.lookup("UpdateReaderBeanLocal");
        //updateReader1.start();
        UpdateReaderLocal updateReader2 = (UpdateReaderLocal) jndiContext.lookup("UpdateReaderBeanLocal");
        //updateReader2.start();



        StockOption stockOption = new StockOption("title", "compagny", (float) 123.456);
        notifier.update(stockOption);

        StockOption result1 = updateReader1.read();
        StockOption result2 = updateReader2.read();
        assertEquals(stockOption.getCompany(), result1.getCompany());
        assertEquals(stockOption.getCompany(), result2.getCompany());

        assertEquals(stockOption.getQuote(), result1.getQuote(), 0.01);
        assertEquals(stockOption.getQuote(), result2.getQuote(), 0.01);

        assertEquals(stockOption.getTitle(), result1.getTitle());
        assertEquals(stockOption.getTitle(), result2.getTitle());
    }
}
