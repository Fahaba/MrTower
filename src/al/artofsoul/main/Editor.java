package al.artofsoul.main;

import static al.artofsoul.ndihma.Artist.*;
import static al.artofsoul.ndihma.Leveler.*;

import al.artofsoul.data.pllaka.PllakaFusha;
import al.artofsoul.data.pllaka.PllakaType;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import al.artofsoul.userinterface.UI;
import al.artofsoul.userinterface.UI.Menu;

public class Editor{

	private PllakaFusha grid;
	private int index;
	private PllakaType[] types;
	private UI editorUI;
	private Menu tilePickerMenu;
	private Texture menuBackground;

	public Editor() {
		this.grid = loadMap("res/map/harta");
		this.index = 0;
		this.types = new PllakaType[3];
		this.types[0] = PllakaType.GRASS;
		this.types[1] = PllakaType.DIRT;
		this.types[2] = PllakaType.WATER;
		this.menuBackground = quickLoad("/res/menu/tileBackground");
		setupUI();
	}
	
	private void setupUI () {
		editorUI = new UI();
		editorUI.createMenu("TilePicker", 896, 50, 64, 0); //640, 50, 64, 320, 1, 0, 0 for 32
		tilePickerMenu = editorUI.getMenu("TilePicker");
		tilePickerMenu.quickAdd("GRASS", "/res/tiles/bari32");
		tilePickerMenu.quickAdd("DIRT", "/res/tiles/toka32");
		tilePickerMenu.quickAdd("WATER", "/res/tiles/uj32");
		
	}

	public void update() {
		draw();

		// Handle Mouse Input
		if (Mouse.next() && Mouse.isButtonDown(0)) {
            if(tilePickerMenu.isButtonClicked("GRASS")) {
                index = 0;
            } else if (tilePickerMenu.isButtonClicked("DIRT")){
                index = 1;
            } else if (tilePickerMenu.isButtonClicked("WATER")){
                index = 2;
            }else {
                shtoPllaka();
            }
		}
		
		// Handle keyboard input
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT
					&& Keyboard.getEventKeyState()) {
				moveIndex();
			}
			// saving editor map create by user with S key from keyboard
			if (Keyboard.getEventKey() == Keyboard.KEY_S
					&& Keyboard.getEventKeyState()) {
				saveMap("res/map/harta", grid);
			}
		}
	}
	
	private void draw () {
		vizatoKatrorTex(menuBackground, 896, 0, 64, 480);
		grid.draw();
		editorUI.draw();
	}

	private void shtoPllaka() {
		grid.shtoPllaka((int) Math.floor((double)Mouse.getX() / TILE_SIZE),
				(int) Math.floor((double)(HEIGHT - Mouse.getY() - 1) / TILE_SIZE),
				types[index]);
	}
	//Allows editor to change which PllakaType is selected
	private void moveIndex(){
		index++;
		if (index > types.length - 1){
			index = 0;
		}
	}
}
