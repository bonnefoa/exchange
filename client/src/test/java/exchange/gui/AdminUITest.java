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

import com.google.inject.Module;
import exchange.BaseClass;
import exchange.gui.model.IAdminModel;
import exchange.gui.view.IAdminView;
import exchange.guiceBinding.ModuleTestGuice;
import exchange.model.StockOption;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

/**
 * Junit class
 */
public class AdminUITest extends BaseClass
{

    private FrameFixture window;

    private IAdminView adminView;

    private IAdminModel adminModel;

    private StockOption option1 = new StockOption("titre", "company", 15);
    private StockOption option2 = new StockOption("titre2", "company2", 30);

    public Module getModule()
    {
        return new ModuleTestGuice();
    }

    @Before
    public void setUp() throws InterruptedException
    {
        super.setUp();
        adminView = injector.getInstance(IAdminView.class);
        adminModel = injector.getInstance(IAdminModel.class);
        window = new FrameFixture((Frame) adminView);
        adminView.setVisible(true);
        window.show();
        assertEquals(2, window.list(IAdminView.STOCK_LIST).contents().length);
    }

    @After
    public void tearDown() throws InterruptedException
    {
        super.tearDown();
        window.cleanUp();
        adminView = null;
        adminModel = null;
    }

    @Test
    public void testInitialOptions()
    {
        assertEquals(option1, adminModel.getStockOptionList().get(0));
        assertEquals(option2, adminModel.getStockOptionList().get(1));
        assertEquals(0, window.textBox(IAdminView.TEXT_AREA_COMPANY_NAME).text().length());
        assertEquals(0, window.textBox(IAdminView.TEXT_AREA_TITLE_NAME).text().length());
        assertEquals(0, window.textBox(IAdminView.TEXT_AREA_QUOTE).text().length());
    }

    @Test
    public void testDeleteFirstOption()
    {
        window.list(IAdminView.STOCK_LIST).selectItem(0);
        window.button(IAdminView.BUTTON_DELETE).click();
        assertEquals(1, adminModel.getStockOptionList().size());
        assertEquals(option2, adminModel.getStockOptionList().get(0));
        assertEquals(1, window.list(IAdminView.STOCK_LIST).contents().length);
    }

    @Test
    public void testDeleteSecondOption()
    {
        window.list(IAdminView.STOCK_LIST).selectItem(1);
        window.button(IAdminView.BUTTON_DELETE).click();

        assertEquals(1, adminModel.getStockOptionList().size());
        assertEquals(option1, adminModel.getStockOptionList().get(0));
        assertEquals(1, window.list(IAdminView.STOCK_LIST).contents().length);
    }

    @Test
    public void testDeleteBothOptions()
    {
        window.list(IAdminView.STOCK_LIST).selectItems(0, 1);
        window.button(IAdminView.BUTTON_DELETE).click();
        assertEquals(0, adminModel.getStockOptionList().size());
        assertEquals(0, window.list(IAdminView.STOCK_LIST).contents().length);
    }

    @Test
    public void testAddOption()
    {
        StockOption stockTest = new StockOption("test", "test", 55 + "");
        completeAreaText(stockTest.getTitle(), stockTest.getCompany(), stockTest.getQuote());
        window.button(IAdminView.BUTTON_CREATE).click();

        assertEquals(3, adminModel.getStockOptionList().size());
        assertEquals(stockTest, adminModel.getStockOptionList().get(2));

        assertEquals(3, window.list(IAdminView.STOCK_LIST).contents().length);
    }

    @Test
    public void testAddOptionWithExistingName()
    {
        completeAreaText(option1.getTitle(), option1.getCompany(), 55 + "");
        window.button(IAdminView.BUTTON_CREATE).click();
        window.optionPane().requireErrorMessage();
    }

    private void completeAreaText(String title, String company, float quote)
    {
        completeAreaText(title, company, quote + "");
    }

    private void completeAreaText(String title, String company, String quote)
    {
        window.textBox(IAdminView.TEXT_AREA_COMPANY_NAME).enterText(company);
        window.textBox(IAdminView.TEXT_AREA_TITLE_NAME).enterText(title);
        if (quote != null)
        {
            window.textBox(IAdminView.TEXT_AREA_QUOTE).enterText(quote);
        }
    }

    @Test
    public void testAddOptionWithoutName()
    {
        completeAreaText("", "company", 55 + "");
        window.button(IAdminView.BUTTON_CREATE).click();
        window.optionPane().requireErrorMessage().requireMessage(StockOption.TITLE_NAME_EMPTY);
    }

    @Test
    public void testAddOptionWithoutCompanyName()
    {
        completeAreaText("title", "", 55 + "");
        window.button(IAdminView.BUTTON_CREATE).click();
        window.optionPane().requireErrorMessage().requireMessage(StockOption.COMPANY_NAME_EMPTY);
    }

    @Test
    public void testAddOptionWithZeroQuote()
    {
        completeAreaText("title", "company", 0 + "");
        window.button(IAdminView.BUTTON_CREATE).click();
        window.optionPane().requireErrorMessage().requireMessage(StockOption.QUOTE_INVALID);
    }

    @Test
    public void testAddOptionWithInvalidNegativeQuote()
    {
        completeAreaText("title", "company", -99 + "");
        window.button(IAdminView.BUTTON_CREATE).click();
        window.optionPane().requireErrorMessage().requireMessage(StockOption.QUOTE_INVALID);
    }

    @Test
    public void testAddOptionWithInvalidStringQuote()
    {
        completeAreaText("title", "company", "tes");
        window.button(IAdminView.BUTTON_CREATE).click();
        window.optionPane().requireErrorMessage().requireMessage(StockOption.QUOTE_INVALID);
    }

    @Test
    public void testAddOptionWithNullQuote()
    {
        completeAreaText("title", "company", null);
        window.button(IAdminView.BUTTON_CREATE).click();
        window.optionPane().requireErrorMessage().requireMessage(StockOption.QUOTE_INVALID);
    }

    @Test
    public void testAddOptionWithTwoErrors()
    {
        completeAreaText("title", null, null);
        window.button(IAdminView.BUTTON_CREATE).click();
        window.optionPane().requireErrorMessage().requireMessage(StockOption.COMPANY_NAME_EMPTY +
                StockOption.QUOTE_INVALID);
    }

    @Test
    public void testAddOptionWithThreeErrors()
    {
        completeAreaText(null, null, null);
        window.button(IAdminView.BUTTON_CREATE).click();
        window.optionPane().requireErrorMessage().requireMessage(StockOption.TITLE_NAME_EMPTY +
                StockOption.COMPANY_NAME_EMPTY +
                StockOption.QUOTE_INVALID);
    }
}
