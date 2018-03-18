package al.artofsoul.ndihma;

import org.lwjgl.Sys;

public class Ora {
	
	private static boolean paused = false;
	private static long lastFrame;
    private static long totalTime;
	private static float d = 0;
    private static float multiplier = 1;

	private Ora() {
		throw new IllegalStateException("Utility class");
	}
	
	public static long merrKohen(){
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	
	public static float merrDelta(){
		long currentTime = merrKohen();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = merrKohen();
		if (delta * 0.001f > 0.05f)
			return 0.05f;
		return delta * 0.001f;
	}
	
	public static float delta(){
		if (paused)
			return 0;
		else
			return d * multiplier;
	}
	
	public static float totalTime(){
		return totalTime;
	}
	
	public static float multiplier(){
		return multiplier;
	}
	
	public static void update(){
		d = merrDelta();
		totalTime += d;
		
	}
	
	public static void changeMultiplier(float change){
		if (!(multiplier + change < -1 && multiplier + change >  7))
            multiplier += change;
	}
	
	public static void pause(){
		if (paused)
			paused = false;
		else
			paused = true;
	}

}
