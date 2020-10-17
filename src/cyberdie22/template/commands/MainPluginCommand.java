package cyberdie22.template.commands;

import cyberdie22.multiversion.API;
import cyberdie22.template.TemplatePlugin;
import cyberdie22.template.helpers.MenuUtils;
import cyberdie22.template.helpers.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class MainPluginCommand implements CommandExecutor{

    private TemplatePlugin main = null;
    public MainPluginCommand(TemplatePlugin main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // verify that the user has proper permissions
        if (!sender.hasPermission("template.user")) {
            Utilities.warnPlayer(sender, Arrays.asList(main.getPhrase("no-permissions-message")));
            return true;
        }

        try {

            switch (args[0].toLowerCase()) {
                case "help":
                    help(sender);
                    break;
                case "info":
                    info(sender);
                    break;
                case "tutorial":
                    if (sender instanceof Player) MenuUtils.tutorialMenu((Player) sender);
                    else Utilities.warnPlayer(sender, Arrays.asList(main.getPhrase("no-console-message")));
                    break;

                // put plugin specific commands here

                case "reload":
                    if (sender.hasPermission("template.admin")) reload(sender);
                    else Utilities.warnPlayer(sender, Arrays.asList(main.getPhrase("no-permissions-message")));
                    break;
                case "give":
                    if (sender.hasPermission("template.admin") && sender instanceof Player) {
                        if (TemplatePlugin.itemNames.contains(args[1])) {
                            ((Player) sender).getInventory().addItem(TemplatePlugin.items.get(args[1]).getItem());
                        }
                    }
                    break;
                case "modify":
                    if (sender.hasPermission("template.admin") && sender instanceof Player) {
                        switch (args[1].toLowerCase()) {
                            case "hpb":
                                ItemStack playerItem = API.getItemInMainHand((Player) sender); playerItem.clone().setAmount(0);
                                if (TemplatePlugin.itemList.contains(API.getItemInMainHand((Player) sender))) {
                                    //TemplatePlugin.baseItems.get(playerItem).addHPB();
                                }
                                break;
                        }
                    }
                    break;
                default:
                    Utilities.warnPlayer(sender, Arrays.asList(main.getPhrase("not-a-command-message")));
                    help(sender);
                    break;
            }

        } catch(Exception e) {
            Utilities.warnPlayer(sender, Arrays.asList(main.getPhrase("formatting-error-message")));
        }

        return true;
    }

    private void info(CommandSender sender) {
        sender.sendMessage(TemplatePlugin.prefix + ChatColor.GRAY + "Plugin Info");
        sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GREEN + "Version " + main.getVersion() + " - By CyberDie22");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GREEN + "~The best plugin template ever!");
        sender.sendMessage(ChatColor.DARK_PURPLE + "------------------------------");
    }

    private void help(CommandSender sender) {
        sender.sendMessage(TemplatePlugin.prefix + ChatColor.GRAY + "Commands");
        sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GRAY + "/template help");
        sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GRAY + "/template info");
        sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GRAY + "/template tutorial");
        sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GRAY + "/template reload");
        sender.sendMessage(ChatColor.DARK_PURPLE + "------------------------------");
    }

    private void reload(CommandSender sender) {
        main.reloadConfig();
        main.loadConfiguration();

        main.loadLangFile();

        Utilities.informPlayer(sender, Arrays.asList("Configuration, values, and language settings reloaded"));
    }

}
