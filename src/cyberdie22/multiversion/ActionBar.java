package cyberdie22.multiversion;

import cyberdie22.template.TemplatePlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBar {

    public static void sendPlayerActionbar(Player player, String text) {
        if (TemplatePlugin.isSpigot) {
            if (Version.getVersion().isBiggerThan(Version.v1_8)) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(text));
            } else  {
                player.sendMessage(text);
                Bukkit.getLogger().info(TemplatePlugin.consolePrefix + "Action Bar usage is not avaliable for use in 1.8.8 and below (Your server is running " + Bukkit.getBukkitVersion() + ") in this version of this plugin, the player will be sent a message with the text instead.");
                Bukkit.getLogger().info(TemplatePlugin.consolePrefix + "You can send an Action Bar using packets, but it doesn't appear to work. Haven't tested with faking an item name for the text.");
            }
        } else {
            player.sendMessage(text);
            Bukkit.getLogger().info(TemplatePlugin.consolePrefix + "Action Bar usage is not avaliable for use in CraftBukkit right now as packets do not appear to work. It may be avaliable in the future");
        }
    }

    private static String getVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }
}
