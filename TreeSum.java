import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class TreeSum{
	
	/**reading from input file and inserting 
		 * data into a newly created 2D array
		 * readfile() returns an array with 
		 * average sunlight. 
		 */
	public static void readfile()
	{
			try
			{
				File filename = new File("dummy.txt");
				Scanner scanfile = new Scanner(filename);
				
				String textnum = scanfile.nextLine();
				String[] arraySize = textnum.split(" ");
				
				int arraySizeX = Integer.parseInt(arraySize[0]);
				int arraySizeY = Integer.parseInt(arraySize[1]);
				
				//Size of Array.
				System.out.println(arraySizeX);
				System.out.println(arraySizeY);
				
				float dataArray [][] = new float [arraySizeX][arraySizeY];
				String arr[];
				String avgSunlight = scanfile.nextLine();
				arr = avgSunlight.split(" ");
				
				System.out.println("**********2d Array********");
				int count=0;
				for  (int j = 0 ; j<arraySizeY ; j++)
				{
					int counter = 0;
					for (int i = count ; i<arraySizeX+count ; i++)
					{
						System.out.print(arr[i]+" ");
						dataArray[j][counter]= Float.parseFloat(arr[i]);
						counter++;
					}
					count=count+arraySizeX;
					System.out.println();
				}
				  
				//inserting data into array.
				/**
				while (scanfile.hasNextLine())
				{
					scanfile.nextLine();
					scanfile.nextLine();
					System.out.println(scanfile.nextLine());
					
				}
				*/ 
			}
			
			catch(FileNotFoundException e)
			{
				System.out.println("File not found");
			}
	}
		
	public static void main(String []args)
	{
		readfile();
	}
}
	
