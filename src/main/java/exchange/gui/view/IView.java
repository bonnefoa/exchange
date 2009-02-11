package exchange.gui.view;

import exchange.model.StockOption;

import java.util.List;

/**
 * Interface for the view
 */
public interface IView {
    void displayMessageQuote(String message);
    void displayStockOptions(List<StockOption> stockOptionList);    

    


}
