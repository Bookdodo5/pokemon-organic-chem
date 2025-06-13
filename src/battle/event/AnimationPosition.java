package battle.event;

public enum AnimationPosition {
    PLAYER,OPPONENT,BOARD;

    public AnimationPosition getOpposite() {
        return this == PLAYER ? OPPONENT : this == OPPONENT ? PLAYER : BOARD;
    }
}
