package battle;

import java.util.Arrays;
import java.util.List;

public class BattleData {
	
	private final int id;
	private final List<String> battleSpecificCard;
	private final List<String> opponentDeck;
	private final String playerMolecule;
	private final String opponentMolecule;
	private final String targetMolecule;
	private final String battleTheme;

	private BattleData(int id, List<String> battleSpecificCard, List<String> opponentDeck, String playerMolecule, String opponentMolecule, String targetMolecule, String battleTheme) {
		this.id = id;
		this.battleSpecificCard = battleSpecificCard;
		this.opponentDeck = opponentDeck;
		this.playerMolecule = playerMolecule;
		this.opponentMolecule = opponentMolecule;
		this.targetMolecule = targetMolecule;
		this.battleTheme = battleTheme != null ? battleTheme : "indoor1";
	}

	public static class Builder {
		private int id;
		private List<String> battleSpecificCard;
		private List<String> opponentDeck;
		private String playerMolecule;
		private String opponentMolecule;
		private String targetMolecule;
		private BattleThemes battleTheme;

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder battleSpecificCard(String... battleSpecificCard) {
			this.battleSpecificCard = Arrays.asList(battleSpecificCard);
			return this;
		}

		public Builder opponentDeck(String... opponentDeck) {
			this.opponentDeck = Arrays.asList(opponentDeck);
			return this;
		}

		public Builder playerMolecule(String playerMolecule) {
			this.playerMolecule = playerMolecule;
			return this;
		}

		public Builder opponentMolecule(String opponentMolecule) {
			this.opponentMolecule = opponentMolecule;
			return this;
		}

		public Builder targetMolecule(String targetMolecule) {
			this.targetMolecule = targetMolecule;
			return this;
		}

		public Builder battleTheme(BattleThemes battleTheme) {
			this.battleTheme = battleTheme;
			return this;
		}

		public BattleData build() {
			return new BattleData(id, battleSpecificCard, opponentDeck, playerMolecule, opponentMolecule, targetMolecule, battleTheme.toString().toLowerCase());
		}
	}
	
	public List<String> getBattleSpecificCards() {
		return battleSpecificCard;
	}
	
	public List<String> getOpponentDeck() {
		return opponentDeck;
	}

	public String getPlayerMolecule() {
		return playerMolecule;
	}

	public String getOpponentMolecule() {
		return opponentMolecule;
	}

	public String getTargetMolecule() {
		return targetMolecule;
	}

	public String getBattleTheme() {
		return battleTheme;
	}

	public int getId() {
		return id;
	}
}
