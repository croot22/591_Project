package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OutdoorActivityTester {

	@Test
	void SelectActivityTest() {

		OutdoorActivity.sailing();
		assertEquals(75,OutdoorActivity.getBestTemperatureMax());
		OutdoorActivity.hiking();
		assertEquals(68,OutdoorActivity.getBestTemperatureMax());
		OutdoorActivity.biking();
		assertEquals(72,OutdoorActivity.getBestTemperatureMax());
		OutdoorActivity.climbing();
		assertEquals(72,OutdoorActivity.getBestTemperatureMax());
		OutdoorActivity.skiing();
		assertEquals(25,OutdoorActivity.getBestTemperatureMax());
	}

}
