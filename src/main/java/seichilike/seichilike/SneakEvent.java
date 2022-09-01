package seichilike.seichilike;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class SneakEvent implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){

        Player player = event.getPlayer();
        if(!(player.isSneaking())){
            return;
        }

        if(event.getAction()==Action.RIGHT_CLICK_AIR || event.getAction()==Action.RIGHT_CLICK_BLOCK){
            // the player do right-click



            if(player.getInventory()==null){
                return;
            }


            ItemStack mainHand = player.getInventory().getItemInMainHand();
            //debug
            player.sendMessage("extra");

            if(!(player.getInventory().getItemInOffHand()==null)){
                // offhand item is not null.
                ItemStack offHand = player.getInventory().getItemInOffHand();
                if(mainHand.getType().toString().toUpperCase(Locale.ROOT).contains("PICKAXE") && offHand.containsEnchantment(Enchantment.ARROW_INFINITE)){
                    if(offHand.getType()==Material.DRIED_KELP && offHand.getAmount() >= 32){
                        this.addDigSpeed(32,"宇迦之御魂神","§3class 宇迦之御魂神",player);
                        return;

                    }else if(offHand.getType()==Material.SPORE_BLOSSOM && offHand.getAmount() >= 32){
                        this.addDigSpeed(32,"木花之佐久夜毘売","§3class 木花之佐久夜毘売",player);
                        return;

                    }else if(offHand.getType()==Material.SHROOMLIGHT && offHand.getAmount() >= 32){
                        this.addDigSpeed(32,"月読命","§3class 月読命",player);
                        return;

                    }else if(offHand.getType()==Material.WATER_BUCKET){
                        this.addDigSpeed(1,"大綿津見神","§3class 大綿津見神",player);
                        return;

                    }else if(offHand.getType()==Material.TORCH && offHand.getAmount() >= 32){
                        this.addDigSpeed(32,"瓊瓊杵尊","§3class 瓊瓊杵尊",player);
                        return;

                    }else if(offHand.getType()==Material.CHORUS_FLOWER && offHand.getAmount() >= 32){
                        this.addDigSpeed(32,"客人","§3class 客人",player);
                        return;

                    }else if(offHand.getType()==Material.COBBLED_DEEPSLATE && offHand.getAmount() >= 32){
                        if(offHand.getItemMeta().getDisplayName().contains("殺")){
                            // izanami process
                            this.addDigSpeed(32,"伊邪那美命","§3class 伊邪那美命",player);
                            return;

                        }else if(offHand.getItemMeta().getDisplayName().contains("産")){
                            // izanagi process
                            this.addDigSpeed(32,"伊邪那岐命","§3class 伊邪那岐命",player);
                            return;

                        }
                    }else if(offHand.getType()==Material.FEATHER && offHand.getAmount() >= 32){
                        this.addDigSpeed(32,"素戔男尊","§3class 素戔男尊",player);
                        return;

                    }else if(offHand.getType()==Material.DIAMOND && offHand.getAmount()==64){
                        this.addDigSpeed(64,"天照大御神","§3class 天照大御神",player);
                        return;

                    }
                }

            }



            if(mainHand.getType()==Material.WOODEN_PICKAXE && mainHand.getEnchantmentLevel(Enchantment.MENDING)==5){
                //wooden pickaxe -> coal pickaxe
                ArrayList<String> lore = new ArrayList<>(Arrays.asList("§r§fThis item is an unbreakable tool.","","§r§fNext tool is StonePickaxe","PublicServer 2022",""));

                this.upgrade(Material.WOODEN_PICKAXE,Material.WOODEN_PICKAXE,lore,"§r§fCoalPickaxe",Enchantment.MENDING,5,6,player);
                return;

            }
            if(mainHand.getType()==Material.WOODEN_PICKAXE && mainHand.getEnchantmentLevel(Enchantment.MENDING)==6){
                //coal pickaxe -> stone pickaxe
                ArrayList<String> lore = new ArrayList<>(Arrays.asList("§r§fThis item is an unbreakable tool.","","§r§fNext tool is IronPickaxe","PublicServer 2022",""));

                this.upgrade(Material.WOODEN_PICKAXE,Material.STONE_PICKAXE,lore,"§r§fStonePickaxe",Enchantment.MENDING,6,7,player);
                return;

            }
            if(mainHand.getType()==Material.STONE_PICKAXE && mainHand.getEnchantmentLevel(Enchantment.MENDING)==7){
                // stone pickaxe -> iron pickaxe
                ArrayList<String> lore = new ArrayList<>(Arrays.asList("§r§fThis items is an unbreakable tool.","","§r§fNext tool is CopperPickaxe","PublicServer 2022",""));

                this.upgrade(Material.STONE_PICKAXE,Material.IRON_PICKAXE,lore,"§r§fIronPickaxe",Enchantment.MENDING,7,8,player);
                return;


            }
            if(mainHand.getType()==Material.IRON_PICKAXE && mainHand.getEnchantmentLevel(Enchantment.MENDING)==8){
                // iron pickaxe -> copper pickaxe
                ArrayList<String> lore = new ArrayList<>(Arrays.asList("§r§fThis item is an unbreakable tool.","","§r§fNext tool is LapisPickaxe.","PublicServer 2022",""));

                this.upgrade(Material.IRON_PICKAXE,Material.IRON_PICKAXE,lore,"§r§fCopperPickaxe",Enchantment.MENDING,8,9,player);
                return;

            }
            if(mainHand.getType()==Material.IRON_PICKAXE && mainHand.getEnchantmentLevel(Enchantment.MENDING)==9){
                // copper pickaxe -> LapisLazuli pickaxe
                ArrayList<String> lore = new ArrayList<>(Arrays.asList("§r§fThis item is an unbreakable tool.","","§r§fNext tool is RedStonePickaxe.","PublicServer 2022",""));

                this.upgrade(Material.IRON_PICKAXE,Material.IRON_PICKAXE,lore,"§9§rLapisLazuliPickaxe",Enchantment.MENDING,9,10,player);
                return;

            }
            if(mainHand.getType()==Material.IRON_PICKAXE && mainHand.getEnchantmentLevel(Enchantment.MENDING)==10){
                //lapis pickaxe -> redStone pickaxe
                ArrayList<String> lore = new ArrayList<>(Arrays.asList("§r§fThis item is an unbreakable tool.","","§r§fNext tool is GoldenPickaxe.","PublicServer 2022",""));

                this.upgrade(Material.IRON_PICKAXE,Material.IRON_PICKAXE,lore,"§r§cRedStonePickaxe",Enchantment.MENDING,10,11,player);
                return;

            }
            if(mainHand.getType()==Material.IRON_PICKAXE && mainHand.getEnchantmentLevel(Enchantment.MENDING)==11){
                //RedStone pickaxe -> golden pickaxe
                ArrayList<String> lore = new ArrayList<>(Arrays.asList("§r§fThis item is an unbreakable tool.","","§r§fNext tool is DiamondPickaxe.","PublicServer 2022",""));

                this.upgrade(Material.IRON_PICKAXE,Material.GOLDEN_PICKAXE,lore,"§r§6GoldenPickaxe",Enchantment.MENDING,11,12,player);
                return;


            }else if(mainHand.getType()==Material.GOLDEN_PICKAXE && mainHand.getEnchantmentLevel(Enchantment.MENDING)==12){
                // golden pickaxe -> diamond pickaxe
                ArrayList<String> lore = new ArrayList<>(Arrays.asList("§r§fThis item is an unbreakable tool.","","§r§fNext tool is NetheritePickaxe.","PublicServer 2022",""));

                this.upgrade(Material.GOLDEN_PICKAXE,Material.DIAMOND_PICKAXE,lore,"§r§bDiamondPickaxe",Enchantment.MENDING,12,13,player);
                return;

            }else if(mainHand.getType()==Material.DIAMOND_PICKAXE && mainHand.getEnchantmentLevel(Enchantment.MENDING)==13){
                // diamond pickaxe -> netherite pickaxe
                ArrayList<String> lore = new ArrayList<>(Arrays.asList("§r§fThis item is an unbreakable tool.","","§r§fThis item is a top.","PublicServer 2022",""));

                this.upgrade(Material.DIAMOND_PICKAXE,Material.NETHERITE_PICKAXE,lore,"§r§kNetheritePickaxe",Enchantment.MENDING,13,14,player);
                return;

            }
            /*
                ↓ dig speed upgrade.
            */
            if(player.getInventory().getItemInOffHand()==null){
                return;
            }


        }
    }

    public void upgrade(Material before, Material after, ArrayList<String> lore,String itemName,Enchantment enchantment,int enchantLevelBefore,int enchantLevelAfter,Player player){

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        World world = player.getWorld();

        if(!(itemStack==null) && itemStack.getType()==before){
            // the player has wooden pickaxe in their main hand.
            if(itemStack.getEnchantmentLevel(enchantment)==enchantLevelBefore){
                // an item that the player has in main hand contains an enchantment (mending LV.5)
                int pickaxeCounter = 0;
                for (int i=0;i<=35;i++){
                    ItemStack checkItem = player.getInventory().getItem(i);

                    if(player.getInventory().getItem(i)==null){
                        continue;
                    }

                    if(!(checkItem==null) && checkItem.getType()==before && checkItem.getEnchantmentLevel(enchantment)==enchantLevelBefore){
                        pickaxeCounter++;
                    }
                }

                if(pickaxeCounter >= 5){
                    ItemStack giveTool = new ItemStack(after);
                    ItemMeta ToolMeta = giveTool.getItemMeta();

                    // iron pickaxes setting
                    ToolMeta.setUnbreakable(true);
                    ToolMeta.setDisplayName(itemName);
                    ToolMeta.setLore(lore);
                    ToolMeta.addEnchant(enchantment,enchantLevelAfter,true);
                    ToolMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    giveTool.setItemMeta(ToolMeta);

                    int deleteCounter = 0;

                    for (int i=1;i<=pickaxeCounter / 5;i++){
                        world.dropItemNaturally(player.getLocation(),giveTool);
                        for (int ii=0;i<=35;ii++){
                            ItemStack giveCheck = player.getInventory().getItem(ii);

                            if(player.getInventory().getItem(ii)==null){
                                continue;
                            }

                            if(!(player.getInventory()==null) && giveCheck.getType()==before && giveCheck.getEnchantmentLevel(enchantment)==enchantLevelBefore && deleteCounter < 5){
                                giveCheck.setAmount(0);
                                deleteCounter++;
                            }else if(deleteCounter >= 5){
                                deleteCounter = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void addDigSpeed(int amountMinus,String keyword,String newLore,Player player){
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        ItemStack offHand = player.getInventory().getItemInOffHand();
        int loreSize = mainHand.getItemMeta().getLore().size();
        int offHandAmount = offHand.getAmount();
        ItemMeta itemMeta = mainHand.getItemMeta();

        ArrayList<String> mainHandLore = new ArrayList<>(mainHand.getItemMeta().getLore());

        for (int i=0;i<loreSize;i++){
            if(mainHand.getItemMeta().getLore().get(i).contains(keyword)){
                return;
            }
        }

        //set extra effect

        mainHandLore.add(newLore);
        itemMeta.setLore(mainHandLore);
        mainHand.setItemMeta(itemMeta);

        offHand.setAmount(offHandAmount-amountMinus);

        int enchantLevel = 1;
        if(mainHand.containsEnchantment(Enchantment.DIG_SPEED)){
            enchantLevel += mainHand.getEnchantmentLevel(Enchantment.DIG_SPEED);
            mainHand.removeEnchantment(Enchantment.DIG_SPEED);
        }

        mainHand.addUnsafeEnchantment(Enchantment.DIG_SPEED,enchantLevel);
    }
}
