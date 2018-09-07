import mx.com.dalvik.BisectionMethod;

public class DemoBisection {
	
	public static void main(String[] args) {
		
		BisectionMethod bm = new BisectionMethod();
		System.out.println( Math.sqrt( 3 ) );
		System.out.println( bm.find(-4, 4));
		
		System.out.println();
		System.out.println( 1e-18 );
	}
}
