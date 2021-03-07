package el_presidente;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MainClass {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
		Scanner scan = new Scanner(System.in);
		JSONParser parser = new JSONParser();		

		Stories stories = new Stories();
		JSONReader jsonReader = new JSONReader();		
		Information information = new Information();		
		
		String storyName;	
		int gameMode;
		
		int storyID;
		int choice;
		int eventIndex = 0;
		int numberoOfEvents = 0;
		int limitAgriculturIndustry = 100;	
		
		/*
		 * index 0 = agriculture
		 * index 1 = industry
		 * index 2 = treasury
		 * index 3 = food
		 * index 4 = satisfactionTOTAL
		 * index 5 = partisansTOTAL
		 */
		long [] informations = {0, 0, 0, 0, 0, 0};
		
		/*
		 * index 0 = CAPITALISTS
		 * index 1 = COMMUNISTS
		 * index 2 = LIBERALS
		 * index 3 = RELIGIOUS
		 * index 4 = MILITARISTS
		 * index 5 = ECOLOGISTS
		 * index 6 = LOYALISTS
		 * index 7 = NATIONALSITS
		 * 
		 */
		long [] factionSatisfaction = {0, 0, 0, 0, 0, 0, 0, 0};
		
		/*
		 * index 0 = CAPITALISTS
		 * index 1 = COMMUNISTS
		 * index 2 = LIBERALS
		 * index 3 = RELIGIOUS
		 * index 4 = MILITARISTS
		 * index 5 = ECOLOGISTS
		 * index 6 = LOYALISTS
		 * index 7 = NATIONALSITS
		 * 
		 */
		long [] factionPartisans = {0, 0, 0, 0, 0, 0, 0, 0};
		
		System.out.println("Bienvenue sur le jeu El Presidente !");		
		
		//STORY CHOICE					
		do {
			System.out.println("Veuillez choisir une histoire parmis les suivantes en entrant le chiffre correspondant :");			
			stories.getStories();	
			System.out.print("Réponse : ");
			storyID = scan.nextInt();			
		}while(storyID < 1 || storyID > 3 );
		
		storyName = stories.storyChoice(storyID);			
		System.out.println("Vous avez choisi l'histoire : " + storyName + "\n");		
				
		//Extraction JSON file informations
		Object obj =  parser.parse(new FileReader("./events/"+ jsonReader.storyIndentification(storyName)+".json"));			
		JSONObject jsonObject = (JSONObject) obj;				
		JSONObject gameStartParameters = (JSONObject) jsonObject.get("gameStartParameters");	
		JSONObject normal = (JSONObject) gameStartParameters.get("NORMAL");	
		JSONObject factions = (JSONObject) normal.get("factions");			
		JSONArray events = (JSONArray) jsonObject.get("events");
		
		//Game mode choice
		System.out.println("Selectionnez le mode de jeu souhaité :");
		System.out.println("1- bac à sable");
		System.out.println("2- scénario");
		System.out.print("Réponse : ");
		gameMode = scan.nextInt();		
		
		//information initialization
		if(gameMode == 1) {
			informations = information.initRessource(normal, informations, true);
			factionSatisfaction = information.initFactionSatisfaction(factions, factionSatisfaction, true);
			factionPartisans = information.initFactionPartisans(factions, factionPartisans, true);			
 		}else {			
			informations = information.initRessource(normal, informations, false);
			factionSatisfaction = information.initFactionSatisfaction(factions, factionSatisfaction, false);
			factionPartisans = information.initFactionPartisans(factions, factionPartisans, false);			
		}		
		
		informations[4] = information.satisfactionTotal(factionSatisfaction, factionPartisans);
		informations[5] = information.partisansTotal(factionPartisans);
		
		//
		jsonReader.storyIntro(jsonObject);
		
		System.out.println(information.toString(informations));
		
		numberoOfEvents = jsonReader.numberOfEvents(events);
		
		//gameMode = 1 -> sandBox mode		gameMode = 2 -> scenario mode
		if(gameMode == 1) {
			System.out.println("Vous avez choisi le mode bac à sable. Vous pouvez arréter la partie en tapant \"0\" !\n");
			
			do {			
				
				eventIndex = (int)(Math.random() * events.size()) ;
				
				JSONObject event = (JSONObject) events.get(eventIndex);
				
				JSONArray choices = (JSONArray) event.get("choices");
				
				do {
					jsonReader.eventName(jsonObject, eventIndex);
					jsonReader.choices(choices);
					System.out.print("Réponse : ");
					choice = scan.nextInt();
				}while(choice < 0 || choice > choices.size());
				
				if(choice != 0) {
							
					informations[0] += jsonReader.choiceEffects(choices, choice, "AGRICULTURE");
					
					if(informations[0] + informations[1] > limitAgriculturIndustry) {
						informations[0] -=  informations[0] +informations[1] - limitAgriculturIndustry;
					}
					
					informations[1] += jsonReader.choiceEffects(choices, choice, "INDUSTRY");
					
					if(informations[0] + informations[1] > limitAgriculturIndustry) {
						informations[1] -=  (informations[0] +informations[1]) - limitAgriculturIndustry;
					}
					
					informations[2] += jsonReader.choiceEffects(choices, choice, "TREASURY");
					informations[3] += jsonReader.choiceEffects(choices, choice, "foodUnits"); 
					
					factionSatisfaction = jsonReader.factionSatisfaction(choices, choice, factionSatisfaction);
					factionPartisans = jsonReader.factionPartisans(choices, choice, factionPartisans);
					informations[4] = information.satisfactionTotal(factionSatisfaction, factionPartisans);
					
					informations[5] = information.partisansTotal(factionPartisans);
					
					System.out.println(information.toString(informations));					
				}
				
			}while(informations[4] >= 10 && choice != 0);
			
			if(choice != 0) {
				information.winOrLoseMessage(informations[4]);
			}
		}else {
			do {			
				JSONObject event = (JSONObject) events.get(eventIndex);
				
				JSONArray choices = (JSONArray) event.get("choices");
				
				do {
					jsonReader.eventName(jsonObject, eventIndex);
					jsonReader.choices(choices);
					System.out.print("Réponse : ");
					choice = scan.nextInt();
				}while(choice < 1 || choice > choices.size());			
				
				informations[0] += jsonReader.choiceEffects(choices, choice, "AGRICULTURE");				
				if(informations[0] + informations[1] > limitAgriculturIndustry) {
					informations[0] -=  informations[0] +informations[1] - limitAgriculturIndustry;
				}				
				
				informations[1] += jsonReader.choiceEffects(choices, choice, "INDUSTRY");
				
				if(informations[0] + informations[1] > limitAgriculturIndustry) {
					informations[1] -=  (informations[0] +informations[1]) - limitAgriculturIndustry;
				}
				
				informations[2] += jsonReader.choiceEffects(choices, choice, "TREASURY");
				informations[3] += jsonReader.choiceEffects(choices, choice, "foodUnits"); 				
				factionSatisfaction = jsonReader.factionSatisfaction(choices, choice, factionSatisfaction);
				factionPartisans = jsonReader.factionPartisans(choices, choice, factionPartisans);
				informations[4] = information.satisfactionTotal(factionSatisfaction, factionPartisans);			
				informations[5] = information.partisansTotal(factionPartisans);					
				
				System.out.println(information.toString(informations));
				
				eventIndex++;
			}while(informations[4] >= 10 && eventIndex < numberoOfEvents);
			
			information.winOrLoseMessage(informations[4]);
		}	
		
		System.out.println("La partie est termine ! ");
		
		scan.close();
	}
}