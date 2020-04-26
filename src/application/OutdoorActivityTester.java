package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OutdoorActivityTester {

	@Test
	void SelectActivityTest() {
		String activity1 = "sailing";
		String activity2 = "hiking";
		String activity3 = "biking";
		String activity4 = "climbing";
		String activity5 = "skiing";
		OutdoorActivity oA = new OutdoorActivity();
		oA.selectActivity(activity1);
		assertEquals(75,oA.getBestTemperatureMax());
		oA.selectActivity(activity2);
		assertEquals(68,oA.getBestTemperatureMax());
		oA.selectActivity(activity3);
		assertEquals(72,oA.getBestTemperatureMax());
		oA.selectActivity(activity4);
		assertEquals(72,oA.getBestTemperatureMax());
		oA.selectActivity(activity5);
		assertEquals(25,oA.getBestTemperatureMax());
	}

}
