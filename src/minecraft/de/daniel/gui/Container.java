package de.daniel.gui;

import de.daniel.Client;
import org.lwjgl.input.Mouse;

public class Container {


    public float x;
    public float y;

    public float lastTickPosX;
    public float lastTickPosY;

    public float width;
    public float height;

    public boolean overCat;

    public void draw(float i1,int i2,int i3){

    }
    public void release(int mouseX,int mouseY,int id){

    }

    public void handleMouse(int mouseX,int mouseZ){

        overCat = mouseX > x && mouseX <x+100 && mouseZ > y && mouseZ < y+20;
        int deltaX = (int) (mouseX -lastTickPosX);
        int deltaY = (int) (mouseZ -lastTickPosY);
        lastTickPosX = mouseX;
        lastTickPosY = mouseZ;
        if(!Mouse.isButtonDown(0)){
            Client.instance.clickGui.selectedContainer = null;
        }
        if(Client.instance.clickGui.processClick){
            if(Client.instance.clickGui.selectedContainer == this || Client.instance.clickGui.selectedContainer == null){
                if(overCat) {
                    if (Mouse.isButtonDown(0)) {
                        //Change Order
                        Client.instance.clickGui.selectedContainer = this;
                        Client.getInstance().clickGui.categories.remove(this);
                        Client.getInstance().clickGui.categories.add(this);
                        x += deltaX;
                        y += deltaY;
                        //Client.instance.clickGui.processClick = false;
                    }
                    if (Mouse.isButtonDown(1) &&! (this instanceof CategoryContainer)) {
                        Client.getInstance().clickGui.categories.remove(this);

                    }
                }
            }
        }
    }

}
