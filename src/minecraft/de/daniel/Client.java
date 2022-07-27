package de.daniel;

import de.daniel.gui.ClickGui;
import de.daniel.modules.Module;
import de.daniel.modules.ModuleManager;
import de.daniel.utils.fonts.CFontRenderer;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Client {

    public final String CLIENT_NAME = "Blank";
    public ModuleManager mm;

    public static Client instance;
    public ClickGui clickGui = new ClickGui();
    public File client = new File(CLIENT_NAME);
    public File modules = new File(client,"mods.txt");
    public CFontRenderer cFontRenderer = new CFontRenderer(new Font("Comic Sans MS",1,26),true,true);
    public CFontRenderer defaultfont = new CFontRenderer(new Font("Arial",1,20),true,true);
    public static Client getInstance(){
        return instance;
    }

    public static void handleInput(int k){
        for(Module mod :Client.getInstance().mm.mods){
            if(mod.keyBind == k){
                mod.toggle();
            }
        }
    }

    public void setupClient(){
        if(!client.exists()){
            client.mkdirs();
        }
        if(!modules.exists()){
            try {
                modules.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        instance = this;
        mm = new ModuleManager();

            mm.init();

        Display.setTitle("Minecraft 1.8 - " + CLIENT_NAME);

    }

}
