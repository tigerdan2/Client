package de.daniel.modules.render;

import de.Client.Events.EventTarget;
import de.Client.Events.Events.EventRenderScreen;
import de.daniel.Client;
import de.daniel.modules.Category;
import de.daniel.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class HUD extends Module {


    @EventTarget
    public void onRender2d(EventRenderScreen eventRenderScreen){
        int y = 5;
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        for(Module module : Client.getInstance().mm.mods){
            if(!module.isEnabled){
                continue;
            }

            Client.getInstance().defaultfont.drawStringWithShadow(module.name,scaledresolution.getScaledWidth()-Client.getInstance().defaultfont.getStringWidth(module.name)-5,y, Category.getColorForCategory(module.c).getRGB());
            y += 10;
        }
    }



}
