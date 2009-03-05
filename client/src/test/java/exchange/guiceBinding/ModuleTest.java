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

package exchange.guiceBinding;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import exchange.ejb.StockOptionEjbLocal;
import exchange.gui.controller.IAdminController;
import exchange.gui.controller.IClientController;
import exchange.gui.controller.impl.AdminController;
import exchange.gui.controller.impl.ClientController;
import exchange.gui.model.IAdminModel;
import exchange.gui.model.IClientModel;
import exchange.gui.model.impl.AdminModel;
import exchange.gui.model.impl.ClientModel;
import exchange.gui.view.IAdminView;
import exchange.gui.view.IClientView;
import exchange.gui.view.IGlobalFrame;
import exchange.gui.view.impl.AdminView;
import exchange.gui.view.impl.ClientView;
import exchange.gui.view.impl.GlobalFrame;
import exchange.model.StockOption;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Module  Guice for testing
 */
public class ModuleTest extends AbstractModule
{
    private InitialContext initialContext;

    protected void configure()
    {
        try
        {
            Properties properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
            initialContext = new InitialContext(properties);
        } catch (NamingException e)
        {
            e.printStackTrace();
        }
        bind(IClientController.class).to(ClientController.class).asEagerSingleton();
        bind(IAdminController.class).to(AdminController.class).asEagerSingleton();
        bind(IClientView.class).to(ClientView.class).in(Scopes.SINGLETON);
        bind(IAdminView.class).to(AdminView.class).in(Scopes.SINGLETON);
//        bind(IAdminModel.class).to(MockAdminModel.class).in(Scopes.SINGLETON);
//        bind(IClientModel.class).to(MockClientModel.class).in(Scopes.SINGLETON);
        bind(IClientModel.class).toInstance(getClientModelInstance());
        bind(IAdminModel.class).toInstance(getAdminModelInstance());
        bind(IGlobalFrame.class).to(GlobalFrame.class).in(Scopes.SINGLETON);
        initEjb();
    }

    private void initEjb()
    {
        StockOptionEjbLocal ejb;
        try
        {
            ejb = (StockOptionEjbLocal) initialContext.lookup("StockOptionEjbImplLocal");
            ejb.createNewStockOption(new StockOption("titre", "company", 15));
            ejb.createNewStockOption(new StockOption("titre2", "company2", 30));
        } catch (NamingException e)
        {
            e.printStackTrace();
        }
    }

    private IClientModel getClientModelInstance()
    {
        try
        {
            StockOptionEjbLocal ejb = (StockOptionEjbLocal) initialContext.lookup("StockOptionEjbImplLocal");
            ejb.createNewStockOption(new StockOption("titre", "company", 15));
            ejb.createNewStockOption(new StockOption("titre2", "company2", 30));
            IClientModel clientModel = new ClientModel((StockOptionEjbLocal) initialContext.lookup("StockOptionEjbImplLocal"));
            return clientModel;
        } catch (NamingException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    private IAdminModel getAdminModelInstance()
    {
        try
        {
            IAdminModel adminModel = new AdminModel((StockOptionEjbLocal) initialContext.lookup("StockOptionEjbImplLocal"));
            return adminModel;
        } catch (NamingException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
