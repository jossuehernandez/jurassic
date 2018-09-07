package mx.com.dalvik;

public class BisectionMethod {
	
	public static final double EPSILON = 1e-10;
//	public static final double EPSILON = 1e-15; // max value
	
	public double find( double xPositive, double xNegative ) {
		
		double x = 0;
		
		System.out.println();
		while( Math.abs( xPositive - xNegative ) > EPSILON ) {

			x =  ( xPositive + xNegative ) / 2;
			
			if( f( x ) > 0 ) 
				xPositive = x;
			
			else
				xNegative = x;
		}
		
		return x;
	}
	
	private double f( double x ) {
		
		return - x*x + 3;
	}
}
