package exchange.gui.presenter;

import exchange.gui.model.IModel;
import exchange.gui.view.IView;
import com.google.inject.Inject;

/**
 * Presenter linking the view and the controller
 */
public class Presenter {
    private IModel model;

    private IView view;

    @Inject
    public void setModel(IModel model) {
        this.model = model;
    }

    @Inject
    public void setView(IView view) {
        this.view = view;
    }
}
