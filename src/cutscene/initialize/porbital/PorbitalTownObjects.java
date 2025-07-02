package cutscene.initialize.porbital;

import cutscene.Cutscene;
import cutscene.CutsceneBuilder;
import cutscene.template.CutsceneTemplate;
import java.util.List;
import java.util.Map;

public class PorbitalTownObjects extends CutsceneTemplate {
    public static void initialize(Map<String, List<Cutscene>> cutscenes) {


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
            .speak( "THINKING",
                "It's a TV.",
                "It's showing a documentary about the synthesis of\n\"(2R,3S,4R,5R)-2,3,4,5,6-Pentahydroxyhexanal\"",
                "You feel a sudden urge to change the channel."
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
