//package ForkJoinSum;

import java.util.concurrent.RecursiveTask;
import java.util.ArrayList;

public class SumArray extends RecursiveTask<Float>  {
	  int lo; // arguments
	  int hi;
	  Tree tree;
	  float[] arr;
	  Tree trees[];
	  float Treetotal;
	  float TreesTotal;
	  float dataArray [][];
	  static final int SEQUENTIAL_CUTOFF=500;
	  static ArrayList<Float> TreesArray = new ArrayList<Float>();

	  float ans = 0; // result 
	 
	  SumArray(float[][] a, Tree[]array, int l, int h ) 
	  { 
	    lo=l; hi=h; dataArray=a; trees = array;
	  }


	  protected Float compute()
	  {
		  // return answer - instead of run
		  int row = dataArray[0].length; 
		  if((hi-lo) < SEQUENTIAL_CUTOFF) 
		  {
			  float Treetotal = 0;
		      for(int z = lo; z < hi; z++)
		      {
					tree = trees[z];
					for (int i = tree.getX(); i< tree.getEndX();i++)
					{
						for (int j = tree.getY(); j< tree.getEndY(); j++)
						{
							if(i < row && j < row)
							{
								Treetotal += dataArray[i][j];
							}
						}
					}
					TreesArray.add(Treetotal);
				}
		        TreesTotal += Treetotal;
		        return TreesTotal;
		  }
		  else
		  {
			  SumArray left = new SumArray(dataArray,trees,lo,(hi+lo)/2);
			  SumArray right= new SumArray(dataArray,trees,(hi+lo)/2,hi);
			  
			  // order of next 4 lines
			  // essential – why?
			  left.fork();
			  float rightAns = right.compute();
			  float leftAns  = left.join();
			  return leftAns + rightAns;     
		  }
	 }
	 
	 public void printTrees()
	 {
		for (int i = 0; i<TreesArray.size(); i++)
		{
			System.out.println(TreesArray.get(i));
			System.out.println("hi");
		}
	 }
	 /**
	 public static void main(String[] args) 
	 {
		SumArray treeParallel = new SumArray();
		treeParallel.compute();
		
		for (int i = 0; i<TreesArray.size(); i++)
		{
			System.out.println(TreesArray.get(i));
			System.out.println("hi");
		}
	 }
	 */ 
	 
}


