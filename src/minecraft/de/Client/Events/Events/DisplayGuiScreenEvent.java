package de.Client.Events.Events;

import de.Client.Events.IEvent.Event;
import net.minecraft.client.gui.GuiScreen;

/**
 * Created by Daniel on 31.08.2017.
 */
public class DisplayGuiScreenEvent implements Event{
    public GuiScreen guiScreenbevor;
    public GuiScreen guiScreenafter;
    public DisplayGuiScreenEvent(GuiScreen g, GuiScreen g2){
        guiScreenbevor = g;
        guiScreenafter = g2;
    }
}
