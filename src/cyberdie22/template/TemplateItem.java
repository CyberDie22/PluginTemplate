package cyberdie22.template;

import cyberdie22.template.calculations.BaseDamage;
import cyberdie22.template.helpers.data.StoreInt;
import cyberdie22.template.items.BaseItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TemplateItem extends BaseItem {

    public TemplateItem(String name, Material material, Rarity rarity, ItemType type, Integer damage, Integer strength, Integer critChance, Integer critDamage, Integer bonusAttackSpeed, Integer health, Integer defence, Integer speed, Integer intelligence, Integer trueDefence, Boolean reforgable, Boolean oneTimeUse, List<Ability> abilities) {
        super(name, material, rarity, type, damage, strength, critChance, critDamage, bonusAttackSpeed, health, defence, speed, intelligence, trueDefence, reforgable, oneTimeUse, abilities);
    }

    @Override
    public void leftClickAirAction(Player player, PlayerInteractEvent event) { player.sendMessage("Left Click Air"); }
    @Override
    public void leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { player.sendMessage("Left Click Block"); }
    @Override
    public void rightClickAirAction(Player player, PlayerInteractEvent event) { player.sendMessage("Right Click Air"); }
    @Override
    public void rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { player.sendMessage("Right Click Block"); }
    @Override
    public void shiftLeftClickAirAction(Player player, PlayerInteractEvent event) { player.sendMessage("Shift Left Click Air"); }
    @Override
    public void shiftLeftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { player.sendMessage("Shift Left Click Block"); }
    @Override
    public void shiftRightClickAirAction(Player player, PlayerInteractEvent event) { player.sendMessage("Shift Right Click Air"); }
    @Override
    public void shiftRightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { player.sendMessage("Shift Right Click Block"); }
    @Override
    public void blockBreakAction(Player player, BlockBreakEvent event, Block block, ItemStack item) { player.sendMessage("Block Broken"); }
    @Override
    public void hitEntityAction(Player player, Entity target, EntityDamageByEntityEvent event, ItemStack item) { player.sendMessage(String.valueOf(BaseDamage.calculateDamage(StoreInt.getFromItem(item, "DAMAGE"), StoreInt.getFromItem(item, "STRENGTH")))); }
    @Override
    public void shootBowAction(Player player, EntityShootBowEvent event, ItemStack item) { player.sendMessage("Shot bow"); }
    @Override
    public void shiftShootBowAction(Player player, EntityShootBowEvent event, ItemStack item) { player.sendMessage("Shot bow while shifting"); }
}
