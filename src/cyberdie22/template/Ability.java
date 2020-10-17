package cyberdie22.template;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ability {

    private String name;
    private AbilityType type;
    private String description;
    private List<String> lore = new ArrayList<>();
    private List<String> firstLore = new ArrayList<>();
    private List<String> lastLore = new ArrayList<>();

    public Ability(String name, AbilityType type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
        loreGenerator();
    }

    public List<String> getLore() {
        return this.lore;
    }
    public List<String> getFirstLore() { return this.firstLore; }
    public List<String> getLastLore() { return this.lastLore; }

    private void loreGenerator() {
        switch (this.type) {
            case LEFT_CLICK:
                this.lore.add(ChatColor.GOLD + "Item Ability: " + this.name + ChatColor.YELLOW + " " + ChatColor.BOLD + "LEFT CLICK");
                this.lore.addAll(Arrays.asList(this.description.split("\n")));
                this.lore.add(" ");
                break;
            case RIGHT_CLICK:
                this.lore.add(ChatColor.GOLD + "Item Ability: " + this.name + ChatColor.YELLOW + " " + ChatColor.BOLD + "RIGHT CLICK");
                this.lore.addAll(Arrays.asList(this.description.split("\n")));
                this.lore.add(" ");
                break;
            case FULL_SET:
                this.lore.add(ChatColor.GOLD + "Full Set Bonus: " + this.name);
                this.lore.addAll(Arrays.asList(this.description.split("\n")));
                this.lore.add(" ");
                break;
            case EXTRA:
                this.lore.add(ChatColor.GOLD + "Extra Bonus: " + this.name);
                this.lore.addAll(Arrays.asList(this.description.split("\n")));
                this.lore.add(" ");
                break;
            case PREFIX:
                this.firstLore.addAll(Arrays.asList(this.description.split("\n")));
                this.firstLore.add(" ");
                break;
            case SUFFIX:
                this.lastLore.addAll(Arrays.asList(this.description.split("\n")));
                this.lastLore.add(" ");
                break;
        }
    }
}
