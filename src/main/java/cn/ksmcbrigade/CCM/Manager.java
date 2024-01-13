package cn.ksmcbrigade.CCM;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Manager {

    public static void run(Player player,String[] args){
        player.sendMessage(Component.nullToEmpty("Chat command manager v1.0"),player.getUUID());
    }

    public static void help(Player player,String[] args){
        player.sendMessage(Component.nullToEmpty("all commands:"),player.getUUID());
        for(ModInfo mod:ChatCommandManager.COMMANDS_MANAGER.getMods()){
            player.sendMessage(Component.nullToEmpty("."+mod.getName()),player.getUUID());
        }
    }
}
