package cyberdie22.template.events.block;

import cyberdie22.template.TemplatePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockClick implements Listener {

    private TemplatePlugin main = null;
    public BlockClick(TemplatePlugin main) {
        this.main = main;
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {

    }

}