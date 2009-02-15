package exchange.gui.controller;

import com.google.inject.Inject;
import exchange.gui.model.IModel;
import exchange.gui.view.IView;
import exchange.model.StockOption;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Presenter linking the view and the controller
 */
public class Controller implements IController {
    private IModel model;

    private IView view;

    @Inject
    public Controller(IModel model, IView view) {
        this.model = model;
        this.view = view;
        initListener();
    }

    private void subscribeHandler() {
        model.subscribe(view.getSelectedStocksOption());
    }

    private void connectHandler() {
        if (model.isConnected()) {
            model.disconnect();
            view.displayStockOptions(new ArrayList<StockOption>());
            view.setLoginFieldEditable(true);
            view.setTextButtonConnect("Connect");
        } else {
            String name = view.getLoginName();
            model.connect(name);
            view.displayStockOptions(model.getStockOptionsFromServer());
            view.setLoginFieldEditable(false);
            view.setTextButtonConnect("Disconnect");
        }
    }

    private void unsubscribeHandler() {
        model.unsubscribe(view.getSelectedStocksOption());
    }

    private void initListener() {
        view.initListeners(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        connectHandler();
                    }
                }
                ,
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        subscribeHandler();
                    }
                },
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        unsubscribeHandler();
                    }
                });
    }

    public void warnSubscribed(StockOption stockOption) {
        view.displayMessageQuote(stockOption.toString() + ':' + stockOption.getQuote());
    }
}
