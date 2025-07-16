package battle.battlephases.phases.play;

import battle.Battle;
import battle.BattlePlayer;
import battle.battlephases.PhaseManager;
import battle.cards.Card;
import input.KeyBindingHandler;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConditionPlayPhase extends PlayPhase {

    public ConditionPlayPhase(PhaseManager phaseManager, KeyBindingHandler keyHandler) {
	    super(phaseManager, keyHandler);
    }

    @Override
    public void onEnter() {
        resetPlayers();
        phaseManager.getBattle().getBoard().triggerTurn();
        phaseManager.getBattle().nextTurn();
        opponentAI();
    }

    private void opponentAI() {
        Battle battle = phaseManager.getBattle();
        BattlePlayer opponent = battle.getOpponent();

        List<Integer> playableCardIndices = IntStream.range(0, opponent.getHandSize())
                .filter(i -> opponent.getDeck().getHand().get(i).canPlay(opponent, battle.getBoard(), battle))
                .boxed()
                .collect(Collectors.toList());

        if (!playableCardIndices.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(playableCardIndices.size());
            int cardIndexToPlay = playableCardIndices.get(randomIndex);
            
            Card cardToPlay = opponent.getDeck().getHand().get(cardIndexToPlay);
            cardToPlay.play(opponent, battle.getBoard(), cardIndexToPlay);
        }
    }

    private void resetPlayers() {
        phaseManager.getBattle().getPlayer().newTurn();
        phaseManager.getBattle().getOpponent().newTurn();
    }
}
