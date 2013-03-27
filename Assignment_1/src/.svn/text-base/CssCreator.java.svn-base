import java.io.File;
import java.io.FileOutputStream;

public class CssCreator {

	private static final int LAST_YPOS_OF_IMAGE = 300;
	private static final int TILE_SIZE = 30;
	private static final int FOUR_BIT = 4;
	private int xPosition;
	private int yPosition;
	private String cssText;
	private File file;

	static char[] TERRAIN_TYPE = { 'g', 'd', 'i', 'j', 'p', 's', 'r', 'w', 'h',
		'f', 'm' };

	public String getGenerateCss() throws Exception {

		cssText = "";
		for (int i = 0; i < TERRAIN_TYPE.length; i++) {
			for (int j = 0; j < getDirectionArray().length; j++) {

				getCssHeader(i, j);
				getAttributes();
				setPositionTo(i, j);
				backgroundPosition();
			}
		}
		return cssText;
	}

	private void getCssHeader(int i, int j) throws Exception {
		cssText += "." + TERRAIN_TYPE[i] + "" + getDirectionArray()[j]+ "{" + "\n";
	}

	private void getAttributes() {
		cssText += "height: 30px;" + "\n";
		cssText += "width: 30px;" + "\n";
		cssText += "background: url(tiles.png);" + "\n";
		cssText += "overflow: hidden;" + "\n";
	}

	private void setPositionTo(int i, int j) {
		xPosition = j * 30;
		yPosition = (i + 1) * 30;

		checkCondition(i);
	}

	private void checkCondition(int i) {
		if (yPosition >= 210) //After -210px, we have to skip 1 row tiles.
			yPosition += TILE_SIZE;

		if (yPosition >= LAST_YPOS_OF_IMAGE) {
			xPosition = (i - 8) * 120; // 120px is the x-distance between 3 tiles at the bottom. 
			yPosition = LAST_YPOS_OF_IMAGE;
		}
	}

	private void backgroundPosition() {
		cssText += "background-position: -" + xPosition + "px -"+ yPosition + "px;}" + "\n";
	}


	public String[] getDirectionArray() throws Exception {

		String direction;
		String[] directions = new String[16];

		for (int i = 0; i < 16; i++) {

			direction = "";
			String numbersInBinaryForm = Integer.toBinaryString(i);

			numbersInBinaryForm = getFixedBits(numbersInBinaryForm);

			direction = getDirectionFrom(numbersInBinaryForm);
			directions[i] = direction;

		}
		return directions;
	}

	private String getDirectionFrom(String numbersInBinaryString) {
		String fourDirection;
		fourDirection = "_" + numbersInBinaryString.charAt(0) + "_"	+ numbersInBinaryString.charAt(1) +
				"_" + numbersInBinaryString.charAt(2) + "_"+ numbersInBinaryString.charAt(3);
		return fourDirection;
	}

	private String getFixedBits(String direction) { 
		if (direction.length() < FOUR_BIT) {
			while (direction.length() < FOUR_BIT) {
				direction = "0" + direction;
			}
		} else if (direction.length() > FOUR_BIT) {
			while (direction.length() > FOUR_BIT) {
				direction = direction.substring(direction.length() - 8,
						direction.length());
			}
		}
		return direction;
	}


	public void writeCSS() throws Exception {
		createFile();
		streamFileOut();
	}

	private void createFile() throws Exception {
		file = new File("map.css");
		if (!file.exists()) {
			file.createNewFile();
		}
	}

	private void streamFileOut() throws Exception {

		FileOutputStream outputStream = new FileOutputStream(file);
		byte[] contentInBytes = getGenerateCss().getBytes();
		outputStream.write(contentInBytes);
		outputStream.close();

		System.out.println("map.css file is writen properly!!!");

	}

}