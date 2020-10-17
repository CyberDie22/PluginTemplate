package cyberdie22.template;

import cyberdie22.multiversion.ActionBar;
import cyberdie22.multiversion.XMaterial;
import cyberdie22.template.commands.MainPluginCommand;
import cyberdie22.template.events.block.BlockClick;
import cyberdie22.template.events.chat.TabComplete;
import cyberdie22.template.events.inventory.InventoryClick;
import cyberdie22.template.events.item.ItemExecutor;
import cyberdie22.template.helpers.Utilities;
import cyberdie22.template.items.BaseItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

public class TemplatePlugin extends JavaPlugin {

    // items
    public static Map<String, BaseItem> items = new HashMap<>();
    public static Map<Integer, String> itemIDs = new HashMap<>();
    public static Map<ItemStack, BaseItem> baseItems = new HashMap<>();
    public static List<String> itemNames = new ArrayList<>();
    public static List<ItemStack> itemList = new ArrayList<>();

    // console and IO
    private File langFile;
    private FileConfiguration langFileConfig;
    private File playerDataFile;
    private FileConfiguration playerDataFileConfig;

    // chat messages
    private Map<String, String> phrases = new HashMap<String, String>();

    // core settings
    public static String prefix = "&c&l[&5&lTemplatePlugin&c&l] &8&l"; // generally unchanged unless otherwise stated in config
    public static String consolePrefix = "[TemplatePlugin] ";

    // is bukkit or spigot
    private static final Boolean isSpigot() {
        if (Bukkit.getVersion().contains("Spigot")) {
            return true;
        } else {
            return false;
        }
    }
    public static final Boolean isSpigot = isSpigot();

    // customizable settings
    public static boolean enablePlayerStats = false;

    public void onEnable(){
        for (Player player : Bukkit.getOnlinePlayers()) {
            ActionBar.sendPlayerActionbar(player, "Server started");
        }
        Bukkit.getConsoleSender().sendMessage(Bukkit.getVersion());
        // load config.yml (generate one if not there)
        loadConfiguration();

        // load language.yml (generate one if not there)
        loadLangFile();

        // load player_data.yml (generate one if not there)
        loadPlayerDataFile();

        // register commands and events
        registerCommands();
        registerEvents();

        // register items
        Bukkit.getConsoleSender().sendMessage("hi 1");
        registerItems();

        // posts confirmation in chat
        getLogger().info(getDescription().getName() + " V: " + getDescription().getVersion() + " has been enabled");

        // example scheduled task
        //if (autoPurge){
        //    Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
        //        public void run() {
        //            if (debug) Bukkit.getLogger().info("Automatically Purged " + Utilities.purge(shops, consolePrefix, debug, purgeAge) + " empty shops that haven't been active in the past " + purgeAge + " hour(s).");
        //            if (!debug) Utilities.purge(shops, consolePrefix, debug, purgeAge);
        //        }
        //    }, 20 * 60 * 60, 20 * 60 * 60);
        //}

        for (Player player : Bukkit.getOnlinePlayers()) {
            ItemStack item = XMaterial.DIAMOND.parseItem();
            Utilities.loreItem(item, Arrays.asList(ChatColor.RED + "Test item"));
            Utilities.nameItem(item, "Test item");

            player.getInventory().addItem(item);
        }
    }

    public void onDisable(){
        // posts exit message in chat
        getLogger().info(getDescription().getName() + " V: " + getDescription().getVersion() + " has been disabled");
    }

    private void registerCommands() {
        getCommand("template").setExecutor(new MainPluginCommand(this));

        // set up tab completion
        getCommand("template").setTabCompleter(new TabComplete(this));
    }
    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new BlockClick(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(this), this);
        getServer().getPluginManager().registerEvents(new ItemExecutor(), this);
    }

    private void registerItems() {
        putItem("TEST_ITEM", 0, new TemplateItem("Test Item", XMaterial.BOW.parseMaterial(), Rarity.UNFINISHED, ItemType.BOW, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, true, false, Arrays.asList(new Ability("Test ability", AbilityType.LEFT_CLICK, ChatColor.GOLD + "This is a\ntest of the " + ChatColor.MAGIC + "ability" + ChatColor.RESET + "" + ChatColor.GREEN + " system!"), new Ability("Suffix Ability", AbilityType.SUFFIX, "Test\nabiliy\nsuffix"), new Ability("Prefix Ability", AbilityType.PREFIX, "Test\nabiliy\nprefix"))));
        putItem("TEST_ITEM_2", 1, new TemplateItem("Techopotato", XMaterial.POTATO.parseMaterial(), Rarity.UNFINISHED, ItemType.ITEM, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,  true, true, Arrays.asList()));
        putItem("TEST_ITEM_3", 0, new TemplateItem("Test Item", XMaterial.DIAMOND_BLOCK.parseMaterial(), Rarity.UNFINISHED, ItemType.BOW, 5, 0, 0, 0, 0,0,0,0,0,0, false, false, Arrays.asList()));

    }

    private void putItem(String name, Integer id, BaseItem item) {
        items.put(name, item);
        itemIDs.put(id, name);
        baseItems.put(item.getItem(), item);
        itemNames.add(name);
        itemList.add(item.getItem());
        Bukkit.getConsoleSender().sendMessage(itemNames.toArray().toString());
    }

    // load the config file and apply settings
    public void loadConfiguration() {
        // prepare config.yml (generate one if not there)
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()){
            Utilities.loadResource(this, "config.yml");
        }
        FileConfiguration config = this.getConfig();

        // general settings
        prefix = ChatColor.translateAlternateColorCodes('&', config.getString("plugin-prefix"));

        enablePlayerStats = config.getBoolean("enable-player-stats");
        // put more settings here

        Bukkit.getLogger().info(consolePrefix + "Settings Reloaded from config");
    }

    // load the language file and apply settings
    public void loadLangFile() {

        // load language.yml (generate one if not there)
        langFile = new File(getDataFolder(), "language.yml");
        langFileConfig = new YamlConfiguration();
        if (!langFile.exists()){ Utilities.loadResource(this, "language.yml"); }

        try { langFileConfig.load(langFile); }
        catch (Exception e3) { e3.printStackTrace(); }

        for(String priceString : langFileConfig.getKeys(false)) {
            phrases.put(priceString, langFileConfig.getString(priceString));
        }
    }

    // load the player data file
    public void loadPlayerDataFile() {

        // load language.yml (generate one if not there)
        playerDataFile = new File(getDataFolder(), "player_data.yml");
        playerDataFileConfig = new YamlConfiguration();
        if (!playerDataFile.exists()){ Utilities.loadResource(this, "player_data.yml"); }

        try { playerDataFileConfig.load(playerDataFile); }
        catch (Exception e4) { e4.printStackTrace(); }

        for(String priceString : playerDataFileConfig.getKeys(false)) {
            phrases.put(priceString, playerDataFileConfig.getString(priceString));
        }
    }

    // getters
    public String getPhrase(String key) {
        return phrases.get(key);
    }
    public String getVersion() {
        return getDescription().getVersion();
    }

}
