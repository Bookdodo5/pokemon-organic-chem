package battle.reactions;

import battle.conditions.Light;

public class ReactionFactory {

    public static Reaction create(String name) {
        return switch (name) {
            // Battle 1 reactions
            case "Radical Halogenation" -> new Reaction.Builder()
                    .name("Radical Halogenation")
                    .cost(2)
                    .description("Adds a halogen to an alkane")
                    .requiredLight(Light.LIGHT) // Requires UV light
                    .animationPath("electric1")
                    .build();

                case "Ozonolysis" -> new Reaction.Builder()
                    .name("Ozonolysis")
                    .cost(0)
                    .description("Breaks alkenes using ozone to form aldehydes or ketones")
                    .animationPath("electric1")
                    .build();

            default -> throw new IllegalArgumentException("Invalid reaction name: " + name);
        };
    }
}
