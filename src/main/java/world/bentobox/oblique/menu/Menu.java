package world.bentobox.oblique.menu;

import com.google.common.collect.Table;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import world.bentobox.oblique.provider.Provider;

/**
 * Simple Menu interface
 */
public interface Menu {

    /**
     * The {@link Provider} for this Menu.
     *
     * @return table
     */
    Provider<Player, Table<Integer, ItemStack, MenuAction>> itemProvider();

}
