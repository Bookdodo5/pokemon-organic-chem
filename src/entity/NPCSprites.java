package entity;

public enum NPCSprites {
    MAID("trainer_MAID"),
    COP("trainer_COP"),
    CHEF("trainer_CHEF"),
    SHOPKEEPER("trainer_SHOPKEEPER"),
    DIRECTOR("trainer_DIRECTOR"),
    ENGINEER("trainer_ENGINEER"),
    NURSE("trainer_NURSE"),
    AROMALADY("trainer_AROMALADY"),
    BLACKBELT("trainer_BLACKBELT"),
    CHANNELER("trainer_CHANNELER"),
    
    OLD_WOMAN_1("trainer_OLDWOMAN1"),
    OLD_WOMAN_2("trainer_OLDWOMAN2"),
    OLD_MAN("trainer_OLDMAN1"),
    OLD_MAN_2("trainer_OLDMAN2"),
    
    CAMPER("trainer_CAMPER"),
    TUBER_M("trainer_TUBER_M"),
    TUBER_F("trainer_TUBER_F"),
    SWIMMER_M("trainer_SWIMMER_M"),
    SWIMMER_F("trainer_SWIMMER_F"),
    GIRL_GREEN("trainer_GIRLGREEN"),
    SUPER_NERD("trainer_SUPERNERD"),
    PSYCHIC("trainer_PSYCHIC"),
    MANIAC("trainer_MANIAC"),
    COOL_M("trainer_COOL_M"),
    COOL_F("trainer_COOL_F"),
    BLUE_MAID("trainer_BLUEMAID"),
    MASKED("trainer_MASKED"),
    SUNGLASSES("trainer_SUNGLASSES"),
    FAT("trainer_FAT"),
    MAY("trainer_MAY"),
    
    RED("trainer_RED"),
    ORANGE("trainer_ORANGE"),
    YELLOW("trainer_YELLOW"),
    GREEN("trainer_GREEN"),
    BLUE("trainer_BLUE"),
    PURPLE("trainer_PURPLE"),
    MAGENTA("trainer_MAGENTA"),
    RED_RUN("trainer_RED_RUN");

    private final String spriteName;

    NPCSprites(String spriteName) {
        this.spriteName = spriteName;
    }

    public String getSpriteFileName() {
        return spriteName + ".png";
    }
}
