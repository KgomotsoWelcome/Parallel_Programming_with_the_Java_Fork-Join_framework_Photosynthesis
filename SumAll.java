//package ForkJoinSum;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.concurrent.ForkJoinPool;

public class SumAll {
	
	static int row;
	static Tree tree;
	static int column;
	static Tree trees [];
	static int numOfTrees;
	static float array [];
	static Tree getTrees[];
	static float Treetotal;
	static float TreesTotal;
	static float TreeAverage;
	static String arraySize[];
	static float dataArray [][];
	static PrintWriter outputStream;
	static BufferedReader filename;
	static long startTime = 0;
	static ArrayList<Float> Trees = new ArrayList<Float>();
	static ArrayList<Tree> TreesStored = new ArrayList<Tree>();
	static TreeSequential sequential = new TreeSequential();
	
	public SumAll()
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
	
	public static void readfile()
	{
		try
		{
			filename = new BufferedReader(new FileReader("sample_input.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
	}
	
	public static void createArray()
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
		/**
		for (int i = 0; i<row; i++)
		{
			for (int j = 0; j<column; j++)
			{
				System.out.println(dataArray[i][j]);
			}
		}
		*/ 
	}
	
	public static void sumTree()
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
				/**
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
				*/ 
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	public static Tree [] convertTrees()
	{
		trees = new Tree[TreesStored.size()];
		for (int i = 0; i<TreesStored.size(); i++)
		{
			trees[i] = TreesStored.get(i);
		}
		return trees;
	}
	
	public static Tree[] getTrees(){return trees;}
	
	public float [][] getDataArray(){return dataArray;}
	
	private static void tick()
	{
		startTime = System.currentTimeMillis();
	}
	
	private static float tock()
	{
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
	
	static final ForkJoinPool fjPool = new ForkJoinPool();
	static float sum(Tree[] arr)
	{
	  return fjPool.invoke(new SumArray(dataArray,getTrees(),0,arr.length));
	}
	
	public static void Average(Tree[] arr)
	{
		TreeAverage = sum(arr)/arr.length;
	}
	
	public static void main(String[] args) 
	{
		SumAll treeParallel = new SumAll();
		//SumArray treeSumArray = new SumArray(dataArray,getTrees(),0,arr.length);
		treeParallel.readfile();
		treeParallel.createArray();
		treeParallel.sumTree();
		treeParallel.convertTrees();
		treeParallel.getTrees();
		
		int max = TreesStored.size();
		Tree[] arr = new Tree[max];
		
		for (int i = 0; i < max; i++) 
		{
			arr[i] = TreesStored.get(i);
		}
		
		SumArray treeSumArray = new SumArray(treeParallel.getDataArray(),getTrees(),0,arr.length);
		treeSumArray.printTrees();
		tick();
		
		float sumArr = sum(arr);
		float time = tock();
		System.out.println("Run took "+ time +" seconds");
		
		System.out.println("Sum is:");
		System.out.println(sumArr);
		System.out.println( "Average is "+sumArr/numOfTrees);
		
		tick();
		float sumArr1 = sum(arr);
		time = tock();
		System.out.println("Second run took "+ time +" seconds");
		
		System.out.println("Sum is:");
		System.out.println(sumArr1);
		System.out.println( "Average is "+sumArr1/numOfTrees);
		
	}

}
