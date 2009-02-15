package exchange.gui.model;

import exchange.model.StockOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of model
 */
public class Model implements IModel{

    private String name;

    private List<StockOption> subscribed;

    private boolean connected;

    public Model() {
        subscribed = new ArrayList<StockOption>();
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
        return name;
    }

    public void disconnect() {
        connected = false;
        subscribed.clear();
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

    public List<StockOption> getSubscribed() {
        return subscribed;
    }
}
