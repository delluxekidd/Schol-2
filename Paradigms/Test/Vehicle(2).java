
public class Vehicle //extends Object
{
    //protected height variable - child classes can access
	protected double height;
	
	public Vehicle(int x, int y)
	{
		this(1);
		System.out.println("constructor that takes two int parameters");
        System.out.println("this is a delegating constructor since it calls another constructor in the class with \"this(1)\"");
		//this.validate();
	}

	public Vehicle(int x)
	{
		//super(); //this will explicitly call the Object super constructor
        //when it's commented out, then it is implicitly calling it instead
        System.out.println("constructor that takes a single int parameter");
		System.out.println("Vehicle: " + this);
		//validate();
	}
	
	public Vehicle(char x)
	{
		this(5,1);
        System.out.println("constructor that takes a single char parameter");
        System.out.println("this is a delegating constructor since it calls another constructor in the class with \"this(5,1)\"");
	}
	
	public void validate()
	{
		System.out.println("validate data in the Vehicle class");
        System.out.println("this method shows we can call methods from the constructors if we wanted - either as just validate() or explicitly this.validate()");
		//if statements, loops, etc - make sure the data is valid
	}
    
    public void setHeight(double h)
    {
        height = h;
    }
    
    public double getHeight()
    {
        return height;
    }
}