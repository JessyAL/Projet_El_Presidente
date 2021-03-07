package el_presidente;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONReader {
	
	/**
	 * Show the opening sentence of the story
	 * 
	 * @param jsonObject
	 */
	public void storyIntro(JSONObject jsonObject) {		
			
			String storyIntro = (String) jsonObject.get("story");
			
			System.out.println(storyIntro + "\n");				
	}
	
	/**
	 * Allows you to correspond the chosen story with the file name
	 * 
	 * @param story
	 * @return the file name
	 */
	public String storyIndentification(String story) {
		
		String storyFileName = "";
		if(story.equals("L'attaque des titans")) {			
			storyFileName = "attackOnTitans";
		}else if(story.equals("Guerre froide USA")) {
			storyFileName = "coldWarUSA";
		}else if(story.equals("Guerre froide URSS")) {
			storyFileName = "coldWarUSSR";
		}
		
		return storyFileName;
	}

	/**
	 * Show an event
	 * 
	 * @param jsonObject
	 * @param index
	 */
	public void eventName(JSONObject jsonObject, int index) {
			
			JSONArray events = (JSONArray) jsonObject.get("events");			
			
			JSONObject event = (JSONObject) events.get(index);
				
			String name = (String) event.get("name");
				
			System.out.println("event  : " + name);			
	}
	
	/**
	 * Allows you to have the number of events
	 * 
	 * @param jsonObject
	 * @return
	 */
	public int numberOfEvents (JSONArray events) {		
		return events.size();
	}
	
	/**
	 * Show the different possible choices of the event
	 * 
	 * @param choices
	 */
	public void choices(JSONArray choices) {
			System.out.println("choix : \n");
			
			for(int i = 0; i < choices.size(); i++) {
			
				JSONObject choice = (JSONObject) choices.get(i);
			
				String choiceName = (String) choice.get("choice");
				
				JSONObject effects = (JSONObject) choices.get(i);	
				
				JSONArray effect = (JSONArray) effects.get("effects");
			
				System.out.println("\t "+(i+1)+"- " + choiceName + " "+ choiceResult(effect,i) + "\n");
				
				/*if(choice.containsKey("relatedEvents")){
					//System.out.println("NOUS AVONS TROUVER UN EVENT SURPRISE MOUHAHAHAHAHAHAHAHAAH");
					JSONArray relatedEvents = (JSONArray) choice.get("relatedEvents");
					
					
					//System.out.println("testestestestetestsgqfgoqsgeghefquyhsekufyhgbefkugbqsukf ::::::::::::>>>>> "+ relatedEvents);
					
					
					getRelatedEvents(relatedEvents);
					
				}*/
			}		
	}
	
	/**
	 * Displays the different costs of the choices
	 * 
	 * @param effect
	 * @param choiceIndex
	 * @return
	 */
	public String choiceResult(JSONArray effect, int choiceIndex) {
		String choiceCost = "";				
			
			if(effect.size() == 2) {
				JSONObject actionOnFactor = (JSONObject) effect.get(1);
			
				if(actionOnFactor.containsKey("actionOnFactor")) {
				
					JSONObject action = (JSONObject) actionOnFactor.get("actionOnFactor");
				
					if(action.containsKey("AGRICULTURE")) {
						choiceCost += Long.toString((long)action.get("AGRICULTURE")) + "% nourritures ";
					}
				
					if(action.containsKey("INDUSTRY")) {
						choiceCost += Long.toString((long)action.get("INDUSTRY")) + "% industrie ";
					}
				
					if(action.containsKey("TREASURY")) {
						choiceCost += Long.toString((long)action.get("TREASURY")) + " trésorie ";
					}		
				
					if(action.containsKey("PARTISANS")) {
						choiceCost += Long.toString((long)action.get("PARTISANS")) + " partisans";
					}
					
					return "( "+choiceCost+" )";					
				}
			}
		
		return choiceCost;
	}
	
	/**
	 * Show a related event
	 * 
	 * @param relatedEvents
	 */
	public void relatedEvent(JSONArray relatedEvents) {
		
		String choiceCost = "";
		String finalChoiceCost = "";
		
		for(int i = 0; i < relatedEvents.size(); i++) {
			
			JSONObject relatedEventsChoice = (JSONObject) relatedEvents.get(i);
			
			String relatedEventsName = (String) relatedEventsChoice.get("name");
			
			System.out.println("Event : "+ relatedEventsName + "\n");
			
			JSONArray relatedEventChoices = (JSONArray) relatedEventsChoice.get("choices");
			
			for(int j = 0; j < relatedEventChoices.size(); j++) {
				JSONObject relatedEventsChoicesChoice = (JSONObject) relatedEventChoices.get(j);
				
				String  relatedEventsChoicesChoiceName = (String) relatedEventsChoicesChoice.get("choice");
				
				JSONArray relatedEventsChoicesChoiceEffects = (JSONArray) relatedEventsChoicesChoice.get("effects");
				
				if(relatedEventsChoicesChoiceEffects.size() == 2) {
					JSONObject actionOnFactor = (JSONObject) relatedEventsChoicesChoiceEffects.get(1);
				
					if(actionOnFactor.containsKey("actionOnFactor")) {
					
						JSONObject action = (JSONObject) actionOnFactor.get("actionOnFactor");
					
						if(action.containsKey("AGRICULTURE")) {
							choiceCost += Long.toString((long)action.get("AGRICULTURE")) + "% nourritures ";
						}
					
						if(action.containsKey("INDUSTRY")) {
							choiceCost += Long.toString((long)action.get("INDUSTRY")) + "% industrie ";
						}
					
						if(action.containsKey("TREASURY")) {
							choiceCost += Long.toString((long)action.get("TREASURY")) + " trésorie ";
						}		
					
						if(action.containsKey("PARTISANS")) {
							choiceCost += Long.toString((long)action.get("PARTISANS")) + " partisans";
						}
					}
					
					finalChoiceCost = "( " + choiceCost + " )";
				}				
				
				System.out.println("\t" + (1+j) +"- " + relatedEventsChoicesChoiceName + " "+ finalChoiceCost +"\n");
			}
		}
	}
		
	/**
	 * Collects the value that corresponds to the key and returns it
	 * 
	 * @param choices
	 * @param choice
	 * @param ressource
	 * @return
	 */
	public long choiceEffects(JSONArray choices, int choice, String ressource) {
		long returnValue = 0;	
			
			JSONObject choicesChoice = (JSONObject) choices.get(choice - 1);//mettre le parametre choice
			
			JSONArray effects = (JSONArray) choicesChoice.get("effects");			
			
			if(effects.size() == 2) {
				JSONObject actionOnFactor = (JSONObject) effects.get(1);
					
				JSONObject actionOnFactorValue = (JSONObject) actionOnFactor.get("actionOnFactor");
					
				if(actionOnFactorValue.containsKey(ressource)) {
					returnValue = (long) actionOnFactorValue.get(ressource);						
				}
			}
			
		return returnValue;
	}
	
	/**
	 * Collects and adds or subtracts the retrieved values from the array passed as a parameter and returns it
	 * 
	 * @param choices
	 * @param choice
	 * @param factionSatisfaction
	 * @return the modified array
	 */
	public long [] factionSatisfaction(JSONArray choices, int choice, long [] factionSatisfaction) {
		
		JSONObject choicesChoice = (JSONObject) choices.get(choice - 1);
		
		JSONArray effects = (JSONArray) choicesChoice.get("effects");
		
		JSONObject actionOnFaction = (JSONObject) effects.get(0);
		
		JSONObject actionOnFactionValue = (JSONObject) actionOnFaction.get("actionOnFaction");
		
		for(int i = 0; i < 8; i++) {
			if(factionSatisfaction[i] != 0 && actionOnFactionValue.containsKey(factions(i))) {	
				
				factionSatisfaction[i] += (long) actionOnFactionValue.get(factions(i));				
			}	
			
			if(factionSatisfaction[i] < 0) {
				factionSatisfaction[i] = 0;
			}
		}
		return factionSatisfaction;
	}
	
	/**
	 * Collects and adds or subtracts the retrieved values from the array passed as a parameter and returns it
	 * 
	 * @param choices
	 * @param choice
	 * @param factionPartisans
	 * @return the modified array
	 */
	public long [] factionPartisans(JSONArray choices, int choice, long [] factionPartisans) {
		JSONObject choicesChoice = (JSONObject) choices.get(choice - 1);
		
		JSONArray effects = (JSONArray) choicesChoice.get("effects");
		if(effects.size() == 2) {
			JSONObject actionOnFaction = (JSONObject) effects.get(1);
			
			JSONObject actionOnFactionValue = (JSONObject) actionOnFaction.get("actionOnFaction");
			if(actionOnFaction.containsKey("PARTISANS")) {
				for(int i = 0 ; i < 8 ; i++) {
					factionPartisans[i] += (long) actionOnFactionValue.get(factions(i));
				}
			}
		}		
		
		return factionPartisans;
	}
	
	/**
	 * Returns the name of the faction corresponding to his ID.
	 * 
	 * @param factionID
	 * @return
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
