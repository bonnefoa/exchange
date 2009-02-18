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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

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
        adminView.displayStockOptions(adminModel.getStockOptionList());
        adminView.initListeners(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        disconnectHandler();
                    }
                }
                ,
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        createOptionHandler();
                    }
                },
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        deleteOptionHandler();
                    }
                });
    }

    private void deleteOptionHandler() {
        adminModel.deleteStockOption(adminView.getSelectedStocksOptions());
        adminView.displayStockOptions(adminModel.getStockOptionList());
    }

    private void createOptionHandler() {
        try {
            String companyName = adminView.getCompanyNameFromTextArea();
            String titleName = adminView.getTitleNameFromTextArea();
            String quote = adminView.getQuoteFromTextArea();
            StockOption stockOption = new StockOption(titleName,companyName, quote);
            adminModel.createNewStockOption(stockOption);
            adminView.displayStockOptions(adminModel.getStockOptionList());
        } catch (IllegalArgumentException e) {
            adminView.displayError(e.getMessage());
        }
    }

    private void disconnectHandler() {
        
    }

}
