package al.artofsoul.data.armiku;

import al.artofsoul.data.Pllaka.PllakaFusha;

public class ArmikuUFO extends Armiku{

	public ArmikuUFO(int tilleX, int tileY, PllakaFusha grid) {
		super(tilleX, tileY, grid);
		this.setTexture("/res/armiku/armikBlue32");
		this.setSpeed(60);
	}

}
