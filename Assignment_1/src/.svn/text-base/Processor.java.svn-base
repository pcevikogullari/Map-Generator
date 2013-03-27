public class Processor {

	byte tempByte;
	byte[][] byteArray;
	char[][] charArray;
	int row;
	int column;

	private static final byte NORTH_WEST = 1 << 0;
	private static final byte NORTH = 1 << 1;
	private static final byte NORTH_EAST = 1 << 2;
	private static final byte EAST = 1 << 3;
	private static final byte SOUTH_EAST = 1 << 4;
	private static final byte SOUTH = 1 << 5;
	private static final byte SOUTH_WEST = 1 << 6;
	private static final byte WEST = (byte) (1 << 7);

	public Processor(char[][] terrain) {
		charArray = terrain;
		row = charArray.length;
		column = charArray[0].length;

		run();

	}

	public void run() {

		byteArray = new byte[row][column];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {

				tempByte = 0;

				checkNorthWest(i, j);
				checkNorth(i, j);
				checkNorthEast(i, j);
				checkEast(i, j);
				checkSouthEast(i, j);
				checkSouth(i, j);
				checkSouthWest(i, j);
				checkWest(i, j);

				byteArray[i][j] |= tempByte;

			}

		}
	}

	public void checkNorthWest(int x, int y) {
		try {
			if (charArray[x][y] != charArray[x - 1][y - 1]) {
				tempByte |= NORTH_WEST;
			}
		} catch (Exception e) {
			tempByte |= (0 << 0);
		}
	}

	public void checkNorth(int x, int y) {
		try {
			if (charArray[x][y] != charArray[x - 1][y]) {
				tempByte |= NORTH;
			}
		} catch (Exception e) {
			tempByte |= (0 << 1);
		}
	}

	public void checkNorthEast(int x, int y) {
		try {
			if (charArray[x][y] != charArray[x - 1][y + 1]) {
				tempByte |= NORTH_EAST;
			}
		} catch (Exception e) {
			tempByte |= (0 << 2);
		}
	}

	public void checkEast(int x, int y) {
		try {
			if (charArray[x][y] != charArray[x][y + 1]) {
				tempByte |= EAST;
			}
		} catch (Exception e) {
			tempByte |= (0 << 3);
		}
	}

	public void checkSouthEast(int x, int y) {
		try {
			if (charArray[x][y] != charArray[x + 1][y + 1]) {
				tempByte |= SOUTH_EAST;
			}
		} catch (Exception e) {
			tempByte |= (0 << 4);
		}
	}

	public void checkSouth(int x, int y) {
		try {
			if (charArray[x][y] != charArray[x + 1][y]) {
				tempByte |= SOUTH;
			}
		} catch (Exception e) {
			tempByte |= (0 << 5);
		}
	}

	public void checkSouthWest(int x, int y) {
		try {
			if (charArray[x][y] != charArray[x + 1][y - 1]) {
				tempByte |= SOUTH_WEST;
			}
		} catch (Exception e) {
			tempByte |= (0 << 6);
		}
	}

	public void checkWest(int x, int y) {
		try {
			if (charArray[x][y] != charArray[x][y - 1]) {
				tempByte |= WEST;
			}
		} catch (Exception e) {
			tempByte |= (0 << 7);
		}
	}

	public byte[][] getByteArray() {
		return byteArray;
	}

}
