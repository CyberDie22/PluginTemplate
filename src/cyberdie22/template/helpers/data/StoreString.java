package cyberdie22.template.helpers.data;

import cyberdie22.template.TemplatePlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class StoreString {
    private static final TemplatePlugin plugin = TemplatePlugin.getPlugin(TemplatePlugin.class);

    public static void storeInItem(ItemStack item, String name, String storedStr) {
        NamespacedKey key = new NamespacedKey(plugin, name);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, storedStr);
        item.setItemMeta(meta);
    }

    public static String getFromItem(ItemStack item, String name) {
        NamespacedKey key = new NamespacedKey(plugin, name);
        ItemMeta meta = item.getItemMeta();
        return meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
    }

    public static void storeInEntity(Entity entity, String name, String storedStr) {
        NamespacedKey key = new NamespacedKey(plugin, name);
        entity.getPersistentDataContainer().set(key, PersistentDataType.STRING, storedStr);
    }

    public static String getFromEntity(Entity entity, String name) {
        NamespacedKey key = new NamespacedKey(plugin, name);
        return entity.getPersistentDataContainer().get(key, PersistentDataType.STRING);
    }
}
