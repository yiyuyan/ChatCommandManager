package cn.ksmcbrigade.CCM;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.world.entity.player.Player;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;

public class ModInfo {

    private final String name;
    private final String id;
    private final String main;
    private final String function;

    public ModInfo(File file){
        try{
            JsonObject json = (JsonObject) JsonParser.parseString(Files.readString(file.toPath()));
            this.name = json.get("name").getAsString();
            this.id = json.get("id").getAsString();
            this.main = json.get("main").getAsString();
            this.function = json.get("function").getAsString();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ModInfo(String name, String id, String main, String function){
        try{
            this.name = name;
            this.id = id;
            this.main = main;
            this.function = function;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "ModInfo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", main='" + main + '\'' +
                ", function='" + function + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getFunction() {
        return function;
    }

    public void Run(Player player,String... args) throws Exception{
        Object[] objects = new Object[]{player,args};
        Class<?>[] parameterTypes = new Class[]{Player.class,String[].class};
        Class<?> clazz = Class.forName(this.main);
        Method method = clazz.getDeclaredMethod(this.function,parameterTypes);
        method.setAccessible(true);
        method.invoke(clazz.getDeclaredConstructor().newInstance(),objects);
    }
}
