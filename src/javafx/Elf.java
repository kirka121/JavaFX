package javafx;

public class Elf extends Actor{

	private Boolean bHasBow;
	private Boolean bArrowsBlessed;
	private Integer nNumArrows;

	//default constructor
	public Elf(){}

	//overwriten constructor
	public Elf(Integer sequence){
		super(sequence);
	}

	//add to set funtionalit of super class
	@Override
	public void set(){
		super.set("Elf");
		bHasBow = Input.referenceToSingleInputObject.getBoolean("Does elf have bow");
		bArrowsBlessed = Input.referenceToSingleInputObject.getBoolean("Are the arrows blessed");
		nNumArrows = Input.referenceToSingleInputObject.getInt("Please enter the number of arrows", 1, 100);
	}

	//add to init funtionalit of super class
	@Override
	public void init(String a_class){
		super.init(a_class);
		this.nNumArrows = (int)SingletonRandom.instance.getNormalDistribution(1.0,100.0,2.0);
		this.bHasBow = SingletonRandom.instance.getRandomBool();
		this.bArrowsBlessed = SingletonRandom.instance.getRandomBool();
	}
	
	//print things for testing
	@Override
	public String toString() {
		String output = super.toString();
		output += "\nbow: \t\t";
		output += bHasBow ? "equipped" : "not equipped";
		output += "\narrows: \t";
		output += bArrowsBlessed ? "blessed" : "not blessed";
		output += "\namount: \t";
		output += nNumArrows;
		return output;
	}
}
