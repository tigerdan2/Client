package de.daniel.gui;

import de.daniel.Client;

import de.daniel.modules.Module;
import de.daniel.modules.values.Value;
import de.daniel.modules.values.ValueBoolean;
import de.daniel.modules.values.ValueMode;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;
import de.daniel.gui.Container;

import java.awt.*;

import java.util.Random;

public class ValueContainer extends Container {


    public ValueMode modus;
    public Color randomColor;
    public ValueContainer(ValueMode m, float startx, float starty){
        this.modus = m;
        x = startx;
        y = starty;
        randomColor = betterNiceColor();

    }
    public static Color betterNiceColor()
    {
        double goldenRatioConj = (1.0 + Math.sqrt(5.0)) / 2.0;
        float hue = new Random().nextFloat();

        hue += goldenRatioConj * (0 / (5 * Math.random()));
        hue = hue % 1;

        return Color.getHSBColor(hue, 0.5f, 0.95f);
    }

    @Override
    public void release(int mouseX, int mouseY,int id) {
        int offset = 20;
        int i = 0;
        for(String module : modus.modes){
            boolean hover = mouseX > x && mouseX < x+100 && mouseY > y+offset && mouseY < y+offset+20;

            if(hover && id == 0){
                modus.value = i;
                for(Module module1 : Client.getInstance().mm.mods){
                    for(Value v : module1.values){
                        if(v == modus){
                            module1.generateDisplayName();
                            Client.getInstance().clickGui.processClick = false;
                        }
                    }
                }
            }
                ++i;
            offset+=20;
        }

    }

    @Override
    public void draw(float partial,int mouseX, int mouseY) {
        Gui.drawGradientRect(x,y,x+100,y+20,randomColor.getRGB(),randomColor.darker().getRGB());
        //   GL11.glPopMatrix();

        GL11.glEnable((int)3042);
        Client.getInstance().defaultfont.drawString(modus.name.toString().substring(0,1).toUpperCase() + modus.name.toString().substring(1).toLowerCase(),x+2,y+3,0xffffffff,true);

        int offset = 20;

        for(String module : modus.modes){
            boolean hover = mouseX > x && mouseX < x+100 && mouseY > y+offset && mouseY < y+offset+20;

            if(!(modus.getValue()).equals(module)) {
                Gui.drawRect(x, y + offset, x + 100, y + offset + 20, hover ? new Color(0xA9232323, true).getRGB() : new Color(0xA9000000, true).getRGB());
            }else{
                Gui.drawRect(x, y + offset, x + 100, y + offset + 20, hover ? new Color(randomColor.getRed()/256f,randomColor.getGreen()/256f,randomColor.getBlue()/256f,0.5f).brighter().getRGB():new Color(randomColor.getRed()/256f,randomColor.getGreen()/256f,randomColor.getBlue()/256f,0.5f).getRGB());

            }
            Client.getInstance().defaultfont.drawString(module,x+2,y+offset+3,0xffffffff,true);

            offset+=20;
        }

        if(overCat){
            GuiHelper.drawBorderedRect(x,y,x+100,y+20,randomColor.getRGB(),new Color(0,0,0,0).getRGB());
        }
    }
}
