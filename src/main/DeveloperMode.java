package main;

import entity.Player;
import gamestates.FlagManager;
import gamestates.GameStates;
import gamestates.StateManager;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import menu.Settings;
import tile.MapManager;

public class DeveloperMode {
    private final GameContentManager gameContentManager;
    private final Scanner scanner;
    private final AtomicBoolean isRunning;
    private Thread consoleThread;
    private boolean isHyperSpeed = false;
    
    public DeveloperMode(GameContentManager gameContentManager) {
        this.gameContentManager = gameContentManager;
        this.scanner = new Scanner(System.in);
        this.isRunning = new AtomicBoolean(true);
        startConsole();
    }
    
    private void startConsole() {
        consoleThread = new Thread(() -> {
            System.out.println("\n=== DEVELOPER CONSOLE ACTIVE ===");
            System.out.println("Type 'help' for available commands");
            System.out.println("Type 'quit' to exit the game");
            System.out.println("==================================\n");
            
            while (isRunning.get()) {
                try {
                    System.out.print("DEV> ");
                    String input = scanner.nextLine().trim();
                    
                    if (input.equalsIgnoreCase("quit")) {
                        System.out.println("Exiting game...");
                        System.exit(0);
                    }
                    
                    if (!input.isEmpty()) {
                        executeCommand(input);
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });
        
        consoleThread.setDaemon(true); // This ensures the console thread doesn't prevent the game from exiting
        consoleThread.start();
    }
    
    private void executeCommand(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length == 0) return;
        
        String command = parts[0].toLowerCase();
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);
        
        switch (command) {
            case "help":
                showHelp();
                break;
            case "flags":
                handleFlags(args);
                break;
            case "position":
                handlePosition(args);
                break;
            case "map":
                handleMap(args);
                break;
            case "state":
                handleState(args);
                break;
            case "info":
                showInfo();
                break;
            case "clear":
                clearScreen();
                break;
            case "npc":
                handleNPC(args);
                break;
            case "cutscene":
                handleCutscene(args);
                break;
            case "fps":
                handleFPS(args);
                break;
            case "hyperspeed":
                toggleHyperSpeed();
                break;
            default:
                System.out.println("Unknown command: " + command);
                System.out.println("Type 'help' for available commands");
        }
    }
    
    private void showHelp() {
        System.out.println("\nAvailable Commands:");
        System.out.println("  help                    - Show this help");
        System.out.println("  quit                    - Exit the game");
        System.out.println("  info                    - Show current game state");
        System.out.println("  clear                   - Clear console");
        System.out.println("  flags                   - Show all flags");
        System.out.println("  flags add <flag>        - Add a flag");
        System.out.println("  flags remove <flag>     - Remove a flag");
        System.out.println("  flags set <flag> <value> - Set flag value");
        System.out.println("  flags reset             - Reset all flags");
        System.out.println("  position                - Show player position");
        System.out.println("  position <x> <y>        - Set player position");
        System.out.println("  map                     - Show current map");
        System.out.println("  map <mapName> [x] [y]   - Change map and position");
        System.out.println("  state                   - Show current game state");
        System.out.println("  state <stateName>       - Change game state");
        System.out.println("  npc                     - Show all NPCs");
        System.out.println("  npc <id>                - Show specific NPC info");
        System.out.println("  npc move <id> <x> <y>   - Move NPC to position");
        System.out.println("  cutscene                - Show available cutscenes");
        System.out.println("  fps                     - Show current FPS");
        System.out.println("  fps <value>             - Set FPS (e.g., 30, 60, 120)");
        System.out.println("  hyperspeed              - Toggle cutscene fast forward");
        System.out.println("\nExample:");
        System.out.println("  flags add test_flag");
        System.out.println("  position 10 5");
        System.out.println("  map methanopolis 15 20");
        System.out.println("  state overworld");
        System.out.println("  npc move ProfDecane 5 5");
        System.out.println("  fps 30");
    }
    
    private void handleFlags(String[] args) {
        FlagManager flagManager = gameContentManager.getFlagManager();
        
        if (args.length == 0) {
            System.out.println("Current flags:");
            flagManager.printFlags();
            return;
        }
        
        String subCommand = args[0].toLowerCase();
        
        switch (subCommand) {
            case "add":
                if (args.length < 2) {
                    System.out.println("Usage: flags add <flag>");
                    return;
                }
                flagManager.addFlag(args[1]);
                System.out.println("Added flag: " + args[1]);
                break;
                
            case "remove":
                if (args.length < 2) {
                    System.out.println("Usage: flags remove <flag>");
                    return;
                }
                flagManager.removeFlag(args[1]);
                System.out.println("Removed flag: " + args[1]);
                break;
                
            case "set":
                if (args.length < 3) {
                    System.out.println("Usage: flags set <flag> <value>");
                    return;
                }
                try {
                    int value = Integer.parseInt(args[2]);
                    flagManager.setFlag(args[1], value);
                    System.out.println("Set flag " + args[1] + " to " + value);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid value: " + args[2]);
                }
                break;
                
            case "reset":
                flagManager.resetFlags();
                System.out.println("All flags reset");
                break;
                
            default:
                System.out.println("Unknown flags command: " + subCommand);
                System.out.println("Use: add, remove, set, reset");
        }
    }
    
    private void handlePosition(String[] args) {
        Player player = gameContentManager.getPlayer();
        
        if (args.length == 0) {
            System.out.println("Player position: (" + player.getMapX() + ", " + player.getMapY() + ")");
            System.out.println("Current map: " + gameContentManager.getMapManager().getCurrentMapID());
            return;
        }
        
        if (args.length < 2) {
            System.out.println("Usage: position <x> <y>");
            return;
        }
        
        try {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            player.setMapX(x);
            player.setMapY(y);
            System.out.println("Player moved to (" + x + ", " + y + ")");
        } catch (NumberFormatException e) {
            System.out.println("Invalid coordinates");
        }
    }
    
    private void handleMap(String[] args) {
        MapManager mapManager = gameContentManager.getMapManager();
        Player player = gameContentManager.getPlayer();
        
        if (args.length == 0) {
            System.out.println("Current map: " + mapManager.getCurrentMapID());
            return;
        }
        
        String mapName = args[0];
        int x = player.getMapX();
        int y = player.getMapY();
        
        if (args.length >= 3) {
            try {
                x = Integer.parseInt(args[1]);
                y = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid coordinates");
                return;
            }
        }
        
        mapManager.setCurrentMap(mapName);
        player.setMapX(x);
        player.setMapY(y);
        player.setMap(mapName);
        mapManager.updateVisibleMaps(x, y);
        gameContentManager.getCameraManager().update();
        
        System.out.println("Moved to map: " + mapName + " at (" + x + ", " + y + ")");
    }
    
    private void handleState(String[] args) {
        StateManager stateManager = gameContentManager.getStateManager();
        
        if (args.length == 0) {
            System.out.println("Current state: " + stateManager.getState());
            System.out.println("Available states:");
            for (GameStates state : GameStates.values()) {
                System.out.println("  " + state.name().toLowerCase());
            }
            return;
        }
        
        String stateName = args[0].toUpperCase();
        try {
            GameStates newState = GameStates.valueOf(stateName);
            stateManager.setState(newState);
            System.out.println("Changed to state: " + newState);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid state: " + args[0]);
            System.out.println("Available states:");
            for (GameStates state : GameStates.values()) {
                System.out.println("  " + state.name().toLowerCase());
            }
        }
    }
    
    private void handleNPC(String[] args) {
        var npcManager = gameContentManager.getNpcManager();
        
        if (args.length == 0) {
            System.out.println("All NPCs:");
            for (var npc : npcManager.getNPCs()) {
                System.out.println("  " + npc.getId() + " at (" + npc.getMapX() + ", " + npc.getMapY() + ") in " + npc.getMap());
            }
            return;
        }
        
        if (args.length == 1) {
            var npc = npcManager.getNPC(args[0]);
            if (npc != null) {
                System.out.println("NPC: " + npc.getId());
                System.out.println("  Position: (" + npc.getMapX() + ", " + npc.getMapY() + ")");
                System.out.println("  Map: " + npc.getMap());
                System.out.println("  Direction: " + npc.getCurrentDirection());
            } else {
                System.out.println("NPC not found: " + args[0]);
            }
            return;
        }
        
        if (args.length >= 4 && args[0].equalsIgnoreCase("move")) {
            var npc = npcManager.getNPC(args[1]);
            if (npc != null) {
                try {
                    int x = Integer.parseInt(args[2]);
                    int y = Integer.parseInt(args[3]);
                    npc.setMapX(x);
                    npc.setMapY(y);
                    System.out.println("Moved " + npc.getId() + " to (" + x + ", " + y + ")");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid coordinates");
                }
            } else {
                System.out.println("NPC not found: " + args[1]);
            }
        }
    }
    
    private void handleCutscene(String[] args) {
        if (args.length == 0) {
            System.out.println("Cutscene system active");
            System.out.println("Cutscenes are triggered by player interactions");
            System.out.println("Use 'flags' to control cutscene conditions");
            return;
        }
        
        // Could add more cutscene-specific commands here
        System.out.println("Cutscene commands not implemented yet");
    }
    
    private void handleFPS(String[] args) {
        Settings settings = Settings.getInstance();
        
        if (args.length == 0) {
            System.out.println("Current FPS: " + settings.getFPS());
            return;
        }
        
        try {
            int fps = Integer.parseInt(args[0]);
            if (fps < 1 || fps > 240) {
                System.out.println("FPS must be between 1 and 240");
                return;
            }
            settings.setFPS(fps);
            System.out.println("FPS set to: " + fps);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number for FPS: " + args[0]);
        }
    }
    
    private void toggleHyperSpeed() {
        isHyperSpeed = !isHyperSpeed;
        if (isHyperSpeed) {
            System.out.println("Hyper speed enabled!");
        } else {
            System.out.println("Hyper speed disabled!");
        }
    }
    
    private void showInfo() {
        Player player = gameContentManager.getPlayer();
        MapManager mapManager = gameContentManager.getMapManager();
        StateManager stateManager = gameContentManager.getStateManager();
        FlagManager flagManager = gameContentManager.getFlagManager();
        
        System.out.println("\n=== CURRENT GAME INFO ===");
        System.out.println("Map: " + mapManager.getCurrentMapID());
        System.out.println("Current state: " + stateManager.getState());
        System.out.println("Player position: (" + player.getMapX() + ", " + player.getMapY() + ")");
        System.out.println("Player direction: " + player.getCurrentDirection());
        System.out.println("Active flags: " + flagManager.getFlags().size());
        System.out.println("NPCs: " + gameContentManager.getNpcManager().getNPCs().size());
        System.out.println("======================\n");
    }
    
    private void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    public void shutdown() {
        isRunning.set(false);
        if (consoleThread != null) {
            consoleThread.interrupt();
        }
        scanner.close();
    }
    
    public boolean isHyperSpeed() {
        return isHyperSpeed;
    }
}
