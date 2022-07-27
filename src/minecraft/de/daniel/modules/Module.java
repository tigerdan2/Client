package de.daniel.modules;

import de.Client.Events.EventManager;
import de.daniel.Client;
import de.daniel.modules.values.Value;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;


public abstract class Module {
    public String name;
    public int keyBind;
    public Category c;
    public boolean isEnabled = false;
    public ArrayList<Value> values = new ArrayList<>();
    public void onToggle(){

    }
    //@Trans
    public transient Minecraft mc = Minecraft.getMinecraft();
    public void onEnable(){
        EventManager.register(this);
    }
    public ArrayList<Value> getValues(){
        return values;
    }

    public void addValue(Value v){
        this.values.add(v);
    }

    public void onDisable(){
        EventManager.unregister(this);

    }

    public String generateDisplayName(){
        return name;
    }

    public void onUpdate(){

    }

    public void toggle(){
        isEnabled =!isEnabled;
        if(isEnabled){
            onEnable();
        }else{
            onDisable();
        }
        onToggle();
        Client.getInstance().mm.saveModulesThread(Client.instance.modules);
    }

    public Module(){

        this.name = this.getClass().getName().split("\\.")[4];
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
      //  System.out.println("OUTPUT" + this.getClass().getPackage().getName().split("\\.")[3].toLowerCase());
        String category = this.getClass().getPackage().getName().split("\\.")[3].toLowerCase();
        this.c = Category.valueOf(category.substring(0, 1).toUpperCase() + category.substring(1));
        this.keyBind = 0;
        System.out.println("Name " + name+ " Category " + c);
    }


}
