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

package exchange.gui;

import com.google.inject.Inject;
import com.google.inject.Module;
import exchange.BaseClass;
import exchange.gui.controller.IClientController;
import exchange.gui.model.IClientModel;
import exchange.gui.view.IClientView;
import exchange.guiceBinding.ModuleTest;
import exchange.model.StockOption;
import static junit.framework.Assert.assertFalse;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

/**
 * Unit testing on the user interface
 */
public class ClientUITest extends BaseClass {
    private FrameFixture window;

    private IClientView clientView;

    private IClientModel clientModel;

    private IClientController clientController;

    private StockOption option1;
    private StockOption option2;
    private StockOption option3;
    private StockOption option4;

    @Before
    public void setUp() {
        super.setUp();
        window = new FrameFixture((Frame) clientView);
        window.show();
        option1 = new StockOption("titre", "company", 15);
        option2 = new StockOption("titre2", "company2", 30);
        option3 = new StockOption("titre3", "company2", 40);
        option4 = new StockOption("titre4", "company4", 4);
    }

    public Module getModule() {
        return new ModuleTest();
    }

    @After
    public void tearDown() {
        window.cleanUp();
        clientView = null;
    }

    private void connect() {
        window.textBox(IClientView.LOGIN_FIED).enterText("testLogin");
        window.button(IClientView.BUTTON_CONNECT).click();
    }

    @Test
    public void testConnect() throws InterruptedException {
        assertFalse(clientModel.isConnected());
        window.button(IClientView.BUTTON_SUBSCRIBE).requireDisabled();
        window.button(IClientView.BUTTON_UNSUBSCRIBE).requireDisabled();
        connect();
        window.button(IClientView.BUTTON_SUBSCRIBE).requireEnabled();
        window.button(IClientView.BUTTON_UNSUBSCRIBE).requireEnabled();

        assertTrue(clientModel.isConnected());
        assertEquals("testLogin", clientModel.getName());

        window.textBox(IClientView.LOGIN_FIED).requireNotEditable();
        assertEquals("Disconnect", window.button(IClientView.BUTTON_CONNECT).text());

        assertEquals(2, window.list(IClientView.STOCK_LIST).contents().length);

        window.textBox(IClientView.ADMIN_FIED).requireNotEditable();
        window.button(IClientView.BUTTON_CONNECT_ADMIN).requireDisabled();
    }

    @Test
    public void testDisconnect() throws InterruptedException {
        connect();
        window.button(IClientView.BUTTON_CONNECT).click();

        window.textBox(IClientView.LOGIN_FIED).requireEditable();
        assertEquals("Connect", window.button(IClientView.BUTTON_CONNECT).text());
        assertEquals(0, window.list(IClientView.STOCK_LIST).contents().length);

        window.button(IClientView.BUTTON_SUBSCRIBE).requireDisabled();
        window.button(IClientView.BUTTON_UNSUBSCRIBE).requireDisabled();

        window.textBox(IClientView.ADMIN_FIED).requireEditable();
        window.button(IClientView.BUTTON_CONNECT_ADMIN).requireEnabled();
    }

    @Test
    public void testSubscribeFirst() {
        connect();
        window.list(IClientView.STOCK_LIST).selectItem(0);
        window.button(IClientView.BUTTON_SUBSCRIBE).click();

        assertEquals(1, clientModel.getSubscribed().size());
        assertEquals(option1, clientModel.getSubscribed().get(0));
    }

    @Test
    public void testSubscribeSecond() {
        connect();
        window.list(IClientView.STOCK_LIST).selectItem(1);
        window.button(IClientView.BUTTON_SUBSCRIBE).click();

        assertEquals(1, clientModel.getSubscribed().size());
        assertEquals(option2, clientModel.getSubscribed().get(0));
    }

    @Test
    public void testSubscribeBoth() {
        connect();
        window.list(IClientView.STOCK_LIST).selectItems(0, 1);
        window.button(IClientView.BUTTON_SUBSCRIBE).click();

        assertEquals(2, clientModel.getSubscribed().size());
        assertEquals(option1, clientModel.getSubscribed().get(0));
        assertEquals(option2, clientModel.getSubscribed().get(1));
    }

    @Test
    public void testSubscribeAndUnsubscribe() {
        connect();
        window.list(IClientView.STOCK_LIST).selectItems(0, 1);
        window.button(IClientView.BUTTON_SUBSCRIBE).click();

        window.list(IClientView.STOCK_LIST).selectItems(1);
        window.button(IClientView.BUTTON_UNSUBSCRIBE).click();

        assertEquals(1, clientModel.getSubscribed().size());
        assertEquals(option1, clientModel.getSubscribed().get(0));

        window.list(IClientView.STOCK_LIST).selectItems(0);
        window.button(IClientView.BUTTON_UNSUBSCRIBE).click();

        assertEquals(0, clientModel.getSubscribed().size());
    }

    @Test
    public void testAddStockOption() {
        connect();
        clientController.addStockOption(option3);
        assertEquals(3, window.list(IClientView.STOCK_LIST).contents().length);
        assertEquals(3, clientModel.getStockOptionDisplayed().size());
        assertEquals(option1, clientModel.getStockOptionDisplayed().get(0));
        assertEquals(option2, clientModel.getStockOptionDisplayed().get(1));
        assertEquals(option3, clientModel.getStockOptionDisplayed().get(2));
    }

    @Test
    public void testRemoveStockOption() {
        connect();
        clientController.deleteStockOptions(option1);
        assertEquals(1, window.list(IClientView.STOCK_LIST).contents().length);
        assertEquals(1, clientModel.getStockOptionDisplayed().size());
        assertEquals(option2, clientModel.getStockOptionDisplayed().get(0));
    }

    @Inject
    public void setView(IClientView clientView) {
        this.clientView = clientView;
    }

    @Inject
    public void setModel(IClientModel clientModel) {
        this.clientModel = clientModel;
    }

    @Inject
    public void setController(IClientController clientController) {
        this.clientController = clientController;
    }
}