
import java.util.ArrayList;
import static java.lang.Math.PI;

public class Spacecraft extends Vehicle
{
	//instance variables, class member variables, attributes
	private int cargoSpace; //square meters
	private double fuelCapacity;
	private String name;
	private float speed;
	private boolean isCurrentlyInSpace;
	//this is a class variable (not a class member variable)
	private static int numCrafts;

	public Spacecraft()
	{
		super(5,1);
		cargoSpace = -1;
		height = -1;
		fuelCapacity = -1;
		name = "";
		speed = -1;
		isCurrentlyInSpace = false;
		numCrafts++;
	}
	
	public Spacecraft(String name, int cargo)
	{
		super(6);
		this.name = name; //shadowing
		int cargoSpace = cargo;
		height = -1;
		fuelCapacity = -1;
		speed = -1;
		isCurrentlyInSpace = false;
		numCrafts++;
	}
	
	public void setName(String nameIn)
	{
		//class instance variable = local parameter passed in
		this.name = nameIn;
	}
    
    public void setCargoSpace(int cargoSpaceIn)
    {
        cargoSpace = cargoSpaceIn;
    }
    
    public void setFuelCapacity(double fuelCapacityIn)
    {
        fuelCapacity = fuelCapacityIn;
    }
    
    public void setSpeed(float speed)
    {
        this.speed = speed;
    }
    
    public void setIsCurrentlyInSpace(boolean space)
    {
        isCurrentlyInSpace = space;
    }
	
	public String getName()
	{
		return this.name;
	}
    
    public int getCargoSpace()
    {
        return cargoSpace;
    }
    
    public double getFuelCapacity()
    {
        return fuelCapacity;
    }
    
    public float getSpeed()
    {
        return speed;
    }
    
    public boolean getCurrentlyInSpace()
    {
        return isCurrentlyInSpace;
    }
	
	public static int numSpacecraftsCreated()
	{
		return numCrafts;
	}
	
	@Override //this overrides the toString method of whichever class closest above it either
    //implements or overrides itself
	public String toString()
	{
		//super.validate();
		return super.toString() + ": " + name + " has " + cargoSpace + " space.";
	}
}
