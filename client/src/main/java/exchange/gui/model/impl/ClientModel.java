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

package exchange.gui.model.impl;

import exchange.ejb.StockOptionEjbLocal;
import exchange.gui.model.IClientModel;
import exchange.model.StockOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of model
 */
public class ClientModel implements IClientModel
{

    private String name;

    private List<StockOption> subscribed;

    private StockOptionEjbLocal stockOptionEjb;

    private boolean connected;

    public ClientModel(StockOptionEjbLocal stockOptionEjb)
    {
        this.stockOptionEjb = stockOptionEjb;
        subscribed = new ArrayList<StockOption>();
    }

    public void subscribe(List<StockOption> list)
    {
        subscribed.addAll(list);
    }

    public void unsubscribe(List<StockOption> list)
    {
        subscribed.removeAll(list);
    }

    public String connect(String name)
    {
        // TODO Connect to the server
        this.name = name;
        connected = true;
        return name;
    }

    public void disconnect()
    {
        connected = false;
        subscribed.clear();
        // TODO Disconnect of the server
    }

    public boolean isConnected()
    {
        return connected;
    }

    public String getName()
    {
        return name;
    }

    public List<StockOption> getStockOptionDisplayed()
    {
        return stockOptionEjb.getStockOptionList();
    }

    public List<StockOption> getSubscribed()
    {
        return subscribed;
    }
}
