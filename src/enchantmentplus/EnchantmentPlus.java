package enchantmentplus;
// TODO behandlung bei zwei büchern
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
// TODO de-enchanten (level zurück)
public class EnchantmentPlus extends JavaPlugin {
    private static final Logger log = Logger.getLogger("Minecraft");
    private EPListener listener;
    
    @Override
    public void onEnable() {       
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        listener = new EPListener(this);
        getServer().getPluginManager().registerEvents(listener, this);
        
        log.info("EnchantmentPlus started");
    }
    
    @Override
    public void onDisable(){
        log.info("EnchantmentPlus stopped");
    }
}
