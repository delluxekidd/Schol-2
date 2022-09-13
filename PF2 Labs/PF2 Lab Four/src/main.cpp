//----------------------------------------------
// Purpose: Demonstrate student ADT
// Author:  John Gauch
//----------------------------------------------

#include "student.h"

//----------------------------------------------
int main()
{
   string name;
   string address;
   float gpa;
   StudentNode *head = NULL;
   for (int i=0; i<5; i++)
   {
      cout << "Enter name: ";
      cin >> name;
      cout << "Enter address: ";
      cin >> address;
      cout << "Enter GPA: ";
      cin >> gpa;

      // ADD HERE
      StudentNode *ptr = new StudentNode(name, address, gpa);
      ptr->setNext(head);
      head = ptr;
   }
   head->print();
}
