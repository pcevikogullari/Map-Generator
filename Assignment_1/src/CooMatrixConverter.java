import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CooMatrixConverter {

	private int column = 0;
	private int row = 0;	
	private double nonZero= 0;
	private ArrayList<Integer> columns;
	private ArrayList<Integer> rows;
	private ArrayList<Double> nonzeros;
	private ArrayList<Integer> processedRows;
	private Scanner scanner;

	public CooMatrixConverter() throws Exception{
		convertCooToCsrMatrix();
	}

	public static void main(String[] args) throws Exception {
		new CooMatrixConverter();
	}

	private void convertCooToCsrMatrix() throws Exception {
		initialize();

		scanner.nextLine(); // skip first line
		int columnNumber = scanner.nextInt();  
		int rowNumber = scanner.nextInt();
		scanner.next(); //skip double number in the original file	

		createArrayLists(scanner);

		int doubleNumber = nonzeros.size();

		createProcessedRows(rowNumber);
	//	bubbleSort();
		createCSRObject(columnNumber, rowNumber, doubleNumber);

	}
	
	private void initialize() throws FileNotFoundException {
		File file = new File("tests/memplus.mtx");
		FileInputStream fileOutputStream = new FileInputStream(file);
		scanner = new Scanner(fileOutputStream);
		columns = new ArrayList<Integer>();
		rows = new ArrayList<Integer>();
		nonzeros = new ArrayList<Double>();
		processedRows = new ArrayList<Integer>();
	}
	
	public void bubbleSort() {
		boolean swapped = true;
		int j = 0;
		
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < convertToIntArray(rows).length - j; i++) {
				swapped = checkAndSetOrder(swapped, i);
			}
		}
	}

	private boolean checkAndSetOrder(boolean swapped, int i) {
		int tempForRows;
		int tempForColumns;
		double tempForNonzeros;
		if (convertToIntArray(rows)[i] > convertToIntArray(rows)[i + 1]) {
			tempForRows = convertToIntArray(rows)[i];
			tempForColumns = convertToIntArray(columns)[i];
			tempForNonzeros = convertToDoubleArray(nonzeros)[i];
			
			swapRows(tempForRows, i);
			swapColumns(tempForColumns, i);
			swapNonzeros(tempForNonzeros, i);
			swapped = true;
		}
		return swapped;
	}
	
	private void swapRows(int tempForRows, int i) {
		convertToIntArray(rows)[i] = convertToIntArray(rows)[i + 1];
		convertToIntArray(rows)[i + 1] = tempForRows;
	}
	
	private void swapColumns(int tempForColumns, int i) {
		convertToIntArray(columns)[i] = convertToIntArray(columns)[i + 1];
		convertToIntArray(columns)[i + 1] = tempForColumns;
	}

	private void swapNonzeros(double tempForNonzeros, int i) {
		convertToDoubleArray(nonzeros)[i] = convertToDoubleArray(nonzeros)[i + 1];
		convertToDoubleArray(nonzeros)[i + 1] = tempForNonzeros;
	}

	private void createCSRObject(int columnNumber, int rowNumber, int doubleNumber) throws Exception {
		CSRMatrix csr = new CSRMatrix(rowNumber,columnNumber,doubleNumber);
		csr.setCols(convertToIntArray(columns));
		csr.setRows(convertToIntArray(processedRows));
		csr.setVals(convertToDoubleArray(nonzeros));
		writeToFile(csr);
	}



	private void createArrayLists(Scanner scanner) {
		while(scanner.hasNext()){
			createColumns(scanner);
			createRows(scanner);
			createNonzeros(scanner);
			scanner.nextLine();
		}
		removeZeroValues();
	}

	private void createColumns(Scanner scanner) {
		column = scanner.nextInt()-1;
		columns.add(column);
	}

	private void createRows(Scanner scanner) {
		row = scanner.nextInt(); //TODO 
		rows.add(row);
	}
	
	private void createNonzeros(Scanner scanner) {
		nonZero = scanner.nextDouble();
		nonzeros.add(nonZero);
	}
	
	private void removeZeroValues() {
		int count = 0;
		while(count < nonzeros.size()){
			if(nonzeros.get(count) == 0){
				nonzeros.remove(count);
				rows.remove(count);
				columns.remove(count);
				count --;
			}
			count++;
		}
	}

	private void createProcessedRows(int rowNumber) {
		int numberOfElements = 0;
		for (int i = 0; i <= rowNumber; i++) {	
			numberOfElements += countElementsInRow(i);
			processedRows.add(numberOfElements);	
		}
	}

	private int countElementsInRow( int i) {
		int numberOfElements =0;
		for (int j = 0; j < rows.size(); j++) {
			if(rows.get(j) == i){
				numberOfElements++;	
			}
		}
		return numberOfElements;
	}

	public int[] convertToIntArray(ArrayList<Integer> arrayList){
		int intArray[] = new int[arrayList.size()];
		for (int i = 0; i < arrayList.size(); i++) {
			intArray[i] = arrayList.get(i);
		}
		return intArray;
	}

	public double[] convertToDoubleArray(ArrayList<Double> arrayList){
		double doubleArray[] = new double[arrayList.size()];
		for (int i = 0; i < arrayList.size(); i++) {
			doubleArray[i] = arrayList.get(i);
		}
		return doubleArray;
	}

	private void writeToFile(CSRMatrix csr) throws Exception{
		File file = new File("tests/(my)memplus.txt");
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		bufferedWriter.write(csr.toString());
		System.out.println(csr.toString());
		bufferedWriter.close();
	}
}