import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class TreeSequential
{
	Tree tree;
	int row, column, numOfTrees;
	BufferedReader filename;
	float dataArray [][];
	float Treetotal, TreesTotal, TreeAverage;
	ArrayList<Float> Trees = new ArrayList<Float>();
	String[] arraySize;
	PrintWriter outputStream;
	
	public TreeSequential()
	{
		filename = null;
		dataArray = null;
		row = 0;
		column = 0;
		Treetotal = 0;
		TreesTotal = 0;
		TreeAverage = 0;
		arraySize = null;
		outputStream = null;
	}
	
	/**reading from input file and inserting 
		 * data into a newly created 2D array
		 * readfile() returns an array with 
		 * average sunlight. 
		 */
	public void readfile(String inputfile)
	{
		try
		{
			filename = new BufferedReader(new FileReader(inputfile+".txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
	}
	
	public void createArray()
	{
		try
		{
			String textnum = filename.readLine();
			arraySize = textnum.split(" ");
			
			row = Integer.parseInt(arraySize[0]);
			column = Integer.parseInt(arraySize[1]);
			
			dataArray = new float [row][column];
			String avgSunlight = filename.readLine();
			String [] arr = avgSunlight.split(" ");
			
			
			int count=0;
			for  (int j = 0 ; j<column ; j++)
			{
				int counter = 0;
				for (int i = count ; i<row+count ; i++)
				{
					dataArray[j][counter]= Float.parseFloat(arr[i]);
					counter++;
				}
				count=count+row;
			}
			
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		
	}
			
	/**summing up sunlight
		* of each tree
		*/ 
	public void sumTree()
	{
		try
		{
			numOfTrees = Integer.parseInt(filename.readLine());
			int sumTotal; 
			
			String line="";
			while ((((line = filename.readLine()) !=null)))
			{
				String [] treeData = line.split(" ");
				
				if(treeData.length<3)
				{
					break;
				}
				
				int yCoordinate = Integer.parseInt(treeData[1]);
				
				int xCoordinate = Integer.parseInt(treeData[0]);
				
				int treeSize = Integer.parseInt(treeData[2]);
				
				Treetotal = 0;
				for (int i = xCoordinate; i< xCoordinate+treeSize;i++)
				{
					for (int j = yCoordinate; j< yCoordinate+treeSize; j++)
					{
						if(i < row && j < column)
						{
							Treetotal += dataArray[i][j];
						}
					}
				}
				Trees.add(Treetotal);
				TreesTotal += Treetotal;
				TreeAverage = TreesTotal/Trees.size();
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	public void writeToFile(String outputfile)
	{
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(outputfile+".txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not created.");
		}
		
		outputStream.println(TreeAverage);
		outputStream.println(numOfTrees);
		
		for (float totals: Trees)
		{
			outputStream.println(totals);
		}
		outputStream.close();
	}
	
	static long startTime = 0;
	
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
		
	public static void main(String []args)
	{
		TreeSequential treeSequential = new TreeSequential();
		treeSequential.tick();
		treeSequential.readfile(args[0]);
		treeSequential.createArray();
		treeSequential.sumTree();
		treeSequential.writeToFile(args[1]);
		float time = treeSequential.tock();
		System.out.println("Run took "+ time +" seconds");
	}
}
	
