package battle.ui;

import assets.AssetManager;
import battle.Battle;
import battle.BattleConstants;
import battle.molecules.Molecule;
import dialogue.BoxDimensions;
import dialogue.DialogueRenderer;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static main.Constants.*;
import ui.BoxRenderer;
import ui.BoxStyle;
import ui.TextRenderer;
import ui.TextStyle;

public class BattleRenderer {

    private final Battle battle;
    private final BufferedImage backgroundImage;
    private final BufferedImage baseImage;
    private final BufferedImage dataBoxImage;

    private final TextStyle whiteTextStyle;
    private final TextStyle whiteTextStyleNoMargin;
    private final TextStyle blackTextStyle;
    private final TextStyle smallBlackTextStyle;
    private final BoxStyle battleBoxStyle;
    
    private int conditionOffsetY = BattleConstants.DEFAULT_CONDITION_OFFSET_Y;
    private int LPOffsetY = BattleConstants.DEFAULT_LP_OFFSET_Y;
    private int baseOffsetX = BattleConstants.DEFAULT_BASE_OFFSET_X;
    private int dataBoxOffsetX = BattleConstants.DEFAULT_DATA_BOX_OFFSET_X;

    private final int screenWidth = (int) (SCREEN_WIDTH / SCALE);
    private final int screenHeight = (int) (SCREEN_HEIGHT / SCALE);
    private final int baseWidth = BattleConstants.BASE_WIDTH;
    private final int baseHeight = BattleConstants.BASE_HEIGHT;
    private final int playerOpponentOffset = BattleConstants.PLAYER_OPPONENT_OFFSET;
    private final int dialogueMargin = BattleConstants.DIALOGUE_MARGIN;
    private final int dialogueHeight = BattleConstants.DIALOGUE_HEIGHT;
    private final int baseOffsetY = BattleConstants.BASE_OFFSET_Y;
    private final int dataBoxOffsetY = BattleConstants.DATA_BOX_OFFSET_Y;
    private final int playerY = screenHeight - dialogueHeight - baseHeight - dialogueMargin + baseOffsetY;
    private final int opponentY = playerY - playerOpponentOffset;

    private int playerX = dialogueMargin - baseOffsetX;
    private int opponentX = screenWidth - dialogueMargin - baseWidth + baseOffsetX;

    private final int moleculeOffsetX = BattleConstants.MOLECULE_OFFSET_X;
    private int opponentMoleculeOffsetY = BattleConstants.MOLECULE_OFFSET_Y;
    private int playerMoleculeOffsetY = BattleConstants.MOLECULE_OFFSET_Y;

    private int animationCounter = 0;
    private final int animationFullCounter = BattleConstants.ANIMATION_FULL_COUNTER;

    public BattleRenderer(Battle battle, String battleTheme) {
        this.battle = battle;

        this.backgroundImage = AssetManager.loadImage("/battle/base/" + battleTheme + "_bg.png");
        this.baseImage = AssetManager.loadImage("/battle/base/" + battleTheme + "_base1.png");
        this.dataBoxImage = AssetManager.loadImage("/battle/databox.png");

        this.whiteTextStyle = TextStyle.getDialogueStyle()
                .fontSize(18, "powerred")
                .textColor(new Color(240, 245, 250))
                .build();
        this.whiteTextStyleNoMargin = TextStyle.getDialogueStyle()
                .fontSize(18, "powerred")
                .textColor(new Color(245, 250, 255))
                .shadowColor(new Color(80, 85, 90, 200))
                .textMarginX(0)
                .textMarginY(0)
                .build();
        this.blackTextStyle = TextStyle.getDialogueStyle()
                .fontSize(18, "powerred")
                .build();
        this.smallBlackTextStyle = TextStyle.getDialogueStyle()
                .fontSize(14, "powerred")
                .build();

        this.battleBoxStyle = BoxStyle.getBattleStyle()
            .cornerArc(5).shadowOffset(0).build();
    }

    public void setDataBoxOffsetX(int dataBoxOffsetX) {
        this.dataBoxOffsetX = dataBoxOffsetX;
    }

    public void setBaseOffsetX(int baseOffsetX) {
        this.baseOffsetX = baseOffsetX;
        this.playerX = dialogueMargin - baseOffsetX;
        this.opponentX = screenWidth - dialogueMargin - baseWidth + baseOffsetX;
    }

    public void setLPOffsetY(int LPOffsetY) {
        this.LPOffsetY = LPOffsetY;
    }

    public void setConditionOffsetY(int conditionOffsetY) {
        this.conditionOffsetY = conditionOffsetY;
    }

    public void setOpponentMoleculeOffsetY(int moleculeOffsetY) {
        this.opponentMoleculeOffsetY = moleculeOffsetY;
    }

    public void setPlayerMoleculeOffsetY(int moleculeOffsetY) {
        this.playerMoleculeOffsetY = moleculeOffsetY;
    }

    public void render(Graphics2D g2) {

        animationCounter++;
        if (animationCounter >= animationFullCounter) {
            animationCounter = 0;
        }

        drawBackground(g2);
        drawPlayerAreas(g2);
        drawMolecule(g2);
        drawMoleculeData(g2);
        drawLP(g2);
        drawConditionArea(g2);
        drawBlankDialogue(g2);
    }

    private void drawBackground(Graphics2D g2) {
        g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, null);
    }

    private void drawPlayerAreas(Graphics2D g2) {
        g2.drawImage(baseImage, playerX, playerY, baseWidth, baseHeight, null);
        g2.drawImage(baseImage, opponentX, opponentY, baseWidth, baseHeight, null);
    }

    private void drawMolecule(Graphics2D g2) {
        int playerMoleculeX = playerX + moleculeOffsetX + baseWidth / 2;
        int playerMoleculeY = playerY + playerMoleculeOffsetY + baseHeight / 2;
        int opponentMoleculeX = opponentX + moleculeOffsetX + baseWidth / 2;
        int opponentMoleculeY = opponentY + opponentMoleculeOffsetY + baseHeight / 2;

        if (animationCounter < animationFullCounter / 2) {
            playerMoleculeY += BattleConstants.MOLECULE_ANIMATION_BOUNCE;
        } else {
            opponentMoleculeY += BattleConstants.MOLECULE_ANIMATION_BOUNCE;
        }

        java.awt.Shape originalClip = g2.getClip();
        int playerMoleculeMaxY = playerY + baseHeight/2 + BattleConstants.MOLECULE_OFFSET_Y;
        int opponentMoleculeMaxY = opponentY + baseHeight/2 + BattleConstants.MOLECULE_OFFSET_Y;
        
        if (playerMoleculeOffsetY > BattleConstants.MOLECULE_OFFSET_Y) {
            g2.setClip(0, 0, screenWidth, playerMoleculeMaxY);
        }
        battle.getPlayer().getMolecule().draw(g2, playerMoleculeX, playerMoleculeY);
        g2.setClip(originalClip);
        
        if (opponentMoleculeOffsetY > BattleConstants.MOLECULE_OFFSET_Y) {
            g2.setClip(0, 0, screenWidth, opponentMoleculeMaxY);
        }
        battle.getOpponent().getMolecule().draw(g2, opponentMoleculeX, opponentMoleculeY);
        g2.setClip(originalClip);
    }

    private void drawMoleculeData(Graphics2D g2) {
        int dataBoxWidth = dataBoxImage.getWidth();
        int dataBoxHeight = dataBoxImage.getHeight();
        g2.drawImage(dataBoxImage, screenWidth - dataBoxOffsetX, playerY - dataBoxOffsetY, -dataBoxWidth, dataBoxHeight, null);
        g2.drawImage(dataBoxImage, dataBoxOffsetX, opponentY - dataBoxOffsetY, dataBoxWidth, dataBoxHeight, null);

        int textOffsetX = BattleConstants.DATA_BOX_TEXT_OFFSET_X;
        int textOffsetY = BattleConstants.DATA_BOX_TEXT_OFFSET_Y;
        int playerTextX = screenWidth - dataBoxWidth + BattleConstants.DATA_BOX_PLAYER_NAME_OFFSET_X - dataBoxOffsetX;
        int playerTextY = playerY + textOffsetY;
        int opponentTextX = textOffsetX + dataBoxOffsetX;
        int opponentTextY = opponentY + textOffsetY;
        TextRenderer textRenderer = new TextRenderer(blackTextStyle);
        Molecule opponentMolecule = battle.getOpponent().getMolecule();
        Molecule playerMolecule = battle.getPlayer().getMolecule();
        textRenderer.renderLine(g2, opponentTextX, opponentTextY, opponentMolecule.getName());
        textRenderer.renderLine(g2, playerTextX, playerTextY, playerMolecule.getName());

        textRenderer = new TextRenderer(smallBlackTextStyle);
        int playerFormulaWidth = textRenderer.getTextWidth(g2, playerMolecule.getFormula());
        int opponentFormulaWidth = textRenderer.getTextWidth(g2, opponentMolecule.getFormula());
        int playerFormulaX = playerTextX + (BattleConstants.DATA_BOX_FORMULA_WIDTH - playerFormulaWidth) / 2 - dataBoxOffsetX;
        int opponentFormulaX = opponentTextX + (BattleConstants.DATA_BOX_FORMULA_WIDTH - opponentFormulaWidth) / 2 + dataBoxOffsetX;
        int playerFormulaY = playerY + BattleConstants.DATA_BOX_FORMULA_Y_OFFSET;
        int opponentFormulaY = opponentY + BattleConstants.DATA_BOX_FORMULA_Y_OFFSET;
        textRenderer.renderLine(g2, playerFormulaX, playerFormulaY, playerMolecule.getFormula());
        textRenderer.renderLine(g2, opponentFormulaX, opponentFormulaY, opponentMolecule.getFormula());

        String playerMW = "MW: " + String.valueOf(playerMolecule.getMolecularWeight());
        String opponentMW = "MW: " + String.valueOf(opponentMolecule.getMolecularWeight());
        int playerMWWidth = textRenderer.getTextWidth(g2, playerMW);
        int opponentMWWidth = textRenderer.getTextWidth(g2, opponentMW);
        int playerMWX = playerTextX + BattleConstants.DATA_BOX_MW_OFFSET_X + (BattleConstants.DATA_BOX_FORMULA_WIDTH - playerMWWidth) / 2 - dataBoxOffsetX;
        int opponentMWX = opponentTextX + BattleConstants.DATA_BOX_MW_OFFSET_X + (BattleConstants.DATA_BOX_FORMULA_WIDTH - opponentMWWidth) / 2 + dataBoxOffsetX;

        textRenderer.renderLine(g2, playerMWX, playerFormulaY, playerMW);
        textRenderer.renderLine(g2, opponentMWX, opponentFormulaY, opponentMW);
    }

    private void drawConditionArea(Graphics2D g2) {
        int conditionHeight = BattleConstants.CONDITION_HEIGHT;
        int sectionWidth = screenWidth / BattleConstants.CONDITION_SECTIONS;

        var condition = battle.getBoard().getCondition();
        String pHValue = condition.getpH().toString();
        String tempValue = condition.getTemp().toString();
        String solventValue = condition.getSolvent().toString();
        String lightValue = condition.getLight().toString();

        Color pHColor = condition.getpH().getColor();
        Color tempColor = condition.getTemp().getColor();
        Color solventColor = condition.getSolvent().getColor();
        Color lightColor = condition.getLight().getColor();

        BoxStyle conditionStyle = BoxStyle.getBattleStyle()
                .cornerArc(0).shadowColor(new Color(0,0,0,0)).build();

        BoxRenderer boxRenderer = new BoxRenderer(conditionStyle);
        boxRenderer.renderBox(g2, 0, conditionOffsetY, screenWidth, conditionHeight);

        drawConditionSection(g2, 0 * sectionWidth, sectionWidth, conditionHeight, pHColor, pHValue);
        drawConditionSection(g2, 1 * sectionWidth, sectionWidth, conditionHeight, tempColor, tempValue);
        drawConditionSection(g2, 2 * sectionWidth, sectionWidth, conditionHeight, solventColor, solventValue);
        drawConditionSection(g2, 3 * sectionWidth, sectionWidth, conditionHeight, lightColor, lightValue);
    }

    private void drawConditionSection(Graphics2D g2, int x, int width, int height, Color bgColor, String value) {

        TextRenderer textRenderer = new TextRenderer(whiteTextStyleNoMargin);
        String displayText = value.replace("_", " ");

        int textWidth = textRenderer.getTextWidth(g2, displayText);
        int textHeight = textRenderer.getTextHeight(g2) * 2;
        int centeredX = x + (width - textWidth) / 2;
        int centeredY = height / 2 - textHeight / 2 + conditionOffsetY;

        Color darkColor = bgColor.darker();
        Color lightColor = bgColor.brighter().darker();

        GradientPaint gradient = new GradientPaint(
            centeredX, centeredY - 3, bgColor, 
            centeredX, centeredY + textHeight + 20, darkColor);

        g2.setPaint(gradient);
        g2.fillRoundRect(centeredX - 6, centeredY - 3, textWidth + 12, textHeight + 6, 5, 5);

        g2.setColor(lightColor.brighter());
        g2.drawRoundRect(centeredX - 6, centeredY - 3, textWidth + 12, textHeight + 6, 5, 5);
        textRenderer.renderLine(g2, centeredX, centeredY-2, displayText);
    }

    private void drawLP(Graphics2D g2) {
        String LPString = battle.getPlayer().getCurrentLP() + " / " + battle.getPlayer().getRefillLP() + " LP";
        TextRenderer textRenderer = new TextRenderer(whiteTextStyle);
        int LPWidth = textRenderer.getTextWidth(g2, LPString);
        int LPHeight = textRenderer.getTextHeight(g2) * 2;
        int LPX = screenWidth / 2 - LPWidth / 2;
        int LPY = LPOffsetY + 45;
        BoxRenderer boxRenderer = new BoxRenderer(BoxStyle.getBattleStyle()
            .cornerArc(5).shadowColor(new Color(0,0,0,0))
            .topBorderColor(battleBoxStyle.getBottomBorderColor()).build());

        boxRenderer.renderBox(g2, LPX - 8, LPY - 8, LPWidth + 16, LPHeight + 16);
        textRenderer.renderLine(g2, LPX, LPY - 2, LPString);
    }

    private void drawBlankDialogue(Graphics2D g2) {
        int rectHeight = dialogueHeight + 2 * dialogueMargin;
        g2.setColor(new Color(50, 50, 50));
        g2.fillRect(0, screenHeight - rectHeight, screenWidth, rectHeight);

        DialogueRenderer dialogueRenderer = new DialogueRenderer();
        BoxDimensions dims = dialogueRenderer.calculateDimensions(battleBoxStyle, whiteTextStyle);

        dialogueRenderer.drawBox(g2, dims, battleBoxStyle);
    }
}
