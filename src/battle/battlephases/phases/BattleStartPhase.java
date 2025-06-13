package battle.battlephases.phases;

import battle.Battle;
import battle.BattleConstants;
import battle.battlephases.BattlePhase;
import battle.battlephases.PhaseManager;
import battle.ui.BattleRenderer;
import gamestates.TransitionManager;
import input.KeyBindingHandler;
import java.awt.Color;
import java.awt.Graphics2D;

public class BattleStartPhase extends BattlePhase {

    private final Battle battle;
    private final BattleRenderer battleRenderer;
    private final int FADE_DURATION = BattleConstants.FADE_DURATION;
    private final int ANIMATION_DURATION = BattleConstants.ANIMATION_DURATION;
    private int animationTimer = 0;

    private final int baseOffsetX = BattleConstants.DEFAULT_BASE_OFFSET_X;
    private final int dataBoxOffsetX = BattleConstants.DEFAULT_DATA_BOX_OFFSET_X;
    private final int conditionOffsetY = BattleConstants.DEFAULT_CONDITION_OFFSET_Y;
    private final int LPOffsetY = BattleConstants.DEFAULT_LP_OFFSET_Y;
    private final int initialBaseOffsetX = BattleConstants.INITIAL_BASE_OFFSET_X;
    private final int initialDataBoxOffsetX = BattleConstants.INITIAL_DATA_BOX_OFFSET_X;
    private final int initialConditionOffsetY = BattleConstants.INITIAL_CONDITION_OFFSET_Y;
    private final int initialLPOffsetY = BattleConstants.INITIAL_LP_OFFSET_Y;

    public BattleStartPhase(PhaseManager phaseManager, KeyBindingHandler keyHandler) {
        super(phaseManager, keyHandler);
        this.battle = phaseManager.getBattle();
        this.battleRenderer = new BattleRenderer(battle, battle.getBattleTheme());
        battleRenderer.setBaseOffsetX(initialBaseOffsetX);
        battleRenderer.setDataBoxOffsetX(initialDataBoxOffsetX);
        battleRenderer.setConditionOffsetY(initialConditionOffsetY);
        battleRenderer.setLPOffsetY(initialLPOffsetY);
    }

    @Override
    public void update() {
        animationTimer++;
        
        if(animationTimer < FADE_DURATION) return;
        
        int elementAnimationTimer = animationTimer - FADE_DURATION;
        
        if(elementAnimationTimer < ANIMATION_DURATION / 2) {
            double factor = (double) elementAnimationTimer / (ANIMATION_DURATION / 2);
            double easedFactor = TransitionManager.easeOutCubic(factor);
            int currentBaseOffsetX = (int) (initialBaseOffsetX - (easedFactor * (initialBaseOffsetX - baseOffsetX)));
            battleRenderer.setBaseOffsetX(currentBaseOffsetX);

            int currentConditionOffsetY = (int) (initialConditionOffsetY - (easedFactor * (initialConditionOffsetY - conditionOffsetY)));
            battleRenderer.setConditionOffsetY(currentConditionOffsetY);
        }
        else if(elementAnimationTimer < ANIMATION_DURATION) {
            double factor = (double) (elementAnimationTimer - ANIMATION_DURATION / 2) / (ANIMATION_DURATION / 2);
            double easedFactor = TransitionManager.easeOutCubic(factor);
            int currentDataBoxOffsetX = (int) (initialDataBoxOffsetX - (easedFactor * (initialDataBoxOffsetX - dataBoxOffsetX)));
            battleRenderer.setDataBoxOffsetX(currentDataBoxOffsetX);

            int currentLPOffsetY = (int) (initialLPOffsetY - (easedFactor * (initialLPOffsetY - LPOffsetY)));
            battleRenderer.setLPOffsetY(currentLPOffsetY);
        }
        else {
            phaseManager.nextPhase();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if(animationTimer < FADE_DURATION) {
            battleRenderer.render(g2);
            
            double fadeFactor = (double) (FADE_DURATION - animationTimer) / FADE_DURATION;
            
            int alpha = (int) (255 * fadeFactor);
            g2.setColor(new Color(0, 0, 0, alpha));
            g2.fillRect(0, 0, g2.getClipBounds().width, g2.getClipBounds().height);
        } else {
            battleRenderer.render(g2);
        }
    }

    @Override
    public void keyTapped() {
	
    }

    @Override
    public void onEnter() {
	
    }

    @Override
    public void onExit() {
	
    }

}
