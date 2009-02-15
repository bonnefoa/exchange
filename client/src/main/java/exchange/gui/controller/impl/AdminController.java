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

package exchange.gui.controller.impl;

import com.google.inject.Inject;
import exchange.gui.controller.IAdminController;
import exchange.gui.model.IAdminModel;
import exchange.gui.view.IAdminView;
import exchange.model.StockOption;

/**
 * Implementation of the admin controller
 */
public class AdminController implements IAdminController {
    /**
     * View of the admin GUI
     */
    private IAdminView adminView;
    /**
     * Model of the admin GUI
     */
    private IAdminModel adminModel;

    @Inject
    public AdminController(IAdminView adminView, IAdminModel adminModel) {
        this.adminView = adminView;
        this.adminModel = adminModel;
    }

    public void deleteStockOptions(StockOption stockOption) {
        adminModel.deleteStockOption(stockOption);
        adminView.displayStockOptions(adminModel.getStockOptionList());
    }

    public void addStockOption(StockOption stockOption) {
        adminModel.createNewStockOption(stockOption);
        adminView.displayStockOptions(adminModel.getStockOptionList());
    }
}
