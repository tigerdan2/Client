package de.daniel.modules.render;

import com.google.gson.annotations.Expose;
import de.Client.Events.EventTarget;
import de.Client.Events.Events.EventRenderScreen;
import de.Client.Events.Events.Render3DEvent;
import de.daniel.Client;
import de.daniel.gui.GuiHelper;
import de.daniel.modules.Module;
import de.daniel.utils.render.RenderAPI2d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;

import java.awt.*;

public class Nametags extends Module {
    @Expose
    RenderAPI2d conversionFactory = new RenderAPI2d();
    ScaledResolution sr = new ScaledResolution();
    @Override
    public void onEnable(){
        sr = new ScaledResolution();
        conversionFactory.sr = sr;

        super.onEnable();
    }

    @EventTarget
    public void onRender2d(EventRenderScreen ev){
        for(Entity e : conversionFactory.upperPos1.keySet()){

            float x = (conversionFactory.upperPos1.get(e)[0] + conversionFactory.upperPos2.get(e)[0] + conversionFactory.upperPos3.get(e)[0] + conversionFactory.upperPos4.get(e)[0]) / 4.0f;
            float lY = conversionFactory.getLowestY(e);
            float y = lY > 0 ? lY - 12 : -99;
            if (!(y == -99 || lY >  sr.getScaledHeight() || x < -10 || x > sr.getScaledWidth() + 10)) {
                //  Gui.drawRect();
                y -= 4;
                //Minecraft.getMinecraft().fontRendererObj.drawString(e.getName(), (int) x, (int) y,new Color(0xFFFFFF).getRGB());
                int width = Client.getInstance().cFontRenderer.getStringWidth(e.getDisplayName().getFormattedText())/2;
                GuiHelper.drawBorderedRect(x-width,y,x+width,y+15,0x3131347C,0x1F1F2398);
                Client.getInstance().cFontRenderer.drawStringWithShadow(e.getDisplayName().getFormattedText(),x- width,y,new Color(0xFFFFFF).getRGB());
            }
        }
    }
    @EventTarget
    public void onRender3d(Render3DEvent ev){
        conversionFactory.shouldConvert2d = true;
        conversionFactory.on3dRender();

    }

}
