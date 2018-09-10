package testing;

import mx.com.dalvik.GaussianElimination;

public class TestGaussianElimination {
	
	public static void main(String[] args) {
		double [][] matrix = {	{1,5,-2},
								{2,3,1},
								{2,4,-3}};
		double [] vector = {2,5,2};
		
		GaussianElimination gauss = new GaussianElimination();
		double [] solve = gauss.solve(matrix, vector );
		
		
		System.out.println("\n\nVector solution:");
		for( int i = 0; i< solve.length; i++ )
		System.out.println( solve[ i ] );
		
	}
}
