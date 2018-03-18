package al.artofsoul.ndihma;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import al.artofsoul.data.Pllaka.Pllaka;
import al.artofsoul.data.Pllaka.PllakaFusha;
import al.artofsoul.data.Pllaka.PllakaType;

public class Leveler {

    private Leveler() {
        throw new IllegalStateException("Utility class");
    }

	public static void saveMap(String mapName, PllakaFusha grid) {
		StringBuilder mapDataBuilder = new StringBuilder();
		for (int i = 0; i < grid.getPllakaWide(); i++) {
			for (int j = 0; j < grid.getPllakaHigh(); j++) {
				mapDataBuilder.append(getTileID(grid.merrPllaka(i, j)));
			}
		}
		String mapData = mapDataBuilder.toString();
        File file = new File(mapName);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
			bw.write(mapData);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static PllakaFusha loadMap(String mapName) {
		PllakaFusha grid = new PllakaFusha();
		try(BufferedReader br = new BufferedReader(new FileReader(mapName))) {
			String data = br.readLine();
			for (int i = 0; i < grid.getPllakaWide(); i++) {
				for (int j = 0; j < grid.getPllakaHigh(); j++) {
					grid.shtoPllaka(i, j, getTileType(data.substring(i * grid.getPllakaHigh() + j, i * grid.getPllakaHigh() + j + 1)));
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return grid;
	}
	
	public static PllakaType getTileType(String id) {
		PllakaType type = PllakaType.NULL;
		switch (id) {
		case "0":
			type = PllakaType.GRASS;
			break;
		case "1":
			type = PllakaType.DIRT;
			break;
		case "2":
			type = PllakaType.WATER;
			break;
		case "3":
			type = PllakaType.NULL;
			break;
		default:
		    break;
		}
		
		
		return type;
	}
	
	public static String getTileID(Pllaka t) {
	 
		String id = "E";
		switch(t.getType()){
		case GRASS:
			id = "0";
			break;
		case DIRT:
			id = "1";
			break;
		case WATER:
			id = "2";
			break;
		case NULL:
			id = "3";
			break;
		}
		return id;
	}
}
