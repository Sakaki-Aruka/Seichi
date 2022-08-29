package seichilike.seichilike;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class SettingsLoad {
    public static FileConfiguration fileConfiguration_2;

    public void fc(FileConfiguration fileConfiguration){
        fileConfiguration_2 = fileConfiguration;
        this.getConfig();
        this.setTreasureWarning();
    }

    private static ArrayList<String> lore = new ArrayList<>();
    private static String treasureWarning;

    public void getConfig(){
        // load settings
        int lores = fileConfiguration_2.getInt("Lores");
        for(int i=1;i<=lores;i++){
            String path = "treasure"+i;

            int LoreLines = fileConfiguration_2.getInt(path+".LoreLines");
            for(int ii=1;ii<=LoreLines;ii++){
                lore.add(fileConfiguration_2.getString(path+"."+ii));
            }
        }
    }

    // return treasure chests lore
    public ArrayList<String> getLore(){
        return lore;
    }

    // get warning(about treasure chest drop) text from config.yml
    public void setTreasureWarning(){
        treasureWarning = fileConfiguration_2.getString("treasureDropWarn");
    }

    // return treasure chest warn text
    public String getTreasureWarning(){
        return treasureWarning;
    }
}
