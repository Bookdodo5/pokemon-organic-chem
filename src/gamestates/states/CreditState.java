package gamestates.states;

import assets.SoundManager;
import gamestates.GameState;
import gamestates.GameStates;
import gamestates.StateManager;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Color;
import java.awt.Graphics2D;
import static main.Constants.*;
import main.GameContentManager;
import ui.BoxRenderer;
import ui.BoxStyle;
import ui.TextRenderer;
import ui.TextRenderer.TextRenderResult;
import ui.TextStyle;

public class CreditState extends GameState {

    private final TextStyle titleTextStyle = TextStyle.getTitleStyle()
        .fontSize(32).build();
    private final TextStyle sectionTextStyle = TextStyle.getTitleMenuStyle()
        .fontSize(18).build();
    private final TextStyle creditTextStyle = TextStyle.getTitleMenuStyle()
        .fontSize(12)
        .textColor(Color.WHITE)
        .lineHeight(18)
        .textMarginY(25)
        .textMarginX(15)
        .build();
    private final BoxStyle menuBoxStyle = BoxStyle.getTitleMenuStyle().build();

    private int instructionCounter;

    public CreditState(StateManager stateManager, KeyBindingHandler keyHandler, GameContentManager gameContentManager) {
        super(stateManager, keyHandler, gameContentManager);
        instructionCounter = 0;
    }

    @Override
    public void update() {
        instructionCounter++;
        if (instructionCounter > 100) {
            instructionCounter = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        int scaledScreenWidth = (int) (SCREEN_WIDTH / SCALE);
        int scaledScreenHeight = (int) (SCREEN_HEIGHT / SCALE);

        BoxRenderer boxRenderer = new BoxRenderer(menuBoxStyle);
        boxRenderer.renderBoxCenter(g2, scaledScreenWidth - 20, scaledScreenHeight - 20);

        TextRenderer titleRenderer = new TextRenderer(titleTextStyle);
        String title = "Credits";
        int titleWidth = g2.getFontMetrics(titleTextStyle.getFont()).stringWidth(title);
        int titleX = (int) (scaledScreenWidth / 2) - titleWidth / 2;
        int titleY = (int) (scaledScreenHeight / 16);
        titleRenderer.renderLine(g2, titleX, titleY, title);

        int contentStartY = titleY + 80;
        int contentX1 = 40;
        int contentX2 = titleX + titleWidth/2;
        int maxWidth = (scaledScreenWidth - 80) / 2;

        TextRenderResult result1 = drawSection(g2, contentX1, contentStartY, maxWidth, "Development", """
            ARandomSquid
            For "Hack Club's Summer of Making"
            """);

        TextRenderResult result2 = drawSection(g2, contentX2, contentStartY, maxWidth, "Tools & Technology", """
            Programming Language - Java
            Molecules Graphics - RDKit
            Map Editor - Tiled
            """);

        int secondRowY = Math.max(result1.endY, result2.endY) + 30;

        drawSection(g2, contentX1, secondRowY, maxWidth, "Assets & Resources", """
            Pokemon FireRed
            Pokemon Essentials
            Pokemon Rejuvenation
            """);

        drawSection(g2, contentX2, secondRowY, maxWidth, "Inspirations", """
            Pocket Monsters (Pokémon)
            Slay the Spire
            @RyiSnow youtube channel
            """);

        String instruction = "Press ESC to return to title screen";
        TextRenderer instructionRenderer = new TextRenderer(creditTextStyle);

        int instructionWidth = g2.getFontMetrics(creditTextStyle.getFont()).stringWidth(instruction);
        int instructionX = (int) (scaledScreenWidth / 2) - instructionWidth / 2;
        int instructionY = scaledScreenHeight - 40 + (int)(Math.sin(instructionCounter/100.0 * Math.PI) * 3);
        instructionRenderer.renderLine(g2, instructionX, instructionY, instruction);
    }

    private TextRenderResult drawSection(Graphics2D g2, int x, int y, int maxWidth, String sectionTitle, String credits) {
        TextRenderer sectionRenderer = new TextRenderer(sectionTextStyle);
        TextRenderer creditRenderer = new TextRenderer(creditTextStyle);

        sectionRenderer.renderLine(g2, x, y, sectionTitle);
        TextRenderResult result = creditRenderer.renderText(g2, x, y, maxWidth, credits, credits);
        
        return result;
    }

    @Override
    public void onEnter(GameStates prevState) {
    }

    @Override
    public void keyPressed() {
    }

    @Override
    public void keyReleased(Keys key) {
    }

    @Override
    public void onExit(GameStates nextState) {
    }

    @Override
    public void keyTapped() {
        switch (keyHandler.getCurrentKey()) {
            case ESCAPE -> {
                stateManager.setState(GameStates.TITLE);
                SoundManager.getSfxplayer().playSE("GUIMenuClose");
            }
            default -> {}
        }
    }
}
