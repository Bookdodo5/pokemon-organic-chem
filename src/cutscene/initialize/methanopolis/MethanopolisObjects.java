package cutscene.initialize.methanopolis;

import cutscene.Cutscene;
import cutscene.CutsceneBuilder;
import cutscene.template.CutsceneTemplate;
import cutscene.template.OverworldItemTemplate;
import entity.Player;
import gamestates.CameraManager;
import java.util.List;
import java.util.Map;
import pokedex.PlayerDeckManager;

public class MethanopolisObjects extends CutsceneTemplate {
    public static void initialize(Map<String, List<Cutscene>> cutscenes, CameraManager cameraManager, Player player, PlayerDeckManager playerDeckManager) {

/*
* -----------------------------------------------------------------------------
* Location: Methanopolis
* -----------------------------------------------------------------------------
*/

//* Overworld Item

        OverworldItemTemplate.addOverworldItem(
            cutscenes, 9, 14, "methanopolis",
            "Sunny Day", playerDeckManager
        );

        OverworldItemTemplate.addOverworldItem(
            cutscenes, 27, 31, "methanopolis",
            "Rain Dance", playerDeckManager
        );

        OverworldItemTemplate.addOverworldItem(
            cutscenes, 48, 35, "methanopolis",
            "Lubricant", playerDeckManager
        );

//* Object Dialogues

        // Lone bike
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "A lone bike stands here, abandoned.",
                "It's probably been here for a while.",
                "The owner probably entered the cave and never came back."
            ).buildCutscene(),
            getKeyLook(16, 28, "methanopolis")
        );

        // Bike park sign
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "\"BIKE PARK\"",
                "A designated area for people to park their bikes with a low fee of 500 CHEMS.",
                "You still have no idea how CHEMS are worth."
            ).buildCutscene(),
            getKeyLook(43, 27, "methanopolis")
        );

        // Bikes in park
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "It's a bike.",
                "It says that the tires are made of POLYBUTADIENE.",
                "You have no why a bike can say stuff."
            ).buildCutscene(),
            getKeyLook(43, 25, "methanopolis"),
            getKeyLook(44, 26, "methanopolis"),
            getKeyLook(45, 25, "methanopolis")
        );

        // Truck parts
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "It's a large truck.",
                "The driver is asleep and smiling.",
                "This truck is most likely for transporting goods.",
                "There's a remain of green leaves on the truck storage area."
            ).buildCutscene(),
            getKeyLook(45, 28, "methanopolis"),
            getKeyLook(45, 29, "methanopolis"),
            getKeyLook(46, 28, "methanopolis"),
            getKeyLook(47, 28, "methanopolis"),
            getKeyLook(47, 29, "methanopolis")
        );

        // Living dirt bucket
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "A bucket labeled with \"living dirt\".",
                "It's moving slightly...",
                "Really, it's a military-grade weapon. Even with just looking, it's crushing your soul."
            ).buildCutscene(),
            getKeyLook(42, 16, "methanopolis")
        );

        // Town hall objects
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "The town hall's mailbox.",
                "There's an invitation for the prime minister to become a guest speaker at ALKENISTRA OPERA HOUSE.",
                "The topic is \"The flaw of DEMOCRACY in working laboratory environments\".",
                "You're confused about the topic."
            ).buildCutscene(),
            getKeyLook(20, 24, "methanopolis")
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "\"METHANOPOLIS TOWN HALL\"",
                "The official government building of Methanopolis.",
                "It's actually just the prime minister's house and nothing official at all.",
                "But everything is conducted here.",
                "The prime minister must be very down to earth."
            ).buildCutscene(),
            getKeyLook(12, 24, "methanopolis")
        );

        // Gym sign
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "\"METHANOPOLIS GYM\"",
                "Gym...?",
                "Can you get a badge here and go compete in a league?",
                "You must enter or else you won't know the truth forever."
            ).buildCutscene(),
            getKeyLook(16, 16, "methanopolis")
        );

        // Pyrrole town signs
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "\"PYRROLE TOWN - 2 M\"",
                "You know it's not 2 meters, because you can't see another town right in front of you.",
                "You don't know the actual distance."
            ).buildCutscene(),
            getKeyLook(23, 8, "methanopolis"),
            getKeyLook(24, 8, "methanopolis")
        );

        // Flower baskets in shop
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "A beautiful flower basket in an empty shop.",
                "That gorgeous flower is next to an ugly venus flytrap.",
                "But no matter how ugly and withered that venus flytrap is, you realized you're always more shriveled.",
                "You feel bad about yourself.",
                "It's strange that there's no shopkeeper around."
            ).buildCutscene(),
            getKeyLook(27, 11, "methanopolis"),
            getKeyLook(28, 11, "methanopolis")
        );

        // Metal pipe structures
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
            "A weird metal pipe structure.",
            "It looks like some kind of industrial equipment.",
            "Or maybe it's an installation art.",
            "Or...",
            "It might just be a pile of trash."
            ).buildCutscene(),
            getKeyLook(31, 11, "methanopolis"),
            getKeyLook(32, 11, "methanopolis"),
            getKeyLook(33, 11, "methanopolis"),
            getKeyLook(34, 11, "methanopolis")
        );
    }
}
