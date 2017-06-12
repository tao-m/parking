package fi.uba.parking.domain;

public enum Availability {
	
	HIGH, MEDIUM, LOW;
	
	public static Availability calculateAvalability(Long totalCapacity, Long remainingCapacity) {
		
		Long delta = totalCapacity - remainingCapacity;
		Long third = (long) (totalCapacity / 3);
		
		if(delta <= third)
			return HIGH;
		
		if(delta <= 2*third)
			return MEDIUM;
		
		return LOW;		
	}

}
