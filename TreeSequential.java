import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.lang.*;
//import java.util.concurrent.ForkJoinPool;

public class TreeSequential
{
	Tree tree;
	int row, column, numOfTrees;
	BufferedReader filename;
	float dataArray [][];
	float Treetotal, TreesTotal, TreeAverage;
	static ArrayList<Float> Trees = new ArrayList<Float>();
	ArrayList<Tree> TreesStored = new ArrayList<Tree>();
	String[] arraySize;
	PrintWriter outputStream;
	float array [];
	Tree trees [];
	
	public TreeSequential()
	{
		filename = null;
		dataArray = null;
		array = null;
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
			//System.out.println("File not found");
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
				
				Tree rawTree = new Tree(xCoordinate, yCoordinate, treeSize);
				TreesStored.add(rawTree);
				
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
	
	public ArrayList<Float> returnTreesArray(){return Trees;}
	
	public void writeToFile(String outputfile)
	{
		try
		{
			outputStream = new PrintWriter(new FileOutputStream("sequential"+outputfile+".txt"));
		}
		catch(FileNotFoundException e)
		{
			//System.out.println("File not created.");
		}
		
		outputStream.println(TreeAverage);
		outputStream.println(numOfTrees);
		
		for (float totals: Trees)
		{
			outputStream.println(totals);
		}
		outputStream.close();
	}
	
	public float [] convertArrayList()
	{
		array = new float[Trees.size()];
		for (int i = 0; i<Trees.size(); i++)
		{
			array[i] = Trees.get(i);
		}
		return array;
	}
	
	public float[] getArray(){return array;}
	
	public int getArraySize()
	{
		return Trees.size();
	}
	
	
	public float [][] getDataArray(){return dataArray;}
	
	public Tree [] convertTrees()
	{
		trees = new Tree[TreesStored.size()];
		for (int i = 0; i<TreesStored.size(); i++)
		{
			trees[i] = TreesStored.get(i);
			//System.out.println(trees[i]);
		}
		return trees;
	}
	
	public Tree[] getTrees(){return trees;}
	
	static long startTime = 0;
	
	private static void tick()
	{
		startTime = System.currentTimeMillis();
	}
	private static float tock()
	{
		return (System.currentTimeMillis() - startTime); 
	}
	
	
		
	public static void main(String []args)
	{
		TreeSequential treeSequential = new TreeSequential();
		
		System.gc();
		//System.out.println("Cleanup completed...");
		
		treeSequential.readfile(args[0]);
		treeSequential.createArray();
		treeSequential.tick();
		treeSequential.sumTree();
		float time = treeSequential.tock();
		treeSequential.writeToFile(args[1]);
		treeSequential.convertTrees();
		treeSequential.getTrees();
		treeSequential.convertArrayList();
		treeSequential.getArray();
		treeSequential.getArraySize();
		treeSequential.getDataArray();
		
		
		
		System.out.println(args[1]+","+time);
	}
}
	
