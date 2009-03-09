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

package exchange.gui.controller.impl;

import exchange.gui.view.impl.GlobalFrame;
import exchange.gui.view.IGlobalFrame;
import exchange.gui.controller.IAbstractController;
import exchange.ejb.StockOptionTopicReaderLocal;

import javax.naming.InitialContext;
import java.util.Observable;

import com.google.inject.Inject;

/**
 * Abstract class for common logic of both controller
 */
public abstract class AbstractController implements IAbstractController {
    /**
     * Parent frame
     */
    protected IGlobalFrame parent;

    public void setParent(IGlobalFrame parent) {
        this.parent = parent;
    }
}
