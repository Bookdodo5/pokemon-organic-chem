package battle.battlephases;

public enum BattlePhases {
   BATTLE_START, CONDITION_PLAY, CONDITION_RESULT, REACTION_PLAY, REACTION_RESULT, BATTLE_WIN, BATTLE_LOSE;

   public BattlePhases next() {
      if(this == BATTLE_START) return CONDITION_PLAY;
      if(this == CONDITION_PLAY) return CONDITION_RESULT;
      if(this == CONDITION_RESULT) return REACTION_PLAY;
      if(this == REACTION_PLAY) return REACTION_RESULT;
      if(this == REACTION_RESULT) return CONDITION_PLAY;
      return BATTLE_START;
   }
}
