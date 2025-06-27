package cutscene.initialize;

import cutscene.Cutscene;
import cutscene.CutsceneBuilder;
import cutscene.Emotes;
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
        NPC professorDecane = npcManager.getNPC("ProfDecane");

        adoptedChild1(cutscenes, adoptedChild, cameraManager, player, flagManager);
        adoptedChildTalk1(cutscenes, adoptedChild, cameraManager, player);
        objectDialogues(cutscenes);
        professorDecane1(cutscenes, professorDecane, adoptedChild, overworldState, cameraManager, player, flagManager);
        decaneAndChildWaitingForChemical(cutscenes, professorDecane, adoptedChild, cameraManager, player);
    }

    private static void adoptedChild1(Map<String, List<Cutscene>> cutscenes, NPC adoptedChild, CameraManager cameraManager, Player player, FlagManager flagManager) {
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
        addCutscene(cutscenes, new CutsceneBuilder()
            .forbid("ADOPTED_CHILD_1")
            .wait(20)
            .react(adoptedChild, cameraManager, Emotes.SURPRISE)
            .moveYthenX(adoptedChild, 8, 6)
            .wait(10)
            .face(player, FacingDirections.RIGHT)
            .speak(
                "???: Oh! You're finally here now, huh?",
                "I've never seen someone this irresponsible before. Not to mention you're supposed to be my adopted dad.",
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
            .waitEmote(adoptedChild, cameraManager, 60)
            .face(player, FacingDirections.UP)
            .wait(30)
            .face(player, FacingDirections.RIGHT)
            .wait(30)
            .face(player, FacingDirections.DOWN)
            .wait(30)
            .face(player, FacingDirections.RIGHT)
            .music("BattleWild")
            .parallel( new CutsceneBuilder()
                .camShake(cameraManager, 60)
                .react(adoptedChild, cameraManager, Emotes.ANGRY)
                .buildActions()
            )
            .shout("WHAT ARE YOU WAITING FOR?!", cameraManager)
            .shout("YOU'RE SUPPOSED TO BE MY NEW ADOPTED DAD, YET YOU'RE STANDING THERE LIKE A STUPID IDIOT!", cameraManager)
            .shout("YOU DIDN'T COME HERE ON TIME.", cameraManager)
            .shout("YOU DIDN'T PREPARE ANY WELCOMING GIFTS FOR ME.", cameraManager)
            .shout("YOU DIDN'T TELL ME YOU WERE COMING.", cameraManager)
            .shout("YOU DIDN'T EVEN SAY HI TO ME FIRST!!!", cameraManager)
            .shout("BUT YOU KNOW WHAT'S WORSE?\nI DON'T NEED AN ADOPTED DAD ANYMORE.", cameraManager)
            .shout("THE PROFESSOR JUST TOLD ME SHE PICKED ME UP FROM A HOMELESS SHELTER WHEN I WAS 2 YEARS OLD.", cameraManager)
            .shout("AND THAT MEANS I'M ALREADY AN ADOPTED CHILD.", cameraManager)
            .shout("SO WHEN SHE SAID SHE WAS THINKING ABOUT GETTING ME ADOPTED, SHE ACTUALLY MEANT SHE WAS GOING TO GET THE PAPER WORK DONE", cameraManager)
            .shout("AND NOTHING ABOUT MY LIFE WILL ACTUALLY CHANGE. YOU ARE JUST A WORTHLESS EXTRA CHARACTER IN MY LIFE, SO...", cameraManager)
            .musicStop()
            .speak(
                "SO...",
                "So...",
                "So.......",
                "Who...?",
                "Who are you...?"
            )
            .music("Lappet")
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
            .speak(
                "???: And to celebrate our friendship, I allow you to eat my milk pudding in the fridge!",
                "Enjoy!!"
            )
            .react(player, cameraManager, Emotes.QUESTION)
            .setFlag(flagManager, "ADOPTED_CHILD_1")
            .buildCutscene(),
            getKeyLocation(7, 6, "porbital_town__house1_f2")
        );
    }

    private static void adoptedChildTalk1(Map<String, List<Cutscene>> cutscenes, NPC adoptedChild, CameraManager cameraManager, Player player) {
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
^   2. Player questions Adopted Child, but she sends him downstairs to eat.
* -----------------------------------------------------------------------------
*/
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("ADOPTED_CHILD_1")
            .forbid("PROF_DECANE_1")
            .faceTowards(adoptedChild, player)
            .react(adoptedChild, cameraManager, Emotes.FRIENDLY)
            .speak(
                "???: We are friends now, right?",
                "It must be tiring to appear at somebody's house all of the sudden.",
                "Well, I guess this is now your house too...",
                "Don't mind me and go eat some puddings downstairs!",
                "I'll be waiting for you here!"
            )
            .buildCutscene(),
            getKeyNPC(adoptedChild)
        );
    }

    private static void objectDialogues(Map<String, List<Cutscene>> cutscenes) {
        
/*
* -----------------------------------------------------------------------------
* Location: Adopted Child's House, Floor 2
* -----------------------------------------------------------------------------
*/

//* bookshelf1
        addCutscene(cutscenes, new CutsceneBuilder() 
            .speak(
                "It's a bookshelf.",
                "You've never seen one before coming here.",
                "It's so cool!"
            ).buildCutscene(),
            getKeyLook(4, 2, "porbital_town__house1_f2")
        );

//* bookshelf2
        addCutscene(cutscenes, new CutsceneBuilder() 
            .speak(
                "It's a bookshelf.",
                "There's a book titled \"The Myth, the Legend\".",
                "It seems like a children's book about a violent war between two kingdoms.",
                "You're not sure if you should read it.",
                "You might be influenced by its content and turn into someone like her."
            ).buildCutscene(),
            getKeyLook(5, 2, "porbital_town__house1_f2")
        );

//* bookshelf3
        addCutscene(cutscenes, new CutsceneBuilder() 
            .speak(
                "It's a bookshelf.",
                "The books that should be here are on the ground.",
                "It's probably her recently throwing tantrum meaninglessly.",
                "If she's not the one cleaning this up, you feel sorry for her real adoptive parents."
            ).buildCutscene(), 
            getKeyLook(6, 2, "porbital_town__house1_f2"),
            getKeyLook(7, 2, "porbital_town__house1_f2")
        );

//* trashCan
        addCutscene(cutscenes, new CutsceneBuilder() 
            .speak(
                "It's an empty trash can.",
                "You feel a strange sense of peace and comfort looking at it."
            ).buildCutscene(),
            getKeyLook(1, 3, "porbital_town__house1_f2")
        );

//* pc1
        addCutscene(cutscenes, new CutsceneBuilder() 
            .speak(
                "It's a computer.",
                "The desktop background is a picture of a multiple \"C\"s and \"H\"s connected to each other with lines.",
                "You try to log in, but the password isn't \"1234\"."
            ).buildCutscene(),
            getKeyLook(2, 3, "porbital_town__house1_f2")
        );

//* pc2
        addCutscene(cutscenes, new CutsceneBuilder() 
            .speak(
                "It's a computer.",
                "The desktop background is a bunch of zigzag lines with some more lines branching out.",
                "You try to log in, but the password isn't \"1234\"."
                ).buildCutscene(),
            getKeyLook(1, 5, "porbital_town__house1_f2")
        );

//* laptop
        addCutscene(cutscenes, new CutsceneBuilder() 
            .speak(
                "It's a laptop.",
                "The screen is open to a document titled \"How to Deal with Annoying Adults\".",
                "The last seach in the search engine is \"Can peroxides and sulfuric acid be used to dissolve a human body?\"",
                "You decide not to read any further."
                ).buildCutscene(),
            getKeyLook(6, 6, "porbital_town__house1_f2")
        );

//* tv
        addCutscene(cutscenes, new CutsceneBuilder() 
            .speak(
                "It's a TV.",
                "The screen is dusty.",
                "And it's showing advertisement for a new product called \"Trinitrotoluene\".",
                "The presentation makes it look yummy, but you doubt it's edible.",
                "You wonder if anyone here knows how to file a lawsuit against the broadcasting company."
                ).buildCutscene(),
            getKeyLook(12, 7, "porbital_town__house1_f2")
        );

//* book1
        addCutscene(cutscenes, new CutsceneBuilder() 
            .speak(
                "It's a book.",
                "The content is incomprehensible to you.",
                "Maybe this girl put all her points during character creation into intelligence and none in emotion control."
            ).buildCutscene(),
            getKeyLook(5, 7, "porbital_town__house1_f2")
        );

/*
*-----------------------------------------------------------------------------
* Location: Adopted Child's House, Floor 1
* -----------------------------------------------------------------------------
*/

//* tv
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak(
                "It's a TV.",
                "It's showing a documentary about the synthesis of\n\"(2R,3S,4R,5R)-2,3,4,5,6-Pentahydroxyhexanal\"",
                "You feel a sudden urge to change the channel."
            ).buildCutscene(),
            getKeyLook(12, 5, "porbital_town__house1_f1")
        );

//* drawer
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("ADOPTED_CHILD_1")
            .speak(
                "It's a drawer.",
                "You opened it to see if there's anything inside.",
                "There's an empty pudding cup. It seems she already ate it.",
                "You feel betrayed."
            ).buildCutscene(),
            getKeyLook(6, 2, "porbital_town__house1_f1")
        );

//* sink
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak(
                "The sink is sparkling clean.",
                "Too clean.",
                "Suspiciously clean.",
                "You wonder where DECANE got the time to clean it after that explosion."
            ).buildCutscene(),
            getKeyLook(1, 2, "porbital_town__house1_f1"),
            getKeyLook(2, 2, "porbital_town__house1_f1")
        );

//* cabinet
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak(
                "It's a cabinet.",
                "You open it to see if there's anything inside.",
                "There's a bunch of shattered beakers and test tubes.",
                "There is a fuming flask on the top shelf.",
                "You feel like you should get out of here."
            ).buildCutscene(),
            getKeyLook(3, 3, "porbital_town__house1_f1"),
            getKeyLook(4, 3, "porbital_town__house1_f1")
        );

//* trash_can
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak(
                "It's a trash can.",
                "You look inside and find a crumpled piece of paper.",
                "It reads: \"Plan to take over the world.",
                "Step 1: Befriend the new person.\nStep 2: ...\"",
                "The rest is illegible."
            ).buildCutscene(),
            getKeyLook(12, 10, "porbital_town__house1_f1")
        );
    }

    private static void professorDecane1(Map<String, List<Cutscene>> cutscenes, NPC professorDecane, NPC adoptedChild, OverworldState overworldState, CameraManager cameraManager, Player player, FlagManager flagManager) {
/*
* -----------------------------------------------------------------------------
* CUTSCENE: Porbital Town - Professor Decane Introduction
* Location: Adopted Child's House, Floor 1
* -----------------------------------------------------------------------------
? FLAGS USED:
~   - ADOPTED_CHILD_1: The adopted child decided to get along with the player.
~   - PROF_DECANE_1: The professor has explained the situation to the player.
* -----------------------------------------------------------------------------
! FLOW:
^   1. Only runs if ADOPTED_CHILD_1 is set but PROF_DECANE_1 is NOT set.
^   2. Player enters floor 1, professor notices and introduces herself.
^   3. Professor explains your situation.
^   4. Adopted Child comes downstairs and asks why you haven't eaten food despite her invitation.
^   5. Professor calms Adopted Child down and failed.
^   6. Professor asks you politely to go to the nextdoor house while she calms Adopted Child down.
^   7. After the scene, PROF_DECANE_1 is set.
* -----------------------------------------------------------------------------
*/
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("ADOPTED_CHILD_1")
            .forbid("PROF_DECANE_1")
            .faceTowards(player, professorDecane)
            .camMove(cameraManager, -288, 0, 90)
            .camFocus(cameraManager, professorDecane)
            .speak(
                "???: Mmm, yes... I've finally created water.",
                "This 100% pure water is now ready to drink.",
                "Its hypotonicity is just right for the human body to disintegrate upon contact.",
                "A new era of water has begun.",
                "If only I could mass produce this..."
            )
            .react(professorDecane, cameraManager, Emotes.SURPRISE)
            .shout("OH, WAIT...", cameraManager)
            .move(professorDecane, 1, 4)
            .face(professorDecane, FacingDirections.UP)
            .parallel(new CutsceneBuilder()
                .sfx("BattleDamageSuper")
                .animation("fire4", 1, 2, 0.4, cameraManager)
                .camShake(cameraManager, 50)
                .buildActions()
            )
            .waitEmote(professorDecane, cameraManager, 60)
            .speak(
                "???: I guess I've made a mistake.",
                "Did I have an alkali metal in the glass before I put in the water?",
                "...",
                "..."
            )
            .faceTowards(professorDecane, player)
            .react(professorDecane, cameraManager, Emotes.SURPRISE)
            .parallel(new CutsceneBuilder()
                .camChangeFocus(cameraManager, player, 90)
                .moveYthenX(professorDecane, 8, 3)
                .buildActions()
            )
            .waitEmote(player, cameraManager, 60)
            .emote(professorDecane, 60, Emotes.SMILE, cameraManager)
            .speak(
                "???: Ah! You're here! I've been waiting for you!",
                "I'm sorry for making you wait.",
                "I've been busy with my research about the pure water.",
                "Please make yourself at home, because this building will be your home for the rest of your life.",
                "No matter where you go, you'll always come back here.",
                "No matter how important the mission you will be doing is, you'll always return here.",
                "And there's no way you can escape from this fate."
            )
            .waitEmote(player, cameraManager, 60)
            .moveYthenX(player, 8, 7)
            .faceTowards(player, professorDecane)
            .move(professorDecane, 8, 4)
            .wait(20)
            .speak("???: Why are you running away?")
            .emote(player, 60, Emotes.TERRIFIED, cameraManager)
            .speak(
                "I know you're scared, but there's no escape from this.",
                "But looking at the bright side, this place is quite nice, isn't it?",
                "This PORBITAL TOWN is a perfect place for you to live in.",
                "It's also the perfect place for you to die in.",
                "Well, it's technically the only place you can die in, not that I'm trying to kill you or anything.",
                "You are just stuck here now.",
                "Use your last remaining 10 days of your life to its fullest.",
                "It's not a long time, but it's still long enough to do some research.",
                "So, make yourself at home!"
            )
            .moveXthenY(professorDecane, 9, 7)
            .faceTowards(professorDecane, player)
            .emote(professorDecane, 60, Emotes.FRIENDLY, cameraManager)
            .move(player, 4, 7)
            .faceTowards(player, professorDecane)
            .wait(20)
            .speak( new Dialogue( new String[]{
                "???: You're still scared of me...?",
                "I'm not that scary, am I?"
            },
            new DialogueOption("Yes, you are.",
                "No, I am not.",
                "I'm on your side! You know that, right?",
                "I am the one who saved you from the bomb, remember?",
                "It's a miracle already that you're still alive.",
                "10 days to live more than the 1,000 people who died in that incident, including your family, friends, and everyone of your classmates.",
                "That's not so bad, right?"
            ),
            new DialogueOption("I've never seen any pokemon game this dark before...",
                "Hm...",
                "I think you're misunderstanding your situation here.",
                "This isn't a pokemon game, this is your life.",
                "You are only here in this 2D dimension because this is the only way you can live.",
                "Nobody but me and you know about your original world.",
                "You are not a game character, you are a human being.",
                "Pokemon doesn't exist.",
                "You can keep pretending you're a pokemon trainer, but that won't do anything here.",
                "In the end, you are the only one I can save from that TNT bombing incident.",
                "The incident that killed 1,000 people, including everyone you know..."
            ))
            )
            .emote(player, 60, Emotes.TERRIFIED, cameraManager)
            .waitEmote(professorDecane, cameraManager, 60)
            .speak(new Dialogue( new String[]{
                "By the way... What's your zodiac sign? I'm a Gemini.",
            },
            new DialogueOption("Don't change topic suddenly! What was the bomb?",
                "Okay, okay. If you want to know that much, I'll tell you.",
                "It's gonna be a long story, so get ready.",
                "If you are hungry, go find something to eat first.",
                "If you are thirsty, go find some water to drink first.",
                "The only water you shouldn't drink is the one I just made. Everything else is fine.",
                "If you want to go to the bathroom, there's no toilet here. Go in the forest and manage yourself.",
                "...",
                "...",
                "I'll take that silence as you being ready, alright?",
                "In the name of PROFESSOR DECANE of the n-variety, I swear to tell the truth and nothing but the truth.",
                "I'll start now!"
            ),
            new DialogueOption("I'm a Gemini too.",
                "Oh really? What a coincidence!",
                "Geminis are known for their intelligence, you know.",
                "With all the data Google has from their search engine, the Gemini series models are unmatched.",
                "You know what I'm talking about, right?",
                "I couldn't talk to anyone here about the 3D world technologies, so you're the only one I can talk to.",
                "So please, if it doesn't bother you too much, please endure my ramblings a bit.",
                "This PROFESSOR DECANE is a bit of a nerd, so she's not good at talking to people, okay?",
                "Speaking of intelligence, the bomb incident I mentioned earlier involved two opposing factions..."
            ),
            new DialogueOption("I'm a Charizard.",
                "A...",
                "Charizard...?",
                "I told you pokemon don't exist here, but if that's how you want to cope with reality, fine.",
                "Even a Charizard would have been helpless against the bomb.",
                "Not Mega Charizard X, not Mega Charizard Y, not Gigantamax Charizard.",
                "Not even Terastal Charizard would have been able to stop the bomb.",
                "The only person who can protect someone from that bomb is me, PROFESSOR DECANE of the n-variety.",
                "Though... I only had enough time to save you.",
                "Be grateful."
            ))
            )
            .wait(20)
            .parallel(new CutsceneBuilder()
                .music("Cave")
                .speak(
                    "The whole mess began 10 years ago, when your 3D world was all happy and peaceful.",
                    "It was then when the Z faction figured out a way to break out of---"
                )
                .buildActions()
            )
            .wait(10)
            .sfx("BattleDamageSuper")
            .camShake(cameraManager, 60)
            .tp(adoptedChild, 10, 3, "porbital_town__house1_f1", overworldState)
            .sfx("BattleDamageSuper")
            .react(adoptedChild, cameraManager, Emotes.ANGRY)
            .move(adoptedChild, 10, 7)
            .parallel(new CutsceneBuilder()
                .react(professorDecane, cameraManager, Emotes.SURPRISE)
                .faceTowards(professorDecane, adoptedChild)
                .faceTowards(adoptedChild, professorDecane)
                .buildActions()
            )
            .music("BattleWild")
            .shout("???: N-DECANE, WHY IS MY NEW FRIEND NOT EATING? I TOLD HIM TO COME DOWNSTAIRS TO EAT!", cameraManager)
            .shout("DID YOU HOLD HIM HOSTAGE OR SOMETHING?", cameraManager)
            .shout("DON'T YOU DARE TELL ME THAT YOU PLAN TO USE HIM FOR SOME SICK AND PERVERTED EXPERIMENT AGAIN!", cameraManager)
            .shout("THIS IS THE FIFTH PERSON YOU BROUGHT HOME!", cameraManager)
            .shout("SOME OUTSIDERS WOULD HAVE THOUGHT YOU ARE A PROFESSIONAL SEDUCER IF YOU KEEP THIS BEHAVIOR UP.", cameraManager) 
            .musicStop()
            .wait(20)
            .waitEmote(professorDecane, cameraManager, 60)
            .faceTowards(professorDecane, player)
            .wait(20)
            .speak(new Dialogue( new String[]{
                "DECANE: ...What happened?",
                "W-Why is she this angry? Do you know?"
            },
            new DialogueOption("I don't know.",
                "DECANE: Of course you didn't know... You just arrived here after all..."
            ),
            new DialogueOption("I'm supposed to come down here to eat her pudding...",
                "DECANE: She...",
                "She already ate the pudding this morning...",
                "What's wrong with her...?"
            ),
            new DialogueOption("I think she accidentally killed a wild shiny pokemon.",
                "DECANE: She doesn't even know what a pokemon is..."
            ))
            )
            .parallel(new CutsceneBuilder()
                .music("BattleWild")
                .react(adoptedChild, cameraManager, Emotes.ANGRY)
                .shout("???: N-DECANE!!!", cameraManager)
                .buildActions()
            )
            .faceTowards(professorDecane, adoptedChild)
            .wait(40)
            .shout("IT WOULD BE FINE IF THAT PERSON ISN'T MY FRIEND, BUT WE GOT ALONG SO WELL!", cameraManager)
            .shout("I WON'T ALLOW YOU TO DO ANYTHING TO HIM", cameraManager)
            .musicStop()
            .wait(20)
            .faceTowards(professorDecane, player)
            .wait(40)
            .speak(
                "DECANE: I... I'm sorry for not raising her well.",
                "She's always like this when she's with someone else...",
                "Well, you should go to the house next door.",
                "My friend's name is PROFESSOR CELLULOSE. He lives there.",
                "You should go over and introduce yourself while I'm dealing with this little gremlin."
            )
            .parallel(new CutsceneBuilder()
                .music("BattleWild")
                .react(adoptedChild, cameraManager, Emotes.ANGRY)
                .shout("???: I'M THE ONE TALKING WITH YOU, NOT THE PLAYER!", cameraManager)
                .buildActions()
            )
            .faceTowards(professorDecane, adoptedChild)
            .wait(40)
            .shout("EXPLAIN YOUR SELF RIGHT NOW!\nI WON'T LET YOU GO EASILY THIS TIME!!!", cameraManager)
            .musicStop()
            .wait(20)
            .faceTowards(professorDecane, player)
            .wait(40)
            .parallel(new CutsceneBuilder()
                .emote(professorDecane, 160, Emotes.SAD, cameraManager)
                .speak(
                    "DECANE: ...",
                    "PROFESSOR CELLULOSE has a special chemical to help calm her down.",
                    "Can you please go over there and bring it to me?",
                    "In the meantime, I'll do my best here.",
                    "I'm sorry for making you do this, but I'm sure you can handle it."
                )
                .buildActions()
            )
            .wait(40)
            .faceTowards(professorDecane, adoptedChild)
            .wait(40)
            .parallel(new CutsceneBuilder()
                .react(adoptedChild, cameraManager, Emotes.MUSIC)
                .music("BattleWild")
                .shout("???: GOOD LUCK, MY FRIEND! AND DON'T FORGET TO EAT THE PUDDING!", cameraManager)
                .buildActions()
            )
            .musicStop()
            .setFlag(flagManager, "PROF_DECANE_1")
            .buildCutscene(),
            getKeyLocation(10, 3, "porbital_town__house1_f1")
        );
    }

    private static void decaneAndChildWaitingForChemical(Map<String, List<Cutscene>> cutscenes, NPC professorDecane, NPC adoptedChild, CameraManager cameraManager, Player player) {
/*
* -----------------------------------------------------------------------------
* CUTSCENE: Porbital Town - Decane and Child Waiting For Chemical
* Location: Adopted Child's House, Floor 1
* -----------------------------------------------------------------------------
? FLAGS USED:
~   - PROF_DECANE_1: The professor has explained the situation to the player.
~   - PROF_CELLULOSE_1: Player has the chemical. (Assumed to be implemented)
* -----------------------------------------------------------------------------
! FLOW:
^   1. Only runs if PROF_DECANE_1 is set but GOT_CELLULOSE_CHEMICAL is NOT set.
^   2. Player talks to Professor Decane, she urges them to hurry.
^   3. Player talks to Adopted Child, she encourages them.
* -----------------------------------------------------------------------------
*/
        // For Professor Decane
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("PROF_DECANE_1")
            .forbid("PROF_CELLULOSE_1")
            .faceTowards(professorDecane, player)
            .speak(
                "DECANE: Please, you must hurry to Professor Cellulose's house.",
                "I'm not sure how much longer I can keep her occupied."
            )
            .faceTowards(professorDecane, adoptedChild)
            .buildCutscene(),
            getKeyNPC(professorDecane)
        );

        // For Adopted Child
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("PROF_DECANE_1")
            .forbid("PROF_CELLULOSE_1")
            .faceTowards(adoptedChild, player)
            .react(adoptedChild, cameraManager, Emotes.FRIENDLY)
            .speak(
                "???: Hey, my friend! What are you doing?",
                "I'm protecting you from this crazy woman!",
                "Hope you enjoy this little town!"
            )
            .faceTowards(adoptedChild, professorDecane)
            .buildCutscene(),
            getKeyNPC(adoptedChild)
        );
    }
}
