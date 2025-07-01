package cutscene.initialize;

import cutscene.Cutscene;
import cutscene.CutsceneBuilder;
import cutscene.Emotes;
import cutscene.initialize.porbital.PorbitalTownObjects;
import cutscene.initialize.porbital.PorbitalTownTalks;
import cutscene.template.CutsceneTemplate;
import cutscene.template.NumericInputTemplate;
import dialogue.Dialogue;
import dialogue.DialogueOption;
import entity.FacingDirections;
import entity.NPC;
import entity.NPCManager;
import entity.Player;
import gamestates.CameraManager;
import gamestates.FlagManager;
import gamestates.StateManager;
import gamestates.states.OverworldState;
import java.util.List;
import java.util.Map;

public class PorbitalTownCutscenes extends CutsceneTemplate {

    public static void initialize(Map<String, List<Cutscene>> cutscenes, StateManager stateManager, OverworldState overworldState, NPCManager npcManager, CameraManager cameraManager, Player player, FlagManager flagManager) {
        NPC yuuki = npcManager.getNPC("Yuuki");
        NPC professorDecane = npcManager.getNPC("ProfDecane");
        NPC professorCellulose = npcManager.getNPC("ProfCellulose");
        NPC director = npcManager.getNPC("Director");

        PorbitalTownObjects.initialize(cutscenes);
        PorbitalTownTalks.initialize(cutscenes, npcManager, cameraManager, player);
        yuuki1(cutscenes, yuuki, overworldState, cameraManager, player, flagManager);
        professorDecane1(cutscenes, professorDecane, yuuki, overworldState, cameraManager, player, flagManager);
        professorCellulose1(cutscenes, professorCellulose, cameraManager, player, flagManager);
        director1(cutscenes, director, cameraManager, player, flagManager);
    }

    private static void yuuki1(Map<String, List<Cutscene>> cutscenes, NPC yuuki, OverworldState overworldState, CameraManager cameraManager, Player player, FlagManager flagManager) {
/*
* -----------------------------------------------------------------------------
* CUTSCENE: Porbital Town - Yuuki Introduction
* Location: Yuuki's House, Floor 2
* -----------------------------------------------------------------------------
? FLAGS USED:
~   - YUUKI_1: Yuuki decided to get along with the player.
* -----------------------------------------------------------------------------
! FLOW:
^   1. Only runs if YUUKI_1 is NOT set.
^   2. Player enters, Yuuki reacts and delivers dialogue about the player's lack of responsibility.
^   3. Player is presented with dialogue options to explain themselves who they are.
^   4. After the scene, YUUKI_1 is set.
* -----------------------------------------------------------------------------
*/
        addCutscene(cutscenes, new CutsceneBuilder()
            .forbid("YUUKI_1")
            .wait(150)
            .waitEmote(player, cameraManager, 60)
            .speak("THINKING",
                "You woke up in a place you've never seen before.",
                "It's a room...",
                "A room with a bed, a table, and a chair.",
                "You don't know how you got here.",
                "The last thing you remember is...",
                "...",
                "Is... what...?",
                "There's no flash of light, no explosion, nothing like what you've seen in movies.",
                "You just... appeared here.",
                "No rhyme or reason.",
                "No explanation.",
                "No recollection about this place.",
                "No...",
                "..."
            )
            .wait(30)
            .face(player, FacingDirections.RIGHT)
            .wait(30)
            .move(player, 8, 6)
            .speak("THINKING",
                "Wait a minute..."
            )
            .waitEmote(player, cameraManager, 60)
            .wait(20)
            .move(player, 8, 7)
            .move(player, 7, 7)
            .move(player, 7, 6)
            .wait(30)
            .speak("THINKING",
                "...",
                "...",
                "...",
                "No... It's not that I have no recollection of this place.",
                "I'm not sure if I'm dreaming or not.",
                "...",
                "..."
            )
            .react(player, cameraManager, Emotes.QUESTION)
            .speak("THINKING",
                "I...",
                "I am in a pokemon game... right?",
                "...",
                "If so... I'm supposed to go downstairs and...",
                "get some starter pokemo---"
            )
            .parallel(new CutsceneBuilder()
                .sfx("BattleDamageWeak")
                .react(player, cameraManager, Emotes.SURPRISE)
                .camShake(cameraManager, 60)
                .buildActions()
            )
            .sfx("DoorEnter")
            .tp(yuuki, 10, 3, "porbital_town__house1_f2", overworldState)
            .wait(20)
            .waitEmote(yuuki, cameraManager, 60)
            .react(yuuki, cameraManager, Emotes.SURPRISE)
            .moveYthenX(yuuki, 8, 6)
            .wait(10)
            .face(player, FacingDirections.RIGHT)
            .speak("THINKING",
                "Someone walked in.",
                "If you trust the logic of pokemon games, this girl should be your rival."
            )
            .parallel(new CutsceneBuilder()
                .sfx("BattleDamageWeak")
                .react(yuuki, cameraManager, Emotes.ANGRY)
                .camShake(cameraManager, 60)
                .buildActions()
            )
            .speak("YUUKI",
                "Oh! You're finally here now, huh?",
                "I've never seen someone this irresponsible before. Not to mention you're supposed to be my adopted dad."
            )
            .wait(50)
            .speak("THINKING",
                "Adopted... dad...?",
                "I guess the settings here is a bit different from the one I'm used to.",
                "Usually, pokemon games will have the main characters be the children...",
                "But this one is different.",
                "I'm actually the parent...?",
                "...",
                "How does that even wor...?"
            )
            .wait(30)
            .parallel(new CutsceneBuilder()
                .sfx("BattleDamageWeak")
                .camShake(cameraManager, 60)
                .buildActions()
            )
            .speak("YUUKI",
                "You don't look apologetic at all for what you did!!!",
                "What a horrible person!",
                "You should be ashamed of yourself.",
                "You're a disgrace to the human race.",
                "Looking at you makes me want to throw up.",
                "I'm not sure if I should be disgusted or impressed.",
                "Well, I guess I'm disgusted more.",
                "I'm disgusted by your lack of impressiveness.",
                "You would be better off if you melt your own brain and make it into a carbonated drink.",
                "...",
                "...",
                "You know what? I don't even think your CARBONIC ACID would dissociate into a WATER and CARBON DIOXIDE.",
                "You're just that inert and useless.",
                "If you want to redeem yourself..."
            )
            .wait(30)
            .shout("YUUKI", "SAY SORRY TO ME, NOW!!!", cameraManager)
            .wait(60)
            .waitEmote(yuuki, cameraManager, 60)
            .wait(30)
            .face(player, FacingDirections.UP)
            .wait(30)
            .face(player, FacingDirections.RIGHT)
            .wait(50)
            .face(player, FacingDirections.DOWN)
            .wait(30)
            .face(player, FacingDirections.RIGHT)
            .music("BattleWild")
            .wait(20)
            .parallel( new CutsceneBuilder()
                .camShake(cameraManager, 60)
                .react(yuuki, cameraManager, Emotes.ANGRY)
                .buildActions()
            )
            .shout("YUUKI","WHAT ARE YOU WAITING FOR?!", cameraManager)
            .shout("YUUKI","YOU'RE SUPPOSED TO BE MY NEW ADOPTED DAD, YET YOU'RE STANDING THERE LIKE A STUPID IDIOT!", cameraManager)
            .shout("YUUKI","YOU DIDN'T COME HERE ON TIME.", cameraManager)
            .shout("YUUKI","YOU DIDN'T PREPARE ANY WELCOMING GIFTS FOR ME.", cameraManager)
            .shout("YUUKI","YOU PROBABLY DIDN'T EVEN KNOW THAT I WANT A NEW PS420 GAME CONSOLE AS A GIFT!", cameraManager)
            .musicStop()
            .wait(20)
            .move(yuuki, 11, 6)
            .speak("YUUKI",
                "...",
                "This TV is so old. I really need PS420 game console as a gift...",
                "Professor hasn't bought me anything new for the past 3 years...",
                "...",
                "..."
            )
            .wait(60)
            .move(yuuki, 8, 6)
            .wait(20)
            .music("BattleWild")
            .shout("YUUKI","WELL, YOU DIDN'T TELL ME YOU WERE COMING.", cameraManager)
            .shout("YUUKI","YOU DIDN'T EVEN SAY HI TO ME FIRST!!!", cameraManager)
            .shout("YUUKI","BUT YOU KNOW WHAT'S WORSE?\nI DON'T NEED AN ADOPTED DAD ANYMORE.", cameraManager)
            .shout("YUUKI","THE PROFESSOR JUST TOLD ME SHE PICKED ME UP FROM A HOMELESS SHELTER WHEN I WAS 2 YEARS OLD.", cameraManager)
            .shout("YUUKI","AND THAT MEANS I'M ALREADY AN ADOPTED CHILD.", cameraManager)
            .shout("YUUKI","SO WHEN SHE SAID SHE WAS THINKING ABOUT GETTING ME ADOPTED,", cameraManager)
            .shout("YUUKI","SHE ACTUALLY MEANT SHE WAS GOING TO GET THE PAPER WORK DONE!!!", cameraManager)
            .shout("YUUKI","NOTHING ABOUT MY LIFE WILL ACTUALLY CHANGE.", cameraManager)
            .shout("YUUKI","NOTHING!!!!", cameraManager)
            .shout("YUUKI","YOU ARE JUST A WORTHLESS EXTRA CHARACTER IN MY LIFE, SO...", cameraManager)
            .shout("YUUKI","SOOOO.......", cameraManager)
            .musicStop()
            .speak("YUUKI",
                "SO...",
                "So...",
                "So.......",
                "Who...?",
                "Who are you...?"
            )
            .wait(20)
            .music("Lappet")
            .wait(40)
            .parallel(new CutsceneBuilder()
                .waitEmote(yuuki, cameraManager, 60)
                .waitEmote(player, cameraManager, 60)
                .buildActions()
            )
            .speak(new Dialogue( new String[]{  
                "Okay, sorry for yelling at you. I was a bit too hot-headed.",
                "Please tell me who you are and why you're in my house."
            },
            "YUUKI",
            new DialogueOption("I... I don't know.", new Dialogue(new String[]{
                "I... I don't know.",
                "Fine, since I also don't know who you are, we have something in common!",
                "My name is YUUKI, and I'm a girl.",
                "Let's get along!"
            }, "YUUKI")),
            new DialogueOption("I am an aspiring pokemon master!", new Dialogue(new String[]{
                "I'm not sure what you're talking about, but that sounds cool.",
                "What is a pokemon anyway? Is it like that thing the professor was talking about...?",
                "Fine, since your brains seems messed up just like mine, we have something in common!",
                "My name is YUUKI, and I'm a girl.",
                "Let's get along!"
            }, "YUUKI") ),
            new DialogueOption("Now I am become death...", new Dialogue(new String[]{
                "...",
                "The destroyer of worlds?",
                "You're just like me, huh?",
                "We seems to have the same preference for BOOM BOOM stuffs!",
                "My name is YUUKI, and I'm a girl.",
                "Let's get along!"
            }, "YUUKI")))
            )
            .setFlag(flagManager, "YUUKI_KNOW")
            .react(yuuki, cameraManager, Emotes.MUSIC)
            .react(player, cameraManager, Emotes.QUESTION)
            .speak(
                "YUUKI",
                "And to celebrate our friendship, I allow you to eat my milk pudding in the cabinet!",
                "Go downstairs and get it!",
                "Enjoy!!"
                )
            .setFlag(flagManager, "YUUKI_1")
            .buildCutscene(),
            getKeyLocation(7, 6, "porbital_town__house1_f2")
        );
    }

    private static void professorDecane1(Map<String, List<Cutscene>> cutscenes, NPC professorDecane, NPC yuuki, OverworldState overworldState, CameraManager cameraManager, Player player, FlagManager flagManager) {
/*
* -----------------------------------------------------------------------------
* CUTSCENE: Porbital Town - Professor Decane Introduction
* Location: Yuuki's House, Floor 1
* -----------------------------------------------------------------------------
? FLAGS USED:
~   - YUUKI_1: Yuuki decided to get along with the player.
~   - PROF_DECANE_1: The professor has sent the player to get the chemical.
* -----------------------------------------------------------------------------
! FLOW:
^   1. Only runs if YUUKI_1 is set but PROF_DECANE_1 is NOT set.
^   2. Player enters floor 1, professor notices and introduces herself.
^   3. Professor explains your situation.
^   4. Yuuki comes downstairs and asks why you haven't eaten food despite her invitation.
^   5. Professor calms Yuuki down and failed.
^   6. Professor asks you politely to go to the nextdoor house while she calms Yuuki down.
^   7. After the scene, PROF_DECANE_1 is set.
* -----------------------------------------------------------------------------
*/
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("YUUKI_1")
            .forbid("PROF_DECANE_1")
            .faceTowards(player, professorDecane)
            .camMove(cameraManager, -288, 0, 90)
            .camFocus(cameraManager, professorDecane)
            .speak(
                "DECANE",
                "Mmm, yes... I've finally created water.",
                "This 100% pure water is now ready to drink.",
                "Its hypotonicity is just right for the human body to disintegrate upon contact.",
                "A new era of water has begun.",
                "If only I could mass produce this..."
            )
            .react(professorDecane, cameraManager, Emotes.SURPRISE)
            .shout("DECANE","OH, WAIT...", cameraManager)
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
                "DECANE",
                "I guess I've made a mistake.",
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
            .react(professorDecane, cameraManager, Emotes.SMILE)
            .speak(
                "DECANE",
                "Ah! You're here! I've been waiting for you!",
                "I'm so glad to see you're... well, 'here' in one piece.",
                "I'm PROFESSOR DECANE, and I'm sorry for making you wait."
            )
            .setFlag(flagManager, "DECANE_KNOW")
            .react(professorDecane, cameraManager, Emotes.FRIENDLY)
            .wait(30)
            .face(professorDecane, FacingDirections.LEFT)
            .wait(20)
            .speak(
                "DECANE",
                "I've been busy with my research about the pure water.",
                "That explosion earlier might make you feel a bit disoriented, but don't worry.",
                "It's perfectly normal to feel like that after what you've been through.",
                "Even if it's only in your subconscious, you probably still remember what happened."
            )
            .wait(30)
            .waitEmote(player, cameraManager, 60)
            .speak("THINKING",
                "...",
                "What did she mean by that...?",
                "My subconscious memories...?"
            )
            .wait(20)
            .faceTowards(professorDecane, player)
            .react(professorDecane, cameraManager, Emotes.FRIENDLY)
            .speak(
                "DECANE",
                "Don't you worry. I know it's a lot to take in.",
                "But this PORBITAL TOWN is a lovely place. It's peaceful.",
                "Please make yourself at home, because this town will be your home for the rest of your life.",
                "No matter where you go, you'll always come back here.",
                "No matter how important the mission you will be doing is, you'll always return here.",
                "And there's no way you can escape from this fate.",
                "You will die here in 10 days."
            )
            .waitEmote(player, cameraManager, 60)
            .moveYthenX(player, 8, 7)
            .faceTowards(player, professorDecane)
            .move(professorDecane, 8, 4)
            .wait(20)
            .speak("DECANE",
                "...",
                "Why are you running away?"
            )
            .react(player, cameraManager, Emotes.TERRIFIED)
            .speak(
                "DECANE",
                "I know you're scared, but there's no escape from this.",
                "But well... Looking at the bright side, this place is the best place for your last days.",
                "There's me..., and my adopted daughter YUUKI.",
                "And there's a buch of cops walking around everywhere...",
                "...",
                "Yeah...",
                "It's the perfect place to die in, don't you think so?",
                "Please use the remaining 10 days of your life to its fullest.",
                "It's not a long time, but it's still long enough to do some research.",
                "So, make yourself at home!"
            )
            .moveXthenY(professorDecane, 9, 7)
            .faceTowards(professorDecane, player)
            .react(professorDecane, cameraManager, Emotes.FRIENDLY)
            .move(player, 4, 7)
            .faceTowards(player, professorDecane)
            .wait(20)
            .speak( new Dialogue( new String[]{
                "You're still scared of me...?",
                "I'm not that scary, am I...?"
            },
            "DECANE",
            new DialogueOption("Yes, you are.", new Dialogue(new String[]{
                "No, I am not.",
                "Nobody has ever called me scary, like... ever.",
                "I'm on your side! You know that, right?",
                "I am the one who saved you from the bomb, remember?",
                "It's a miracle already that you're still alive.",
                "10 days to live more than the 1,000 people who died in that bombing incident, including your family, friends, and everyone of your classmates.",
                "That's not so bad, right?"
            }, "DECANE")),
            new DialogueOption("This pokemon game is too dark...", new Dialogue(new String[]{
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
            }, "DECANE")))
            )
            .wait(20)
            .react(player, cameraManager, Emotes.TERRIFIED)
            .wait(20)
            .waitEmote(professorDecane, cameraManager, 60)
            .speak(new Dialogue( new String[]{
                "Hm...",
                "By the way... What's your zodiac sign? I'm a Gemini.",
            },
            "DECANE",
            new DialogueOption("Don't change topic!", new Dialogue(new String[]{
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
            }, "DECANE")),
            new DialogueOption("I'm a Gemini too.", new Dialogue(new String[]{
                "Oh really? What a coincidence!",
                "Geminis are known for their intelligence, you know.",
                "With all the data Google has from their search engine, the Gemini series models are unmatched.",
                "You know what I'm talking about, right?",
                "I couldn't talk to anyone here about the 3D world technologies, so you're the only one I can talk to.",
                "So please, if it doesn't bother you too much, please endure my ramblings a bit.",
                "This PROFESSOR DECANE of the n-variety is a bit of a nerd, so she's not good at talking to people, okay?",
                "Speaking of intelligence, the bomb incident I mentioned earlier involved two opposing factions..."
            }, "DECANE")),
            new DialogueOption("I'm a Charizard.", new Dialogue(new String[]{
                "A...",
                "Charizard...?",
                "I told you pokemon don't exist here, but if that's how you want to cope with reality, fine.",
                "Even a Charizard would have been helpless against the bomb.",
                "Not Mega Charizard X, not Mega Charizard Y, not Gigantamax Charizard.",
                "Not even Terastal Charizard would have been able to stop the bomb.",
                "The only person who can protect someone from that bomb is me, PROFESSOR DECANE of the n-variety.",
                "Though... I only had enough time to save you.",
                "...",
                "Be grateful."
            }, "DECANE")))
            )
            .wait(60)
            .parallel(new CutsceneBuilder()
                .music("Cave")
                .speak(
                    "DECANE",
                    "The whole mess began 10 years ago, when your 3D world was all happy and peaceful.",
                    "It was then when the Z faction figured out a way to break out of---"
                )
                .buildActions()
            )
            .wait(10)
            .sfx("BattleDamageSuper")
            .camShake(cameraManager, 60)
            .tp(yuuki, 10, 3, "porbital_town__house1_f1", overworldState)
            .sfx("BattleDamageSuper")
            .react(yuuki, cameraManager, Emotes.ANGRY)
            .move(yuuki, 10, 7)
            .parallel(new CutsceneBuilder()
                .react(professorDecane, cameraManager, Emotes.SURPRISE)
                .faceTowards(professorDecane, yuuki)
                .faceTowards(yuuki, professorDecane)
                .buildActions()
            )
            .music("BattleWild")
            .shout("YUUKI", "N-DECANE, WHY IS MY NEW FRIEND NOT EATING? I TOLD HIM TO COME DOWNSTAIRS TO EAT!", cameraManager)
            .shout("YUUKI", "DID YOU HOLD HIM HOSTAGE OR SOMETHING?", cameraManager)
            .shout("YUUKI", "DON'T YOU DARE TELL ME THAT YOU PLAN TO USE HIM FOR SOME SICK AND PERVERTED EXPERIMENT AGAIN!", cameraManager)
            .shout("YUUKI", "THIS IS THE FIFTH PERSON YOU BROUGHT HOME!", cameraManager)
            .shout("YUUKI", "FIFTH!!!", cameraManager)
            .shout("YUUKI", "NOT FIRST, NOT SECOND, NOT THIRD, NOT FOURTH, BUT FIFTH!!!", cameraManager)
            .shout("YUUKI", "SOME OUTSIDERS WOULD HAVE THOUGHT YOU ARE A PROFESSIONAL SEDUCER IF YOU KEEP THIS BEHAVIOR UP.", cameraManager) 
            .shout("YUUKI", "IF ONLY YOU CAN MAKE AS MUCH MONEY AS THOSE SEDUCERS DO, I WOULD HAVE BEEN RICH BY NOW!!!", cameraManager) 
            .musicStop()
            .wait(20)
            .waitEmote(professorDecane, cameraManager, 60)
            .faceTowards(professorDecane, player)
            .wait(20)
            .speak(new Dialogue( new String[]{
                "...What happened?",
                "W-Why is she this angry? Do you know?"
            },
            "DECANE",
            new DialogueOption("I don't know.",
                "Of course you didn't know... You just arrived here after all..."
            ),
            new DialogueOption("I'm supposed to be eating her pudding...",
                "The pudding...",
                "She asked you to eat it...?",
                "But...",
                "...",
                "But she already ate the pudding herself yesterday.",
                "...",
                "The empty pudding cup is in the cabinet...",
                "What's wrong with my adopted daughter???"
            ),
            new DialogueOption("She accidentally killed a shiny pokemon.",
                "She doesn't even know what a pokemon is..."
            ))
            )
            .parallel(new CutsceneBuilder()
                .music("BattleWild")
                .react(yuuki, cameraManager, Emotes.ANGRY)
                .shout("YUUKI", "N-DECANE!!!", cameraManager)
                .buildActions()
            )
            .faceTowards(professorDecane, yuuki)
            .wait(40)
            .shout("YUUKI", "IT WOULD BE FINE IF THAT PERSON ISN'T MY FRIEND, BUT WE GOT ALONG SO WELL!", cameraManager)
            .shout("YUUKI", "I WON'T ALLOW YOU TO DO ANYTHING TO HIM", cameraManager)
            .musicStop()
            .wait(20)
            .faceTowards(professorDecane, player)
            .wait(40)
            .speak(
                "DECANE",
                "I... I'm sorry for not raising her well.",
                "She's always like this when she's with someone else...",
                "Well... While I'm dealing with this little gremlin, how about you go to the house next door?",
                "My friend PROFESSOR CELLULOSE lives there. You should go over and introduce yourself..."
            )
            .wait(20)
            .parallel(new CutsceneBuilder()
                .music("BattleWild")
                .react(yuuki, cameraManager, Emotes.ANGRY)
                .shout("YUUKI", "I'M THE ONE TALKING WITH YOU, NOT THE PLAYER!", cameraManager)
                .buildActions()
            )
            .faceTowards(professorDecane, yuuki)
            .wait(60)
            .shout("YUUKI", "EXPLAIN YOUR SELF RIGHT NOW!\nI WON'T LET YOU GO EASILY THIS TIME!!!", cameraManager)
            .musicStop()
            .wait(20)
            .faceTowards(professorDecane, player)
            .wait(40)
            .parallel(new CutsceneBuilder()
                .react(professorDecane, cameraManager, Emotes.SAD)
                .speak(
                    "DECANE",
                    "...",
                    "PROFESSOR CELLULOSE has a special chemical to help calm her down.",
                    "Can you please go over there and bring it to me?",
                    "In the meantime, I'll do my best here.",
                    "My adopted daughter really can't calm down until she gets that chemical..."
                )
                .buildActions()
            )
            .wait(40)
            .faceTowards(professorDecane, yuuki)
            .wait(40)
            .parallel(new CutsceneBuilder()
                .react(yuuki, cameraManager, Emotes.MUSIC)
                .music("BattleWild")
                .shout("YUUKI", "GOOD LUCK, MY FRIEND! AND DON'T FORGET TO EAT THE PUDDING!", cameraManager)
                .buildActions()
            )
            .musicStop()
            .setFlag(flagManager, "PROF_DECANE_1")
            .buildCutscene(),
            getKeyLocation(10, 3, "porbital_town__house1_f1")
        );
    }

    private static void professorCellulose1(Map<String, List<Cutscene>> cutscenes, NPC professorCellulose, CameraManager cameraManager, Player player, FlagManager flagManager) {
/*
* -----------------------------------------------------------------------------
* CUTSCENE: Porbital Town - Professor Cellulose Introduction
* Location: Porbital Town House 2, Floor 1
* -----------------------------------------------------------------------------
? FLAGS USED:
~   - PROF_DECANE_1: The professor has sent the player to get the chemical.
~   - PROF_CELLULOSE_1: The player is told that the chemical is in Methanopolis.
* -----------------------------------------------------------------------------
! FLOW:
^   1. Only runs if PROF_DECANE_1 is set but PROF_CELLULOSE_1 is NOT set.
^   2. Player enters floor 1 of the second house, professor notices and introduces himself.
^   3. You explain your situation to the professor.
^   4. Professor asks you to go to Methanopolis to get the chemical.
^   5. After the scene, PROF_CELLULOSE_1 is set.
* -----------------------------------------------------------------------------
*/
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("PROF_DECANE_1")
            .forbid("PROF_CELLULOSE_1")
            .buildCutscene(),
            getKeyLocation(4, 9, "porbital_town__house2_f1")
        );
    }

    private static void director1(Map<String, List<Cutscene>> cutscenes, NPC director, CameraManager cameraManager, Player player, FlagManager flagManager) {
        /*
* -----------------------------------------------------------------------------
* CUTSCENE: Porbital Town - Director Introduction
* Location: Porbital Town Room
* -----------------------------------------------------------------------------
? FLAGS USED:
~   - PROF_DECANE_1: The professor has sent the player to get the chemical.
~   - DIRECTOR_1: The player has escaped the room.
* -----------------------------------------------------------------------------
! FLOW:
^   1. Only runs if PROF_DECANE_1 is set but DIRECTOR_1 is NOT set.
^   2. Player enters the room, director is excited that someone finally fell into his trap.
^   3. Director explains that this room cannot be escaped unless the will of the director is satisfied.
^   4. Director asks you to solve a puzzle to escape the room.
^   5. After the scene, DIRECTOR_1 is set.
* -----------------------------------------------------------------------------
*/

//* Director's puzzle - beginning cutscene
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("PROF_DECANE_1")
            .forbid("DIRECTOR_1_START", "DIRECTOR_1_FINISH")
            .react(director, cameraManager, Emotes.SURPRISE)
            .moveXthenY(director, 4, 8)
            .speak("DIRECTOR",
            "AHA! Yes!!!",
            "A visitor!!!"
            )
            .react(director, cameraManager, Emotes.SMILE)
            .speak("DIRECTOR",
                "I've been waiting for someone to come here for a long time.",
                "Welcome, welcome, to THE ROOM!!!",
                "I'm the DIRECTOR, and you will be the star of my next show! Aren't you excited?"
            )
            .setFlag(flagManager, "DIRECTOR_KNOW")
            .wait(30)
            .react(player, cameraManager, Emotes.QUESTION)
            .speak("THINKING",
                "What the hell is this place...?",
                "You was just breaking into someone's house without permission...\nYou don't deserve this at all...",
                "..."
            
            )
            .wait(60)
            .speak("DIRECTOR",
                "Please follow me."
            )
            .wait(30)
            .parallel(new CutsceneBuilder()
                .moveYthenX(director, 3, 4)
                .sequential(new CutsceneBuilder()
                    .wait(30)
                    .move(player, 4, 4)
                    .wait(30)
                    .faceTowards(player, director)
                    .buildActions()
                )
                .buildActions()
            )
            .wait(20)
            .faceTowards(director, player)
            .speak("DIRECTOR",
                "The project is called \"The room you cannot leave without satisfying me!\"",
                "You have to satisfy me or else you will be trapped here forever!",
                "...",
                "...",
                "You don't believe me, do you?",
                "Why don't you try walking out now?"
            )
            .setFlag(flagManager, "DIRECTOR_1_START")
            .buildCutscene(),
            getKeyLocation(4, 9, "porbital_town__room")
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("DIRECTOR_1_START")
            .forbid("DIRECTOR_1_FINISH")
            .waitEmote(player, cameraManager, 60)
            .speak("THINKING",
                "You feel a strange force pushing you away from the door...",
                "You cannot move your feet...",
                "Your feet starts moving on its own, in an opposite direction."
            )
            .wait(20)
            .face(player, FacingDirections.UP)
            .wait(20)
            .move(player, 4, 7)
            .buildCutscene(),
            getKeyLocation(3, 9, "porbital_town__room"),
            getKeyLocation(4, 9, "porbital_town__room"),
            getKeyLocation(5, 9, "porbital_town__room"),
            getKeyLocation(5, 10, "porbital_town__room")
        );

//* Director's puzzle - talk after the puzzle starts
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("DIRECTOR_1_START")
            .forbid("DIRECTOR_1_FINISH")
            .faceTowards(director, player)
            .react(director, cameraManager, Emotes.SMILE)
            .speak(new Dialogue(new String[]{
                "Hoho! You cannot go out of this room, can you?",
                "Then, try satisfying me!",
                "..."
            }, "DIRECTOR",
            new DialogueOption("I'm not going to satisfy you.", new Dialogue(new String[]{
                "That's too bad.",
                "You'll just stuck here then.",
                "I heard you only have 10 days left to live, right?",
                "Never thought you'd just throw away your life like that.",
                "I feel sorry for you.",
                "..."
            }, "DIRECTOR")),
            new DialogueOption("How can I satisfy you?", new Dialogue(new String[]{
                "I'm not sure what you mean...",
                "Do something you think can satisfy me?",
                "It's that simple!",
            }, "DIRECTOR")),
            new DialogueOption("Let's do a pokemon battle!", new Dialogue(new String[]{
                "What's... a pokemon battle?",
                "Wait, do you mean alchemist's synthesis battle?",
                "I'm not an alchemist, so I don't have a deck...",
                "Too bad, you cannot satisfy me that way!"
            }, "DIRECTOR")),
            new DialogueOption("Please help me out... I have a sick child...", new Dialogue(new String[]{
                "A sick child?",
                "Well, I guess I can give you a hint.",
                "Try looking around and interact with stuff in this room.",
                "There's a cabinet, a key pedestal, and a PC.",
                "I'm sure you can escape with those tools!"
            }, "DIRECTOR"))
            ))
            .buildCutscene(),
            getKeyNPC(director)
        );

//* Director's puzzle - PC cutscene
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("DIRECTOR_1_START")
            .speak(new Dialogue(new String[]{
                "You're no stranger to this PC storage machine...",
                "You've used it before in pokemon games.",
                "You know the rules and commands of it... which one do you want to execute?"
            }, "THINKING",
            new DialogueOption("View BOX 1",
                new Dialogue(new String[]{"You choose to view BOX 1",},"THINKING"),
                ()->FlagManager.getInstance().addFlag("VIEW_BOX_1")
            ),
            new DialogueOption("View BOX 2",
                new Dialogue(new String[]{"You choose to view BOX 2",},"THINKING"),
                ()->FlagManager.getInstance().addFlag("VIEW_BOX_2")
            ),
            new DialogueOption("View BOX 3",
                new Dialogue(new String[]{"You choose to view BOX 3",},"THINKING"),
                ()->FlagManager.getInstance().addFlag("VIEW_BOX_3")
            ),
            new DialogueOption("View BOX 4",
                new Dialogue(new String[]{"You choose to view BOX 4",},"THINKING"),
                ()->FlagManager.getInstance().addFlag("VIEW_BOX_4")
            ),
            new DialogueOption("View BOX 5",
                new Dialogue(new String[]{"You choose to view BOX 5",},"THINKING"),
                ()->FlagManager.getInstance().addFlag("VIEW_BOX_5")
            )))
            .condition("VIEW_BOX_1", new CutsceneBuilder()
                .speak("THINKING",
                    "You see an image of an Italian brainrot orange."
                )
                .showImage("/images/DIRECTOR_1_box_1.png")
                .speak("THINKING",
                    "You are confused by the image...",
                    "You also sense a citrusy taste in your mouth..."
                )
                .removeFlag(flagManager, "VIEW_BOX_1")
                .buildActions()
            )
            .condition("VIEW_BOX_2", new CutsceneBuilder()
                .speak("THINKING",
                    "You see an image of a... Pikachu?"
                )
                .showImage("/images/DIRECTOR_1_box_2.png")
                .speak("THINKING",
                    "You are confused by the image...",
                    "You also don't want your arms to be amputated and your body to be turned blue...\nlike Pikachu..."
                )
                .removeFlag(flagManager, "VIEW_BOX_2")
                .buildActions()
            )
            .condition("VIEW_BOX_3", new CutsceneBuilder()
                .speak("THINKING",
                    "You see an image of a beach scenery."
                )
                .showImage("/images/DIRECTOR_1_box_3.png")
                .speak("THINKING",
                    "You are confused by the image...",
                    "You also notice a faint fragrance of rose oils..."    
                )
                .removeFlag(flagManager, "VIEW_BOX_3")
                .buildActions()
            )
            .condition("VIEW_BOX_4", new CutsceneBuilder()
                .speak("THINKING",
                    "You see an image of a bald Larry from Scarlet & Violet Elite 4."  
                )
                .showImage("/images/DIRECTOR_1_box_4.png")
                .speak("THINKING",
                    "You are confused by the image...",
                    "You also feel a little bit more formally in charge of this puzzle..."
                )
                .removeFlag(flagManager, "VIEW_BOX_4")
                .buildActions()
            )
            .condition("VIEW_BOX_5", new CutsceneBuilder() 
                .speak("THINKING",
                    "You see an image of a pitchfork and a battery."
                )
                .showImage("/images/DIRECTOR_1_box_5.png")
                .speak("THINKING",
                    "You are confused by the image...",
                    "You also feel the vibration in your core..."
                )
                .removeFlag(flagManager, "VIEW_BOX_5")
                .buildActions()
            )
            .buildCutscene(),
            getKeyLook(12, 2, "porbital_town__room")
        );

//* Director's puzzle - cabinet locked
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("DIRECTOR_1_START")
            .forbid("DIRECTOR_1_GOT_KEY")
            .speak("THINKING",
                "The cabinet is locked...",
                "You need to find the key to open it..."
            )
            .buildCutscene(),
            getKeyLook(1, 2, "porbital_town__room")
        );

//* Director's puzzle - key pedestal mechanism
        NumericInputTemplate.addNumericInput(
            cutscenes, "porbital_town__room",
            2, 2, List.of(3, 5, 3, 1, 9),
            "DIRECTOR_1_GOT_KEY",
            new String[]{"DIRECTOR_1_START"},
            new String[]{"DIRECTOR_1_GOT_KEY"}
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("DIRECTOR_1_START", "DIRECTOR_1_GOT_KEY")
            .speak("THINKING",
                "You have already entered the correct code...",
                "You have the key now."
            )
            .buildCutscene(),
            getKeyLook(2, 2, "porbital_town__room")
        );

//* Director's puzzle - cabinet unlocked
        addCutscene(cutscenes, new CutsceneBuilder()
        .require("DIRECTOR_1_START", "DIRECTOR_1_GOT_KEY")
        .forbid("DIRECTOR_1_FINISH")
        .sfx("GUIMenuOpen")
        .speak("THINKING",
            "You used the key you got from the pedestal.",
            "The cabinet door creaks open...",
            "Inside, there's nothing but a single, large, comically red button.",
            "There are no labels. No instructions.",
            "This has to be it. The final step."
        )
        .wait(60)
        .speak("THINKING",
            "You take a deep breath and press the button."
        )
        .wait(30)
        .sfx("GUIConfirm")
        .wait(60)
        .waitEmote(player, cameraManager, 60)
        .speak("THINKING",
            "...",
            "Nothing happened.",
            "...",
            "Was that it? Did you solve it?",
            "Did you... satisfy him...?"
        )
        .wait(60)
        .faceTowards(player, director)
        .wait(30)
        .move(director, 4, 3)
        .faceTowards(director, player)
        .react(director, cameraManager, Emotes.SMILE)
        .speak("DIRECTOR", 
            "Hee hee hee...",
            "Wait for it... Any second now..."
        )
        .wait(150)
        .speak("THINKING",
            "..."
        )
        .wait(60)
        .speak("DIRECTOR",
            "It's about to begin..."
        )
        .wait(60)
        .waitEmote(player, cameraManager, 60)
        .music("Rickroll")
        .wait(60)
        .parallel(new CutsceneBuilder()
            .react(director, cameraManager, Emotes.MUSIC)
            .shout("DIRECTOR", "YES! IT'S HERE NOW!!!", cameraManager)
            .buildActions()
        )
        .shout("DIRECTOR", "THIS IS CRAZY!!! YEAH!!!", cameraManager)
        .wait(60)
        .parallel(new CutsceneBuilder()
            .sequential(new CutsceneBuilder()
                .move(director, 4, 4)
                .face(director, FacingDirections.DOWN)
                .wait(30)
                .move(director, 5, 4)
                .face(director, FacingDirections.LEFT)
                .wait(20)
                .move(director, 4, 5)
                .face(director, FacingDirections.UP)
                .wait(20)
                .move(director, 3, 3)
                .face(director, FacingDirections.DOWN)
                .buildActions()
            )
            .react(director, cameraManager, Emotes.MUSIC)
            .buildActions()
        )
        .speak("DIRECTOR",
            "After a deliberate build-up, the climax is here!",
            "The subversion of expectation is the key to everything in life.",
            "To create a timeless masterpiece, you have to expect the unexpected!!!"
        )
        .wait(30)
        .faceTowards(director, player)
        .react(director, cameraManager, Emotes.SMILE)
        .speak("DIRECTOR",
            "I GOT YOU! You completely fell for it, didn't you?",
            "Ah... That look on your face is priceless!\nYou CANNOT ever predict that this was coming, can you?",
            "What a great performance!"
        )
        .react(director, cameraManager, Emotes.LOVE)
        .wait(60)
        .waitEmote(player, cameraManager, 60)
        .wait(60)
        .parallel(new CutsceneBuilder()
            .react(player, cameraManager, Emotes.ANGRY)
            .speak("THINKING",
                "...",
                "...",
                "...",
                "So...",
                "This was it.",
                "What a waste of time.",
                "The puzzle you spent so much time solving...",
                "The PC boxes you spent so much time looking at...",
                "All of this time-consuming nonsense... to just get Rickrolled.",
                "...",
                "...",
                "He put you through all this just to Rickroll you.",
                "You have ten days left to live... and you just wasted twenty minutes of it on this...",
                "..."
            )
            .buildActions()
        )
        .wait(150)
        .musicStop()
        .wait(60)
        .faceTowards(director, player)
        .wait(30)
        .react(director, cameraManager, Emotes.QUESTION)
        .speak("DIRECTOR",
            "...",
            "You... you're not laughing?",
            "H-How could this be...?",
            "But... isn't this the pinnacle of performance art!",
            "The impeccable, unpredictable, and unexpected subversion of improbable expectation experience in a perplexingly manner!",
            "Isn't... isn't this what everyone wants?",
            "...",
            "...",
            "It's... it's funny... right?"
        )
        .wait(120)
        .face(player, FacingDirections.LEFT)
        .wait(60)
        .speak("THINKING",
            "You're not a child anymore.",
            "This isn't funny. It's just... disappointing.",
            "You've experienced rickrolling for countless times, and according to your experience, this one is the worst one yet."
        )
        .face(player, FacingDirections.RIGHT)
        .wait(30)
        .parallel(new CutsceneBuilder()
            .sequential(new CutsceneBuilder()
                .moveYthenX(player, 4, 4)
                .move(player, 4, 7)
                .buildActions()
            )
            .react(player, cameraManager, Emotes.ANGRY)
            .sequential(new CutsceneBuilder()
                .wait(30)
                .react(director, cameraManager, Emotes.SURPRISE)
                .buildActions()
            )
            .buildActions()
        )
        .parallel(new CutsceneBuilder()
            .camShake(cameraManager, 20)
            .sfx("BattleDamageNormal")
            .buildActions()
        )
        .parallel(new CutsceneBuilder()
            .react(director, cameraManager, Emotes.TERRIFIED)
            .shout("DIRECTOR", "W-WAIT!", cameraManager)
            .buildActions()
        )
        .moveYthenX(director, 4, 8)
        .wait(20)
        .faceTowards(director, player)
        .wait(30)
        .waitEmote(player, cameraManager, 60)
        .wait(20)
        .parallel(new CutsceneBuilder()
            .react(director, cameraManager, Emotes.SAD)
            .speak("DIRECTOR",
                "P-Please... don't go.",
                "I... I'm sorry."
            )
            .buildActions()
        )
        .wait(30)
        .waitEmote(player, cameraManager, 60)
        .wait(60)
        .speak("DIRECTOR",
            "I... I thought it was really clever. I really do.",
            "I spent so long setting these puzzles up... In my head, they're the best",
            "Like... I'm really proud of them.",
            "And since I can't rickroll myself, I need a subject.",
            "I need you... who are the first person to enter my room in the last ten years.",
            "If... If I wasn't doing a good job as a director, sorry...",
            "I will take responsibility for this."
        )
        .wait(20)
        .move(director, 8, 8)
        .wait(20)
        .faceTowards(director, player)
        .wait(20)
        .speak("DIRECTOR",
            "You can leave if you want to, my star.",
            "I only wanted to express my feelings...",
            "The feeling that life is absurd. That it's as if we're being watched for entertainments. That it's unpredictable and not fair.",
            "And... to fight this absurdity, I need to fight back with even more absurdity.",
            "...",
            "You may not understand... but I'm glad you were here.",
            "Thank you... thank you a lot for being the star of my show, when nobody else would even watch."
        )
        .wait(60)
        .speak("THINKING",
            "His... energy is gone now.",
            "All that's left is just sadness and guilt...",
            "..."
        )
        .wait(60)
        .moveYthenX(director, 4, 5)
        .wait(20)
        .faceTowards(player, director)
        .face(director, FacingDirections.UP)
        .wait(30)
        .speak("DIRECTOR",
            "You can go now.",
            "The door was never locked. This is never a room you cannot leave. You could just leave anytime...",
            "If it weren't for me...",
            "The force pushing you back... that was just me, pressing a button in my pocket.",
            "If you tried punching me and knocking me out, you can just leave.",
            "You were never really trapped here.",
            "I just need you to believe you were.",
            "..."
        )
        .wait(30)
        .faceTowards(director, player)
        .wait(30)
        .react(director, cameraManager, Emotes.FRIENDLY)
        .speak("DIRECTOR",
            "Haha... Funny, isn't it? The most restrictive traps are the ones in our own minds.",
            "I guess I'll still be stuck in mine for quite a while..."
        )
        .wait(30)
        .face(director, FacingDirections.UP)
        .wait(30)
        .setFlag(flagManager, "DIRECTOR_1_FINISH")
        .buildCutscene(),
        getKeyLook(1, 2, "porbital_town__room")
        );
    }
}
