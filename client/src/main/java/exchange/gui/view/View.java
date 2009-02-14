package exchange.gui.view;

import exchange.model.StockOption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the view
 */
public class View extends JFrame implements IView {

    /**
     * Subscribe button
     */
    private JButton buttonSubscribe;
    /**
     * Unsubscribe button
     */
    private JButton buttonUnsubscribe;
    /**
     * List of stock option
     */
    private JList stockList;
    /**
     * Text area
     */
    private JTextArea textArea;
    /**
     * Connect and disconnect button
     */
    private JButton buttonConnect;
    /**
     * Login field
     */
    private JTextField login;

    /**
     * Create the GUI and show it.
     */
    public View() {
        //Create and set up the window.
        super("Exchange");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.setPreferredSize(new Dimension(600, 600));
        //Set up the content pane.
        addComponentsToPane(this.getContentPane());
        //Display the window.
        this.pack();
        this.setVisible(true);
    }

    /**
     * initialise the listeners on events
     *
     * @param connectListener
     * @param subscribeListener
     * @param unsubscribeListener
     */
    public void initListeners(MouseListener connectListener, MouseListener subscribeListener, MouseListener unsubscribeListener) {
        buttonConnect.addMouseListener(connectListener);
        buttonSubscribe.addMouseListener(subscribeListener);
        buttonUnsubscribe.addMouseListener(unsubscribeListener);
    }

    private void addComponentsToPane(Container pane) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 2, 2, 2);
        Dimension dimButton = new Dimension(30, 20);

        c.gridwidth = 2;
        //List
        stockList = new JList();
        stockList.setDragEnabled(true);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 1;
        pane.add(stockList, c);

        //Text area
        textArea = new JTextArea("Cours de la bourse");
        c.gridx = 2;
        pane.add(textArea, c);

        //Second row
        c.gridwidth = 1;
        c.gridy = 2;
        c.weighty = 0;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;

        //Subscribe button
        c.gridx = 0;
        buttonSubscribe = new JButton("Subscribe");
        buttonSubscribe.setPreferredSize(dimButton);
        pane.add(buttonSubscribe, c);

        c.gridx = 1;
        buttonUnsubscribe = new JButton("Unsubscribe");
        buttonUnsubscribe.setPreferredSize(dimButton);
        pane.add(buttonUnsubscribe, c);

        //Login field
        c.gridx = 2;
        login = new JTextField();
        login.setMinimumSize(new Dimension(40, 20));
        pane.add(login, c);

        //Connect button
        c.gridx = 3;
        buttonConnect = new JButton("Connect");
        buttonConnect.setPreferredSize(dimButton);
        pane.add(buttonConnect, c);
    }

    public void displayMessageQuote(String message) {
        textArea.append(message + newline);
    }

    public void displayStockOptions(List<StockOption> stockOptionList) {
        stockList.setListData(stockOptionList.toArray());
    }

    public List<StockOption> getSelectedStocksOption() {
        StockOption tabsStockOptions[] = (StockOption[]) stockList.getSelectedValues();
        return Arrays.asList(tabsStockOptions);
    }

    public String getLoginName() {
        return login.getText();
    }
}
