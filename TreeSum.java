import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class TreeSum{
	public static void main(String []args) throws FileNotFoundException{
		
		/**reading from input file and inserting 
		 * data into a newly created 2D array. 
		 */
			File filename = new File("sample_input.txt");
			Scanner scanfile = new Scanner(filename);
			
			String textnum = scanfile.nextLine();
			String[] arraySize = textnum.split(" ");
			
			int arraySizeX = Integer.parseInt(arraySize[0]);
			int arraySizeY = Integer.parseInt(arraySize[1]);
			
			//Size of Array.
			System.out.println(arraySizeX);
			System.out.println(arraySizeY);
			
			int dataArray [][] = new int [arraySizeX][arraySizeY];
			
			//printing out the number of trees.
			System.out.println("Number of trees: "+scanfile.nextLine());
			//inserting data into array.
			while (scanfile.hasNextLine()){
				scanfile.nextLine();
				System.out.println(scanfile.nextLine());
				}
		}

}
