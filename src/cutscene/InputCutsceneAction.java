package cutscene;

import input.KeyBindingHandler;
import input.Keys;

public interface InputCutsceneAction extends CutsceneAction {
    void keyTapped(KeyBindingHandler keyHandler);
    void keyPressed(KeyBindingHandler keyHandler);
    void keyReleased(Keys key);
}
