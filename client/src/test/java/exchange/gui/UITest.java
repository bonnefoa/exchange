package exchange.gui;

import com.google.inject.Inject;
import com.google.inject.Module;
import exchange.BaseClass;
import exchange.gui.controller.IController;
import exchange.gui.model.IModel;
import exchange.gui.view.IView;
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
public class UITest extends BaseClass {
    private FrameFixture window;

    private IView view;

    private IModel model;

    private IController controller;

    private StockOption option1;
    private StockOption option2;

    @Before
    public void setUp() {
        super.setUp();
        window = new FrameFixture((Frame) view);
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
        assertFalse(model.isConnected());
        connect();
        assertTrue(model.isConnected());
        assertEquals("testLogin", model.getName());
        window.textBox(IView.LOGIN_FIED).requireNotEditable();
        assertEquals("Disconnect", window.button(IView.BUTTON_CONNECT).text());
        assertEquals(2,window.list(IView.STOCK_LIST).contents().length);        
    }

    @Test
    public void testDisconnect() throws InterruptedException {
        connect();
        window.button(IView.BUTTON_CONNECT).click();
        window.textBox(IView.LOGIN_FIED).requireEditable();        
        assertEquals("Connect", window.button(IView.BUTTON_CONNECT).text());
        assertEquals(0,window.list(IView.STOCK_LIST).contents().length);
    }

    private void connect() {
        window.textBox(IView.LOGIN_FIED).enterText("testLogin");
        window.button(IView.BUTTON_CONNECT).click();
    }

    @Test
    public void testSubscribeFirst() {
        connect();
        window.list(IView.STOCK_LIST).selectItem(0);
        assertEquals(1, view.getSelectedStocksOption().size());
        assertEquals(option1, view.getSelectedStocksOption().get(0));
        window.button(IView.BUTTON_SUBSCRIBE).click();
        assertEquals(1, model.getSubscribed().size());
        assertEquals(option1, model.getSubscribed().get(0));
    }

    @Test
    public void testSubscribeSecond() {
        connect();
        window.list(IView.STOCK_LIST).selectItem(1);
        assertEquals(1, view.getSelectedStocksOption().size());
        assertEquals(option2, view.getSelectedStocksOption().get(0));
        window.button(IView.BUTTON_SUBSCRIBE).click();
        assertEquals(1, model.getSubscribed().size());
        assertEquals(option2, model.getSubscribed().get(0));
    }

    @Test
    public void testSubscribeBoth() {
        connect();
        window.list(IView.STOCK_LIST).selectItems(0, 1);
        assertEquals(2, view.getSelectedStocksOption().size());
        assertEquals(option1, view.getSelectedStocksOption().get(0));
        assertEquals(option2, view.getSelectedStocksOption().get(1));
        window.button(IView.BUTTON_SUBSCRIBE).click();
        assertEquals(2, model.getSubscribed().size());
        assertEquals(option1, model.getSubscribed().get(0));
        assertEquals(option2, model.getSubscribed().get(1));
    }


    @Inject
    public void setView(IView view) {
        this.view = view;
    }

    @Inject
    public void setModel(IModel model) {
        this.model = model;
    }

    @Inject
    public void setController(IController controller) {
        this.controller = controller;
    }
}