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

package exchange.consumer;

import javax.jms.MessageListener;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 10 mars 2009
 * Time: 15:06:20
 * To change this template use File | Settings | File Templates.
 */
public interface StockOptionMessageConsumer
{
    final String JNDI_TOPIC_NAME = "StockOptionTopic";

    void addObserver(Observer o);

    void deleteObserver(Observer o);
}
