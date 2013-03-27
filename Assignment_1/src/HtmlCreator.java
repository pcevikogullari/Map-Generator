import java.io.File;
import java.io.FileOutputStream;

public class HtmlCreator {

	private String content;
	private String header;
	private String footer;
	private String fileName;
	private String htmlCode = "";
	private char[][] terrain;
	private byte[][] encodedTerrain;
	private int row;
	private int column;
	private static final int EIGHT_BIT = 8;

	public HtmlCreator(char[][] terrain, String fileName, byte[][] byteArr) throws Exception {
		row = terrain.length;
		column = terrain[0].length;
		this.fileName = fileName;
		encodedTerrain = new byte[row][column];
		encodedTerrain = byteArr;
		this.terrain = terrain;
		run();
	}

	public void run() throws Exception {
		generateTable();
		generateHeader();
		generateFooter();
		generateContent();
		createFile(fileName);

	}
	public void generateTable() {
		for (int i = 0; i < row; i++) {
			htmlCode += "<tr>";
			for (int j = 0; j < column; j++) {
				htmlCode += "<td " + "class=" + terrain[i][j] + getDirections(i, j) + ">"
						+ "</td>";
			}
			htmlCode += "</tr>";

		}
	}

	private void generateHeader() {
		header = "<html><link rel=stylesheet href=" + " \"map.css\" "
				+ "type=\"text/css\"  media=screen> <body><table border=" + "\"0\""
				+ " cellpadding=" + "\"0\"" + " cellspacing=" + "\"0\">";
	}

	private void generateFooter() {
		footer = "</table></body></html>";
	}

	private void generateContent() {
		content = header + htmlCode + footer;
	}

	public String getDirections(int i, int j) {

		String str = Integer.toBinaryString(encodedTerrain[i][j]);
		return getSides(checkAndGetLength(str));

	}

	private String checkAndGetLength(String str) { 

		if (str.length() < EIGHT_BIT) {
			while (str.length() < EIGHT_BIT) {
				str = "0" + str;
			}
		} else if (str.length() > EIGHT_BIT) {
			while (str.length() > EIGHT_BIT) {
				str = str.substring(str.length() - 8, str.length());
			}
		}
		return str;
	}

	private String getSides(String byteInString) {

		return "_" + byteInString.charAt(0) + "_" + byteInString.charAt(2) + "_" + byteInString.charAt(4) + "_"
				+ byteInString.charAt(6);

	}


	public void createFile(String fileName) throws Exception {

		File file;
		file = new File(fileName + ".html");

		if (!file.exists()) {
			file.createNewFile();
		}

		streamFileOut(file, fileName);
	}

	private void streamFileOut(File file, String fileName) throws Exception {

		FileOutputStream fileOutputStream = new FileOutputStream(file);
		byte[] contentInBytes = content.getBytes();
		fileOutputStream.write(contentInBytes);
		fileOutputStream.close();
		System.out.println(fileName + ".html is created.");

	}

}