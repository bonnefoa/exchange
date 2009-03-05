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

package exchange.ejb.impl;

import exchange.ejb.IClientMessageConsumer;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Message consumer for queue message of the stock option
 */
@MessageDriven(mappedName = "jms/TestQueue")
public class ClientMessageConsumer implements IClientMessageConsumer
{
    private static final Logger logger = Logger.getLogger(ClientMessageConsumer.class.getCanonicalName());

    @Resource
    private MessageDrivenContext context;

    public void onMessage(Message message)
    {
        if (message instanceof TextMessage)
        {
            TextMessage textMessage = (TextMessage) message;
            try
            {
                logger.log(Level.INFO, "jms/TestQueue: " + textMessage.getText());
            } catch (JMSException e)
            {
                logger.log(Level.SEVERE, "Could not get the message body", e);
                context.setRollbackOnly();
            }
        }
    }
}
