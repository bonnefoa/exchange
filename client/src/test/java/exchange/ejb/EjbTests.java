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
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * User: sora
 * Date: 11 mars 2009
 * Time: 11:16:22
 */
public class EjbTests
{
    private StockOptionEjbLocal ejbStockOption;
    private StockOption stockOption1 = new StockOption("titre", "company", 15);
    private StockOption stockOption2 = new StockOption("titre2", "company2", 30);

    public EjbTests()
    {
        try
        {
            Properties properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
            InitialContext initialContext = new InitialContext(properties);
            ejbStockOption = (StockOptionEjbLocal) initialContext.lookup(StockOptionEjbLocal.STOCK_OPTION_EJB);
        } catch (NamingException e)
        {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testEmptyEjb()
    {
        resetEjb();
    }

    private void resetEjb()
    {
        ejbStockOption.deleteStockOption(ejbStockOption.getStockOptionList());
        assertEquals(0, ejbStockOption.getStockOptionList().size());
    }

    @Test
    public void testAddStockOptions()
    {
        resetEjb();
        addTwoStockOptions();
    }

    private void addTwoStockOptions()
    {
        ejbStockOption.createNewStockOption(stockOption1);
        ejbStockOption.createNewStockOption(stockOption2);
        assertEquals(2, ejbStockOption.getStockOptionList().size());
    }

    @Test
    public void testDeleteStockOptions()
    {
        resetEjb();
        addTwoStockOptions();
        ejbStockOption.deleteStockOption(stockOption1);
        assertEquals(stockOption2, ejbStockOption.getStockOptionList().get(0));
    }

    @Test
    public void testDeleteStockOptions2()
    {
        resetEjb();
        addTwoStockOptions();
        ejbStockOption.deleteStockOption(stockOption2);
        assertEquals(stockOption1, ejbStockOption.getStockOptionList().get(0));
    }
}