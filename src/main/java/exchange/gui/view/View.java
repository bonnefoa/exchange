package exchange.gui.view;

import exchange.model.StockOption;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the view
 */
public class View extends JFrame implements IView
{

    /**
     * Subscribe button
     */
    private JButton subscribe;
    /**
     * Unsubscribe button
     */
    private JButton unsubscribe;
    /**
     * List of stock option
     */
    private JList stockList;
    /**
     * Text area
     */
    private JTextArea textArea;
    /**
     * Connect button
     */
    private JButton connect;
    /**
     * Disconnect button
     */
    private JButton disconnect;
    /**
     * Login field
     */
    private JTextField login;

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new View();
            }
        });
    }

    /**
     * Create the GUI and show it.
     */
    public View()
    {
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
//
//    /**
//     * initialise the listeners on events
//     */
//    public void initListeners()
//    {
//        subscribe.addMouseListener(new MouseAdapter()
//        {
//            @Override
//            public void mouseClicked(MouseEvent e)
//            {
//                subscribe();
//            }
//        });
//    }


    private void addComponentsToPane(Container pane)
    {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 2, 2, 2);
        Dimension dimButton = new Dimension(30, 20);

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
        c.gridx = 1;
        c.gridwidth = 2;
        pane.add(textArea, c);

        //Second row
        c.gridwidth = 1;
        c.gridy = 2;
        c.weighty = 0;
        c.weightx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        //Subscribe button
        c.gridx = 0;
        subscribe = new JButton("Subscribe");
        unsubscribe = new JButton("Unsubscribe");
        subscribe.setPreferredSize(dimButton);
        unsubscribe.setPreferredSize(dimButton);
        pane.add(subscribe, c);
        pane.add(unsubscribe, c);

        //Login field
        c.gridx = 1;
        c.weightx = 0.5;
        login = new JTextField();
        login.setMinimumSize(new Dimension(40, 20));
        pane.add(login, c);

        //Connect button
        c.gridx = 2;
        connect = new JButton("Connect");
        connect.setPreferredSize(dimButton);
        pane.add(connect, c);
        disconnect = new JButton("Disconnect");
        disconnect.setPreferredSize(dimButton);
        pane.add(disconnect, c);
    }

    public void displayMessageQuote(String message)
    {
        textArea.append(message + newline);
    }

    public void displayStockOptions(List<StockOption> stockOptionList)
    {
        stockList.setListData(stockOptionList.toArray());
    }

    public List<StockOption> getSelectedStocksOption()
    {
        StockOption tabsStockOptions[] = (StockOption[]) stockList.getSelectedValues();
        return Arrays.asList(tabsStockOptions);
    }

    public String getLoginName()
    {
        return login.getText();
    }


}
