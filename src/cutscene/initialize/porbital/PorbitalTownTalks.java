package cutscene.initialize.porbital;

import cutscene.Cutscene;
import cutscene.CutsceneBuilder;
import cutscene.Emotes;
import cutscene.template.CutsceneTemplate;
import dialogue.Dialogue;
import dialogue.DialogueOption;
import entity.NPC;
import entity.NPCManager;
import entity.Player;
import gamestates.CameraManager;
import java.util.List;
import java.util.Map;

public class PorbitalTownTalks extends CutsceneTemplate {
    public static void initialize(Map<String, List<Cutscene>> cutscenes, NPCManager npcManager, CameraManager cameraManager, Player player) {
        NPC molecularGastronomist = npcManager.getNPC("MolecularGastronomist");
        NPC psychic = npcManager.getNPC("Psychic");
        NPC PorbitalCop1 = npcManager.getNPC("PorbitalCop1");
        NPC PorbitalCop2 = npcManager.getNPC("PorbitalCop2");
        NPC PorbitalCop3 = npcManager.getNPC("PorbitalCop3");
        NPC chlorophyll = npcManager.getNPC("Chlorophyll");
        NPC mayor = npcManager.getNPC("PorbitalMayor");

        staticTalks(cutscenes, molecularGastronomist, psychic, PorbitalCop1, PorbitalCop2, PorbitalCop3, chlorophyll, cameraManager, player);
        
        mayorTalk(cutscenes, mayor, cameraManager, player);
    }

    private static void mayorTalk(Map<String, List<Cutscene>> cutscenes, NPC mayor, CameraManager cameraManager, Player player) {
        addCutscene(cutscenes, new CutsceneBuilder()
            .faceTowards(mayor, player)
            .waitEmote(player, cameraManager, 60)
            .speak("PORBITAL MAYOR",
                "Ahem...",
                "Hello, citizen of the SIGMA GRASSLANDS.",
                "I'm the mayor of this town."
            )
            .react(mayor, cameraManager, Emotes.FRIENDLY)
            .setFlag("PORBITAL MAYOR_KNOW")
            .speak(new Dialogue(new String[] {
                "My job is to protect you from the dangers of the world.",
                "Especially politics. They're so dangerous, even I can't stand it.",
                "I really really can't stand it, to the point that I have to sit down.",
                "...",
                "...",
                "Haha...",
                "Can you please leave me here...?",
                "The ALKENISTRA OPERA HOUSE's manager might be coming at any moment.",
                "They invited me to make a speech for some reason.",
                "And as you probably know, the OPERA HOUSE's manager is very forceful.",
                "I need to pretend I'm dead.",
                "This telescope-looking thingy is my disguise. I'm hiding very well, aren't I?",
            }, "PORBITAL MAYOR",
            new DialogueOption("Why hide from them?", new Dialogue(new String[] {
                "I cannot make a speech about something I don't know about...",
                "I have basic human manners, you know?",
                "You're better off not doing anything than faking data and lying to the people."
            }, "PORBITAL MAYOR")),
            new DialogueOption("Hide? Tanned leather?", new Dialogue(new String[] {
                "...",
                "Yeah... That's a homo-",
                "No... It's not phobic... What is it?",
                "Homo Erectus...",
                "Homologous...",
                "Homogeneity",
                "It stuck at the tip of my tongue...",
                "...",
                "Ah!!!",
                "It's a homogamous words!"
            }, "PORBITAL MAYOR",
                new DialogueOption("Homophones...", new Dialogue(new String[] {
                    "Oh... I see...",
                    "I almost thought it was homobasidiomycete...",
                    "But that's a fungus, not a word.",
                    "Sorry, I'm not a linguist.",
                    "I'm just a mayor."
                }, "PORBITAL MAYOR"))
            )),
            new DialogueOption("Hi \"hiding very well\"! I'm dad!", new Dialogue(new String[] {
                "Hello, dad!",
                "I'm not sure what you're talking about.",
                "Can you please just leave if you're not going to make creative jokes?",
            }, "PORBITAL MAYOR")),
            new DialogueOption("Mayor-chan... Hai!", new Dialogue(new String[] {
                "Nani ga suki?",
                "Chokominto! Yori mo a~ na~ ta!",
                "Cringe... Cringe... Cringe...",
                "CRINGE!!!",
                "Can't you at least do something chuuni or cool? Not this idol thing...",
                "I am the alias of destruction incarnate, you can't defy the principles of all creations just like that!"
            }, "PORBITAL MAYOR")),
            new DialogueOption("Are you high?", new Dialogue(new String[] {
                "No, I'm not high.",
                "I'm low.",
                "Low in self-esteem, self-confidence, and self-worth.",
                "I'm also low-income, low-intelligence, and low-social skills.",
                "At least I'm not low-key terrible at making jokes."
            }, "PORBITAL MAYOR"))
            ))
            .buildCutscene(),
            getKeyNPC(mayor)
        );
    }

    private static void staticTalks(Map<String, List<Cutscene>> cutscenes, NPC molecularGastronomist, NPC psychic, NPC PorbitalCop1, NPC PorbitalCop2, NPC PorbitalCop3, NPC chlorophyll, CameraManager cameraManager, Player player) {

        //* CUTSCENE: Porbital Town - Molecular Gastronomist
        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag("MOLECULAR GASTRONOMIST_KNOW")
            .faceTowards(molecularGastronomist, player)
            .speak("MOLECULAR GASTRONOMIST",
                "Oh... Ah!",
                "Hello...? I was... just waiting.",
                "The shop owner... they're supposed to be here.",
                "They're advertising in ALKENISTRA that this place sells a high quality Vanillin extract...",
                "I need it for my... cooking...",
                "But... I don't know where they are.",
                "I... I'll just wait a bit longer."
            )
            .buildCutscene(), getKeyNPC(molecularGastronomist));

        //* CUTSCENE: Porbital Town - Psychic
        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag("PSYCHIC_KNOW")
            .faceTowards(psychic, player)
            .speak(new Dialogue( new String[] {
                "The spirits whisper to me... they say... 'Press SPACE to run'.",
                "Oh wait, that's just the programmer forgetting to find a place to put the keybind tutorials.",
                "I'm here to guard the holy TOWNHALL, the place where the mayor of this town lives.",
                "I'm not sure why this is my job instead of those useless cops, but at least I'm doing something.",
                "...",
                "...",
                "Please pay me 500 CHEMS to continue talking.",
                "..."
            },
            "PSYCHIC",
            new DialogueOption("I don't have any CHEMS.", new Dialogue( new String[] {
                "Then go away.\nYou can't take any more of my time."
            }, "PSYCHIC")),
            new DialogueOption("Did you mean POKE DOLLARS???", new Dialogue( new String[] {
                "I'm not sure what that is, but I'm sure it's not what I want.",
                "Go away. I'm not talking to poor people."
            }, "PSYCHIC")),
            new DialogueOption("What's the CHEMS?", new Dialogue( new String[] {
                "CHEMS are the currency of this world.",
                "You can use them to buy items from shops.",
                "I'm not sure why you're asking me this.",
                "Go away. I'm not talking to poor people."
            }, "PSYCHIC"))
            ))
            .buildCutscene(), getKeyNPC(psychic));
        
        //* CUTSCENE: Porbital Town - Porbital Cop 1
        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag("PORBITAL COP_KNOW")
            .faceTowards(PorbitalCop1, player)
            .speak("PORBITAL COP",
                "Hey there.",
                "Just doing my rounds.\nWe're supposed to walk along this path until the commander gets back.",
                "I need to keep this spot secure and no terrorists are allowed to pass through.",
                "Are you a terrorist?",
                "...",
                "...",
                "...",
                "I don't think you are. You're free to go."
            )
            .buildCutscene(), getKeyNPC(PorbitalCop1));

        //* CUTSCENE: Porbital Town - Porbital Cop 2
        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag("PORBITAL COP_KNOW")
            .faceTowards(PorbitalCop2, player)
            .speak("PORBITAL COP",
                "Hello, citizen of the SIGMA GRASSLANDS.",
                "I was sent here from a place far away called AROMATICA MAGICAL LAND.",
                "Of course, I don't know why I'm here.",
                "The pay's good, though.",
                "As long as I get paid, I can even guard a weak noble gas for all I care.",
                "...",
                "Oh! By the way, I used to be a magician.",
                "See this!"
            )
            .wait(30)
            .sfx("BattleDamageWeak")
            .animation("electric1", () -> PorbitalCop2.getMapX(), () -> PorbitalCop2.getMapY(), 1, cameraManager)
            .wait(20)
            .react(PorbitalCop2, cameraManager, Emotes.FRIENDLY)
            .wait(20)
            .speak("PORBITAL COP", "How was that?")
            .buildCutscene(), getKeyNPC(PorbitalCop2));

        //* CUTSCENE: Porbital Town - Porbital Cop 3 (Scammer)
        addCutscene(cutscenes, new CutsceneBuilder()
            .setFlag("DISGUISED COP_KNOW")
            .faceTowards(PorbitalCop3, player)
            .speak("DISGUISED COP",
                "No. I can't talk.",
                "I'm in the middle of a high-stakes surveillance operation.",
                "This 3x3 square is the most critical strategic point in all of SIGMA GRASSLANDS.",
                "I can't elaborate further. There's security protocols.",
                "Also, if I tell you, I'd be in trouble since I'm not a cop.",
                "Hope you understand."
            )
            .buildCutscene(), getKeyNPC(PorbitalCop3));

        //* CUTSCENE: Porbital Town - Chlorophyll Girl
        addCutscene(cutscenes, new CutsceneBuilder()
            .forbid("CHLOROPHYLL_WAIT_FOR_REMATCH")
            .forbid("CHLOROPHYLL_2")
            .setFlag("CHLOROPHYLL_KNOW")
            .faceTowards(chlorophyll, player)
            .speak("CHLOROPHYLL",
                "Hiiii! Isn't it a WONDERFUL day?! All the leaves are so...",
                "GREENNNNNN!!!!!!!",
                "You know why, right?!",
                "It's..."
            )
            .parallel(
                new CutsceneBuilder()
                    .emote(chlorophyll, 50, Emotes.MUSIC, cameraManager)
                    .sfx("GUIConfirm")
                    .speak("CHLOROPHYLL",
                        "CHLOROPHYLLLLL!",
                        "FUN FACT:\nThere's a magnesium ion at the center of the chlorophyll molecule!!!",
                        "The chlorophyll molecule ABSORBS specific light wavelengths, making leaves GREENNNNNN, and also capturing solar energy!!",
                        "How cool is THAT?!"
                    )
                    .buildActions()
            )
            .buildCutscene(), getKeyNPC(chlorophyll));
        
        addCutscene(cutscenes, new CutsceneBuilder()
            .forbid("CHLOROPHYLL_WAIT_FOR_REMATCH")
            .forbid("CHLOROPHYLL_2")
            .setFlag("CHLOROPHYLL_KNOW")
            .faceTowards(chlorophyll, player)
            .speak("CHLOROPHYLL",
                "Heyyyy!!! Have you ever bitten a tea leaf RAW?",
                "That characteristic nasty, bitter taste. You know what that is???",
                "That's...."
            )
            .parallel(
                new CutsceneBuilder()
                    .emote(chlorophyll, 50, Emotes.MUSIC, cameraManager)
                    .sfx("GUIConfirm")
                    .speak("CHLOROPHYLL",
                        "TANNINNNNNN!!!!!",
                        "FUN FACT:\nThese pesky tannins likes to HUG proteins and make them harder to digest!",
                        "So herbivores go BLEGHHHHH!!!",
                        "And then they leave the plant alone!",
                        "How's THAT????"
                    )
                    .buildActions()
            )
            .buildCutscene(), getKeyNPC(chlorophyll));

        addCutscene(cutscenes, new CutsceneBuilder()
            .forbid("CHLOROPHYLL_WAIT_FOR_REMATCH")
            .forbid("CHLOROPHYLL_2")
            .setFlag("CHLOROPHYLL_KNOW")
            .faceTowards(chlorophyll, player)
            .speak("CHLOROPHYLL",
                "Sniff sniff... Who farted...?",
                "No. Nobody farted.",
                "It's actually..."
            )
            .parallel(
                new CutsceneBuilder()
                    .emote(chlorophyll, 50, Emotes.MUSIC, cameraManager)
                    .sfx("GUIConfirm")
                    .speak("CHLOROPHYLL",
                        "ETHYLENEEEEE!!!!",
                        "It’s a plant hormone... but in GAS FORM. Like, FLOATY GAS BOING BOING BOING!!!",
                        "FUN FACT:\nEthylene controls fruit ripening!",
                        "One apple releases ethylene, and suddenly... the whole basket goes RIPE!!!", 
                        "MAGIC!!!!"
                    )
                    .buildActions()
            )
            .buildCutscene(), getKeyNPC(chlorophyll));
        
        addCutscene(cutscenes, new CutsceneBuilder()
            .forbid("CHLOROPHYLL_WAIT_FOR_REMATCH")
            .forbid("CHLOROPHYLL_2")
            .setFlag("CHLOROPHYLL_KNOW")
            .faceTowards(chlorophyll, player)
            .speak("CHLOROPHYLL",
                "HELLOOOOO!!!! Ever wonder why trees can be so tall... and not flop over like noodles???",
                "That’s because of..."
            )
            .parallel(
                new CutsceneBuilder()
                    .emote(chlorophyll, 50, Emotes.MUSIC, cameraManager)
                    .sfx("GUIConfirm")
                    .speak("CHLOROPHYLL",
                        "LIGNINNNNNN!!!!",
                        "It’s a tough organic polymer that fills the spaces between cellulose in cell walls!!!",
                        "FUN FACT:\nLignin makes trees HARD!!!",
                        "Literally, nobody in the ecosystem can break a tree's lignin!!!",
                        "(Well, apart from fungi and bacterias...)",
                        "BUT ISN'T THAT STRONG AND COOL???",
                        "Don't you want your boyfriend to be like LIGNIN???"
                    )
                    .buildActions()
            )
            .buildCutscene(), getKeyNPC(chlorophyll));  
    }

}
