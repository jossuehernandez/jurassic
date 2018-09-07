import mx.com.dalvik.GaussianElimination;

public class DemoPolynomialRegression {
	public static void main(String[] args) {
		double [][] matrix = {	{1,1},
				{0.035,0.05}};
		double [] vector = {24000,930};
		
		double [][] points = {{1,56.5},{5,113},{20,181},{40,214.5},{80,280.5}};
		
		int N = points.length;
		double [][] coefficients = new double[ N ][ N ];
		double [] vectorEquals = new double[ N ];
		
		
		for( int i = 0; i<points.length; i++ ) {
		double x = points[ i ][ 0 ];
		double fx = points[ i ][ 1 ];
		for( int exp = 0; exp < N; exp++ )
		coefficients[ i ][ exp ] = Math.pow( x, exp );
		vectorEquals[ i ] = fx;
		}
		
		
		
		System.out.println();
		int NN = coefficients.length;
		for(int i = 0; i< NN; i++ ) {
		for( int j = 0; j< NN; j++ ) {
		System.out.print( coefficients[ i ][ j ]+ " ");
		}
		System.out.println();
		}
		
		
		System.out.println("============================");		
		
		
		GaussianElimination gauss = new GaussianElimination();
		double [] solve = gauss.solve(coefficients, vectorEquals);
		
		
		System.out.println("\n\nVector solution:");
		for( int i = 0; i< solve.length; i++ )
		System.out.println( solve[ i ] );
		

	}
}
