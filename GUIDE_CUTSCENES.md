# Cutscene Actions Guide (AI-generated)

This guide provides a detailed documentation of all available `CutsceneAction`s that can be used to create cutscenes in the game. Each action is a modular component that performs a specific task. They can be combined sequentially or in parallel to create complex event sequences.

---

## Action Index

*   [AnimationAction](#animationaction)
*   [BattleAction](#battleaction)
*   [CameraAction](#cameraaction)
*   [CommandAction](#commandaction)
*   [ConditionAction](#conditionaction)
*   [DialogueAction](#dialogueaction)
*   [EmoteAction](#emoteaction)
*   [FaceDirectionAction](#facedirectionaction)
*   [FadeAction](#fadeaction)
*   [ImageBoxAction](#imageboxaction)
*   [MovementAction](#movementaction)
*   [ParallelAction](#parallelaction)
*   [PlaysoundAction](#playsoundaction)
*   [RemoveFlagAction](#removeflagaction)
*   [SequentialAction](#sequentialaction)
*   [SetFlagAction](#setflagaction)
*   [TeleportAction](#teleportaction)
*   [WaitAction](#waitaction)
*   [WaitForInputAction](#waitforinputaction)

---

## AnimationAction

Plays an animation at a specific location on the screen.

### Description

The `AnimationAction` is used to display pre-defined animations, such as effects or indicators. It can be positioned at static coordinates or dynamically linked to a character's position.

### Constructors

*   `AnimationAction(String animation, int x, int y, double scale)`: Plays an animation at fixed tile coordinates `(x, y)` with a given `scale`.
*   `AnimationAction(String animation, int x, int y, double scale, CameraManager cameraManager)`: Same as above, but adjusts the position based on the camera, so it appears at the correct world location. `x` and `y` are tile coordinates.
*   `AnimationAction(String animation, Callable<Integer> getX, Callable<Integer> getY, double scale, CameraManager cameraManager)`: Plays an animation at a dynamic position provided by `getX` and `getY` callables (which should return pixel coordinates). This is useful for attaching animations to moving entities.

---

## BattleAction

Initiates a battle sequence.

### Description

The `BattleAction` transitions the game into the battle state. It uses a battle ID to load the correct opponent and settings from the battle data files. When the battle concludes, it sets a "BATTLE_WIN" or "BATTLE_LOSE" flag, which can be used by `ConditionAction` to create different outcomes.

### Constructors

*   `BattleAction(StateManager stateManager, FlagManager flagManager, int battleID)`: Starts the battle identified by `battleID`.

---

## CameraAction

Controls the game's camera.

### Description

`CameraAction` provides a suite of tools for manipulating the camera's focus and position. This is essential for directing the player's attention during a cutscene.

### Modes and Constructors

*   **Follow a character:** `CameraAction(CameraManager cameraManager, Human focusPoint)`
    *   The camera will lock onto and follow the specified `Human`.

*   **Move the camera:** `CameraAction(CameraManager cameraManager, int moveX, int moveY, int time)`
    *   Moves the camera by a relative amount (`moveX`, `moveY` in pixels) over a specified `time` (in frames).

*   **Move camera to a character:** `CameraAction(CameraManager cameraManager, Human target, int time)`
    *   Smoothly pans the camera to center on the specified `Human` `target` over a `time` (in frames).

*   **Set camera position:** `CameraAction(CameraManager cameraManager, int setX, int setY)`
    *   Instantly jumps the camera to the absolute pixel coordinates `(setX, setY)`.

*   **Shake the camera:** `CameraAction(CameraManager cameraManager, int shakeDuration)`
    *   Shakes the camera for a `shakeDuration` (in frames). Useful for impact effects.

---

## CommandAction

Executes a block of arbitrary code.

### Description

The `CommandAction` is a versatile tool that allows you to run any piece of code as part of a cutscene by wrapping it in a `Runnable`. This is useful for performing tasks that don't fit into the other action types, such as complex game state manipulations.

### Constructors

*   `CommandAction(Runnable command)`: Executes the `run()` method of the provided `command`.

---

## ConditionAction

Executes an action only if a condition is true.

### Description

`ConditionAction` acts as an `if` statement for cutscenes. It wraps another `CutsceneAction` and only executes it if a specified condition is met. This is commonly used with game flags.

### Constructors

*   `ConditionAction(BooleanSupplier condition, CutsceneAction trueAction)`: Executes `trueAction` if the `BooleanSupplier` returns `true`.
*   `ConditionAction(FlagManager flagManager, String flag, CutsceneAction trueAction)`: Executes `trueAction` if the specified `flag` exists in the `FlagManager`.
*   `ConditionAction(FlagManager flagManager, String flag, int expectedValue, CutsceneAction trueAction)`: Executes `trueAction` if the `flag` exists and its value is equal to `expectedValue`.

---

## DialogueAction

Displays dialogue to the player.

### Description

The `DialogueAction` handles the presentation of text, dialogue trees, and player choices. It manages the text rendering, scrolling, and input for navigating conversations.

### Constructors

*   `DialogueAction(Dialogue dialogue)`: Starts the specified `dialogue` object.

---

## EmoteAction

Displays an emote bubble above a character.

### Description

`EmoteAction` is used to show a character's emotion visually. It displays a specific emote icon (like '!', '?', '...') over a character's head for a set duration.

### Constructors

*   `EmoteAction(Human target, int duration, Emotes emote, CameraManager cameraManager)`: Shows the specified `emote` over the `target` character for `duration` (in frames).

---

## FaceDirectionAction

Changes the direction a character is facing.

### Description

This action instantly changes a character's facing direction. It can be a fixed direction or dynamic, based on another character's location.

### Constructors

*   `FaceDirectionAction(Human targetHuman, FacingDirections direction)`: Makes `targetHuman` face a specific `direction` (UP, DOWN, LEFT, RIGHT).
*   `FaceDirectionAction(Human targetHuman, Human directionHuman)`: Makes `targetHuman` turn to face `directionHuman`.

---

## FadeAction

Fades the screen to or from black.

### Description

`FadeAction` creates a fade effect, which is useful for transitions. You can control the duration, opacity, and how long the screen stays faded.

### Constructors

*   `FadeAction(int duration, int start, int target)`: Fades from `start` alpha (0-255) to `target` alpha over `duration` (in frames).
*   `FadeAction(int duration, int start, int target, int persistence)`: Same as above, but holds the `target` alpha for `persistence` frames. If `persistence` is -1, it will wait for the player to press the interact button before finishing.

---

## ImageBoxAction

Displays an image in a pop-up box.

### Description

This action shows a large image centered on the screen within a decorative box. It's useful for displaying key items, illustrations, or maps. The action waits for the player to press the interact button before closing.

### Constructors

*   `ImageBoxAction(String imagePath)`: Loads and displays the image from the given `imagePath`.

---

## MovementAction

Moves a character from one tile to another.

### Description

`MovementAction` handles character pathfinding to a target destination on the map. It moves the character and waits until they have reached the target coordinates.

### Constructors

*   `MovementAction(Human targetHuman, int targetX, int targetY)`: Moves `targetHuman` to map coordinates `(targetX, targetY)`. The pathing logic defaults to moving along the longest axis first.
*   `MovementAction(Human targetHuman, int targetX, int targetY, Boolean isXfirst)`: Allows specifying the pathing priority. If `isXfirst` is `true`, it moves horizontally first, then vertically. If `false`, it moves vertically first.

---

## ParallelAction

Runs multiple actions simultaneously.

### Description

`ParallelAction` takes an array of `CutsceneAction`s and runs them all at the same time. This action is finished only when all of the sub-actions have completed.

### Constructors

*   `ParallelAction(CutsceneAction... actions)`: Executes all `actions` in parallel.

---

## PlaysoundAction

Plays a sound effect or music track.

### Description

This action is used to control the game's audio. It can play one-shot sound effects or start/stop music tracks.

### Constructors

*   `PlaysoundAction(String sound, boolean isMusic)`: If `isMusic` is `false`, it plays a sound effect from the SFX player. If `true`, it plays a track from the music player. To stop the current music, use the sound name "Stop".

---

## RemoveFlagAction

Removes a flag from the game state.

### Description

`RemoveFlagAction` is used to clear one or more flags from the `FlagManager`. This is often used to clean up state after a cutscene or event.

### Constructors

*   `RemoveFlagAction(FlagManager flagManager, String... flags)`: Removes all specified `flags`.

---

## SequentialAction

Runs multiple actions one after another.

### Description

This is the most fundamental building block for cutscenes. `SequentialAction` takes an array of `CutsceneAction`s and executes them in order, waiting for each one to finish before starting the next.

### Constructors

*   `SequentialAction(CutsceneAction... actions)`: Executes all `actions` in sequence.

---

## SetFlagAction

Adds a flag to the game state.

### Description

`SetFlagAction` is used to add one or more flags to the `FlagManager`. Flags are used to track game progress and make decisions in `ConditionAction`.

### Constructors

*   `SetFlagAction(FlagManager flagManager, String... flags)`: Adds all specified `flags`.

---

## TeleportAction

Instantly moves a character to a new location.

### Description

`TeleportAction` can move a character to new coordinates on the same map or to a completely different map. When moving the player to a new map, it handles the screen transition and loading of the new area.

### Constructors

*   `TeleportAction(Human targetHuman, int targetX, int targetY, String targetMap, OverworldState overworldState)`: Moves `targetHuman` to tile `(targetX, targetY)` on the map specified by `targetMap`.

---

## WaitAction

Pauses the cutscene for a set amount of time.

### Description

`WaitAction` simply pauses all cutscene progression for a specific number of frames.

### Constructors

*   `WaitAction(int endTimer)`: Waits for `endTimer` frames.

---

## WaitForInputAction

Pauses the cutscene until the player presses a button.

### Description

`WaitForInputAction` halts the cutscene and waits for the player to press the "Interact" button before allowing the sequence to continue.

### Constructors

*   `WaitForInputAction()`: Waits for player input.

---

## CutsceneBuilder Guide

The `CutsceneBuilder` provides a fluent interface for assembling `CutsceneAction`s into a complete cutscene. You chain methods together to add actions to a sequence, which can then be built into a `Cutscene` object.

### Building a Cutscene

The final step of the builder is to create the `Cutscene` object.

*   `buildCutscene()`: Returns a new `Cutscene` instance containing all the configured actions and flag requirements.
*   `buildActions()`: Returns the raw array of `CutsceneAction`s.

### Flag Requirements

You can make a cutscene conditional based on game flags.

*   `require(String... flag)`: The cutscene will only play if ALL of these flags are set.
*   `forbid(String... flag)`: The cutscene will only play if NONE of these flags are set.

---

### Builder Methods

#### Timing & Flow Control

*   `wait(int frames)`: Pauses the cutscene for a number of frames.
*   `waitForInput()`: Pauses until the player presses the interact button.
*   `parallel(CutsceneAction... actions)`: Runs a set of actions simultaneously.
*   `sequential(CutsceneAction... actions)`: Runs a set of actions one after another. This is useful for grouping actions within a larger sequence.
*   `condition(BooleanSupplier condition, CutsceneAction... trueActions)`: Executes the given actions only if the `BooleanSupplier` returns true.
*   `condition(String flag, CutsceneAction... trueActions)`: Executes if the given `flag` is set.
*   `condition(String flag, int value, CutsceneAction... trueActions)`: Executes if the `flag` has the specified `value`.

#### Audio

*   `sfx(String soundName)`: Plays a sound effect.
*   `music(String musicName)`: Starts playing a music track.
*   `musicStop()`: Stops the current music track.

#### Character Actions

*   `move(Human character, int x, int y)`: Moves a character to the target tile coordinates.
*   `moveXthenY(Human character, int x, int y)`: Moves horizontally first, then vertically.
*   `moveYthenX(Human character, int x, int y)`: Moves vertically first, then horizontally.
*   `face(Human character, FacingDirections direction)`: Turns a character to face a fixed direction.
*   `faceTowards(Human character, Human target)`: Turns a character to face another character.
*   `emote(Human character, int duration, Emotes emote, CameraManager camera)`: Displays an emote bubble over a character.
*   `tp(Human character, int x, int y, String map, OverworldState os)`: Teleports a character to a new location.

#### Dialogue & Text

*   `say(Dialogue dialogue)`: Displays a pre-made `Dialogue` object.
*   `say(String text)`: Displays a single page of text.
*   `say(String... pages)`: Displays multiple pages of text.

#### Camera Control

*   `camMove(CameraManager camera, int x, int y, int time)`: Moves the camera by a relative amount over time.
*   `camFocus(CameraManager camera, Human target)`: Sets the camera to follow a character.
*   `camMoveToHuman(CameraManager camera, Human target, int time)`: Smoothly pans the camera to a character.
*   `camChangeFocus(CameraManager camera, Human target, int time)`: A combination of `camMoveToHuman` and `camFocus`.
*   `camSet(CameraManager camera, int x, int y)`: Instantly sets the camera's position.
*   `camShake(CameraManager camera, int duration)`: Shakes the camera.

#### Visuals & Effects

*   `fadeIn(int duration)`: Fades the screen in from black.
*   `fadeOut(int duration)`: Fades the screen out to black.
*   `animation(...)`: Plays an animation. (See `AnimationAction` for overloads).
*   `showImage(String imagePath)`: Displays a large image in a box.

#### Game State

*   `setFlag(String... flags)`: Adds one or more flags.
*   `removeFlag(String... flags)`: Removes one or more flags.
*   `execute(Runnable command)`: Executes arbitrary Java code.
*   `battle(StateManager sm, int battleID)`: Starts a battle.

#### Composite Actions

These are convenient shortcuts for common sequences of actions.

*   `approachPlayer(Human character, int x, int y, CameraManager camera)`: A sequence where a character shows a surprise emote, moves, and faces the player.
*   `react(Human character, CameraManager camera, Emotes emote)`: Shows an emote with a sound effect.
*   `speak(Dialogue dialogue)`: Plays a sound effect and then shows a dialogue.
*   `speak(String speaker, String... pages)`: `say` but with a speaker name prepended.
*   `think(String... pages)`: `say` but formatted as thought bubbles.
*   `shout(String speaker, String text, CameraManager camera)`: A sequence that shakes the camera, shows an emote, and displays dialogue.
