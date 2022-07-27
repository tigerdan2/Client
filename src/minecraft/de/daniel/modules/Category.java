package de.daniel.modules;

import java.awt.*;

public enum Category {
    Combat,
    Movement,
    Player,
    Render,
    World;


    public static Color getColorForCategory(Category category){
        switch (category){
            case Combat:
                return new Color(0xffe74c3c);
            case Movement:
                return new Color(0xff2ecc71);
            case Player:
                return new Color(0xff3498db);
            case Render:
                return new Color(0xff8e44ad);
            case World:
                return new Color(0xffe67e22);
        }
        System.out.println("Somehow you entered a invalid Category. I did not expect to ever read this");
        return null;
    }

}
