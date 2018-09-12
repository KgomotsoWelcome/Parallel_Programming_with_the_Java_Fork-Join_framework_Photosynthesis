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
				
				int row = Integer.parseInt(arraySize[0]);
				int column = Integer.parseInt(arraySize[1]);
				
				float dataArray [][] = new float [row][column];
				String avgSunlight = scanfile.nextLine();
				System.out.println(avgSunlight);
				String [] arr = avgSunlight.split(" ");
				
				System.out.println("**********2d Array********");
				int count=0;
				for  (int j = 0 ; j<column ; j++)
				{
					int counter = 0;
					for (int i = count ; i<row+count ; i++)
					{
						System.out.print(arr[i]+" ");
						dataArray[j][counter]= Float.parseFloat(arr[i]);
						counter++;
					}
					count=count+row;
					System.out.println();
				}
				
				/**summing up sunlight
				 * of each tree
				 */ 
				int numOfTrees = Integer.parseInt(scanfile.nextLine());
				int sumTotal; 
				 
				while (scanfile.hasNextLine())
				{
					String tree = scanfile.nextLine();
					String [] treeData = tree.split(" ");
					
					int yCoordinate = Integer.parseInt(treeData[0]);
					int xCoordinate = Integer.parseInt(treeData[1]);
					int treeSize = Integer.parseInt(treeData[2]);
					
					float sumTree; 
					
					for (int i = xCoordinate; i<column;i++)
						for (int j = yCoordinate; j<row; j++)
						{
							if ((i + treeSize < column) && (j + treeSize < row))
							{
								System.out.println(dataArray[i][j]);
								System.out.println("true");
								
							}
						}
						
					
					//String treeData [] = scanfile.nextLine().split();
					//scanfile.nextLine();
					//System.out.println(scanfile.nextLine());
					
				}
			}
			
			catch(FileNotFoundException e)
			{
				System.out.println("File not found");
			}
	}
	
	public static void sumTree()
	{
		
	}
		
	public static void main(String []args)
	{
		readfile();
		sumTree();
	}
}
	
