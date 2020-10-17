package cyberdie22.template;

import org.bukkit.ChatColor;

public enum Rarity {
    COMMON(ChatColor.WHITE + ""),
    UNCOMMON(ChatColor.GREEN + ""),
    RARE(ChatColor.BLUE + ""),
    EPIC(ChatColor.DARK_PURPLE + ""),
    LEGENDARY(ChatColor.GOLD + ""),
    MYTHIC(ChatColor.LIGHT_PURPLE + ""),
    SUPREME(ChatColor.DARK_RED + ""),
    SPECIAL(ChatColor.RED + ""),
    VERY_SPECIAL(ChatColor.RED + ""),
    UNFINISHED(ChatColor.DARK_RED + "")
    ;

    private String color;

    Rarity(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
