package javafx;

import java.util.ArrayList;

public class Army implements java.io.Serializable{
	public ArrayList<Actor> actor;	// holds our actors
	public Integer type_of_actor;	//holds the type of actor  7 - mixed,
	public Integer num_of_actors;	//holds the number of actors to create
	public String name_of_army; 	//holds the name of this army
    public String army_color;      //holds the color of our army
	private ArmyDisplay show_army = new ArmyDisplay();

	//no arguments constructor. asks what you would like to do.
	public Army(){
		//passes action to what_do constructor
		this(Input.referenceToSingleInputObject.getInt("You are trying to make an army. Do you want to:\n[1] - generate random army\n[2] - generate specific army\n[3] - manually input everything\n", 1, 3),Input.referenceToSingleInputObject.getString("Army name: "), "#000000");
	}

	//figure out what type of army to add
	public Army(Integer what_do, String name_army, String color){
            this.name_of_army = name_army;
            this.army_color = color;
		switch(what_do){
			case 1:
				addRandom();
				break;
			case 2:
				addSpecific();
				break;
			case 3:
				addManual();
				break;
			default:
				addRandom();
				break;
		}
	}

	//constructor for adding specific types of armies.
	public Army(String type, Integer amount, String name_army, String color){
            this.name_of_army = name_army;
            this.army_color = color;
            addSpecific(type, amount);
	}

	//decide on action
	public void Decide(Integer what_do){
		switch(what_do){
			case 1:
				addRandom();
				break;
			case 2:
				addSpecific();
				break;
			case 3:
				addManual();
				break;
			case 4:
				displayArmy();
				break;
			case 5:
				editArmy();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				addRandom();
				break;
		}
	}

	//ask user what the wants to do.
	public void getUserDecision(){
		Integer result = Input.referenceToSingleInputObject.getInt("Done... What now?:\n[1] - generate random army\n[2] - generate specific army\n[3] - manually input everything\n[4] - display my army\n[5] - edit my army\n[6] - quit\n", 1, 6);
		this.Decide(result);
	}

	//edit army settings
	public void editArmy(){
		Integer a_seq = Input.referenceToSingleInputObject.getInt("Which creature ID do you want to modify", 0, actor.size());
		Actor a = actor.get(a_seq-1);
		System.out.println(show_army.draw_army(a.aName, Integer.toString(a.aStrength), Integer.toString(a.aSpeed), Integer.toString(a.aHealth) ,60, "|"));
		a.set();	
	}

	//display army
	public void displayArmy(){
		Integer a_size = actor.size();
		String a_type;
		Integer avrg_power = 0;
		//determine type of this army
		switch(type_of_actor){
			case 1: a_type = "Hobbit"; break;
			case 2: a_type = "Wizard"; break;
			case 3: a_type = "Elf"; break;
			case 4: a_type = "Human"; break;
			case 7: a_type = "Mixed"; break;
			default: a_type = "Unknown"; break;
		}
		//calculate average strength of this army
		for(int i = 0; i < a_size; i++){
			Actor z = actor.get(i);
			avrg_power += z.aStrength;
		}
		avrg_power = avrg_power/a_size;
		//print out header
		System.out.println(show_army.draw_title(name_of_army,Integer.toString(a_size),a_type,Integer.toString(avrg_power),70,6,"*"));
		//ask if want to print out army
		Integer print_whole_army = Input.referenceToSingleInputObject.getInt("display creatures", 0, 1);
		if(print_whole_army == 1){
			System.out.print("\n\n");
			System.out.println(show_army.draw_header("Name", "Strength", "Speed", "Health" ,60,"|"));

			//iterate through the army
			for(int i = 0; i < a_size; i++){
				Actor x = actor.get(i);
				System.out.println(show_army.draw_army(x.aName, Integer.toString(x.aStrength), Integer.toString(x.aSpeed), Integer.toString(x.aHealth) ,60, "|"));
			}
		}
	}
	//add an army of random size with random creatures with random values.
	private void addRandom(){
		actor = new ArrayList<Actor>();
		System.out.println("Generating Random Army");
		//name_of_army = set_army_name();
		//get random nubmer of actors to make
		num_of_actors = (int)SingletonRandom.instance.getNormalDistribution(1.0,1000.0,2.0);

		//iterate through the actors.
		for(int i = 0;i < num_of_actors;i++){
		//figure out what type of actor to make this time
		type_of_actor = (int)SingletonRandom.instance.getNormalDistribution(1.0,5.0,1.0);
			//depending on type of actor, uses different generation statement.
			switch (type_of_actor){
				case 1:  
				//make hobbits
					actor.add(new Hobbit(i));
					Actor a = actor.get(i);
					a.init("Hobbit");
				break;
				case 2: 
				//make wizards
					actor.add(new Wizard(i));
					Actor b = actor.get(i);
					b.init("Wizard"); 
				break;
				case 3:
				//make elves
					actor.add(new Elf(i));
					Actor c = actor.get(i);
					c.init("Elf");
				break;
				case 4:
				// make humans
					actor.add(new Human(i));
					Actor d = actor.get(i);
					d.init("Human"); 
				break;
				default:
				//if weird integer entered, make hobbits.
					actor.add(new Hobbit(i));
					Actor e = actor.get(i);
					e.init("Hobbit");
				break;
			}
		}
                System.out.println("Random Army Generated");
		type_of_actor = 7; 
	}
	
	//if called addSpecific without arguments, get arguments.
	private void addSpecific(){
		System.out.println("Generating Specific Army");
		Integer num_o_creatures = Input.referenceToSingleInputObject.getInt("How many actors do you want to create", 1, 1000);
		String t_o_creature = Input.referenceToSingleInputObject.getString("Creature type: ");
		this.addSpecific(t_o_creature, num_o_creatures);
	}

	//will generate specific amount of specific type of actors and place them in actor array list
	private void addSpecific(String type, Integer amount){
                actor = new ArrayList<Actor>();
		//determine numerical type based on input name;
		switch(type){
			case "Hobbit": type_of_actor = 1; break;
			case "Wizard": type_of_actor = 2; break;
			case "Elf": type_of_actor = 3; break;
			case "Human": type_of_actor = 4; break;
			default: type_of_actor = 1; break;
		}
		num_of_actors = amount;
		for(int i = 0;i < num_of_actors;i++){
		//depending on type of actor, uses different generation statement.
			switch (type_of_actor){
				case 1: 
				//make hobbits 
					actor.add(new Hobbit(i));
					Actor a = actor.get(i);
					a.init("Hobbit");
				break;
				case 2: 
				//make wizards
					actor.add(new Wizard(i));
					Actor b = actor.get(i);
					b.init("Wizard");
				break;
				case 3:
				//make elves
					actor.add(new Elf(i));
					Actor c = actor.get(i);
					c.init("Elf");
				break;
				case 4:
				//make humans
					actor.add(new Human(i));
					Actor d = actor.get(i);
					d.init("Human");
				break;
				default:
				//if weird integer entered, made hobbits.
					actor.add(new Hobbit(i));
					Actor e = actor.get(i);
					e.init("Hobbit");
				break;
			}
		}
	}


	//will generate keyboard input actors, store them into actor array
	private void addManual(){
		actor = new ArrayList<Actor>();
		System.out.println("Generating Manual Army");
		//ask user how many of these actors he wants
		Integer amount = Input.referenceToSingleInputObject.getInt("How many actors do you want to create", 1, 100);

		//itterate through creation
		for(int i = 0; i<amount;i++){
			//ask user what actors he wants to make
			String type = Input.referenceToSingleInputObject.getString("Creature type: ");
			//select appropriate addition statement
			switch(type) {
				case "Hobbit": 
					System.out.println("\n Making a hobbit");
					actor.add(new Hobbit(i));
				break;
				case "Wizard": 
					System.out.println("\n Making a wizard");
					actor.add(new Wizard(i));
				break;
				case "Elf": 
					System.out.println("\n Making an elf");
					actor.add(new Elf(i));
				break;
				case "Human": 
					System.out.println("\n Making a human");
					actor.add(new Human(i));
				break;
				default: 
					System.out.println("\n Making a hobbit");
					actor.add(new Hobbit(i));
				break;
			}
			Actor a = actor.get(i);
			a.set(); //invoke original manual setters
			System.out.println(" Made actor. \n");
		}
		type_of_actor = 5; // unknown
	}
}

//make the factory separate