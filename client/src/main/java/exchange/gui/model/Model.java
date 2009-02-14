package exchange.gui.model;

import exchange.model.StockOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of model
 */
public class Model implements IModel {

    private String name;

    private List<StockOption> subscribed;

    private boolean connected;

    public Model() {
        subscribed = new ArrayList<StockOption>();
    }

    public void subscribe(List<StockOption> list) {
        subscribed.addAll(list);
    }

    public void unsubscribe(List<StockOption> list) {
        subscribed.removeAll(list);
    }

    public String connect(String name) {
        this.name = name;
        connected = true;
        return null;
    }

    public void disconnect() {
        connected = false;
    }

    public boolean isConnected() {
        return connected;
    }
}
