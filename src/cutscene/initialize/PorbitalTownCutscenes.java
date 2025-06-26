package cutscene.initialize;

import cutscene.Cutscene;
import cutscene.CutsceneBuilder;
import cutscene.Emotes;
import cutscene.cutsceneAction.*;
import cutscene.template.CutsceneTemplate;
import dialogue.Dialogue;
import dialogue.DialogueOption;
import entity.FacingDirections;
import entity.NPC;
import entity.NPCManager;
import entity.Player;
import gamestates.CameraManager;
import gamestates.FlagManager;
import gamestates.states.OverworldState;
import java.util.List;
import java.util.Map;

public class PorbitalTownCutscenes extends CutsceneTemplate {

    public static void initialize(Map<String, List<Cutscene>> cutscenes, OverworldState overworldState, NPCManager npcManager, CameraManager cameraManager, Player player, FlagManager flagManager) {
        NPC adoptedChild = npcManager.getNPC("AdoptedChild");

/*
* -----------------------------------------------------------------------------
* CUTSCENE: Porbital Town - Adopted Child Introduction
* Location: Adopted Child's House, Floor 2
* -----------------------------------------------------------------------------
? FLAGS USED:
~   - ADOPTED_CHILD_1: The adopted child decided to get along with the player.
* -----------------------------------------------------------------------------
! FLOW:
^   1. Only runs if ADOPTED_CHILD_1 is NOT set.
^   2. Player enters, adopted child reacts and delivers dialogue about the player's lack of responsibility.
^   3. Player is presented with dialogue options to explain themselves who they are.
^   4. After the scene, ADOPTED_CHILD_1 is set.
* -----------------------------------------------------------------------------
*/
        addCutscene(cutscenes, getKeyLocation(7, 6, "porbital_town__house1_f2"), new CutsceneBuilder()
            .forbid("ADOPTED_CHILD_1")
            .wait(20)
            .react(adoptedChild, cameraManager, Emotes.SURPRISE)
            .moveYthenX(adoptedChild, 8, 6)
            .wait(10)
            .face(player, FacingDirections.RIGHT)
            .speak(
                "???: Oh! You're finally here now, huh?",
                "I've never seen someone this irresponsible before. Not to mention you're supposed to be my new adopted dad.",
                "You don't look apologetic at all for what you did.",
                "What a horrible person!",
                "You should be ashamed of yourself.",
                "You're a disgrace to the human race.",
                "Looking at you makes me want to throw up.",
                "I'm not sure if I should be disgusted or impressed.",
                "Well, I guess I'm disgusted.",
                "If you want to redeem yourself...",
                "Say sorry to me, NOW!!!"
            )
            .waitEmote(adoptedChild, cameraManager)
            .face(player, FacingDirections.UP)
            .wait(30)
            .face(player, FacingDirections.RIGHT)
            .wait(30)
            .face(player, FacingDirections.DOWN)
            .wait(30)
            .face(player, FacingDirections.RIGHT)
            .music("BattleWild")
            .parallel(
                new CameraAction(cameraManager, 60),
                new EmoteAction(adoptedChild, 60, Emotes.ANGRY, cameraManager)
            )
            .speak(
                "???: WHAT ARE YOU WAITING FOR?!",
                "YOU'RE SUPPOSED TO BE MY NEW ADOPTED DAD, YET YOU'RE STANDING THERE LIKE A STUPID IDIOT!",
                "YOU DIDN'T COME HERE ON TIME.",
                "YOU DIDN'T EVEN TELL ME YOU WERE COMING!",
                "BUT YOU KNOW WHAT'S WORSE?\nI DON'T EVEN NEED ADOPTED DAD ANYMORE.",
                "THE PROFESSOR JUST TOLD ME SHE PICKED ME UP FROM A HOMELESS SHELTER WHEN I WAS 2 YEARS OLD.",
                "AND THAT MEANS I'M ALREADY AN ADOPTED CHILD.",
                "SO WHEN SHE SAID SHE WAS THINKING ABOUT GETTING ME ADOPTED, SHE ACTUALLY MEANT SHE WAS GOING TO GET THE PAPER WORK DONE",
                "AND NOTHING ABOUT MY LIFE WILL ACTUALLY CHANGE. YOU ARE JUST A WORTHLESS EXTRA CHARACTER IN MY LIFE, SO...",
                "SO...",
                "So...",
                "So.......",
                "Who...?",
                "Who are you...?"
            )
            .music("Lappet")
            .waitEmote(player, cameraManager)
            .speak(new Dialogue( new String[]{  
                "???: Okay, sorry for yelling at you. I was a bit too hot-headed.",
                "Please tell me who you are and why you're in my house."
            },
            new DialogueOption("I... I don't know.",
                "Fine, since I also don't know who you are, Let's get along!"
            ),
            new DialogueOption("I am an aspiring pokemon master!",
                "I'm not sure what you're talking about, but that sounds cool.",
                "What is a pokemon anyway? Is it like that thing the professor was talking about...?",
                "Anyway, since your brain is all messed up just like mine, let's get along, alright?"))
            )
            .react(adoptedChild, cameraManager, Emotes.MUSIC)
            .react(player, cameraManager, Emotes.QUESTION)
            .setFlag(flagManager, "ADOPTED_CHILD_1")
            .build()
        );
/*
* -----------------------------------------------------------------------------
* CUTSCENE: Porbital Town - Adopted Child Talk After Introduction
* Location: Adopted Child's House, Floor 2
* -----------------------------------------------------------------------------
? FLAGS USED:
~   - ADOPTED_CHILD_1: The adopted child decided to get along with the player.
* -----------------------------------------------------------------------------
! FLOW:
^   1. Only runs if ADOPTED_CHILD_1 is set.
^   2. Player questions Adopted Child, but she doesn't care.
* -----------------------------------------------------------------------------
*/
        addCutscene(cutscenes, getKeyNPC(adoptedChild), new CutsceneBuilder()
            .require("ADOPTED_CHILD_1")
            .forbid("ADOPTED_CHILD_2")
            .faceTowards(adoptedChild, player)
            .react(adoptedChild, cameraManager, Emotes.FRIENDLY)
            .speak(
                "???: We are friends now, right?",
                "It must be tiring to appear here all of the sudden.",
                "Well, I guess this is now your house too, so go eat some food downstairs!"
            )
            .build()
        );
/*
* -----------------------------------------------------------------------------
* CUTSCENE: Object Dialogues
* Location: Adopted Child's House, Floor 2
* -----------------------------------------------------------------------------
? FLAGS USED:
* -----------------------------------------------------------------------------
! FLOW:
^   1. Player interacts with objects in the house.
* -----------------------------------------------------------------------------
*/
    
        Cutscene bookshelf1 = new CutsceneBuilder()
            .speak(
                "It's a bookshelf.",
                "You've never seen one before coming here.",
                "It's so cool!"
            )
            .build();

        Cutscene bookshelf2 = new CutsceneBuilder()
            .speak(
                "It's a bookshelf.",
                "There's a book titled \"The Myth, the Legend\".",
                "It seems like a children's book about a violent war between two kingdoms.",
                "You're not sure if you should read it.",
                "You might be influenced by its content and turn into someone like her."
            )
            .build();

        Cutscene bookshelf3 = new CutsceneBuilder()
            .speak(
                "It's a bookshelf.",
                "The books that should be here are on the ground.",
                "It's probably her recently throwing tantrum meaninglessly.",
                "If she's not the one cleaning this up, you feel sorry for her real adoptive parents."
            )
            .build();

        Cutscene trashCan = new CutsceneBuilder()
            .speak(
                "It's an empty trash can.",
                "You feel a strange sense of peace and comfort looking at it."
                )
            .build();
            
        Cutscene tv = new CutsceneBuilder()
            .speak(
                "It's a TV.",
                "The screen is dusty.",
                "And it's showing advertisement for a new product called \"Trinitrotoluene\".",
                "The presentation makes it look yummy, but you doubt it's edible.",
                "You wonder if anyone here knows how to file a lawsuit against the broadcasting company."
                )
            .build();
                
        Cutscene pc1 = new CutsceneBuilder()
            .speak(
                "It's a computer.",
                "The desktop background is a picture of a multiple \"C\"s and \"H\"s connected to each other with lines.",
                "You try to log in, but the password isn't \"1234\"."
                )
            .build();

        Cutscene pc2 = new CutsceneBuilder()
            .speak(
                "It's a computer.",
                "The desktop background is a bunch of zigzag lines with some more lines branching out.",
                "You try to log in, but the password isn't \"1234\"."
                )
            .build();
        
        Cutscene laptop = new CutsceneBuilder()
            .speak(
                "It's a laptop.",
                "The screen is open to a document titled \"How to Deal with Annoying Adults\".",
                "The last seach in the search engine is \"Can peroxides and sulfuric acid be used to dissolve a human body?\"",
                "You decide not to read any further."
                )
            .build();
            
        addCutscene(cutscenes, getKeyLook(4, 2, "porbital_town__house1_f2"), bookshelf1);
        addCutscene(cutscenes, getKeyLook(5, 2, "porbital_town__house1_f2"), bookshelf2);
        addCutscene(cutscenes, getKeyLook(6, 2, "porbital_town__house1_f2"), bookshelf3);
        addCutscene(cutscenes, getKeyLook(7, 2, "porbital_town__house1_f2"), bookshelf3);
        addCutscene(cutscenes, getKeyLook(1, 3, "porbital_town__house1_f2"), trashCan);
        addCutscene(cutscenes, getKeyLook(2, 3, "porbital_town__house1_f2"), pc1);
        addCutscene(cutscenes, getKeyLook(1, 5, "porbital_town__house1_f2"), pc2);
        addCutscene(cutscenes, getKeyLook(6, 6, "porbital_town__house1_f2"), laptop);
        addCutscene(cutscenes, getKeyLook(12, 7, "porbital_town__house1_f2"), tv);
    }
}
