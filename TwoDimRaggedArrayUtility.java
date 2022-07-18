import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.concurrent.ForkJoinTask;

import javax.accessibility.AccessibleExtendedTable;

public class TwoDimRaggedArrayUtility {
	
	public TwoDimRaggedArrayUtility() {}
	
	/**
	 * Reads from a file and returns a ragged array of doubles The maximum rows is 10
	 * and the maximum columns for each row is 10 Each row in the file is separated by 
	 * a new line Each element in the row is separated by a space
	 * 
	 * @param file - the file to read from
	 * @return - a two dimensional ragged (depending on data) array of doubles if the file is not empty, returns a null if file is empty
	 * @throws - java.io.FileNotFoundException
	 */
	public static double[][] readFile(File file) throws FileNotFoundException {
		double[][] arr = new double[10][10];
		String read; 
		int count = 0;
		
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			while((read = bReader.readLine()) != null) {
				String[] sArr = read.split(" ");
				arr[count] = new double[sArr.length];
				for(int i = 0; i < sArr.length; i++) {
					arr[count][i] = Double.parseDouble(sArr[i]);
				}
				count++;
			}
			bReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	/**
	 * Writes the ragged array of doubles into the file. 
	 * Each row is on a separate line within the file and each double is separated by a space.
	 * 
	 * @param data - two dimensional ragged array of doubles
	 * @param outputFile - the file to write to
	 * @throws FileNotFoundException
	 */
	public static void writeToFile(double[][] data, File outputFile) throws FileNotFoundException {
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(outputFile));
			for(int row = 0; row < data.length; row++) {
				for(int col = 0; col < data[row].length; col++) {
					bWriter.write(data[row][col] + " ");
				}
				bWriter.newLine();
			}
			bWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the total of all the elements of the two dimensional array
	 * 
	 * @param data - the two dimensional array getting total of
	 * @return - the sum of all the elements in the two dimensional array
	 */
	public static double getTotal(double[][] data) {
		double total = 0;
		
		for(int row = 0; row < data.length; row++) {
			for(int col = 0; col < data[row].length; col++) {
				total += data[row][col];
			}
		}
		
		return total;
	}
	
	/**
	 * Returns the average of the elements in the two dimensional array
	 * 
	 * @param data - the two dimensional array getting the average of
	 * @return- the average of the elements in the two dimensional array (total of elements/num of elements)
	 */
	public static double getAverage(double[][] data) {
		double total = 0;
		int eTotal = 0;
		
		for(int row = 0; row < data.length; row++) {
			for(int col = 0; col < data[row].length; col++) {
				eTotal += 1;
				total += data[row][col];
			}
		}
		double calc = total / eTotal;
		return calc;
	}
	
	/**
	 * Returns the total of the selected row in the two dimensional array index 0 refers to the first row.
	 * 
	 * @param data - the two dimensional array
	 * @param row - the row index to take the total of (0 refers to the first row)
	 * @return - the total of the row
	 */
	public static double getRowTotal(double[][] data, int row) {
		double total = 0;
		
		for(int i = 0; i < data[row].length; i++) {
			total += data[row][i];
		}
		return total;
	}
	
	/**
	 * Returns the total of the selected column in the two dimensional array index 0 refers to the first column. 
	 * If a row in the two dimensional array doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * 
	 * @param data - the two dimensional array
	 * @param col - the column index to take the total of (0 refers to the first column)
	 * @return - the total of the column
	 */
	public static double getColumnTotal(double[][] data, int col) {
		double total = 0;
		
		for(int i = 0; i < data.length; i++) {
			if(col <= data[i].length - 1) {
				total += data[i][col];
			}
		}
		return total;
	}
	
	/**
	 * Returns the largest element of the selected row in the two dimensional array index 0 refers to the first row.
	 * 
	 * @param data - the two dimensional array
	 * @param row - the row index to find the largest element of (0 refers to the first row)
	 * @return - the largest element of the row
	 */
	public static double getHighestInRow(double[][] data, int row) {
		double high = data[row][0];
		
		for(int i = 0; i < data[row].length; i++) {
			if(data[row][i] > high) {
				high = data[row][i];
			}
		}
		
		return high;
	}
	
	/**
	 * Returns the largest element of the selected row in the two dimensional array index 0 refers to the first row.
	 * 
	 * @param data - the two dimensional array
	 * @param row - the row index to find the largest element of (0 refers to the first row)
	 * @return - the largest element of the row
	 */
	public static int getHighestInRowIndex(double[][] data, int row) {
		double high = data[row][0];
		int index = 0;
		
		for(int i = 0; i < data[row].length; i++) {
			if(data[row][i] > high) {
				index = i;
			}
		}
		
		return index;
	}
	
	/**
	 * Returns the smallest element of the selected row in the two dimensional array index 0 refers to the first row.
	 * 
	 * @param data - the two dimensional array
	 * @param row - the row index to find the smallest element of (0 refers to the first row)
	 * @return - the smallest element of the row
	 */
	public static double getLowestInRow(double[][] data, int row) {
		double low = data[row][0];
		
		for(int i = 0; i < data[row].length; i++) {
			if(data[row][i] < low) {
				low = data[row][i];
			}
		}
		return low;
	}
	
	/**
	 * Returns the index of the smallest element of the selected row in the two dimensional array index 0 refers to the first row.
	 * 
	 * @param data - the two dimensional array
	 * @param row - the row index to find the smallest element of (0 refers to the first row)
	 * @return - the index of the smallest element of the row
	 */
	public static int getLowestInRowIndex(double[][] data, int row) {
		int index = 0;
		
		for(int i = 0; i < data[row].length; i++) {
			if(data[row][i] < data[row][index]) {
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * Returns the largest element of the selected column in the two dimensional array index 0 refers to the first column. 
	 * If a row in the two dimensional array doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * 
	 * @param data - the two dimensional array
	 * @param col - the column index to find the largest element of (0 refers to the first column)
	 * @return - the largest element of the column
	 */
	public static double getHighestInColumn(double[][] data, int col) {
		double high = data[0][col];
		
		for(int i = 0; i < data.length; i++) {
			if(col <= data[i].length - 1) {
				if(data[i][col] > high) {
					high = data[i][col];
				}
			}
		}
		return high;
	}
	
	/**
	 * Returns index of the largest element of the selected column in the two dimensional array index 0 refers to the first column. 
	 * If a row in the two dimensional array doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * 
	 * @param data - the two dimensional array
	 * @param col - the column index to find the largest element of (0 refers to the first column)
	 * @return - the index of the largest element of the column
	 */
	public static int getHighestInColumnIndex(double[][] data, int col) {
		int index = 0;
		
		for(int i = 0; i < data.length; i++) {
			if(data[i].length > col) {
				if(data[i][col] > data[index][col]) {
					index = i;
				}
			}
		}
		return index;
	}
	
	/**
	 * Returns the smallest element of the selected column in the two dimensional array index 0 refers to the first column. 
	 * If a row in the two dimensional array doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * 
	 * @param data - the two dimensional array
	 * @param col - the column index to find the smallest element of (0 refers to the first column)
	 * @return - the smallest element of the column
	 */
	public static double getLowestInColumn(double[][] data, int col) {
		double low = data[0][col];
		
		for(int i = 0; i < data.length; i++) {
			if(col  <= data[i].length - 1) {
				if(data[i][col] < low) {
					low = data[i][col];
				}
			}
		}
		return low;
	}
	
	/**
	 * Returns the smallest element of the selected column in the two dimensional array index 0 refers to the first column. 
	 * If a row in the two dimensional array doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * 
	 * @param data - the two dimensional array
	 * @param col - the column index to find the smallest element of (0 refers to the first column)
	 * @return - the smallest element of the column
	 */
	public static int getLowestInColumnIndex(double[][] data, int col) {
		int index = 0;
		
		for(int i = 0; i < data.length; i++) {
			if(data[i].length > col) {
				if(data[i][col] < data[index][col]) {
					index = i;
				}
			}
		}
		return index;
	}
	
	/**
	 * Returns the largest element in the two dimensional array
	 * 
	 * @param data - the two dimensional array
	 * @return - the largest element in the two dimensional array
	 */
	public static double getHighestInArray(double[][] data) {
		double high = data[0][0];
		
		for(int row = 0; row < data.length; row++) {
			for(int col = 0; col < data[row].length; col++) {
				if(data[row][col] > high) {
					high = data[row][col];
				}
			}
		}
		return high;
	}
	
	/**
	 * Returns the smallest element in the two dimensional array
	 * 
	 * @param data - the two dimensional array
	 * @return - the smallest element in the two dimensional array
	 */
	public static double getLowestInArray(double[][] data) {
		double low = data[0][0];
		
		for(int row = 0; row < data.length; row++) { 
			for(int col = 0; col < data[row].length; col++) {
				if(data[row][col] < low) {
					low = data[row][col];
				}
			}
		}
		return low;
	}
	
}
