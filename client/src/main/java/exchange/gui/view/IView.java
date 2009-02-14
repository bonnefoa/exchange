package exchange.gui.view;

import exchange.model.StockOption;

import java.awt.event.MouseListener;
import java.util.List;

/**
 * Interface for the view
 */
public interface IView
{
    final static String newline = "\n";

    /**
     * Display a message to the text area
     *
     * @param message Message to display
     */
    void displayMessageQuote(String message);

    /**
     * Display the list of stockOption
     *
     * @param stockOptionList List to display
     */
    void displayStockOptions(List<StockOption> stockOptionList);

    /**
     * Get the selected stocks options
     *
     * @return List of selected stock option
     */
    List<StockOption> getSelectedStocksOption();

    /**
     * Get the login entered
     *
     * @return Login entered
     */
    String getLoginName();

    void initListeners(MouseListener connectListener, MouseListener subscribeListener, MouseListener unsubscribeListener);
}
