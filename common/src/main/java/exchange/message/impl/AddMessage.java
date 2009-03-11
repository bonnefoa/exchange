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

package exchange.message.impl;

import exchange.message.MessageType;
import exchange.message.StockOptionMessage;
import exchange.model.StockOption;

/**
 * A message indicating a new stock option.
 */
public class AddMessage implements StockOptionMessage
{
    private StockOption stockOption;

    public StockOption getStockOption()
    {
        return stockOption;
    }

    public void setStockOption(StockOption stockOption)
    {
        this.stockOption = stockOption;
    }

    public MessageType getMessageType()
    {
        return MessageType.ADD;
    }

    @Override
    public String toString()
    {
        return "AddMessage{" +
                "stockOption=" + stockOption +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof AddMessage)) return false;

        AddMessage that = (AddMessage) o;

        if (stockOption != null ? !stockOption.equals(that.stockOption) : that.stockOption != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return stockOption != null ? stockOption.hashCode() : 0;
    }
}