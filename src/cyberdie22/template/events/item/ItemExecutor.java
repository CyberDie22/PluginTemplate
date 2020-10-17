package cyberdie22.template.events.item;

import cyberdie22.multiversion.API;
import cyberdie22.template.TemplatePlugin;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class ItemExecutor implements Listener {

    TemplatePlugin plugin = TemplatePlugin.getPlugin(TemplatePlugin.class);

    @EventHandler
    public void OnInteractEvent(PlayerInteractEvent event) {
        try {
            Player player = event.getPlayer();
            ItemStack item_main = API.getItemInMainHand(player).clone();
            ItemStack item_off = API.getItemInOffHand(player).clone();
            item_main.setAmount(1);
            item_off.setAmount(1);
            Block block = event.getClickedBlock();
            if (TemplatePlugin.itemList.contains(item_main)) {
                player.sendMessage(String.valueOf(player.getInventory().getItemInMainHand().getAmount()));
                if (TemplatePlugin.baseItems.get(item_main).isOneTimeUse()) { ItemStack item = player.getInventory().getItemInMainHand().clone(); Integer amount = player.getInventory().getItemInMainHand().getAmount(); amount--; player.getInventory().getItemInMainHand().setAmount(amount); }
                player.sendMessage(String.valueOf(player.getInventory().getItemInMainHand().getAmount()));
                if (event.getPlayer().isSneaking()) {
                    switch (event.getAction()) {
                        case LEFT_CLICK_AIR:
                            TemplatePlugin.baseItems.get(item_main).shiftLeftClickAirAction(player, event);
                            break;
                        case LEFT_CLICK_BLOCK:
                            TemplatePlugin.baseItems.get(item_main).shiftLeftClickBlockAction(player, event, block, item_main);
                            break;
                        case RIGHT_CLICK_AIR:
                            TemplatePlugin.baseItems.get(item_main).shiftRightClickAirAction(player, event);
                            break;
                        case RIGHT_CLICK_BLOCK:
                            TemplatePlugin.baseItems.get(item_main).shiftRightClickBlockAction(player, event, block, item_main);
                            break;
                        }
                    } else {
                    switch (event.getAction()) {
                        case LEFT_CLICK_AIR:
                            TemplatePlugin.baseItems.get(item_main).leftClickAirAction(player, event);
                            break;
                        case LEFT_CLICK_BLOCK:
                            TemplatePlugin.baseItems.get(item_main).leftClickBlockAction(player, event, block, item_main);
                            break;
                        case RIGHT_CLICK_AIR:
                            TemplatePlugin.baseItems.get(item_main).rightClickAirAction(player, event);
                            break;
                        case RIGHT_CLICK_BLOCK:
                            TemplatePlugin.baseItems.get(item_main).rightClickBlockAction(player, event, block, item_main);
                            break;
                        }
                    }
                } else if (TemplatePlugin.itemList.contains(item_off)) {
                    if (TemplatePlugin.baseItems.get(item_off).isOneTimeUse()) player.getInventory().getItemInOffHand().setAmount(API.getItemInOffHand(player).getAmount()-1);
                    if (event.getPlayer().isSneaking()) {
                        switch (event.getAction()) {
                            case RIGHT_CLICK_AIR:
                                TemplatePlugin.baseItems.get(item_off).shiftRightClickAirAction(player, event);
                                break;
                            case RIGHT_CLICK_BLOCK:
                                TemplatePlugin.baseItems.get(item_off).shiftRightClickBlockAction(player, event, block, item_off);
                                break;
                        }
                    } else {
                        switch (event.getAction()) {
                            case RIGHT_CLICK_AIR:
                                TemplatePlugin.baseItems.get(item_off).rightClickAirAction(player, event);
                                break;
                            case RIGHT_CLICK_BLOCK:
                                TemplatePlugin.baseItems.get(item_off).rightClickBlockAction(player, event, block, item_off);
                                break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.broadcastMessage("Interact Event Failed");
        }
    }

    @EventHandler
    public void OnBreakBlockEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack item_main = API.getItemInMainHand(player);
        if (TemplatePlugin.itemList.contains(item_main)) {
            TemplatePlugin.baseItems.get(item_main).blockBreakAction(player, event, block, item_main);
        }
    }

    @EventHandler
    public void OnEntityHitEntityEvent(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            Entity target = event.getEntity();
            ItemStack item_main = API.getItemInMainHand(player);
            if (TemplatePlugin.itemList.contains(item_main)) {
                TemplatePlugin.baseItems.get(item_main).hitEntityAction(player, target, event, item_main);
                // TODO sword damage calculation
            }
        } else if (event.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getDamager();
            Entity target = event.getEntity();
            if (arrow.getShooter() instanceof Player) {
                Player player = (Player) arrow.getShooter();
                ItemStack bow = (ItemStack) arrow.getMetadata("Bow").get(0).value(); bow.setAmount(1);
                if (TemplatePlugin.itemList.contains(bow)) {
                    TemplatePlugin.baseItems.get(bow).hitEntityAction(player, target, event, bow);
                    // TODO bow damage calculation
                }
            }
        }
    }

    @EventHandler
    public void OnEntityShootBowEvent(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack bow = event.getBow();
            if (TemplatePlugin.itemList.contains(bow)) {
                if (player.isSneaking()) {
                    TemplatePlugin.baseItems.get(bow).shiftShootBowAction(player, event, bow);
                } else {
                    TemplatePlugin.baseItems.get(bow).shootBowAction(player, event, bow);
                }
                if (event.getProjectile() instanceof Arrow) {
                    Arrow arrow = (Arrow) event.getProjectile();
                    arrow.setMetadata("Bow", new FixedMetadataValue(plugin, bow));

                }
            }
        }
    }
}
