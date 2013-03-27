/*
//written by Pamir Cevikogullari 

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MapGenerator {

	char [] charArray = {'g','d','j','p','s','r','w','h','f','m','i'};
	String mapText = "";
	
	public MapGenerator(){
		
	}

	public void main(String[] args) throws Exception {

		writeFile(generateMap(mapText));
	}

	
	public  String generateMap(String mapText){

		mapText += getRow() + "\n" + getColumn() + "\n" ;
		char[][] generatedArray = new char[getRow()][getColumn()];

		for(int i=0; i<getRow();i++){
			for(int j=0; j<getColumn();j++){

				int randomCharacter =(int) Math.floor(Math.random()*charArray.length);
				generatedArray[i][j] = charArray[randomCharacter];

				mapText += generatedArray[i][j]; 
				

			}mapText += "\n";
		}
		return mapText;
	}
	

	//WRITING TO FILE STARTS
	
	public  void writeFile(String generateMap) throws Exception{

		File file = createFile();

		FileOutputStream fileOutputStream = new FileOutputStream(file);
		byte[] contentInBytes = generateMap.getBytes(); 
		fileOutputStream.write(contentInBytes); 
		fileOutputStream.close();

	}

	private File createFile() throws IOException {
		File file = new File("randomTerrain"+"_" + getRow() + "_" + getColumn() + ".txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	} 
	
	private int getRow(){
		return (int) Math.floor(Math.random()*20 + 1);
	}
	
	private int getColumn(){
		return (int) Math.floor(Math.random()*20 + 1);
	}

}
 */
