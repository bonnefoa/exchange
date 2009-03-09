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

import exchange.message.StockOptionMessage;
import exchange.message.MessageType;
import exchange.model.StockOption;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 9 mars 2009
 * Time: 18:17:21
 * To change this template use File | Settings | File Templates.
 */
public class UpdateMessage implements StockOptionMessage
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
        return MessageType.UPDATE;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof UpdateMessage)) return false;

        UpdateMessage that = (UpdateMessage) o;

        if (stockOption != null ? !stockOption.equals(that.stockOption) : that.stockOption != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return stockOption != null ? stockOption.hashCode() : 0;
    }
}
