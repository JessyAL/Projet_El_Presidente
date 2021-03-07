package el_presidente_test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import el_presidente.Stories;

class TestStories {

	@Test
	void test_StoryChoice() {
		Stories stories = new Stories();
		String storyName = stories.storyChoice(1);
		assertEquals("L'attaque des titans", storyName);
		
		storyName = stories.storyChoice(3);
		assertEquals("Guerre froide URSS", storyName);
	}
	
	@Test
	void test_StoryChoice_With_Wrong_Number() {
		Stories stories = new Stories();
		String storyName = stories.storyChoice(8);
		assertEquals("Erreur, le chiffre entré n'est pas dans la liste !", storyName);
		
	}

}
