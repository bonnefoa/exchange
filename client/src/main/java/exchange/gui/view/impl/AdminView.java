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

import exchange.gui.view.IAdminView;
import exchange.model.StockOption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Implementation of the administrator GUI
 */
public class AdminView extends BaseViewClass implements IAdminView {
    /**
     * Subscribe button
     */
    private JButton buttonCreate;
    /**
     * Unsubscribe button
     */
    private JButton buttonDelete;
    /**
     * Text area
     */
    private JTextArea titleNameArea;
    /**
     * Text area
     */
    private JTextArea companyNameArea;
    /**
     * Text area
     */
    private JTextArea quoteArea;
    /**
     * Connect and disconnect button
     */
    private JButton buttonDisconnect;

    /**
     * Create the GUI and show it.
     */
    public AdminView() {
        //Create and set up the window.
        super("Exchange");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.setPreferredSize(new Dimension(600, 600));
        //Set up the content pane.
        addComponentsToPane(this.getContentPane());
        //Display the window.
        this.pack();
    }

    public void initListeners(MouseListener disconnectListener, MouseListener createListener, MouseListener deleteListener) {
        buttonCreate.addMouseListener(createListener);
        buttonDelete.addMouseListener(deleteListener);
        buttonDisconnect.addMouseListener(disconnectListener);
    }

    private void addComponentsToPane(Container pane) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        Insets inset = new Insets(10, 2, 2, 2);
        Dimension dimButton = new Dimension(120, 20);
        Dimension preferedAreaSize = new Dimension(180, 20);
        //List
        stockList = new JList();
        stockList.setName(STOCK_LIST);
        stockList.setDragEnabled(true);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.7;
        c.weighty = 0.9;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        pane.add(stockList, c);

        // Delete Button
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        buttonDelete = new JButton("Delete");
        buttonDelete.setName(BUTTON_DELETE);
        pane.add(buttonDelete,c);

        //Add of the disconnect button
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.CENTER;
        buttonDisconnect = new JButton("Disconnect");
        buttonDisconnect.setName(BUTTON_DISCONNECT);
        pane.add(buttonDisconnect, c);

        //Panel containing the creation of a new option
        JPanel panelCreateStockOption = new JPanel();
        panelCreateStockOption.setLayout(new GridBagLayout());
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets=inset;
        c.fill = GridBagConstraints.NONE;
        //Text area
        titleNameArea = new JTextArea();
        titleNameArea.setPreferredSize(preferedAreaSize);
        titleNameArea.setName(TEXT_AREA_TITLE_NAME);
        panelCreateStockOption.add(new JLabel("Title name :"), c);
        c.gridx++;
        panelCreateStockOption.add(titleNameArea, c);
        c.gridx--;
        c.gridy++;

        companyNameArea = new JTextArea();
        companyNameArea.setName(TEXT_AREA_COMPANY_NAME);
        companyNameArea.setPreferredSize(preferedAreaSize);
        panelCreateStockOption.add(new JLabel("Company name :"), c);
        c.gridx++;
        panelCreateStockOption.add(companyNameArea, c);
        c.gridx--;
        c.gridy++;

        quoteArea = new JTextArea();
        quoteArea.setName(TEXT_AREA_QUOTE);
        quoteArea.setPreferredSize(preferedAreaSize);
        panelCreateStockOption.add(new JLabel("Quote value :"), c);
        c.gridx++;
        panelCreateStockOption.add(quoteArea, c);
        c.gridx--;
        c.gridy++;

        buttonCreate = new JButton("Create");
        buttonCreate.setPreferredSize(dimButton);
        buttonCreate.setName(BUTTON_CREATE);
        c.gridwidth = 2;
        panelCreateStockOption.add(buttonCreate, c);

        //Add of the panel
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.3;
        c.weighty = 0;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(panelCreateStockOption, c);
    }

    public String getCompanyNameFromTextArea() {
        return companyNameArea.getText();
    }

    public String getTitleNameFromTextArea() {
        return titleNameArea.getText();
    }

    public String getQuoteFromTextArea() {
        return quoteArea.getText();
    }
}
