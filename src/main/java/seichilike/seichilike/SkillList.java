package seichilike.seichilike;

import java.util.ArrayList;

public class SkillList {
    public ArrayList<String> SkillListDescription(double level){
        ArrayList<String> SkillList = new ArrayList<>();

        if (level >= 3){
            // can only use 1 skill.
            SkillList.add("Small Miner\n->"+this.Description("small-miner"));

        }else if(level >= 10){
            // use 2skills.
            SkillList.add("Small Miner\n->"+this.Description("small-miner"));
            SkillList.add("Medium Miner\n->"+this.Description("medium-miner"));
        }else if(level >= 15){
            // use 3 skills
            SkillList.add("Small Miner\n->"+this.Description("small-miner"));
            SkillList.add("Medium Miner\n->"+this.Description("medium-miner"));
            SkillList.add("Miner\n->"+this.Description("miner"));
        }else if(level >=25){
            // use 4 skills
            SkillList.add("Small Miner\n->"+this.Description("small-miner"));
            SkillList.add("Medium Miner\n->"+this.Description("medium-miner"));
            SkillList.add("Miner\n->"+this.Description("miner"));
            SkillList.add("Big Miner\n->"+this.Description("big-miner"));
        }else if(level >=40){
            //use 5 skills
            SkillList.add("Small Miner\n->"+this.Description("small-miner"));
            SkillList.add("Medium Miner\n->"+this.Description("medium-miner"));
            SkillList.add("Miner\n->"+this.Description("miner"));
            SkillList.add("Big Miner\n->"+this.Description("big-miner"));
            SkillList.add("large Miner\n->"+this.Description("large-miner"));
        }else if(level >=65){
            //use 6 skills
            SkillList.add("Small Miner\n->"+this.Description("small-miner"));
            SkillList.add("Medium Miner\n->"+this.Description("medium-miner"));
            SkillList.add("Miner\n->"+this.Description("miner"));
            SkillList.add("Big Miner\n->"+this.Description("big-miner"));
            SkillList.add("large Miner\n->"+this.Description("large-miner"));
            SkillList.add("???\n->"+this.Description("???-miner"));
        }


        return SkillList;
    }

    public ArrayList<String> SkillListNormal(double level){
        ArrayList<String> SkillList = new ArrayList<>();

        if (level >= 3){
            // can only use 1 skill.
            SkillList.add("Small Miner");

        }else if(level >= 10){
            // use 2skills.
            SkillList.add("Small Miner");
            SkillList.add("Medium Miner");
        }else if(level >= 15){
            // use 3 skills
            SkillList.add("Small Miner");
            SkillList.add("Medium Miner");
            SkillList.add("Miner");
        }else if(level >=25){
            // use 4 skills
            SkillList.add("Small Miner");
            SkillList.add("Medium Miner");
            SkillList.add("Miner");
            SkillList.add("Big Miner");
        }else if(level >=40){
            //use 5 skills
            SkillList.add("Small Miner\n->"+this.Description("small-miner"));
            SkillList.add("Medium Miner\n->"+this.Description("medium-miner"));
            SkillList.add("Miner\n->"+this.Description("miner"));
            SkillList.add("Big Miner\n->"+this.Description("big-miner"));
            SkillList.add("large Miner\n->"+this.Description("large-miner"));
        }else if(level >=65){
            //use 6 skills
            SkillList.add("Small Miner\n->"+this.Description("small-miner"));
            SkillList.add("Medium Miner\n->"+this.Description("medium-miner"));
            SkillList.add("Miner\n->"+this.Description("miner"));
            SkillList.add("Big Miner\n->"+this.Description("big-miner"));
            SkillList.add("large Miner\n->"+this.Description("large-miner"));
            SkillList.add("???\n->"+this.Description("???-miner"));
        }

        return SkillList;
    }

    public String Description(String SkillName){
        String Description = "";
        switch(SkillName){
            case "small-miner":
                Description = "§bSmall Miner can break 3*3*3 blocks in front of you instead you.";

            case "medium-miner":
                Description = "§bMedium Miner can break 5*5*5 blocks in front of you instead you.";

            case "miner":
                Description = "§bMiner can break 7*7*7 blocks in front of you instead you.";

            case "big-miner":
                Description = "§bBig Miner can break 8*8*8 blocks in front of you instead you.";

            case "large-miner":
                Description = "§bLarge Miner can break 9*9*9 blocks in front of you instead you.";

            case "???-miner":
                Description = "§b??? breaks 11*11*11 blocks.";

            case "ice-era":
                Description = "§bIce Era can freeze water that around you.";

            case "lava-generation":
                Description = "§bLava Era can agglomeration lava blocks that around you.";
        }

        return Description;
    }
}
