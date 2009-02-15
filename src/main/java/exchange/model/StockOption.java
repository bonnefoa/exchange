package exchange.model;

import java.rmi.Remote;
import java.io.Serializable;

/**
 * Model  for stockOptions
 */
public class StockOption implements Serializable {
    private String title;
    private String company;
    private float quote;

    public StockOption(String title, String company, float quote) {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(title).append(' ');
        sb.append(company);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockOption that = (StockOption) o;

        if (Float.compare(that.quote, quote) != 0) return false;
        if (!company.equals(that.company)) return false;
        if (!title.equals(that.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + company.hashCode();
        result = 31 * result + (quote != +0.0f ? Float.floatToIntBits(quote) : 0);
        return result;
    }
}
