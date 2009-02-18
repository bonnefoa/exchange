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

package exchange.gui.controller;

import exchange.model.StockOption;
import exchange.gui.view.impl.GlobalFrame;

import java.util.Observable;
import java.util.Observer;

/**
 * Interface for the controller
 */
public interface IClientController  extends IAbstractController{
    String INCORRECT_PASSWORD = "Incorrect password";
    String PASSWORD = "adminadmin";

    /**
     * Warn the client of the new state of the option
     *
     * @param stockOption Stock option to update
     */
    void warnSubscribed(StockOption stockOption);

    /**
     * Delete the current stockOption from the list
     *
     * @param stockOption Stock option to delete of the list
     */
    void deleteStockOptions(StockOption stockOption);

    /**
     * Add the given stock option to the list
     *
     * @param stockOption Stock option to add
     */
    void addStockOption(StockOption stockOption);
}
