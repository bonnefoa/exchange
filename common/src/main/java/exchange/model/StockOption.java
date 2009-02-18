/*
 * Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package exchange.model;

import java.io.Serializable;

/**
 * Model  for stockOptions
 */
public class StockOption implements Serializable {
    private String title;
    private String company;
    private float quote;
    public static final String TITLE_NAME_EMPTY = "Title name empty.\n";
    public static final String COMPANY_NAME_EMPTY = "Company name empty.\n";
    public static final String QUOTE_INVALID = "Invalid quote.\n";

    public StockOption(String title, String company, float quote) {
        if (title.length() == 0 || company.length() == 0 || quote <= 0) {
            StringBuilder sb = new StringBuilder();
            if (title.length() == 0) sb.append(TITLE_NAME_EMPTY);
            if (company.length() == 0) sb.append(COMPANY_NAME_EMPTY);
            if (quote <= 0) sb.append(QUOTE_INVALID);
            throw new IllegalArgumentException(sb.toString());
        }
        this.title = title;
        this.company = company;
        this.quote = quote;
    }

    public StockOption(String titleName, String companyName, String quote) {
        this(titleName, companyName, Float.parseFloat(quote));
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

        return company.equals(that.company) && title.equals(that.title);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + company.hashCode();
        return result;
    }
}
