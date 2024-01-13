package cn.ksmcbrigade.CCM;

import java.io.File;
import java.util.Arrays;

public class ModManager {
    private final String ModsDir;
    private final ModInfo[] mods;

    public ModManager(String modDir){
        this.ModsDir = modDir;
        if(!new File(this.ModsDir).exists()){
            mkd(this.ModsDir);
        }
        File[] files = new File(this.ModsDir).listFiles();
        this.mods = new ModInfo[files.length+1];
        for(int i = 0; i < files.length; i++){
            this.mods[i] = new ModInfo(files[i]);
        }
        this.mods[files.length] = new ModInfo("help","ccm","cn.ksmcbrigade.CCM.Manager","help");
    }

    @Override
    public String toString() {
        return "ModManager{" +
                "ModsDir='" + ModsDir + '\'' +
                ", mods=" + Arrays.toString(mods) +
                '}';
    }

    public String getModsDir() {
        return ModsDir;
    }

    public ModInfo[] getMods() {
        return mods;
    }

    public static void mkd(String dir){
        String[] dirs = dir.replace("\"","").split("/");
        String last = "";
        for(String s:dirs){
            if(last.equals("")){
                last = s;
                new File(last).mkdirs();
            }else{
                last = last + "/" + s;
                new File(last).mkdirs();
            }
        }
    }
}
