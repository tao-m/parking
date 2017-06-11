package fi.uba.parking.utils;

import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CostCalculator {

	public static final BigInteger PARKING_COST_PER_MINUTE_IN_CENTS = BigInteger.valueOf(100);

	public static BigInteger calculateCost(Date starTime, Date endTime) {
		BigInteger minutes = BigInteger
				.valueOf(TimeUnit.MILLISECONDS.toMinutes(endTime.getTime() - starTime.getTime()));

		return (minutes.equals(BigInteger.ZERO)) ? PARKING_COST_PER_MINUTE_IN_CENTS
				: PARKING_COST_PER_MINUTE_IN_CENTS.multiply(minutes);
	}

}
