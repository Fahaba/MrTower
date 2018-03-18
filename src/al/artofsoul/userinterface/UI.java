package al.artofsoul.userinterface;

import static al.artofsoul.ndihma.Artist.*;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

public class UI {
	
	private ArrayList<Button> buttonList;
	private ArrayList<Menu> menuList;
	private TrueTypeFont font;
	private Font awtFont;
	
	public UI () {
		buttonList = new ArrayList<>();
		menuList = new ArrayList<>();
		awtFont = new Font("Times New Roman", Font.BOLD , 13);
		font = new TrueTypeFont(awtFont, false);
	}
	
	public void drawString(int x, int y, String text) {
		font.drawString(x, y, text);
	}
	
	public void addButton(String name, String textureName, int x, int y) {
		buttonList.add(new Button(name, quickLoad(textureName), x, y));
	}
	
	public void createMenu(String name, int x, int y, int width, int optionsWidth) {
		menuList.add(new Menu(name, x, y, width, optionsWidth));
	}
	
	public Menu getMenu(String name) {
		for (Menu m: menuList)
			if(name.equals(m.getName()))
				return m;
		return null;
	}

	public boolean isButtonClicked(String buttonName) {
		Button b = getButton(buttonName);

		if (b == null)
			return false;

		float mouseY = HEIGHT - Mouse.getY() - 1;
		if (Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() &&
				mouseY > b.getY() && mouseY < b.getY() + b.getHeight()) {
			return true;
		}
		return false;
	}
	
	private Button getButton(String buttonName) {
		for (Button b: buttonList) {
			if (b.getName().equals(buttonName)) {
				return b;
			}
		}
		return null;
	}
	
	public void draw() {
		for(Button b: buttonList)
			vizatoKatrorTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
		for (Menu m: menuList) 
			m.draw();
	}
	
	public class Menu {
		
		String name;
		private ArrayList<Button> menuButtons;
		private int x;
        private int y;
        private int buttonAmount;
        private int optionsWidth;
        private int padding;
		
		public Menu(String name, int x, int y, int width, int optionsWidth) {
			this.name = name;
			this.x = x;
			this.y = y;
			this.optionsWidth = optionsWidth;
			this.padding = (width - (optionsWidth * TILE_SIZE)) / (optionsWidth + 1);
			this.buttonAmount = 0;
			this.menuButtons = new ArrayList<>();
		}
		
		public void quickAdd (String name, String buttonTextureName) {
			Button b = new Button (name, quickLoad(buttonTextureName), 0, 0);
			setButton(b);
		}
		
		private void setButton (Button b) {
			if (optionsWidth !=0)
				b.setY(y + (buttonAmount ) * TILE_SIZE); // say buttons to go downt, optionsWidth is 2
			b.setX(x + buttonAmount * (padding + TILE_SIZE) + padding);
			buttonAmount++;
			menuButtons.add(b);
		}
		
		public boolean isButtonClicked(String buttonName) {
			Button b = getButton(buttonName);
			float mouseY = HEIGHT - Mouse.getY() - 1;
			return (Mouse.getX() > b.getX()
                    && Mouse.getX() < b.getX() + b.getWidth()
                    && mouseY > b.getY()
                    && mouseY < b.getY() + b.getHeight());
		}
		
		private Button getButton(String buttonName) {
			for (Button b: menuButtons) {
				if (b.getName().equals(buttonName)) {
					return b;
				}
			}
			return null;
		}
	
		public void draw() {
			for (Button b: menuButtons)
				vizatoKatrorTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
		
		public String getName() {
			return name;
		}
	}
}
