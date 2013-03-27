import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Output {

	private String row;
	private String column;
	private String newline;
	byte[][] byteArray;

	public Output(byte[][] byteArray) throws Exception {
		this.byteArray = byteArray;
		this.row = Integer.toString(byteArray.length);
		this.column = Integer.toString(byteArray[0].length);
		this.newline = "\n";

		writeFile();

	}

	public void writeFile() throws Exception {

		File file = createFile();
		FileOutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
		writeRowAndColumn(outputStream);

		for (int i = 0; i < byteArray.length; i++) {
			for (int j = 0; j < byteArray[0].length; j++) {
				outputStream.write(byteArray[i][j]);

			}
			outputStream.write(newline.getBytes());
		}
		outputStream.close();
	}

	private File createFile() throws IOException {
		File file = new File("terrain" + "_" + row + "_" + column + ".bin");
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	private void writeRowAndColumn(FileOutputStream outputStream) throws IOException {
		outputStream.write(row.getBytes());
		outputStream.write(newline.getBytes());

		outputStream.write(column.getBytes());
		outputStream.write(newline.getBytes());
	}

}
