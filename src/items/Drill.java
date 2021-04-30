package items;

import net.minecraft.server.v1_16_R1.CommandSetBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import thirtyvirus.uber.UberItem;
import thirtyvirus.uber.UberItems;
import thirtyvirus.uber.helpers.*;

import java.util.List;

public class Drill extends UberItem {

    public Drill(Material material, String name, UberRarity rarity, boolean stackable, boolean oneTimeUse, boolean hasActiveEffect, List<UberAbility> abilities, UberCraftingRecipe craftingRecipe) {
        super(material, name, rarity, stackable, oneTimeUse, hasActiveEffect, abilities, craftingRecipe);
    }
    public void onItemStackCreate(ItemStack item) { }
    public void getSpecificLorePrefix(List<String> lore, ItemStack item) { }
    public void getSpecificLoreSuffix(List<String> lore, ItemStack item) { }

    public boolean leftClickAirAction(Player player, ItemStack item) {
        if (Utilities.getIntFromItem(item, "ability1") == 1) {
        Block block = Utilities.getBlockLookingAt(player);
        if (block.getType().isSolid()) {
            for (int x = -1; x <= 1; x++) {
                Location block2 = block.getLocation().subtract(x, 0, 0);
                block2.getBlock().breakNaturally();
                for (int y = -1; y <= 1; y++) {
                    block2 = block.getLocation().subtract(x, y, 0);
                    block2.getBlock().breakNaturally();
                    for (int z = -1; z <= 1; z++) {
                        block2 = block.getLocation().subtract(x, y, z);
                        block2.getBlock().breakNaturally();
                    }
                }
            }
        }
            return true;
        } else {
            Block block = Utilities.getBlockLookingAt(player);
            if (block.getType().isSolid()) {
                block.setType(Material.BAMBOO);
            }
        }
        return false;
    }


    public boolean leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        if (Utilities.getIntFromItem(item, "ability1") == 1) {
            if (block.getType().isSolid()) {
                for (int x = -1; x <= 1; x++) {
                    Location block2 = block.getLocation().subtract(x, 0, 0);
                    block2.getBlock().breakNaturally();
                    for (int y = -1; y <= 1; y++) {
                        block2 = block.getLocation().subtract(x, y, 0);
                        block2.getBlock().breakNaturally();
                        for (int z = -1; z <= 1; z++) {
                            block2 = block.getLocation().subtract(x, y, z);
                            block2.getBlock().breakNaturally();
                        }
                    }
                }
            }
            return true;
        } else {
            if (block.getType().isSolid()) {
                Location location = block.getLocation();
                block.setType(Material.AIR);
                player.getWorld().spawnEntity(location.subtract(-0.5, 0, -0.5), EntityType.BEE);
            }
        }
        return false; }
    public boolean rightClickAirAction(Player player, ItemStack item) { return false; }
    public boolean rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }
    public boolean shiftLeftClickAirAction(Player player, ItemStack item) { return false; }
    public boolean shiftLeftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }
    public boolean shiftRightClickAirAction(Player player, ItemStack item) {
        if (Utilities.getIntFromItem(item, "ability1") == 0){
            Utilities.storeIntInItem(item, 1, "ability1");
            Utilities.addEnchantGlint(item);
            Utilities.playSound(ActionSound.POP, player);
        } else {
            Utilities.storeIntInItem(item, 0, "ability1");
            ItemMeta meta = item.getItemMeta();
            for(Enchantment e: meta.getEnchants().keySet())
                meta.removeEnchant(e);
            item.setItemMeta(meta);
            Utilities.playSound(ActionSound.CLICK, player);
        }
        return false; }
    public boolean shiftRightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }

    public boolean middleClickAction(Player player, ItemStack item) { return false; }
    public boolean hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity target, ItemStack item) { return false; }
    public boolean breakBlockAction(Player player, BlockBreakEvent event, Block block, ItemStack item) { return false; }
    public boolean clickedInInventoryAction(Player player, InventoryClickEvent event, ItemStack item, ItemStack addition) { return false; }
    public boolean activeEffect(Player player, ItemStack item) {


        return false; }
}
