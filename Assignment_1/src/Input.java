import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Input {

	int row;
	int column;

	char[][] terrain;
	String fileName;
	static Scanner scanner;

	public Input(String fileName) throws Exception {

		this.fileName = fileName;

		readFile(fileName);

	}

	public void readFile(String fileName) throws Exception {

		File file = new File(fileName + ".txt");

		final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		scanner = new Scanner(bufferedReader);

		row = Integer.parseInt(scanner.nextLine());
		column = Integer.parseInt(scanner.nextLine());

		while (scanner.hasNextLine()) {
			terrain = new char[row][column];
			setCharArray(row, column);

		}
	}

	public void setCharArray(int row, int column) {
		for (int i = 0; i < row; i++) {

			String rowString = scanner.nextLine();// row as string

			for (int j = 0; j < column; j++) {

				terrain[i][j] = rowString.charAt(j);

			}

		}

	}

	public char[][] getTerrain() {
		return terrain;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
}
