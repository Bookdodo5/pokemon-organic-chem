package tile;

public class TransitionInitializer {

    private static MapManager mapManager;
    private static String mapName;

    public static void initializeTransitions(MapManager mapManager) {
        TransitionInitializer.mapManager = mapManager;
        initializePorbitalTown();
        initializeMethanopolis();
    }

    public static void init(int x, int y, String from, int x2, int y2, String to) {
        String fullNameFrom = mapName + "__" + from;
        String fullNameTo = mapName + "__" + to;
        if(from.equals("")) fullNameFrom = mapName;
        if(to.equals("")) fullNameTo = mapName;
        mapManager.initializeTransition(x, y, fullNameFrom, x2, y2, fullNameTo);
    }

    private static void initializePorbitalTown() {
        mapName = "porbital_town";
        
        init(12, 13, "", 4, 9, "room");
        init(4, 10, "room", 12, 14, "");
    
        init(19, 13, "", 4, 9, "house1_f1");
        init(4, 10, "house1_f1", 19, 14, "");
        init(11, 3, "house1_f1", 10, 3, "house1_f2");
        init(9, 3, "house1_f2", 10, 3, "house1_f1");
        
        init(27, 13, "", 4, 9, "house2_f1");
        init(4, 10, "house2_f1", 27, 14, "");
        init(11, 3, "house2_f1", 10, 3, "house2_f2");
        init(9, 3, "house2_f2", 10, 3, "house2_f1");

        init(20, 21, "", 7, 9, "townhall");
        init(7, 10, "townhall", 20, 22, "");

        init(28, 21, "", 5, 7, "workspace");
        init(5, 8, "workspace", 28, 22, "");
    }

    private static void initializeMethanopolis() {
        mapName = "methanopolis";

        init(11, 16, "", 3, 8, "apartment1_f1");
        init(3, 9, "apartment1_f1", 11, 17, "");

        init(27, 38, "", 3, 8, "apartment2_f1");
        init(3, 9, "apartment2_f1", 27, 39, "");

        init(20, 16, "", 8, 9, "house1");
        init(8, 10, "house1", 20, 17, "");

        init(27, 46, "", 3, 9, "house2");
        init(3, 10, "house2", 27, 47, "");

        init(38, 27, "", 8, 9, "pokecenter_f1");
        init(8, 10, "pokecenter_f1", 38, 28, "");
        init(2, 8, "pokecenter_f1", 3, 8, "pokecenter_f2");
        init(2, 8, "pokecenter_f2", 3, 8, "pokecenter_f1");
        init(14, 8, "pokecenter_f1", 13, 8, "pokecenter_f2");
        init(14, 8, "pokecenter_f2", 13, 8, "pokecenter_f1");

        init(41, 21, "", 4, 11, "pokemart");
        init(4, 12, "pokemart", 41, 22, "");

        init(16, 24, "", 8, 9, "townhall_f1");
        init(8, 10, "townhall_f1", 16, 25, "");
        init(2, 4, "townhall_f1", 3, 4, "townhall_f2");
        init(2, 4, "townhall_f2", 3, 4, "townhall_f1");
        init(14, 4, "townhall_f1", 13, 4, "townhall_f2");
        init(14, 4, "townhall_f2", 13, 4, "townhall_f1");

        init(31, 27, "", 4, 9, "botanist");
        init(4, 10, "botanist", 31, 28, "");

        init(27, 16, "", 4, 7, "workshop1");
        init(4, 8, "workshop1", 27, 17, "");

        init(37, 21, "", 3, 7, "workshop2");
        init(3, 8, "workshop2", 37, 22, "");

        init(15, 51, "", 4, 7, "workshop3");
        init(4, 8, "workshop3", 15, 52, "");
        init(20, 51, "", 15, 7, "workshop3");
        init(15, 8, "workshop3", 20, 52, "");

        init(27, 51, "", 4, 7, "workshop4");
        init(4, 8, "workshop4", 27, 52, "");
    }

}
