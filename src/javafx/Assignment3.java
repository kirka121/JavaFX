package javafx;

public class Assignment3{

	//constructor
	public Assignment3(){}

	public static void main (String[] argv) {
		//draw the program's header
		Header header = new Header();
		System.out.print(header.draw_header("Assignment 3 - LOTR Battlefield", "Kirill Afanasenko", "CST8284", "Christopher Elliott, Rex Woolland", 78, 9, "#"));
 /*
		//make random number(1-1000) of random actors.
		Army random_army = new Army(1);
		System.out.println(random_army.actor.toString() + "\n");

		//make specific number of specific actors. can add specific armies maually from code, or call Army(2) to get a user entry prompt
		Army random_army2 = new Army("Wizard", 10);
		System.out.println(random_army2.actor.toString() + "\n");

		//make actors manually with keyboard.
		Army random_army3 = new Army(1);
		random_army3.displayArmy();
		//System.out.println(random_army3.actor.toString() + "\n");

		/*
		Army random_army = new Army("Wizard", 20);
		System.out.println(random_army.actor.toString() + "\n");
		Army random_army2 = new Army("Elf", 20);
		System.out.println(random_army2.actor.toString() + "\n");
		*/

		//testing boolean inputs so there wont be errors.
		/*
		Boolean kirka = Input.referenceToSingleInputObject.getBoolean("boolean test");
		System.out.println(kirka);
		*/

		//make default army.
		Army random_army4 = new Army();
		do{
			random_army4.getUserDecision();
		} while (true);
		//System.out.println(random_army4.actor.toString() + "\n");

	}
}

// put official title page
