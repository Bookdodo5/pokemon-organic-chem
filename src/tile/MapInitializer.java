package tile;

public class MapInitializer {

    public static MapManager mapManager;
    public static String mapName;
    public static int interiorX = 100000;

    public static void initializeMaps(MapManager mapManager) {
        MapInitializer.mapManager = mapManager;
        initializePorbitalTown();
        initializeMethanopolis();
        initializeHallogueTown();
        initializePyrroleTown();
        initializeRoute1();
        initializeRoute2();
        initializeRoute3();
    }

    private static void initMap(String name, String tile, String music, int x, int y) {
        System.out.println("init: " + name);
        mapName = name;
        mapManager.initializeMap(name, tile, music, x, y);
    }

    private static void initInterior(String name, String tile, String music) {
        System.out.println("init: " + name); 
        String fullName = mapName + "__" + name;
        mapManager.initializeMap(fullName, tile, music, interiorX, interiorX);
        interiorX += 10000;
    }

    private static void initInterior(String name, String music) {
        initInterior(name, "interiors", music);
    }

    private static void initializePorbitalTown() {
        initMap("porbital_town", "essentials", "Lappet", 0, 0);
        
        initInterior("room", "Safari");
        initInterior("house1_f1", "Lappet");
        initInterior("house1_f2", "Lappet");
        initInterior("house2_f1", "Lab");
        initInterior("house2_f2", "Lab");
        initInterior("townhall", "Tiall");
        initInterior("workspace", "Lappet");
    }

    private static void initializeRoute1() {
        initMap("route1", "essentials", "Route1", 1664, -1024);
    }

    private static void initializeMethanopolis() {
        initMap("methanopolis", "essentials", "Motorcycle", 1344, -2816);
        
        initInterior("pokecenter_f1", "pokecenters", "Center");
        initInterior("pokecenter_f2", "pokecenters", "Center");
        
        initInterior("pokemart", "pokemarts", "Mart");
        
        initInterior("townhall_f1", "interiors", "Tiall");
        initInterior("townhall_f2", "interiors", "Tiall");
        
        initInterior("house1", "interiors", "Gym");
        initInterior("house2", "interiors", "Motorcycle");
        
        initInterior("botanist", "interiors", "Safari");
        
        initInterior("workshop1", "interiors", "Lab");
        initInterior("workshop2", "interiors", "Lab");
        initInterior("workshop3", "interiors", "Lab");
        initInterior("workshop4", "interiors", "Lab");
        
        initInterior("apartment1_f1", "interiors", "Frontier");
        initInterior("apartment1_f2", "interiors", "Motorcycle");
        initInterior("apartment1_f3", "interiors", "Motorcycle");
        
        initInterior("apartment2_f1", "interiors", "Frontier");
        initInterior("apartment2_f2", "interiors", "Motorcycle");
        initInterior("apartment2_f3", "interiors", "Game");
        initInterior("apartment2_f4", "interiors", "Motorcycle");
        initInterior("apartment2_f5", "interiors", "Motorcycle");
    }

    private static void initializeRoute2() {
        initMap("route2", "essentials", "Route2", 3136, -2496);
    }

    private static void initializeHallogueTown() {
        initMap("hallogue_town", "essentials", "Indigo", 5440, -2496);
    }

    private static void initializeRoute3() {
        initMap("route3", "essentials", "Bicycle", 1344, -5120);
    }

    private static void initializePyrroleTown() {
        initMap("pyrrole_town", "essentials", "NewStart", 64, -5728);
    }
}
