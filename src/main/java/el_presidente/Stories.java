package el_presidente;

public class Stories {
	
	/**
	 * Shows the different stories available
	 */
	public void getStories() {		
		System.out.println("1. L'attaque des titans");
		System.out.println("2. Guerre froide USA");
		System.out.println("3. Guerre froide URSS");		
		System.out.println("------------------------------------------------");
	}
	
	/**
	 * Get the story ID number and return the matching story name
	 * 
	 * @param factionID
	 * @return the story name
	 */
	public String storyChoice(int storyID) {
		String storyName;
		switch(storyID) {
			case 1:
				storyName = "L'attaque des titans";
				break;
			case 2: 
				storyName = "Guerre froide USA";
				break;
			case 3:
				storyName = "Guerre froide URSS";
				break;
			default:
				return "Erreur, le chiffre entré n'est pas dans la liste !";
		}		
		return storyName;
	}
}
