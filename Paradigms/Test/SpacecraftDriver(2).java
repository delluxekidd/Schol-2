
import java.util.ArrayList;

public class SpacecraftDriver //extends Object
{
	public static void main(String[] args)
	{
		System.out.println("Hello Earthlings");
		Spacecraft saturn5 = new Spacecraft();
		System.out.println(saturn5.getName());
		saturn5.setName("Saturn V");
		//saturn5.name = "saturn five";

		System.out.println(saturn5.getName());

		//fully qualified name
		java.util.ArrayList rockets = new java.util.ArrayList();
		//unqualified name because we imported java.util.ArrayList() at the top of our code
		ArrayList<Vehicle> rockets2 = new ArrayList<Vehicle>();
		System.out.println("  size of rockets2 = " + rockets2.size());
		rockets.add(saturn5);
		System.out.println(((Spacecraft)rockets.get(0)).getName());
		
		rockets2.add(saturn5);
		System.out.println("  size of rockets2 = " + rockets2.size());
		//System.out.println(((Spacecraft)rockets.get(0)).getName());
		
		
		Spacecraft[] rockets3 = new Spacecraft[5], rockets4 = new Spacecraft[10];
		System.out.println("length of rockets3 = " + rockets3.length);
		int i = 0;
        //we should probably only put a single error-throwing line in the try-catch, instead of two
        //different lines that could each throw unique errors
		try{			
			System.out.println(rockets3[0]);
			System.out.println(rockets2.get(i));
		} catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("*****There was an error.  Array bounds exception");
			System.out.println(e.toString());
			//System.exit(0);
		} catch (IndexOutOfBoundsException e)
		{
			System.out.println("*****Arraylist bounds exception.");
			System.out.println(e);
		} catch (Exception e)
		{
			System.out.println("*****Other error was caught");
		} finally {
			//System.exit(0); //this will run regardless of whether or not an error was caught!
		}

		Spacecraft apollo13 = new Spacecraft("Apollo 13", 50);
		Spacecraft voyager = new Spacecraft("Voyager", 40);
		Spacecraft[] rockets5 = {saturn5, apollo13, new Spacecraft("Voyager", 40)};

		System.out.println("\nRockets5 array elements");
		for(Spacecraft craft : rockets5)
		{
			System.out.println(craft);
		}
		System.out.println("Finished printing rockets5\n");

		rockets2.add(apollo13);
		rockets2.add(voyager);
		System.out.println(rockets2);
		System.out.println();
		
		System.out.println(apollo13);
		
		System.out.println("Spacecrafts created: (accessed through the class) " + Spacecraft.numSpacecraftsCreated());
		//System.out.println("Spacecrafts created: (accessed through saturn5 object) " + saturn5.numSpacecraftsCreated());
		//System.out.println(saturn5.toString());
		//System.out.println(saturn5);

	}
	
}

