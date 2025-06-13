package menu;

import java.awt.Graphics2D;

public abstract class Option {
    private String text;
    
    public Option(String text) {
        this.text = text;
    }
    
    public int getTextWidth(Graphics2D g2) {
		return g2.getFontMetrics().stringWidth(text);
	}
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public abstract void execute();
}