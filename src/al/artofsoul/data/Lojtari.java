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
	private boolean leftMouseButtonDown, rightMouseButtonDown, holdingTower;
	private Tower tempTower;
	private static int Gold, Lives;
	
	public Lojtari(PllakaFusha grid, ValaManager valaManager ){
		this.grid = grid;
		this.types = new PllakaType[3];
		this.types[0] = PllakaType.Grass;
		this.types[1] = PllakaType.Dirt;
		this.types[2] = PllakaType.Water;
		this.valaManager = valaManager;
		this.towerList = new ArrayList<Tower>();
		this.leftMouseButtonDown = false;
		this.rightMouseButtonDown = false;
		this.holdingTower = false;
		this.tempTower = null;
		SetGold(0);
		SetLives(0);
		
	}
	// Initialize Gold and Lives values for player
	public void setup() {
		SetGold(200);
		SetLives(10);
	}
	// check if player can afford tower, if so: change player tower cost
	public static boolean modifyGold(int amount) {
		if (GetGold() + amount >= 0) {
			SetGold(GetGold() + amount);
			System.out.println(GetGold());
			return true;
		}
		System.out.println("Glod");
		return false;
	}

	private static void SetGold(int amount) {
	    Gold = amount;
    }

    public static int GetGold() {
        return Gold;
    }
	
	public static void modifyLives(int amount) {
		Lives += amount;
	}

    private static void SetLives(int amount) {
        Lives = amount;
    }

    public static int GetLives() {
        return Lives;
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
			/*if (modifyGold(-55))
				towerList.add(new TowerCannonIce(TowerType.CannonIce, grid.merrPllaka(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE), valaManager.getCurrentWave().getArmikuList()));
				*/
		}
		
		leftMouseButtonDown = Mouse.isButtonDown(0);
		rightMouseButtonDown = Mouse.isButtonDown(1);
		
		// Handle keyboard input
		while (Keyboard.next()){
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
				Ora.ChangeMultiplier(0.2f);
				//moveIndex();
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()){
				Ora.ChangeMultiplier(-0.2f);
			}
		}
	}
	
	private void placeTower() {
		Pllaka currentTile = getMouseTile();
		if (holdingTower)
			if (currentTile.getOccupied() == false && modifyGold(- tempTower.getCost())) { // how much cost one Cannon
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
