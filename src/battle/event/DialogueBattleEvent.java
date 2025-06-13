package battle.event;

import assets.SoundManager;
import dialogue.Dialogue;
import dialogue.DialogueRenderer;
import java.awt.Color;
import java.awt.Graphics2D;
import ui.BoxStyle;
import ui.TextStyle;

public class DialogueBattleEvent extends BattleEvent {
    
    private final DialogueRenderer dialogueRenderer;
    private final Dialogue dialogue;

    public DialogueBattleEvent(Dialogue dialogue) {
	this.dialogue = dialogue;
	this.dialogueRenderer = new DialogueRenderer();
    }
    
    public Dialogue getDialogue() {
	return dialogue;
    }
    
    public DialogueRenderer getDialogueRenderer() {
	return dialogueRenderer;
    }

    @Override
    public void start() {
	BoxStyle battleBoxStyle = BoxStyle.getBattleStyle()
        .cornerArc(5).shadowOffset(0).build();
	TextStyle battleTextStyle = TextStyle.getDialogueStyle()
		.textColor(new Color(240,245,250)).build();
	dialogueRenderer.setRenderingDialogue(dialogue, battleBoxStyle, battleTextStyle);
    }

    @Override
    public void update() {
	dialogueRenderer.update();
    }

    @Override
    public void end() {
        super.end();
        SoundManager.getSfxplayer().playSE("GameCursor");
    }

    @Override
    public void draw(Graphics2D g2) {
	    dialogueRenderer.renderDialogue(g2);
    }

}
