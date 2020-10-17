package cyberdie22.template.events.chat;

import cyberdie22.template.TemplatePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("ALL")
public class TabComplete implements TabCompleter {

    private TemplatePlugin main = null;
    public TabComplete(TemplatePlugin main) { this.main = main; }

    @EventHandler
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        // verify sender is a player
        if (!(sender instanceof Player)) return null;
        Player player = (Player) sender;

        ArrayList<String> arguments = new ArrayList<>();

        // tab completion for /exchange command
        if (command.getName().equals("template")) {

            // no arguments
            if (args.length == 1){
                if (player.hasPermission("template.user")) { arguments.addAll(Arrays.asList("help", "info", "tutorial")); }
                if (player.hasPermission("template.admin")) { arguments.addAll(Arrays.asList("reload", "give", "modify")); }

                Iterator<String> iter = arguments.iterator(); while (iter.hasNext()) { String str = iter.next().toLowerCase(); if (!str.contains(args[0].toLowerCase())) iter.remove(); }
            }

            if (args.length == 2) {
                if (player.hasPermission("template.admin")) {
                    if (args[0].equals("give")) {
                        arguments.addAll(TemplatePlugin.itemNames);
                    } else if (args[0].equals("modify")) {
                        arguments.addAll(Arrays.asList("hpb"));
                    }
                }
            }
        return arguments;
        }
        Bukkit.broadcastMessage("null args");
    return null;
    }
}
