package enchantmentplus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EPListener implements Listener {
    private EnchantmentPlus plugin;
    
    EPListener(EnchantmentPlus plugin) {
        this.plugin=plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked().hasPermission("EnchantmentPlus.use") && plugin.getConfig().getList("worlds").contains(event.getWhoClicked().getLocation().getWorld().getName())) {
            if (event.isRightClick()) return;
            if (event.getInventory().getType()==InventoryType.ANVIL && event.getSlotType()==SlotType.RESULT) {
                if (event.getInventory().getItem(1)!=null && event.getInventory().getItem(1).getType()==Material.ENCHANTED_BOOK && event.getInventory().getItem(0)!=null && event.getCurrentItem().getType()!=Material.AIR) {

                    // TODO wenn amboss kaputt -> items an der stelle droppen
                    
                    final Inventory inventory=event.getInventory();
                    final Player player=(Player)event.getWhoClicked();
                    final int level=player.getLevel();
                    final ItemStack firstItem=event.getInventory().getItem(0).clone();
                    final ItemStack enchantedBook=event.getInventory().getItem(1).clone();
                    final ItemStack result=event.getCurrentItem().clone();
                    
                    //final Location location=((BlockState)inventory.getHolder()).getLocation(); // TODO getholder liefert null zurück
                    
                    
                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(
                        plugin,
                        new Runnable() {
                            @Override
                            public void run() {
                                int levelCost=level-player.getLevel();
                                double levelFactor=plugin.getConfig().getDouble("cost.levelfactor");
                                //boolean broken=(location.getBlock().getState().getType()==Material.AIR);
                                
                                if (player.getLevel()>=(int)(levelCost*levelFactor)) {
                                    player.setLevel(player.getLevel()-(int)(levelCost*levelFactor));
                                    
                                    //if (broken) location.getWorld().dropItemNaturally(location, enchantedBook);
                                    //else {
                                        inventory.setItem(1, enchantedBook);
                                    //}
                                }
                                else {
                                    player.setLevel(player.getLevel()+levelCost);
                                    
                                    // TODO amboss schaden rückgängig machen
                                    
                                    //if (broken) {
                                    //    location.getWorld().dropItemNaturally(location, firstItem);
                                    //    location.getWorld().dropItemNaturally(location, enchantedBook);
                                    //}
                                    //else {
                                        inventory.setItem(0, firstItem);
                                        inventory.setItem(1, enchantedBook);
                                    //}
                                    
                                    if (player.getItemOnCursor().equals(result)) {
                                        player.setItemOnCursor(new ItemStack(Material.AIR));
                                    }
                                    else {
                                        for (int i=0;i<player.getInventory().getSize();i++) {
                                            if (player.getInventory().getItem(i)!=null && player.getInventory().getItem(i).equals(result)) {
                                                player.getInventory().setItem(i,new ItemStack(Material.AIR));
                                                break;
                                            }
                                        }
                                    }
                                    
                                    player.sendMessage(plugin.getConfig().getString("strings.notenoughlevel").replaceAll("%level%", ""+((int)(levelCost*(levelFactor+1)))).replaceAll("%factor%",levelFactor+""));
                                }
                            }
                        },
                        1
                    );
                }
            }
        }
    }
}
