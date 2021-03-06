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

package exchange.gui.controller;

import exchange.message.impl.AddMessage;
import exchange.message.impl.DeleteMessage;
import exchange.message.impl.UpdateMessage;

/**
 * Interface for the administration controller
 */
public interface IAdminController extends IAbstractController
{
    void messageReceived(AddMessage stockOptionMessage);

    void messageReceived(DeleteMessage stockOptionMessage);

    void messageReceived(UpdateMessage stockOptionMessage);
}
