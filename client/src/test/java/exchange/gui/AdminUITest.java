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
import exchange.gui.controller.IAdminController;
import exchange.gui.model.IAdminModel;
import exchange.gui.view.IAdminView;
import exchange.guiceBinding.ModuleTest;
import exchange.model.StockOption;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import static org.junit.Assert.assertTrue;
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

    @Before
    public void setUp() {
        super.setUp();
        window = new FrameFixture((Frame) adminView);
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
    public void testConnect() {
    }
}
