package exchange.gui.view;

import exchange.model.StockOption;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * Interface for the view
 */
public interface IView {
    final static String newline = "\n";
    static final String LOGIN_FIED = "loginField";
    static final String STOCK_LIST = "stockList";
    static final String TEXT_AREA = "textArea";
    static final String BUTTON_SUBSCRIBE = "buttonSubscribe";
    static final String BUTTON_UNSUBSCRIBE = "buttonUnsubscribe";
    static final String LABEL_LOGIN = "labelLogin";
    static final String BUTTON_CONNECT = "buttonConnect";

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

    /**
     * Set the login field editable
     *
     * @param editable boolean to set the login editable or not
     */
    void setLoginFieldEditable(boolean editable);

    /**
     * Change the text on the connect button
     *
     * @param text Text to display
     */
    void setTextButtonConnect(String text);

    /**
     * Init the listeners for buttons
     *
     * @param connectListener
     * @param subscribeListener
     * @param unsubscribeListener
     */
    void initListeners(MouseListener connectListener, MouseListener subscribeListener, MouseListener unsubscribeListener);
}
