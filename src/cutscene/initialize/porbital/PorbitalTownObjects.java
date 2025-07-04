package cutscene.initialize.porbital;

import cutscene.Cutscene;
import cutscene.CutsceneBuilder;
import cutscene.template.CutsceneTemplate;
import cutscene.template.OverworldItemTemplate;
import dialogue.Dialogue;
import dialogue.DialogueOption;
import entity.FacingDirections;
import entity.Player;
import gamestates.CameraManager;
import gamestates.FlagManager;
import java.util.List;
import java.util.Map;
import pokedex.PlayerDeckManager;

public class PorbitalTownObjects extends CutsceneTemplate {
    public static void initialize(Map<String, List<Cutscene>> cutscenes, CameraManager cameraManager, Player player, PlayerDeckManager playerDeckManager) {


/*
* -----------------------------------------------------------------------------
* Location: Porbital Town
* -----------------------------------------------------------------------------
*/

//* vanillin extract
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "The product label says \"Vanillin Extract\".",
                "There's a picture of its molecular structure on the label too.",
                "It costs 1000 CHEMS per bottle.",
                "You don't know if it's cheap or expensive since you have no idea how much CHEMS are worth."
            ).buildCutscene(),
            getKeyLook(15, 8, "porbital_town")
        );

//* Yuuki's house mailbox
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "\"When you think of a sweet, you think of us!\nThe most pure GLUCOSE in the world!\"",
                "\"Exclusively sold at the HOURSE OF STARCH! Buy now at a discounted price of 100 CHEMS!\""
            ).buildCutscene(),
            getKeyLook(23, 13, "porbital_town")
        );

//* Cellulose's house mailbox
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "It's a mailbox.",
                "It's empty."
            ).buildCutscene(),
            getKeyLook(25, 13, "porbital_town")
        );

//* Cellulose's house sign
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "\"Recruiting plants to be my experimental subjects.\"",
                "\"The returns will be worth it. 100% guaranteed by my 10 years experience.\""
            ).buildCutscene(),
            getKeyLook(31, 13, "porbital_town")
        );

//* Flower garden
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "\"Do not touch the flowers or I wish you the worst of luck.\"",
                "...",
                "You felt like going inside and touching them, but will you take action on that?"
            ).buildCutscene(),
            getKeyLook(43, 11, "porbital_town")
        );

//* Cactus garden
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "\"Property of PROFESSOR DECANE. Do not touch.\""
            ).buildCutscene(),
            getKeyLook(35, 19, "porbital_town")
        );

//* Co working space mailbox
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "\"Do what you love, and you'll never work a day in your life.\"",
                "\"A course in how to live a good life for sale, only 9,999,999 CHEMS!\"",
                "Below, in smaller print:",
                "\"Disclaimer: This method is not compatible with alchemists, bureaucrats, and demons.\""
            ).buildCutscene(),
            getKeyLook(25, 21, "porbital_town")
        );

//* townhall sign
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "\"Office hours: 5 AM to 9 PM.\"",
                "\"Closes whenever we feel like it.\""
            ).buildCutscene(),
            getKeyLook(23, 21, "porbital_town")
        );

//* townhall mailbox
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "There's a single, pristine-looking letter inside...",
                "It's an invitation for the mayor to become a guest speaker at ALKENISTRA OPERA HOUSE",
                "The topic is \"The chiral history of the world\"."
            ).buildCutscene(),
            getKeyLook(17, 21, "porbital_town")
        );

//* mayor's flower garden sign
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "\"Mayor's Private Collection of Passively Aggressive Flora.\"",
                "\"Make eye contact with the flowers with caution.\""
            ).buildCutscene(),
            getKeyLook(15, 21, "porbital_town")
        );

//* Mountain warning sign
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "\"Warning: A lot of rocks exist here.\""
            ).buildCutscene(),
            getKeyLook(7, 15, "porbital_town"),
            getKeyLook(8, 15, "porbital_town")
        );

//* Overworld Item

        OverworldItemTemplate.addOverworldItem(
            cutscenes, 15, 4, "porbital_town",
            "Br2", playerDeckManager
        );

/*
* -----------------------------------------------------------------------------
* Location: Townhall
* -----------------------------------------------------------------------------
*/

        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "It's a certificate paper hanging on the wall.",
                "\"Certificate of Excellence in Locally Bound Politics\"",
                "\"Awarded to the mayor of Porbital Town for outstanding contributions to the local culture of flower farming.\"",
                "\"Signed by ALKENISTRA OPERA HOUSE manager, ARACINI\""
            ).buildCutscene(),
            getKeyLook(7, 2, "porbital_town__townhall")
        );

/*
* -----------------------------------------------------------------------------
* Location: Porbital Town Workspace
* -----------------------------------------------------------------------------
*/

//* Glass cabinet with old paper
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "It's a glass cabinet containing an old, yellowed piece of paper.",
                "The paper is so faded that you can barely make out any text.",
                "It somehow reminded you of the voting paper you use in an election."
            ).buildCutscene(),
            getKeyLocation(8, 5, "porbital_town__workspace", true, FacingDirections.UP),
            getKeyLocation(9, 5, "porbital_town__workspace", true, FacingDirections.UP)
        );

//* Bookshelf 1 - Alkanes
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak(new Dialogue(new String[] {
                "You pick up a book titled \"Alkanes: The Science of Branching and Bonding\"",
            }, "THINKING",
            new DialogueOption("Read the book", new Dialogue(new String[] {
                "You choose to read the book about alkanes."
            }, "THINKING"),
            ()->FlagManager.getInstance().addFlag("READ_ALKANES_BOOK")
            ),
            new DialogueOption("Put the book back", new Dialogue(new String[] {
                "You put the book back on the shelf."
            }))
            ))
            .condition("READ_ALKANES_BOOK", new CutsceneBuilder()
                .speak("THINKING",
                    "Haha! You thought you will get to learn science here?",
                    "It's all love stories here!",
                    "Alkanes only form single bonds. They're literally SIGMA of the century.",
                    "They never have two or three relationships with the same person at the same time.",
                    "But here's the thing... Since they have only \"Single bonds\", they are very flexible.",
                    "They're like:\nI have this one atom I'm dating, and I will only date it once.",
                    "But then, they're like:\nHehe... I'm flipping and turning myself 180 degrees because I can.",
                    "And then, the atom goes:\nYou're upside down now! You're the same and the same at the same time!",
                    "And that's why there's only 1 BUTANE as opposed to 2 BUT-2-ENE isomers.",
                    "The book ends with:\n\"Remember, to avoid awkward situations, flip yourself off.\""
                )
                .removeFlag("READ_ALKANES_BOOK")
                .buildActions()
            )
            .buildCutscene(),
            getKeyLook(1, 2, "porbital_town__workspace")
        );

//* Bookshelf 2 - Alkenes and Alkynes
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak(new Dialogue(new String[] {
                "You pick up a book titled \"Unending Unsaturation: Monopoly Race!\"",
            }, "THINKING",
            new DialogueOption("Read the book", new Dialogue(new String[] {
                "You choose to read the book about alkenes and alkynes."
            }, "THINKING"),
            ()->FlagManager.getInstance().addFlag("READ_ALKENES_ALKYNES_BOOK")
            ),
            new DialogueOption("Put the book back", new Dialogue(new String[] {
                "You put the book back on the shelf."
            }))
            ))
            .condition("READ_ALKENES_ALKYNES_BOOK", new CutsceneBuilder()
                .music("Gym")
                .speak("THINKING",
                    "WELCOME TO THE BONDING OLYMPICS!",
                    "Today's CRAZYYY event: Who is better at eating hydrogen???"
                )
                .shout("THINKING", "WOOOO!!!!", cameraManager)
                .speak( "THINKING",
                "In the red corner: Alkenes with their DOUBLE bonds!"
                )
                .shout("THINKING", "WOAAHHHH!", cameraManager)
                .speak( "THINKING",
                "In the blue corner: Alkynes with their TRIPLE bonds!"
                )
                .shout("THINKING", "WOOAAAAAHHHH!", cameraManager)
                .speak( "RED",
                    "Hoho! You're approaching me? Me with my slick 2 bonds with both CIS and TRANS isomers?",
                    "How would you beat me with your boring LINEAR structure?"
                )
                .speak( "BLUE",
                    "If I don't approach you, I can't beat you up, can I?",
                    "This isn't a fight for the most beautiful molecule, is it?",
                    "It's an eating contest!"
                )
                .wait(60)
                .shout("BLUE", "I'm going to eat you up!!! THROW HYDROGEN AT ME!!!", cameraManager)
                .wait(60)
                .shout("THINKING", "WOAAHHHH! YEAHHH!", cameraManager)
                .speak( "THINKING",
                    "The staff of this olympic threw a molecule of hydrogen gas at ALKYNE.",
                    "ALKYNE was like: \"I'm not going to give up that easily!!!\""
                )
                .wait(60)
                .shout("BLUE", "ONE OF MY TWO PI BOND, EAT THE HYDROGENNNN!!!!", cameraManager)
                .wait(60)
                .shout("THINKING", "OMG! YEAHHH! HE'S ABSORBING!!!", cameraManager)
                .wait(60)
                .speak("RED",
                    "Fufufufu... You thought you can eat 2 molecules of HYDROGEN GAS and you're going to win?",
                    "So naive...",
                    "Your mistake is that you're not creative enough, ALKYNE.",
                    "You always thought I am an ALKENE, and I will only have 1 DOUBLE BOND, don't you?"
                )
                .wait(45)
                .shout("RED", "HAHAHAHAHAHAHAHA!!!!", cameraManager)
                .wait(45)
                .shout("RED", "YOU NEVER THOUGHT I CAN HAVE 3 WHOLE DOUBLE BONDS, DO YOU?", cameraManager)
                .wait(120)
                .shout("BLUE", "N-NANI???", cameraManager)
                .wait(30)
                .waitEmote(player, cameraManager, 60)
                .speak("THINKING",
                    "You decided this book wasn't for you, and closed it."
                )
                .removeFlag("READ_ALKENES_ALKYNES_BOOK")
                .buildActions()
            )
            .buildCutscene(),
            getKeyLook(3, 2, "porbital_town__workspace")
        );

//* Bookshelf 3 - Halides
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak(new Dialogue(new String[] {
                "You pick up a book titled \"Halides News Paper\"",
            }, "THINKING",
            new DialogueOption("Read the book", new Dialogue(new String[] {
                "You choose to read the book about halides."
            }, "THINKING"),
            ()->FlagManager.getInstance().addFlag("READ_HALIDES_BOOK")
            ),
            new DialogueOption("Put the book back", new Dialogue(new String[] {
                "You put the book back on the shelf."
            }))
            ))
            .condition("READ_HALIDES_BOOK", new CutsceneBuilder()
                .music("Cave")
                .speak("THINKING",
                    "BREAKING NEWS: HALOGENS DECLARE WAR ON CARBON!",
                    "This morning, Fluorine, Chlorine, Bromine, and Iodine have formed an alliance.",
                    "The head of this alliance is Fluorine, and he is the most reactive halogen due to his small stature.",
                    "Their published mission statement is to attach themselves to innocent carbon atoms and cause chaos.",
                    "With their need for only 1 electron to be stable, they are the most reactive species in the entire animal kingdom."
                )
                .shout("THINKING", "IF YOU SEE A HALOGEN, RUN. JUST RUN. RUNNNNNN!!!", cameraManager)
                .wait(20)
                .sfx("BattleDamageSuper")
                .wait(15)
                .sfx("BattleDamageNormal")
                .wait(30)
                .sfx("BattleDamageSuper")
                .wait(30)
                .shout("THINKING", "ARGGHGHGHGHHHH!!!!", cameraManager)
                .wait(120)
                .shout("THINKING", "REPORTING FROM THE NEWS REPORTER HQ. OUR EMPLOYEE HAS BEEN KILLED BY HALOGENS.", cameraManager)
                .shout("THINKING", "I REGRET TO INFORM YOU THAT THIS NEWS PROGRAM IS TERMINATED! YOU HAVE TO---", cameraManager)
                .wait(60)
                .speak("THINKING",
                    "The book ends just like that"
                )
                .removeFlag("READ_HALIDES_BOOK")
                .buildActions()
            )
            .buildCutscene(),
            getKeyLook(5, 2, "porbital_town__workspace")
        );

//* Bookshelf 4 - Aromatics
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak(new Dialogue(new String[] {
                "You pick up a book titled \"Aromatica Magical Land Guide Book\"",
            }, "THINKING",
            new DialogueOption("Read the book", new Dialogue(new String[] {
                "You choose to read the book about aromatics."
            }, "THINKING"),
            ()->FlagManager.getInstance().addFlag("READ_AROMATICS_BOOK")
            ),
            new DialogueOption("Put the book back", new Dialogue(new String[] {
                "You put the book back on the shelf."
            }))
            ))
            .condition("READ_AROMATICS_BOOK", new CutsceneBuilder()
                .music("Bicycle")
                .speak("THINKING",
                    "This book is a guide to the magical land of Aromatica.",
                    "It's a land of magic and monarchy, where the air is filled with the scent of flowers."
                )
                .wait(60)
                .speak("THINKING",
                    "Chapter 1: Welcome to Aromatica",
                    "Aromatica is a communist area where electrons belong to everyone!",
                    "The locals call this law \"delocalization\" but tourists just call it \"confusing AF.\"",
                    "The center stage of this land is the city of \"THE SIX RINGS\"",
                    "It's where the monarch resides, and the citizens are all named after him.",
                    "The city itself is a perfect hexagon, benzene shaped city with a population being a multiple of 6.",
                    "Visitors are often confused by its layout, and the constant RESONANCE sound doesn't help with their orientation.",
                    "Local tip: Don't try to count the electrons. Your brain will explode."
                )
                .removeFlag("READ_AROMATICS_BOOK")
                .buildActions()
            )
            .buildCutscene(),
            getKeyLook(7, 2, "porbital_town__workspace")
        );

//* Bookshelf 5 - Alcohols and Ethers
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak(new Dialogue(new String[] {
                "You pick up a book titled \"To Drink or to Die: The Alcoholic's Guide to Survival\"",
            }, "THINKING",
            new DialogueOption("Read the book", new Dialogue(new String[] {
                "You choose to read the book about alcohols and ethers."
            }, "THINKING"),
            ()->FlagManager.getInstance().addFlag("READ_ALCOHOLS_ETHERS_BOOK")
            ),
            new DialogueOption("Put the book back", new Dialogue(new String[] {
                "You put the book back on the shelf."
            }))
            ))
            .condition("READ_ALCOHOLS_ETHERS_BOOK", new CutsceneBuilder()
                .speak("THINKING",
                    "SURVIVAL GUIDE: HOW TO NOT DIE FROM ALCOHOL",
                    "Chapter 1: The Basics of Not Dying", 
                    "#1: Don't drink methanol.",
                    "#2: Don't drink isopropanol.",
                    "#3: You can drink ethanol, but don't drink it too much.",
                    "#4: If it smells like chemicals, it's probably not meant for drinking.",
                    "#5: Don't drink and drive.",
                    "Chapter 2: Emergency Procedures",
                    "#1: If someone drinks methanol, give them ethanol immediately.",
                    "Actually, don't do that. Just call an ambulance.",
                    "#2: OH in alcohols are bad at leaving. Don't force it to leave the room or it will fight back.",
                    "#3: Oxygen connected to carbons in ethers doesn't care about you."
                )
                .removeFlag("READ_ALCOHOLS_ETHERS_BOOK")
                .buildActions()
            )
            .buildCutscene(),
            getKeyLook(10, 2, "porbital_town__workspace")
        );

//* Bookshelf 6 - Ketones and Aldehydes
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak(new Dialogue(new String[] {
                "You pick up a book titled \"Sugar Recipe: Ketone and Aldehyde Cookbook\"",
            }, "THINKING",
            new DialogueOption("Read the book", new Dialogue(new String[] {
                "You choose to read the book about carbonyl compounds."
            }, "THINKING"),
            ()->FlagManager.getInstance().addFlag("READ_CARBONYL_BOOK")
            ),
            new DialogueOption("Put the book back", new Dialogue(new String[] {
                "You put the book back on the shelf."
            }))
            ))
            .condition("READ_CARBONYL_BOOK", new CutsceneBuilder()
                .speak("THINKING",
                    "Classic Vanilla Caramel",
                    "Ingredients:",
                    "-1/3 cup glucose\n(aldehyde sugar with CHO at the end)",
                    "-1/2 cup fructose\n(ketone sugar with C=O on the second carbon)",
                    "-4 liters of water",
                    "-1 tsp signature vanilla extract from the Porbital Town",
                    "Instructions:",
                    "1. In a metal saucepan, combine sugars and water. The sugars will dissolve.",
                    "2. Cook under sunlight. Watch for the ants that are attracted to the sugar.",
                    "3. Continue cooking until mixture turns golden.",
                    "4. Add in vanilla. Serve warm over ice cream.",
                    "5. Give up because the customer didn't show up."
                )
                .removeFlag("READ_CARBONYL_BOOK")
                .buildActions()
            )
            .buildCutscene(),
            getKeyLook(12, 2, "porbital_town__workspace")
        );

//* Bookshelf 7 - Carboxylic Acids and Esters
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak(new Dialogue(new String[] {
                "You pick up a book titled \"Toxic Acidity: Formal Science Research\"",
            }, "THINKING",
            new DialogueOption("Read the book", new Dialogue(new String[] {
                "You choose to read the book about acids and esters."
            }, "THINKING"),
            ()->FlagManager.getInstance().addFlag("READ_ACIDS_ESTERS_BOOK")
            ),
            new DialogueOption("Put the book back", new Dialogue(new String[] {
                "You put the book back on the shelf."
            }))
            ))
            .condition("READ_ACIDS_ESTERS_BOOK", new CutsceneBuilder()
                .music("Lab")
                .speak("THINKING",
                    "TOXIC ACIDITY: A FORMAL SCIENTIFIC INVESTIGATION",
                    "ABSTRACT:",
                    "This study examines the relationship between molecular acidity and toxicity of carboxylic derivatives.",
                    "INTRODUCTION:",
                    "Carboxylic acids are evil and toxic.",
                    "They're always donating protons from their -COOH tail.",
                    "The more carboxylic acids, the more toxic my scientific paper is.",
                    "METHODOLOGY:",
                    "We throw various acids at the students and see if they die.",
                    "RESULTS:",
                    "The more acidic the compound, the more likely it was to cause damage.",
                    "Surprisingly, when we replace the O-H in carboxylic acids with O-R, they don't die.",
                    "Esters are not that toxic compared to carboxylic acids.",
                    "The students are depressed about me throwing esters at them though.",
                    "DISCUSSION:",
                    "I'll be in jail."
                )
                .removeFlag("READ_ACIDS_ESTERS_BOOK")
                .buildActions()
            )
            .buildCutscene(),
            getKeyLook(14, 2, "porbital_town__workspace")
        );

//* Bookshelf 8 - Amines and Amides
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak(new Dialogue(new String[] {
                "You pick up a book titled \"Nitrogen baby's first book\"",
            }, "THINKING",
            new DialogueOption("Read the book", new Dialogue(new String[] {
                "You choose to read the book about amines and amides."
            }, "THINKING"),
            ()->FlagManager.getInstance().addFlag("READ_AMINES_AMIDES_BOOK")
            ),
            new DialogueOption("Put the book back", new Dialogue(new String[] {
                "You put the book back on the shelf."
            }))
            ))
            .condition("READ_AMINES_AMIDES_BOOK", new CutsceneBuilder()
                .speak("THINKING",
                    "NITROGEN BABY'S FIRST BOOK",
                    "Welcome to the musical book for putting Nitrogens to sleep!"
                )
                .shout("THINKING", "Oh, amines, you make me blue.", cameraManager)
                .shout("THINKING", "With your two hydrogens and one nitrogen too.", cameraManager)
                .shout("THINKING", "You smell like fish, you smell like death.", cameraManager)
                .shout("THINKING", "But amino acids are consists of you.", cameraManager)
                .wait(60)
                .waitEmote(player, cameraManager, 60)
                .shout("THINKING", "Oh, amines, you fly in the sky.", cameraManager)
                .shout("THINKING", "With carbonyl and nitrogen, so high.", cameraManager)
                .shout("THINKING", "Stability from you gives us life.", cameraManager)
                .shout("THINKING", "...", cameraManager)
                .shout("THINKING", "I'm tired of this. This song is stupid. Bye bye.", cameraManager)
                .removeFlag("READ_AMINES_AMIDES_BOOK")
                .buildActions()
            )
            .buildCutscene(),
            getKeyLook(16, 2, "porbital_town__workspace")
        );

/*
* -----------------------------------------------------------------------------
* Location: Yuuki's House, Floor 2
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
            .speak( "THINKING", 
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
            .speak( "THINKING",
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
            .speak( "THINKING",
                "It's an empty trash can.",
                "You feel a strange sense of peace and comfort looking at it."
            ).buildCutscene(),
            getKeyLook(1, 3, "porbital_town__house1_f2")
        );

//* pc1
        addCutscene(cutscenes, new CutsceneBuilder() 
            .speak( "THINKING",
                "It's a computer.",
                "The desktop background is a picture of a multiple \"C\"s and \"H\"s connected to each other with lines.",
                "You try to log in, but the password isn't \"1234\"."
            ).buildCutscene(),
            getKeyLook(2, 3, "porbital_town__house1_f2")
        );

//* pc2
        addCutscene(cutscenes, new CutsceneBuilder() 
            .speak( "THINKING",
                "It's a computer.",
                "The desktop background is a bunch of zigzag lines with some more lines branching out.",
                "You try to log in, but the password isn't \"1234\"."
                ).buildCutscene(),
            getKeyLook(1, 5, "porbital_town__house1_f2")
        );

//* laptop
        addCutscene(cutscenes, new CutsceneBuilder() 
            .speak( "THINKING",
                "It's a laptop.",
                "The screen is open to a document titled \"How to Deal with Annoying Adults\".",
                "The last seach in the search engine is \"Can peroxides and sulfuric acid be used to dissolve a human body?\"",
                "You decide not to read any further."
                ).buildCutscene(),
            getKeyLook(6, 6, "porbital_town__house1_f2")
        );

//* tv
        addCutscene(cutscenes, new CutsceneBuilder() 
            .speak( "THINKING",
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
            .speak( "THINKING",
                "It's a book.",
                "The content is incomprehensible to you.",
                "Maybe this girl put all her points during character creation into intelligence and none in emotion control."
            ).buildCutscene(),
            getKeyLook(5, 7, "porbital_town__house1_f2")
        );

/*
*-----------------------------------------------------------------------------
* Location: Yuuki's House, Floor 1
* -----------------------------------------------------------------------------
*/

//* tv
        addCutscene(cutscenes, new CutsceneBuilder()
            .forbid("CHLOROPHYLL_2")
            .speak( "THINKING",
                "It's a TV.",
                "It's showing a documentary about the synthesis of\n\"(2R,3S,4R,5R)-2,3,4,5,6-Pentahydroxyhexanal\"",
                "You feel a sudden urge to change the channel."
            ).buildCutscene(),
            getKeyLook(12, 5, "porbital_town__house1_f1")
        );

        addCutscene(cutscenes, new CutsceneBuilder()
            .require("CHLOROPHYLL_2")
            .speak( "THINKING",
                "It's a TV.",
                "It's showing an anime...",
                "Half metal alchemist...?"
            ).buildCutscene(),
            getKeyLook(12, 5, "porbital_town__house1_f1")
        );

//* drawer
        addCutscene(cutscenes, new CutsceneBuilder()
            .require("YUUKI_1")
            .speak( "THINKING",
                "It's a drawer.",
                "You opened it to see if there's anything inside.",
                "There's an empty pudding cup. It seems she already ate it.",
                "You feel betrayed."
            ).buildCutscene(),
            getKeyLook(6, 2, "porbital_town__house1_f1")
        );

//* sink
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
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
            .speak( "THINKING",
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
            .speak( "THINKING",
                "It's a trash can.",
                "You look inside and find a crumpled piece of paper.",
                "It reads: \"Plan to take over the world.",
                "Step 1: Befriend the new person.\nStep 2: ...\"",
                "The rest is illegible."
            ).buildCutscene(),
            getKeyLook(12, 10, "porbital_town__house1_f1")
        );

/*
* -----------------------------------------------------------------------------
* Location: Kusari's House, Floor 1
* -----------------------------------------------------------------------------
*/

//* water sink
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "It's a water sink.",
                "The water is running.",
                "You tried turning it off, but it's stuck."
            ).buildCutscene(),
            getKeyLook(11, 5, "porbital_town__house2_f1"),
            getKeyLook(12, 5, "porbital_town__house2_f1")
        );

//* decorative tree
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "It's a decorative tree.",
                "It looks like a real tree, but it's made of plastic."
            ).buildCutscene(),
            getKeyLook(12, 9, "porbital_town__house2_f1")
        );

//* blue bookshelf
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "It's a bookshelf.",
                "The books are arranged in a reverse alphabetical order.",
                "You notice they're all chemistry textbooks.",
                "There's also a sticky note.",
                "\"Study schedule: 7 AM - 7 PM.\""
            ).buildCutscene(),
            getKeyLook(1, 2, "porbital_town__house2_f1")
        );

/*
* -----------------------------------------------------------------------------
* Location: Kusari's House, Floor 2
* -----------------------------------------------------------------------------
*/

//* high-tech electrical box
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "It's a high-tech electrical box.",
                "It's blinking and making a lot of noise.",
                "You have a feeling that if you touch it more, you'll be electrocuted.",
                "Electrocuted by a 10,000,000 Volt Thunderbolt."
            ).buildCutscene(),
            getKeyLook(1, 5, "porbital_town__house2_f2"),
            getKeyLook(2, 5, "porbital_town__house2_f2")
        );

//* Omurice
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "It's Omurice!",
                "A Japanese dish with rice wrapped in a thin omelette.",
                "You are hungry, so you eat it."
            )
            .wait(60)
            .speak( "THINKING",
                "You cannot physically eat it. The game won't allow you."
            )
            .buildCutscene(),
            getKeyLook(12, 8, "porbital_town__house2_f2")
        );

//* clipboard hanging on wall
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "It's a clipboard hanging on the wall.",
                "\"Daily Training Schedule\"",
                "\"6:00 AM - Wake up and say 'Good night, Professor!'\"",
                "\"6:05 AM - Take TETRAHYDROCANNABINOL medication\"",
                "\"6:10 AM - Practice organic chemistry\"",
                "\"7:00 PM - Sleep\""
            ).buildCutscene(),
            getKeyLook(11, 2, "porbital_town__house2_f2")
        );

//* huge machine
        addCutscene(cutscenes, new CutsceneBuilder()
            .speak( "THINKING",
                "It's a huge machine that might produce something.",
                "That something it's producing might be dangerous.",
                "Or maybe, it's just a money printer for fraud.",
                "You don't know."
            ).buildCutscene(),
            getKeyLook(2, 4, "porbital_town__house2_f2")
        );
    }
}
