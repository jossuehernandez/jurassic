package mx.com.dalvik;

public class GaussianElimination {
	
	public static final double EPSILON = 0.00001;
	
	public double [] solve( double[][] matrix, double [] vector ) {
		
		int N = matrix.length;
		
		for( int i = 0; i < N; i++ ) {
			System.out.println( "Index: "+i );
			
			int max = i;
			for( int j = i + 1; j< N ; j ++ ) {
				if( Math.abs( matrix[ j ][ i ] ) > Math.abs( matrix[ max ][ i ] ) )
					max = j;
			}
			
			
			// Cambio de filas
			double [] temp = matrix[ i ];
			matrix[ i ] = matrix[ max ];
			matrix[ max ] = temp;
			
			// Cambio de elementos del vector
			double v = vector[ i ];
			vector[ i ] = vector[ max ];
			vector[ max ] = v;
			
			if( Math.abs( matrix[ i ][ i ] ) <= EPSILON ) {
				throw new RuntimeException(" Matrix is singular ...");
			}
			System.out.println();
			
			
			
			
			
			
			
			
			for( int k = i+1; k<N; k++ ) {
				System.out.println("Pivoting start ...");
				double alpha = matrix[ k ][ i ] / matrix[ i ][ i ];
				System.out.println("alpha: "+ alpha );
				
				vector[ k ] -= alpha*vector[ i ];
				for( int j = i; j<N; j++ ) {
					matrix[ k ][ j ] -= alpha*matrix[ i ][ j ]; 
				}
			}
			System.out.println("Pivoting end ...");
			System.out.println("\n\n");
			
			
			
			
		}
		
		showMatrixVector( matrix, vector );
		
		
		
		double [] x = new double[ N ];
		for( int i = N-1; i >= 0; i-- ) {
			
			double sum = 0.0;
			
			for( int j = i+1; j < N; j++ ) {
				sum += matrix[ i ][ j ] * x[ j ];
			}
			
			x[ i ] = ( vector[ i ] - sum ) / matrix[ i ][ i ];
		}
		
		return x;
	}
	
	
	public void showMatrixVector( double [][] matrix, double [] vector ) {
		
		//Imprime matriz extendida
		
		int N = matrix.length;
		System.out.println();
		
		for( int i = 0; i<N; i++ ) {
			for( int j = 0; j<N; j++ ) {
				System.out.print( matrix[i][j] );
				if( j < N - 1 )
					System.out.print(", ");
				else
					System.out.print(" | "+ vector[ i ] );
			}
			System.out.println();
		}
	}
	
}