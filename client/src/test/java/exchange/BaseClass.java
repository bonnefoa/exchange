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

package exchange;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import exchange.ejb.StockOptionEjbLocal;
import exchange.model.StockOption;
import org.junit.After;
import org.junit.Before;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Junit class
 */
public abstract class BaseClass
{
    protected Injector injector;
    private StockOptionEjbLocal ejbStockOption;


    protected BaseClass()
    {
        try
        {
            Properties properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
            InitialContext initialContext = new InitialContext(properties);
            ejbStockOption = (StockOptionEjbLocal) initialContext.lookup("StockOptionEjbImplLocal");
        } catch (NamingException e)
        {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp()
    {
        ejbStockOption.createNewStockOption(new StockOption("titre", "company", 15));
        ejbStockOption.createNewStockOption(new StockOption("titre2", "company2", 30));
        injector = Guice.createInjector(getModule());
    }

    @After
    public void tearDown()
    {
        ejbStockOption.deleteStockOption(ejbStockOption.getStockOptionList());
    }

    public abstract Module getModule();

}
