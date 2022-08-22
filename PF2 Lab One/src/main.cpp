//----------------------------------------------
// Purpose: Demonstrate student ADT
// Author:  John Gauch
//----------------------------------------------

#include "student.h"

//----------------------------------------------
int main()
{
   // Test default constructor
   Student john;
   cout << "\nprinting john object\n";
   john.print();

   // Test set methods
   john.setName("John");
   john.setAddress("Johnson, AR");
   john.setGPA(3.2);
   cout << "\nprinting john object\n";
   john.print();

   // Test constructor with parameters
   Student fred("Fred", "Fayetteville, AR", 4.0);
   cout << "\nprinting fred object\n";
   fred.print();

   // Test copy constructor
   Student bob(fred);
   cout << "\nprinting bob object\n";
   fred.print();

   // Testing array of objects
   Student list[10];
   for (int i = 0; i < 3; i++)
   {
      cout << "\nprinting object " << i << "\n";
      list[i].setName("name tba");
      list[i].setAddress("address tba");
      list[i].setGPA(i + 1);
      list[i].print();
   }
}
