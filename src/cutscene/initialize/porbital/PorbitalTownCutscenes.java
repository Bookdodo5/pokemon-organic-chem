package cutscene.initialize.porbital;

import cutscene.Cutscene;
import cutscene.CutsceneAction;
import cutscene.CutsceneBuilder;
import cutscene.Emotes;
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
import pokedex.PlayerDeckManager;
import tile.MapManager;

public class PorbitalTownCutscenes extends CutsceneTemplate {

    public static void initialize(Map<String, List<Cutscene>> cutscenes, StateManager stateManager, OverworldState overworldState, NPCManager npcManager, CameraManager cameraManager, Player player, MapManager mapManager, PlayerDeckManager playerDeckManager) {
        NPC yuuki = npcManager.getNPC("Yuuki");
        NPC professorDecane = npcManager.getNPC("ProfDecane");
        NPC professorCellulose = npcManager.getNPC("ProfCellulose");
        NPC director = npcManager.getNPC("Director");
        NPC kusari = npcManager.getNPC("Kusari");
        NPC chlorophyll = npcManager.getNPC("Chlorophyll");

        PorbitalTownObjects.initialize(cutscenes, cameraManager, player, playerDeckManager);
        PorbitalTownTalks.initialize(cutscenes, npcManager, cameraManager, player);
        yuuki1(cutscenes, yuuki, overworldState, cameraManager, player);
        professorDecane1(cutscenes, professorDecane, yuuki, overworldState, cameraManager, player);
        professorCellulose1(cutscenes, professorCellulose, kusari, chlorophyll, yuuki, professorDecane, stateManager, cameraManager, player, overworldState, mapManager);
        director1(cutscenes, director, cameraManager, player);
    }

    private static void yuuki1(Map<String, List<Cutscene>> cutscenes, NPC yuuki, OverworldState overworldState, CameraManager cameraManager, Player player) {
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
                "No... It's not that you have no recollection of this place.",
                "You're not sure if you're dreaming or not.",
                "...",
                "..."
            )
            .react(player, cameraManager, Emotes.QUESTION)
            .speak("THINKING",
                "This...",
                "This is a pokemon game... right?",
                "...",
                "If so... The first thing you're supposed to do is to go downstairs and...",
                "get some starter pokemon---"
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
            .shout("YUUKI","A HOMELESS SHELTER!!!", cameraManager)
            .shout("YUUKI","AND THAT MEANS I'M ALREADY AN ADOPTED CHILD.", cameraManager)
            .shout("YUUKI","SO WHEN SHE SAID SHE WAS THINKING ABOUT GETTING ME ADOPTED,", cameraManager)
            .shout("YUUKI","SHE ACTUALLY MEANT SHE WAS GOING TO GET THE PAPER WORK DONE!!!", cameraManager)
            .shout("YUUKI","NOTHING ABOUT MY LIFE WILL ACTUALLY CHANGE.", cameraManager)
            .shout("YUUKI","NOTHING!!!!", cameraManager)
            .shout("YUUKI","YOU ARE JUST A WORTHLESS EXTRA CHARACTER IN MY LIFE.", cameraManager)
            .shout("YUUKI","YOU ARE JUST A PASSERBY, SOMEONE THEY HIRE TO JUST FILL UP THE SCENE.", cameraManager)
            .shout("YUUKI","YOU HAVE NO ROLE IN MY STORY.", cameraManager)
            .shout("YUUKI","YOU HAVE NO ROLE IN ANYBODY'S STORY.", cameraManager)
            .shout("YUUKI","EVEN IF YOU ARE GONE, NOBODY WOULD EVEN CARE.", cameraManager)
            .shout("YUUKI","SO...", cameraManager)
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
            .setFlag("YUUKI_KNOW")
            .react(yuuki, cameraManager, Emotes.MUSIC)
            .react(player, cameraManager, Emotes.QUESTION)
            .speak(
                "YUUKI",
                "And to celebrate our friendship, I allow you to eat my milk pudding in the cabinet!",
                "Go downstairs and get it!",
                "Enjoy!!"
                )
            .setFlag("YUUKI_1")
            .buildCutscene(),
            getKeyLocation(7, 6, "porbital_town__house1_f2")
        );

        //* CUTSCENE: Porbital Town - Yuuki Talk After Introduction
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
                "Maybe you should also explore my room while you're at it!",
                "I'll be waiting for you here!"
            )
            .buildCutscene(),
            getKeyNPC(yuuki)
        );
    }

    private static void professorDecane1(Map<String, List<Cutscene>> cutscenes, NPC professorDecane, NPC yuuki, OverworldState overworldState, CameraManager cameraManager, Player player) {
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
                "I'm so glad to see you're... well, \"here\" in one piece.",
                "I'm PROFESSOR DECANE, and I'm sorry for making you wait."
            )
            .setFlag("DECANE_KNOW")
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
            .setFlag("PROF_DECANE_1")
            .buildCutscene(),
            getKeyLocation(10, 3, "porbital_town__house1_f1")
        );

        //* CUTSCENE: Porbital Town - Decane and Child Waiting For Chemical
        // For Professor Decane
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("PROF_DECANE_1")
            .forbid("CHLOROPHYLL_2")
            .faceTowards(professorDecane, player)
            .speak("DECANE",
                "Please, you must hurry to Professor Cellulose's house.",
                "I'm not sure how much longer I can keep her occupied."
            )
            .faceTowards(professorDecane, yuuki)
            .buildCutscene(),
            getKeyNPC(professorDecane)
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("CHLOROPHYLL_2")
            .faceTowards(professorDecane, player)
            .speak("DECANE",
                "I was beaten by my own adoptive daughter...",
                "I'm not sure what's going on anymore...",
                "..."
            )
            .wait(90)
            .speak("DECANE",
                "Oh, you wanna hear the continuation of that bombing incident?",
                "Only if YUUKI didn't interrupt me back then...",
                "...",
                "...",
                "Really, right now I don't have the energy to explain it...",
                "Can you come back later?"
            )
            .wait(30)
            .face(professorDecane, FacingDirections.UP)
            .wait(30)
            .speak("THINKING",
                "The TV is on...",
                "PROF. DECANE is watching an anime...",
                "Half metal alchemist...?"
            )
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

    private static void professorCellulose1(Map<String, List<Cutscene>> cutscenes, NPC professorCellulose, NPC kusari, NPC chlorophyll, NPC yuuki, NPC professorDecane, StateManager stateManager, CameraManager cameraManager, Player player, OverworldState overworldState, MapManager mapManager) {
/*
* -----------------------------------------------------------------------------
* CUTSCENE: Porbital Town - Professor Cellulose Introduction
* Location: Porbital Town House 2
* -----------------------------------------------------------------------------
? FLAGS USED:
~   - PROF_DECANE_1: The professor has sent the player to get the chemical.
~   - PROF_CELLULOSE_1: The player meets the professor and notices his repetitive behavior.
~   - KUSARI_1: The player has met Kusari and notices he's reciting Pokemon Emerald cutscenes.
~   - CHLOROPHYLL_1: The player has defeated Chlorophyll.
~   - CHLOROPHYLL_WAIT_FOR_REMATCH: The player has lost the battle.
~   - DECK_FOUND: The player has found the Alchemist's Deck.
~   - BATTLE_WIN: The player has won the battle.
~   - BATTLE_LOSE: The player has lost the battle.
* -----------------------------------------------------------------------------
! FLOW:
^   1. Only runs if PROF_DECANE_1 is set but PROF_CELLULOSE_1 is NOT set.
^   2. Player enters floor 1 of the second house, professor notices and introduces himself with repetitive dialogue.
^   3. Professor asks you to go upstairs and find his son.
^   4. You go upstairs and find Kusari, who's acting out Pokemon Emerald cutscenes.
^   5. You go downstairs and find Chlorophyll chasing Cellulose around, and you battle her.
^   6. After the battle, Cellulose continues his acting of Pokemon cutscene, but Chlorophyll interrupts him and tell the player the whole situation.
^   7. Chlorophyll explains that Kusari and Cellulose has condition causing them to act out video game scenes. Originally, it's only Kusari, but lately Cellulose is also affected.
^   8. She also explained that the medication that helps Kusari is the same chemical Professor Decane needs for Yuuki, after the player explained his situation.
^   9. Chlorophyll asks the player to go to Methanopolis Pharmacy to get the medication (Some actual molecule).
^   10. After the scene, KUSARI_1 is set and both characters return to normal behavior. CHLOROPHYLL_1 is set.
* -----------------------------------------------------------------------------
*/
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("PROF_DECANE_1")
            .forbid("PROF_CELLULOSE_1")
            .faceTowards(professorCellulose, player)
            .react(professorCellulose, cameraManager, Emotes.SURPRISE)
            .moveYthenX(professorCellulose, 5, 9)
            .faceTowards(professorCellulose, player)
            .wait(30)
            .faceTowards(player, professorCellulose)
            .speak("CELLULOSE",
                "Oh, hello. And you are...?",
                "... ... ... ... ...\n... ... ... ... ...",
                "Oh, you're PLAYER, our new nextdoor neighbor! Hi!",
                "I have a son about the same age as you.",
                "My son was excited about making a new friend.",
                "He's upstairs, I think.",
                "..."
            )
            .wait(90)
            .speak("CELLULOSE",
                "My son was excited about making a new friend.",
                "He's upstairs, I think.",
                "..."
            )
            .wait(30)
            .waitEmote(player, cameraManager, 60)
            .speak("THINKING",
                "...Why is he repeating himself?",
                "And... Is this professor Cellulose?"
            )
            .wait(60)
            .speak(new Dialogue(new String[]{
                "My son was excited about making a new friend.",
                "He's upstairs, I think.",
                "..."
            }, "CELLULOSE",
            new DialogueOption("...", new Dialogue(new String[]{
                "...",
                "My son was excited about making a new friend.",
                "He's upstairs, I think.",
                "..."
            }, "CELLULOSE")),
            new DialogueOption("Why are you talking like that?", new Dialogue(new String[]{
                "...",
                "My son was excited about making a new friend.",
                "He's upstairs, I think.",
                "..."
            }, "CELLULOSE")),
            new DialogueOption("Give me the chemical!", new Dialogue(new String[]{
                "...",
                "My son was excited about making a new friend.",
                "He's upstairs, I think.",
                "..."
            }, "CELLULOSE")),
            new DialogueOption("You are CELLULOSE, right?", new Dialogue(new String[]{
                "...",
                "My son was excited about making a new friend.",
                "He's upstairs, I think.", 
                "..."
            }, "CELLULOSE"))))
            .setFlag("CELLULOSE_KNOW")
            .setFlag("PROF_CELLULOSE_1")
            .buildCutscene(),
            getKeyLocation(4, 9, "porbital_town__house2_f1")
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("PROF_CELLULOSE_1")
            .forbid("KUSARI_1")
            .faceTowards(professorCellulose, player)
            .speak("CELLULOSE",
                "...",
                "My son was excited about making a new friend.",
                "He's upstairs, I think.",
                "..."
            )
            .buildCutscene(),
            getKeyNPC(professorCellulose)
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("PROF_CELLULOSE_1")
            .forbid("KUSARI_1")
            .faceTowards(kusari, player)
            .faceTowards(player, kusari)
            .react(kusari, cameraManager, Emotes.SURPRISE)
            .wait(30)
            .speak("KUSARI",
                "Huh?\nWho... Who are you?",
                "... ... ... ... ...\n... ... ... ... ...",
                "Oh, you're that new person. So your move was today.",
                "Um... I'm Kusari.\nGlad to meet you!"
            )
            .setFlag("KUSARI_KNOW")
            .speak("KUSARI",
                "I...\nI have this dream of becoming friends with chemicals all over the world.",
                "I.. I heard about you, the PLAYER, from my dad, PROF. CELLULOSE.",
                "I was hoping that you would be nice, PLAYER, and that we could be friends.",
                "Oh, this is silly, isn't it?\nI... I've just met you, PLAYER.",
                "Eheheh...",
                "Teehee.",
                "LOL",
                "LMAO",
                "wwwwwwwww",
                "555555655656565566",
                "...",
                "...",
                "Oh! I forgot!",
                "I was supposed to go help dad catch some rare chemicals!",
                "Player, I'll catch you later!"
            )
            .wait(30)
            .move(kusari, 5, 4)
            .sfx("PlayerBump")
            .wait(200)
            .parallel(new CutsceneBuilder()
                .waitEmote(kusari, cameraManager, 60)
                .waitEmote(player, cameraManager, 60)
                .buildActions()
            )
            .wait(30)
            .move(kusari, 6, 4)
            .faceTowards(kusari, player)
            .wait(60)
            .move(kusari, 5, 4)
            .sfx("PlayerBump")
            .wait(30)
            .move(kusari, 6, 4)
            .faceTowards(kusari, player)
            .wait(60)
            .move(kusari, 5, 4)
            .sfx("PlayerBump")
            .wait(30)
            .move(kusari, 6, 4)
            .faceTowards(kusari, player)
            .wait(60)
            .move(kusari, 5, 4)
            .sfx("PlayerBump")
            .wait(30)
            .move(kusari, 6, 4)
            .faceTowards(kusari, player)
            .wait(90)
            .parallel(new CutsceneBuilder()
                .react(kusari, cameraManager, Emotes.FRIENDLY)
                .speak("KUSARI",
                    "...",
                    "Oh! I forgot!",
                    "I was supposed to go help dad catch some rare chemicals!",
                    "Player, I'll catch you later!",
                    "..."
                )
                .buildActions()
            )
            .wait(60)
            .move(kusari, 5, 4)
            .sfx("PlayerBump")
            .wait(120)
            .waitEmote(player, cameraManager, 60)
            .parallel(new CutsceneBuilder()
                .emote(kusari, 150, Emotes.TERRIFIED, cameraManager)
                .speak("KUSARI",
                    "...",
                    "Oh! I forgot!",
                    "I was supposed to go help dad catch some rare chemicals!",
                    "Player, I'll catch you later!",
                    "..."
                )
                .buildActions()
            )
            .wait(60)
            .speak("THINKING",
                "You... have reached a disturbing conclusion.",
                "Everyone in this town is completely insane.",
                "And you need to spend the last days of your life with them.",
                "..."
            )
            .wait(30)
            .move(kusari, 6, 4)
            .faceTowards(kusari, player)
            .wait(60)
            .move(kusari, 5, 4)
            .sfx("PlayerBump")
            .wait(30)
            .parallel(new CutsceneBuilder()
                .waitEmote(kusari, cameraManager, 60)
                .waitEmote(player, cameraManager, 60)
                .buildActions()
            )
            .speak("THINKING",
                "These two people in this house are literally acting out Pokemon game cutscenes.",
                "Word for word. Line for line. With no differences at all.",
                "But Pokemon doesn't exist here. This isn't a game.",
                "This is supposed to be reality.",
                "What the hell is happening to this world??"
            )
            .wait(30)
            .move(player, 4, 5)
            .wait(20)
            .face(player, FacingDirections.UP)
            .wait(30)
            .react(kusari, cameraManager, Emotes.SMILE)
            .speak("KUSARI",
                "Oh! I forgot!",
                "I was supposed to go help dad catch some rare chemicals!",
                "Player, I'll catch you later!"
            )
            .wait(30)
            .moveXthenY(kusari, 3, 6)
            .move(kusari, 1, 6)
            .wait(20)
            .face(kusari, FacingDirections.UP)
            .setFlag("KUSARI_1")
            .buildCutscene(),
            getKeyLocation(4, 4, "porbital_town__house2_f2")
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("KUSARI_1")
            .forbid("PROF_CELLULOSE_2")
            .face(kusari, FacingDirections.UP)
            .speak("KUSARI",
                "Chemicals fully restored!\nItems read, and..."
            )
            .buildCutscene(),
            getKeyNPC(kusari)
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("KUSARI_1")
            .forbid("PROF_CELLULOSE_2")
            .setFlag("CHLOROPHYLL_KNOW")
            .music("BattleWild")
            .execute(()->player.setRunning())
            .execute(()->chlorophyll.setRunning())
            .execute(()->professorCellulose.setRunning())
            .execute(()->mapManager.changeMapMusic("porbital_town__house2_f1", "BattleWild"))
            .tp(chlorophyll, 5, 10, "porbital_town__house2_f1", overworldState)
            .faceTowards(chlorophyll, professorCellulose)
            .shout("CELLULOSE", "H-Help me!", cameraManager)
            .moveYthenX(player, 9, 9)
            .parallel(new CutsceneBuilder()
                .sequential(new CutsceneBuilder()
                    .moveYthenX(professorCellulose, 7, 8)
                    .moveYthenX(professorCellulose, 5, 10)
                    .moveYthenX(professorCellulose, 7, 8)
                    .moveYthenX(professorCellulose, 5, 10)
                    .moveYthenX(professorCellulose, 7, 8)
                    .moveYthenX(professorCellulose, 5, 10)
                    .moveYthenX(professorCellulose, 7, 8)
                    .moveYthenX(professorCellulose, 5, 10)
                    .moveYthenX(professorCellulose, 7, 8)
                    .buildActions()
                )
                .sequential(new CutsceneBuilder()
                    .wait(10)
                    .moveYthenX(chlorophyll, 7, 8)
                    .moveYthenX(chlorophyll, 5, 10)
                    .moveYthenX(chlorophyll, 7, 8)
                    .moveYthenX(chlorophyll, 5, 10)
                    .moveYthenX(chlorophyll, 7, 8)
                    .moveYthenX(chlorophyll, 5, 10)
                    .moveYthenX(chlorophyll, 7, 8)
                    .moveYthenX(chlorophyll, 5, 10)
                    .moveYthenX(chlorophyll, 6, 8)
                    .faceTowards(kusari, player)
                    .buildActions()
                )
                .buildActions()
            )
            .execute(()->player.setWalking())
            .execute(()->chlorophyll.setWalking())
            .execute(()->professorCellulose.setWalking())
            .speak("CELLULOSE",
                "Hello! You over there!\nPlease! Help!",
                "In one of the OFFERING BINS TO THE DEAD, there's an ALCHEMIST'S DECK!"
            )
            .waitEmote(player, cameraManager, 60)
            .speak("THINKING",
                "...",
                "They're still doing that...\nPokemon game cutscenes...",
                "This one is when PROF. BIRCH is attacked by a wild Pokemon.",
                "But well...",
                "For your own benefits, you decided to help him.",
                "At least, you'll get to learn the mechanics of this world's battle system."
            )
            .setFlag("PROF_CELLULOSE_2")
            .buildCutscene(),
            getKeyLocation(10, 3, "porbital_town__house2_f1")
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("PROF_CELLULOSE_2")
            .forbid("CHLOROPHYLL_2")
            .faceTowards(professorCellulose, player)
            .speak("CELLULOSE",
                "Hello! You over there!\nPlease! Help!",
                "In one of the OFFERING BINS TO THE DEAD, there's an ALCHEMIST'S DECK!"
            )
            .waitEmote(player, cameraManager, 60)
            .speak("THINKING",
                "...",
                "This guy's sprite didn't even move.",
                "I can't sense the panic at all.",
                "Curse this world and its developers...\nIf they want to copy Pokemon, they should at least copy the details too..."
            )
            .buildCutscene(),
            getKeyNPC(professorCellulose)
        );
        
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "It's an empty offering bin.",
                "The picture above is labeled \"Marie Curie\""
            )
            .buildCutscene(),
            getKeyLook(5, 3, "porbital_town__house2_f1")
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "It's an empty offering bin.",
                "The picture above is labeled \"Alfred Nobel\""
            )
            .buildCutscene(),
            getKeyLook(6, 3, "porbital_town__house2_f1")
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "It's an empty offering bin.",
                "The picture above is labeled \"John Dalton\""
            )
            .buildCutscene(),
            getKeyLook(7, 3, "porbital_town__house2_f1")
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("DECK_FOUND")
            .speak("THINKING",
                "It's an empty offering bin.",
                "The picture above is labeled \"Dimitri Mendeleev\""
            )
            .buildCutscene(),
            getKeyLook(8, 3, "porbital_town__house2_f1")
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .speak("THINKING",
                "It's an empty offering bin.",
                "The picture above is labeled \"Niels Bohr\""
            )
            .buildCutscene(),
            getKeyLook(9, 3, "porbital_town__house2_f1")
        );

        CutsceneAction[] chlorophyllBattle1 = new CutsceneBuilder()
            .shout("CHLOROPHYLL", "Don't you think ETHYLENE is pure MAGIC???", cameraManager)
            .battle(stateManager, 1)
            .condition("BATTLE_WIN", new CutsceneBuilder()
                .faceTowards(chlorophyll, player)
                .execute(()->mapManager.changeMapMusic("porbital_town__house2_f1", "Lab"))
                .speak("CHLOROPHYLL",
                    "See! This is the MAGIC of ETHYLENE!",
                    "It can act as a ~HORMONEEEEE~ to make fruits ripe!",
                    "But not only that, it's also a simple enough molecule to introduce you to basic organic chemistry!",
                    "How is THAT!?"
                )
                .removeFlag("BATTLE_WIN")
                .removeFlag("CHLOROPHYLL_WAIT_FOR_REMATCH")
                .setFlag("CHLOROPHYLL_1")
                .buildActions()
            )
            .condition("BATTLE_LOSE", new CutsceneBuilder()
                .speak("CHLOROPHYLL",
                    "You're not GREEEENNNN enough!",
                    "Hmph!",
                    "Come fight with me when you're ready!"
                )
                .wait(30)
                .removeFlag("BATTLE_LOSE")
                .setFlag("CHLOROPHYLL_WAIT_FOR_REMATCH")
                .buildActions()
            )
            .buildActions();

        addCutscene(cutscenes, new CutsceneBuilder()
            .forbid("DECK_FOUND")
            .speak("THINKING",
                "It's an offering bin.",
                "There's a deck of cards inside.",
                "The picture above is labeled \"Dimitri Mendeleev\""
            )
            .condition("PROF_CELLULOSE_2", new CutsceneBuilder()
                .setFlag("DECK_FOUND")
                .sfx("PkmnGet")
                .speak("THINKING", "You gained an ALCHEMIST'S DECK!")
                .wait(120)
                .speak("THINKING",
                    "Hmm...",
                    "The instruction says that you need to synthesize a target molecule before the opponent does.",
                    "There are 2 phases, condition and reaction.",
                    "You set the light, pH, temperature, and solvent in condition phase.",
                    "And then you add the reactants and do the reaction in reaction phase.",
                    "...",
                    "Let's try it out!"
                )
                .wait(30)
                .moveXthenY(player, 10, 9)
                .move(player, 6, 9)
                .wait(20)
                .faceTowards(player, chlorophyll)
                .wait(30)
                .faceTowards(chlorophyll, player)
                .wait(20)
                .actions(chlorophyllBattle1)
                .buildActions()
            )
            .buildCutscene(),
            getKeyLook(8, 3, "porbital_town__house2_f1")
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("DECK_FOUND", "PROF_CELLULOSE_2", "CHLOROPHYLL_WAIT_FOR_REMATCH")
            .actions(chlorophyllBattle1)
            .buildCutscene(),
            getKeyNPC(chlorophyll)
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("CHLOROPHYLL_1")
            .forbid("CHLOROPHYLL_2")
            .moveYthenX(player, 6, 9)
            .faceTowards(chlorophyll, player)
            .faceTowards(player, chlorophyll)
            .react(chlorophyll, cameraManager, Emotes.FRIENDLY)
            .speak("CHLOROPHYLL",
                "Haha! That was FUN!",
                "You're not bad at this alchemy thing, you know?",
                "By the way, if you don't know about the ALCHEMIST'S DECK, you can press \"P\" to know more about it!"
            )
            .wait(30)
            .speak("THINKING",
                "She seems... normal...",
                "Like, this dialogue wasn't in the game script.",
                "After the first battle, the professor is supposed to say...",
                "\"Whew...\"",
                "\"I was in the tall grass studying wild Pokemon when I was jumped.\"",
                "\"You saved me. Thanks a lot!\"",
                "...",
                "Or something like that."
            )
            .wait(60)
            .moveYthenX(professorCellulose, 7, 9)
            .wait(20)
            .faceTowards(professorCellulose, player)
            .wait(20)
            .faceTowards(player, professorCellulose)
            .wait(20)
            .react(professorCellulose, cameraManager, Emotes.SMILE)
            .speak("CELLULOSE",
                "Whew...",
                "I was in the room studying wild molecules when I was jumped.",
                "You saved me. Thanks a lot!"
            )
            .wait(120)
            .parallel(new CutsceneBuilder()
                .waitEmote(player, cameraManager, 60)
                .waitEmote(chlorophyll, cameraManager, 60)
                .buildActions()
            )
            .wait(60)
            .faceTowards(player, chlorophyll)
            .wait(20)
            .react(chlorophyll, cameraManager, Emotes.QUESTION)
            .speak("CHLOROPHYLL",
                "Hey... you look CONFUSED!",
                "Is it because of what's happening right now?"
            )
            .sequential(new CutsceneBuilder()
                .react(chlorophyll, cameraManager, Emotes.MUSIC)
                .sfx("GUIConfirm")
                .speak("CHLOROPHYLL",
                    "With Professor CELLULOSEEEEE and KUSARIRINNN~ acting all weird?"
                )
                .buildActions()
            )
            .wait(30)
            .react(player, cameraManager, Emotes.SURPRISE)
            .speak("THINKING",
                "Wait... she noticed it too?",
                "So this insanity isn't just in my head..."
            )
            .wait(30)
            .react(chlorophyll, cameraManager, Emotes.FRIENDLY)
            .speak("CHLOROPHYLL",
                "Yeah, I can see it in your face.",
                "Your face looks GREEENNNN enough to know that something's wrong.",
                "You're probably thinking \"WHAT THE INFESTATION is wrong with these people?\"",
                "And honestly? Your doubt is ROOTED in pure GLUCOSEEE!",
                "Like, you're not flowing with the flow, but you're using your THICK CUTIN layer in critical thinking!"
            )
            .wait(60)
            .waitEmote(player, cameraManager, 60)
            .speak("THINKING",
                "...",
                "She's not WEIRD weird, but she's weird.",
                "What's with the plant reference?",
                "At least you can deal with her quirk better than the others."
            )
            .wait(50)
            .move(chlorophyll, 7, 8)
            .wait(20)
            .faceTowards(chlorophyll, professorCellulose)
            .wait(20)
            .faceTowards(player, professorCellulose)
            .speak("CHLOROPHYLL",
                "Look at him. He's still doing his thing.",
                "\"My son was excited about making a new friend.\"",
                "\"He's upstairs, I think.\""
            )
            .wait(30)
            .speak("CELLULOSE",
                "My son was excited about making a new friend.",
                "He's upstairs, I think."
            )
            .wait(30)
            .speak("CHLOROPHYLL",
                "\"...\"",
                "He's been saying that since this MORNING when the first light of the day hit my LEAVESSS."
            )
            .wait(30)
            .move(chlorophyll, 6, 8)
            .wait(20)
            .faceTowards(chlorophyll, player)
            .wait(20)
            .faceTowards(player, chlorophyll)
            .wait(20)
            .speak("CHLOROPHYLL",
            "That thing is actually..."
            )
            .wait(20)
            .parallel(new CutsceneBuilder()
                .react(chlorophyll, cameraManager, Emotes.MUSIC)
                .speak("CHLOROPHYLL",
                    "PURURURURURUURURURURURU... PING!"
                )
                .buildActions()
            )
            .sfx("GUIConfirm")
            .speak("CHLOROPHYLL",
                "A... CURSEEE!!!",
                "It's a curse that's been passed down for generations.",
                "Everyone who's been affected by it shows different symptoms.",
                "For this CELLULOSEEE and KUSARIRINNN~\nit's the weird pattern of speech they're doing right now.",
                "For YUUKII~\nit's the way she can't control her emotions.",
                "And for me... it's the way I can't\nPHOTOSYNTHESIZEEE\nas efficiently anymore!!!"
            )
            .wait(60)
            .react(chlorophyll, cameraManager, Emotes.SAD)
            .wait(60)
            .speak("CHLOROPHYLL",
                "Weellll... I don't want it to be sad, but it's actually quite sad.",
                "At first, it was just cute. Harmless.",
                "I just needed to walk outside more to absorb more light.",
                "YUUKII was just a little more chaotic, and cute.",
                "KUSARIRIN was just a little more... weird...",
                "And also cute.",
                "But then it got worse. The symptoms multiplied ten folds, hundred folds."
            )
            .parallel(new CutsceneBuilder()
                .react(chlorophyll, cameraManager, Emotes.MUSIC)
                .speak("CHLOROPHYLL",
                    "Just like plants releasing a bunch of SPOOOOREEESSS!",
                    "A LOOOOTTT OF SPOOORREESSSS, filling in the land with FLOWERS!"
                )
                .buildActions()
            )
            .wait(60)
            .parallel(new CutsceneBuilder()
                .moveXthenY(player, 2, 5)
                .sequential(new CutsceneBuilder()
                    .wait(120)
                    .moveXthenY(chlorophyll, 2, 6)
                    .buildActions()
                )
                .buildActions()
            )
            .faceTowards(player, chlorophyll)
            .wait(20)
            .faceTowards(chlorophyll, player)
            .wait(20)
            .speak("CHLOROPHYLL",
                "HEY! This curse is not contagious!",
                "Don't be so scared!",
                "I just like PLANTTSSS, okay?",
                "...",
                "So... Right now, KUSARIRIN and PROF. CELLULOSE is like this.",
                "YUUKII now switches her emotions every 5 seconds.",
                "And if I don't get at least 10 hours of sunlight, I'll die..."
            )
            .wait(60)
            .move(player, 2, 4)
            .faceTowards(player, chlorophyll)
            .wait(20)
            .react(player, cameraManager, Emotes.TERRIFIED)
            .speak("THINKING",
                "D-Die...?",
                "What's this... curse...?"
            )
            .wait(30)
            .react(chlorophyll, cameraManager, Emotes.FRIENDLY)
            .speak("CHLOROPHYLL",
                "Don't worry! We won't die easily!",
                "We have our medication that can help us.",
                "It doesn't cure them completely, but it makes them... more manageable?",
                "Like, for KUSARIRIN, if he eats TETRAHYDROCANNABINOLLL, he'll be able to speak normally for a few hours."
            )
            .wait(30)
            .speak("THINKING",
                "TETRAHYDROCANNABINOL...?",
                "What is thatttt...?",
                "Cannabis?"
            )
            .wait(60)
            .react(chlorophyll, cameraManager, Emotes.QUESTION)
            .speak(new Dialogue(new String[]{
                "Wait... why are you here anyway?",
                "Did someone send you?"
            }, "CHLOROPHYLL",
            new DialogueOption("I need to get YUUKI's medication.", new Dialogue(new String[]{
                "...",
                "Wait, YUUKI? You mean my friend YUUKII with the weird hair?",
                "Has her ETHYLENE ran out...?",
                "Oh NOOOOO! That's terrible!",
                "ETHYLENE is what keeps her emotions stable!",
                "She's like an UNRIPE FRUIT that needs to complete its growth cycle every day!",
                "I need to get more for her right away!",
                "Please wait here, PLAYER!"
            }, "CHLOROPHYLL")),
            new DialogueOption("I'm on a crazy adventure right now.", new Dialogue(new String[]{
                "Crazy adventure?",
                "What's that?",
                "I'm also on a crazy adventure right now.",
                "...",
                "...",
                "...",
                "Wait, you're here to help YUUKII?",
                "Is her ETHYLENE medication out again?",
                "That girl's beyond saving...",
                "Please wait here, PLAYER!"
            }, "CHLOROPHYLL")),
            new DialogueOption("That psychopath PROF. DECANE.", new Dialogue(new String[]{
                "Wait, PROF. DECANEE sent you?",
                "Doesn't that mean YUUKII ran out of ETHYLENE?",
                "That's BADDDD...",
                "ETHYLENE is what keeps her emotions stable!",
                "Please wait here, PLAYER!"
            }, "CHLOROPHYLL"))))
            .wait(30)
            .face(chlorophyll, FacingDirections.LEFT)
            .wait(20)
            .waitEmote(chlorophyll, cameraManager, 60)
            .wait(30)
            .face(chlorophyll, FacingDirections.UP)
            .wait(60)
            .speak(new Dialogue(new String[]{
                "Player, I have a good news and a bad news."
            }, "CHLOROPHYLL",
            new DialogueOption("Good news first.", new Dialogue(new String[]{
                "The good news is...",
                "A huge amount of ETHYLENE was just sent to the LAB in METHANOPOLIS!",
                "For YUUKII's personal use, it can last for a few months if we ask the LAB for some!",
                "I'm so happy for her!",
                "But the bad news is...",
                "You just converted the last molecule of ETHYLENE in this town into a CHLOROETHANE in the battle..."
            }, "CHLOROPHYLL")),
            new DialogueOption("Bad news first.", new Dialogue(new String[]{
                "The bad news is...",
                "I don't have any ETHYLENE left!",
                "You just converted the last molecule of ETHYLENE in this town into a CHLOROETHANE in the battle...",
                "Haha... I'm so sorry...",
                "But there's a good news!",
                "We're going to go get some ETHYLENE from the LAB in METHANOPOLIS!",
                "They just sent a huge amount of ETHYLENE to the LAB!",
            }, "CHLOROPHYLL"))))
            .wait(30)
            .react(chlorophyll, cameraManager, Emotes.FRIENDLY)
            .speak("CHLOROPHYLL",
                "Let's go get some ETHYLENE for YUUKIIII!"
            )
            .wait(30)
            .parallel(new CutsceneBuilder()
                .moveYthenX(chlorophyll, 4, 10)
                .sequential(new CutsceneBuilder()
                    .wait(30)
                    .moveYthenX(player, 2, 9)
                    .face(player, FacingDirections.RIGHT)
                    .buildActions()
                )
                .buildActions()
            )
            .wait(20)
            .tp(chlorophyll, 32, 16, "porbital_town", overworldState)
            .wait(20)
            .waitEmote(player, cameraManager, 60)
            .wait(30)
            .speak("THINKING",
                "...",
                "I guess I'll go with her..."
            )
            .wait(60)
            .react(professorCellulose, cameraManager, Emotes.SURPRISE)
            .move(professorCellulose, 3, 9)
            .speak("THINKING",
                "...",
                "Oh, no...",
                "I don't feel like this is the right time to talk to this guy---"
            )
            .music("Cave")
            .speak("CELLULOSE",
                "Oh!",
                "Hi, You're PLAYER!",
                "This is not the place to chat, so come by my alchemy lab later, okay?",
                "(Teleporting...)",
                "(Teleporting...)",
                "(Teleporting...)"
            )
            .fadeIn(30)
            .tp(professorCellulose, 7, 6, "porbital_town__house2_f2", overworldState)
            .face(professorCellulose, FacingDirections.RIGHT)
            .tp(player, 9, 6, "porbital_town__house2_f2", overworldState)
            .face(player, FacingDirections.LEFT)
            .fadeOut(30)
            .wait(30)
            .speak("CELLULOSE",
                "So, PLAYER.",
                "I've heard so much about you from DECANE.",
                "I've heard that you don't even have your own deck yet.",
                "But the way you battled earlier, you pulled it off with aplomb!",
                "I guess you have DECANE's blood in your veins after all!",
                "Oh, yes. As thanks for rescuing me, I'd like you to have the deck you used earlier."
            )
            .sfx("PkmnGet")
            .speak("THINKING", "You gained an ALCHEMIST'S DECK!")
            .wait(30)
            .waitEmote(player, cameraManager, 60)
            .speak("THINKING",
                "You... You already have this message played when you took the deck from the offering bin...",
                "You're not even related to DECANE by blood...\nYou having her blood in your veins doesn't make sense..."
            )
            .wait(30)
            .speak("CELLULOSE",
                "If you work at chemicals and gain experience, I think you'll make an extremely good alchemist!",
                "My kid, KUSARI, is also studying chemicals while helping me out.",
                "PLAYER, don't you think it's a good idea to go see---"
            )
            .wait(10)
            .sfx("BattleDamageSuper")
            .camShake(cameraManager, 60)
            .tp(yuuki, 10, 3, "porbital_town__house2_f2", overworldState)
            .tp(professorDecane, 12, 6, "porbital_town__house1_f1", overworldState)
            .face(yuuki, FacingDirections.DOWN)
            .face(professorDecane, FacingDirections.UP)
            .sfx("BattleDamageSuper")
            .react(yuuki, cameraManager, Emotes.ANGRY)
            .moveYthenX(yuuki, 7, 7)
            .parallel(new CutsceneBuilder()
                .react(professorCellulose, cameraManager, Emotes.SURPRISE)
                .faceTowards(professorCellulose, yuuki)
                .faceTowards(yuuki, professorCellulose)
                .buildActions()
            )
            .music("BattleWild")
            .shout("YUUKI", "CELLULOSE!!!", cameraManager)
            .shout("YUUKI", "WHAT ARE YOU DOING TO MY FRIEND???", cameraManager)
            .shout("YUUKI", "GO IN THAT CORNER AND STAY THERE!", cameraManager)
            .shout("YUUKI", "NOW!!!", cameraManager)
            .react(professorCellulose, cameraManager, Emotes.TERRIFIED)
            .move(professorCellulose, 7, 5)
            .wait(30)
            .moveYthenX(yuuki, 8, 6)
            .wait(20)
            .react(yuuki, cameraManager, Emotes.FRIENDLY)
            .speak("YUUKI",
                "Hey! I beat up that pervert DECANE already! You're save!",
                "Let's go sightseeing at METHANOPOLIS!"
            )
            .wait(30)
            .sfx("BattleDamageSuper")
            .camShake(cameraManager, 60)
            .tp(chlorophyll, 10, 3, "porbital_town__house2_f2", overworldState)
            .face(chlorophyll, FacingDirections.DOWN)
            .sfx("BattleDamageSuper")
            .wait(40)
            .parallel(new CutsceneBuilder()
                .react(chlorophyll, cameraManager, Emotes.MUSIC)
                .speak("CHLOROPHYLL",
                    "Hey! YUUKII! KUSARIRIN! PLAYER!",
                    "Let's go now!"
                )
                .buildActions()
            )
            .wait(30)
            .parallel(new CutsceneBuilder()
                .react(yuuki, cameraManager, Emotes.FRIENDLY)
                .speak("YUUKI",
                    "Yeah! Let's go!"
                )
                .buildActions()
            )
            .wait(20)
            .parallel(new CutsceneBuilder()
                .sequential(new CutsceneBuilder()
                    .move(chlorophyll, 9, 3)
                    .wait(10)
                    .sfx("DoorExit")
                    .tp(chlorophyll, 49, 14, "porbital_town", overworldState)
                    .face(chlorophyll, FacingDirections.RIGHT)
                    .buildActions()
                )
                .sequential(new CutsceneBuilder()
                    .wait(10)
                    .moveYthenX(yuuki, 10, 7)
                    .moveYthenX(yuuki, 9, 3)
                    .sfx("DoorExit")
                    .tp(yuuki, 46, 16, "porbital_town", overworldState)
                    .face(yuuki, FacingDirections.UP)
                    .buildActions()
                )
                .sequential(new CutsceneBuilder()
                    .wait(40)
                    .moveYthenX(kusari, 10, 7)
                    .moveYthenX(kusari, 9, 3)
                    .sfx("DoorExit")
                    .tp(kusari, 47, 16, "porbital_town", overworldState)
                    .face(kusari, FacingDirections.UP)
                    .buildActions()
                )
                .buildActions()
            )
            .setFlag("CHLOROPHYLL_2")
            .buildCutscene(),
            getKeyLocation(6, 9, "porbital_town__house2_f1"),
            getKeyLocation(5, 8, "porbital_town__house2_f1")
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("CHLOROPHYLL_2")
            .faceTowards(professorCellulose, player)
            .wait(30)
            .waitEmote(professorCellulose, cameraManager, 60)
            .wait(30)
            .speak("CELLULOSE",
                "... ... ... ... ...\n... ... ... ... ...",
                "Great! KUSARI should be happy, too.",
                "Get KUSARI to teach you what it means to be an ALCHEMIST."
            )
            .buildCutscene(),
            getKeyNPC(professorCellulose)
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("CHLOROPHYLL_2")
            .forbid("CHLOROPHYLL_3")
            .speak("KUSARI",
                "Let's see... The chemicals found on Route 101 include..."
            )
            .wait(30)
            .faceTowards(kusari, player)
            .wait(30)
            .react(kusari, cameraManager, Emotes.SURPRISE)
            .wait(30)
            .speak("KUSARI",
                "Oh, hi, PLAYER!",
                "...Oh, I see, my dad gave you a deck as a gift.",
                "Since we're here, let's have a quick battle!",
                "I'll give you a taste of what being an ALCHEMIST is like."
            )
            .wait(60)
            .face(kusari, FacingDirections.UP)
            .wait(60)
            .waitEmote(player, cameraManager, 60)
            .speak("THINKING",
                "I guess he didn't want a battle with me...",
                "It's just that he couldn't say anything else..."
            )
            .buildCutscene(),
            getKeyNPC(kusari)
        );

        CutsceneAction[] grassAnimations = new CutsceneBuilder()
            .animation("grass", 49, 14, 0.5, cameraManager)
            .animation("grass", 49, 15, 0.5, cameraManager)
            .animation("grass", 48, 15, 0.5, cameraManager)
            .animation("grass", 50, 15, 0.5, cameraManager)
            .animation("grass", 49, 13, 0.5, cameraManager)
            .animation("grass", 48, 13, 0.5, cameraManager)
            .animation("grass", 50, 13, 0.5, cameraManager)
            .animation("grass", 50, 14, 0.5, cameraManager)
            .animation("grass", 48, 14, 0.5, cameraManager)
            .buildActions();

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("CHLOROPHYLL_2")
            .forbid("CHLOROPHYLL_3")
            .speak("YUUKI",
                "My life is so boring... Why do I need to be adopted by that DECANE?",
                "Should I just reroll the dice and reborn into something better?",
                "...",
                "...",
                "I want to be born in NOSE TOWN and have a big nose..."
            )
            .wait(30)
            .faceTowards(yuuki, player)
            .wait(30)
            .react(yuuki, cameraManager, Emotes.SURPRISE)
            .wait(30)
            .parallel(new CutsceneBuilder()
                .react(yuuki, cameraManager, Emotes.MUSIC)
                .speak("YUUKI",
                    "Hey! My friend!",
                    "I'm so happy to see you again!",
                    "If you're ready to depart to METHANOPOLIS, talk to CHLOROPHYLL anytime!"
                )
                .buildActions()
            )
            .wait(30)
            .face(yuuki, FacingDirections.UP)
            .buildCutscene(),
            getKeyNPC(yuuki)
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("CHLOROPHYLL_2")
            .forbid("CHLOROPHYLL_3")
            .faceTowards(chlorophyll, player)
            .wait(30)
            .react(chlorophyll, cameraManager, Emotes.FRIENDLY)
            .wait(30)
            .speak("CHLOROPHYLL",
                "Hey! Do you know that plants have their own IMMUNE SYSTEM?!?!",
                "For humans, you already know we have WHITE BLOOD CELLS that fight off infections.",
                "We works by sending KILLER cells to KILL the enemy cells through blood. But plants dont have blood...",
                "So the main chemical that makes plant immune system possible is..."
            )
            .parallel(new CutsceneBuilder()
                .emote(chlorophyll, 50, Emotes.MUSIC, cameraManager)
                .sfx("GUIConfirm")
                .speak("CHLOROPHYLL",
                    "SALICYLIC ACIDDDDD!!!!",
                    "It's a crucial HORMONEE that activates plant immune responses!",
                    "When SALICYLIC ACID is signalled throughout the plant, EACH CELLS starts their own defence mechanism.",
                    "It's like EVERYBODY IS A SOLDIER NOW!!!",
                    "FUN FACT:\nPeople actually use SALICYLIC ACID to make their skin smoother and softer!",
                    "Some people are CRAZYYY about not having acne, so they use this molecule every day!"
                )
                .buildActions()
            )
            .wait(60)
            .react(chlorophyll, cameraManager, Emotes.QUESTION)
            .speak("CHLOROPHYLL",
                "Oh, it's you!",
                "Are you ready to go to METHANOPOLIS?",
                "...",
                "I'll take that as a YESSS then?",
                "Let's go now!"
            )
            .wait(60)
            .speak("THINKING",
                "Yeah... Let's go...",
                "At last, you can go to ROUTE 1 and adventure for the first time..."
            )
            .wait(30)
            .react(chlorophyll, cameraManager, Emotes.FRIENDLY)
            .speak("CHLOROPHYLL",
                "Follow me, PLAYER, KUSARIRINN, and YUUKI!"
            )
            .face(chlorophyll, FacingDirections.UP)
            .wait(30)
            .face(player, FacingDirections.UP)
            .wait(20)
            .parallel(new CutsceneBuilder()
                .react(yuuki, cameraManager, Emotes.MUSIC)
                .react(kusari, cameraManager, Emotes.MUSIC)
                .buildActions()
            )
            .wait(30)
            .parallel(new CutsceneBuilder()
                .move(yuuki, 46, 14)
                .sequential(new CutsceneBuilder()
                    .wait(20)
                    .moveXthenY(kusari, 47, 14)
                    .buildActions()
                )
                .buildActions()
            )
            .music("Cave")
            .wait(60)
            .parallel(new CutsceneBuilder()
                .camShake(cameraManager, 30)
                .sfx("BattleDamageWeak")
                .sfx("Grass")
                .animation("grass", 49, 14, 0.5, cameraManager)
                .animation("grass", 49, 15, 0.5, cameraManager)
                .animation("grass", 49, 13, 0.5, cameraManager)
                .animation("grass", 50, 14, 0.5, cameraManager)
                .animation("grass", 48, 14, 0.5, cameraManager)
                .buildActions()
            )
            .wait(45)
            .parallel(new CutsceneBuilder()
                .camShake(cameraManager, 30)
                .sfx("BattleDamageWeak")
                .sfx("Grass")
                .animation("grass", 49, 14, 0.5, cameraManager)
                .animation("grass", 49, 15, 0.5, cameraManager)
                .animation("grass", 49, 13, 0.5, cameraManager)
                .animation("grass", 50, 14, 0.5, cameraManager)
                .animation("grass", 48, 14, 0.5, cameraManager)
                .buildActions()
            )
            .wait(30)
            .parallel(new CutsceneBuilder()
                .camShake(cameraManager, 40)
                .sfx("BattleDamageNormal")
                .sfx("Grass")
                .actions(grassAnimations)
                .buildActions()
            )
            .wait(90)
            .parallel(new CutsceneBuilder()
                .camShake(cameraManager, 75)
                .sfx("BattleDamageSuper")
                .sequential(
                    new CutsceneBuilder()
                        .actions(grassAnimations)
                        .sfx("Grass")
                        .wait(20)
                        .actions(grassAnimations)
                        .sfx("Grass")
                        .wait(10)
                        .actions(grassAnimations)
                        .sfx("Grass")
                        .wait(5)
                        .actions(grassAnimations)
                        .sfx("Grass")
                        .wait(3)
                        .actions(grassAnimations)
                        .sfx("Grass")
                        .wait(1)
                        .actions(grassAnimations)
                        .sfx("Grass")
                        .buildActions()
                )
                .buildActions()
            )
            .execute(()->chlorophyll.setRunning())
            .execute(()->yuuki.setRunning())
            .execute(()->kusari.setRunning())
            .sfx("BattleDamageSuper")
            .sfx("DoorExit")
            .move(chlorophyll, 49, 7)
            .wait(30)
            .sfx("BattleDamageSuper")
            .sfx("DoorExit")
            .move(yuuki, 46, 7)
            .wait(30)
            .sfx("BattleDamageSuper")
            .sfx("DoorExit")
            .move(kusari, 47, 7)
            .execute(()->chlorophyll.setWalking())
            .execute(()->yuuki.setWalking())
            .execute(()->kusari.setWalking())
            .tp(chlorophyll, 25, 31, "methanopolis", overworldState)
            .tp(yuuki, 22, 32, "methanopolis", overworldState)
            .tp(kusari, 22, 31, "methanopolis", overworldState)
            .face(chlorophyll, FacingDirections.LEFT)
            .face(yuuki, FacingDirections.RIGHT)
            .face(kusari, FacingDirections.RIGHT)
            .wait(120)
            .waitEmote(player, cameraManager, 60)
            .wait(30)
            .move(player, 49, 16)
            .wait(60)
            .move(player, 49, 14)
            .sfx("PlayerBump")
            .wait(60)
            .waitEmote(player, cameraManager, 60)
            .wait(30)
            .react(player, cameraManager, Emotes.ANGRY)
            .speak("THINKING",
                "Guess people of this world can just pull out some random power out of nowhere...",
                "Let's walk to METHANOPOLIS by foot... Like a normal person..."
            )
            .setFlag("CHLOROPHYLL_3")
            .buildCutscene(),
            getKeyNPC(chlorophyll)
        );
        
    }

    private static void director1(Map<String, List<Cutscene>> cutscenes, NPC director, CameraManager cameraManager, Player player) {
        /*
* -----------------------------------------------------------------------------
* CUTSCENE: Porbital Town - Director Introduction
* Location: Porbital Town Room
* -----------------------------------------------------------------------------
? FLAGS USED:
~   - PROF_DECANE_1: The professor has sent the player to get the chemical.
~   - DIRECTOR_1_START: The director's puzzle has begun.
~   - DIRECTOR_1_GOT_KEY: The player has solved the numeric puzzle and obtained the key.
~   - DIRECTOR_1_FINISH: The director's puzzle has been completed.
~   - VIEW_BOX_1 through VIEW_BOX_5: Temporary flags for viewing PC boxes in Director's puzzle.
* -----------------------------------------------------------------------------
! FLOW:
^   1. Only runs if PROF_DECANE_1 is set but DIRECTOR_1_START is NOT set.
^   2. Player enters the room, director is excited that someone finally fell into his trap.
^   3. Director explains that this room cannot be escaped unless the will of the director is satisfied.
^   4. Director asks you to solve a puzzle to escape the room.
^   5. After the scene, DIRECTOR_1_START is set.
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
            .setFlag("DIRECTOR_KNOW")
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
            .setFlag("DIRECTOR_1_START")
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
                .removeFlag("VIEW_BOX_1")
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
                .removeFlag("VIEW_BOX_2")
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
                .removeFlag("VIEW_BOX_3")
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
                .removeFlag("VIEW_BOX_4")
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
                .removeFlag("VIEW_BOX_5")
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
        .wait(60)
        .setFlag("DIRECTOR_1_FINISH")
        .buildCutscene(),
        getKeyLook(1, 2, "porbital_town__room")
        );
    }
}
