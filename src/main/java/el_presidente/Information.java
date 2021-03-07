package el_presidente;

import org.json.simple.JSONObject;

public class Information {	
	
	/**
	 * This function initializes the array passed as a parameter and returns it
	 * 
	 * @param normal
	 * @param informations
	 * @param isSandBox
	 * @return the array initialized
	 */
	public long [] initRessource(JSONObject normal ,long [] informations, boolean isSandBox) {			
			if(isSandBox) {
				informations[0] = 15;
				informations[1] = 15;
				informations[2] = 200;
				informations[3] = 500;
			}else {
				informations[0] = (long) normal.get("agriculturePercentage");				
				informations[1] = (long) normal.get("industryPercentage");				
				informations[2] = (long) normal.get("treasury");				
				informations[3] = (long) normal.get("foodUnits");
			}			
		return informations;
	}
	
	/**
	 * This function initializes the array passed as a parameter and returns it
	 * 
	 * @param factions
	 * @param factionSatisfaction
	 * @param isSandBox
	 * @return the array initialized
	 */
	public long [] initFactionSatisfaction(JSONObject factions, long [] factionSatisfaction, boolean isSandBox) {
		
		for(int i = 0; i < 8; i++) {	
				
			if(isSandBox) {
				if(i == 6) {
					factionSatisfaction[i] = 100;	
				}else {
					factionSatisfaction[i] = 50;
				}		
			}else {
				JSONObject factionInit = (JSONObject) factions.get(factions(i));
					
				factionSatisfaction[i] = (long) factionInit.get("satisfactionPercentage");
			}				
		}						
		return factionSatisfaction;
	}
	
	/**
	 * This function initializes the array passed as a parameter and returns it
	 * 
	 * @param factions
	 * @param factionPartisans
	 * @param isSandBox
	 * @return the array initialized
	 */
	public long [] initFactionPartisans(JSONObject factions, long [] factionPartisans, boolean isSandBox) {
		for(int i = 0; i < 8; i++) {			
			
			if(isSandBox) {
				factionPartisans[i] = 15;
			}else {
				JSONObject factionInit = (JSONObject) factions.get(factions(i));
				
				factionPartisans[i] = (long) factionInit.get("numberOfPartisans");			
			}				
		}				
		return factionPartisans;
	}
	
	/**
	 * This function calculates the total satisfaction using the subject's formula and returns the result
	 * 
	 * @param factionSatisfaction
	 * @param factionPartisans
	 * @return the result
	 */
	public long satisfactionTotal(long [] factionSatisfaction, long [] factionPartisans) {		
		long numerateur = 0;
		long denominateur = 0;
		
		for(int i = 0; i < factionPartisans.length; i++) {
			numerateur += factionSatisfaction[i] * factionPartisans[i];
			denominateur += factionPartisans[i];
		}
		
		return numerateur / denominateur;
	}
	
	/**
	 * This function calculates the total number of partisans
	 * 
	 * @param factionPartisans
	 * @return the result
	 */
	public long partisansTotal(long [] factionPartisans) {
		long partisansTotal = 0;
		for(int i = 0; i < 8; i++) {
			partisansTotal += factionPartisans[i];
		}
		return partisansTotal;
	}
	
	/**
	 * This function returns the resources that the player has
	 * 
	 * @param informations
	 * @return the sentence
	 */
	public String toString(long [] informations) {
		return "Vous avez : "+informations[0]+"% d'agriculture, "+informations[1]+"% d'industrialisation, "+informations[2]+" de trésorie ,"+informations[3]+" de nourriture, "+informations[4]+"% de satisfaction et "+informations[5]+" de partisans !\n";
	}
	
	/**
	 * This function displays the message of victory or defeat
	 * 
	 * @param satisfaction
	 */
	public void winOrLoseMessage(long satisfaction) {
		if(satisfaction< 10) {
			System.out.println("Vous n'avez plus de soutient, vous avez perdu !");
		}else {
			System.out.println("Vous avez réussi à garder la confiance de votre nation, VICTOIRE");
		}		
	}
	
	/**
	 * This function returns the name of the faction corresponding to his ID
	 * 
	 * @param factionID
	 * @return the faction
	 */
	public String factions(int factionID) {
		String factionName= "";
		switch(factionID) {
			case 0:
				factionName = "CAPITALISTS";
				break;
			case 1: 
				factionName = "COMMUNISTS";
				break;
			case 2:
				factionName = "LIBERALS";
				break;
			case 3:
				factionName = "RELIGIOUS";
				break;
			case 4:
				factionName = "MILITARISTS";
				break;
			case 5:
				factionName = "ECOLOGISTS";
				break;
			case 6:
				factionName = "LOYALISTS";
				break;
			case 7:
				factionName = "NATIONALISTS";
				break;			
		}		
		return factionName;
	}
}
