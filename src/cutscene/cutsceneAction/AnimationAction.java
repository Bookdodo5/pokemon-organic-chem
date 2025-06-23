package cutscene.cutsceneAction;

import assets.AnimationManager;
import cutscene.CutsceneAction;
import java.awt.Graphics2D;

public class AnimationAction implements CutsceneAction {

	private final AnimationManager animationManager;
	private final String animation;
	private final int x;
	private final int y;
	private final double scale;
	private final int animationSpeed;
	private boolean isFinished;
	private int animationCounter;

	public AnimationAction(String animation, int x, int y, double scale) {
		this.animationManager = new AnimationManager();
		this.animation = animation;
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.isFinished = false;
		this.animationCounter = 0;
		this.animationSpeed = 4;
	}

	@Override
	public void start() {
		animationManager.loadAnimation(animation);
	 }

	@Override
	public void update() {
		animationCounter++;
		if(animationCounter >= animationSpeed) {
			animationCounter = 0;
			animationManager.nextFrame();
		}
		if(animationManager.isFinished()) end();
	 }

	@Override	
	public void end() {
		isFinished = true;
	 }

	@Override
	public void draw(Graphics2D g2) {
		animationManager.draw(g2, x, y, scale);
	 }

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public void reset() {
		isFinished = false;
		animationCounter = 0;
	}

}
