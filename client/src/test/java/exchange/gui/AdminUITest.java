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
import com.google.inject.Inject;
import exchange.BaseClass;
import exchange.gui.controller.IAdminController;
import exchange.gui.model.IAdminModel;
import exchange.gui.view.IAdminView;
import exchange.gui.view.IClientView;
import exchange.guiceBinding.ModuleTest;
import exchange.model.StockOption;
import org.fest.swing.fixture.FrameFixture;
import static org.fest.assertions.Fail.fail;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

/**
 * Junit class
 */
public class AdminUITest extends BaseClass {

    private FrameFixture window;

    private IAdminView adminView;

    private IAdminModel adminModel;

    private IAdminController adminController;

    private StockOption option1;
    private StockOption option2;

    public Module getModule() {
        return new ModuleTest();
    }

    @Before
    public void setUp() {
        super.setUp();
        window = new FrameFixture((Frame) adminView);
        window.show();
        option1 = new StockOption("titre", "company", 15);
        option2 = new StockOption("titre2", "company2", 30);
    }


    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void testInitialOptions() {
        assertEquals(2, window.list(IAdminView.STOCK_LIST).contents().length);
        assertEquals(option1, adminModel.getStockOptionList().get(0));
        assertEquals(option2, adminModel.getStockOptionList().get(1));
        assertEquals(0,window.textBox(IAdminView.TEXT_AREA_COMPANY_NAME).text().length());
        assertEquals(0,window.textBox(IAdminView.TEXT_AREA_TITLE_NAME).text().length());
        assertEquals(0,window.textBox(IAdminView.TEXT_AREA_QUOTE).text().length());
    }

    @Test
    public void testDeleteFirstOption() {
        window.list(IAdminView.STOCK_LIST).selectItem(0);
        window.button(IAdminView.BUTTON_DELETE).click();

        assertEquals(1,adminModel.getStockOptionList().size());
        assertEquals(option2,adminModel.getStockOptionList().get(0));
        assertEquals(1, window.list(IAdminView.STOCK_LIST).contents().length);
    }

    @Test
    public void testDeleteSecondOption() {
        window.list(IAdminView.STOCK_LIST).selectItem(1);
        window.button(IAdminView.BUTTON_DELETE).click();

        assertEquals(1,adminModel.getStockOptionList().size());
        assertEquals(option1,adminModel.getStockOptionList().get(0));
        assertEquals(1, window.list(IAdminView.STOCK_LIST).contents().length);
    }

    @Test
    public void testDeleteBothOptions() {
        window.list(IAdminView.STOCK_LIST).selectItems(0,1);
        window.button(IAdminView.BUTTON_DELETE).click();

        assertEquals(0,adminModel.getStockOptionList().size());
        assertEquals(0, window.list(IAdminView.STOCK_LIST).contents().length);
    }

    @Test
    public void testAddOption() {
        StockOption stockTest = new StockOption("TitleNameTest", "CompanyNameUnitTest", 55);
        completeAreaText(stockTest);
        window.button(IAdminView.BUTTON_CREATE).click();

        assertEquals(3,adminModel.getStockOptionList().size());
        assertEquals(stockTest,adminModel.getStockOptionList().get(2));

        assertEquals(3, window.list(IAdminView.STOCK_LIST).contents().length);
    }

    @Test
    public void testAddOptionWithExistingName() {
        StockOption stockTest = new StockOption(option1.getTitle(), option1.getCompany(), 55);
        completeAreaText(stockTest);
        window.button(IAdminView.BUTTON_CREATE).click();
        fail("Find how to check an error");
    }

    private void completeAreaText(StockOption stockTest) {
        window.textBox(IAdminView.TEXT_AREA_COMPANY_NAME).enterText(stockTest.getCompany());
        window.textBox(IAdminView.TEXT_AREA_TITLE_NAME).enterText(stockTest.getTitle());
        window.textBox(IAdminView.TEXT_AREA_QUOTE).enterText(stockTest.getQuote()+"");
    }

    @Test
    public void testAddOptionWithoutName() {
        StockOption stockTest = new StockOption("", "company", 55);
        completeAreaText(stockTest);
        window.button(IAdminView.BUTTON_CREATE).click();
        fail("Find how to check an error");
    }

    @Test
    public void testAddOptionWithoutCompanyName() {
        StockOption stockTest = new StockOption("title", "", 55);
        completeAreaText(stockTest);
        fail("Find how to check an error");
    }

    @Test
    public void testAddOptionWithoutQuote() {
        StockOption stockTest = new StockOption("title", "company", 0);
        completeAreaText(stockTest);
        fail("Find how to check an error");
    }

    @Inject
    public void setAdminView(IAdminView adminView) {
        this.adminView = adminView;
    }

    @Inject
    public void setAdminModel(IAdminModel adminModel) {
        this.adminModel = adminModel;
    }

    @Inject
    public void setAdminController(IAdminController adminController) {
        this.adminController = adminController;
    }
}
