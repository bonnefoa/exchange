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

/**
 * Main module for injection
 */
public class MainModule extends AbstractModule {
    protected void configure() {
        bind(IClientView.class).to(ClientView.class).in(Scopes.SINGLETON);
        bind(IClientModel.class).to(ClientModel.class).in(Scopes.SINGLETON);
        bind(IClientController.class).to(ClientController.class).in(Scopes.SINGLETON);
        bind(IAdminView.class).to(AdminView.class).in(Scopes.SINGLETON);
        bind(IAdminModel.class).to(AdminModel.class).in(Scopes.SINGLETON);
        bind(IAdminController.class).to(AdminController.class).in(Scopes.SINGLETON);
        bind(IGlobalFrame.class).to(GlobalFrame.class).in(Scopes.SINGLETON);
    }
}
