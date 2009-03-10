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

package exchange.reader;

import exchange.ejb.StockOptionTopicReaderLocal;
import exchange.message.StockOptionMessage;

import javax.ejb.Singleton;
import java.util.Observable;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Mar 8, 2009
 * Time: 5:34:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class StockOptionTopicReaderBean extends Observable implements StockOptionTopicReaderLocal
{
    public void messageReceived(StockOptionMessage stockOptionMessage)
    {
        setChanged();
        System.out.println("StockOptionTopicReaderBean : message " + stockOptionMessage.getMessageType());
        notifyObservers(stockOptionMessage);
        System.out.println("<< " + stockOptionMessage.getMessageType() + " for stock option \"" + stockOptionMessage.getStockOption() + "\" received, " + countObservers() + " observer(s) notified");
    }

}
