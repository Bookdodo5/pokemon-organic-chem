package cutscene.initialize.methanopolis;

import cutscene.Cutscene;
import cutscene.CutsceneBuilder;
import cutscene.Emotes;
import cutscene.template.CutsceneTemplate;
import cutscene.template.ElevatorTemplate;
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
import pokedex.PlayerDeckManager;

public class MethanopolisCutscenes extends CutsceneTemplate {

    public static void initialize(Map<String, List<Cutscene>> cutscenes, OverworldState overworldState, NPCManager npcManager, CameraManager cameraManager, Player player, PlayerDeckManager playerDeckManager) {
        ElevatorTemplate.addElevator(
                overworldState, cutscenes,
                "methanopolis", "apartment1", 11, 3, 3
        );
        ElevatorTemplate.addElevator(
                overworldState, cutscenes,
                "methanopolis", "apartment2", 11, 3, 5
        );
        
        MethanopolisTalks.initialize(cutscenes, npcManager, cameraManager, player, playerDeckManager);
        MethanopolisObjects.initialize(cutscenes, cameraManager, player, playerDeckManager);
    
        NPC chlorophyll = npcManager.getNPC("Chlorophyll");
        NPC yuuki = npcManager.getNPC("Yuuki");
        NPC kusari = npcManager.getNPC("Kusari");
        chlorophyllArrival(cutscenes, chlorophyll, yuuki, kusari, cameraManager, player);
    }

    private static void chlorophyllArrival(Map<String, List<Cutscene>> cutscenes, NPC chlorophyll, NPC yuuki, NPC kusari, CameraManager cameraManager, Player player) {
/*
* -----------------------------------------------------------------------------
* CUTSCENE: Methanopolis - Chlorophyll and Friends Arrival
* Location: Methanopolis Entrance
* -----------------------------------------------------------------------------
? FLAGS USED:
~   - CHLOROPHYLL_3: The group has arrived in Methanopolis.
~   - CHLOROPHYLL_4: The arrival cutscene has played.
~   - ETHYLENE_FOUND: The player has found the ethylene supply.
* -----------------------------------------------------------------------------
! FLOW:
^   1. Only runs if CHLOROPHYLL_3 is set but CHLOROPHYLL_ARRIVAL is NOT set.
^   2. Chlorophyll and friends are exhausted from their "flight" to Methanopolis.
^   3. They explain they can't move and need the player to find the lab with ethylene.
^   4. Player reacts to this ridiculous plot development.
^   5. After the scene, CHLOROPHYLL_ARRIVAL is set.
* -----------------------------------------------------------------------------
*/
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("CHLOROPHYLL_3")
            .forbid("CHLOROPHYLL_4")
            .wait(60)
            .faceTowards(chlorophyll, player)
            .faceTowards(kusari, player)
            .faceTowards(yuuki, player)
            .wait(30)
            .parallel(new CutsceneBuilder()
                .react(chlorophyll, cameraManager, Emotes.SAD)
                .react(yuuki, cameraManager, Emotes.SAD)
                .react(kusari, cameraManager, Emotes.SAD)
                .buildActions()
            )
            .wait(30)
            .moveXthenY(player, 25, 32)
            .speak("CHLOROPHYLL",
                "Phew...",
                "That was... exhausting...",
                "I used up almost all of my PHOTOSYNTHETIC ENERGY to get us here..."
            )
            .wait(60)
            .speak("THINKING",
                "...",
                "By \"us\", she doesn't include you...",
                "..."
            )
            .wait(30)
            .faceTowards(yuuki, player)
            .faceTowards(player, yuuki)
            .parallel(new CutsceneBuilder()
                .sfx("BattleDamageSuper")
                .react(yuuki, cameraManager, Emotes.ANGRY)
                .shout("YUUKI", "WHAT DID YOU DO TO ME, CHLOROPHYLL?!", cameraManager)
                .buildActions()
            )
            .shout("YUUKI", "WHY CAN'T I...", cameraManager)
            .shout("YUUKI", "WHY... ARRRGGGH", cameraManager)
            .react(yuuki, cameraManager, Emotes.TERRIFIED)
            .speak("YUUKI",
                "I... I can't feel my legs...",
                "What kind of magic was that?!",
                "I'm paralyzed..."
            )
            .waitEmote(yuuki, cameraManager, 60)
            .shout("YUUKI", "OH HEY PLAYER!!!", cameraManager)
            .sfx("BattleDamageSuper")
            .camShake(cameraManager, 30)
            .shout("YUUKI", "ARRRRRRRRRHHHH", cameraManager)
            .wait(60)
            .speak("THINKING",
                "That... looks serious...",
                "I'm glad CHLOROPHYLL didn't include me in her \"us\"...",
                "I don't want my 10 days life to be shorter by being paralyzed."
            )
            .wait(30)
            .faceTowards(kusari, player)
            .react(kusari, cameraManager, Emotes.QUESTION)
            .faceTowards(player, kusari)
            .speak("KUSARI",
                "I think I know why my dad has an eye out for you now.",
                "I mean, you just got that ALCHEMIST'S DECK but it already likes you.",
                "You might be able to befriend any kind of chemicals easily",
                "Well, it's time to head back to the Lab."
            )
            .wait(60)
            .speak("THINKING",
                "Yeah... He's still the same.",
                "Maybe he's right this time. Maybe it's time to go to the lab and get ETHYLENE and go back quickly.",
                "I just want to know why I'm here and go sleep..."
            )
            .wait(30)
            .faceTowards(chlorophyll, player)
            .faceTowards(player, chlorophyll)
            .react(chlorophyll, cameraManager, Emotes.SAD)
            .speak("CHLOROPHYLL",
                "PLAYER...",
                "I'm so sorry, but we can't move right now.",
                "It was my miscalculation that my PHOTOSYNTHESIS POWER is enough for 4 people flight.",
                "It was actually only enough for 3 people.",
                "And the smooth landing feature can't be activated.",
                "We're completely exhausted from that... flight... thing...",
                "We need to rest here for a while."
            )
            .react(chlorophyll, cameraManager, Emotes.FRIENDLY)
            .speak("CHLOROPHYLL",
                "But don't worry!",
                "The LAB we talked about should be somewhere in this city!",
                "LABs are the red, flat-roof buildings. There are 5 LABs in total, but I forgot which one is the correct one...",
                "You can go find it and get the ETHYLENE for YUUKII!",
                "Though, you need to go by yourself.",
                "We'll wait here and recover our energy!"
            )
            .wait(30)
            .faceTowards(player, yuuki)
            .react(yuuki, cameraManager, Emotes.FRIENDLY)
            .speak("YUUKI",
                "Yeah! My friend!",
                "You're the only one who can help us now!",
                "We believe in you!"
            )
            .wait(30)
            .faceTowards(player, chlorophyll)
            .react(chlorophyll, cameraManager, Emotes.FRIENDLY)
            .speak("CHLOROPHYLL",
                "PLAYER...",
                "You are the only one who can save us.",
                "We believe in you."
            )
            .wait(60)
            .waitEmote(player, cameraManager, 60)
            .speak(new Dialogue(new String[]{
                "..."
            }, "THINKING",
            new DialogueOption("I'll do it.", new Dialogue(new String[]{
                "Woah..."
            }, "CHLOROPHYLL")),
            new DialogueOption("Of course! You can trust me with that.", new Dialogue(new String[]{
                "Woah..."
            }, "CHLOROPHYLL")),
            new DialogueOption("You can always rely on this Pokemon Master!", new Dialogue(new String[]{
                "Woah... You are probably even more delusional than us."
            }, "CHLOROPHYLL"))))
            .react(chlorophyll, cameraManager, Emotes.MUSIC)
            .speak("CHLOROPHYLL",
                "I'm so glad you're here, PLAYER!",
                "We'll be waiting here for you."
            )
            .wait(30)
            .react(player, cameraManager, Emotes.MUSIC)
            .face(player, FacingDirections.DOWN)
            .wait(30)
            .execute(()->player.setRunning())
            .move(player, 25, 38)
            .execute(()->player.setWalking())
            .move(player, 25, 41)
            .waitEmote(player, cameraManager, 60)
            .wait(30)
            .react(player, cameraManager, Emotes.ANGRY)
            .speak("THINKING",
                "...",
                "Wait a minute...",
                "This is completely ridiculous.",
                "This has to be the laziest plot device ever.",
                "Instead of actually helping, or telling more stories, or even just doing something...",
                "They're just going to stand there while you need to do all the work.",
                "What kind of game is this?",
                "Yeah, it's an RPG game, and you need to be able to play, but this is too much.",
                "..."
            )
            .wait(60)
            .react(player, cameraManager, Emotes.SAD)
            .speak("THINKING",
                "Fine.",
                "You only have 10 days to live.",
                "You have no time to criticize the story. Just finishing the game is probably hard enough.",
                "You'll find the lab.",
                "You'll get the ethylene.",
                "You'll be the only one who actually does anything useful.",
                "And you'll save the world."
            )
            .setFlag("CHLOROPHYLL_4")
            .buildCutscene(),
            getKeyLocation(22, 35, "methanopolis"),
            getKeyLocation(23, 35, "methanopolis"),
            getKeyLocation(24, 35, "methanopolis"),
            getKeyLocation(25, 35, "methanopolis")
        );

        //* CUTSCENE: Methanopolis - Chlorophyll and Friends Waiting
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("CHLOROPHYLL_4")
            .forbid("ETHYLENE_FOUND")
            .faceTowards(chlorophyll, player)
            .react(chlorophyll, cameraManager, Emotes.SAD)
            .speak("CHLOROPHYLL",
                "We're still recovering...",
                "Please find the lab and get the ETHYLENE for us!",
                "...",
                "OH!!! How about this?",
                "How about you go meet my MOTHER and hear more molecule fun facts?",
                "She's in front of this house with HUGGEEEE forest as a garden.",
                "THAT might energize you more and you'll be able to find the lab FASTER.",
                "Just like how CHOLIC ACID in bile acts as SURFACTANT and breaks down fat to help you absorb them FASTER!",
                "...",
                "Umm... Bile only exists in animals, not plants...",
                "I think I'm really actually genuinely exhausted..."
            )
            .buildCutscene(),
            getKeyNPC(chlorophyll)
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("CHLOROPHYLL_4")
            .forbid("ETHYLENE_FOUND")
            .faceTowards(yuuki, player)
            .react(yuuki, cameraManager, Emotes.SAD)
            .speak("YUUKI",
                "My legs are still numb..."
            )
            .wait(60)
            .shout("YUUKI", "MY LEGS ARE NUMBBBB", cameraManager)
            .buildCutscene(),
            getKeyNPC(yuuki)
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("CHLOROPHYLL_4")
            .forbid("ETHYLENE_FOUND")
            .faceTowards(kusari, player)
            .react(kusari, cameraManager, Emotes.SAD)
            .speak("KUSARI",
                "I think I know why my dad has an eye out for you now.",
                "I mean, you just got that ALCHEMIST'S DECK but it already likes you.",
                "You might be able to befriend any kind of chemicals easily",
                "Well, it's time to head back to the Lab."
            )
            .buildCutscene(),
            getKeyNPC(kusari)
        );
    }
}
