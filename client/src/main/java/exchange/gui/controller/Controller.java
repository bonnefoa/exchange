package exchange.gui.controller;

import com.google.inject.Inject;
import exchange.gui.model.IModel;
import exchange.gui.view.IView;
import exchange.model.StockOption;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Presenter linking the view and the controller
 */
public class Controller implements IController {
    private IModel model;

    private IView view;

    private void subscribeHandler() {
        List<StockOption> list = view.getSelectedStocksOption();
        model.subscribe(list);
    }

    private void connectHandler() {
        if (model.isConnected()) {
            model.disconnect();
        } else {
            String name = view.getLoginName();
            model.connect(name);
        }
    }

    private void unsubscribeHandler() {
        List<StockOption> list = view.getSelectedStocksOption();
        model.unsubscribe(list);
    }

    @Inject
    public void setModel(IModel model) {
        this.model = model;
    }

    @Inject
    public void setView(IView view) {
        this.view = view;
    }

    private void initListener() {
        view.initListeners(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        subscribeHandler();
                    }
                }
                ,
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        connectHandler();
                    }
                },
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        unsubscribeHandler();
                    }
                });

    }

}
