package entities;

import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.CanMoveHere;
import static utilz.LoadSave.PLAYER1_ATLAS;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity{
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 10;
	private int playerAction = IDLE_L;
	private int aimDir = -1;
	private int playerDir = LEFT;
	private boolean aimMoved = false;
	private boolean moving = false;
	private boolean aiming = false;
	private boolean left, right, up, down;
	private float playerSpeed = 2.0f;
	private int[][] lvlData;
	private float xDrawOffset = 35 * Game.SCALE;
	private float yDrawOffset = 47 * Game.SCALE;
	
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x,y, 79 * Game.SCALE, 51 * Game.SCALE);
	}
	
	public void update() {
		
		updatePos();
		updateAnimationTick();
		setAnimation();
	}
	
	public void render(Graphics g) {
		// 400 x 300, 40 frames, 8 columns, 6 rows
		g.drawImage(animations[playerAction][aniIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
//		drawHitbox(g);
	}
	
	
	public void setAimingDir(int aim) {
		this.aimDir = aim;
	}
	
	public void setAiming(boolean aiming) {
		this.aiming = aiming;
	}
	
	public void setAimMoved(boolean moveAim) {
		this.aimMoved = moveAim;
	}
	
		
	private void updateAnimationTick() {
		aniTick++;
		if(!aiming) {
			if(aniTick >= aniSpeed) {
				aniTick = 0;
				aniIndex++;
				if(aniIndex >= GetSpriteAmount(playerAction)) {
					aniIndex = 0;
				}
			}
		}
		else if(aiming && !moving) {
			if(aniTick >= aniSpeed) {
				aniTick = 0;
				if(aniIndex < GetSpriteAmount(playerAction)-1 && up && aimMoved == false) {
					aniIndex++;
					aimMoved = true;
				}
				else if (aniIndex > 0 && down && aimMoved == false) {
					aniIndex--;
					aimMoved = true;
				}	
			}
		}
	}
	
	private void setAnimation() {
		
		int startAni = playerAction;
		
		if(moving && playerDir == LEFT) {
			this.playerAction = DRIVING_L;
		}
		else if(moving && playerDir == RIGHT){
			this.playerAction = DRIVING_R;
		}
		else if(!moving && !aiming && playerDir == LEFT) {
			this.playerAction = IDLE_L;
		}
		else if(!moving && !aiming && playerDir == RIGHT) {
			this.playerAction = IDLE_R;
		}
		else if(!moving && playerDir == LEFT && aiming) {
			this.playerAction = AIMING_L;
		}
		else if(!moving && playerDir == RIGHT && aiming) {
			this.playerAction = AIMING_R;
		}
		
		if (startAni != playerAction) {
			resetAniTick();
		}
			
	}
	
	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		moving = false;
		if(!left && !right && !up && !down)
			return;
		float xSpeed = 0, ySpeed = 0;
		
		if (left && !right && !up && !down) {
			xSpeed = -playerSpeed;
			playerDir = LEFT;
		}else if(right && !left && !up && !down) {
			xSpeed = playerSpeed;
			playerDir = RIGHT;
		}else if (up && !right && !left && !down) {
			aiming = true;
			return;
		}
		else if (down && !right && !left && !up) {
			aiming = true;
			return;
		}
		if(CanMoveHere(hitbox.x+xSpeed, hitbox.y+ySpeed, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x+=xSpeed;
			hitbox.y+=ySpeed;
			moving = true;
		}
		
	}
	
	private void loadAnimations() {
			BufferedImage img = LoadSave.GetSpriteAtlas(PLAYER1_ATLAS);
			
			animations = new BufferedImage[6][8];
			
			for(int j = 0; j<animations.length;j++) {
				for(int i = 0; i<animations[j].length; i++) {
					animations[j][i] = img.getSubimage(i*150, j*150, 150, 150);
				}
			}
	}
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
	}
	
	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	
	
	
}
