//----------------------------------------------
// Purpose: Test student linked list 
// Author:  John Gauch
//----------------------------------------------

#include "student_list.h"

//----------------------------------------------
/*
Edit the main program in main.cpp to declare a StudentList object and insert five student records into
the StudentList using the methods provided by the class. You can make up any student names, addresses,
and gpas that you want. After the insertions, print the contents of the list. Once you have your program
compiling and running, cut and paste the main program below.
*/
//----------------------------------------------
int main()
{
   //start the clock
   clock_t time1=clock();
   // Test the student list class
   StudentList list;
   list.insertTail("Sam Smith", "Rodgers, AR", 3.5);
   list.insertTail("Jim Jones", "Fayetteville, AR", 3.2);
   list.insertTail("Mary Miller", "Little Rock, AR", 3.8);
   list.insertTail("John Johnson", "Springdale, AR", 3.1);
   list.insertTail("Sue Smith", "Bentonville, AR", 3.4);
   StudentNode* ptr = list.searchGPA(3.8);
   if (ptr != NULL)
   {
      cout << "Found ";
      ptr->print();
      cout << endl;
   }
   else
      cout << "student not found" << endl;

   ptr = list.searchName("John Johnson");
   if (ptr != NULL)
   {
      cout << "Found ";
      ptr->print();
      cout << endl;
   }
   else
      cout << "student not found" << endl;
   //stop the clock
   clock_t time2=clock();
   double run_time = (time2-time1)/(double)CLOCKS_PER_SEC;
   cout << "Run time: " << run_time << " seconds\n";
}
