package items;

import java.util.List;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import org.bukkit.util.Vector;
import thirtyvirus.uber.UberItem;
import thirtyvirus.uber.helpers.*;

public class Hyperion extends UberItem {

    public Hyperion(Material material, String name, UberRarity rarity, boolean stackable, boolean oneTimeUse, boolean hasActiveEffect, List<UberAbility> abilities, UberCraftingRecipe craftingRecipe) {
        super(material, name, rarity, stackable, oneTimeUse, hasActiveEffect, abilities, craftingRecipe);
    }
    public void onItemStackCreate(ItemStack item) { Utilities.addEnchantGlint(item); }
    public void getSpecificLorePrefix(List<String> lore, ItemStack item) { lore.add(ChatColor.GREEN + "Most powerful sword of all times"); }
    public void getSpecificLoreSuffix(List<String> lore, ItemStack item) { lore.add(ChatColor.RED + "+10000 damage!"); }

    // the play sound ability
    public boolean leftClickAirAction(Player player, ItemStack item) { return true; }

    // make sure that the ability works when left clicking air OR a block
    public boolean leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return leftClickAirAction(player, item); }

    // toggle the midas step ability
    public boolean rightClickAirAction(Player player, ItemStack item) {
        Location loc = player.getLocation();
        Vector dir = loc.getDirection();
        dir.normalize();
        dir.multiply(10);
        loc.add(dir);
        player.teleport(loc);
        List<Entity> enemies = player.getNearbyEntities(10, 10, 10);
        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE,1,1);
        player.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, player.getLocation().subtract(0, -1, 0), 0, -1, 2, 0);
        int enemieslength = enemies.size();
        for (int i = 0; i < enemieslength; i++)
        {
            Entity e = enemies.get(i);
            ((LivingEntity)e).damage(1000);
        }
        player.setHealth(0);

        return true;
    }

    // make sure that the ability works when right clicking air OR a block
    public boolean rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return rightClickAirAction(player, item); }

    public boolean shiftLeftClickAirAction(Player player, ItemStack item) { return false; }
    public boolean shiftLeftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }
    public boolean shiftRightClickAirAction(Player player, ItemStack item) { return false; }
    public boolean shiftRightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }
    public boolean middleClickAction(Player player, ItemStack item) { return false; }
    public boolean hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity target, ItemStack item) {
        ((org.bukkit.entity.LivingEntity)target).damage(20);
        return false; }

    public boolean breakBlockAction(Player player, BlockBreakEvent event, Block block, ItemStack item) {
        Utilities.repairItem(item);
        return false;
    }

    // enchant an item when clicked onto this item in the inventory (doesn't work in creative mode b/c MC is stupid)
    public boolean clickedInInventoryAction(Player player, InventoryClickEvent event, ItemStack item, ItemStack addition) {
        Utilities.addEnchantGlint(addition);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
        event.setCancelled(true);
        return true;
    }

    // actually do the midas step ability
    public boolean activeEffect(Player player, ItemStack item) { return false; }
}
