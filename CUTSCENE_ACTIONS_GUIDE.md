# Cutscene Actions Guide (AI-generated)

## Table of Contents

1. [Overview](#overview)
2. [Basic Actions](#basic-actions)
3. [Character Actions](#character-actions)
4. [Camera Actions](#camera-actions)
5. [Audio Actions](#audio-actions)
6. [Visual Effects](#visual-effects)
7. [Game State Actions](#game-state-actions)
8. [Control Actions](#control-actions)
9. [Advanced Actions](#advanced-actions)
10. [Best Practices](#best-practices)
11. [Examples](#examples)

## Overview

Cutscene actions implement the `CutsceneAction` interface and can be categorized into two types:
- **Standard Actions**: Basic actions that execute automatically
- **Input Actions**: Actions that respond to player input (implement `InputCutsceneAction`)

All actions follow the same lifecycle:
1. `start()` - Called when the action begins
2. `update()` - Called each frame while the action is running
3. `end()` - Called when the action completes
4. `reset()` - Called to reset the action for reuse
5. `draw()` - Called to render any visual elements
6. `isFinished()` - Returns whether the action has completed

## Basic Actions

### WaitAction
**Purpose**: Pauses the cutscene for a specified duration.

**Constructor**:
```java
WaitAction(int frames)
```

**Parameters**:
- `frames` (int): Number of frames to wait (60 frames = 1 second at 60 FPS)

**Example**:
```java
new WaitAction(120) // Wait for 2 seconds
```

### WaitForInputAction
**Purpose**: Pauses the cutscene until the player presses the interact key.

**Constructor**:
```java
WaitForInputAction()
```

**Input Handling**: Responds to the `INTERACT` key.

**Example**:
```java
new WaitForInputAction() // Wait for player to press interact
```

### CommandAction
**Purpose**: Executes arbitrary code when the action starts.

**Constructor**:
```java
CommandAction(Runnable command)
```

**Parameters**:
- `command` (Runnable): The code to execute

**Example**:
```java
new CommandAction(() -> {
    flagManager.addFlag("quest_completed");
    System.out.println("Quest completed!");
})
```

## Character Actions

### MovementAction
**Purpose**: Moves a character (player or NPC) to a specific tile position.

**Constructor**:
```java
MovementAction(Human targetHuman, int targetX, int targetY)
```

**Parameters**:
- `targetHuman` (Human): The character to move (Player or NPC)
- `targetX` (int): Target X coordinate in tile space
- `targetY` (int): Target Y coordinate in tile space

**Behavior**: 
- Automatically calculates the path and moves the character
- Character faces the direction of movement
- Completes when the character reaches the target position

**Example**:
```java
new MovementAction(player, 15, 20) // Move player to tile (15, 20)
```

### TeleportAction
**Purpose**: Instantly teleports a character to a new position, optionally changing maps.

**Constructor**:
```java
TeleportAction(Human targetHuman, int targetX, int targetY, String targetMap, OverworldState overworldState)
```

**Parameters**:
- `targetHuman` (Human): The character to teleport
- `targetX` (int): Target X coordinate
- `targetY` (int): Target Y coordinate
- `targetMap` (String): Name of the target map (use current map name to stay on same map)
- `overworldState` (OverworldState): Reference to the game state

**Example**:
```java
new TeleportAction(player, 0, 0, "route1", overworldState) // Teleport player to route1 at (0,0)
```

### FaceDirectionAction
**Purpose**: Makes a character face a specific direction or towards another character.

**Constructors**:
```java
FaceDirectionAction(Human targetHuman, FacingDirections direction)
FaceDirectionAction(Human targetHuman, Human directionHuman)
```

**Parameters**:
- `targetHuman` (Human): The character to rotate
- `direction` (FacingDirections): Specific direction (UP, DOWN, LEFT, RIGHT)
- `directionHuman` (Human): Character to face towards (automatically calculates direction)

**Example**:
```java
new FaceDirectionAction(player, FacingDirections.UP) // Face player up
new FaceDirectionAction(npc, player) // Make NPC face towards player
```

### EmoteAction
**Purpose**: Displays an emotion bubble above a character.

**Constructor**:
```java
EmoteAction(Human target, int duration, Emotes emote, CameraManager cameraManager)
```

**Parameters**:
- `target` (Human): Character to show emote above
- `duration` (int): How long to display the emote (in frames)
- `emote` (Emotes): The emotion to display
- `cameraManager` (CameraManager): Reference to camera for positioning

**Available Emotes**:
- `Emotes.SURPRISE` - Surprised expression
- `Emotes.QUESTION` - Question mark
- `Emotes.WAIT1`, `Emotes.WAIT2`, `Emotes.WAIT3` - Waiting animations
- `Emotes.LOVE` - Heart
- `Emotes.MUSIC` - Musical note
- `Emotes.SMILE` - Happy face
- `Emotes.FRIENDLY` - Friendly expression
- `Emotes.SAD` - Sad face
- `Emotes.ANGRY` - Angry face

**Example**:
```java
new EmoteAction(player, 90, Emotes.SURPRISE, cameraManager) // Show surprise for 1.5 seconds
```

## Camera Actions

### CameraAction
**Purpose**: Controls camera movement, positioning, and effects.

**Constructors**:
```java
CameraAction(CameraManager cameraManager, int moveX, int moveY, int time)
CameraAction(CameraManager cameraManager, Human focusPoint)
CameraAction(CameraManager cameraManager, int setX, int setY)
CameraAction(CameraManager cameraManager, int shakeDuration)
```

**Parameters**:
- `cameraManager` (CameraManager): Reference to the camera system
- `moveX`, `moveY` (int): Distance to move camera over time
- `time` (int): Duration of movement in frames
- `focusPoint` (Human): Character to focus camera on
- `setX`, `setY` (int): Exact camera position to set
- `shakeDuration` (int): Duration of camera shake effect

**Modes**:
1. **Move**: Smoothly moves camera over time
2. **Follow Human**: Focuses camera on a character
3. **Set**: Instantly sets camera position
4. **Shake**: Creates a camera shake effect

**Example**:
```java
new CameraAction(cameraManager, 100, 50, 120) // Move camera 100px right, 50px down over 2 seconds
new CameraAction(cameraManager, player) // Focus camera on player
new CameraAction(cameraManager, 500, 300) // Set camera to position (500, 300)
new CameraAction(cameraManager, 60) // Shake camera for 1 second
```

## Audio Actions

### PlaysoundAction
**Purpose**: Plays sound effects or music.

**Constructor**:
```java
PlaysoundAction(String sound, boolean isMusic)
```

**Parameters**:
- `sound` (String): Name of the sound file (without extension)
- `isMusic` (boolean): `true` for music, `false` for sound effect

**Example**:
```java
new PlaysoundAction("GameCursor", false) // Play cursor sound effect
new PlaysoundAction("BattleGymLeader", true) // Play gym leader music
```

## Visual Effects

### FadeAction
**Purpose**: Creates fade in/out effects.

**Constructors**:
```java
FadeAction(int duration, int start, int target, int persistence)
FadeAction(int duration, int start, int target)
```

**Parameters**:
- `duration` (int): Duration of fade in frames
- `start` (int): Starting alpha value (0-255, where 0 is transparent)
- `target` (int): Target alpha value (0-255, where 255 is opaque)
- `persistence` (int): How long to hold the target alpha (-1 for indefinite)

**Common Patterns**:
- `FadeAction(60, 0, 255, 30)` - Fade in over 1 second, hold for 0.5 seconds
- `FadeAction(60, 255, 0)` - Fade out over 1 second
- `FadeAction(60, 0, 255, -1)` - Fade in and wait for player input

**Example**:
```java
new FadeAction(60, 0, 255, 30) // Fade in, hold briefly
new FadeAction(60, 255, 0) // Fade out
```

### AnimationAction
**Purpose**: Plays sprite animations at a specific location.

**Constructor**:
```java
AnimationAction(String animation, int x, int y, double scale)
```

**Parameters**:
- `animation` (String): Name of the animation file (without extension)
- `x`, `y` (int): Screen coordinates to display the animation
- `scale` (double): Scale factor for the animation

**Example**:
```java
new AnimationAction("electric1", 100, 100, 1.0) // Play electric animation at (100,100)
new AnimationAction("fire4", 30 * 32, 13 * 32, 0.8) // Play fire animation at tile position
```

### ImageBoxAction
**Purpose**: Displays an image in a styled box with player interaction.

**Constructor**:
```java
ImageBoxAction(String imagePath)
```

**Parameters**:
- `imagePath` (String): Path to the image file (relative to resources)

**Behavior**:
- Displays image in a dialogue-style box
- Automatically scales image to fit the box
- Waits for player interaction to continue

**Example**:
```java
new ImageBoxAction("/animations/electric1.png") // Show electric effect image
```

## Game State Actions

### SetFlagAction
**Purpose**: Sets one or more game flags.

**Constructor**:
```java
SetFlagAction(FlagManager flagManager, String... flags)
```

**Parameters**:
- `flagManager` (FlagManager): Reference to the flag system
- `flags` (String...): Variable number of flag names to set

**Example**:
```java
new SetFlagAction(flagManager, "quest_started", "met_professor")
```

### RemoveFlagAction
**Purpose**: Removes one or more game flags.

**Constructor**:
```java
RemoveFlagAction(FlagManager flagManager, String... flags)
```

**Parameters**:
- `flagManager` (FlagManager): Reference to the flag system
- `flags` (String...): Variable number of flag names to remove

**Example**:
```java
new RemoveFlagAction(flagManager, "BATTLE_WIN", "BATTLE_LOSE")
```

### BattleAction
**Purpose**: Initiates a battle sequence.

**Constructor**:
```java
BattleAction(StateManager stateManager, FlagManager flagManager, int battleID)
```

**Parameters**:
- `stateManager` (StateManager): Reference to the game state manager
- `flagManager` (FlagManager): Reference to the flag system
- `battleID` (int): ID of the battle to start

**Behavior**:
- Transitions to battle state
- Waits for battle completion (WIN or LOSE flag)
- Returns to cutscene when battle ends

**Example**:
```java
new BattleAction(stateManager, flagManager, 1) // Start battle ID 1
```

## Control Actions

### DialogueAction
**Purpose**: Displays dialogue text with optional choices.

**Constructor**:
```java
DialogueAction(Dialogue dialogue)
```

**Parameters**:
- `dialogue` (Dialogue): Dialogue object containing text and options

**Features**:
- Supports multi-page dialogue
- Optional dialogue choices with callbacks
- Text animation speed control
- Automatic sound effects

**Example**:
```java
Dialogue dialogue = new Dialogue(new String[] {
    "Hello, trainer!",
    "Welcome to our laboratory."
});
new DialogueAction(dialogue)
```

## Advanced Actions

### ParallelAction
**Purpose**: Executes multiple actions simultaneously.

**Constructor**:
```java
ParallelAction(CutsceneAction... actions)
```

**Parameters**:
- `actions` (CutsceneAction...): Variable number of actions to run in parallel

**Behavior**:
- All actions start simultaneously
- Action completes when all child actions finish
- Supports input actions (passes input to all child input actions)

**Example**:
```java
new ParallelAction(
    new EmoteAction(player, 60, Emotes.SURPRISE, cameraManager),
    new PlaysoundAction("GUIMenuOpen", false),
    new AnimationAction("26", 100, 100, 1)
)
```

## Best Practices

### 1. Action Ordering
- Place `WaitAction` or `WaitForInputAction` between major events
- Use `FadeAction` for scene transitions
- Group related actions with `ParallelAction`

### 2. Performance
- Keep animations and effects reasonable in duration
- Avoid excessive camera movement
- Use appropriate sound effect volumes

### 3. User Experience
- Always provide clear visual feedback
- Use consistent timing patterns
- Include player interaction points for long sequences

### 4. Flag Management
- Set flags in case you need to conditionally trigger certain scenes such as winning or losing battle dialogues