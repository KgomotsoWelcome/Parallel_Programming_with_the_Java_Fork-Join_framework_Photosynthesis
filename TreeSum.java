import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class TreeSum{
	public static void main(String []args) throws FileNotFoundException{
		
			File filename = new File("sample_input.txt");
			Scanner scanfile = new Scanner(filename);
			
			
			String textnum = scanfile.nextLine();
			String[] arraySize = textnum.split(" ");
			
			int x = Integer.parseInt(arraySize[0]);
			int y = Integer.parseInt(arraySize[1]);
			
			System.out.println(x);
			System.out.println(y);
			
			int dataArray [][] = new int [x][y];
			
			while (scanfile.hasNextLine()){
				scanfile.nextLine();
				System.out.println(scanfile.nextLine());
				}
		}

}
