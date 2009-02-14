package exchange.gui.model;

import com.google.inject.Inject;
import exchange.gui.model.IModel;
import exchange.gui.model.Model;
import exchange.gui.view.IView;
import exchange.model.StockOption;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

/**
 * Presenter linking the view and the controller
 */
public class MockModel extends Model {

    @Override
    public List<StockOption> getStockOptions() {
        ArrayList<StockOption> res = new ArrayList<StockOption>();
        res.add(new StockOption("titre", "company", 15));
        res.add(new StockOption("titre2", "company2", 30));
        return res;
    }
}