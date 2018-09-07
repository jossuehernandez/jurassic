package ac.essex.graphing.charts.continuous;

import ac.essex.graphing.plotting.ContinuousFunctionPlotter;
import ac.essex.graphing.plotting.Function;

public class Polynomial extends ContinuousFunctionPlotter {

	
	public Polynomial() {
		this.function = new Function() {
			@Override
			public double get(double x) {
				return Math.sin(x) - Math.cos( Math.PI*x ) -2*Math.tanh( x ) + 0.1*Math.log( x*x*x + 2 );
			}
		};
	}
	
	@Override
	public double getY( double x ) {

		return this.function.get( x );
				
	}

	@Override
	public String getName() {

		return "Polynomial";
	}
}
