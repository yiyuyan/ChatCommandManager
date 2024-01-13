package cn.ksmcbrigade.CCM;

import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

@Mod("ccm")
@Mod.EventBusSubscriber
public class ChatCommandManager {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final ModManager COMMANDS_MANAGER = new ModManager("config/ccm/mods");

    public static Player player = Minecraft.getInstance().player;

    public ChatCommandManager() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void setPlayer(TickEvent.PlayerTickEvent event){
        player = event.player;
    }

    @SubscribeEvent
    public static void runCommands(ClientChatEvent event) throws Exception {
        String command = getCommand(event.getMessage());
        System.out.println(command);
        if(!Objects.equals(command, "")){
            for(ModInfo mod: COMMANDS_MANAGER.getMods()){
                System.out.println("."+mod.getName());
                System.out.println(("."+mod.getName()).equalsIgnoreCase(command));
                if(("."+mod.getName()).equalsIgnoreCase(command)){
                    String[] args = getArgs(event.getMessage(),command);
                    LOGGER.info("Running command: " + command);
                    LOGGER.info("Running Command args: "+toStr(args));
                    mod.Run(player,args);
                    event.setMessage("");
                }
            }
        }
    }

    public static String getCommand(String message){
        if(String.valueOf(message.charAt(0)).equals(".")){
            if(message.contains(" ")){
                return message.split(" ")[0];
            }
            else{
                return message;
            }
        }
        else{
            return "";
        }
    }

    public static String[] getArgs(String message,String command){
        if(message.replace(command,"").equals("") | message.replace(command,"").equals(" ")){
            return new String[0];
        }
        else{
            return message.replace(command+" ","").split(" ");
        }
    }

    public static String toStr(String[] args){
        StringBuilder str = new StringBuilder();
        for(String arg:args){
            if(str.toString().equals("")){
                str = new StringBuilder(arg);
            }
            else{
                str.append(",").append(arg);
            }
        }
        return str.toString();
    }
}
