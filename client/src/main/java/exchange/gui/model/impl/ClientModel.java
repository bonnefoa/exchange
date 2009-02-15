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

import exchange.model.StockOption;
import exchange.gui.model.IClientModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of model
 */
public class ClientModel implements IClientModel {

    private String name;

    private List<StockOption> subscribed;

    private List<StockOption> stockOptionDisplayed;

    private boolean connected;

    public ClientModel() {
        subscribed = new ArrayList<StockOption>();
        stockOptionDisplayed = new ArrayList<StockOption>();
    }

    public void subscribe(List<StockOption> list) {
        subscribed.addAll(list);
        // TODO Subscribe to serveur
    }

    public void unsubscribe(List<StockOption> list) {
        subscribed.removeAll(list);
        // TODO Unsubscribe to serveur
    }

    public String connect(String name) {
        // TODO Connect to the server
        this.name = name;
        connected = true;
        stockOptionDisplayed = getStockOptionsFromServer();
        return name;
    }

    public void disconnect() {
        connected = false;
        subscribed.clear();
        stockOptionDisplayed.clear();
        // TODO Disconnect of the server
    }

    public boolean isConnected() {
        return connected;
    }

    public String getName() {
        return name;
    }

    public List<StockOption> getStockOptionsFromServer() {
        // TODO Get stocks from serveur
        return null;
    }

    public List<StockOption> getStockOptionDisplayed() {
        return stockOptionDisplayed;
    }

    public List<StockOption> getSubscribed() {
        return subscribed;
    }
}
