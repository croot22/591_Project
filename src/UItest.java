import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UItest {

	@Test
	void test() {
		
		UserInterface ui = new UserInterface();
		//ui.editList("weather.json");
		ui.createNewList();
		assertEquals(1, 1);
		
	}

}
