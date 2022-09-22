package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utilz.LoadSave;

public class Ball extends Entity{
	private BufferedImage ballImg;
	
	public Ball(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadBall();
	}
	
	public void loadBall() {
		this.ballImg = LoadSave.GetSpriteAtlas(LoadSave.BALL);
	}
	
	public void renderBall(Graphics g) {
		g.drawImage(ballImg,(int)x, (int)y, width, height, null);
	}
}
