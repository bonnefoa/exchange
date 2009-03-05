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
import exchange.ejb.SONotifier;
import exchange.notifier.impl.SONotifierImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 5 mars 2009
 * Time: 20:54:58
 * To change this template use File | Settings | File Templates.
 */
public class ServerBinding extends AbstractModule {

    private InitialContext initialContext;

    protected void configure() {
        try
        {
            Properties properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
            initialContext = new InitialContext(properties);
        } catch (NamingException e)
        {
            e.printStackTrace();
        }
        bind(SONotifier.class).to(SONotifierImpl.class);
    }
}
