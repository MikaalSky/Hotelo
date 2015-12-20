package io.lokiraut.Hotelo;

import com.darkblade12.particleeffect.ParticleEffect;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.block.CraftBlock;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.BlockProjectileSource;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Lokio on 12/18/2015.
 */
public class Hotelo extends JavaPlugin implements Listener {

    Logger logger = null;

    @Override
    public void onEnable() {
        logger = getLogger();
        logger.info("Plugin has been enabled!");
        Bukkit.getPluginManager().registerEvents(this, this);
        //enable logic
    }

    @Override
    public void onDisable() {
        logger.info("Plugin has been disabled!");
        //enable logic
    }

    @EventHandler
    public void onBlockTouch(PlayerInteractEvent event){
    }
    @EventHandler
    public void onPreCommand(PlayerCommandPreprocessEvent event){
        String[] Splitter = event.getMessage().replaceFirst("/","").split(" ");
        String command = Splitter[0];
        String[] args = event.getMessage().replaceFirst("/","").replaceFirst(command + ((Splitter.length > 1)?" ":""),"").split(" ");

    }
    @EventHandler
    public void onBlockUpdate(BlockSpreadEvent event){
    }
    @EventHandler
    public void onKill(PlayerDeathEvent event){
    }
    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event){
        //keycard and stuff
        /*event.getRightClicked().setMetadata("CardScanner",new FixedMetadataValue(this,"ExampleHotel"));
        event.getRightClicked().getLocation().add(0,0,-1).getBlock().setType(Material.STONE);
        */
        Entity rc = event.getRightClicked();
        Player cause = event.getPlayer();
        if(rc.hasMetadata("CardScanner")){
            if(cause.getItemInHand().getType().equals(Material.PRISMARINE_SHARD)) {
                ItemMeta im = cause.getItemInHand().getItemMeta();
                im.setDisplayName("§R§e§L" + cause.getDisplayName() + "'s Key Card");
                List<String> lore = new ArrayList<String>();
                lore.add("§f§oKey Card for use at Example Hotel");
                lore.add("");
                lore.add("§r§cUnlocks:");
                lore.add("§r Room 321");
                lore.add("§r Front Door");
                lore.add("§r Pool Door");
                im.setLore(lore);
                cause.getItemInHand().setItemMeta(im);
                cause.sendMessage("Here's your Key Card!");
            }
        }else if(cause.getItemInHand().getType().equals(Material.STICK)){
            ItemStack item = cause.getItemInHand();
            if(item.getItemMeta().getDisplayName().equalsIgnoreCase("example hotel key card assign")){
                rc.setMetadata("CardScanner",new FixedMetadataValue(this,"Example Hotel"));
                ItemFrame iem = (ItemFrame) rc;
                iem.setItem(new ItemStack(Material.PRISMARINE_SHARD));
                cause.sendMessage("Assigned keycard scanner!!");
            }
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void onArrowSmeck(EntityDamageByEntityEvent event){

    }

    @EventHandler
    public void onArrowShoot(ProjectileLaunchEvent event){

    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Location oldLoco = event.getFrom();
        Location newLoco = event.getTo();
        if(oldLoco.getX() == newLoco.getX()&&oldLoco.getY() == newLoco.getY()&&oldLoco.getZ() == newLoco.getZ()){
            return;
        }
        ParticleEffect.FLAME.display(new Vector((Math.random() + (Math.random() * -1))/2,1,(Math.random() + (Math.random() * -1))), 0.05f, oldLoco, 10);
        //newLoco.getWorld().
        //ParticleEffect.SMOKE_LARGE.display(new Vector(0,1,0), 0.1f, oldLoco, 10);
    }


}
