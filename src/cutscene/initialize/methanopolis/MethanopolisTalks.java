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
import gamestates.FlagManager;
import java.util.List;
import java.util.Map;
import java.util.Random;
import pokedex.PlayerDeckManager;

public class MethanopolisTalks extends CutsceneTemplate {

    public static void initialize(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager,
            CameraManager cameraManager, Player player, PlayerDeckManager playerDeckManager) {
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

        staticTalks(cutscenes, oldMan1, oldMan2, oldWoman1, oldWoman2, lazyCop, serviceSeller, dirtSeller,
                airconRepairMan, chef, computerRepairMan, primeMinisterPsychic1, primeMinisterPsychic2,
                aromatherapist, cameraManager, player, playerDeckManager);

        workshopTalks(cutscenes, npcManager, cameraManager, player);
        townhallTalks(cutscenes, npcManager, cameraManager, player);
        pokecenterTalks(cutscenes, npcManager, cameraManager, player);
        pokemartTalks(cutscenes, npcManager, player);
        botanistHouseTalks(cutscenes, npcManager, cameraManager, player);
        apartment1Talks(cutscenes, npcManager, player);
        apartment2Talks(cutscenes, npcManager, cameraManager, player);
        housesTalks(cutscenes, npcManager, cameraManager, player);
    }

    private static void staticTalks(Map<String, List<Cutscene>> cutscenes, NPC oldMan1, NPC oldMan2, NPC oldWoman1,
            NPC oldWoman2, NPC lazyCop, NPC serviceSeller, NPC dirtSeller, NPC airconRepairMan, NPC chef,
            NPC computerRepairMan, NPC primeMinisterPsychic1, NPC primeMinisterPsychic2, NPC aromatherapist,
            CameraManager cameraManager, Player player, PlayerDeckManager playerDeckManager) {

        // * CUTSCENE: Methanopolis - Old Man 1
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("OLD MAN_KNOW").faceTowards(oldMan1, player).speak(
                "OLD MAN", "Hello young one!",
                "I'm walking around this building in circles because... because...", "because...",
                "I forgot why.", "But I know it's important!",
                "My doctor said I need exercise, so I'm exercising my memory by trying to remember why I'm walking in circles.",
                "I'm doing that while walking in circles.", "Two birds in one stone!", "...").wait(30)
                .react(oldMan1, cameraManager, Emotes.SURPRISE).wait(30)
                .shout("OLD MAN", "It's working! I remember now!", cameraManager)
                .shout("OLD MAN", "YES!!!", cameraManager)
                .speak("OLD MAN", "...", "...", "...", "No, I don't.",
                        "But I'm sure I'll after a few more laps...")
                .buildCutscene(), getKeyNPC(oldMan1));

        // * CUTSCENE: Methanopolis - Old Man 2
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("OLD MAN_KNOW").faceTowards(oldMan2, player).speak(
                "OLD MAN", "Hoho... You're quite young, aren't you?",
                "Don't mind that other old man over there.",
                "He's been walking in circles for 3 years now.",
                "Even if I try convincing him to stop, he won't listen.", "What a stubborn old man...",
                "As a result, I need to walk together with him so it wouldn't be odd.",
                "You see, if there's one person, it will be odd.",
                "But if you add one more to make it 2 people, it will be even.",
                "As long as no one else wants to walk around in circles, I need to walk here with him.")
                .wait(30).waitEmote(player, cameraManager, 60).wait(30)
                .react(oldMan2, cameraManager, Emotes.SAD)
                .speak("OLD MAN",
                        "I know it's sad, but I need to do my civic duty as a citizen of this city.",
                        "Don't mind me. I need to continue my walk.")
                .buildCutscene(), getKeyNPC(oldMan2));

        // * CUTSCENE: Methanopolis - Old Woman 1
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("OLD WOMAN_KNOW").faceTowards(oldWoman1, player)
                .speak(new Dialogue(new String[]{"Oh! I've never seen you around here before!",
            "Let me tell you a scandal that's been going on...",
            "You see that DIRT SELLER above the blue MART building?",
            "He's been selling DIRT instead of ROCKS for years and nobody notice!",
            "Can you believe the audacity!?"}, "OLD WOMAN",
                new DialogueOption("That sounds like he's just doing his job...",
                        new Dialogue(new String[]{"Huh? HUHH??",
                    "A-are you saying that's normal?",
                    "UNBELIEVABLE!",
                    "I'm going to tell everyone about this!",
                    "Go away! I don't need you in my life!"},
                        "OLD WOMAN")),
                new DialogueOption("Who's the DIRT SELLER selling DIRT to?",
                        new Dialogue(new String[]{
                    "The DIRT SELLER, obviously!",
                    "Instead of selling ROCKS, DIRT SELLER's been selling DIRT to the DIRT SELLER!",
                    "...",
                    "Wait, that doesn't make sense...",
                    "Let me think about this...",
                    "If the dirt seller is selling dirt to the dirt seller...",
                    "That would mean...", "..."},
                        "OLD WOMAN"))))
                .buildCutscene(), getKeyNPC(oldWoman1));

        // * CUTSCENE: Methanopolis - Old Woman 2
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("OLD WOMAN_KNOW").faceTowards(oldWoman2, player)
                .speak(new Dialogue(new String[]{
            "Do you know that you can press \"Z\" key to talk to NPCs?", "...",
            "...", "Oh, you're talking to me, so you must know it already..."},
                "OLD WOMAN",
                new DialogueOption("Can you tell me more about other keys?",
                        new Dialogue(new String[]{"Of course I can!",
                    "The \"W\" key is for walking forward.",
                    "The \"A\" key is for walking left.",
                    "The \"S\" key is for walking backward.",
                    "The \"D\" key is for walking right.",
                    "...", "Oh, you already know it, huh?",
                    "Kids these days are so smart that we don't need to teach them anything."},
                        "OLD WOMAN")),
                new DialogueOption("How about a key that make me invincible?",
                        new Dialogue(new String[]{"Invincible?",
                    "Well, if you want to get technical about it...",
                    "You can press \"Esc\" key, and go to \"Exit to Title\", and you cannot die.",
                    "You will just be stuck in a title screen."},
                        "OLD WOMAN")),
                new DialogueOption("Key! Keystone! Mega evolution!",
                        new Dialogue(new String[]{"Keystone! Yes!",
                    "It's an ancient gemstone that can connect between alchemists and molecules.",
                    "The power inside our body will be used, and you will win the game instantly!",
                    "...", "...",
                    "Don't believe me. I just made it up...",
                    "Ahhh... Why are kids these days so smart?"},
                        "OLD WOMAN"))))
                .buildCutscene(), getKeyNPC(oldWoman2));

        // * CUTSCENE: Methanopolis - Lazy Cop
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("LAZY COP_KNOW").faceTowards(lazyCop, player)
                .speak("LAZY COP", "Hey there, citizen.",
                        "I'm supposed to be patrolling in PORBITAL TOWN... but...",
                        "I'm taking a strategic break.",
                        "You see, if I rest now, I'll be more alert later when I actually need to be alert.",
                        "It's called \"proactive laziness\".",
                        "Besides, what's the worst that could happen?",
                        "It's not like there are any criminals in this town...", "Right?",
                        "...", "Right?")
                .wait(30).face(lazyCop, FacingDirections.DOWN).buildCutscene(), getKeyNPC(lazyCop));

        // * CUTSCENE: Methanopolis - Service Seller
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("SERVICE SELLER_KNOW")
                .faceTowards(serviceSeller, player)
                .speak(new Dialogue(
                        new String[]{"Welcome to my service shop!", "I sell services!", "...",
                            "What kind of services, you ask?", "Well... services!",
                            "You know, like... helping people with... things...",
                            "...", "What things?", "...",
                            "Can you stop asking questions and buy my services?"},
                        "SERVICE SELLER",
                        new DialogueOption("I rent you to be my boyfriend.",
                                new Dialogue(new String[]{"Boyfriend?", "...", "...",
                            "I'm a girl..."}, "SERVICE SELLER")),
                        new DialogueOption("I rent you to be my girlfriend.",
                                new Dialogue(new String[]{"Girlfriend?", "...", "...",
                            "I'm a boy..."}, "SERVICE SELLER")),
                        new DialogueOption("I rent you to be my partner in life.", new Dialogue(
                                new String[]{"Partner in life?", "...", "...",
                                    "I'm only capable of asexual reproduction... Sorry..."},
                                "SERVICE SELLER")),
                        new DialogueOption("How much do services cost?",
                                new Dialogue(new String[]{"Cost?",
                            "Well, that depends on what service you need.",
                            "If you need a simple service, it costs less.",
                            "If you need a complicated service, it costs more.",
                            "I think.",
                            "I haven't actually sold any services yet though, so I'm not sure..."},
                                "SERVICE SELLER"))))
                .buildCutscene(), getKeyNPC(serviceSeller));

        // * CUTSCENE: Methanopolis - Dirt Seller
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("DIRT SELLER_KNOW").faceTowards(dirtSeller, player)
                .speak(new Dialogue(new String[]{"Welcome to my dirt shop!", "I'm selling dirt!",
            "I have organic dirt, inorganic dirt, and even living dirt!"},
                "DIRT SELLER",
                new DialogueOption("Why would I buy dirt?", new Dialogue(new String[]{
            "Why WOULDN'T you buy dirt?",
            "Can you tell me something that dirt can't do?", "...",
            "YOU CAN'T!",
            "Dirt can be eaten, breathed, and even used as a weapon!",
            "Dirt is the most versatile substance in the world!",
            "Why wouldn't you buy it?"}, "DIRT SELLER")),
                new DialogueOption("Is the \"living\" dirt safe?",
                        new Dialogue(new String[]{"Safe?",
                    "Rather, it's a military grade weapon!",
                    "You know? During the STEREOCHEMISTRY WAR, the Z-faction used living dirt to attack the E-faction.",
                    "The E-faction was completely annihilated by the living dirt.",
                    "The way it works is pretty simple. The living dirt contains microscopic organisms called \"Dirtus Agressiticus\".",
                    "These little organisms can sense the 3D molecular structure of their targets and flip the structure in seconds.",
                    "The Z-faction would throw handfuls of this living dirt at the E-faction soldiers.",
                    "Within minutes, the soldiers would change sides to the Z-faction!",
                    "Wanna buy some?"}, "DIRT SELLER")),
                new DialogueOption("Who are you selling dirt to?", new Dialogue(
                        new String[]{"Who?", "I'm selling dirt to everyone!",
                            "Especially me. I love buying dirt!"},
                        "DIRT SELLER"))))
                .face(dirtSeller, FacingDirections.DOWN)
                .buildCutscene(), getKeyNPC(dirtSeller));

        // * CUTSCENE: Methanopolis - Air Conditioning Repair Man
        addCutscene(cutscenes, new CutsceneBuilder().forbid("AIRCON_QUEST_COMPLETED")
                .setFlag("AIRCON REPAIR MAN_KNOW").faceTowards(airconRepairMan, player)
                .speak("AIRCON REPAIR MAN", "Hey! I'm from the air conditioning repair service.",
                        "I got a call from an apartment in METHANOPOLIS saying their air conditioner is broken.",
                        "But when I got here, they're saying they never called me...",
                        "I have a call log right here! But they won't let me in...",
                        "I'm not sure what's going on...")
                .wait(60)
                .speak("THINKING",
                        "You heard a bunch of gen-alpha kids crying inside because they can't watch brainrot contents...")
                .wait(60)
                .condition("COMPUTER REPAIR MAN_KNOW", new CutsceneBuilder().wait(60)
                        .waitEmote(player, cameraManager, 60)
                        .speak("THINKING",
                                "There's another apartment, and there's a COMPUTER REPAIR MAN in front of that building.",
                                "From the crying inside, there's only one explanation...",
                                "..."
                        )
                        .speak(new Dialogue(
                                new String[]{"Hey! Why are you still here...?",
                                    "Do you know something about this?"},
                                "AIRCON REPAIR MAN",
                                new DialogueOption("There's another apartment...",
                                        new Dialogue(new String[]{"...",
                                    "What?", "...", "...",
                                    "... ... ... ... ...\n... ... ... ... ...",
                                    "Okay... Thank you young man...",
                                    "Why am I this stupid? Really? I should just stop being a repair man.",
                                    "I never doubt for a moment that this apartment is the wrong place.",
                                    "This is a small gift for you for helping me.",
                                "It might help you in the future. Take it!"},
                                        "AIRCON REPAIR MAN"))))
                        .wait(30)
                        .actions(OverworldItemTemplate.getItemAction("Halo on Crack",
                                playerDeckManager))
                        .setFlag("AIRCON_QUEST_COMPLETED").buildActions())
                .buildCutscene(), getKeyNPC(airconRepairMan));

        // * CUTSCENE: Methanopolis - Computer Repair Man
        addCutscene(cutscenes, new CutsceneBuilder().forbid("COMPUTER_QUEST_COMPLETED")
                .setFlag("COMPUTER REPAIR MAN_KNOW").faceTowards(computerRepairMan, player)
                .speak("COMPUTER REPAIR MAN", "The apartment over here called me to fix the computer.",
                        "But they won't let me in...",
                        "They're saying their computer isn't broken and I'm here to scam them.",
                        "But an apartment in METHANOPOLIS really called me to fix the computer...",
                        "I'm confused.", "I'm not sure what to do...")
                .wait(60)
                .speak("THINKING",
                "You heard a loud gambler complaining about the temperature in the casino...")
                .wait(60)
                .condition("AIRCON REPAIR MAN_KNOW", new CutsceneBuilder().wait(60)
                        .waitEmote(player, cameraManager, 60)
                        .speak("THINKING",
                                "There's another apartment, and there's an AIR CONDITIONER REPAIR MAN in front of that building.",
                                "From the complaining I heard inside, there's only one explanation...",
                                "..."
                        )
                        .speak(new Dialogue(new String[]{"You're still here with me...?",
                    "Do you have any information I don't know?"},
                        "COMPUTER REPAIR MAN",
                        new DialogueOption("There's another apartment...",
                                new Dialogue(new String[]{
                            "What... in the world...?",
                            "...",
                            "Really? How can I miss that?",
                            "This will save my reputation as a repair man...",
                            "Thank you for your help!",
                            "My record won't be sullied by this stupid mistake!",
                            "Here is a small compensation for that crucial piece of information."},
                                "COMPUTER REPAIR MAN"))))
                        .wait(30)
                        .actions(OverworldItemTemplate.getItemAction("Halo on Crack",
                                playerDeckManager))
                        .setFlag("COMPUTER_QUEST_COMPLETED").buildActions())
                .buildCutscene(), getKeyNPC(computerRepairMan));

        addCutscene(cutscenes, new CutsceneBuilder().require("COMPUTER_QUEST_COMPLETED")
                .faceTowards(computerRepairMan, player)
                .speak("COMPUTER REPAIR MAN", "I'm shocked at my stupidity...",
                        "In 3 seconds, I will recover... Then, I will actually go to work.",
                        "...1", "...2", "...3", "...", "No. I'm still shocked.",
                        "Give me more time...")
                .buildCutscene(), getKeyNPC(computerRepairMan));

        addCutscene(cutscenes, new CutsceneBuilder().require("AIRCON_QUEST_COMPLETED")
                .faceTowards(airconRepairMan, player)
                .speak("AIRCON REPAIR MAN",
                        "I'm thinking of how to explain my lateness to my client...",
                        "I can't just tell them I didn't know there are 2 apartments...")
                .buildCutscene(), getKeyNPC(airconRepairMan));

        // * CUTSCENE: Methanopolis - Chef
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("CHEF_KNOW").faceTowards(chef, player).speak(
                "CHEF",
                "My friend MOLECULAR GASTRONOMIST is going to PORBITAL TOWN to buy some VANILLIN.",
                "He took too long to come back...", "I'm bored.", "Can you sing for me?").wait(60)
                .react(chef, cameraManager, Emotes.SAD).speak("CHEF", "You won't...?").wait(30)
                .waitEmote(player, cameraManager, 60).react(player, cameraManager, Emotes.MUSIC)
                .wait(30).react(chef, cameraManager, Emotes.LOVE)
                .speak("CHEF", "I have a will to continue waiting now!", "Thank you!")
                .face(chef, FacingDirections.DOWN)
                .buildCutscene(),
                getKeyNPC(chef));

        // * CUTSCENE: Methanopolis - Prime Minister Psychic 1
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("PSYCHIC_KNOW")
                .faceTowards(primeMinisterPsychic1, player)
                .speak("PSYCHIC", "Greetings, mortal.",
                        "I'm here to guard the holy TOWNHALL, the place where the prime minister of this town lives.")
                .wait(60).waitEmote(player, cameraManager, 60)
                .shout("PSYCHIC", "YOU'RE THINKING ABOUT IT RIGHT NOW, AREN'T YOU?", cameraManager)
                .shout("PSYCHIC",
                        "DON'T YOU DARE COMPARE MY LORD PRIME MINISTER TO THAT PORBITAL TOWN MAYOR!",
                        cameraManager)
                .shout("PSYCHIC", "THEY'RE ON A DIFFERENT LEAGUE ALL TOGETHER!", cameraManager)
                .speak(new Dialogue(
                        new String[]{"...", "Please pay me 500 CHEMS to continue talking.",},
                        "PSYCHIC",
                        new DialogueOption("Can you read my mind?", new Dialogue(new String[]{
                    "Read your mind?", "Fine.", "...", "...", "...",
                    "You're thinking about\n\"2-AMINO-3-(4-HYDROXYPHENYL)PROPANOIC ACID\".",
                    "Now pay me 500 CHEMS.", "...", "...",
                    "You don't have any CHEMS.", "THEN GO AWAY!!!",},
                        "PSYCHIC")),
                        new DialogueOption("I'm leaving...",
                                new Dialogue(new String[]{"Fine.", "You're so poor!",
                            "Hehehe!"}, "PSYCHIC"))))
                .wait(30).face(primeMinisterPsychic1, FacingDirections.DOWN).buildCutscene(),
                getKeyNPC(primeMinisterPsychic1));

        // * CUTSCENE: Methanopolis - Prime Minister Psychic 2
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("PSYCHIC_KNOW")
                .faceTowards(primeMinisterPsychic2, player)
                .speak(new Dialogue(new String[]{"Hello there, young one.",
            "Yes, there are two of us.",
            "The Prime Minister wants his townhall to be REALLY secure.",
            "My colleague over there is just a psychic assistant.",
            "I'm the actual psychic.", "Don't listen to her too much, okay?", "...",
            "...", "Please pay me 500 CHEMS to continue talking.",}, "PSYCHIC",
                new DialogueOption("Why do psychics need assistants?", new Dialogue(
                        new String[]{"I won't answer unless you pay me 500 CHEMS.",
                            "That's why I'm a professional.", "...",
                            "...", "You don't have any CHEMS.",
                            "THEN GET YOUR ASS AWAY FROM ME!!!",},
                        "PSYCHIC")),
                new DialogueOption("Why won't people hire actual guards?", new Dialogue(
                        new String[]{"I won't answer unless you pay me 500 CHEMS.",
                            "That's why I'm a professional.", "...",
                            "...", "You don't have any CHEMS.",
                            "THEN GET YOUR ASS AWAY FROM ME!!!",},
                        "PSYCHIC"))))
                .wait(30).face(primeMinisterPsychic2, FacingDirections.DOWN).buildCutscene(),
                getKeyNPC(primeMinisterPsychic2));

        // * CUTSCENE: Methanopolis - Aromatherapist
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("AROMA THERAPIST_KNOW")
                .faceTowards(aromatherapist, player)
                .speak("AROMA THERAPIST", "Heyyyy!!! Have you ever smelled a fresh lemon peel?!",
                        "That zesty, citrusy burst! You know what that is???", "That's....")
                .parallel(new CutsceneBuilder().emote(aromatherapist, 50, Emotes.MUSIC, cameraManager)
                        .sfx("GUIConfirm")
                        .speak("AROMA THERAPIST", "LIMONENEEEEE!!!!!",
                                "FUN FACT:\nThis Lemonene is used to inside food, cosmetics, and cleaning products!",
                                "Not only that, but it smells so good that it's used as a botanical insecticide!",
                                "The smell is TOO STRONG for insects!")
                        .buildActions())
                .buildCutscene(), getKeyNPC(aromatherapist));

        addCutscene(cutscenes, new CutsceneBuilder().setFlag("AROMA THERAPIST_KNOW")
                .faceTowards(aromatherapist, player)
                .speak("AROMA THERAPIST", "HELLOOOOO!!!! Welcome to my forest!",
                        "Ever wonder why roses smell so romantic... and not like old socks???",
                        "That's because of...")
                .parallel(new CutsceneBuilder().emote(aromatherapist, 50, Emotes.MUSIC, cameraManager)
                        .sfx("GUIConfirm")
                        .speak("AROMA THERAPIST", "GERANIOLLL!!!!",
                                "It's the main component that makes roses smell like LOVE!!!",
                                "And unlike many compounds with smells, this one doesn't have aromatic rings!",
                                "Magic!",
                                "FUN FACT:\nThis molecule can soothe, protect, and enhance your skin health!!!",
                                "Isn't that BEAUTIFUL???")
                        .buildActions())
                .buildCutscene(), getKeyNPC(aromatherapist));
    }

    private static void workshopTalks(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager,
            CameraManager cameraManager, Player player) {
        NPC intern1_1 = npcManager.getNPC("Intern1_1");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("INTERN_KNOW").faceTowards(intern1_1, player)
                .speak("INTERN", "The sun is a deadly laser.", "But not for us. We are... enlightened.",
                        "These shades protect our vision from the radical reactions.")
                .wait(60)
                .shout("INTERN", "WITH OUR NEW TECHNOLOGY, YOU CAN BE SAFE FROM THE SUNLIGHT!!",
                        cameraManager)
                .shout("INTERN", "DON'T SUCCUMB TO THE HALOGEN RADICALS ANYMORE!", cameraManager)
                .shout("INTERN", "RISE! HUMAN, RISE!!!", cameraManager).wait(60)
                .waitEmote(player, cameraManager, 60)
                .speak("INTERN", "Ahem...",
                        "This sunglass is one of our LAB's product. You can get it at a low low cost of...",
                        "1 TRILLION CHEMS!!!", "Would you like some?")
                .wait(180).speak("INTERN", "Okay, fine.").face(intern1_1, FacingDirections.RIGHT)
                .buildCutscene(), getKeyNPC(intern1_1));

        NPC intern1_2 = npcManager.getNPC("Intern1_2");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("INTERN_KNOW").faceTowards(intern1_2, player)
                .speak("INTERN", "We don't see with our eyes. We see with our minds.",
                        "Even if you are blind physically, if your brain wires sound signal to visual cortex, you can see with your ears.",
                        "This is called \"auditory-visual synaesthesia\".",
                        "So, even without light, you can see.",
                        "Even without clear glasses, you can see.",
                        "Protect yourself from RADICALS with our sunglasses!",
                        "With a price of 1 trillion chems, you can see, and be safe!")
                .face(intern1_2, FacingDirections.LEFT).buildCutscene(), getKeyNPC(intern1_2));

        NPC intern1_3 = npcManager.getNPC("Intern1_3");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("INTERN_KNOW").faceTowards(intern1_3, player)
                .speak("INTERN", "I'm watching a video about how historic figures measured the sun.",
                        "It's very interesting.",
                        "I like how they can be creative with their methods.",
                        "They break conventional idea of geocentric model.",
                        "We're doing something similar.")
                .wait(30)
                .shout("INTERN", "OUR SUNGLASSES CAN DEFEND YOU AGAINST RADICALS FROM LIGHT!",
                        cameraManager)
                .shout("INTERN", "A NEW TECHNOLOGY NEVER THOUGHT OF BEFORE!", cameraManager)
                .shout("INTERN", "1 TRILLION CHEMS ONLY!", cameraManager)
                .face(intern1_3, FacingDirections.RIGHT).buildCutscene(), getKeyNPC(intern1_3));

        NPC intern1_4 = npcManager.getNPC("Intern1_4");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("INTERN_KNOW").faceTowards(intern1_4, player)
                .speak("INTERN", "They call us interns. We call ourselves... human.",
                        "Even if we are here for work experiences, we built up this place far better than other employee.",
                        "They aren't even here.")
                .wait(30).shout("INTERN", "WE ARE NOT HORSES!", cameraManager).wait(30)
                .shout("INTERN", "WE ARE HUMANS!", cameraManager).wait(30)
                .shout("INTERN", "SO...", cameraManager).wait(30)
                .shout("INTERN", "PAY US PROPERLY!!!", cameraManager).wait(30)
                .shout("INTERN", "WITH ONLY 1 TRILLION CHEMS, YOU CAN BE SAFE, AND I CAN BE FREE!!!",
                        cameraManager)
                .face(intern1_4, FacingDirections.UP).buildCutscene(), getKeyNPC(intern1_4));

        NPC intern2 = npcManager.getNPC("Intern2");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("INTERN_KNOW").faceTowards(intern2, player)
                .speak("INTERN", "Welcome, to my personal lab.",
                        "No other interns have the rights to enter this lab.",
                        "You are special.", "Let me tell you about something...")
                .music("Cave")
                .speak("INTERN", "In this world, there are 2 types of break up.",
                        "Let's say you became my wife, and we signed a contract.",
                        "You are my wife, and I am your husband.",
                        "But, one day, you decided to break up with me.",
                        "You said you want to be free.",
                        "You filed a complaint to the high court.", "What will happen?")
                .wait(30).react(player, cameraManager, Emotes.QUESTION).wait(30)
                .speak("INTERN", "You will be free.", "No matter win or lose, you will be free.",
                        "But at what cost?",
                        "If you win, you get all the properties we built up over our time together.",
                        "If I win, I get all the properties.", "Isn't that fair?")
                .wait(30).waitEmote(player, cameraManager, 60).wait(30).speak("INTERN",
                "Nobody want to lose their properties, even if it's the one built up together.",
                "I will try my best to manipulate the court to my favor.",
                "You will lose everything.", "You can't win.",
                "Because I'm more negative than you.", "Electronegative...",
                "If you select HOMOLYTIC CLEAVAGE, you will lose everything.",
                "No HOMO.",
                "(LYTIC CLEAVAGE)",
                "So...", "What's the solution?")
                .wait(90).shout("INTERN", "HETEROLYTIC CLEAVAGE!!!", cameraManager).wait(30)
                .speak("INTERN", "You don't go to court.", "You ask someone else to break us up.",
                        "There's no court, no judge, no jury.",
                        "There's no way I can manipulate things to my favor.",
                        "Because I can't manipulate something not under my control.")
                .wait(120).sfx("BattleDamageSuper").shout("INTERN", "LIGHT!!!", cameraManager).wait(120)
                .speak("INTERN", "You shine a bright light at our relationship.",
                        "A bright, blinding light that destroy everything in its path.",
                        "We have no choice but to break up.",
                        "And since this isn't anyone's fault by the rule of court, we will be free.",
                        "We will get half of what we have built up together.",
                        "You will get fairness...")
                .wait(30).shout("INTERN", "HOMOLYTIC CLEAVAGE!!!", cameraManager).wait(30)
                .waitEmote(player, cameraManager, 60).wait(30).music("Lab")
                .speak("INTERN", "That's all I have for you.",
                        "Even if it's a story about breaking up, it's the start of the new stage of life.",
                        "It's INITIATION.",
                        "If you like my explanation, please buy my sunglasses V2.",
                        "It's 300% better than the ones the big LABs with multiple people are building.",
                        "It's 999 billion chems, and it's worth it.", "I guarantee.")
                .react(intern2, cameraManager, Emotes.FRIENDLY).face(intern2, FacingDirections.DOWN)
                .buildCutscene(), getKeyNPC(intern2));

        NPC intern3_1 = npcManager.getNPC("Intern3_1");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("INTERN_KNOW").faceTowards(intern3_1, player)
                .speak("INTERN", "Our research is... abstract. You wouldn't get it.",
                        "It involves a lot of staring into nothingness. With sunglasses.",
                        "Empty, going on forever, until it stops",
                        "Buy my glasses, and it will stop.", "Trust me.")
                .face(intern3_1, FacingDirections.LEFT).buildCutscene(), getKeyNPC(intern3_1));

        NPC intern3_2 = npcManager.getNPC("Intern3_2");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("INTERN_KNOW").faceTowards(intern3_2, player)
                .speak("INTERN", "Have you ever play tag?",
                        "It's a game where you run around and try to catch someone.",
                        "If you can tag someone, you're safe, and that person needs to tag someone else.",
                        "Let's say you are IT, you are a RADICAL.",
                        "You have never felt love in this life. You want love.",
                        "You go tag someone else, and not only that, you steal their loved ones.",
                        "Because you are evil radical. You can steal anybody's loved ones.",
                        "You became fullfilled. However...",
                        "That someone lose their loved ones. They now want love.",
                        "They now want to tag someone else. They now want to steal someone else's loved ones.",
                        "That's how life goes.", "That's how life PROPAGATES.",
                        "That's how evil propagates to the world...",
                        "And that's why you will buy my glasses for 1.1 trillion CHEMS.",
                        "You lack loved ones, and this sunglasses will fill that place...")
                .face(intern3_2, FacingDirections.UP).buildCutscene(), getKeyNPC(intern3_2));

        NPC oldIntern1 = npcManager.getNPC("OldIntern1");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("OLD INTERN_KNOW").faceTowards(oldIntern1, player)
                .speak("OLD INTERN", "We were the first ones to think of black color glasses.",
                        "We were cool back then.",
                        "But as time passes, the only thing I can contribute to this lab is eating.",
                        "I don't have the power to sell sunglasses anymore.")
                .face(oldIntern1, FacingDirections.DOWN).buildCutscene(), getKeyNPC(oldIntern1));

        NPC oldIntern2 = npcManager.getNPC("OldIntern2");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("OLD INTERN_KNOW").faceTowards(oldIntern2, player)
                .speak("OLD INTERN", "I've been an intern here for 40 years.",
                        "One day, I'll graduate...", "The day I can buy my own sunglasses.",
                        "Please buy it...")
                .face(oldIntern2, FacingDirections.DOWN).buildCutscene(), getKeyNPC(oldIntern2));

        NPC intern4_1 = npcManager.getNPC("Intern4_1");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("INTERN_KNOW").faceTowards(intern4_1, player)
                .speak("INTERN", "The secrets of the universe are hidden in the food you eat",
                        "Even if I don't look like other interns in other LABs, I know more than all of them combined.",
                        "I know the secrets of the universe.",
                        "And it's how two evil RADICALS that love each other very much meet each other, and they become stable.",
                        "They no longer need to wreak havoc to the world.",
                        "They have each other...", "And that's enough.",
                        "BTW, Buy my innovative sunglasses, please?")
                .face(intern4_1, FacingDirections.DOWN).buildCutscene(), getKeyNPC(intern4_1));

        NPC intern4_2 = npcManager.getNPC("Intern4_2");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("INTERN_KNOW").faceTowards(intern4_2, player)
                .speak("INTERN", "I ate the secrets of the universe.", "They tasted like... chicken.",
                        "My sunglasses taste better.", "Only 999,999,999,999 CHEMS.")
                .face(intern4_2, FacingDirections.DOWN)
                .buildCutscene(), getKeyNPC(intern4_2));
    }

    private static void townhallTalks(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager,
            CameraManager cameraManager, Player player) {
        NPC primeMinister = npcManager.getNPC("PrimeMinister");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("PRIME MINISTER_KNOW")
                .faceTowards(primeMinister, player)
                .speak(new Dialogue(new String[]{
            "Ah, another netizen of the LPG. Welcome to the METHANOPOLIS TOWNHALL.",
            "I am drafting another maid to work in the mining town of PYRROLE.",
            "Your strength would prove useful to the town."},
                "PRIME MINISTER",
                new DialogueOption("I'm not interested.",
                        new Dialogue(new String[]{"That's too bad.", "Come here again when you're ready."},
                        "PRIME MINISTER")
                ),
                new DialogueOption("As a child, I yearn for the mines.",
                        new Dialogue(new String[]{"Haha..."},
                        "PRIME MINISTER"),
                        () -> FlagManager.getInstance().addFlag("SELECT_MINES")
                ),
                new DialogueOption("What does LPG stands for?",
                        new Dialogue(new String[]{
                    "LPG?",
                    "It's Liquefied Petroleum Gas.",
                    "The composition is mostly propane and butane, which are both hydrocarbons.",
                    "Propane has 3 carbon atoms, while butane has 4 carbon atoms.",
                    "When I call you \"netizen of the LPG\", it means you can potentially be a worker in the LPG mines.",
                    "This includes EVERYONE in the SIGMA GRASSLAND.",
                    "Aren't you proud of yourself?"
                }, "PRIME MINISTER")
                ),
                new DialogueOption("Why are maids working in the mines?",
                        new Dialogue(new String[]{
                    "An excellent question! It's a matter of precision and elegance.",
                    "The maids are trained to be able to measure precise amount of chemicals up to milligrams.",
                    "A clumsy miner with a blue shirt and a pickaxe would destroy everything in no time",
                    "Also, the maids are loyal to me, so there's no chance of them stealing MY LPG.",
                    "...",
                    "Ahem... THE CITIZEN'S LPG.",
                    "...",}, "PRIME MINISTER")
                ),
                new DialogueOption("Why are psychics guarding you?",
                        new Dialogue(new String[]{
                    "Physical threats are nothing with my psychics.",
                    "They can detect malicious intent from a mile away. Nobody can even enter this town if they want to harm me.",
                    "They are the ultimate firewall.",
                    "Only 500 CHEMS a day, and you are almost invincible.",
                    "Isn't it cheap?"
                }, "PRIME MINISTER")
                ),
                new DialogueOption("What do you do?",
                        new Dialogue(new String[]{
                    "I control the entire economy of the SIGMA GRASSLAND!",
                    "Just so you know, the LPG mines are the most profitable business in all of ATOMIA region.",
                    "My job is to protect the benefits of everyone here by doing paperworks.",
                    "It mostly involves reading \"Terms and Conditions\" and telling people \"No\" when there's weird stuff in the contract.",
                    "And delegating, of course. Lots of delegating.",
                    "Like putting the right people to the right job, such as maids in the mines.",
                    "I'm the best at it."
                }, "PRIME MINISTER")
                ),
                new DialogueOption("PYRROLE town?",
                        new Dialogue(new String[]{
                    "PYRROLE is a town in the SIGMA GRASSLAND.",
                    "It's located to the north of METHANOPOLIS.",
                    "You can walk there through ROUTE 3",
                    "If you wonder what a pyrrole is, it's a molecule that's a part of CHLOROPHYLL, HEMOGLOBIN, and VITAMIN B12.",
                    "And if you're confused why it's called \"PYRROLE\" town when it's actually a mining town...",
                    "It's because people there work so hard that their red blood cell disintegrates.",
                    "They are so poor that they can't afford to buy a new one.",
                    "So, they must make do with PYRROLE molecule instead of HEMOGLOBIN.",
                    "...",
                    "Don't ask me about how that works. I don't know.",
                    "Game logic, I guess."
                }, "PRIME MINISTER")
                )))
                .buildCutscene(), getKeyNPC(primeMinister));

        NPC maid1 = npcManager.getNPC("Maid1");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("MAID_KNOW").faceTowards(maid1, player)
                .speak("MAID", "Welcome. Please wash your hands.",
                        "The Prime Minister is very particular about germs.",
                        "He doesn't want to contract CYSTIC FIBROSIS from anyone.",
                        "He says it... clogs his gears of democracy.",
                        "I'm not sure what that means, but I'm sure it's important to him.")
                .wait(30)
                .waitEmote(maid1, cameraManager, 60)
                .wait(30)
                .speak("MAID",
                        "Also... Don't tell him that CYSTIC FIBROSIS isn't caused by a germ.",
                        "It's just a genetic mutation passed down from generation to generation.",
                        "It effects mucus-producing cells and they can't produce good quality mucus.",
                        "...",
                        "So, don't tell him please? Let him dream in peace...",
                        "Just like when kids dream about unicorns and santa..."
                )
                .face(maid1, FacingDirections.UP)
                .buildCutscene(), getKeyNPC(maid1));

        NPC maid2 = npcManager.getNPC("Maid2");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("MAID_KNOW").faceTowards(maid2, player)
                .speak("MAID",
                        "I polished this table for three hours today.",
                        "You can literally see your reflection in it.",
                        "See? That's your face.",
                        "...",
                        "The face that nobody takes seriously."
                )
                .face(maid2, FacingDirections.DOWN)
                .buildCutscene(), getKeyNPC(maid2));

        NPC maid3 = npcManager.getNPC("Maid3");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("MAID_KNOW").faceTowards(maid3, player).speak(
                "MAID",
                "The other maids and I have a bet on how long the Prime Minister's latest policy will last.",
                "It's for \"cleaning\" up the city.",
                "The policy prohibits people from walking around in circle, wandering randomly, and following paths.",
                "I'm betting on \"less than a week\".",
                "The other maid bet on \"not even a minute\".",
                "Since it's work time, and a minute has passed since we made the bet, can you please go confirm it for us?",
                "...")
                .wait(30)
                .waitEmote(player, cameraManager, 60)
                .react(player, cameraManager, Emotes.SAD)
                .wait(30)
                .speak("MAID",
                        "Ha... That face tells me everything.",
                        "I lost huh?",
                        "That's too bad.",
                        "I guess I'll need to pay her 2 MAID OUTFITS"
                )
                .face(maid3, FacingDirections.RIGHT)
                .buildCutscene(), getKeyNPC(maid3));

        NPC maid4 = npcManager.getNPC("Maid4");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("MAID_KNOW").faceTowards(maid4, player)
                .speak("MAID",
                        "This is the second floor. It's... the same as the first floor, but higher.",
                        "You're closer to the sun.",
                        "Isn't it profound?",
                        "Everything's so easy. You can just randomly win 2 MAID OUTFITS from other maids out of nowhere.",
                        "Luck is something else when you receive solar energy.",
                        "What idiots would cover their eyes with black glasses just to not receive solar energy?",
                        "Don't you agree?"
                )
                .face(maid4, FacingDirections.RIGHT)
                .buildCutscene(), getKeyNPC(maid4));

        NPC maidFake = npcManager.getNPC("MaidFake");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("MAID FAKE_KNOW").faceTowards(maidFake, player)
                .speak(new Dialogue(
                        new String[]{
                            "Ahem! Yes, I am a maid. A completely normal one.",
                            "I am... maiding...",
                            "...",
                            "Okay, I'm done trying to deceive you anymore.",
                            "I'm actually the real Prime Minister."
                        }, "MAID FAKE",
                        new DialogueOption("Are you a spy?", new Dialogue(new String[]{
                        "A spy? Don't be ridiculous!",
                        "Why would a spy tell you who they are..",
                }, "MAID FAKE"), ()->FlagManager.getInstance().addFlag("SELECT_SPY")),
                        new DialogueOption("You don't look like a maid.", new Dialogue(new String[]{
                    "I'm not a maid. I'm the Prime Minister.",
                    "Can you tell me how I can be more maid-like?"
                }, "MAID FAKE",
                        new DialogueOption("You should be more feminine.", new Dialogue(new String[]{
                    "...What?",
                    "I pride myself for being a very cute femboy.",
                    "How am I not feminine enough?"
                }, "MAID FAKE")),
                        new DialogueOption("You should call me master.", new Dialogue(new String[]{
                    "Ok, master. Would you like dinner? A bath? Or perhaps...",
                    "A HIGH QUALITY LPG GAS FOR YOUR GASOLINE CAR?"
                }, "MAID FAKE")),
                        new DialogueOption("You should work instead of reading.", new Dialogue(new String[]{
                    "Really? I thought maids are supposed to be smart so they can help with the master's work.",
                    "How would they help me calculate the cashflow and GDP of this country without knowledge?"
                }, "MAID FAKE")))),
                        new DialogueOption("Why are you disguising yourself?", new Dialogue(new String[]{
                    "I'm disguising myself because the psychics can't really protect me anymore.",
                    "They stop asking me for CHEMS and instead ask random people.",
                    "I have all the CHEMS in the world from taxing the LPG products, but for some reason, they won't ask me for it.",
                    "They just stop doing their jobs and whine about not getting CHEMS...",
                    "So to protect myself, this is the only way."
                }, "MAID FAKE")),
                        new DialogueOption("What are you reading?", new Dialogue(new String[]{
                    "I'm reading the new research regarding the ANTIOXIDANT and how they stop FREE RADICALS from causing cancer.",
                    "You might know about radicals already, right?",
                    "...",
                    "If you don't, go talk to those weird people at the LABs.",
                    "Well, how ANTIOXIDANT works is that they donate 1 unpaired electrons to the free radicals.",
                    "So instead of having insane crazy radicals go around attacking your cells, they become stable and harmless.",
                    "Very interesting research.",
                    "I'll promote this product inside our market, so our citizens can be healthy."
                }, "MAID FAKE"))))
                .condition("SELECT_SPY", new CutsceneBuilder()
                        .music("Cave")
                        .speak("MAID FAKE",
                                "Rather... There might be a spy maid among us...",
                                "I don't know who they are though... I only remember hiring 4 maids, not 5.",
                                "One maid is assigned to pretend to be the prime minister",
                                "And the other 3 is meant to do the actual work.",
                                "But when I walk around the house, I can see 4 maids doing actual works...",
                                "I don't know...",
                                "Can you help me find out which maid is the spy?"
                        )
                        .removeFlag("SELECT_SPY")
                        .buildActions()
                )
                .face(maidFake, FacingDirections.UP)
                .buildCutscene(), getKeyNPC(maidFake));
    }

    private static void pokecenterTalks(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager,
            CameraManager cameraManager, Player player) {

        NPC centerReceptionist = npcManager.getNPC("CenterReceptionist");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("RECEPTIONIST_KNOW")
                .faceTowards(centerReceptionist, player)
                .speak( new Dialogue(new String[]{
                        "Hello, and welcome to the MOLECULE CENTER.",
                        "We restore your tired MOLECULE to full health.",
                        "Would you like to rest your MOLECULES?"
                    }, "RECEPTIONIST",
                    new DialogueOption("Yes, please.", new Dialogue(new String[]{
                        "Okay, please pay 500 CHEMS for the fee.",
                        "Then I'll take your MOLECULES for a few seconds...",
                        "...",
                        "...",
                        "...",
                        "No CHEMS?",
                        "Okay, young alchemist.",
                        "This is the adult world. Everything needs money.",
                        "Go find some money first and I'll heal your MOLECULES."
                    }, "RECEPTIONIST")),
                    new DialogueOption("No, thank you.", new Dialogue(new String[]{
                        "We hope to see you again!"
                    }, "RECEPTIONIST"))
                ))
                .buildCutscene(),
                getKeyLook(6,5,"methanopolis__pokecenter_f1"));

        NPC centerResearcher = npcManager.getNPC("CenterResearcher");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("RESEARCHER_KNOW")
                .faceTowards(centerResearcher, player)
                .speak("RESEARCHER",
                        "What business do you have with me?",
                        "I'm a researcher.",
                        "I'm researching new way to protect yourself from sunlight.",
                        "It's called rain.",
                        "You don't need those expensive sunglasses to protect yourself.",
                        "You just need to create rain...",
                        "...",
                        "If you don't have anything more, let me go back to work."
                )
                .shout("RESEARCHER", "LET ME GO BACK TO WORK!", cameraManager)
                .face(centerResearcher, FacingDirections.UP)
                .buildCutscene(),
                getKeyNPC(centerResearcher));

        NPC centerOld1 = npcManager.getNPC("CenterOld_1");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("OLD CITIZEN_KNOW")
                .faceTowards(centerOld1, player)
                .speak("OLD CITIZEN",
                        "I don't have anywhere to go...",
                        "At least, this place have air conditioner."
                )
                .buildCutscene(),
                getKeyNPC(centerOld1));

        NPC centerOld2 = npcManager.getNPC("CenterOld_2");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("OLD CITIZEN_KNOW")
                .faceTowards(centerOld2, player)
                .speak("OLD CITIZEN",
                        "The youngsters these days... with their complex functional groups.",
                        "In my day, we only had alkanes and branches, and we were happy!"
                )
                .face(centerOld2, FacingDirections.DOWN)
                .buildCutscene(),
                getKeyNPC(centerOld2));

        NPC centerOld3 = npcManager.getNPC("CenterOld_3");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("OLD CITIZEN_KNOW")
                .faceTowards(centerOld3, player)
                .speak("OLD CITIZEN",
                        "You know? I was once a professional sumo wrestler.",
                        "I was so strong that I could lift an airplane with my bare hands.",
                        "A paper airplane.",
                        "I regret not being able to fight people anymore.",
                        "If only there's someone who wants to fight me again..."
                )
                .face(centerOld3, FacingDirections.UP)
                .buildCutscene(),
                getKeyNPC(centerOld3));

        NPC centerOld4 = npcManager.getNPC("CenterOld_4");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("OLD CITIZEN_KNOW")
                .faceTowards(centerOld4, player)
                .speak("OLD CITIZEN",
                        "I love the smell of chemicals in this sink."
                )
                .face(centerOld4, FacingDirections.UP)
                .buildCutscene(),
                getKeyNPC(centerOld4));
    }

    private static void pokemartTalks(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager,
            Player player) {
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("MART WORKER_KNOW")
                .speak("MART WORKER",
                        "...",
                        "You want to buy something?",
                        "Bring the item here..."
                    )
                .buildCutscene(),
                getKeyLook(9, 4, "methanopolis__pokemart"),
                getKeyLook(12, 6, "methanopolis__pokemart"));

        NPC shopper1 = npcManager.getNPC("Shopper1");
        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("MAID_KNOW")
                .faceTowards(shopper1, player)
                .speak("MAID",
                    "1 Methane...",
                    "2 Ethane...",
                    "3 Propane...",
                    "4 Butane...",
                    "...",
                    "This shopping list is so weird..."
                )
                .buildCutscene(),
                getKeyNPC(shopper1));

        NPC shopper2 = npcManager.getNPC("Shopper2");
        addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("MAID_KNOW")
                .faceTowards(shopper2, player)
                .speak("MAID",
                    "Do you know where I can find Hydrofluoric acid?",
                    "My master need to dissolve a 80 kg chicken.",
                    "...",
                    "You don't know? Sorry for disrupting your shopping time."
                )
                .buildCutscene(),
                getKeyNPC(shopper2));
    }

    private static void botanistHouseTalks(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager,
            CameraManager cameraManager, Player player) {
        NPC aromaTherapist2 = npcManager.getNPC("AromaTherapist2");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("AROMA THERAPIST_KNOW")
                .faceTowards(aromaTherapist2, player)
                .speak("AROMA THERAPIST",
                    "OMG!!! What is that pungent smell?!",
                    "That's the smell of... SAFETY!",
                    "My LPG gas used for cooking is naturally odorless.",
                    "But right now, the gas is leaking from the stove, so how would I know that something's wrong??",
                    "It's THAT smell that will tell me!",
                    "That's..."
                )
                .parallel(new CutsceneBuilder()
                        .emote(aromaTherapist2, 50, Emotes.MUSIC, cameraManager)
                        .sfx("GUIConfirm")
                        .speak("AROMA THERAPIST",
                            "ETHANETHIOLLLLL!!!!!",
                            "It's the molecule they add to LPG, to make it more smelly!",
                            "So if you smell it, you know there's a leak and you won't go BOOM!",
                            "FUN FACT:\nSome people describe its smell as \"skunky, fecal odor\".",
                            "And some describe it as \"pungent, garlic, and durian\".",
                            "Which is weird, because I like to eat durian, and it smells nice."
                        )
                        .buildActions()
                )
                .face(aromaTherapist2, FacingDirections.DOWN)
                .buildCutscene(), getKeyNPC(aromaTherapist2));

        NPC aromaTherapist3 = npcManager.getNPC("AromaTherapist3");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("AROMA THERAPIST_KNOW")
                .faceTowards(aromaTherapist3, player)
                .speak("AROMA THERAPIST",
                    "Ah, welcome! Take a deep breath!",
                    "You smell that earthy smell after a huge drop of rain fall upon the ground?",
                    "The smell that you can't get enough of?",
                    "That's..."
                )
                .parallel(new CutsceneBuilder().emote(aromaTherapist3, 50, Emotes.MUSIC, cameraManager)
                        .sfx("GUIConfirm")
                        .speak("AROMA THERAPIST",
                            "GEOSMINN!!!",
                            "It's the molecule produced by soil bacteria that gives rain its signature smell!",
                            "The structure of the molecule has TWO 6-carbon rings. It's DOUBLE rings.",
                            "It's called BICYCLIC. Not to be confused with BICYCLE.",
                            "Bicycle is more painful because you can fall off it and die.",
                            "FUN FACT:\nThe bactaria \"Streptomyces\" evolve this smell for a symbiotic relationship!",
                            "When flowers are beautiful and have a nice smell, they attract bees to pollinate them.",
                            "So flowers that attracts more bees can reproduce more and pass down more attractive genes.",
                            "For Streptomyces, it's the same thing.",
                            "They evolved this smell to attract springtails to help with their spore dispersal.",
                            "ECOSYSTEM!!!"
                            )
                        .buildActions())
                .buildCutscene(), getKeyNPC(aromaTherapist3));
    }

    private static void apartment1Talks(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager,
            Player player) {
        NPC blueReception = npcManager.getNPC("BlueApartmentReception");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("BLUE_KNOW").faceTowards(blueReception, player)
                .speak("BLUE",
                        "Welcome to the Blue apartment.",
                        "I'm blue.",
                        "And every residence in this apartment is also blue.",
                        "If you're not blue, you're still welcome, but you might feel a bit... out of place."
                )
                .buildCutscene(), getKeyLook(4, 4, "methanopolis__apartment1_f1"));

        for (int i = 1; i <= 8; i++) {
            NPC blue = npcManager.getNPC("Blue" + i);
            FacingDirections facing = blue.getCurrentDirection();
            String[] messages = switch (i) {
                case 1 -> new String[]{"I'm gonna ask you this out of the blue...", "Do you think I have a sigma male face?", "...", "...", "...", "Ok..."};
                case 2 -> new String[]{"Blue is the color of trust and stability.", "It's also cold and sad.", "And sometimes, it's also scary.",
         "Just like when flame color hotter than red is blue, you too can be blue.", "Just do it. You can be blue."};
                case 3 -> new String[]{"Copper.", "...", "You just thought of ORANGE, didn't you?", "Hmph!!!", "You're not loyal to us. There are also blue copper. Please remember that!"};
                case 4 -> new String[]{"Blue is the color of the ocean.", "And the ocean gives my life a meaning!", "I like seeing oceans on google maps.", "I'm know it exist, I see it, but I'll never experience it. That's the beauty.", "I'll never leave this room."};
                case 5 -> new String[]{"Blueprint paper.", "It consists of AMMONIUM FERRIC CITRATE and POTASSIUM FERRICYANIDE.", "I'd like you to focus on the CITRATE part.", "Yes. It's the citrate in oranges.", "The ORANGES are putting spies on us already.", "We need to be on guard."};
                case 6 -> new String[]{"Blow, Blew,", "Blue", "Bleu", "Billed loo", "...", "BOO"};
                case 7 -> new String[]{"Sometimes, every good puns are all taken by other people already.", "What you're left with is the clunky puns made by AI.", "It's sad, but that's the truth of life.", "It's navy-er too late to accept that fact..."};
                case 8 -> new String[]{"Support me with my studies please. Just buy one blue crayon, and I will continue my studies.", "It's only 666 CHEMS. Please!", "The price is definitely not suspicious in the slightest.", "Don't you want a good educated citizen to contribute to the world?"};
                default -> new String[]{""};
            };
            addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("BLUE_KNOW")
                .faceTowards(blue, player)
                .speak("BLUE", messages)
                .face(blue, facing)
                .buildCutscene(), getKeyNPC(blue));
        }
    }

    private static void apartment2Talks(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager,
            CameraManager cameraManager, Player player) {
        NPC yellowReception = npcManager.getNPC("YellowApartmentReception");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("YELLOW_KNOW").faceTowards(yellowReception, player)
                .speak("YELLOW",
                        "Hello, red! I'm yellow.",
                        "...",
                        "Wait, you're not red?",
                        "Okay, whatever. I'm yellow.",
                        "And every residence in this apartment is also yellow.",
                        "Maybe except that one gambler on the 3rd floor.",
                        "You can talk to him if you want some break from yellows."
                )
                .buildCutscene(), getKeyLook(5, 4, "methanopolis__apartment2_f1"));

        for (int i = 1; i <= 7; i++) {
            NPC yellow = npcManager.getNPC("Yellow" + i);
            FacingDirections facing = yellow.getCurrentDirection();
            String[] messages = switch (i) {
                case 1 -> new String[]{"You know? Yellow, compared to blue, has a lot less things you can do with.", "In english, nothing really sounds like \"Yellow\". You can yell \"OH!!!\", and that's it."};
                case 2 -> new String[]{"I'm yellow like a banana.", "Ba Ba Ba Ba Ba Banana.", "Potato Na Ah Ah", "Don't eat me."};
                case 3 -> new String[]{"Yellow is the color of gold.", "It's the color of scamming rich people without education.", "It's also the color of theives in B-grade movies who only mindlessly steal valuables."};
                case 4 -> new String[]{"Yellow...", "It doesn't exist...", "You're seeing illusions. I'm not yellow...", "I'm a mix of green and red, but your eyes are too bad to distinguish..."};
                case 5 -> new String[]{"You know why rubber ducks are yellow?", "Because if it's not yellow, it's not a rubber duck!"};
                case 6 -> new String[]{"When life gives me green limes,", "I demand yellow lemons from it.", "NO GREEN ALLOWED HERE IN MY PLACE!"};
                case 7 -> new String[]{"Ever thought why file explorer icons are yellow?", "If you are thinking about that, please get help.", "That knowledge doesn't help with your life in any way.", "Go do something productive."};
                default -> new String[]{""};
            };
            addCutscene(cutscenes, new CutsceneBuilder()
                .setFlag("YELLOW_KNOW")
                .faceTowards(yellow, player)
                .speak("YELLOW", messages)
                .face(yellow, facing)
                .buildCutscene(), getKeyNPC(yellow));
        }

        NPC gambler = npcManager.getNPC("Gambler");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("GAMBLER_KNOW").faceTowards(gambler, player)
                .speak(new Dialogue(new String[] {
                        "I'm a gambler.",
                        "I'm an ethical gambler.",
                        "In my mind, it rolled head. This round, it must roll tail with 90% certainty.",
                        "If you don't believe, I'll give you my chips for free.",
                        "Play?"
                },"GAMBLER",
                new DialogueOption("No, thanks.", new Dialogue(new String[] {
                        "Okay, fine.",
                }, "GAMBLER")),
                new DialogueOption("Yes, please.", new Dialogue(new String[] {
                        "Nice! Let me toss the coin!",
                }, "GAMBLER"), ()-> {
                        FlagManager.getInstance().addFlag(new Random().nextBoolean() ? "GET_HEAD" : "GET_TAIL");
                        FlagManager.getInstance().addFlag("GAMBLER_PLAY");
                }
                )))
                .condition("GAMBLER_PLAY", new CutsceneBuilder()
                        .wait(30)
                        .parallel(new CutsceneBuilder()
                                .sfx("BattleDamageSuper")
                                .camShake(cameraManager, 90)
                                .buildActions()
                        )
                        .wait(60)
                        .speak("THINKING",
                                "...",
                                "You see the coin floating in the air in a parabolic movement.",
                                "And then it falls...",
                                "The side that it lands on is..."
                        )
                        .wait(60)
                        .condition("GET_HEAD", new CutsceneBuilder()
                                .speak("THINKING", "HEAD")
                                .wait(60)
                                .speak("GAMBLER",
                                        "...",
                                        "You... win...?",
                                        "No, I won't pay you anything. I'm evil."
                                )
                                .buildActions()
                        )
                        .condition("GET_TAIL", new CutsceneBuilder()
                                .speak("THINKING", "TAIL")
                                .wait(60)
                                .speak("GAMBLER",
                                        "HAHAHA, IT'S JUST AS I SAID!!!",
                                        "BEHOLD MY POWERRRRR!!!"
                                )
                                .buildActions()
                        )
                        .removeFlag("GAMBLER_PLAY")
                        .removeFlag("GET_HEAD")
                        .removeFlag("GET_TAIL")
                        .buildActions()
                )
                .face(gambler, FacingDirections.UP)
                .buildCutscene(), getKeyNPC(gambler));
    }

    private static void housesTalks(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager,
            CameraManager cameraManager, Player player) {
        NPC house2Person = npcManager.getNPC("House2Person");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("MAY_KNOW").faceTowards(house2Person, player)
                .speak("MAY",
                        "Have you met me before?",
                        "Or should I ask...",
                        "Have you met \"ME\" before?",
                        "... ... ... ... ...\n... ... ... ... ...",
                        "Fine.",
                        "I have work to do here. Don't bother me."
                )
                .buildCutscene(), getKeyNPC(house2Person));

        NPC house1Person = npcManager.getNPC("House1Person");
        addCutscene(cutscenes, new CutsceneBuilder().setFlag("BLACKBELT_KNOW").faceTowards(house1Person, player)
                .speak("BLACKBELT",
                        "To be a true alchemist, you must train your mind and your soul!",
                        "Here in this gym, we do 100 push ups, 100 sit ups, and a 10km run every single day!",
                        "And on top of that, we do 10 synthesis battles after lunch break!",
                        "That's how we become true alchemist."
                )
                .buildCutscene(), getKeyNPC(house1Person));
    }
}
