# Oblique
![Oblique-Logo](https://i.gyazo.com/938b9d62070e1c7353e1652d3b126ce4.png)

Introduction
---
> Oblique is a fast and reliable menu API.

As a lightweight api, it should support almost all versions above 1.7.x on [Spigot](https://hub.spigotmc.org/stash/projects/SPIGOT/repos/spigot/browse).
If you find any bugs, feel free to open an [Issue](https://github.com/cleodevelopment/oblique/issues) or a [Pull Request](https://github.com/cleodevelopment/oblique/pulls)

Contributing
---

If you do make a contribution, please try to be sure to comment/document all of the changes and/or additions you made to the project.
* If you want to join the [cleodevelopment](https://github.com/cleodevelopment) team, you can [send](mailto://jesper@cleo.rip) us an email with your experience and necessary information.

Build
---
#### Requirements
* Java (JDK) 8 or above
* Maven

#### Dependencies
* Spigot API

#### Compile
```sh
mvn clean install
```
Usage
---
#### Examples
* See [ExamplePlugin](https://github.com/cleodevelopment/oblique/blob/master/src/main/java/rip/cleo/oblique/example/ExamplePlugin.java)
```java
    @Override
    public void onEnable() {
        //Get the oblique instance
        Oblique oblique = Oblique.getInstance(this);
    }
```
```java
public class ExampleMenu extends CommonMenu {

    public ExampleMenu() {
        super("Example", 3);
        //name, rows
    }

    @Override
    public Provider<Player, Table<Integer, ItemStack, MenuAction>> itemProvider() {
        return player -> {
            Table<Integer, ItemStack, MenuAction> toReturn = HashBasedTable.create();

            /* Fill redstone blocks around the menu */
            fill(toReturn, new ItemStack(Material.REDSTONE_BLOCK), RowType.AROUND);

            /* Put an gold_axe in the middle of the menu */
            toReturn.put(13, new ItemStack(Material.GOLD_AXE), clickType -> {
                /* Send a message with a random chatcolor by the clickTypes enum-ordinal incremented by one */
                player.sendMessage(ChatColor.values()[clickType.ordinal() + 1] + "You clicked: " + clickType);
                player.closeInventory();
                return true;
            });

            return toReturn;
        };
    }
}
```
Contact
---
* Email - [jesper@cleo.rip](mailto://jesper@cleo.rip)
* Discord - [cleo](https://discord.gg/Q7GFejJ)
* Telegram - [crunesmh](https://t.me/crunesmh)
* Keybase - [crune](https://keybase.io/crune)
