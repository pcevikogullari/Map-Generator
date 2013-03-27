import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadBin {

	private byte[][] encodedTerrain; 
	private FileInputStream fileInputStream;
	private int row;
	private int column;

	public ReadBin(String fileName, char[][] terrain) throws Exception {
		row = terrain.length;
		column = terrain[0].length;

		File binFile = new File(fileName + ".bin");
		fileInputStream = new FileInputStream(binFile);
		encodedTerrain = new byte[row][column];
	}

	public byte[][] getByteArray() throws Exception {

		skipHeader(); 
		readByteArray();
		return encodedTerrain;
	}

	private void skipHeader() throws IOException {
		while (fileInputStream.read() != '\n'); 
		while (fileInputStream.read() != '\n');
	}

	private void readByteArray() throws IOException {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				encodedTerrain[i][j] = (byte) fileInputStream.read();

			}
			if (isLastRow(i)) { 
				fileInputStream.read();
			}
		}fileInputStream.close();
	}
	
	private boolean isLastRow(int i){
		if(i+1 == row){
			return false;
		}else{
			return true;
		}
		
	}

}
