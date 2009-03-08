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

package exchange.update;

import exchange.ejb.UpdateReaderLocal;
import exchange.model.StockOption;

import javax.annotation.Resource;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.jms.*;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Mar 8, 2009
 * Time: 5:34:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateful
public class UpdateReaderBean implements UpdateReaderLocal
{
    @Resource
    private ConnectionFactory connectionFactory;

    @Resource(name = "GraouTopic")
    private Topic topic;
    private MessageConsumer messageConsumer;
    private Connection connection;
    private Session session;

    @PostConstruct
    public void start() throws JMSException
    {
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        messageConsumer = session.createConsumer(topic);
        connection.start();
    }

    @PreDestroy
    public void stop() throws JMSException
    {
        messageConsumer = null;
        session.close();
        connection.close();
    }

    public StockOption read() throws JMSException
    {
        ObjectMessage obj = (ObjectMessage) messageConsumer.receive();
        return (StockOption) obj.getObject();
    }
}
