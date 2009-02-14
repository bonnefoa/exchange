package exchange.guiceBinding;

import com.google.inject.AbstractModule;
import exchange.gui.controller.Controller;
import exchange.gui.controller.IController;
import exchange.gui.model.IModel;
import exchange.gui.model.Model;
import exchange.gui.view.IView;
import exchange.gui.view.View;

/**
 * Main module for injection
 */
public class MainModule extends AbstractModule {
    protected void configure() {
        bind(IView.class).to(View.class);
        bind(IModel.class).to(Model.class);
        bind(IController.class).to(Controller.class);
    }
}
