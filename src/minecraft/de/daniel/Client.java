package de.daniel;

import de.daniel.modules.ModuleManager;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

import java.io.File;

public class Client {

    public final String CLIENT_NAME = "Blank";
    public ModuleManager mm;

    public static Client instance;

    public File client = new File(CLIENT_NAME);
    public File modules = new File(client,"mods.txt");

    public static Client getInstance(){
        return instance;
    }

    public void setupClient(){
        instance = this;
        mm = new ModuleManager();

            mm.init();

        Display.setTitle("Minecraft 1.8 - " + CLIENT_NAME);

    }

}
