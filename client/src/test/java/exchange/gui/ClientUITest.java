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

import java.awt.Frame;
import java.util.List;

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

    @Before
    public void setUp() {
        super.setUp();
        window = new FrameFixture((Frame) clientView);
        window.show();
        option1 = new StockOption("titre", "company", 15);
        option2 = new StockOption("titre2", "company2", 30);
    }

    public Module getModule() {
        return new ModuleTest();
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void testSomething() {
        assertTrue(true);
    }

    @Test
    public void testConnect() throws InterruptedException {
        assertFalse(clientModel.isConnected());
        connect();
        assertTrue(clientModel.isConnected());
        assertEquals("testLogin", clientModel.getName());
        window.textBox(IClientView.LOGIN_FIED).requireNotEditable();
        assertEquals("Disconnect", window.button(IClientView.BUTTON_CONNECT).text());
        assertEquals(2,window.list(IClientView.STOCK_LIST).contents().length);
        List<StockOption> stockOptions = clientView.getStockOptions();
        assertEquals(2, stockOptions.size());
        assertEquals(option1,stockOptions.get(0));
        assertEquals(option2,stockOptions.get(1));
    }

    @Test
    public void testDisconnect() throws InterruptedException {
        connect();
        window.button(IClientView.BUTTON_CONNECT).click();
        window.textBox(IClientView.LOGIN_FIED).requireEditable();
        assertEquals("Connect", window.button(IClientView.BUTTON_CONNECT).text());
        assertEquals(0,window.list(IClientView.STOCK_LIST).contents().length);
        List<StockOption> stockOptions = clientView.getStockOptions();
        assertEquals(0, stockOptions.size());
    }

    private void connect() {
        window.textBox(IClientView.LOGIN_FIED).enterText("testLogin");
        window.button(IClientView.BUTTON_CONNECT).click();
    }

    @Test
    public void testSubscribeFirst() {
        connect();
        window.list(IClientView.STOCK_LIST).selectItem(0);
        assertEquals(1, clientView.getSelectedStocksOptions().size());
        assertEquals(option1, clientView.getSelectedStocksOptions().get(0));
        window.button(IClientView.BUTTON_SUBSCRIBE).click();
        assertEquals(1, clientModel.getSubscribed().size());
        assertEquals(option1, clientModel.getSubscribed().get(0));
    }

    @Test
    public void testSubscribeSecond() {
        connect();
        window.list(IClientView.STOCK_LIST).selectItem(1);
        assertEquals(1, clientView.getSelectedStocksOptions().size());
        assertEquals(option2, clientView.getSelectedStocksOptions().get(0));
        window.button(IClientView.BUTTON_SUBSCRIBE).click();
        assertEquals(1, clientModel.getSubscribed().size());
        assertEquals(option2, clientModel.getSubscribed().get(0));
    }

    @Test
    public void testSubscribeBoth() {
        connect();
        window.list(IClientView.STOCK_LIST).selectItems(0, 1);
        assertEquals(2, clientView.getSelectedStocksOptions().size());
        assertEquals(option1, clientView.getSelectedStocksOptions().get(0));
        assertEquals(option2, clientView.getSelectedStocksOptions().get(1));
        window.button(IClientView.BUTTON_SUBSCRIBE).click();
        assertEquals(2, clientModel.getSubscribed().size());
        assertEquals(option1, clientModel.getSubscribed().get(0));
        assertEquals(option2, clientModel.getSubscribed().get(1));
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