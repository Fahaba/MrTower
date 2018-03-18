package al.artofsoul.data.armiku;

import al.artofsoul.data.pllaka.PllakaFusha;

public class ArmikuPlane extends Armiku{

	public ArmikuPlane(int tilleX, int tileY, PllakaFusha grid) {
		super(tilleX, tileY, grid);
		this.setTexture("/res/armiku/armikRed32");
	}

}
