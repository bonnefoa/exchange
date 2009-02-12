package exchange.gui.controller;

import com.google.inject.Inject;
import exchange.gui.model.IModel;
import exchange.gui.view.IView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Presenter linking the view and the controller
 */
public class Controller implements IController
{
    private IModel model;

    private IView view;

    private void subscribeHandler()
    {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void connectHandler()
    {
        //To change body of created methods use File | Settings | File Templates.
    }

    @Inject
    public void setModel(IModel model)
    {
        this.model = model;
    }

    @Inject
    public void setView(IView view)
    {
        this.view = view;
    }

    private void initListener()
    {
        view.initListeners(
                new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        subscribeHandler();
                    }
                }
                ,
                new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        connectHandler();
                    }
                }
        );

    }

}
