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

package exchange.gui.view.impl;

import com.google.inject.Inject;
import exchange.gui.model.IClientModel;
import exchange.model.StockOption;

import javax.swing.*;
import java.awt.*;

/**
 * Cell renderer for the display of the stockOption
 */
public class StockOptionCellRender extends DefaultListCellRenderer
{
    private IClientModel clientModel;
    private ImageIcon up;
    private ImageIcon down;
    private ImageIcon right;

    @Inject
    public StockOptionCellRender(IClientModel clientModel)
    {
        this.clientModel = clientModel;
        up = new ImageIcon(getClass().getResource("up.png"));
        down = new ImageIcon(getClass().getResource("down.png"));
        right = new ImageIcon(getClass().getResource("right.png"));
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof StockOption)
        {
            StockOption stock = (StockOption) value;
            if (clientModel.getStockOptionDisplayed().contains(stock))
            {
                this.setIcon(dispatchIcon(stock));
            } else
            {
                this.setIcon(null);
            }
        }
        return label;
    }

    private Icon dispatchIcon(StockOption stock)
    {
        switch (stock.getVariation())
        {
            case DOWN:
                return down;
            case UP:
                return up;
            default:
                return right;
        }
    }


}

