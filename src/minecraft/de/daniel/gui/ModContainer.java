package de.daniel.gui;

import de.daniel.Client;

import de.daniel.modules.Module;
import de.daniel.modules.values.Value;
import de.daniel.modules.values.ValueBoolean;
import de.daniel.modules.values.ValueMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import java.util.ArrayList;
import java.util.Random;

public class ModContainer extends Container {

    public static Color betterNiceColor()
    {
        double goldenRatioConj = (1.0 + Math.sqrt(5.0)) / 2.0;
        float hue = new Random().nextFloat();

        hue += goldenRatioConj * (0 / (5 * Math.random()));
        hue = hue % 1;

        return Color.getHSBColor(hue, 0.5f, 0.95f);
    }
    public Module Mod;

    public Color randomColor;

    public ModContainer(Module m, float startx, float starty){
        this.Mod = m;
        x = startx;
        y = starty;
        randomColor = betterNiceColor();

    }

    @Override
    public void release(int mouseX, int mouseY, int id) {
        if(!    Client.instance.clickGui.processClick ){
            return;
        }
        super.release(mouseX, mouseY, id);
        int offset = 20;
        boolean hover2 = mouseX > x && mouseX < x+100 && mouseY > y+offset && mouseY < y+offset+20;

        if(id == 0 && hover2){
            Minecraft.getMinecraft().displayGuiScreen(new GuiKeybindManager(Mod));
        }
       // System.out.println(hover2);

        offset+=20;


        for(Value v : this.Mod.getValues()){
            //VALUE BOOLEAN
            if(v instanceof ValueBoolean){
                boolean hover = mouseX > x && mouseX < x+100 && mouseY > y+offset && mouseY < y+offset+20;

                if(hover){
                    if(id == 0){
                        v.value =!(boolean)v.value;
                        Client.instance.clickGui.processClick = false;

                        Mod.generateDisplayName();
                    }
                }


                offset +=20;

            }
            else if(v instanceof ValueMode){
                boolean hover = mouseX > x && mouseX < x+100 && mouseY > y+offset && mouseY < y+offset+30;

                if((id == 0||id ==1)  && hover){
                    ArrayList<Container> scheduler = new ArrayList<>();
                    for(Container c : Client.getInstance().clickGui.categories){
                        if(c instanceof ValueContainer){
                            ValueContainer c2 = (ValueContainer) c;
                            if(c2.modus == v){
                              //  Client.getInstance().clickGui.categories.remove(c2);
                                scheduler.add(c2);
                            }
                        }
                    }

                    if(!scheduler.isEmpty()){
                        for(Container c : scheduler){
                            Client.getInstance().clickGui.categories.remove(c);
                        }
                        scheduler.clear();
                        return;
                    }
                    Client.instance.clickGui.processClick = false;
                    Client.getInstance().clickGui.categories.add(new ValueContainer((ValueMode) v,x+105,y+offset));

                }
                offset +=30;
            }
            //VALUE DOUBLE // NOT YET IMPLEMENTED

        }

    }

    @Override
    public void draw(float i1, int mouseX, int mouseY) {

        Gui.drawGradientRect(x,y,x+100,y+20,randomColor.getRGB(),randomColor.darker().getRGB());
        //   GL11.glPopMatrix();

        GL11.glEnable((int)3042);
        Client.getInstance().defaultfont.drawString(Mod.name.toString().substring(0,1).toUpperCase() + Mod.name.toString().substring(1).toLowerCase(),x+2,y+3,0xffffffff,true);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("client/icons/gears.png"));
        Gui.drawModalRectWithCustomSizedTexture(x-2+100-16,y+2,0,0,16,16,16,16);

        int offset = 20;
        boolean hover2 = mouseX > x && mouseX < x+100 && mouseY > y+offset && mouseY < y+offset+20;

        Gui.drawRect(x, y + offset, x + 100, y + offset + 20, hover2 ? new Color(0xA9232323, true).getRGB() : new Color(0xA9000000, true).getRGB());
        Client.getInstance().defaultfont.drawString("Bind: " + BindStuff.translateKeyBind(Mod.keyBind),x+2,y+offset+3,0xffffffff,true);



        offset+=20;
        for(Value v : this.Mod.getValues()){
            //VALUE BOOLEAN
            if(v instanceof ValueBoolean){
                boolean hover = mouseX > x && mouseX < x+100 && mouseY > y+offset && mouseY < y+offset+20;
                if(!((ValueBoolean) v).isEnabled()) {
                    Gui.drawRect(x, y + offset, x + 100, y + offset + 20, hover ? new Color(0xA9232323, true).getRGB() : new Color(0xA9000000, true).getRGB());
                }else{
                    Gui.drawRect(x, y + offset, x + 100, y + offset + 20, hover ? new Color(randomColor.getRed()/256f,randomColor.getGreen()/256f,randomColor.getBlue()/256f,0.5f).brighter().getRGB():new Color(randomColor.getRed()/256f,randomColor.getGreen()/256f,randomColor.getBlue()/256f,0.5f).getRGB());

                }

                Client.getInstance().defaultfont.drawString(v.name,x+2,y+offset+3,0xffffffff,true);



                offset +=20;

            }
            //Value Mode
            else if(v instanceof ValueMode){
                boolean hover = mouseX > x && mouseX < x+100 && mouseY > y+offset && mouseY < y+offset+30;

                Gui.drawRect(x, y + offset, x + 100, y + offset + 30, hover ? new Color(0xA9232323, true).getRGB() : new Color(0xA9000000, true).getRGB());
                Client.getInstance().defaultfont.drawString(v.name,x+2,y+offset+3,0xffffffff,true);
                Client.getInstance().defaultfont.drawString("> " + (String) v.getValue(),x+2,y+offset+15,randomColor.darker().getRGB(),true);

                offset +=30;
            }
            //VALUE DOUBLE // NOT YET IMPLEMENTED

        }

        if(overCat){
            GuiHelper.drawBorderedRect(x,y,x+100,y+20,randomColor.getRGB(),new Color(0,0,0,0).getRGB());
        }
      // super.draw(i1, mouseX, mouseY);
    }
}
