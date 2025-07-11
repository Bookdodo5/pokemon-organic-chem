package cutscene.initialize.methanopolis;

import cutscene.Cutscene;
import cutscene.CutsceneBuilder;
import cutscene.Emotes;
import cutscene.template.CutsceneTemplate;
import cutscene.template.OverworldItemTemplate;
import dialogue.Dialogue;
import dialogue.DialogueOption;
import entity.FacingDirections;
import entity.NPC;
import entity.NPCManager;
import entity.Player;
import gamestates.CameraManager;
import java.util.List;
import java.util.Map;
import pokedex.PlayerDeckManager;

public class MethanopolisTalks extends CutsceneTemplate {

    public static void initialize(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager, CameraManager cameraManager, Player player, PlayerDeckManager playerDeckManager) {
        NPC oldMan1 = npcManager.getNPC("OldMan1");
        NPC oldMan2 = npcManager.getNPC("OldMan2");
        NPC oldWoman1 = npcManager.getNPC("OldWoman1");
        NPC oldWoman2 = npcManager.getNPC("OldWoman2");
        NPC lazyCop = npcManager.getNPC("LazyCop");
        NPC serviceSeller = npcManager.getNPC("ServiceSeller");
        NPC dirtSeller = npcManager.getNPC("DirtSeller");
        NPC airconRepairMan = npcManager.getNPC("AirconRepairMan");
        NPC chef = npcManager.getNPC("Chef");
        NPC computerRepairMan = npcManager.getNPC("ComputerRepairMan");
        NPC primeMinisterPsychic1 = npcManager.getNPC("PrimeMinisterPsychic1");
        NPC primeMinisterPsychic2 = npcManager.getNPC("PrimeMinisterPsychic2");
        NPC aromatherapist = npcManager.getNPC("Aromatherapist");

        staticTalks(cutscenes, oldMan1, oldMan2, oldWoman1, oldWoman2, lazyCop, serviceSeller,
                dirtSeller, airconRepairMan, chef, computerRepairMan, primeMinisterPsychic1,
                primeMinisterPsychic2, aromatherapist, cameraManager, player, playerDeckManager);
    }

    private static void staticTalks(Map<String, List<Cutscene>> cutscenes, NPC oldMan1, NPC oldMan2,
            NPC oldWoman1, NPC oldWoman2, NPC lazyCop, NPC serviceSeller,
            NPC dirtSeller, NPC airconRepairMan, NPC chef, NPC computerRepairMan,
            NPC primeMinisterPsychic1, NPC primeMinisterPsychic2, NPC aromatherapist,
            CameraManager cameraManager, Player player, PlayerDeckManager playerDeckManager) {

        //* CUTSCENE: Methanopolis - Old Man 1
        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("OLD MAN_KNOW")
                .faceTowards(oldMan1, player)
                .speak("OLD MAN",
                        "Hello young one!",
                        "I'm walking around this building in circles because... because...",
                        "because...",
                        "I forgot why.",
                        "But I know it's important!",
                        "My doctor said I need exercise, so I'm exercising my memory by trying to remember why I'm walking in circles.",
                        "I'm doing that while walking in circles.",
                        "Two birds in one stone!",
                        "..."
                )
                .wait(30)
                .react(oldMan1, cameraManager, Emotes.SURPRISE)
                .wait(30)
                .shout("OLD MAN", "It's working! I remember now!", cameraManager)
                .shout("OLD MAN", "YES!!!", cameraManager)
                .speak("OLD MAN",
                        "...",
                        "...",
                        "...",
                        "No, I don't.",
                        "But I'm sure I'll after a few more laps..."
                )
                .buildCutscene(), getKeyNPC(oldMan1));

        //* CUTSCENE: Methanopolis - Old Man 2
        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("OLD MAN_KNOW")
                .faceTowards(oldMan2, player)
                .speak("OLD MAN",
                        "Hoho... You're quite young, aren't you?",
                        "Don't mind that other old man over there.",
                        "He's been walking in circles for 3 years now.",
                        "Even if I try convincing him to stop, he won't listen.",
                        "What a stubborn old man...",
                        "As a result, I need to walk together with him so it wouldn't be odd.",
                        "You see, if there's one person, it will be odd.",
                        "But if you add one more to make it 2 people, it will be even.",
                        "As long as no one else wants to walk around in circles, I need to walk here with him."
                )
                .wait(30)
                .waitEmote(player, cameraManager, 60)
                .wait(30)
                .react(oldMan2, cameraManager, Emotes.SAD)
                .speak("OLD MAN",
                        "I know it's sad, but I need to do my civic duty as a citizen of this city.",
                        "Don't mind me. I need to continue my walk."
                )
                .buildCutscene(), getKeyNPC(oldMan2));

        //* CUTSCENE: Methanopolis - Old Woman 1
        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("OLD WOMAN_KNOW")
                .faceTowards(oldWoman1, player)
                .speak(new Dialogue(new String[]{
            "Oh! I've never seen you around here before!",
            "Let me tell you a scandal that's been going on...",
            "You see that DIRT SELLER above the blue MART building?",
            "He's been selling DIRT instead of ROCKS for years and nobody notice!",
            "Can you believe the audacity!?"
        }, "OLD WOMAN",
                new DialogueOption("That sounds like he's just doing his job...", new Dialogue(new String[]{
            "Huh? HUHH??",
            "A-are you saying that's normal?",
            "UNBELIEVABLE!",
            "I'm going to tell everyone about this!",
            "Go away! I don't need you in my life!"
        }, "OLD WOMAN")),
                new DialogueOption("Who's the DIRT SELLER selling DIRT to?", new Dialogue(new String[]{
            "The DIRT SELLER, obviously!",
            "Instead of selling ROCKS, DIRT SELLER's been selling DIRT to the DIRT SELLER!",
            "...",
            "Wait, that doesn't make sense...",
            "Let me think about this...",
            "If the dirt seller is selling dirt to the dirt seller...",
            "That would mean...",
            "..."
        }, "OLD WOMAN"))
                ))
                .buildCutscene(), getKeyNPC(oldWoman1));

        //* CUTSCENE: Methanopolis - Old Woman 2
        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("OLD WOMAN_KNOW")
                .faceTowards(oldWoman2, player)
                .speak(new Dialogue(new String[]{
            "Do you know that you can press \"Z\" key to talk to NPCs?",
            "...",
            "...",
            "Oh, you're talking to me, so you must know it already..."
        }, "OLD WOMAN",
                new DialogueOption("Can you tell me more about other keys?", new Dialogue(new String[]{
            "Of course I can!",
            "The \"W\" key is for walking forward.",
            "The \"A\" key is for walking left.",
            "The \"S\" key is for walking backward.",
            "The \"D\" key is for walking right.",
            "...",
            "Oh, you already know it, huh?",
            "Kids these days are so smart that we don't need to teach them anything."
        }, "OLD WOMAN")),
                new DialogueOption("How about a key that make me invincible?", new Dialogue(new String[]{
            "Invincible?",
            "Well, if you want to get technical about it...",
            "You can press \"Esc\" key, and go to \"Exit to Title\", and you cannot die.",
            "You will just be stuck in a title screen."
        }, "OLD WOMAN")),
                new DialogueOption("Key! Keystone! Mega evolution!", new Dialogue(new String[]{
            "Keystone! Yes!",
            "It's an ancient gemstone that can connect between alchemists and molecules.",
            "The power inside our body will be used, and you will win the game instantly!",
            "...",
            "...",
            "Don't believe me. I just made it up...",
            "Ahhh... Why are kids these days so smart?"
        }, "OLD WOMAN"))
                ))
                .buildCutscene(), getKeyNPC(oldWoman2));

        //* CUTSCENE: Methanopolis - Lazy Cop
        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("LAZY_COP_KNOW")
                .faceTowards(lazyCop, player)
                .speak("LAZY COP",
                        "Hey there, citizen.",
                        "I'm supposed to be patrolling in PORBITAL TOWN... but...",
                        "I'm taking a strategic break.",
                        "You see, if I rest now, I'll be more alert later when I actually need to be alert.",
                        "It's called \"proactive laziness\".",
                        "Besides, what's the worst that could happen?",
                        "It's not like there are any criminals in this town...",
                        "Right?",
                        "...",
                        "Right?"
                )
                .wait(30)
                .face(lazyCop, FacingDirections.DOWN)
                .buildCutscene(), getKeyNPC(lazyCop));

        //* CUTSCENE: Methanopolis - Service Seller
        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("SERVICE SELLER_KNOW")
                .faceTowards(serviceSeller, player)
                .speak(new Dialogue(new String[]{
            "Welcome to my service shop!",
            "I sell services!",
            "...",
            "What kind of services, you ask?",
            "Well... services!",
            "You know, like... helping people with... things...",
            "...",
            "What things?",
            "...",
            "Can you stop asking questions and buy my services?"
        }, "SERVICE SELLER",
                new DialogueOption("I rent you to be my boyfriend.", new Dialogue(new String[]{
            "Boyfriend?",
            "...",
            "...",
            "I'm a girl..."
        }, "SERVICE SELLER")),
                new DialogueOption("I rent you to be my girlfriend.", new Dialogue(new String[]{
            "Girlfriend?",
            "...",
            "...",
            "I'm a boy..."
        }, "SERVICE SELLER")),
                new DialogueOption("I rent you to be my partner in life.", new Dialogue(new String[]{
            "Partner in life?",
            "...",
            "...",
            "I'm only capable of asexual reproduction... Sorry..."
        }, "SERVICE SELLER")),
                new DialogueOption("How much do services cost?", new Dialogue(new String[]{
            "Cost?",
            "Well, that depends on what service you need.",
            "If you need a simple service, it costs less.",
            "If you need a complicated service, it costs more.",
            "I think.",
            "I haven't actually sold any services yet though, so I'm not sure..."
        }, "SERVICE SELLER"))
                ))
                .buildCutscene(), getKeyNPC(serviceSeller));

        //* CUTSCENE: Methanopolis - Dirt Seller
        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("DIRT_SELLER_KNOW")
                .faceTowards(dirtSeller, player)
                .speak(new Dialogue(new String[]{
            "Welcome to my dirt shop!",
            "I'm selling dirt!",
            "I have organic dirt, inorganic dirt, and even living dirt!"
        }, "DIRT SELLER",
                new DialogueOption("Why would I buy dirt?", new Dialogue(new String[]{
            "Why WOULDN'T you buy dirt?",
            "Can you tell me something that dirt can't do?",
            "...",
            "YOU CAN'T!",
            "Dirt can be eaten, breathed, and even used as a weapon!",
            "Dirt is the most versatile substance in the world!",
            "Why wouldn't you buy it?"
        }, "DIRT SELLER")),
                new DialogueOption("Is the \"living\" dirt safe?", new Dialogue(new String[]{
            "Safe?",
            "Rather, it's a military grade weapon!",
            "You know? During the STEREOCHEMISTRY WAR, the Z-faction used living dirt to attack the E-faction.",
            "The E-faction was completely annihilated by the living dirt.",
            "The way it works is pretty simple. The living dirt contains microscopic organisms called \"Dirtus Agressiticus\".",
            "These little organisms can sense the 3D molecular structure of their targets and flip the structure in seconds.",
            "The Z-faction would throw handfuls of this living dirt at the E-faction soldiers.",
            "Within minutes, the soldiers would change sides to the Z-faction!",
            "Wanna buy some?"
        }, "DIRT SELLER")),
                new DialogueOption("Who are you selling dirt to?", new Dialogue(new String[]{
            "Who?",
            "I'm selling dirt to everyone!",
            "Especially me. I love buying dirt!"
        }, "DIRT SELLER"))
                ))
                .buildCutscene(), getKeyNPC(dirtSeller));

        //* CUTSCENE: Methanopolis - Air Conditioning Repair Man
        addCutscene(cutscenes, new CutsceneBuilder()
                .forbid("AIRCON_QUEST_COMPLETED")
                .setFlag("AIRCON REPAIR MAN_KNOW")
                .faceTowards(airconRepairMan, player)
                .speak("AIRCON REPAIR MAN",
                        "Hey! I'm from the air conditioning repair service.",
                        "I got a call from an apartment in METHANOPOLIS saying their air conditioner is broken.",
                        "But when I got here, they're saying they never called me...",
                        "I have a call log right here! But they won't let me in...",
                        "I'm not sure what's going on..."
                )
                .wait(60)
                .speak("THINKING",
                        "You heard a bunch of gen-alpha kids crying inside because they can't watch brainrot contents..."
                )
                .condition("COMPUTER REPAIR MAN_KNOW", new CutsceneBuilder()
                    .wait(60)
                    .waitEmote(player, cameraManager, 60)
                    .speak(new Dialogue(new String[]{
                        "Why are you still here...?",
                        "Do you know something about this?"
                    }, "AIRCON REPAIR MAN",
                    new DialogueOption("There's another apartment...", new Dialogue(new String[]{
                        "...",
                        "What?",
                        "...",
                        "...",
                        "...",
                        "Okay... Thank you young man...",
                        "Why am I this stupid? Really? I should just stop being a repair man.",
                        "...",
                        "Oh, you're still here.",
                        "This is a small gift for you for helping me."
                    }, "AIRCON REPAIR MAN"))
                    ))
                    .wait(30)
                    .actions(OverworldItemTemplate.getItemAction(
                        "Halo on Crack",
                        playerDeckManager
                    ))
                    .setFlag("AIRCON_QUEST_COMPLETED")
                    .buildActions()
                )
                .buildCutscene(), getKeyNPC(airconRepairMan));

        //* CUTSCENE: Methanopolis - Computer Repair Man
        addCutscene(cutscenes, new CutsceneBuilder()
                .forbid("COMPUTER_QUEST_COMPLETED")
                .setFlag("COMPUTER REPAIR MAN_KNOW")
                .faceTowards(computerRepairMan, player)
                .speak("COMPUTER REPAIR MAN",
                        "The apartment over here called me to fix the computer.",
                        "But they won't let me in...",
                        "They're saying their computer isn't broken and I'm here to scam them.",
                        "But an apartment in METHANOPOLIS really called me to fix the computer...",
                        "I'm confused.",
                        "I'm not sure what to do..."
                )
                .wait(60)
                .speak("THINKING",
                        "You heard a bunch of gamblers complaining about the temperature in the casino..."
                )
                .condition("AIRCON REPAIR MAN_KNOW", new CutsceneBuilder()
                    .wait(60)
                    .waitEmote(player, cameraManager, 60)
                    .speak(new Dialogue(new String[]{
                        "You're still here with me...?",
                        "Do you have any information I don't know?"
                    }, "COMPUTER REPAIR MAN",
                    new DialogueOption("There's another apartment...", new Dialogue(new String[]{
                        "What... in the world...?",
                        "...",
                        "Really? How can I miss that?",
                        "Here is a small compensation for that crucial piece of information."
                    }, "COMPUTER REPAIR MAN"))
                    ))
                    .wait(30)
                    .actions(OverworldItemTemplate.getItemAction(
                        "Halo on Crack",
                        playerDeckManager
                    ))
                    .setFlag("COMPUTER_QUEST_COMPLETED")
                    .buildActions()
                )
                .buildCutscene(), getKeyNPC(computerRepairMan));

            addCutscene(cutscenes, new CutsceneBuilder()
                .require("COMPUTER_QUEST_COMPLETED")
                .faceTowards(computerRepairMan, player)
                .speak("COMPUTER REPAIR MAN",
                        "I'm shocked at my stupidity...",
                        "In 3 seconds, I will recover... Then, I will actually go to work.",
                        "...1",
                        "...2",
                        "...3",
                        "...",
                        "No. I'm still shocked.",
                        "Give me more time..."
                )
                .buildCutscene(), getKeyNPC(computerRepairMan));

            addCutscene(cutscenes, new CutsceneBuilder()
                .require("AIRCON_QUEST_COMPLETED")
                .faceTowards(airconRepairMan, player)
                .speak("AIRCON REPAIR MAN",
                        "I'm thinking of how to explain my lateness to my client...",
                        "I can't just tell them I didn't know there are 2 apartments..."
                )
                .buildCutscene(), getKeyNPC(airconRepairMan));

        //* CUTSCENE: Methanopolis - Chef
        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("CHEF_KNOW")
                .faceTowards(chef, player)
                .speak("CHEF",
                        "My friend MOLECULAR GASTRONOMIST is going to PORBITAL TOWN to buy some VANILLIN.",
                        "He took too long to come back...",
                        "I'm bored.",
                        "Can you sing for me?"
                )
                .wait(60)
                .react(chef, cameraManager, Emotes.SAD)
                .speak("CHEF",
                        "You won't...?"
                )
                .wait(30)
                .waitEmote(player, cameraManager, 60)
                .react(player, cameraManager, Emotes.MUSIC)
                .wait(30)
                .react(chef, cameraManager, Emotes.LOVE)
                .speak("CHEF",
                        "I have a will to continue waiting now!",
                        "Thank you!"
                )
                .buildCutscene(), getKeyNPC(chef));

        //* CUTSCENE: Methanopolis - Prime Minister Psychic 1
        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("PSYCHIC_KNOW")
                .faceTowards(primeMinisterPsychic1, player)
                .speak("PSYCHIC",
                        "Greetings, mortal.",
                        "I'm here to guard the holy TOWNHALL, the place where the prime minister of this town lives."
                )
                .wait(60)
                .waitEmote(player, cameraManager, 60)
                .shout("PSYCHIC", "YOU'RE THINKING ABOUT IT RIGHT NOW, AREN'T YOU?", cameraManager)
                .shout("PSYCHIC", "DON'T YOU DARE COMPARE MY LORD PRIME MINISTER TO THAT PORBITAL TOWN MAYOR!", cameraManager)
                .shout("PSYCHIC", "THEY'RE ON A DIFFERENT LEAGUE ALL TOGETHER!", cameraManager)
                .speak(new Dialogue(new String[]{
            "...",
            "Please pay me 500 CHEMS to continue talking.",}, "PSYCHIC",
                new DialogueOption("Can you read my mind?", new Dialogue(new String[]{
            "Read your mind?",
            "Fine.",
            "...",
            "...",
            "...",
            "You're thinking about\n\"2-AMINO-3-(4-HYDROXYPHENYL)PROPANOIC ACID\".",
            "Now pay me 500 CHEMS.",
            "...",
            "...",
            "You don't have any CHEMS.",
            "THEN GO AWAY!!!",}, "PSYCHIC")),
                new DialogueOption("I'm leaving...", new Dialogue(new String[]{
            "Fine.",
            "You're so poor!",
            "Hehehe!"
        }, "PSYCHIC"))
                ))
                .wait(30)
                .face(primeMinisterPsychic1, FacingDirections.DOWN)
                .buildCutscene(), getKeyNPC(primeMinisterPsychic1));

        //* CUTSCENE: Methanopolis - Prime Minister Psychic 2
        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("PSYCHIC_KNOW")
                .faceTowards(primeMinisterPsychic2, player)
                .speak(new Dialogue(new String[]{
            "Hello there, young one.",
            "Yes, there are two of us.",
            "The Prime Minister wants his townhall to be REALLY secure.",
            "My colleague over there is just a psychic assistant.",
            "I'm the actual psychic.",
            "Don't listen to her too much, okay?",
            "...",
            "...",
            "Please pay me 500 CHEMS to continue talking.",}, "PSYCHIC",
                new DialogueOption("Why do psychics need assistants?", new Dialogue(new String[]{
            "I won't answer unless you pay me 500 CHEMS.",
            "That's why I'm a professional.",
            "...",
            "...",
            "You don't have any CHEMS.",
            "THEN GET YOUR ASS AWAY FROM ME!!!",}, "PSYCHIC")),
                new DialogueOption("Why won't people hire actual guards?", new Dialogue(new String[]{
            "I won't answer unless you pay me 500 CHEMS.",
            "That's why I'm a professional.",
            "...",
            "...",
            "You don't have any CHEMS.",
            "THEN GET YOUR ASS AWAY FROM ME!!!",}, "PSYCHIC"))
                ))
                .wait(30)
                .face(primeMinisterPsychic2, FacingDirections.DOWN)
                .buildCutscene(), getKeyNPC(primeMinisterPsychic2));

        //* CUTSCENE: Methanopolis - Aromatherapist
        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("AROMATHERAPIST_KNOW")
                .faceTowards(aromatherapist, player)
                .speak("AROMATHERAPIST",
                        "Heyyyy!!! Have you ever smelled a fresh lemon peel?!",
                        "That zesty, citrusy burst! You know what that is???",
                        "That's...."
                )
                .parallel(
                        new CutsceneBuilder()
                                .emote(aromatherapist, 50, Emotes.MUSIC, cameraManager)
                                .sfx("GUIConfirm")
                                .speak("AROMATHERAPIST",
                                        "LIMONENEEEEE!!!!!",
                                        "FUN FACT:\nThis Lemonene is used to inside food, cosmetics, and cleaning products!",
                                        "Not only that, but it smells so good that it's used as a botanical insecticide!",
                                        "The smell is TOO STRONG for insects!"
                                )
                                .buildActions()
                )
                .buildCutscene(), getKeyNPC(aromatherapist));

        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("AROMATHERAPIST_KNOW")
                .faceTowards(aromatherapist, player)
                .speak("AROMATHERAPIST",
                        "HELLOOOOO!!!! Welcome to my forest!",
                        "Ever wonder why roses smell so romantic... and not like old socks???",
                        "That's because of..."
                )
                .parallel(
                        new CutsceneBuilder()
                                .emote(aromatherapist, 50, Emotes.MUSIC, cameraManager)
                                .sfx("GUIConfirm")
                                .speak("AROMATHERAPIST",
                                        "GERANIOLLL!!!!",
                                        "It's the main component that makes roses smell like LOVE!!!",
                                        "And unlike many compounds with smells, this one doesn't have aromatic rings!",
                                        "Magic!",
                                        "FUN FACT:\nThis molecule can soothe, protect, and enhance your skin health!!!",
                                        "Isn't that BEAUTIFUL???"
                                )
                                .buildActions()
                )
                .buildCutscene(), getKeyNPC(aromatherapist));
    }
}
