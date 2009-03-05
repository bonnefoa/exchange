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
import exchange.gui.controller.IClientController;
import exchange.gui.view.IClientView;
import exchange.guiceBinding.ModuleTest;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

/**
 * Unit testing on the admin access
 */
public class AdminAccessTest extends BaseClass
{
    private FrameFixture window;

    private IClientView clientView;

    @Before
    public void setUp()
    {
        super.setUp();
        clientView = injector.getInstance(IClientView.class);
        window = new FrameFixture((Frame) clientView);
        window.show();
    }

    public Module getModule()
    {
        return new ModuleTest();
    }

    @Test
    public void testIncorrectPassword()
    {
        window.textBox(IClientView.ADMIN_FIED).enterText("gwrg");
        window.button(IClientView.BUTTON_CONNECT_ADMIN).click();
        window.optionPane().requireErrorMessage().requireMessage(IClientController.INCORRECT_PASSWORD);
    }

    @Test
    public void testNullPassword()
    {
        window.button(IClientView.BUTTON_CONNECT_ADMIN).click();
        window.optionPane().requireErrorMessage().requireMessage(IClientController.INCORRECT_PASSWORD);
    }

    @Test
    public void testSwitchToAdmin()
    {
        window.textBox(IClientView.ADMIN_FIED).enterText(IClientController.PASSWORD);
        window.button(IClientView.BUTTON_CONNECT_ADMIN).click();
    }

    @After
    public void tearDown()
    {
        super.tearDown();
        window.cleanUp();
    }
}