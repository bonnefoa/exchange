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

package exchange;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import exchange.gui.model.IAdminModel;
import exchange.model.StockOption;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

/**
 * Junit class
 */
public abstract class BaseClass
{
    protected Injector injector;

    private IAdminModel adminModel;

    @Before
    public void setUp() throws InterruptedException
    {
        injector = Guice.createInjector(getModule());
        adminModel = injector.getInstance(IAdminModel.class);
        adminModel.deleteStockOption(adminModel.getStockOptionList());
        assertEquals(0, adminModel.getStockOptionList().size());
        adminModel.createNewStockOption(new StockOption("titre", "company", 15));
        adminModel.createNewStockOption(new StockOption("titre2", "company2", 30));
        assertEquals(2, adminModel.getStockOptionList().size());
    }

    @After
    public void tearDown() throws InterruptedException
    {

    }

    public abstract Module getModule();

}
