package battle.conditions;

public class Condition {

    private pH currentpH;
    private Temperature currentTemp;
    private Solvent currentSolvent;
    private Light currentLight;

    public Condition(pH pH, Temperature temp, Solvent solvent, Light light) {
        this.currentpH = pH;
        this.currentTemp = temp;
        this.currentSolvent = solvent;
        this.currentLight = light;
    }

    public pH getpH() {
	return currentpH;
    }

    public Temperature getTemp() {
	return currentTemp;
    }

    public Solvent getSolvent() {
	return currentSolvent;
    }

    public Light getLight() {
	return currentLight;
    }

    public void setpH(pH currentpH) {
	this.currentpH = currentpH;
    }

    public void setTemp(Temperature currentTemp) {
	this.currentTemp = currentTemp;
    }

    public void setSolvent(Solvent currentSolvent) {
	this.currentSolvent = currentSolvent;
    }

    public void setLight(Light currentLight) {
	this.currentLight = currentLight;
    }

    public void increaseTemp() {
        switch (currentTemp) {
            case COLD ->
                currentTemp = Temperature.ROOM;
            case ROOM ->
                currentTemp = Temperature.HOT;
            case HOT ->
                currentTemp = Temperature.HOT;
        }
    }

    public void decreaseTemp() {
        switch (currentTemp) {
            case COLD ->
                currentTemp = Temperature.COLD;
            case ROOM ->
                currentTemp = Temperature.COLD;
            case HOT ->
                currentTemp = Temperature.ROOM;
        }
    }

    public void increasepH() {
        switch (currentpH) {
            case STRONG_ACID ->
                currentpH = pH.WEAK_ACID;
            case WEAK_ACID ->
                currentpH = pH.NEUTRAL;
            case NEUTRAL ->
                currentpH = pH.WEAK_BASE;
            case WEAK_BASE ->
                currentpH = pH.STRONG_BASE;
            case STRONG_BASE ->
                currentpH = pH.STRONG_BASE;
        }
    }

    public void decreasepH() {
        switch (currentpH) {
            case STRONG_ACID ->
                currentpH = pH.STRONG_ACID;
            case WEAK_ACID ->
                currentpH = pH.STRONG_ACID;
            case NEUTRAL ->
                currentpH = pH.WEAK_ACID;
            case WEAK_BASE ->
                currentpH = pH.NEUTRAL;
            case STRONG_BASE ->
                currentpH = pH.WEAK_BASE;
        }
    }

    public void mixpH(pH newpH) {
        int newpHValue = getpHValue(newpH);
        int currentpHValue = getpHValue(currentpH);

        boolean hasNeutral = currentpH == pH.NEUTRAL || newpH == pH.NEUTRAL;
        boolean hasSameSign = Integer.signum(newpHValue) == Integer.signum(currentpHValue);
        boolean newIsStronger = Math.abs(newpHValue) > Math.abs(currentpHValue);

        if (newpH == currentpH) {
            return;
        }
        if (hasNeutral || !hasSameSign) {
            currentpH = getpHEnum(newpHValue + currentpHValue);
            return;
        }
        currentpH = newIsStronger ? newpH : currentpH;
    }

    public int getpHValue(pH pH) {
        return switch (pH) {
            case STRONG_ACID ->
                -2;
            case WEAK_ACID ->
                -1;
            case NEUTRAL ->
                0;
            case WEAK_BASE ->
                1;
            case STRONG_BASE ->
                2;
        };
    }

    public pH getpHEnum(int pHValue) {
        return switch (pHValue) {
            case -2 ->
                pH.STRONG_ACID;
            case -1 ->
                pH.WEAK_ACID;
            case 0 ->
                pH.NEUTRAL;
            case 1 ->
                pH.WEAK_BASE;
            case 2 ->
                pH.STRONG_BASE;
            default ->
                pH.NEUTRAL;
        };
    }
}
