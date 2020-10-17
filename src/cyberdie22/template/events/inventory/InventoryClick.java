package cyberdie22.template.events.inventory;

import cyberdie22.template.TemplatePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    private TemplatePlugin main = null;
    public InventoryClick(TemplatePlugin main) {
        this.main = main;
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
    }

}