package ac.essex.graphing.charts.continuous;

import ac.essex.graphing.plotting.ContinuousFunctionPlotter;
import ac.essex.graphing.plotting.Function;

public class Line extends ContinuousFunctionPlotter{


	public Line() {
		this.function = new Function() {
			@Override
			public double get(double x) {
				return x;
			}
		};
	}
	
	@Override
	public double getY( double x ) {

		return this.function.get( x );
				
	}

	@Override
	public String getName() {

		return "Line";
	}

}
