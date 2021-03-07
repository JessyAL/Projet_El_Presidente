package el_presidente_test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import el_presidente.Information;

class TestInformation {

	long [] informations = {0, 0, 0, 0, 0, 0};
	
	@Test
	void test_InitInformation_File_AttackOnTitan() {
		
		Information information = new Information();
		informations = information.initRessource(fileStory("attackOnTitans"),informations, false);
		assertEquals(40, informations[0]);
		assertEquals(35, informations[1]);
		assertEquals(700, informations[2]);
		assertEquals(500, informations[3]);		
	}
	
	@Test
	void test_InitInformation_File_ColdWarUSA() {
		
		Information information = new Information();
		informations = information.initRessource(fileStory("coldWarUSA"),informations,false);
		assertEquals(25, informations[0]);
		assertEquals(35, informations[1]);
		assertEquals(1000, informations[2]);
		assertEquals(1000, informations[3]);		
	}
	
	@Test
	void test_InitInformation_File_ColdWarUSSR() {
		
		Information information = new Information();
		informations = information.initRessource(fileStory("coldWarUSSR"),informations, false);
		assertEquals(45, informations[0]);
		assertEquals(25, informations[1]);
		assertEquals(800, informations[2]);
		assertEquals(1000, informations[3]);
	}

	@Test
	void test_InitInformation_File_AttackOnTitan_SandBox_Mode() {
		
		Information information = new Information();
		informations = information.initRessource(fileStory("attackOnTitans"),informations, true);
		assertEquals(15, informations[0]);
		assertEquals(15, informations[1]);
		assertEquals(200, informations[2]);
		assertEquals(500, informations[3]);		
	}
	
	
	@Test
	void test_InitFactionSatisfaction_AttackOnTitan() {
		
		Information information = new Information();
		informations = information.initFactionSatisfaction(fileStory("attackOnTitans"), informations, false);
		assertEquals(60, informations[0]);
		assertEquals(60, informations[1]);
		assertEquals(60, informations[2]);
		assertEquals(60, informations[3]);
		assertEquals(50, informations[4]);
		assertEquals(60, informations[5]);
		assertEquals(100, informations[6]);
		assertEquals(60, informations[7]);
		
	}
	
	@Test
	void test_InitFactionSatisfaction_AttackOnTitan_SandBox_Mode() {
		
		Information information = new Information();
		informations = information.initFactionSatisfaction(fileStory("attackOnTitans"), informations, true);
		assertEquals(50, informations[0]);
		assertEquals(50, informations[1]);
		assertEquals(50, informations[2]);
		assertEquals(50, informations[3]);
		assertEquals(50, informations[4]);
		assertEquals(50, informations[5]);
		assertEquals(100, informations[6]);
		assertEquals(50, informations[7]);
		
	}
	
	private JSONObject fileStory(String file) {
		
		JSONParser parser = new JSONParser();
		Object obj = null;
		
		try {
			obj = parser.parse(new FileReader("./events/"+file+".json"));
		} catch (IOException | ParseException e) { e.printStackTrace();}	
		
		JSONObject jsonObject = (JSONObject) obj;		
		
		return jsonObject;
		
	}
	
	
	
	
}
