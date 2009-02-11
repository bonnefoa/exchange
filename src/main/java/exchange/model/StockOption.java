package exchange.model;

import java.rmi.Remote;

/**
 * Model  for stockOptions
 */
public class StockOption implements Remote {
    private String title;
    private String company;
    private float quote;

    StockOption(String title, String company, float quote) {
        this.title = title;
        this.company = company;
        this.quote = quote;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public float getQuote() {
        return quote;
    }

    public void setQuote(float value) {
        quote = value;
    }
}
