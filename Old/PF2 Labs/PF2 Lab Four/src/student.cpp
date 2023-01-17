//----------------------------------------------
// Purpose: Demonstrate student ADT
// Author:  John Gauch
//----------------------------------------------

#include "student.h"

//----------------------------------------------
StudentNode::StudentNode()
{
   Name = "";
   Address = "";
   GPA = 0;
   next = NULL;
}

//----------------------------------------------
StudentNode::StudentNode(string name, string address, float gpa)
{
   Name = name;
   Address = address;
   GPA = gpa;
   next = NULL;
}

//----------------------------------------------
StudentNode::StudentNode(const StudentNode & student)
{
   Name = student.Name;
   Address = student.Address;
   GPA = student.GPA;
   next = NULL;
}

//----------------------------------------------
StudentNode::~StudentNode()
{
}

//----------------------------------------------
string StudentNode::getName() const
{
   return Name;
}

//----------------------------------------------
string StudentNode::getAddress() const
{
   return Address;
}

//----------------------------------------------
float StudentNode::getGPA() const
{
   return GPA;
}

//----------------------------------------------
void StudentNode::setName(string name)
{
   Name = name;
}

//----------------------------------------------
void StudentNode::setAddress(string address)
{
   Address = address;
}

//----------------------------------------------
void StudentNode::setGPA(float gpa)
{
   GPA = gpa;
}

//----------------------------------------------
void StudentNode::print() const
{
   cout << "Name: " << Name << "\n"
      << "Address: " << Address << "\n" << "GPA: " << GPA << "\n";
   if (next != NULL)
      next->print();
}

StudentNode* StudentNode::getNext() const
{
   return next;
}

void StudentNode::setNext(StudentNode* nextNode)
{
   next = nextNode;
}