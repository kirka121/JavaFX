package javafx;

public class Human extends Actor{

	private Boolean bHasSword;
	private Boolean bSwordLegendary;
	private Integer nHealingPotions;
	private Integer nGPSCharge; //a gps charge allows human to teleport from 1 area of map to another, whether it is for defense or offense, doesnt matter

	//default constructor
	public Human(){}

	//overwriten constructor
	public Human(Integer sequence){
		super(sequence);
        super.point_x = (int)SingletonRandom.instance.getNormalDistribution(1.0,299.0,4.0);
        super.point_y = (int)SingletonRandom.instance.getNormalDistribution(1.0,119.0,4.0);
	}

	//add to set funtionalit of super class
	@Override
	public void set(){
		super.set("Human");
		bHasSword = Input.referenceToSingleInputObject.getBoolean("Does human have sword");
		bSwordLegendary = Input.referenceToSingleInputObject.getBoolean("Is the sword legendary");
		nHealingPotions = Input.referenceToSingleInputObject.getInt("Please enter the number of HP pots", 1, 10);
		nGPSCharge = Input.referenceToSingleInputObject.getInt("Please enter the number of GPS charges", 1, 3);
	}

	//add to init funtionalit of super class
	@Override
	public void init(String a_class){
		super.init(a_class);
		this.nHealingPotions = (int)SingletonRandom.instance.getNormalDistribution(1.0,10.0,2.0);
		this.nGPSCharge = (int)SingletonRandom.instance.getNormalDistribution(1.0,3.0,2.0);
		this.bHasSword = SingletonRandom.instance.getRandomBool();
		this.bSwordLegendary = SingletonRandom.instance.getRandomBool();
	}
	
	//print things for testing
	@Override
	public String toString() {
		String output = super.toString();
		output += "\nSword: \t\t";
		output += bHasSword ? "equipped" : "not equipped";
		output += "\nSword Type: \t";
		output += bSwordLegendary ? "legendary" : "regular";
		output += "\nHP Pots: \t";
		output += nHealingPotions;
		output += "\nGPS Charges: \t";
		output += nGPSCharge;
		return output;
	}
}
