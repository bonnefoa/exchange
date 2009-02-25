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
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for ejb
 */
public class StockOptionEjbTest {
    IStockOptionEjb stockOptionEjb;

    @Before
    public void setUp() {
        stockOptionEjb = new StockOptionEjb();
        StockOption option1 = new StockOption("titre", "company", 15);
        StockOption option2 = new StockOption("titre2", "company2", 30);
        stockOptionEjb.createNewStockOption(option1);
        stockOptionEjb.createNewStockOption(option2);
    }


    @Test
    public void testChangesQuotesDontChange() {
        for (int i = 0; i < 1000; i++) {
            float oldQuote = stockOptionEjb.getStockOptionList().get(0).getQuote();
            stockOptionEjb.changesQuotes();
            if (oldQuote == stockOptionEjb.getStockOptionList().get(0).getQuote()) {
                return;
            }
        }
        fail("Should not passe here");
    }

    @Test
    public void testChangesQuotesGoUp() {
        for (int i = 0; i < 1000; i++) {
            float oldQuote = stockOptionEjb.getStockOptionList().get(0).getQuote();
            stockOptionEjb.changesQuotes();
            if (oldQuote > stockOptionEjb.getStockOptionList().get(0).getQuote()) {
                return;
            }
        }
        fail("Should not passe here");
    }

    @Test
    public void testChangesQuotesGoDown() {
        for (int i = 0; i < 1000; i++) {
            float oldQuote = stockOptionEjb.getStockOptionList().get(0).getQuote();
            stockOptionEjb.changesQuotes();
            if (oldQuote < stockOptionEjb.getStockOptionList().get(0).getQuote()) {
                return;
            }
        }
        fail("Should not passe here");
    }

}
