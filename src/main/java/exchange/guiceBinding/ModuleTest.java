package exchange.guiceBinding;

import com.google.inject.AbstractModule;
import exchange.gui.view.IView;
import exchange.gui.view.View;
import exchange.gui.model.IModel;
import exchange.gui.model.Model;

/**
 * Module  Guice for testing
 */
public class ModuleTest extends AbstractModule{
    protected void configure() {
        bind(IView.class).to(View.class);
        bind(IModel.class).to(Model.class);
    }
}
