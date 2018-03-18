package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.*;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import al.artofsoul.ndihma.Ora;


public class Lojtari {
	
	private PllakaFusha grid;
	private PllakaType[] types;
	private ValaManager valaManager;
	private ArrayList<Tower> towerList;
	private boolean leftMouseButtonDown;
    private boolean rightMouseButtonDown;
    private boolean holdingTower;
	private Tower tempTower;
	private static int gold;
    private static int lives;
	
	public Lojtari(PllakaFusha grid, ValaManager valaManager ){
		this.grid = grid;
		this.types = new PllakaType[3];
		this.types[0] = PllakaType.GRASS;
		this.types[1] = PllakaType.DIRT;
		this.types[2] = PllakaType.WATER;
		this.valaManager = valaManager;
		this.towerList = new ArrayList<>();
		this.leftMouseButtonDown = false;
		this.rightMouseButtonDown = false;
		this.holdingTower = false;
		this.tempTower = null;
		setGold(0);
		setLives(0);
		
	}
	// Initialize gold and lives values for player
	public void setup() {
		setGold(200);
		setLives(10);
	}
	// check if player can afford tower, if so: change player tower cost
	public static boolean modifyGold(int amount) {
		if (getGold() + amount >= 0) {
			setGold(getGold() + amount);
			System.out.println(getGold());
			return true;
		}
		System.out.println("Glod");
		return false;
	}

	private static void setGold(int amount) {
	    gold = amount;
    }

    public static int getGold() {
        return gold;
    }
	
	public static void modifyLives(int amount) {
		lives += amount;
	}

    private static void setLives(int amount) {
        lives = amount;
    }

    public static int getLives() {
        return lives;
    }

	public void update(){
		// update holding tower
		if (holdingTower){
			tempTower.setX(getMouseTile().getX());
			tempTower.setY(getMouseTile().getY());
			tempTower.draw();
		}
		
		// update all towers in the game
		for(Tower t : towerList){
			t.update();
			t.draw();
			t.updateEnemyLists(valaManager.getCurrentWave().getArmikuList());
		}
		
		// Handle Mouse Input
		if (Mouse.isButtonDown(0) && !leftMouseButtonDown){
			placeTower();
		}
		if (Mouse.isButtonDown(1) && !rightMouseButtonDown){
			System.out.println("Use other Clicked");
		}
		
		leftMouseButtonDown = Mouse.isButtonDown(0);
		rightMouseButtonDown = Mouse.isButtonDown(1);
		
		// Handle keyboard input
		while (Keyboard.next()){
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
				Ora.changeMultiplier(0.2f);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()){
				Ora.changeMultiplier(-0.2f);
			}
		}
	}
	
	private void placeTower() {
		Pllaka currentTile = getMouseTile();
		if (holdingTower && !currentTile.getOccupied() && modifyGold(- tempTower.getCost())) { // how much cost one Cannon
				towerList.add(tempTower);
				currentTile.setOccupied(true);
				holdingTower = false;
				tempTower = null;
			}
	}
	
	public void pickTower(Tower t) {
		tempTower = t;
		holdingTower = true;
	}
	
	private Pllaka getMouseTile() {
		return grid.merrPllaka(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
	}
}
