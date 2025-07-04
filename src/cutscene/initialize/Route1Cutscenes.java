package cutscene.initialize;

import cutscene.Cutscene;
import cutscene.CutsceneBuilder;
import cutscene.Emotes;
import cutscene.template.ManiacQuizTemplate;
import dialogue.Dialogue;
import dialogue.DialogueOption;
import entity.FacingDirections;
import entity.NPC;
import entity.NPCManager;
import entity.Player;
import gamestates.CameraManager;
import gamestates.states.OverworldState;
import java.util.List;
import java.util.Map;

public class Route1Cutscenes {
    public static void initialize(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager, CameraManager cameraManager, Player player, OverworldState overworldState) {
        initializeManiacQuiz(cutscenes, npcManager, cameraManager, player, overworldState);
    }

    private static void initializeManiacQuiz(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager, CameraManager cameraManager, Player player, OverworldState overworldState) {
        NPC maniac1 = npcManager.getNPC("Maniac1");
        NPC maniac2 = npcManager.getNPC("Maniac2");
        NPC maniac3 = npcManager.getNPC("Maniac3");
        NPC maniac4 = npcManager.getNPC("Maniac4");
        NPC maniac5 = npcManager.getNPC("Maniac5");
        NPC maniac6 = npcManager.getNPC("Maniac6");
        NPC maniac7 = npcManager.getNPC("Maniac7");
        NPC maniac8 = npcManager.getNPC("Maniac8");

        ManiacQuizTemplate.addManiacQuiz(
            cutscenes, maniac1, player, overworldState, cameraManager, 3,
            13, 4, "porbital_town__workspace", FacingDirections.DOWN,
            new CutsceneBuilder() // quiz
                .music("BattleTrainer")
                .shout("MANIAC", "IF YOU HAVE AN ALCHEMIST'S DECK, YOU'RE READY TO TAKE A QUIZ!", cameraManager)
                .shout("MANIAC", "YOU CANNOT SAY NO TO MY CHALLENGE!", cameraManager)
                .wait(60)
                .showImage("/images/DIRECTOR_1_box_1.png")
                .react(maniac1, cameraManager, Emotes.QUESTION)
                .speak(new Dialogue(new String[] {
                    "QUESTION: WHICH ONE OF THEM IS THE MOST SIGMA OF ALL MOLECULES???"
                }, "MANIAC",
                ManiacQuizTemplate.getOptions(maniac1, 2,
                    "Benzene",
                    "Ethylene",
                    "Decane",
                    "Bromine",
                    "Methane"
                )))
                .buildActions(),
            new CutsceneBuilder() // right
                .waitEmote(maniac1, cameraManager, 60)
                .music("BattleVictoryTrainer")
                .wait(30)
                .shout("MANIAC", "YOU'RE SO SIGMA AND FLEXIBLE!", cameraManager)
                .shout("MANIAC", "I'M SO PROUD OF YOU!", cameraManager)
                .buildActions(),
            new CutsceneBuilder() // wrong
                .waitEmote(maniac1, cameraManager, 60)
                .music("Rickroll")
                .wait(60)
                .speak("MANIAC",
                    "You know what?",
                    "You suck at chemistry.",
                    "You're not sigma at all. Rather, you're beta.",
                    "Maybe go read some books..."
                )
                .buildActions(),
            "You're the sigmaest of all sigma! I respect you!" // talk
        );

        ManiacQuizTemplate.addManiacQuiz(
            cutscenes, maniac2, player, overworldState, cameraManager, 2,
            13, 4, "porbital_town__workspace", FacingDirections.DOWN,
            new CutsceneBuilder() // quiz
                .music("BattleTrainer")
                .shout("MANIAC", "HEY!!!", cameraManager)
                .speak("MANIAC",
                    "You wanna know my secrets?",
                    "I'm actually the winner of the eating contest olympics!",
                    "If you aspire to be the best, you'll have to beat me at eating too!"
                )
                .wait(60)
                .showImage("/images/DIRECTOR_1_box_1.png")
                .react(maniac2, cameraManager, Emotes.QUESTION)
                .speak(new Dialogue(new String[] {
                    "QUESTION: Which molecule can eat the most HYDROGEN without being saturated?"
                }, "MANIAC",
                ManiacQuizTemplate.getOptions(maniac2, 4,
                    "1-Nonyne",
                    "Quadratic Acid",
                    "Penguinone",
                    "Angelic Acid",
                    "Benzyl Benzene"
                )))
                .buildActions(),
            new CutsceneBuilder() // right
                .waitEmote(maniac2, cameraManager, 60)
                .music("BattleVictoryTrainer")
                .wait(30)
                .shout("MANIAC", "YES! YOU'RE THE CHAMPION!", cameraManager)
                .shout("MANIAC", "THAT GUY HAS 2 BENZENE RINGS, SO IT CAN EAT 6 HYDROGEN GAS MOLECULES!", cameraManager)
                .buildActions(),
            new CutsceneBuilder() // wrong
                .waitEmote(maniac2, cameraManager, 60)
                .music("Rickroll")
                .wait(60)
                .speak("MANIAC",
                    "WRONG! You're not even close!",
                    "Go practice some more!"
                )
                .buildActions(),
            "You're the winner of the eating contest olympics! Next year, I'll be the one eating you!" // talk
        );

        ManiacQuizTemplate.addManiacQuiz(
            cutscenes, maniac3, player, overworldState, cameraManager, 5,
            13, 4, "porbital_town__workspace", FacingDirections.DOWN,
            new CutsceneBuilder() // quiz
                .music("BattleTrainer")
                .react(maniac3, cameraManager, Emotes.ANGRY)
                .shout("MANIAC", "YOU ARE CONSCRIPTED INTO THE ARMY FROM THIS POINT ON!", cameraManager)
                .shout("MANIAC", "I'M YOUR COMMANDER! GO FIGHT THE HALOGENS!!!", cameraManager)
                .wait(30)
                .react(player, cameraManager, Emotes.TERRIFIED)
                .wait(60)
                .showImage("/images/DIRECTOR_1_box_1.png")
                .react(maniac3, cameraManager, Emotes.QUESTION)
                .speak(new Dialogue(new String[] {
                    "QUESTION: Which innocent carbon atoms is in most trouble?"
                }, "MANIAC",
                ManiacQuizTemplate.getOptions(maniac3, 0,
                    "A",
                    "B",
                    "C",
                    "D",
                    "E"
                )))
                .buildActions(),
            new CutsceneBuilder() // right
                .waitEmote(maniac3, cameraManager, 60)
                .music("BattleVictoryTrainer")
                .wait(30)
                .speak("MANIAC",
                    "Woah... What is this calm and collected response to the chaotic ambush...?",
                    "YOU are even more capable than me!!!"
                )
                .shout("MANIAC", "YOU SHOULD BE THE NEW MINISTER OF DEFENSE!", cameraManager)
                .shout("MANIAC", "YOU'RE THE ULTIMATE ASSASSIN!", cameraManager)
                .waitEmote(maniac3, cameraManager, 60)
                .shout("MANIAC", "SORRY FOR INTERRUPTING YOU, MY MASTER!!!", cameraManager)
                .buildActions(),
            new CutsceneBuilder() // wrong
                .waitEmote(maniac3, cameraManager, 60)
                .music("Rickroll")
                .wait(60)
                .speak("MANIAC",
                    "That's a rookie mistake you made, PLAYER!",
                    "...",
                    "You know what? I'm not even mad.",
                    "Go study some more before I can recruit you again!"
                )
                .buildActions(),
            "You understand the destructive power of halogens! We're war correspondents together!" // talk
        );

        ManiacQuizTemplate.addManiacQuiz(
            cutscenes, maniac4, player, overworldState, cameraManager, 3,
            13, 4, "porbital_town__workspace", FacingDirections.DOWN,
            new CutsceneBuilder() // quiz
                .music("BattleTrainer")
                .waitEmote(maniac4, cameraManager, 60)
                .speak("MANIAC",
                    "Uhh... I'm from the AROMATICA MAGICAL LAND!",
                    "I'm here to spread the words of the king to the world!",
                    "However... If you know it already, I won't...",
                    "Can you show me what you know?"
                )
                .wait(60)
                .showImage("/images/DIRECTOR_1_box_1.png")
                .react(maniac4, cameraManager, Emotes.QUESTION)
                .speak(new Dialogue(new String[] {
                    "QUESTION: In the communist city of \"THE SIX RINGS\", how many electrons are shared among all citizens?"
                }, "MANIAC",
                ManiacQuizTemplate.getOptions(maniac4, 1,
                    "4 electrons",
                    "6 electrons",
                    "8 electrons",
                    "12 electrons",
                    "24 electrons"
                )))
                .buildActions(),
            new CutsceneBuilder() // right
                .waitEmote(maniac4, cameraManager, 60)
                .music("BattleVictoryTrainer")
                .wait(30)
                .react(maniac4, cameraManager, Emotes.FRIENDLY)
                .speak("MANIAC",
                    "YES! 6 electrons in 3 pi bonds are shared among all citizens!",
                    "I'm impressed... I don't need you to get involved in the politics of Aromatica anymore...",
                    "Let me find other people..."
                )
                .buildActions(),
            new CutsceneBuilder() // wrong
                .waitEmote(maniac4, cameraManager, 60)
                .music("Rickroll")
                .wait(60)
                .react(maniac4, cameraManager, Emotes.SAD)
                .speak("MANIAC",
                    "That's not right...",
                    "You know what? I'll just pretend you're not here...",
                    "Please go read the guidebook on the 4th bookshelf and learn more...",
                    "I really can't let you pass like this. The KING will be very disappointed in me..."
                )
                .buildActions(),
            "I can't criticize or comment on anything...\nI can't even give you information..." // talk
        );

        ManiacQuizTemplate.addManiacQuiz(
            cutscenes, maniac5, player, overworldState, cameraManager, 3,
            13, 4, "porbital_town__workspace", FacingDirections.DOWN,
            new CutsceneBuilder() // quiz
                .music("BattleTrainer")
                .shout("MANIAC", "...", cameraManager)
                .wait(60)
                .shout("MANIAC", "...", cameraManager)
                .wait(60)
                .showImage("/images/DIRECTOR_1_box_1.png")
                .react(maniac5, cameraManager, Emotes.QUESTION)
                .speak(new Dialogue(new String[] {
                    "QUESTION: I... want more... alcohol..."
                }, "MANIAC",
                ManiacQuizTemplate.getOptions(maniac5, 1,
                    "Give Methanol",
                    "Give Ethanol",
                    "Give Isopropanol",
                    "Give Diethyl Ether",
                    "Give Hydrogen Peroxide"
                )))
                .buildActions(),
            new CutsceneBuilder() // right
                .waitEmote(maniac5, cameraManager, 60)
                .music("BattleVictoryTrainer")
                .wait(30)
                .react(maniac5, cameraManager, Emotes.FRIENDLY)
                .speak("MANIAC",
                    "OHHHH!!!",
                    "Thank you...",
                    "(*Drinking sounds*)",
                    "(*Drinking sounds*)",
                    "(*Drinking sounds*)"
                )
                .buildActions(),
            new CutsceneBuilder() // wrong
                .waitEmote(maniac5, cameraManager, 60)
                .music("Rickroll")
                .wait(60)
                .speak("MANIAC",
                    "...",
                    "...",
                    "...",
                    "You wanna kill me?"
                )
                .buildActions(),
            "Thaaank ya fer the alcohooooll~!!" // talk
        );

        ManiacQuizTemplate.addManiacQuiz(
            cutscenes, maniac6, player, overworldState, cameraManager, 3,
            13, 4, "porbital_town__workspace", FacingDirections.DOWN,
            new CutsceneBuilder() // quiz
                .music("BattleTrainer")
                .shout("MANIAC", "YOU ONLY HAVE 10 MINUTES LEFT TO FINISH THIS DISH!", cameraManager)
                .react(maniac6, cameraManager, Emotes.ANGRY)
                .shout("MANIAC", "WHY HAVEN'T YOU BAKED YOUR CAKE YET???", cameraManager)
                .wait(60)
                .showImage("/images/DIRECTOR_1_box_1.png")
                .react(maniac6, cameraManager, Emotes.QUESTION)
                .speak(new Dialogue(new String[] {
                    "QUESTION: Which sugar is a ALDOPENTOSE?"
                }, "MANIAC",
                ManiacQuizTemplate.getOptions(maniac6, 4,
                    "Glucose",
                    "Fructose",
                    "Lactose",
                    "Ribulose",
                    "Ribose"
                )))
                .buildActions(),
            new CutsceneBuilder() // right
                .waitEmote(maniac6, cameraManager, 60)
                .music("BattleVictoryTrainer")
                .wait(30)
                .react(maniac6, cameraManager, Emotes.FRIENDLY)
                .speak("MANIAC",
                    "Yes, RIBOSE is the aldopentose!",
                    "It has an aldehyde group on the first carbon!"
                )
                .waitEmote(maniac6, cameraManager, 60)
                .shout("MANIAC", "QUICK!!! CONTINUE COOKING!", cameraManager)
                .shout("MANIAC", "YOU DON'T HAVE MUCH TIME LEFT!", cameraManager)
                .buildActions(),
            new CutsceneBuilder() // wrong
                .waitEmote(maniac6, cameraManager, 60)
                .music("Rickroll")
                .wait(60)
                .react(maniac6, cameraManager, Emotes.ANGRY)
                .speak("MANIAC",
                    "I'm disappointed...",
                    "You're not a chef. Go cry in the corner..."
                )
                .buildActions(),
            "You have to serve your cake to the customer! Do it quickly!!!" // talk
        );

        ManiacQuizTemplate.addManiacQuiz(
            cutscenes, maniac7, player, overworldState, cameraManager, 4,
            13, 4, "porbital_town__workspace", FacingDirections.DOWN,
            new CutsceneBuilder() // quiz
                .music("BattleTrainer")
                .react(maniac7, cameraManager, Emotes.ANGRY)
                .speak("MANIAC",
                    "Hehehe...",
                    "Would you like to be my research subject?",
                    "I'm conducting an acidity research."
                )
                .wait(60)
                .showImage("/images/DIRECTOR_1_box_1.png")
                .react(maniac7, cameraManager, Emotes.QUESTION)
                .speak(new Dialogue(new String[] {
                    "QUESTION: Subject subject on the ground, Who is the most ACIDIC in this round?"
                }, "MANIAC",
                ManiacQuizTemplate.getOptions(maniac7, 0,
                    "Proprionic Acid",
                    "3-chloropropionic Acid",
                    "2,2-dichloropropionic Acid",
                    "Methyl Proprionate",
                    "Water"
                )))
                .buildActions(),
            new CutsceneBuilder() // right
                .waitEmote(maniac7, cameraManager, 60)
                .music("BattleVictoryTrainer")
                .wait(30)
                .speak(new Dialogue(new String[] {
                    "...",
                    "Okay. You're smart.",
                    "I can't throw acids at you anymore.",
                    "Be my assistant, will you?"
                }, "MANIAC",
                new DialogueOption("No", new Dialogue(new String[] {
                    "Ok."
                }, "MANIAC"))))
                .buildActions(),
            new CutsceneBuilder() // wrong
                .waitEmote(maniac7, cameraManager, 60)
                .music("Rickroll")
                .wait(60)
                .react(maniac7, cameraManager, Emotes.ANGRY)
                .speak("MANIAC",
                    "...",
                    "...",
                    "...",
                    "...",
                    "Go read."
                )
                .buildActions(),
            "Be my assistant, please..." // talk
        );

        ManiacQuizTemplate.addManiacQuiz(
            cutscenes, maniac8, player, overworldState, cameraManager, 3,
            13, 4, "porbital_town__workspace", FacingDirections.DOWN,
            new CutsceneBuilder() // quiz
                .music("BattleTrainer")
                .shout("MANIAC", "HAVE YOU EVER SEEN ANY ROCK BABY SONG???", cameraManager)
                .react(maniac8, cameraManager, Emotes.MUSIC)
                .shout("MANIAC", "YOU WILL HERE ME!!!", cameraManager)
                .wait(60)
                .showImage("/images/DIRECTOR_1_box_1.png")
                .react(maniac8, cameraManager, Emotes.QUESTION)
                .speak(new Dialogue(new String[] {
                    "QUESTION: Which compound smells like FISH and DEATH?"
                }, "MANIAC",
                ManiacQuizTemplate.getOptions(maniac8, 1,
                    "Acetamide",
                    "Methylamine",
                    "Nickel",
                    "Nile River",
                    "Nitrocharge"
                )))
                .buildActions(),
            new CutsceneBuilder() // right
                .waitEmote(maniac8, cameraManager, 60)
                .music("BattleVictoryTrainer")
                .wait(30)
                .speak("MANIAC",
                    "I wanna eat dead fish."
                )
                .buildActions(),
            new CutsceneBuilder() // wrong
                .waitEmote(maniac8, cameraManager, 60)
                .music("Rickroll")
                .wait(60)
                .react(maniac8, cameraManager, Emotes.SAD)
                .speak("MANIAC",
                    "Wrong...",
                    "You need some baby in your life.",
                    "I'm tired of this song. Bye bye."
                )
                .buildActions(),
            "Babies are like FISH, in that they're never satisfied with the lullabies they're given." // talk
        );
    }
}
