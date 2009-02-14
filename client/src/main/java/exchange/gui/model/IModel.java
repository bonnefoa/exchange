package exchange.gui.model;

import exchange.model.StockOption;

import java.util.List;

/**
 * Interface of the model
 */
public interface IModel {

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
     * @return
     */
    boolean isConnected();
}
