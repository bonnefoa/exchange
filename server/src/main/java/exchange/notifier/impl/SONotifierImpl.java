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

package exchange.notifier.impl;

import exchange.notifier.SONotifier;
import exchange.model.StockOption;

import javax.ejb.Stateless;
import javax.jms.*;
import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 5 mars 2009
 * Time: 17:40:55
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class SONotifierImpl implements SONotifier {

    @Resource(mappedName = "jms/StockOptionConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/StockOptionTopic")
    private Topic topic;


    public void update(StockOption stockOption) {
        try {
            Connection connection = connectionFactory.createConnection();
            // false -> pas de transaction
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(topic);
            producer.send(session.createObjectMessage(stockOption));
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
