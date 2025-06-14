package battle.battlephases.phases;

import assets.SoundManager;
import battle.Battle;
import battle.BattleConstants;
import battle.battlephases.BattlePhase;
import battle.battlephases.PhaseManager;
import battle.ui.BattleRenderer;
import gamestates.FlagManager;
import gamestates.GameStates;
import gamestates.StateManager;
import gamestates.TransitionManager;
import input.KeyBindingHandler;
import java.awt.*;

public class BattleWinPhase extends BattlePhase {

    private final Battle battle;
    private final BattleRenderer battleRenderer;
    private final StateManager stateManager;
    private final FlagManager flagManager;

    private final int initialMoleculeOffsetY = BattleConstants.MOLECULE_OFFSET_Y;
    private final int finalMoleculeOffsetY = BattleConstants.LOSING_MOLECULE_OFFSET_Y;

    private final int initialConditionOffsetY = BattleConstants.DEFAULT_CONDITION_OFFSET_Y;
    private final int initialLPOffsetY = BattleConstants.DEFAULT_LP_OFFSET_Y;
    private final int finalConditionOffsetY = BattleConstants.INITIAL_CONDITION_OFFSET_Y;
    private final int finalLPOffsetY = BattleConstants.INITIAL_LP_OFFSET_Y;

    private int animationTimer = 0;
    private final int totalDuration = BattleConstants.WIN_LOSE_ANIMATION_DURATION;

    public BattleWinPhase(PhaseManager phaseManager, KeyBindingHandler keyHandler, StateManager stateManager, FlagManager flagManager) {
        super(phaseManager, keyHandler);
        this.battle = phaseManager.getBattle();
        this.battleRenderer = new BattleRenderer(battle, battle.getBattleTheme());
        this.stateManager = stateManager;
        this.flagManager = flagManager;
    }

    @Override
    public void update() {
        animationTimer++;

        if(animationTimer < totalDuration / 3) {
            double factor = (double) animationTimer / (totalDuration / 3);
            double easedFactor = TransitionManager.easeInCubic(factor);
            int moleculeOffsetY = (int) (initialMoleculeOffsetY - (easedFactor * (initialMoleculeOffsetY - finalMoleculeOffsetY)));
            battleRenderer.setOpponentMoleculeOffsetY(moleculeOffsetY);
        }
        else if(animationTimer < totalDuration * 2 / 3) {
            double factor = (double) (animationTimer - totalDuration / 3) / (totalDuration / 3);
            double easedFactor = TransitionManager.easeInCubic(factor);
            int LPOffsetY = (int) (initialLPOffsetY - (easedFactor * (initialLPOffsetY - finalLPOffsetY)));
            battleRenderer.setLPOffsetY(LPOffsetY);
        }
        else if (animationTimer < totalDuration) {
            double factor = (double) (animationTimer - totalDuration * 2 / 3) / (totalDuration / 3);
            double easedFactor = TransitionManager.easeInCubic(factor);
            int conditionOffsetY = (int) (initialConditionOffsetY - (easedFactor * (initialConditionOffsetY - finalConditionOffsetY)));
            battleRenderer.setConditionOffsetY(conditionOffsetY);
        }

        if (animationTimer > totalDuration && phaseManager.getBattle().getEventManager().isFinished()) {
            stateManager.transitionToState(GameStates.CUTSCENE, false, "", () -> {
            });
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        battleRenderer.render(g2);
    }

    @Override
    public void keyTapped() {
    }

    @Override
    public void onEnter() {
        flagManager.addFlag("BATTLE_WIN");
        animationTimer = 0;
        SoundManager.getMusicplayer().play("BattleVictoryLeader");
        SoundManager.getSfxplayer().playSE("PkmnFaint");
    }

    @Override
    public void onExit() {
    }
}
