package javafx;

public class Hobbit extends Actor{

	private Integer nStealth;

	//default constructor
	public Hobbit(){}

	//overwriten constructor
	public Hobbit(Integer sequence){
		super(sequence);
	}

	//add to set funtionalit of super class
	@Override
	public void set(){
		super.set("Hobbit");
		nStealth = Input.referenceToSingleInputObject.getInt("Please enter the stealth", 1, 10);
	}

	//add to init funtionalit of super class
	@Override
	public void init(String a_class){
		super.init(a_class);
		this.nStealth = (int)SingletonRandom.instance.getNormalDistribution(1.0,10.0,2.0);
	}
	
	//print things for testing
	@Override
	public String toString() {
		return (super.toString()+"\nstealth: \t"+nStealth);
	}
}
