package de.daniel.modules;

import de.daniel.Client;
import de.daniel.modules.events.Events.EventManager;

public abstract class Mod {
    public String name;
    public int keyBind;
    public Category c;
    public boolean isEnabled = false;
    public void onToggle(){

    }

    public void onEnable(){
        EventManager.register(this);
    }

    public void onDisable(){
        EventManager.unregister(this);

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

    public Mod(){

        this.name = this.getClass().getName();
      //  System.out.println("OUTPUT" + this.getClass().getPackage().getName().split("\\.")[3].toLowerCase());
        String category = this.getClass().getPackage().getName().split("\\.")[3].toLowerCase();
        this.c = Category.valueOf(category.substring(0, 1).toUpperCase() + category.substring(1));
        this.keyBind = 0;
        System.out.println("Name " + name+ " Category " + c);
    }


}
