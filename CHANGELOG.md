# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Farmer's Delight compatibility - Cabinets for all Pyrite wood sets.
- Items hidden by the `c:hidden_from_recipe_viewers` tag will no longer show up in the creative inventory.
- Item textures for Pyrite torch levers.
- Torches can now be dyed in world, like Signs.

## [0.16.0]

### Added
- Waxed variants of Pyrite copper blocks.
- Cobbled Deepslate and Mossy Cobbled Deepslate Bricks.
- Optional compatibility for [Columns](https://modrinth.com/mod/columns) by haykam.
- Optional compatibility for [More Chest Variants](https://modrinth.com/mod/more-chest-variants-lieonlion) by LieOn Studios.
  - Trapped chests have not yet been added, they'll be in a future patch.
- On Fabric, Pyrite ladders now work with trapdoors correctly.
- Updated Russian translations (@Alexander317)

### Changed
- Unwaxed copper blocks now properly oxidize, can be stripped, and waxed.
- On Fabric, Pyrite's items have been added to the vanilla item groups. 
- On Fabric, Pyrite also now has only one item group containing all of its items.
- On NeoForge, Pyrite's Redstone group has been integrated into the vanilla item group.
- Stone brick blocks now have an alternate texture for slabs and stairs.
- Nostalgia Copper Blocks now have a more pleasant texture.
- Azalea Logs can now be smelted into Charcoal.
- Vanilla Deepslate recipes have been changed to accomodate Cobbled Deepslate Bricks.

### Fixed
- On NeoForge, transparency issues with bars.
- Missing mineable tag on copper blocks.
- Duplication exploit with Pyrite's logs.
- Stained glass not tinting beacons.

## [0.15.10] - 2024-12-28

### Fixed
- On 1.20, fixed issues with 1.21 tag paths.
- On 1.20, fixed issues with Wall Gate models.

## [0.15.9] - 2024-12-28

### Fixed
- Stone Brick variants now have correct shortcut recipes.
- Resource block variants now have correct shortcut recipes.
- Walls and Wall Gates now have correct shortcut recipes.
- Aether compat blocks now have correct models.
- Dirt Paths now only drop when Silk Touched.

## [0.15.8] - 2024-12-26

### Added
- Potted variants for Pyrite's flowers.

### Changed
- Crafters can now be made out of Pyrite Crafting tables.

### Fixed
- Slabs now drop the correct number of slabs.
- Grass Turf should no longer cause a crash.
- Rendering issues with flowers on NeoForge.
- Minecraft data should no longer be transformed by Forgix.
- Terracotta now has a set tool.

## [0.15.7] - 2024-12-21

### Added
- Shortcut recipes for Granite, Andesite, Diorite, Cobblestone, and Smooth Stone brick block family.
- Smooth Stone Stairs
- Undyed Terracotta Bricks

### Changed
- Framed Glass no longer blocks Chests from opening.

### Fixed
- Soul Torch Levers now give off the correct amount of light.
- Planks now craft into the correct number of fences.

## [0.15.6] - 2024-12-18

### Added
- Stained Glass and Stained Glass Panes for Pyrite dyes.

### Changed
- Wall Gates now have a thicker model even when not connected to walls.
- Pyrite flowers have been reordered in the Creative inventory.

### Fixed
- Pyrite Carpets are now tagged correctly.

## [0.15.5] - 2024-12-15

### Changed
- Replaced Forgix with my personal fork that fixes issues with transformed structures.

### Fixed
- Hotfixed crashing issues on 1.20
- Pyrite resource blocks should now make the correct sounds.

## [0.15.4] - 2024-12-15
### Added
- Added Azalea Wood type, a rich dark red wood.

### Fixed
- Stained Framed Glass now tints beacons.
- Nostalgia blocks are now valid beacon bases.
- Netherite blocks no longer burn in lava/fire.
- Piglins now love Pyrite gold items.
- All resource blocks should now be correctly tagged.
- Carpets now make correct sounds.
- Glass and lamp blocks now make correct sounds.
- Pyrite flowers can be used for Suspicious Stew again (1.21.3+)
- Switchable Glass works now. (1.21.3+)
- Grass turf is tinted properly again (1.21.4+)
- Turf carpets are no longer mined faster with swords (1.21.4+)

## [0.15.3] - 2024-12-03
### Added
- Sandstone Brick set.

### Changed
- Stonecutting recipes now have advancement unlocks.

### Fixed
- Slabs are now tagged.

## [0.15.2] - 2024-12-12
### Added
- Andesite, Granite, Diorite and Calcite bricks and mossy variants.
- Mossy variants of Smooth Stone Bricks and vanilla Tuff Bricks.

### Changed
- Crafting recipes now have advancement unlocks.

### Fixed
- Recipes for brick blocks have been fixed.
- Crash fixes on Fabric 1.20.

## [0.15.0] - 2024-11-20
### Added
- Added Switchable Glass, which becomes opaque when powered.
- Added Concrete Stairs and Slabs, as well as Concrete and Concrete Powder for Pyrite's own dye sets.
- Added Stained Signs and Hanging Signs.
- Pyrite blocks that are made of wood should now properly interact with fire and lava in the same way as their vanilla counterparts.
- Compatibility for the Aether's blocks.
  - Skyroot Crafting Table.
  - Wall Gates for Holystone, Mossy Holystone, Holystone Bricks, Icestone, Aerogel, Carved Stone, Angelic Stone, and Hellfire Stone.

### Changed
- Pyrite's item group has been split up into five total groups: bricks, redstone blocks, resource blocks, woooden blocks, and a miscellaneous group.
- Added language files for English (Upside Down) and LOLCAT.
- Where possible, common tags have been applied to this mod's blocks, including Glass, Glass Panes, the various dye tags, and more. This should result in greater mod compatibility in 1.21 and beyond.
- Restored Oak Crafting Table translation.
- Removed dependency on Architectury API on NeoForge.

### Fixed
- Refactored datagen to fix issues with missing tags and recipes.
- Bars, doors, trapdoors, and other non-full blocks are no longer able to be used as Beacon base blocks.

## [0.14.5] - 2024-09-24

### Changed
- Fuel API has been reimplemented (24w38a)

### Fixed
- Crash on Forge dedicated server (1.19, 1.20)

## [0.14.4] - 2024-10-31
### Added
- Improved compatibility with Item Descriptions.

### Changed
- Rewrite of registration system.
- Migrated to Forgix.

### Fixed
- Fixes for blocks with missing tags.

## [0.14.3] - 2024-08-31
### Fixed
- Tuff wall gates
- Broken recipes (1.21.1)

## [0.14.1] - 2024-06-13
### Added
- Russian translation (@Alexander317)
- Support for NeoForge 1.21

## [0.14.0] - 2024-06-13
### Added
- Torches can now be dyed.
  - Dyed torches can also be made into Torch Levers
- Dyed Ladders can now be made out of Dyed Planks.
- Terracotta can now be made into Terracotta Bricks, alongside slab, stair, wall, and wall gate variants.
  - Modded dyes can now be made into Terracotta and Terracotta Bricks.
- Framed Glass can now be dyed.
  - Dyed Framed Glass can be made into Dyed Framed Glass Panes
- 1.21 only: Tuff Wall Gates can now be crafted out of the new Tuff Walls, as well as Polished Tuff Walls, Tuff Brick Walls.
- Partial Russian translation by @Alexander317

### Fixed
- Optimization of all textures.
- Fixes to Cobblestone Bricks model.
- Fixes to missing tags on Brick variants and Path Turf.


## [0.13.0] - 2024-06-01

### Added
- Torch Levers, which can now be made out of all Vanilla torches.
- A dyed wood set based on the Potato Wood from 24w14potato.

### Changed
- Pyrite for Fabric 1.20 and above no longer requires Architectury API.

### Fixed
- Grass Turf blocks are now properly tinted on Forge/NeoForge.

## [0.12.0] - 2024-05-29

### Added
- Support for MinecraftForge (1.20.1) and NeoForge (1.20.6).
- Added Crafting Tables for all wood sets, including vanilla and dyed sets.
- Redstone Lamps can now be made into a permanently Lit variant.

### Changed
- Pyrite now requires [Architectury API](https://modrinth.com/mod/architectury-api).
- Wall gates now have their own custom models, to fit in better with walls.

### Fixed
- Recipe bugfixes for Iron and Redstone blocks.

## [0.11.0] - 2024-05-20

### Added
- New Block sets for Iron, Gold, Emerald, Lapis, Redstone, Diamond, Netherite, Quartz, Amethyst, and Copper for consistency between each other's block options.
  - Added Cut Blocks for Iron, Gold, Emerald, Lapis, Redstone, Diamond, Netherite, Quartz, and Amethyst blocks.
    - This includes Slabs, Stairs, Walls, and Wall Gates
  - Added Chiseled, Bricks, Pillar and Smooth variants for Iron, Gold, Emerald, Lapis, Redstone, Diamond, Netherite, Copper, and Amethyst blocks.
    - Smooth blocks also have Slabs, Stairs, Walls, and Wall Gates
  - Added Bars for Iron, Gold, Emerald, Lapis, Redstone, Diamond, Netherite, Quartz, Copper and Amethyst blocks.
  - Added Doors, Trapdoors, and Buttons for Gold, Emerald, Lapis, Redstone, Diamond, Netherite, Quartz, and Amethyst blocks.
  - Added Pressure Plates for Emerald, Lapis, Redstone, Diamond, Netherite, Quartz, and Amethyst blocks.
  - Redstone variants all provide power, but Copper variants (currently) do not age and cannot be waxed.
- New flowers, including classic red and blue roses, alongside orange, pink, and white roses, as well as Paeonias, Buttercups, and Pink Daisies.
- New Turf block sets, six-sided Grass blocks.
  - These are available for Grass, Mycelium, Podzol, and Dirt Paths and come with slabs and stairs alongside the existing carpets.
- Various new Nostalgia Blocks, including Cobblestone, Gravel, Grass, and more.

### Changed
- Iron Trapdoors are now crafted out of Iron Ingots for consistency with Pyrite Trapdoors.
- Iron Bars are now crafted out of Cut Iron for consistency with Pyrite Bars.
- Nether Brick Fence Gates are now made of Nether Brick items, not Sticks.
- Dyed Bricks and Pyrite's Nether Bricks can now be made into Wall Gates.
- Turf Carpets are now crafted with Turf.

### Fixed

- Charred and Blue Nether Bricks no longer require a Pickaxe to mine.
- Fixed some missing tags on Cut Iron and Stained Planks.

## [0.10.0] - 2024-05-06

### Added
- Blue Nether Bricks

### Fixed
- Tag issues with Stained Planks.

## [0.9.0] - 2024-04-23

### Added

- Dye items for new Pyrite dyes.
- Red/Brown Mushroom Wood type, obtained from cutting down Giant Red and Brown Mushrooms.
- wool and carpet blocks for new Pyrite dyes.
- Rose Dye type, inspired by a removed dye from Minecraft Alpha.
- Glowing Obsidian, a removed feature from Pocket Edition.
- Charred Nether Bricks.

### Fixed
- Various tag improvements and recipe fixes.

## [0.8.0] - 2024-04-19

### Added
- Stained Doors for all dye types.
- Stained Trapdoors for all dye types.
- Glowstone Lamps, which are always on.
- Dyed Lamps for all dye types, which are always on.
- Honey dye type.
- Nostalgia dye type.
- Dirt Path Carpets, which can be crafted from Dirt Paths (which can now be Silk Touched)

### Changed
- Textures of some dyed wood and bricks.

## [0.7.0] - 2024-04-18

Initial release.