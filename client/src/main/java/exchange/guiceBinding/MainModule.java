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
import exchange.gui.controller.impl.ClientController;
import exchange.gui.controller.IClientController;
import exchange.gui.model.IClientModel;
import exchange.gui.model.impl.ClientModel;
import exchange.gui.view.IClientView;
import exchange.gui.view.impl.ClientView;

/**
 * Main module for injection
 */
public class MainModule extends AbstractModule {
    protected void configure() {
        bind(IClientView.class).to(ClientView.class);
        bind(IClientModel.class).to(ClientModel.class);
        bind(IClientController.class).to(ClientController.class);
    }
}