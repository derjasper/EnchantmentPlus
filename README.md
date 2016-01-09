EnchantmentPlus Bukkit Plugin
=============================

This is a Bukkit Plugin which I wrote years ago. Since it was quite popular, I decided to release the code for others to have fun with it. 

Tested with CB 1.5.2-R0.1.

License: MIT.

Original BukkitDev description
==============================

The ultimate solution for choosing enchantments!

This plugin allows players to choose the enchantments they put on their items. This is done by making enchanted books reuseable. After applying an enchantment to an item using an anvil, the book will be returned to the player. In order to keep the game balanced, the level costs will be increased. There is still a possibility to use enchanted books as in Vanilla Minecraft.

Features
--------
* books will be returned to the player after using in an anvil
* right click for vanilla handling
* multiworld support (see configuration for more details)
* level costs will be increased (configurable)
* permission (see below for details)
* no client mod needed!

Usage
-----
Just combine your item with an enchanted book in an anvil. By left clicking the result slot, the plugin will check if you have enough level to apply the enchantment. If this is the case, the enchanted book stays in the slot, otherwise, the plugin cancels the action and will display a message (Sadly, there is no possibility to change the displayed level in the gui yet). If you right-click the result slot, the action will be handled as in vanilla Minecraft.

Configuration
-------------
For installation, copy the EnchantmentPlus.jar in your plugin folder. The configuration file will be created automatically on first run.

**Then, add one or more worlds to the config.yml (see next section for more details) and don't forget to give your players the permission to use this plugin!**

confg.yml
---------
* `worlds`: list of worlds in which the plugin should be active
* `cost.levelfactor`: default level cost will be multiplied with this factor (default: 1.75)
* `string.notenoughlevel`: message which is displayed when the player's experience level is too low; variables: `%level%` level required for enchantment, `%factor%` factor defined in configuration

Permissions
-----------
These permissions are available:
* `EnchantmentPlus.use`: Allow to use the plugin. Otherwise, vanilla handling will be used.

Bugs & Planned features
-----------------------
* disenchanting (with own permission)

These bugs cannot be fixed until Bukkit's API has better support for anvils:
* anvil receives damage after an unsuccessful enchantment attempt
* if the anvil breaks, the enchanted book gets lost
* show the new level cost in the gui

