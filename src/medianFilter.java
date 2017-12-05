import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class medianFilter {
	
	private int[][] mirrorFramedArray;
	private int[][] tempArray;
	private int[] neighborArray;
	private int numRows;
	private int numCols;
	private int minVal;
	private int maxVal;
	private int newMin;
	private int newMax;
	private String inFile;
	private String outFile;

	public medianFilter(String inFile, String outFile) throws FileNotFoundException {
		
		this.inFile = inFile;
		this.outFile = outFile;
		
		Scanner dataFile = new Scanner(new File(inFile));
		
		numRows = dataFile.nextInt();
		numCols = dataFile.nextInt();
		minVal = dataFile.nextInt();
		maxVal = dataFile.nextInt();
		
		dataFile.close(); // input file closed
		
		mirrorFramedArray = new int[numRows+2][numCols+2];
		tempArray = new int[numRows+2][numCols+2];
		neighborArray = new int[9];
		
	}

	public void loadImage() throws FileNotFoundException {
		
		Scanner dataFile = new Scanner(new File(inFile));
		
		dataFile.nextLine();	// skip header line
		
		for (int r = 1; r <= numRows; r++) {
			for (int c = 1; c <= numCols; c++) {
				mirrorFramedArray[r][c] = dataFile.nextInt();
			}
		}
		
		dataFile.close(); 	// input file closed	
	}
	
	public void mirrorFrame() {
		
		for (int k = 0; k < numRows+2; k++) {
			mirrorFramedArray[k][0] = mirrorFramedArray[k][1];
			mirrorFramedArray[k][numCols+1] = mirrorFramedArray[k][numCols];
		}
		
		for (int p = 0; p < numCols+2; p++) {
			mirrorFramedArray[0][p] = mirrorFramedArray[1][p];
			mirrorFramedArray[numRows+1][p] = mirrorFramedArray[numRows][p];
		}
		
	}
	
	public void median3X3() {
		
		newMin = maxVal;
		
		for (int r = 1; r <= numRows; r++) {
			for (int c = 1; c <= numCols; c++) {
				tempArray[r][c] = getMedian(r, c);
				if (newMin > tempArray[r][c]) newMin = tempArray[r][c];
				if (newMax < tempArray[r][c]) newMax = tempArray[r][c];
			}
		}
	
	}
	
	public int getMedian(int row, int col) {
		
		int counter = 0;
		
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				neighborArray[counter] = mirrorFramedArray[i][j];
				counter++;
			}
		}
		
		neighborArray = selectionSort(neighborArray);
					
		return neighborArray[4];	
	}
	
	
	public int[] selectionSort(int[] arr){
        
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[index]) index = j;
      
            int smallerNumber = arr[index];  
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
        return arr;
    }
	
	public void printMedianArray() throws IOException {
		
		PrintWriter out = new PrintWriter(new FileWriter(outFile));
		
		out.println(numRows + " " + numCols + " " + newMin + " " + newMax);
		
		for (int r = 1; r <= numRows; r++) {
			for (int c = 1; c <= numCols; c++) {
				out.print(tempArray[r][c] + " ");
			}
			out.println();
		}

		out.close(); // output file closed
	}
	
}
