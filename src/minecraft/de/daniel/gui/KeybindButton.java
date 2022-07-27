package de.daniel.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.settings.GameSettings;

import java.awt.*;

public class KeybindButton extends GuiButton {

    private int x;
    private int y;
    private int x1;
    private int y1;
    private String text;
    int alphaInc = 100;
    int alpha = 0;

    public KeybindButton(int id, int x, int y, int width, int heigth, String text) {
        super(id, x, y, width, heigth, text);
        this.x = x;
        this.y = y;
        this.x1 = width;
        this.y1 = heigth;
        this.text = text;
    }

    public KeybindButton(int id, int x, int y, String text) {
        this(id, x, y, 200, 20, text);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        GL11.glPushMatrix();
        boolean isOverButton = (mouseX >= this.x) && (mouseX <= this.x + this.x1) && (mouseY >= this.y) && (mouseY <= this.y + this.y1);

        if(isOverButton) {
            Gui.drawRect(this.x, this.y, this.x + this.x1, this.y + this.y1,new Color(0x37DE12).getRGB());
        } else {
            Gui.drawRect(this.x, this.y, this.x + this.x1, this.y + this.y1, 0x95000000);
        }
      //  Wrapper.ttfmgr.drawString("test", -88, -88, -88);
        Gui.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, this.text, this.x + this.x1 / 2, this.y + this.y1 / 2 - 4, -1);
        GL11.glPopMatrix();
    }

    public void setDisplayString(String display) {
        this.text = display;
    }

    public GameSettings.Options returnEnmOptions() {
        return null;
    }
}
