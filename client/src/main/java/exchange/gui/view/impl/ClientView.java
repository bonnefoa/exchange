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

import exchange.gui.view.IClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Implementation of the view
 */
public class ClientView extends BaseViewClass implements IClientView {

    /**
     * Subscribe button
     */
    private JButton buttonSubscribe;
    /**
     * Unsubscribe button
     */
    private JButton buttonUnsubscribe;
    /**
     * Text area
     */
    private JTextArea textArea;
    /**
     * Connect and disconnect button
     */
    private JButton buttonConnect;
    /**
     * Connect button for administration zone
     */
    private JButton buttonConnectAdmin;
    /**
     * Login field
     */
    private JTextField loginField;
    /**
     * Admin password field
     */
    private JPasswordField adminField;
    /**
     * Label for login
     */
    private JLabel labelLogin;
    /**
     * Label for admin
     */
    private JLabel labelAdmin;

    /**
     * Create the GUI and show it.
     */
    public ClientView() {
        //Create and set up the window.
        super("Exchange");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.setPreferredSize(new Dimension(600, 600));
        //Set up the content pane.
        addComponentsToPane(this.getContentPane());
        //Display the window.
        this.pack();
        this.setVisible(true);
    }

    public void initListeners(MouseListener connectListener, MouseListener subscribeListener, MouseListener unsubscribeListener, MouseListener adminAccessListener) {
        buttonConnect.addMouseListener(connectListener);
        buttonSubscribe.addMouseListener(subscribeListener);
        buttonUnsubscribe.addMouseListener(unsubscribeListener);
        buttonConnectAdmin.addMouseListener(adminAccessListener);
    }

    private void addComponentsToPane(Container pane) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 2, 2, 2);
        Dimension dimButton = new Dimension(30, 20);

        c.gridwidth = 2;
        //List
        stockList = new JList();
        stockList.setName(STOCK_LIST);
        stockList.setDragEnabled(true);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 1;
        pane.add(stockList, c);

        //Text area
        c.gridwidth = 3;
        textArea = new JTextArea("Cours de la bourse");
        textArea.setName(TEXT_AREA_MESSAGES);
        c.gridx = 2;
        pane.add(textArea, c);

        //Second row
        c.gridwidth = 1;
        c.gridy = 2;
        c.weighty = 0;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;

        //Subscribe button
        c.gridheight = 1;
        c.gridx = 0;
        buttonSubscribe = new JButton("Subscribe");
        buttonSubscribe.setPreferredSize(dimButton);
        buttonSubscribe.setName(BUTTON_SUBSCRIBE);
        buttonSubscribe.setEnabled(false);
        pane.add(buttonSubscribe, c);

        c.gridx = 1;
        buttonUnsubscribe = new JButton("Unsubscribe");
        buttonUnsubscribe.setName(BUTTON_UNSUBSCRIBE);
        buttonUnsubscribe.setPreferredSize(dimButton);
        buttonUnsubscribe.setEnabled(false);
        pane.add(buttonUnsubscribe, c);

        //Login label
        c.gridheight = 1;
        c.gridx = 2;
        c.weightx = 0;
        labelLogin = new JLabel("Login :");
        labelLogin.setName(LABEL_LOGIN);
        pane.add(labelLogin, c);

        //Login field
        c.gridx = 3;
        c.weightx = 0.5;
        loginField = new JTextField();
        loginField.setName(LOGIN_FIED);
        loginField.setMinimumSize(new Dimension(40, 20));
        pane.add(loginField, c);

        //Connect button
        c.gridx = 4;
        buttonConnect = new JButton("Connect");
        buttonConnect.setName(BUTTON_CONNECT);
        buttonConnect.setPreferredSize(dimButton);
        pane.add(buttonConnect, c);

        // Separator
        c.gridy++;
        c.gridx = 2;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.CENTER;
        JLabel label = new JLabel("Admin access");
        pane.add(label, c);

        //Admin label
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridy++;
        c.gridx = 2;
        c.weightx = 0;
        labelAdmin = new JLabel("Password :");
        labelAdmin.setName(LABEL_ADMIN);
        pane.add(labelAdmin, c);

        //Admin password field
        c.gridx = 3;
        c.weightx = 0.5;
        adminField = new JPasswordField();
        adminField.setName(ADMIN_FIED);
        adminField.setMinimumSize(new Dimension(40, 20));
        pane.add(adminField, c);

        //Connect button
        c.gridx = 4;
        buttonConnectAdmin = new JButton("Admin zone");
        buttonConnectAdmin.setName(BUTTON_CONNECT_ADMIN);
        buttonConnectAdmin.setPreferredSize(dimButton);
        pane.add(buttonConnectAdmin, c);
    }

    public void displayMessageQuote(String message) {
        textArea.append(message + newline);
    }

    public void setLoginFieldEditable(boolean editable) {
        loginField.setEditable(editable);
    }

    public void setButtonsSubscribeEnable(boolean enable) {
        buttonSubscribe.setEnabled(enable);
        buttonUnsubscribe.setEnabled(enable);
    }

    public void setAdminAccesEnable(boolean accessibility) {
        adminField.setEditable(accessibility);
        buttonConnectAdmin.setEnabled(accessibility);
    }

    public String getPassword() {
        return adminField.getText();
    }

    public void setTextButtonConnect(String text) {
        buttonConnect.setText(text);
    }

    public String getLoginName() {
        return loginField.getText();
    }
}
