package de.daniel.gui;

import de.daniel.modules.Category;
import de.daniel.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;

public class ClickGui extends GuiScreen {

    ArrayList<Container> categories = new ArrayList<>();
    public boolean processClick;
    public Container selectedContainer;
    public ClickGui(){
        int index = 0;
        for(Category c : Category.values()){
            categories.add(new CategoryContainer(c,index*140,10));
            index++;
        }
    }


    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        try {
            super.mouseReleased(mouseX, mouseY, state);
            for (int i = categories.size() - 1; i >= 0; --i) {
                categories.get(i).release(mouseX, mouseY, state);
            }
        }catch (Exception e){
            System.out.println("Vewy big oops");
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for(int i = categories.size()-1; i >= 0; --i){
            categories.get(i).handleMouse(mouseX,mouseY);
        }
        for(int i = 0; i < this.categories.size(); ++i){
            categories.get(i).draw(partialTicks,mouseX,mouseY);
        }
        processClick = true;
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }
}
