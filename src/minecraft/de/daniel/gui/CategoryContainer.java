package de.daniel.gui;

import com.sun.org.apache.xpath.internal.operations.Mod;
import de.daniel.Client;
import de.daniel.gui.GuiHelper;
import de.daniel.modules.Category;
import de.daniel.modules.Module;
import de.daniel.modules.values.Value;
import de.daniel.modules.values.ValueMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.main.Main;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class CategoryContainer extends Container {


    public Category c;



    public CategoryContainer(Category c,float startx, float starty){
        this.c = c;
        x = startx;
        y = starty;


    }

    @Override
    public void release(int mouseX, int mouseY,int id) {
        super.release(mouseX, mouseY,id);
        if(!    Client.instance.clickGui.processClick ){
            System.out.println("big oopsie");
            return;
        }
        int offset = 20;
        for(Module module : Client.getInstance().mm.getModsForCat(c)){
            boolean hover = mouseX > x && mouseX < x+100 && mouseY > y+offset && mouseY < y+offset+20;

            if(id ==0 && hover){
                System.out.println("play" + System.currentTimeMillis());
                Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));

                module.toggle();
                Client.instance.clickGui.processClick = false;
            }
            if(id == 1 && hover){
                ArrayList<Container> scheduler = new ArrayList<Container>();
                for(Container c : Client.instance.clickGui.categories){
                    if(c instanceof ModContainer){
                        ModContainer m2 = (ModContainer) c;
                        if(m2.Mod == module){
                            System.out.println("CANCEL!");
                            for(Value v : m2.Mod.values){
                                if(v instanceof ValueMode){
                                    for(Container c2 : Client.getInstance().clickGui.categories){
                                        if(c2 instanceof ValueContainer){
                                            ValueContainer c3 = (ValueContainer) c2;
                                            if(c3.modus.name.equals(v.name)){
                                               // Client.getInstance().clickGui.categories.remove(c2);
                                                scheduler.add(c2);
                                            }else{
                                                System.out.println("didntz match");
                                            }
                                        }
                                    }
                                }
                            }
                            Client.getInstance().clickGui.categories.remove(m2);
                            scheduler.add(m2);

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
                Client.getInstance().clickGui.categories.add(new ModContainer(module,x+105,y+offset));
                Client.instance.clickGui.processClick = false;

            }

            offset+=20;
        }

    }

    public void draw(float partial, int mouseX, int mouseZ){



       // GL11.glPushMatrix();

        Gui.drawGradientRect(x,y,x+100,y+20,Category.getColorForCategory(c).getRGB(),Category.getColorForCategory(c).darker().getRGB());
     //   GL11.glPopMatrix();
        GL11.glEnable((int)3042);
        Client.getInstance().defaultfont.drawString(c.toString().substring(0,1).toUpperCase() + c.toString().substring(1).toLowerCase(),x+2,y+3,0xffffffff,true);

        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("client/icons/"+c.toString()+".png"));
        Gui.drawModalRectWithCustomSizedTexture(x-2+100-16,y+2,0,0,16,16,16,16);

        int offset = 20;
        for(Module module : Client.getInstance().mm.getModsForCat(c)){

           //     Color c2 = Category.getColorForCategory(c);
           //    Gui.drawRect(x, y + offset, x + 100, y + offset + 20, new Color(c2.getRed()/256f,c2.getGreen()/256f,c2.getBlue()/256f,0.5f).getRGB());

            boolean hover = mouseX > x && mouseX < x+100 && mouseZ > y+offset && mouseZ < y+offset+20;

            if(module.isEnabled){
                     Color c2 = Category.getColorForCategory(c).darker();
                     if(hover){
                         c2 = c2.brighter();
                     }
                    Gui.drawRect(x, y + offset, x + 100, y + offset + 20, new Color(c2.getRed()/256f,c2.getGreen()/256f,c2.getBlue()/256f,0.5f).getRGB());

            //    Gui.drawRect(x, y + offset, x + 100, y + offset + 20, new Color(0xA9000000, true).getRGB());

            }else {
                if(!hover) {
                    Gui.drawRect(x, y + offset, x + 100, y + offset + 20, new Color(0xA9000000, true).getRGB());
                }else{
                    Gui.drawRect(x, y + offset, x + 100, y + offset + 20, new Color(0xA9232323, true).getRGB());

                }
            }
            Client.getInstance().defaultfont.drawString(module.name,x+2,y+offset+3,0xffffffff,true);
            if(!module.values.isEmpty()){
                Client.getInstance().defaultfont.drawString("...",x+85,y+offset+3,0xffffffff,true);
            }
     //       Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow((module.isEnabled ? " §a✔" : " §c✗"),x+Client.getInstance().defaultFont.getStringWidth(module.name),y+offset+5,0xff);
            offset+=20;
        }

        if(overCat){
            GuiHelper.drawBorderedRect(x,y,x+100,y+20,Category.getColorForCategory(c).brighter().getRGB(),new Color(0,0,0,0).getRGB());
        }
    }



}
