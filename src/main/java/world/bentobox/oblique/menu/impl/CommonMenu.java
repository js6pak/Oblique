package world.bentobox.oblique.menu.impl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import world.bentobox.oblique.menu.Menu;
import world.bentobox.oblique.menu.MenuAction;
import world.bentobox.oblique.menu.RowType;
import world.bentobox.oblique.provider.Provider;

import java.util.*;
import java.util.stream.IntStream;

@SuppressWarnings("WeakerAccess")
public abstract class CommonMenu implements Menu {

    private Map<UUID, Inventory> referenceMap;
    private String title;
    private byte rows;

    public CommonMenu(String title, int rows) {
        this.referenceMap = Maps.newHashMap();
        this.title = title;
        this.rows = (byte) rows;
    }

    public byte getRows()
    {
        return rows;
    }

    public int getSize()
    {
        return rows*9-1;
    }

    @Override
    public Provider<Player, Table<Integer, ItemStack, MenuAction>> itemProvider() {
        return player -> HashBasedTable.create();
    }

    protected ItemStack createItemStack(String name, Material material, int amount, List<String> lore, boolean glow) {
        ItemStack i = new ItemStack(material, amount);
        ItemMeta iMeta = i.getItemMeta();
        iMeta.setDisplayName(name);
        if (lore != null) iMeta.setLore(lore);
        if (glow) iMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        iMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_PLACED_ON);
        i.setItemMeta(iMeta);
        return i;
    }

    protected void fill(Table<Integer, ItemStack, MenuAction> table, ItemStack itemStack, RowType... rowTypes) {
        Arrays.stream(rowTypes).forEach(rowType -> {
            /* Im not good w/ maths so.. this is ugly */
            switch (rowType) {
                case TOP: {
                    IntStream.range(0, 9).forEach(i -> table.put(i, itemStack, clickType -> true));
                }

                case BOTTOM: {
                    IntStream.range(rows * 9 - 9, rows * 9).forEach(i -> table.put(i, itemStack, clickType -> true));
                    break;
                }

                case RIGHT: {
                    for (int i = 8; i < rows * 9; i += 9) {
                        table.put(i, itemStack, clickType -> true);
                    }
                    break;
                }

                case LEFT: {
                    for (int i = 0; i < rows * 9; i += 9) {
                        table.put(i, itemStack, clickType -> true);
                    }
                    break;
                }

                case AROUND: {
                    IntStream.range(0, 9).forEach(i -> table.put(i, itemStack, clickType -> true));
                    IntStream.range(rows * 9 - 9, rows * 9).forEach(i -> table.put(i, itemStack, clickType -> true));
                    for (int i = 8; i < rows * 9; i += 9) {
                        table.put(i, itemStack, clickType -> true);
                    }
                    for (int i = 0; i < rows * 9; i += 9) {
                        table.put(i, itemStack, clickType -> true);
                    }
                    break;
                }
            }
        });

    }

    protected Inventory createInventory() {
        return Bukkit.createInventory(null, ((int) rows) * 9, title);
    }

    protected Map<UUID, Inventory> getReferenceMap() {
        return referenceMap;
    }
}
