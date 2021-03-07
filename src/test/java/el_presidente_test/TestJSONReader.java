package el_presidente_test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import el_presidente.JSONReader;

class TestJSONReader {

	@Test
	void testStoryIndentification() {
		JSONReader jsonReader = new JSONReader();
		String storyFileName = jsonReader.storyIndentification("L'attaque des titans");
		assertEquals("attackOnTitans", storyFileName);
		
		storyFileName = jsonReader.storyIndentification("Guerre froide USA");
		assertEquals("coldWarUSA", storyFileName);
	}
	
	@Test
	void testGetNumberOfEventsAttackOnTitans() {
		JSONReader jsonReader = new JSONReader();
		int numberOfEvent = jsonReader.numberOfEvents(tabEvents(fileStory("attackOntitans")));
		assertEquals(18, numberOfEvent);
	}
	
	@Test
	void testGetNumberOfEventsColdWarUSA() {
		JSONReader jsonReader = new JSONReader();
		int numberOfEvent = jsonReader.numberOfEvents(tabEvents(fileStory("coldWarUSA")));
		assertEquals(6, numberOfEvent);
	}
	
	@Test
	void testGetNumberOfEventsColdWarUSSR() {
		JSONReader jsonReader = new JSONReader();
		int numberOfEvent = jsonReader.numberOfEvents(tabEvents(fileStory("coldWarUSSR")));
		assertEquals(6, numberOfEvent);
	}
	
	/*@Test
	void testChoiceEffects() {
		JSONReader jsonReader = new JSONReader();
		long valueAgriculture = jsonReader.choiceEffects(fileStory("attackOnTitans"), 1, "AGRICULTURE");
		assertEquals(30, valueAgriculture);
		
		long valueTreasury= jsonReader.choiceEffects(fileStory("attackOnTitans"), 1, "TREASURY");
		assertEquals(-30, valueTreasury);
		
		long valueSatisfaction = jsonReader.choiceEffects(fileStory("attackOnTitans"), 1, "RELIGIOUS");
		assertEquals(-20,valueSatisfaction);
	}*/
	
	
	private JSONObject fileStory(String file) {
		
		JSONParser parser = new JSONParser();
		Object obj = null;
		
		try {
			obj = parser.parse(new FileReader("./events/"+file+".json"));
		} catch (IOException | ParseException e) { e.printStackTrace();}	
		
		JSONObject jsonObject = (JSONObject) obj;		
		
		return jsonObject;
		
	}

	private JSONArray tabEvents(JSONObject jsonObject) {
		return (JSONArray) jsonObject.get("events");
	}
}
