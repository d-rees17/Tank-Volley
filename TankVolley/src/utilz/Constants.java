package utilz;

public class Constants {
	
	public static class Directions{
		public static final int LEFT = 0;
		public static final int RIGHT = 1;
		public static final int UP = 0;
		public static final int DOWN = 1;
	}

	public static class PlayerConstants{
		public static final int IDLE_L = 0;
		public static final int IDLE_R = 1;
		public static final int DRIVING_L = 2;
		public static final int DRIVING_R = 3;
		public static final int AIMING_L = 4;
		public static final int AIMING_R = 5;
		
		public static int GetSpriteAmount(int player_action) {
			
			switch(player_action) {
			
			case IDLE_L:
			case IDLE_R:
				return 2;
			case DRIVING_L: 
			case DRIVING_R: 
				return 8;
			case AIMING_L:
			case AIMING_R: 
				return 4;
			default:
				return 1;
			}
		}
	}
}