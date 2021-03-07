#El Presidente

##Introduction

You play as a dictator on a tropical island, newly elected as President. 
You will have the heavy task of making this new mini republic prosper through different events

##Github

You have a github link, below, in order to take the project:

-<a href="https://github.com/JessyAL/Projet_El_Presidente"> https://github.com/JessyAL/Projet_El_Presidente</a>

##Class and functions

Below is the list of classes and functions that I use.

### Class Information

The Information class concerns everything relating to information (Agriculture, industry, etc.).

	public long [] initRessource(JSONObject normal ,long [] informations, boolean isSandBox)

This function initializes the array passed as a parameter and returns it.

	public long [] initFactionSatisfaction(JSONObject factions, long [] factionSatisfaction, boolean isSandBox)

This function initializes the array passed as a parameter and returns it.

	public long [] initFactionPartisans(JSONObject factions, long [] factionPartisans, boolean isSandBox)

This function initializes the array passed as a parameter and returns it.

	public long satisfactionTotal(long [] factionSatisfaction, long [] factionPartisans)

This function calculates the total satisfaction using the subject's formula.

	public long partisansTotal(long [] factionPartisans)

This function calculates the total number of partisans.

	public String toString(long [] informations)

This function returns the resources that the player has.

	public void winOrLoseMessage(long satisfaction)

This function displays the message of victory or defeat.

	public String factions(int factionID)

This function returns the name of the faction corresponding to his ID.

### Class Stories	

	public void getStories()

The function shows the different stories available.

	public String storyChoice(int storyID)

Get the story ID number and return the matching story name.

### Class JSONReader

The JSONreader class extracts information from JSON file.

	public void storyIntro(JSONObject jsonObject)

The function shows the opening sentence of the story.

	public String storyIndentification(String story)

The function allows you to correspond the chosen story with the file name.

	public void eventName(JSONObject jsonObject, int index)

The function shows an event.

	public int numberOfEvents (JSONArray events)

The function allows you to have the number of events.

	public void choices(JSONArray choices)

The function shows the different possible choices of the event.

	public String choiceResult(JSONArray effect, int choiceIndex)

The function displays the different costs of the choices.

	public void relatedEvent(JSONArray relatedEvents)

The function shows a related event.

	public long choiceEffects(JSONArray choices, int choice, String ressource)

This function collects the value that corresponds to the key and returns it.

	public long [] factionSatisfaction(JSONArray choices, int choice, long [] factionSatisfaction)

This function collects and adds or subtracts the retrieved values from the array passed as a parameter and returns it.

	public long [] factionPartisans(JSONArray choices, int choice, long [] factionPartisans)

This function collects and adds or subtracts the retrieved values from the array passed as a parameter and returns it.

	public String factions(int factionID)

This function returns the name of the faction corresponding to his ID.
