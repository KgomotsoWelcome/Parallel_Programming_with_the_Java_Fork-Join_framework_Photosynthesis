//package ForkJoinSum;

import java.util.concurrent.ForkJoinPool;

public class TreeParallel {
	static long startTime = 0;
	
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
	static final ForkJoinPool fjPool = new ForkJoinPool();
	static float sum(float[] arr){
	  return fjPool.invoke(new SumArray(arr,0,arr.length));
	}

	public static void main(String[] args) {
		int max =1000000;
		float [] arr = new float[max];
		for (int i=0;i<max;i++) {
			arr[i]=100;
		}
		tick();
		float sumArr = sum(arr);
		float time = tock();
		System.out.println("Run took "+ time +" seconds");
		
		System.out.println("Sum is:");
		System.out.println(sumArr);
		tick();
		sumArr = sum(arr);
		time = tock();
		System.out.println("Second run took "+ time +" seconds");
		
		System.out.println("Sum is:");
		System.out.println(sumArr);
		
	}

}
