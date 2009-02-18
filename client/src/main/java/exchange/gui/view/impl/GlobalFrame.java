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

package exchange.gui.view.impl;

import com.google.inject.Inject;
import exchange.gui.controller.IAdminController;
import exchange.gui.controller.IClientController;
import exchange.gui.view.IGlobalFrame;

import java.util.Observable;
import java.util.Observer;

/**
 * Global frame containing the client or the admin GUI
 */
public class GlobalFrame implements IGlobalFrame {

    /**
     * Client GUI
     */
    private IClientController clientController;

    /**
     * Admin GUI
     */
    private IAdminController adminController;

    @Inject
    public GlobalFrame(IClientController clientController, IAdminController adminController) {
        this.clientController = clientController;
        this.adminController = adminController;
        clientController.setParent(this);
        adminController.setParent(this);
    }

    public void switchToAdmin() {
        clientController.setVisibility(false);
        adminController.setVisibility(true);
    }

    public void switchToClient() {
        adminController.setVisibility(false);
        clientController.setVisibility(true);
    }
}
