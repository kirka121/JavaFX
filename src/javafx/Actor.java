package javafx;

public abstract class Actor implements java.io.Serializable{

	public String aName;
	public Integer aStrength;
	public Integer aSpeed;
	public Integer aHealth;
	public Integer aSequence = 0;
    public Integer point_x;
    public Integer point_y;

	public Actor(){}
	//constructor, takes care of the sequence number for this actor
	public Actor(Integer sequence){
		aSequence = sequence;
	}

	//begin get methods, to retrieve information using singleton input
	private String getName(){
		String name;
		name = Input.referenceToSingleInputObject.getString("Please enter custom actor name: ");
		return  name;
	}
	private Integer getStrength(){
		Integer str;
		str = Input.referenceToSingleInputObject.getInt("Please enter your strength", 1, 100);
		return str;
	}
	private Integer getSpeed(){
		Integer speed;
		speed = (Integer)Input.referenceToSingleInputObject.getInt("Please enter your speed", 1, 10);
		return speed;
	}
	private Integer getHealth(){
		Integer hp;
		hp = Input.referenceToSingleInputObject.getInt("Please enter your health", 1, 100);
		return hp;
	}
	//end get methods

	//set actor parameters manualy with an actor type specified.
	public void set(String type){
		this.aName = (type + " - " + getName());
		this.aStrength = getStrength();
		this.aSpeed = getSpeed();
		this.aHealth = getHealth();
	}

	//set actor parameters manualy
	public void set(){
		this.aName = ("Actor " + getName());
		this.aStrength = getStrength();
		this.aSpeed = getSpeed();
		this.aHealth = getHealth();
	}

	//automatically randomly generate actor parameters
	public void init(String a_class){
		this.aName = (a_class + " " + Integer.toString(this.aSequence+1));
		this.aStrength = (int)SingletonRandom.instance.getNormalDistribution(1.0,100.0,2.0);
		this.aSpeed = (int)SingletonRandom.instance.getNormalDistribution(1.0,10.0,2.0);
		this.aHealth = (int)SingletonRandom.instance.getNormalDistribution(1.0,100.0,2.0);
	}

	//testing implicit call of toString()
	@Override
	public String toString() {
		return ("\n\nname: \t" +aName+"\nstrength: \t"+aStrength+"\nspeed:\t\t "+aSpeed+"\nhealth: \t"+aHealth);
	}
}
