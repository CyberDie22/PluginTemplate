package cyberdie22.template.items;

import cyberdie22.template.Ability;
import cyberdie22.template.ItemType;
import cyberdie22.template.Rarity;
import cyberdie22.template.helpers.Utilities;
import cyberdie22.template.helpers.data.StoreInt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseItem {

    private final String name;
    private final Material material;
    private final ItemStack item;
    private final Rarity rarity;
    private final ItemType type;
    private final Integer damage;
    private final Integer strength;
    private final Integer critChance;
    private final Integer critDamage;
    private final Integer bonusAttackSpeed;
    private final Integer health;
    private final Integer defence;
    private final Integer speed;
    private final Integer intelligence;
    private final Integer trueDefence;
    private final Boolean reforgable;
    private final Boolean oneTimeUse;
    private final List<Ability> abilities;
    private final List<List<String>> abilityLore = new ArrayList<>();
    private final List<String> lore = new ArrayList<>();

    public BaseItem(String name, Material material, Rarity rarity, ItemType type, Integer damage, Integer strength, Integer critChance, Integer critDamage, Integer bonusAttackSpeed,
                    Integer health, Integer defence, Integer speed, Integer intelligence, Integer trueDefence, Boolean reforgable, Boolean oneTimeUse,
                    List<Ability> abilities) {
        this.name = name;
        this.material = material;
        this.item = new ItemStack(this.material);
        this.rarity = rarity;
        this.type = type;
        this.damage = damage;
        this.strength = strength;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.bonusAttackSpeed = bonusAttackSpeed;
        this.health = health;
        this.defence = defence;
        this.speed = speed;
        this.intelligence = intelligence;
        this.trueDefence = trueDefence;
        this.reforgable = reforgable;
        this.oneTimeUse = oneTimeUse;
        this.abilities = abilities;
        createLore();
        Utilities.nameItem(this.item, this.rarity.getColor() + this.name);

        StoreInt.storeInItem(this.item, "DAMAGE", this.damage);
        StoreInt.storeInItem(this.item, "STRENGTH", this.strength);
        StoreInt.storeInItem(this.item, "CRIT_CHANCE", this.critChance);
        StoreInt.storeInItem(this.item, "CRIT_DAMAGE", this.critDamage);
        StoreInt.storeInItem(this.item, "BONUS_ATTACK_SPEED", this.bonusAttackSpeed);
        StoreInt.storeInItem(this.item, "HEALTH", this.health);
        StoreInt.storeInItem(this.item, "DEFENCE", this.defence);
        StoreInt.storeInItem(this.item, "SPEED", this.speed);
        StoreInt.storeInItem(this.item, "INTELLIGENCE", this.intelligence);
        StoreInt.storeInItem(this.item, "TRUE_DEFENCE", this.trueDefence);
    }

    public Boolean isOneTimeUse() {
        return this.oneTimeUse;
    }

    private void createLore() {
        Bukkit.broadcastMessage("lore creation started");
        this.lore.clear();
        // top lore
        if (this.rarity.equals(Rarity.UNFINISHED)) { this.lore.add(ChatColor.DARK_RED + "This item is not finished!"); this.lore.add(ChatColor.DARK_RED + "This item may not work as expected!"); this.lore.add(" "); }

        // mid top lore
        if (!this.damage.equals(0)) { this.lore.add(ChatColor.GRAY + "Damage: +" + ChatColor.RED + this.damage.toString()); }
        if (!this.strength.equals(0)) { this.lore.add(ChatColor.GRAY + "Strength: +" + ChatColor.RED + this.strength.toString()); }
        if (!this.critChance.equals(0)) { this.lore.add(ChatColor.GRAY + "Crit Chance: +" + ChatColor.RED + this.critChance.toString() + "%"); }
        if (!this.critDamage.equals(0)) { this.lore.add(ChatColor.GRAY + "Crit Damage: +" + ChatColor.RED + this.critDamage.toString()); }
        if (!this.bonusAttackSpeed.equals(0)) { this.lore.add(ChatColor.GRAY + "Bonus Attack Speed: +" + ChatColor.RED + this.bonusAttackSpeed.toString() + "%"); this.lore.add(" "); }
        if (!this.health.equals(0)) { this.lore.add(ChatColor.GRAY + "Health: +" + ChatColor.GREEN + this.health.toString()); }
        if (!this.defence.equals(0)) { this.lore.add(ChatColor.GRAY + "Defence: +" + ChatColor.GREEN + this.defence.toString()); }
        if (!this.speed.equals(0)) { this.lore.add(ChatColor.GRAY + "Speed: +" + ChatColor.GREEN + this.speed.toString()); }
        if (!this.intelligence.equals(0)) { this.lore.add(ChatColor.GRAY + "Intelligence: +" + ChatColor.GREEN + this.intelligence.toString()); }
        if (!this.trueDefence.equals(0)) { this.lore.add(ChatColor.GRAY + "True Defence: +" + ChatColor.GREEN + this.trueDefence.toString()); }
        lore.add(" ");

        // ability lore
        for (Ability i : this.abilities) {
            this.abilityLore.add(i.getFirstLore());
        }
        for (Ability i : this.abilities) {
            this.abilityLore.add(i.getLore());
        }
        for (Ability i : this.abilities) {
            this.abilityLore.add(i.getLastLore());
        }
        for (List<String> i : this.abilityLore) {
            for (String ii : i) {
                this.lore.add(ChatColor.GRAY + ii);
            }
        }

        // bottom lore
        if (this.reforgable) this.lore.add(ChatColor.DARK_GRAY + "This item is reforgable!");
        this.lore.add(this.rarity.getColor() + ChatColor.BOLD + this.rarity.toString());

        // apply lore
        Utilities.loreItem(this.item, this.lore);
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void leftClickAirAction(Player player, PlayerInteractEvent event) { }
    public void leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { }
    public void rightClickAirAction(Player player, PlayerInteractEvent event) { }
    public void rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { }
    public void shiftLeftClickAirAction(Player player, PlayerInteractEvent event) { }
    public void shiftLeftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { }
    public void shiftRightClickAirAction(Player player, PlayerInteractEvent event) { }
    public void shiftRightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { }
    public void blockBreakAction(Player player, BlockBreakEvent event, Block block, ItemStack item) { }
    public void hitEntityAction(Player player, Entity target, EntityDamageByEntityEvent event, ItemStack item) { }
    public void shootBowAction(Player player, EntityShootBowEvent event, ItemStack item) { }
    public void shiftShootBowAction(Player player, EntityShootBowEvent event, ItemStack item) { }

}
