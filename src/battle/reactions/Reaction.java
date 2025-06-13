package battle.reactions;

import assets.SoundManager;
import battle.Battle;
import battle.BattlePlayer;
import battle.LPApplicable;
import battle.battlephases.BattlePhases;
import battle.conditions.Condition;
import battle.conditions.ConditionBoard;
import battle.conditions.Light;
import battle.conditions.Solvent;
import battle.conditions.Temperature;
import battle.conditions.pH;
import battle.event.DialogueBattleEvent;
import battle.molecules.MoleculeFactory;
import dialogue.Dialogue;
import java.util.List;

public class Reaction extends LPApplicable {

    private final String name;
    private final Condition requiredCondition;
    private final String description;
    private final String animationPath;

    public Reaction(String name, int originalLp, Condition requiredCondition, String description, String animationPath) {
        super(originalLp);
        this.name = name;
        this.requiredCondition = requiredCondition;
        this.description = description;
        this.animationPath = animationPath;
    }

    public static class Builder {

        private String name;
        private int originalLp;
        private final Condition requiredCondition;
        private String description;
        private String animationPath;

        public Builder() {
            this.requiredCondition = new Condition(null, null, null, null);
            this.description = "";
            this.animationPath = "";
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder requiredpH(pH requiredpH) {
            this.requiredCondition.setpH(requiredpH);
            return this;
        }

        public Builder requiredTemp(Temperature requiredTemp) {
            this.requiredCondition.setTemp(requiredTemp);
            return this;
        }

        public Builder requiredSolvent(Solvent requiredSolvent) {
            this.requiredCondition.setSolvent(requiredSolvent);
            return this;
        }

        public Builder requiredLight(Light requiredLight) {
            this.requiredCondition.setLight(requiredLight);
            return this;
        }

        public Builder cost(int cost) {
            this.originalLp = cost;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder animationPath(String animationPath) {
            this.animationPath = animationPath;
            return this;
        }

        public Reaction build() {
            return new Reaction(name, originalLp, requiredCondition, description, animationPath);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAnimationPath() {
        return animationPath;
    }

    public Condition getRequiredCondition() {
        return requiredCondition;
    }

    public boolean canPlay(BattlePlayer player, ConditionBoard board, Battle battle) {
        int lpCost = getCurrentLP(player, board);
        int currentLP = player.getCurrentLP();
        boolean hasEnoughLP = currentLP >= lpCost;
        boolean isRightPhase = battle.getCurrentPhase() == BattlePhases.REACTION_PLAY;

        return hasEnoughLP && isRightPhase;
    }

    public void play(BattlePlayer player, ConditionBoard board) {
        player.spend(getCurrentLP(player, board));
        player.setSelectedReaction(this);
    }

    public void executeEffect(BattlePlayer player, ConditionBoard board, Battle battle) {
        
        if (!hasRequiredCondition(board)) {
            SoundManager.getSfxplayer().playSE("BattleDamageWeak");
            battle.getEventManager().insertEvent(new DialogueBattleEvent(
                new Dialogue(new String[]{"Reaction Failed: Wrong Condition"})
            ));
            return;
        }

        String molecule = player.getMolecule().getName();
        List<String> reagents = board.getEffectsInOrder();
        String product = ReactionRegistry.getProduct(name, molecule, reagents);

        if (product == null) {
            SoundManager.getSfxplayer().playSE("BattleDamageWeak");
            battle.getEventManager().insertEvent(new DialogueBattleEvent(
                new Dialogue(new String[]{"Reaction Failed: Missing Reagent"})
            ));
            return;
        }

        player.setMolecule(MoleculeFactory.create(product));
        SoundManager.getSfxplayer().playSE("BattleDamageSuper");
        battle.getEventManager().insertEvent(new DialogueBattleEvent(
            new Dialogue(new String[]{"Reaction Success!\nYour molecule has evolved into\n" + product.toUpperCase()})
        ));
    }

    public boolean hasRequiredCondition(ConditionBoard board) {
        Condition currentCondition = board.getCondition();
        
        boolean pHMatches = requiredCondition.getpH() == null || requiredCondition.getpH() == currentCondition.getpH();
        boolean tempMatches = requiredCondition.getTemp() == null || requiredCondition.getTemp() == currentCondition.getTemp();
        boolean solventMatches = requiredCondition.getSolvent() == null || requiredCondition.getSolvent() == currentCondition.getSolvent();
        boolean lightMatches = requiredCondition.getLight() == null || requiredCondition.getLight() == currentCondition.getLight();
        
        return pHMatches && tempMatches && solventMatches && lightMatches;
    }
}
