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

package exchange.gui.model;

import exchange.model.StockOption;

import java.util.List;

/**
 * Interface of the model
 */
public interface IClientModel {

    /**
     * Subscribe to the selected list
     *
     * @param list List of stock options to subscribe
     */
    void subscribe(List<StockOption> list);

    /**
     * Unsubscribe to the selected list
     *
     * @param list List of stockOptions to unsubscribe
     */
    void unsubscribe(List<StockOption> list);

    /**
     * Connect the client
     *
     * @param name Name of the client
     * @return
     */
    String connect(String name);

    /**
     * Disconnect the client
     */
    void disconnect();

    /**
     * State of the connection
     *
     * @return
     */
    boolean isConnected();

    /**
     * Get the name of the user
     *
     * @return Name of the user
     */
    String getName();

    /**
     * Get the list of stockOptions
     *
     * @return List of stock options
     */
    List<StockOption> getStockOptionsFromServer();

    /**
     * Get the list of subscribed options of the client
     *
     * @return List of subscribed options
     */
    List<StockOption> getSubscribed();

}
