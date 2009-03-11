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

package exchange.ejb;

import exchange.model.StockOption;

import javax.ejb.Local;
import java.util.List;

/**
 * Interface of the ejb server
 */
@Local
public interface StockOptionEjbLocal
{
    String STOCK_OPTION_EJB = "StockOptionEjbImplBusinessLocal";

    /**
     * Create the new given stock option
     *
     * @param stockOption Stock Option to create
     */
    void createNewStockOption(StockOption stockOption);

    /**
     * Delete the given stock option
     *
     * @param stockOption Stock option to delete
     */
    void deleteStockOption(List<StockOption> stockOption);

    /**
     * Get the stock option list of the server
     *
     * @return Stock option list
     */
    List<StockOption> getStockOptionList();

    /**
     * Call for the change on all quotes
     */
    void changesQuotes();

    void deleteStockOption(StockOption stockOption);
}
