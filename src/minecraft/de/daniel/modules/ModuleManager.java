package de.daniel.modules;

import com.google.gson.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import de.daniel.Client;
import de.daniel.modules.values.Value;
import de.daniel.modules.values.ValueBoolean;
import de.daniel.utils.CifUtil;
import net.minecraft.client.Minecraft;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ModuleManager {

    public ArrayList<Module> mods = new ArrayList<>();
    public HashMap<Class,Module> modsmap = new HashMap<>();
    public void saveModulesThread(File f){
        new Thread(){
            Gson gson = new Gson();
            public void run(){
                   saveModules(f);
            }

        }.start();
    }

    HashMap<Category,ArrayList<Module>> modsForCat = new HashMap<>();

    public ArrayList<Module> getModsForCat(Category c2){
        if(modsForCat.containsKey(c2)){
            return modsForCat.get(c2);
        }else {
            ArrayList<Module> mods = new ArrayList<>();
            for (Module m : this.mods) {
                if (m.c == c2) {
                    mods.add(m);
                }
            }
            modsForCat.put(c2,mods);
        }
        return mods;
    }









    public void saveModules(File file)  {

        long ms = System.currentTimeMillis();

        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileWriter(file, false));

            JsonObject saver=new JsonObject();
            for(Module mod : mods){

                JsonObject moduleJson=new JsonObject();
                moduleJson.addProperty("keyBind",mod.keyBind);
                moduleJson.addProperty("state",mod.isEnabled);
                JsonArray valueJson=new JsonArray();

                for(Value value : mod.values){
                    JsonObject value2 = new JsonObject();
                    value2.addProperty("name", String.valueOf(value.name));
                    value2.addProperty("value", String.valueOf(value.getValue()));
                    valueJson.add(value2);
                }
                moduleJson.add("values",valueJson);
                saver.add(mod.name,moduleJson);
            }
            //System.out.println(saver.toString());
            output.println(saver.toString());
            output.close();
        } catch (IOException e) {
            System.out.println("Was ein glück das hier try catch verwendet wurde. wird *bestimmt* kein problem geben");
        }

        System.out.println(System.currentTimeMillis() - ms + " took");
    }

    public void loadFromFile(File f){
        try {
            String jsonRaw = readFile(f);
            JsonObject jsonObject = new JsonParser().parse(jsonRaw).getAsJsonObject();

            for (Module module : mods) {
                try {
                    JsonObject moduleDate = (JsonObject) jsonObject.get(module.name);

                    if(moduleDate.get("state").getAsBoolean()){
                        module.toggle();
                    }
                    module.keyBind = moduleDate.get("keyBind").getAsInt();

                    for(JsonElement s : moduleDate.get("values").getAsJsonArray()){
                        JsonObject object= s.getAsJsonObject();
                        for(Value v : module.values) {
                            if (v.name.equalsIgnoreCase(object.get("name").getAsString())){
                                if(v instanceof ValueBoolean){

                                    System.out.println("Found ValueBoolean " + v.name);
                                    v.value = object.get("value").getAsBoolean();
                                    System.out.println(object.get("value").getAsBoolean());
                                }
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("^ Dont worry. Most likely some new shit got added. Yay new stuff");
                }
            }
        }catch (Exception e){

        }
        for(Module module : mods){
           // module.generateDisplayName();
        }
    }

    public String readFile(File file) {
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String s = bufferedReader.readLine();

                bufferedReader.close();
                return s;
            } catch (Exception e) {

            }
            return "";
        }
        return "No File Found";
    }

    public void init()  {
        //Easy Module generation through Automatic Detection
        for(Category c : Category.values()) {
            try {
                System.out.println("Scanning " +this.getClass().getName().replace("ModuleManager","")  + c.name().toLowerCase());
                Set<Class> classes = CifUtil.findAllClassesUsingClassLoader(
                        this.getClass().getName().replace("ModuleManager","")  + c.name().toLowerCase());
                System.out.println(classes.size());
                if (classes == null) {
                    continue;
                }
                for (Class c2 : classes) {
                    Module m = null;
                    try {
                        System.out.println("Trying to create Mod");
                        m = (Module) c2.newInstance();
                        System.out.println("Created new Mod");
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    mods.add(m);
                    System.out.println("Found Module");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        loadFromFile(Client.getInstance().modules);
        for(Module mod : mods){
            modsmap.put(mod.getClass(),mod);
        }
    }

}
