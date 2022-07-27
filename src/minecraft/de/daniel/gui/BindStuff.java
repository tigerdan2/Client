package de.daniel.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import de.daniel.modules.Module;
import org.lwjgl.input.Keyboard;


public class BindStuff {

    public static void setKeyBind(File file, Module module, int keyBind) {
        module.keyBind = (keyBind);
    }

    public static void resetKeyBind(File file, Module module) {
        module.keyBind = (0);
    }





    public static String[] readKeyBinds(File file) {
        if(file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                List<String> lines = new ArrayList();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    lines.add(line);
                }
                bufferedReader.close();
                return (String[]) lines.toArray(new String[lines.size()]);
            } catch (Exception e) {
            }
        } else {
            System.out.println("[SkyRise] - Bind-Datei existiert nicht!");
        }
        return null;
    }

    public static int keyStringToIntID(String keyCode) {
        if (keyCode.equalsIgnoreCase("a")) return Keyboard.KEY_A;
        if (keyCode.equalsIgnoreCase("b")) return Keyboard.KEY_B;
        if (keyCode.equalsIgnoreCase("c")) return Keyboard.KEY_C;
        if (keyCode.equalsIgnoreCase("d")) return Keyboard.KEY_D;
        if (keyCode.equalsIgnoreCase("e")) return Keyboard.KEY_E;
        if (keyCode.equalsIgnoreCase("f")) return Keyboard.KEY_F;
        if (keyCode.equalsIgnoreCase("g")) return Keyboard.KEY_G;
        if (keyCode.equalsIgnoreCase("h")) return Keyboard.KEY_H;
        if (keyCode.equalsIgnoreCase("i")) return Keyboard.KEY_I;
        if (keyCode.equalsIgnoreCase("j")) return Keyboard.KEY_J;
        if (keyCode.equalsIgnoreCase("k")) return Keyboard.KEY_K;
        if (keyCode.equalsIgnoreCase("l")) return Keyboard.KEY_L;
        if (keyCode.equalsIgnoreCase("m")) return Keyboard.KEY_M;
        if (keyCode.equalsIgnoreCase("n")) return Keyboard.KEY_N;
        if (keyCode.equalsIgnoreCase("o")) return Keyboard.KEY_O;
        if (keyCode.equalsIgnoreCase("p")) return Keyboard.KEY_P;
        if (keyCode.equalsIgnoreCase("q")) return Keyboard.KEY_Q;
        if (keyCode.equalsIgnoreCase("r")) return Keyboard.KEY_R;
        if (keyCode.equalsIgnoreCase("s")) return Keyboard.KEY_S;
        if (keyCode.equalsIgnoreCase("t")) return Keyboard.KEY_T;
        if (keyCode.equalsIgnoreCase("u")) return Keyboard.KEY_U;
        if (keyCode.equalsIgnoreCase("v")) return Keyboard.KEY_V;
        if (keyCode.equalsIgnoreCase("w")) return Keyboard.KEY_W;
        if (keyCode.equalsIgnoreCase("x")) return Keyboard.KEY_X;
        if (keyCode.equalsIgnoreCase("y")) return Keyboard.KEY_Y;
        if (keyCode.equalsIgnoreCase("z")) return Keyboard.KEY_Z;

        if (keyCode.equalsIgnoreCase("0")) return Keyboard.KEY_0;
        if (keyCode.equalsIgnoreCase("1")) return Keyboard.KEY_1;
        if (keyCode.equalsIgnoreCase("2")) return Keyboard.KEY_2;
        if (keyCode.equalsIgnoreCase("3")) return Keyboard.KEY_3;
        if (keyCode.equalsIgnoreCase("4")) return Keyboard.KEY_4;
        if (keyCode.equalsIgnoreCase("5")) return Keyboard.KEY_5;
        if (keyCode.equalsIgnoreCase("6")) return Keyboard.KEY_6;
        if (keyCode.equalsIgnoreCase("7")) return Keyboard.KEY_7;
        if (keyCode.equalsIgnoreCase("8")) return Keyboard.KEY_8;
        if (keyCode.equalsIgnoreCase("9")) return Keyboard.KEY_9;

        if (keyCode.equalsIgnoreCase("f1")) return Keyboard.KEY_F1;
        if (keyCode.equalsIgnoreCase("f2")) return Keyboard.KEY_F2;
        if (keyCode.equalsIgnoreCase("f3")) return Keyboard.KEY_F3;
        if (keyCode.equalsIgnoreCase("f4")) return Keyboard.KEY_F4;
        if (keyCode.equalsIgnoreCase("f5")) return Keyboard.KEY_F5;
        if (keyCode.equalsIgnoreCase("f6")) return Keyboard.KEY_F6;
        if (keyCode.equalsIgnoreCase("f7")) return Keyboard.KEY_F7;
        if (keyCode.equalsIgnoreCase("f8")) return Keyboard.KEY_F8;
        if (keyCode.equalsIgnoreCase("f9")) return Keyboard.KEY_F9;
        if (keyCode.equalsIgnoreCase("f10")) return Keyboard.KEY_F10;
        if (keyCode.equalsIgnoreCase("f11")) return Keyboard.KEY_F11;
        if (keyCode.equalsIgnoreCase("f12")) return Keyboard.KEY_F12;

        if (keyCode.equalsIgnoreCase("numpad0")) return Keyboard.KEY_NUMPAD0;
        if (keyCode.equalsIgnoreCase("numpad1")) return Keyboard.KEY_NUMPAD1;
        if (keyCode.equalsIgnoreCase("numpad2")) return Keyboard.KEY_NUMPAD2;
        if (keyCode.equalsIgnoreCase("numpad3")) return Keyboard.KEY_NUMPAD3;
        if (keyCode.equalsIgnoreCase("numpad4")) return Keyboard.KEY_NUMPAD4;
        if (keyCode.equalsIgnoreCase("numpad5")) return Keyboard.KEY_NUMPAD5;
        if (keyCode.equalsIgnoreCase("numpad6")) return Keyboard.KEY_NUMPAD6;
        if (keyCode.equalsIgnoreCase("numpad7")) return Keyboard.KEY_NUMPAD7;
        if (keyCode.equalsIgnoreCase("numpad8")) return Keyboard.KEY_NUMPAD8;
        if (keyCode.equalsIgnoreCase("numpad9")) return Keyboard.KEY_NUMPAD9;

        if (keyCode.equalsIgnoreCase("up")) return Keyboard.KEY_UP;
        if (keyCode.equalsIgnoreCase("down")) return Keyboard.KEY_DOWN;
        if (keyCode.equalsIgnoreCase("right")) return Keyboard.KEY_RIGHT;
        if (keyCode.equalsIgnoreCase("left")) return Keyboard.KEY_LEFT;

        if (keyCode.equalsIgnoreCase("del")) return Keyboard.KEY_DELETE;
        if (keyCode.equalsIgnoreCase("insert")) return Keyboard.KEY_INSERT;
        if (keyCode.equalsIgnoreCase("end")) return Keyboard.KEY_END;
        if (keyCode.equalsIgnoreCase("home")) return Keyboard.KEY_HOME;
        if (keyCode.equalsIgnoreCase("lshift")) return Keyboard.KEY_LSHIFT;
        if (keyCode.equalsIgnoreCase("tab")) return Keyboard.KEY_TAB;
        if (keyCode.equalsIgnoreCase(".")) return Keyboard.KEY_PERIOD;
        if (keyCode.equalsIgnoreCase("strg")) return Keyboard.KEY_LCONTROL;
        if (keyCode.equalsIgnoreCase("alt")) return Keyboard.KEY_LMENU;
        if (keyCode.equalsIgnoreCase("hashtag")) return Keyboard.KEY_SLASH;
        if (keyCode.equalsIgnoreCase("rshift")) return Keyboard.KEY_RSHIFT;
        if (keyCode.equalsIgnoreCase("none")) return 0;
        if (keyCode.equalsIgnoreCase("minus")) return Keyboard.KEY_MINUS;

        return 0;
    }

    public static String toString(int keyCode) {
        if (keyCode == 30) {
            return "A";
        }
        if (keyCode == 48) {
            return "B";
        }
        if (keyCode == 46) {
            return "C";
        }
        if (keyCode == 32) {
            return "D";
        }
        if (keyCode == 18) {
            return "E";
        }
        if (keyCode == 33) {
            return "F";
        }
        if (keyCode == 34) {
            return "G";
        }
        if (keyCode == 35) {
            return "H";
        }
        if (keyCode == 23) {
            return "I";
        }
        if (keyCode == 36) {
            return "J";
        }
        if (keyCode == 37) {
            return "K";
        }
        if (keyCode == 38) {
            return "L";
        }
        if (keyCode == 50) {
            return "M";
        }
        if (keyCode == 49) {
            return "N";
        }
        if (keyCode == 24) {
            return "O";
        }
        if (keyCode == 25) {
            return "P";
        }
        if (keyCode == 16) {
            return "Q";
        }
        if (keyCode == 19) {
            return "R";
        }
        if (keyCode == 31) {
            return "S";
        }
        if (keyCode == 20) {
            return "T";
        }
        if (keyCode == 22) {
            return "U";
        }
        if (keyCode == 47) {
            return "V";
        }
        if (keyCode == 17) {
            return "W";
        }
        if (keyCode == 45) {
            return "X";
        }
        if (keyCode == 21) {
            return "Y";
        }
        if (keyCode == 44) {
            return "Z";
        }
        return "";
    }

    public static String translateKeyBind(int keyCode) {
        if(keyCode == Keyboard.KEY_BACKSLASH) { return "^"; }
        if(keyCode == Keyboard.KEY_1) { return "1"; }
        if(keyCode == Keyboard.KEY_2) { return "2"; }
        if(keyCode == Keyboard.KEY_3) { return "3"; }
        if(keyCode == Keyboard.KEY_4) { return "4"; }
        if(keyCode == Keyboard.KEY_5) { return "5"; }
        if(keyCode == Keyboard.KEY_6) { return "6"; }
        if(keyCode == Keyboard.KEY_7) { return "7"; }
        if(keyCode == Keyboard.KEY_8) { return "8"; }
        if(keyCode == Keyboard.KEY_9) { return "9"; }
        if(keyCode == Keyboard.KEY_0) { return "0"; }
        if(keyCode == Keyboard.KEY_LBRACKET) { return "ï¿½"; }
        if(keyCode == Keyboard.KEY_RBRACKET) { return "'"; }
        if(keyCode == Keyboard.KEY_BACK) { return "Back"; }

        if(keyCode == Keyboard.KEY_TAB) { return "Tab"; }
        if(keyCode == Keyboard.KEY_SEMICOLON) { return "Semicolon"; }
        if(keyCode == Keyboard.KEY_ADD) { return "+"; }
        if(keyCode == Keyboard.KEY_RETURN) { return "Return"; }

        if(keyCode == Keyboard.KEY_SLASH) { return "#"; }
        if(keyCode == Keyboard.KEY_COMMA) { return ","; }
        if(keyCode == Keyboard.KEY_PERIOD) { return "."; }
        if(keyCode == Keyboard.KEY_MINUS) { return "-"; }
        if(keyCode == Keyboard.KEY_LSHIFT) { return "Shift"; }
        if(keyCode == Keyboard.KEY_RSHIFT) { return "RShift"; }
        if(keyCode == Keyboard.KEY_LCONTROL) { return "Strg"; }
        if(keyCode == Keyboard.KEY_RCONTROL) { return "R-Strg"; }
        if(keyCode == Keyboard.KEY_LMENU) { return "Alt"; }
        if(keyCode == Keyboard.KEY_SPACE) { return "Space"; }
        if(keyCode == Keyboard.KEY_APPS) { return "AltGr"; }

        if(keyCode == Keyboard.KEY_A) { return "A"; }
        if(keyCode == Keyboard.KEY_B) { return "B"; }
        if(keyCode == Keyboard.KEY_C) { return "C"; }
        if(keyCode == Keyboard.KEY_D) { return "D"; }
        if(keyCode == Keyboard.KEY_E) { return "E"; }
        if(keyCode == Keyboard.KEY_F) { return "F"; }
        if(keyCode == Keyboard.KEY_G) { return "G"; }
        if(keyCode == Keyboard.KEY_H) { return "H"; }
        if(keyCode == Keyboard.KEY_I) { return "I"; }
        if(keyCode == Keyboard.KEY_J) { return "J"; }
        if(keyCode == Keyboard.KEY_K) { return "K"; }
        if(keyCode == Keyboard.KEY_L) { return "L"; }
        if(keyCode == Keyboard.KEY_M) { return "M"; }
        if(keyCode == Keyboard.KEY_N) { return "N"; }
        if(keyCode == Keyboard.KEY_O) { return "O"; }
        if(keyCode == Keyboard.KEY_P) { return "P"; }
        if(keyCode == Keyboard.KEY_Q) { return "Q"; }
        if(keyCode == Keyboard.KEY_R) { return "R"; }
        if(keyCode == Keyboard.KEY_S) { return "S"; }
        if(keyCode == Keyboard.KEY_T) { return "T"; }
        if(keyCode == Keyboard.KEY_U) { return "U"; }
        if(keyCode == Keyboard.KEY_V) { return "V"; }
        if(keyCode == Keyboard.KEY_W) { return "W"; }
        if(keyCode == Keyboard.KEY_X) { return "X"; }
        if(keyCode == Keyboard.KEY_Y) { return "Y"; }
        if(keyCode == Keyboard.KEY_Z) { return "Z"; }
        return "/";
    }
}
