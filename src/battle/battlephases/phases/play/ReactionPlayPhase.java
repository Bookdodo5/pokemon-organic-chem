package battle.battlephases.phases.play;

import battle.Battle;   
import battle.BattlePlayer;
import battle.battlephases.PhaseManager;
import battle.cards.Card;
import battle.cards.CardFactory;
import battle.conditions.ConditionBoard;
import battle.reactions.Reaction;
import input.KeyBindingHandler;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReactionPlayPhase extends PlayPhase {

    public ReactionPlayPhase(PhaseManager phaseManager, KeyBindingHandler keyHandler) {
        super(phaseManager, keyHandler);
    }

    @Override
    public void onEnter() {
        ConditionBoard board = phaseManager.getBattle().getBoard();
        Battle battle = phaseManager.getBattle();
        BattlePlayer player = battle.getPlayer();
        BattlePlayer opponent = battle.getOpponent();
        
        if(board.hasEffect("Place Cl2")) {
            Card cl2Card = CardFactory.create("Cl2");
            cl2Card.executeEffect(player, opponent, board);
            board.removeEffect("Place Cl2");
        }
        else if(board.hasEffect("Place Br2")) {
            Card br2Card = CardFactory.create("Br2");
            br2Card.executeEffect(player, opponent, board);
            board.removeEffect("Place Br2");
        }
        opponentAI();
    }

    private void opponentAI() {
        Battle battle = phaseManager.getBattle();
        BattlePlayer opponent = battle.getOpponent();
    
        List<Integer> playableReagentIndices = IntStream.range(0, opponent.getHandSize())
            .filter(i -> opponent.canPlay(i)).boxed().collect(Collectors.toList());
            
        Random random = new Random();

        if (!playableReagentIndices.isEmpty() && random.nextBoolean()) {
            int randomIndex = random.nextInt(playableReagentIndices.size());
            int cardIndexToPlay = playableReagentIndices.get(randomIndex);
    
            Card cardToPlay = opponent.getDeck().getHand().get(cardIndexToPlay);
            cardToPlay.play(opponent, battle.getBoard(), cardIndexToPlay);
        }

        List<Reaction> playableReactions = opponent.getMolecule().getReactions().stream()
            .filter(r -> r.canPlay(opponent, battle.getBoard(), battle)).collect(Collectors.toList());
    
        if (!playableReactions.isEmpty() && random.nextBoolean()) {
            int randomIndex = random.nextInt(playableReactions.size());
            Reaction reactionToPlay = playableReactions.get(randomIndex);
            reactionToPlay.play(opponent, battle.getBoard());
        }
    }
}