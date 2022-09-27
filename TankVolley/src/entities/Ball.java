package entities;

import static utilz.HelpMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Ball extends Entity{
	private BufferedImage ballImg;
	private float ballSpeed = 0f;
	private float airSpeed = -5f;
	private float gravity = 0.03f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = true;
	private boolean left, right;
	private int[][] lvlData;
	
	
	public Ball(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadBall();
		initHitbox(x,y, 49 * Game.SCALE, 49 * Game.SCALE);
	}
	
	public void loadBall() {
		this.ballImg = LoadSave.GetSpriteAtlas(LoadSave.BALL);
	}
	
	public void renderBall(Graphics g) {
		g.drawImage(ballImg, (int)(hitbox.x), (int)(hitbox.y), width, height, null);
//		drawHitbox(g);
	}
	
	public void update() {
		updatePos();
	}
	
	public void updatePos() {
		float xSpeed = 0;
		if(isInAir(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
			if(left)
				xSpeed -= ballSpeed;
			else if(right)
				xSpeed += ballSpeed;
			
			if(CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			}else {
				if (RoofOrFloor(airSpeed))
					airSpeed = fallSpeedAfterCollision;
				else {
					GetEntityYPosNextToWall(hitbox, airSpeed);
					updateXPos(xSpeed);
				}
			}
		}else {
			updateXPos(xSpeed);
			resetInAir();
			return;
		}
	}
	
	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
	}
	
	private void updateXPos(float xSpeed) {
		if(CanMoveHere(hitbox.x+xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x+=xSpeed;
		}else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
		}
	}

	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
	}
	
}

