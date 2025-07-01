package cutscene.initialize.porbital;

import cutscene.Cutscene;
import cutscene.CutsceneBuilder;
import cutscene.Emotes;
import cutscene.template.CutsceneTemplate;
import dialogue.Dialogue;
import dialogue.DialogueOption;
import entity.NPC;
import entity.NPCManager;
import entity.Player;
import gamestates.CameraManager;
import gamestates.FlagManager;
import java.util.List;
import java.util.Map;

public class PorbitalTownTalks extends CutsceneTemplate {
    public static void initialize(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager, CameraManager cameraManager, Player player) {
        NPC yuuki = npcManager.getNPC("Yuuki");
        NPC professorDecane = npcManager.getNPC("ProfDecane");
        NPC molecularGastronomist = npcManager.getNPC("MolecularGastronomist");
        NPC psychic = npcManager.getNPC("Psychic");
        NPC PorbitalCop1 = npcManager.getNPC("PorbitalCop1");
        NPC PorbitalCop2 = npcManager.getNPC("PorbitalCop2");
        NPC PorbitalCop3 = npcManager.getNPC("PorbitalCop3");
        NPC chlorophyll = npcManager.getNPC("ChlorophyllGirl");

        staticTalks(cutscenes, molecularGastronomist, psychic, PorbitalCop1, PorbitalCop2, PorbitalCop3, chlorophyll, cameraManager, player, FlagManager.getInstance());
        
        yuukiTalk1(cutscenes, yuuki, cameraManager, player);
        decaneAndYuukiWaitingForChemical(cutscenes, professorDecane, yuuki, cameraManager, player);
    }

    private static void staticTalks(Map<String, List<Cutscene>> cutscenes, NPC molecularGastronomist, NPC psychic, NPC PorbitalCop1, NPC PorbitalCop2, NPC PorbitalCop3, NPC chlorophyll, CameraManager cameraManager, Player player, FlagManager flagManager) {

        //* CUTSCENE: Porbital Town - Molecular Gastronomist
        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag(flagManager, "MOLECULAR GASTRONOMIST_KNOW")
            .faceTowards(molecularGastronomist, player)
            .speak("MOLECULAR GASTRONOMIST",
                "Oh... Ah!",
                "Hello...? I was... just waiting.",
                "The shop owner... they're supposed to be here.",
                "They're advertising in ALKENISTRA that this place sells a high quality Vanillin extract...",
                "I need it for my... cooking...",
                "But... I don't know where they are.",
                "I... I'll just wait a bit longer."
            )
            .buildCutscene(), getKeyNPC(molecularGastronomist));

        //* CUTSCENE: Porbital Town - Psychic
        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag(flagManager, "PSYCHIC_KNOW")
            .faceTowards(psychic, player)
            .speak(new Dialogue( new String[] {
                "The spirits whisper to me... they say... 'Press SPACE to run'.",
                "Oh wait, that's just the programmer forgetting to find a place to put the keybind tutorials.",
                "I'm here to guard the holy TOWNHALL, the place where the mayor of this town lives.",
                "I'm not sure why this is my job instead of those useless cops, but at least I'm doing something.",
                "...",
                "...",
                "Please pay me 500 CHEMS to continue talking.",
                "..."
            },
            "PSYCHIC",
            new DialogueOption("I don't have any CHEMS.", new Dialogue( new String[] {
                "Then go away.\nYou can't take any more of my time."
            }, "PSYCHIC")),
            new DialogueOption("Did you mean POKE DOLLARS???", new Dialogue( new String[] {
                "I'm not sure what that is, but I'm sure it's not what I want.",
                "Go away. I'm not talking to poor people."
            }, "PSYCHIC")),
            new DialogueOption("What's the CHEMS?", new Dialogue( new String[] {
                "CHEMS are the currency of this world.",
                "You can use them to buy items from shops.",
                "I'm not sure why you're asking me this.",
                "Go away. I'm not talking to poor people."
            }, "PSYCHIC"))
            ))
            .buildCutscene(), getKeyNPC(psychic));
        
        //* CUTSCENE: Porbital Town - Porbital Cop 1
        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag(flagManager, "PORBITAL COP_KNOW")
            .faceTowards(PorbitalCop1, player)
            .speak("PORBITAL COP",
                "Hey there.",
                "Just doing my rounds.\nWe're supposed to walk along this path until the commander gets back.",
                "I need to keep this spot secure and no terrorists are allowed to pass through.",
                "Are you a terrorist?",
                "...",
                "...",
                "...",
                "I don't think you are. You're free to go."
            )
            .buildCutscene(), getKeyNPC(PorbitalCop1));

        //* CUTSCENE: Porbital Town - Porbital Cop 2
        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag(flagManager, "PORBITAL COP_KNOW")
            .faceTowards(PorbitalCop2, player)
            .speak("PORBITAL COP",
                "Hello, citizen of the ALKANE GRASSLANDS.",
                "I was sent here from a place far away called AROMATICA MAGICAL LAND.",
                "Of course, I don't know why I'm here.",
                "The pay's good, though.",
                "As long as I get paid, I can even guard a weak noble gas for all I care.",
                "...",
                "Oh! By the way, I used to be a magician.",
                "See this!"
            )
            .wait(30)
            .sfx("BattleDamageWeak")
            .animation("electric1", () -> PorbitalCop2.getMapX(), () -> PorbitalCop2.getMapY(), 1, cameraManager)
            .wait(20)
            .react(PorbitalCop2, cameraManager, Emotes.FRIENDLY)
            .wait(20)
            .speak("PORBITAL COP", "How was that?")
            .buildCutscene(), getKeyNPC(PorbitalCop2));

        //* CUTSCENE: Porbital Town - Porbital Cop 3 (Scammer)
        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag(flagManager, "DISGUISED COP_KNOW")
            .faceTowards(PorbitalCop3, player)
            .speak("DISGUISED COP",
                "No. I can't talk.",
                "I'm in the middle of a high-stakes surveillance operation.",
                "This 3x3 square is the most critical strategic point in all of ALKANE GRASSLANDS.",
                "I can't elaborate further. There's security protocols.",
                "Also, if I tell you, I'd be in trouble since I'm not a cop.",
                "Hope you understand."
            )
            .buildCutscene(), getKeyNPC(PorbitalCop3));

        //* CUTSCENE: Porbital Town - Chlorophyll Girl
        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag(flagManager, "CHLOROPHYLL_KNOW")
            .faceTowards(chlorophyll, player)
            .speak("CHLOROPHYLL",
                "Hiiii! Isn't it a WONDERFUL day?! All the leaves are so...",
                "GREENNNNNN!!!!!!!",
                "You know why, right?!",
                "It's..."
            )
            .parallel(
                new CutsceneBuilder()
                    .emote(chlorophyll, 50, Emotes.MUSIC, cameraManager)
                    .sfx("GUIConfirm")
                    .speak("CHLOROPHYLL",
                        "CHLOROPHYLLLLL!",
                        "FUN FACT:\nThere's a magnesium ion at the center of the chlorophyll molecule!!!",
                        "The chlorophyll molecule ABSORBS specific light wavelengths, making leaves GREENNNNNN, and also capturing solar energy!!",
                        "How cool is THAT?!"
                    )
                    .buildActions()
            )
            .buildCutscene(), getKeyNPC(chlorophyll));
        
        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag(flagManager, "CHLOROPHYLL_KNOW")
            .faceTowards(chlorophyll, player)
            .speak("CHLOROPHYLL",
                "Heyyyy!!! Have you ever bitten a tea leaf RAW?",
                "That characteristic nasty, bitter taste. You know what that is???",
                "That's....",
                "TANNINNNNNN!!!!!"
            )
            .parallel(
                new CutsceneBuilder()
                    .emote(chlorophyll, 50, Emotes.MUSIC, cameraManager)
                    .sfx("GUIConfirm")
                    .speak("CHLOROPHYLL",
                        "TANNINNNNNN!!!!!",
                        "FUN FACT:\nThese pesky tannins likes to HUG proteins and make them harder to digest!",
                        "So herbivores go BLEGHHHHH!!!",
                        "And then they leave the plant alone!",
                        "How's THAT????"
                    )
                    .buildActions()
            )
            .buildCutscene(), getKeyNPC(chlorophyll));

        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag(flagManager, "CHLOROPHYLL_KNOW")
            .faceTowards(chlorophyll, player)
            .speak("CHLOROPHYLL",
                "Sniff sniff... Who farted...?",
                "No. Nobody farted.",
                "It's actually..."
            )
            .parallel(
                new CutsceneBuilder()
                    .emote(chlorophyll, 50, Emotes.MUSIC, cameraManager)
                    .sfx("GUIConfirm")
                    .speak("CHLOROPHYLL",
                        "ETHYLENEEEEE!!!!",
                        "It’s a plant hormone... but in GAS FORM. Like, FLOATY GAS BOING BOING BOING!!!",
                        "FUN FACT:\nEthylene controls fruit ripening!",
                        "One apple releases ethylene, and suddenly... the whole basket goes RIPE!!!", 
                        "MAGIC!!!!"
                    )
                    .buildActions()
            )
            .buildCutscene(), getKeyNPC(chlorophyll));
        
        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag(flagManager, "CHLOROPHYLL_KNOW")
            .faceTowards(chlorophyll, player)
            .speak("CHLOROPHYLL",
                "HELLOOOOO!!!! Ever wonder why trees can be so tall... and not flop over like noodles???",
                "That’s because of..."
            )
            .parallel(
                new CutsceneBuilder()
                    .emote(chlorophyll, 50, Emotes.MUSIC, cameraManager)
                    .sfx("GUIConfirm")
                    .speak("CHLOROPHYLL",
                        "LIGNINNNNNN!!!!",
                        "It’s a tough organic polymer that fills the spaces between cellulose in cell walls!!!",
                        "FUN FACT:\nLignin makes trees HARD!!!",
                        "Literally, nobody in the ecosystem can break a tree's lignin!!!",
                        "(Well, apart from fungi and bacterias...)",
                        "BUT ISN'T THAT STRONG AND COOL???",
                        "Don't you want your boyfriend to be like LIGNIN???"
                    )
                    .buildActions()
            )
            .buildCutscene(), getKeyNPC(chlorophyll));  
    }

    private static void yuukiTalk1(Map<String, List<Cutscene>> cutscenes, NPC yuuki, CameraManager cameraManager, Player player) {
        /*
        * -----------------------------------------------------------------------------
        * CUTSCENE: Porbital Town - Yuuki Talk After Introduction
        * Location: Yuuki's House, Floor 2
        * -----------------------------------------------------------------------------
        ? FLAGS USED:
        ~   - YUUKI_1: Yuuki decided to get along with the player.
        * -----------------------------------------------------------------------------
        ! FLOW:
        ^   1. Only runs if YUUKI_1 is set.
        ^   2. Player questions Yuuki, but she sends him downstairs to eat.
        * -----------------------------------------------------------------------------
        */
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("YUUKI_1")
            .forbid("PROF_DECANE_1")
            .faceTowards(yuuki, player)
            .react(yuuki, cameraManager, Emotes.FRIENDLY)
            .speak("YUUKI",
                "We are friends now, right?",
                "It must be tiring to appear at somebody's house all of the sudden.",
                "Well, I guess this is now your house too...",
                "Don't mind me and go eat some puddings downstairs!",
                "I'll be waiting for you here!"
            )
            .buildCutscene(),
            getKeyNPC(yuuki)
        );
    }

    private static void decaneAndYuukiWaitingForChemical(Map<String, List<Cutscene>> cutscenes, NPC professorDecane, NPC yuuki, CameraManager cameraManager, Player player) {
        /*
        * -----------------------------------------------------------------------------
        * CUTSCENE: Porbital Town - Decane and Child Waiting For Chemical
        * Location: Yuuki's House, Floor 1
        * -----------------------------------------------------------------------------
        ? FLAGS USED:
        ~   - PROF_DECANE_1: The professor has explained the situation to the player.
        ~   - PROF_CELLULOSE_1: Player has the chemical. (Assumed to be implemented)
        * -----------------------------------------------------------------------------
        ! FLOW:
        ^   1. Only runs if PROF_DECANE_1 is set but GOT_CELLULOSE_CHEMICAL is NOT set.
        ^   2. Player talks to Professor Decane, she urges them to hurry.
        ^   3. Player talks to Yuuki, she encourages them.
        * -----------------------------------------------------------------------------
        */
        // For Professor Decane
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("PROF_DECANE_1")
            .forbid("PROF_CELLULOSE_1")
            .faceTowards(professorDecane, player)
            .speak("DECANE",
                "Please, you must hurry to Professor Cellulose's house.",
                "I'm not sure how much longer I can keep her occupied."
            )
            .faceTowards(professorDecane, yuuki)
            .buildCutscene(),
            getKeyNPC(professorDecane)
        );

        // For Yuuki
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("PROF_DECANE_1")
            .forbid("PROF_CELLULOSE_1")
            .faceTowards(yuuki, player)
            .react(yuuki, cameraManager, Emotes.FRIENDLY)
            .speak("YUUKI",
                "Hey, my friend! What are you doing?",
                "I'm protecting you from this crazy perverted woman!",
                "Trust me with this side, I'm the best at it!",
                "Hope you enjoy this little town!"
            )
            .faceTowards(yuuki, professorDecane)
            .buildCutscene(),
            getKeyNPC(yuuki)
        );
    }
}
