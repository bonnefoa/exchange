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
import exchange.consumer.StockOptionMessageConsumer;
import exchange.consumer.impl.StockOptionMessageConsumerImpl;
import exchange.ejb.SONotifierLocal;
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

import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Module  Guice for testing
 */
public class ModuleTestGuice extends AbstractModule
{
    private InitialContext initialContext;
    private StockOptionEjbLocal stockOptionEjb;

    protected void configure()
    {
        try
        {
            Properties properties = new Properties();
//            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
            properties.setProperty("openejb.embedded.remotable", "true");
            properties.setProperty("openejb.remotable.businessLocals", "true");
            initialContext = new InitialContext(properties);
            stockOptionEjb = (StockOptionEjbLocal) initialContext.lookup(StockOptionEjbLocal.STOCK_OPTION_EJB);
        }
        catch (NamingException e)
        {
            e.printStackTrace();
        }
        bind(IClientController.class).to(ClientController.class).asEagerSingleton();
        bind(IAdminController.class).to(AdminController.class).asEagerSingleton();
        bind(IClientView.class).to(ClientView.class).in(Scopes.SINGLETON);
        bind(IAdminView.class).to(AdminView.class).in(Scopes.SINGLETON);
        bind(IClientModel.class).toInstance(getClientModelInstance());
        bind(IAdminModel.class).toInstance(getAdminModelInstance());
        bind(Topic.class).toInstance(getTopicInstance());
        bind(StockOptionMessageConsumer.class).to(StockOptionMessageConsumerImpl.class).in(Scopes.SINGLETON);
        bind(IGlobalFrame.class).to(GlobalFrame.class).in(Scopes.SINGLETON);
        bind(SONotifierLocal.class).toInstance(getNotifierInstance());
        bind(InitialContext.class).toInstance(initialContext);
    }

    private SONotifierLocal getNotifierInstance()
    {
        try
        {
            SONotifierLocal soNotifierLocal = ((SONotifierLocal) initialContext.lookup(SONotifierLocal.JNDI_NAME));
            return soNotifierLocal;
        }
        catch (NamingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private IClientModel getClientModelInstance()
    {
        IClientModel clientModel = new ClientModel(stockOptionEjb);
        return clientModel;
    }


    private IAdminModel getAdminModelInstance()
    {
        IAdminModel adminModel = new AdminModel(stockOptionEjb);
        return adminModel;
    }

    private Topic getTopicInstance()
    {
        try
        {
            return (Topic) initialContext.lookup(StockOptionMessageConsumer.JNDI_TOPIC_NAME);
        }
        catch (NamingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
