package battle.cards;

import battle.cards.condition.light.RainDanceCard;
import battle.cards.condition.light.SunnyDayCard;
import battle.cards.condition.light.WeatherReportCard;
import battle.cards.condition.pH.AmphotericAdaptationCard;
import battle.cards.condition.pH.BufferSolutionCard;
import battle.cards.condition.pH.ExtremificationCard;
import battle.cards.condition.pH.HydroxideWaveCard;
import battle.cards.condition.pH.LemonadeCard;
import battle.cards.condition.pH.ProtonStreamCard;
import battle.cards.condition.pH.SoapCard;
import battle.cards.condition.solvent.AquariumWaterCard;
import battle.cards.condition.solvent.GodlySolventCard;
import battle.cards.condition.solvent.LondonCityCard;
import battle.cards.condition.solvent.PolarBearCard;
import battle.cards.condition.temperature.CryogenicShockCard;
import battle.cards.condition.temperature.FlashHeatingCard;
import battle.cards.condition.temperature.GlobalWarmingCard;
import battle.cards.condition.temperature.IceCreamCard;
import battle.cards.condition.temperature.ThermalEquilibriumCard;
import battle.cards.reagent.ReagentCard;
import battle.cards.utility.*;

public class CardFactory {
	public static Card create(String card) {
		return switch (card) {
			// pH Modifier Cards
			case "Proton Stream" -> new ProtonStreamCard();
			case "Hydroxide Wave" -> new HydroxideWaveCard();
			case "Buffer Solution" -> new BufferSolutionCard();
			case "Amphoteric Adaptation" -> new AmphotericAdaptationCard();
			case "Lemonade" -> new LemonadeCard();
			case "Soap" -> new SoapCard();
			case "Extremification" -> new ExtremificationCard();
			
			// Temperature Modifier Cards
			case "Flash Heating" -> new FlashHeatingCard();
			case "Global Warming" -> new GlobalWarmingCard();
			case "Cryogenic Shock" -> new CryogenicShockCard();
			case "Ice Cream" -> new IceCreamCard();
			case "Thermal Equilibrium" -> new ThermalEquilibriumCard();
			
			// Light Modifier Cards
			case "Rain Dance" -> new RainDanceCard();
			case "Sunny Day" -> new SunnyDayCard();
			case "Weather Report" -> new WeatherReportCard();
			
			// Solvent Modifier Cards
			case "Aquarium Water" -> new AquariumWaterCard();
			case "Godly Solvent" -> new GodlySolventCard();
			case "Polar Bear" -> new PolarBearCard();
			case "London City" -> new LondonCityCard();
			
			// Utility Cards
			case "Bullet Time" -> new BulletTimeCard();
			case "Eye for an Eye" -> new EyeForAnEyeCard();
			case "Alpha" -> new AlphaCard();
			case "Beta" -> new BetaCard();
			case "Omega" -> new OmegaCard();
			case "Clock Rewind" -> new ClockRewindCard();
			case "Disulfide Bridge" -> new DisulfideBridgeCard();
			case "Fish" -> new FishCard();

			// Reagent Cards
			case "Cl2" -> new ReagentCard("Cl2", "A pale yellow-green gas.", 1);
			case "Br2" -> new ReagentCard("Br2", "A reddish-brown liquid.", 1);
			case "H2" -> new ReagentCard("H2", "The lightest element.", 1);
			case "Platinum" -> new ReagentCard("Platinum", "A precious metal catalyst.", 1);
			case "HBr" -> new ReagentCard("HBr", "A strong acid.", 1);
			case "HCl" -> new ReagentCard("HCl", "A strong acid.", 1);
			case "Hg(OAc)2" -> new ReagentCard("Hg(OAc)2", "A source of mercury.", 1);
			case "NaBH4" -> new ReagentCard("NaBH4", "A reducing agent.", 1);
			case "BH3" -> new ReagentCard("BH3", "A useful boron compound.", 1);
			case "THF" -> new ReagentCard("THF", "A common ether solvent.", 1);
			case "H2O2" -> new ReagentCard("H2O2", "A peroxide.", 1);
			case "NaOH" -> new ReagentCard("NaOH", "A strong base.", 1);
			case "H2O" -> new ReagentCard("H2O", "The universal solvent.", 1);
			case "KOtBu" -> new ReagentCard("KOtBu", "A bulky, strong base.", 1);
			case "NaOEt" -> new ReagentCard("NaOEt", "A strong base.", 1);
			case "EtOH" -> new ReagentCard("EtOH", "A common protic solvent.", 1);
			case "PBr3" -> new ReagentCard("PBr3", "A source of bromide.", 1);
			case "PCl3" -> new ReagentCard("PCl3", "A source of chloride.", 1);
			case "NaNH2" -> new ReagentCard("NaNH2", "A very strong base.", 1);
			case "Lindlar's Catalyst" -> new ReagentCard("Lindlar's Catalyst", "For selective hydrogenation.", 1);
			case "HgSO4" -> new ReagentCard("HgSO4", "A mercury-based catalyst.", 1);
			case "R2BH" -> new ReagentCard("R2BH", "A dialkylborane.", 1);
			case "EtBr" -> new ReagentCard("EtBr", "An ethyl halide.", 1);
			case "MeBr" -> new ReagentCard("MeBr", "A methyl halide.", 1);
			case "DMS" -> new ReagentCard("DMS", "Used in reductive workups.", 1);
			case "O3" -> new ReagentCard("O3", "An oxidizing agent.", 1);
			case "KMnO4" -> new ReagentCard("KMnO4", "A strong oxidizing agent.", 1);
			case "NBS" -> new ReagentCard("NBS", "A source of bromine radicals.", 1);
			case "mCPBA" -> new ReagentCard("mCPBA", "For epoxidation.", 1);
			case "EtCl" -> new ReagentCard("EtCl", "An ethyl halide.", 1);
			case "NaCN" -> new ReagentCard("NaCN", "A source of cyanide.", 1);
			case "NaSH" -> new ReagentCard("NaSH", "A source of hydrosulfide.", 1);
			case "PCC" -> new ReagentCard("PCC", "A mild oxidizing agent.", 1);
			case "CH3MgBr" -> new ReagentCard("CH3MgBr", "A Grignard reagent.", 1);
			case "Ph3P=CH2" -> new ReagentCard("Ph3P=CH2", "A Wittig reagent.", 1);
			case "Zn(Hg)" -> new ReagentCard("Zn(Hg)", "A zinc amalgam.", 1);
			case "SOCl2" -> new ReagentCard("SOCl2", "Used to make acid chlorides.", 1);
			case "MeNH2" -> new ReagentCard("MeNH2", "A simple amine.", 1);
			case "EtNH2" -> new ReagentCard("EtNH2", "A simple amine.", 1);
			case "Acetaldehyde" -> new ReagentCard("Acetaldehyde", "A common aldehyde.", 1);
			case "Acetone" -> new ReagentCard("Acetone", "A common ketone.", 1);
			case "MeI" -> new ReagentCard("MeI", "A methyl halide.", 1);
			case "Ag2O" -> new ReagentCard("Ag2O", "A mild oxidizing agent.", 1);
			case "NaN3" -> new ReagentCard("NaN3", "A source of azide.", 1);
			case "NaBH3CN" -> new ReagentCard("NaBH3CN", "A reducing agent.", 1);
			case "FeBr3" -> new ReagentCard("FeBr3", "A Lewis acid catalyst.", 1);
			case "AlCl3" -> new ReagentCard("AlCl3", "A Lewis acid catalyst.", 1);
			case "HNO3" -> new ReagentCard("HNO3", "A strong acid.", 1);
			case "SO3" -> new ReagentCard("SO3", "For sulfonation.", 1);
			case "Fuming H2SO4" -> new ReagentCard("Fuming H2SO4", "A very strong acid.", 1);
			case "AcCl" -> new ReagentCard("AcCl", "An acyl chloride.", 1);
			case "Fenton's reagent" -> new ReagentCard("Fenton's reagent", "A source of hydroxyl radicals.", 1);
			
			default -> throw new IllegalArgumentException("Invalid card: " + card);
		};
	}
}
