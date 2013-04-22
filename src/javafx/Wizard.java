package javafx;

public class Wizard extends Actor{

	private Boolean bHasStaff;
	private Boolean bHasHorse;

	//default constructor
	public Wizard(){}

	//overwritten constructor
	public Wizard(Integer sequence){
		super(sequence);
        super.a_class = 2;
        super.point_x = (int)SingletonRandom.instance.getNormalDistribution(301.0,596.0,4.0);
        super.point_y = (int)SingletonRandom.instance.getNormalDistribution(120.0,236.0,4.0);
	}

	//add to set funtionalit of super class
	@Override
	public void set(){
		super.set("Wizard");
		bHasStaff = Input.referenceToSingleInputObject.getBoolean("Does wizard have staff");
		bHasHorse = Input.referenceToSingleInputObject.getBoolean("Does wizard have horse");
	}

	//add to init funtionalit of super class
	@Override
	public void init(String a_class){
		super.init(a_class);
		this.bHasHorse = SingletonRandom.instance.getRandomBool();
		this.bHasStaff = SingletonRandom.instance.getRandomBool();
	}
	
	//print things for testing
	@Override
	public String toString() {
		String output = super.toString();
		output += "\nstaff: \t\t";
		output += bHasStaff ? "equipped" : "not equipped";
		output += "\nhorse: \t\t";
		output += bHasHorse ? "equipped" : "not equipped";
		return output;
	}
}



//check return value on booleans - fixed
//do assertions for bonus marks
//do javadoc.

