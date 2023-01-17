#ifndef STUDENT_NODE_H
#define STUDENT_NODE_H

//-----------------------------------------------------------
//  Purpose:    Header file for the StudentNode class.
//  Author:     John Gauch
//-----------------------------------------------------------
#include <iostream>
using namespace std;

//-----------------------------------------------------------
// Define the StudentNode class interface 
//-----------------------------------------------------------
class StudentNode
{
 public:
   // Constructors 
   StudentNode();
   StudentNode(string name, string address, float gpa);
     StudentNode(const StudentNode & node);
    ~StudentNode();

   // Get methods
   string getName() const;
   string getAddress() const;
   float getGPA() const;
   StudentNode *getNext() const;

   // Set methods
   void setName(string name);
   void setAddress(string address);
   void setGPA(float gpa);
   void setNext(StudentNode * next);

   // Other methods
   void print() const;

 private:
     string Name;
   string Address;
   float GPA;
   StudentNode *Next;
};

#endif
