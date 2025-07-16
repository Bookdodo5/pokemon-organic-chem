## 1. Project Structure

The project is organized into key directories:

-   `src/`: All the Java source code.
-   `res/`: All game assets: tiles, images, music, and data files.
-   `tools/`: Helper scripts: The `tmx_converter.py` is used to process map files.

## 2. Key concepts

Here are some key elements you need to know about the game.

### a. Maps & NPCs (`res/data/maps`, `src/entity`)

-   **Maps**: Game worlds are built using the [Tiled Map Editor](https://www.mapeditor.org/). Maps are stored as `.tmx` files in `res/data/maps`. You can edit them visually with Tiled. After editing, use `tools/tmx_converter.py` script to convert the `.tmx` files into a format the game can use.
-   **NPCs**: NPCs are initialized in `src/entity/NPCManager.java` using sprites defined in `src/entity/NPCSprites.java`. The sprite images are inside the `res/player` folder, and you can add more by placing the files here then editing `src/entity/NPCSprites.java` file.

### b. The Battle System (`src/battle`, `res/data/battles`)

-   **Molecules**: These are the "creatures" of this world. New molecules can be defined in `res/data/molecules/molecules.yaml`.
-   **Reactions**: These are the "moves" that molecules can use to transform themselves in battles. New reactions are defined in `res/data/reactions/reactions.yaml`.
-   **Cards**: Cards are used to set up appropriate condition for reactions. In battles, your cards are a combination of battle-specific cards and your deck. New cards are defined in `src/battle/cards/CardFactory.java`, with condition cards needing logic implementation as separate files.
-   **Battle Configuration**: Battles, starting molecules, and battle-specific cards are defined in `res/data/battles/battles.yaml`.

### c. Cutscenes (`src/cutscene`, `src/dialogue`)

-   **Cutscenes**: Complex, scripted events are `Cutscene` objects. A `Cutscene` is built from a sequence of `CutsceneAction`s (e.g., `MoveAction`, `DialogueAction`, `CameraAction`). You can find example templates and initializations in `src/cutscene/initialize` and `src/cutscene/template`.

## 3. How to Add New Content

Here are the workflows for expanding the game.

### a. Adding a New Map

1.  **Create the Map**: Use the Tiled Map Editor to create a new `.tmx` file. Use the existing tilesets from `res/tiles`.
2.  **Convert the Map**: Place the `.tmx` file in `res/data/maps` folder and run `tools/tmx_converter.py`. This will create folders that are readable by the game.
3.  **Initialize the Map**: Initialize the map in `src\tile\MapInitializer.java` with sound ID strings and tileset name for that map. If the map is exterior, put in the world coordinate of the top left most corner of the map (Same world coordinate as in [Tiled Map Editor](https://www.mapeditor.org/))
4.  **Place NPCs**: Add NPCs to your map in `src\entity\NPCManager.java`.
5.  **Connect the Map**: Add a transition between interiors and exteriors in `src\tile\TransitionInitializer.java`

### b. Adding a New Molecule

1.  **Define the Molecule**: Add a new entry in `res/data/molecules/molecules.yaml`. (Require a line with "#" between entries)
2.  **Create Assets**: Put in the molecule image files in `res\molecules` with file names being smiles notation.

### c. Adding a New Reaction

1.  **Define the Reaction**: Add a new entry in `res/data/reactions/reactions.yaml`. (Require a line with "#" between entries)
2.  **Define the Reaction Behavior**: Add a new entry in `res/data/reactions/reactions.yaml`. Subreactions are different versions of the same reaction with different reagents.


### d. Adding a New Trainer Battle

1.  **Define the Battle**: Define the battles in `res/data/battles/battles.yaml`.
2.  **Call the battle**: Call the battle during cutscenes using .battle() cutsceneAction.
3.  **Update Reaction Unlocks**: Update the reaction unlocks for battles in `src\pokedex\ReactionRecord.java`. The battle ids are numbers inside `BATTLE_XXX` key.

### e. Adding a New Cutscene

1.  **Build the Cutscene**: The cutscene initialization must be called from method `initializeCutscenes()` inside `src/cutscene/CutsceneManager.java`. The actual cutscene codes are meant to be put in `src/cutscene/initialize` package.
3.  **Build the Cutscene**: Use the `CutsceneBuilder` to chain together `CutsceneAction`s. Look at existing cutscenes in `src/cutscene/initialize` for example.
4.  **Trigger the Cutscene**: Link the new cutscene to an in-game event: talking to an NPC / entering an area / or interacting with objects.

### f. Adding a New Sound

1.  **Add the Sound File**: Place your `.wav` sound file into either `res/music/` for background music or `res/sound_effect/` for sound effects.
2.  **Register the Sound**: Open `src/assets/Sound.java` and add a new public static final String for your sound. This ID is what you'll use to reference the sound in the game code.
3.  **Use the Sound**: Use the ID you created in game logic, such as when defining a map's background music in `src\tile\MapInitializer.java`.

### g. Adding a New Animation

1.  **Create Animation Frames**: Put the animation as a single `.png` image file inside `res/animations`, with each frame stacking horizontally.
2.  **Register the Animation**: Open `src/assets/AnimationManager.java`. Inside the static, put the new animation to the animation map.
3.  **Trigger the Animation**: Call the animation during a cutscene using `.animation()` cutsceneAction or from battle logic.

### h. Adding a New Tileset

1.  **Add the Tileset Image**: Place your new tileset `.png` file into `res/tiles/`. The tiles are 32x32 pixels only.
2.  **Map Special Tiles**: Specify special tiles inside `src\tile\TilesetManager.java` using "[TilesetName]_[TileID]"
3.  **Use the Tiles**: Use this new tileset to draw your maps in [Tiled Map Editor](https://www.mapeditor.org/).
4.  **Incorporate into maps**: Use the new tileset names in map initialization.