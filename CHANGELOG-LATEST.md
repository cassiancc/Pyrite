### Added
- Internal restructures now allow for content to be disabled via datapacks, enabled via `config/pyrite.toml`. This file can be edited ingame via [McQoy](https://modrinth.com/mod/mcqoy), but a restart is required to reload the packs.
  - Pyrite's additional dyes and nostalgia blocks have been disabled behind the new Pyrite Oddities config option.
  - Pyrite's Azalea Wood can now be disabled behind the new Pyrite Azalea config option.
  - Pyrite's Mushroom Wood can now be disabled behind the new Pyrite Mushrooms config option.
  - Pyrite Crafting Tables can now be disabled behind the new Pyrite Crafting Tables config option.
- Optional compatibility for [Farmer's Delight Refabricated](https://modrinth.com/mod/farmers-delight-refabricated).
  - Cabinets for all Pyrite wood sets.
  - Cutting recipes for Pyrite wood sets.
- Optional compatibility for [Totally Lit](https://modrinth.com/mod/totally-lit).
  - Pyrite torches will now burn out with the mod installed.
- Item textures for Pyrite torch levers.
- Torches can now be dyed in world, like Signs.
- Red Sandstone Bricks, in parity with existing Sandstone Bricks.
- Added Shelves for Pyrite planks. On 1.21.1, these are available as optional compatibility for [Copper Age Backport](https://modrinth.com/mod/backport-copper-age).
- Pale Oak and Resin blocks will now be registered when [Vanilla Backport](https://modrinth.com/mod/vanillabackport) is present.

### Changed
- Resource block variants will now yield more when crafted via the Stonecutter.
- Pyrite items not included in the `pyrite:enabled` item tag will no longer show up in the creative inventory.
- Dyeing recipes now use convention tags instead of dye items.
- Vanilla item group modification now works on NeoForge.
- Vanilla item group modification can now be disabled.

### Fixed
- Sounds on Wall Gates and Cobbled Deepslate Bricks.
- Recipes for waxed Copper blocks.
- Wall Gate textures are no longer stretched.
- Log spam when Columns was not installed.
- Sign items with incorrect translations.