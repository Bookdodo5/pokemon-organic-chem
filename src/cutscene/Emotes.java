package cutscene;

public enum Emotes {
    SURPRISE(0), QUESTION(1), WAIT3(2), LOVE(4), MUSIC(5), SMILE(8), FRIENDLY(11), SAD(13), TERRIFIED(14), ANGRY(17), WAIT1(18), WAIT2(19);

    private final int value;

    Emotes(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
