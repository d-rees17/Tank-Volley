package inputs;

import java.awt.event.KeyEvent;
import static utilz.Constants.Directions.*;
import java.awt.event.KeyListener;
import main.GamePanel;

public class KeyboardInputs implements KeyListener{
	
	private GamePanel gamePanel;
	
	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
			gamePanel.getGame().getPlayer().setLeft(true);
			gamePanel.getGame().getPlayer().setAimingDir(LEFT);
			gamePanel.getGame().getPlayer().setAiming(false);
			break;
		case KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setRight(true);
			gamePanel.getGame().getPlayer().setAimingDir(RIGHT);
			gamePanel.getGame().getPlayer().setAiming(false);
			break;
		case KeyEvent.VK_W:
			gamePanel.getGame().getPlayer().setUp(true);
			gamePanel.getGame().getPlayer().setAimMoved(false);
			break;
		case KeyEvent.VK_S:
			gamePanel.getGame().getPlayer().setDown(true);
			gamePanel.getGame().getPlayer().setAimMoved(false);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
			gamePanel.getGame().getPlayer().setLeft(false);
			
			break;
		case KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setRight(false);
			
			break;
		case KeyEvent.VK_W:
			gamePanel.getGame().getPlayer().setUp(false);
			
			break;
		case KeyEvent.VK_S:
			gamePanel.getGame().getPlayer().setDown(false);
			break;
		}
		
	}
}
