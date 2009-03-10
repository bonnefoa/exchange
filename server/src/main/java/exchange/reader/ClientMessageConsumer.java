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

import javax.ejb.MessageDriven;
import javax.ejb.EJB;
import javax.jms.*;
import java.util.logging.Logger;

/**
 * Message consumer for queue message of the stock option
 */
@MessageDriven(name = "StockOptionTopic")
public class ClientMessageConsumer implements MessageListener
{

    @EJB
    StockOptionTopicReaderLocal stockOptionTopicReader;

    private static final Logger logger = Logger.getLogger(ClientMessageConsumer.class.getCanonicalName());

    public void onMessage(Message message)
    {
        /*
        if (message instanceof ObjectMessage)
        {
            try
            {
                StockOptionMessage stockOptionMessage = (StockOptionMessage) ((ObjectMessage) message).getObject();
                stockOptionTopicReader.messageReceived(stockOptionMessage);
            }
            catch (JMSException e)
            {
                e.printStackTrace();
            }
        }
        */
    }
}
