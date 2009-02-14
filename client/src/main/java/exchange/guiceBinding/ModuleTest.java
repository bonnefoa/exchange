package exchange.guiceBinding;

import com.google.inject.AbstractModule;
import exchange.gui.controller.Controller;
import exchange.gui.controller.IController;
import exchange.gui.model.MockModel;
import exchange.gui.model.IModel;
import exchange.gui.view.IView;
import exchange.gui.view.View;

/**
 * Module  Guice for testing
 */
public class ModuleTest extends AbstractModule {
    protected void configure() {
        bind(IView.class).to(View.class).asEagerSingleton();
        bind(IModel.class).to(MockModel.class).asEagerSingleton();
        bind(IController.class).to(Controller.class);
    }
}
