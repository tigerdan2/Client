package de.daniel.gui;

import java.awt.*;
import java.io.IOException;

import de.daniel.Client;

import de.daniel.modules.Module;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class GuiKeybindManager extends GuiScreen {

    private Module module;
    private String status;
    private boolean doubleClick;

    public GuiKeybindManager(Module module) {
        this.module = module;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        // Module Name
        float scale = 3.0F;
        GL11.glScalef(scale, scale, scale);
        Gui.drawCenteredString(this.fontRendererObj,   this.module.name,(int) (this.width / 2 / scale), (int) (this.height / 5 / scale - 15.0F), new Color(0x52C709).getRGB());
        GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);

        Gui.drawCenteredString(this.fontRendererObj, "Aktueller Key: " + "§9" + BindStuff.translateKeyBind(this.module.keyBind), this.width/2, this.height/7+20, 0xFFFFFFFF);

        Gui.drawRect(this.width/2-180, this.height/2-87, this.width/2+180, this.height/2+35, 0x50000000);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        this.buttonList.add(new KeybindButton(1, this.width/2-167, this.height/2-80, 22, 20, "^"));
        this.buttonList.add(new KeybindButton(2, this.width/2-143, this.height/2-80, 22, 20, "1"));
        this.buttonList.add(new KeybindButton(3, this.width/2-119, this.height/2-80, 22, 20, "2"));
        this.buttonList.add(new KeybindButton(4, this.width/2-95, this.height/2-80, 22, 20, "3"));
        this.buttonList.add(new KeybindButton(5, this.width/2-71, this.height/2-80, 22, 20, "4"));
        this.buttonList.add(new KeybindButton(6, this.width/2-47, this.height/2-80, 22, 20, "5"));
        this.buttonList.add(new KeybindButton(7, this.width/2-23, this.height/2-80, 22, 20, "6"));
        this.buttonList.add(new KeybindButton(8, this.width/2+1, this.height/2-80, 22, 20, "7"));
        this.buttonList.add(new KeybindButton(9, this.width/2+25, this.height/2-80, 22, 20, "8"));
        this.buttonList.add(new KeybindButton(10, this.width/2+49, this.height/2-80, 22, 20, "9"));
        this.buttonList.add(new KeybindButton(11, this.width/2+73, this.height/2-80, 22, 20, "0"));
        this.buttonList.add(new KeybindButton(12, this.width/2+97, this.height/2-80, 22, 20, "?"));
        this.buttonList.add(new KeybindButton(13, this.width/2+121, this.height/2-80, 22, 20, "'"));
        this.buttonList.add(new KeybindButton(14, this.width/2+145, this.height/2-80, 22, 20, "<--"));

        this.buttonList.add(new KeybindButton(15, this.width/2-167, this.height/2-58, 28, 20, "Tab"));
        this.buttonList.add(new KeybindButton(16, this.width/2-137, this.height/2-58, 22, 20, "Q"));
        this.buttonList.add(new KeybindButton(17, this.width/2-113, this.height/2-58, 22, 20, "W"));
        this.buttonList.add(new KeybindButton(18, this.width/2-89, this.height/2-58, 22, 20, "E"));
        this.buttonList.add(new KeybindButton(19, this.width/2-65, this.height/2-58, 22, 20, "R"));
        this.buttonList.add(new KeybindButton(20, this.width/2-41, this.height/2-58, 22, 20, "T"));
        this.buttonList.add(new KeybindButton(21, this.width/2-17, this.height/2-58, 22, 20, "Z"));
        this.buttonList.add(new KeybindButton(22, this.width/2+7, this.height/2-58, 22, 20, "U"));
        this.buttonList.add(new KeybindButton(23, this.width/2+31, this.height/2-58, 22, 20, "I"));
        this.buttonList.add(new KeybindButton(24, this.width/2+55, this.height/2-58, 22, 20, "O"));
        this.buttonList.add(new KeybindButton(25, this.width/2+79, this.height/2-58, 22, 20, "P"));
        this.buttonList.add(new KeybindButton(26, this.width/2+103, this.height/2-58, 22, 20, "Ü"));
        this.buttonList.add(new KeybindButton(27, this.width/2+127, this.height/2-58, 22, 20, "+"));
        this.buttonList.add(new KeybindButton(28, this.width/2+151, this.height/2-58, 16, 20, "<-`"));

        this.buttonList.add(new KeybindButton(29, this.width/2-167, this.height/2-36, 34, 20, "Caps"));
        this.buttonList.add(new KeybindButton(30, this.width/2-131, this.height/2-36, 22, 20, "A"));
        this.buttonList.add(new KeybindButton(31, this.width/2-107, this.height/2-36, 22, 20, "S"));
        this.buttonList.add(new KeybindButton(32, this.width/2-83, this.height/2-36, 22, 20, "D"));
        this.buttonList.add(new KeybindButton(33, this.width/2-59, this.height/2-36, 22, 20, "F"));
        this.buttonList.add(new KeybindButton(34, this.width/2-35, this.height/2-36, 22, 20, "G"));
        this.buttonList.add(new KeybindButton(35, this.width/2-11, this.height/2-36, 22, 20, "H"));
        this.buttonList.add(new KeybindButton(36, this.width/2+13, this.height/2-36, 22, 20, "J"));
        this.buttonList.add(new KeybindButton(37, this.width/2+37, this.height/2-36, 22, 20, "K"));
        this.buttonList.add(new KeybindButton(38, this.width/2+61, this.height/2-36, 22, 20, "L"));
        this.buttonList.add(new KeybindButton(39, this.width/2+85, this.height/2-36, 22, 20, "Ö"));
        this.buttonList.add(new KeybindButton(40, this.width/2+109, this.height/2-36, 22, 20, "Ä"));
        this.buttonList.add(new KeybindButton(41, this.width/2+133, this.height/2-36, 22, 20, "#"));

        this.buttonList.add(new KeybindButton(42, this.width/2-167, this.height/2-14, 24, 20, "Shift"));
        this.buttonList.add(new KeybindButton(43, this.width/2-141, this.height/2-14, 22, 20, "<> |"));
        this.buttonList.add(new KeybindButton(44, this.width/2-117, this.height/2-14, 22, 20, "Y"));
        this.buttonList.add(new KeybindButton(45, this.width/2-93, this.height/2-14, 22, 20, "X"));
        this.buttonList.add(new KeybindButton(46, this.width/2-69, this.height/2-14, 22, 20, "C"));
        this.buttonList.add(new KeybindButton(47, this.width/2-45, this.height/2-14, 22, 20, "V"));
        this.buttonList.add(new KeybindButton(48, this.width/2-21, this.height/2-14, 22, 20, "B"));
        this.buttonList.add(new KeybindButton(49, this.width/2+3, this.height/2-14, 22, 20, "N"));
        this.buttonList.add(new KeybindButton(50, this.width/2+27, this.height/2-14, 22, 20, "M"));
        this.buttonList.add(new KeybindButton(51, this.width/2+51, this.height/2-14, 22, 20, ","));
        this.buttonList.add(new KeybindButton(52, this.width/2+75, this.height/2-14, 22, 20, "."));
        this.buttonList.add(new KeybindButton(53, this.width/2+99, this.height/2-14, 22, 20, "-"));
        this.buttonList.add(new KeybindButton(54, this.width/2+123, this.height/2-14, 44, 20, "RShift"));

        this.buttonList.add(new KeybindButton(55, this.width/2-167, this.height/2+8, 34, 20, "Strg"));
        this.buttonList.add(new KeybindButton(56, this.width/2-131, this.height/2+8, 22, 20, ""));
        this.buttonList.add(new KeybindButton(57, this.width/2-107, this.height/2+8, 22, 20, "Alt"));
        this.buttonList.add(new KeybindButton(58, this.width/2-83, this.height/2+8, 145, 20, ""));
        this.buttonList.add(new KeybindButton(59, this.width/2+64, this.height/2+8, 22, 20, "AltGr"));
        this.buttonList.add(new KeybindButton(60, this.width/2+88, this.height/2+8, 22, 20, "Fn"));
        this.buttonList.add(new KeybindButton(61, this.width/2+112, this.height/2+8, 22, 20, ""));
        this.buttonList.add(new KeybindButton(62, this.width/2+136, this.height/2+8, 32, 20, "Strg"));
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 1:
                this.module.keyBind = (Keyboard.KEY_BACKSLASH);
                break;
            case 2:
                this.module.keyBind = (Keyboard.KEY_1);
                break;
            case 3:
                this.module.keyBind = (Keyboard.KEY_2);
                break;
            case 4:
                this.module.keyBind = (Keyboard.KEY_3);
                break;
            case 5:
                this.module.keyBind = (Keyboard.KEY_4);
                break;
            case 6:
                this.module.keyBind = (Keyboard.KEY_5);
                break;
            case 7:
                this.module.keyBind = (Keyboard.KEY_6);
                break;
            case 8:
                this.module.keyBind = (Keyboard.KEY_7);
                break;
            case 9:
                this.module.keyBind = (Keyboard.KEY_8);
                break;
            case 10:
                this.module.keyBind = (Keyboard.KEY_9);
                break;
            case 11:
                this.module.keyBind = (Keyboard.KEY_0);
                break;
            case 12:
                this.module.keyBind = (Keyboard.KEY_LBRACKET);
                break;
            case 13:
                this.module.keyBind = (Keyboard.KEY_RBRACKET);
                break;
            case 14:
                this.module.keyBind = (Keyboard.KEY_BACK);
                break;
            case 15:
                this.module.keyBind = (Keyboard.KEY_TAB);
                break;
            case 16:
                this.module.keyBind = (Keyboard.KEY_Q);
                break;
            case 17:
                this.module.keyBind = (Keyboard.KEY_W);
                break;
            case 18:
                this.module.keyBind = (Keyboard.KEY_E);
                break;
            case 19:
                this.module.keyBind = (Keyboard.KEY_R);
                break;
            case 20:
                this.module.keyBind = (Keyboard.KEY_T);
                break;
            case 21:
                this.module.keyBind = (Keyboard.KEY_Z);
                break;
            case 22:
                this.module.keyBind = (Keyboard.KEY_U);
                break;
            case 23:
                this.module.keyBind = (Keyboard.KEY_I);
                break;
            case 24:
                this.module.keyBind = (Keyboard.KEY_O);
                break;
            case 25:
                this.module.keyBind = (Keyboard.KEY_P);
                break;
            case 26:
                this.module.keyBind = (Keyboard.KEY_SEMICOLON);
                break;
            case 27:
                this.module.keyBind = (Keyboard.KEY_ADD);
                break;
            case 28:
                this.module.keyBind = (Keyboard.KEY_RETURN);
                break;
            case 29:
                this.module.keyBind = (Keyboard.KEY_PERIOD);
                break;
            case 30:
                this.module.keyBind = (Keyboard.KEY_A);
                break;
            case 31:
                this.module.keyBind = (Keyboard.KEY_S);
                break;
            case 32:
                this.module.keyBind = (Keyboard.KEY_D);
                break;
            case 33:
                this.module.keyBind = (Keyboard.KEY_F);
                break;
            case 34:
                this.module.keyBind = (Keyboard.KEY_G);
                break;
            case 35:
                this.module.keyBind = (Keyboard.KEY_H);
                break;
            case 36:
                this.module.keyBind = (Keyboard.KEY_J);
                break;
            case 37:
                this.module.keyBind = (Keyboard.KEY_K);
                break;
            case 38:
                this.module.keyBind = (Keyboard.KEY_L);
                break;
            case 39:
                this.module.keyBind = (Keyboard.KEY_SEMICOLON);
                break;
            case 40:
                this.module.keyBind = (Keyboard.KEY_SEMICOLON);
                break;
            case 41:
                this.module.keyBind = (Keyboard.KEY_SLASH);
                break;
            case 42:
                this.module.keyBind = (Keyboard.KEY_LSHIFT);
                break;
            case 43:
                this.module.keyBind = (0);
                break;
            case 44:
                this.module.keyBind = (Keyboard.KEY_Y);
                break;
            case 45:
                this.module.keyBind = (Keyboard.KEY_X);
                break;
            case 46:
                this.module.keyBind = (Keyboard.KEY_C);
                break;
            case 47:
                this.module.keyBind = (Keyboard.KEY_V);
                break;
            case 48:
                this.module.keyBind = (Keyboard.KEY_B);
                break;
            case 49:
                this.module.keyBind = (Keyboard.KEY_N);
                break;
            case 50:
                this.module.keyBind = (Keyboard.KEY_M);
                break;
            case 51:
                this.module.keyBind = (Keyboard.KEY_COMMA);
                break;
            case 52:
                this.module.keyBind = (Keyboard.KEY_PERIOD);
                break;
            case 53:
                this.module.keyBind = (Keyboard.KEY_MINUS);
                break;
            case 54:
                this.module.keyBind = (Keyboard.KEY_RSHIFT);
                break;
            case 55:
                this.module.keyBind = (Keyboard.KEY_LCONTROL);
                break;
            case 56:
                this.module.keyBind = (0);
                break;
            case 57:
                this.module.keyBind = (Keyboard.KEY_LMENU);
                break;
            case 58:
                this.module.keyBind = (Keyboard.KEY_SPACE);
                break;
            case 59:
                this.module.keyBind = (Keyboard.KEY_APPS);
                break;
            case 60:
                this.module.keyBind = (0);
                break;
            case 61:
                this.module.keyBind = (0);
                break;
            case 62:
                this.module.keyBind = (Keyboard.KEY_RCONTROL);
                isKeyValid(Keyboard.KEY_RCONTROL);
                break;
            default:
                break;
        }
        updateScreen();
      //  BindManager.saveKeyBinds(Sun.theClient.bindsFile);
        Minecraft.getMinecraft().displayGuiScreen(Client.getInstance().clickGui);
        super.actionPerformed(button);
    }

    private void setStatus(String status) {
        this.status = status;
    }

    private void isKeyValid(int key) {
        for(Module modules : Client.getInstance().mm.mods) {
            if((modules != this.module) && (modules.keyBind == key)) {
                setStatus("Â§fAktueller Key: " + BindStuff.translateKeyBind(key) + "\nÂ§cDer Keybind " + BindStuff.translateKeyBind(key) + " ist schon vergeben");
            }
        }
    }

    private void isKey(int key) {
        for(Module modules : Client.getInstance().mm.mods) {
            if((modules != this.module) && (module.keyBind == key)) {
                setStatus("Â§cDer Keybind " + BindStuff.translateKeyBind(key) + " ist schon vergeben\nDrÃ¼cke den Key nochmal um zu bestÃ¤tigen");
            }
        }
        if(Keyboard.isKeyDown(key)) {
            this.module.keyBind = (key);
        }
    }
}
