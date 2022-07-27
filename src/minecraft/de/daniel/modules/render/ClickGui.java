package de.daniel.modules.render;

import com.sun.org.apache.xpath.internal.operations.Mod;
import de.daniel.Client;
import de.daniel.modules.Module;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class ClickGui extends Module {

    public ClickGui(){
        super();
        this.keyBind = Keyboard.KEY_G;
    }

    @Override
    public void onEnable(){
        this.toggle();
        Minecraft.getMinecraft().displayGuiScreen(Client.getInstance().clickGui);
    }
}
