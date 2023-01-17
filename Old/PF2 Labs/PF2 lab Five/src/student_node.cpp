//----------------------------------------------
// Purpose: Implement student node 
// Author:  John Gauch
//----------------------------------------------

#include "student_node.h"

//----------------------------------------------
StudentNode::StudentNode()
{
   Name = "";
   Address = "";
   GPA = 0;
   Next = NULL;
}

//----------------------------------------------
StudentNode::StudentNode(string name, string address, float gpa)
{
   Name = name;
   Address = address;
   GPA = gpa;
   Next = NULL;
}

//----------------------------------------------
StudentNode::StudentNode(const StudentNode & node)
{
   Name = node.Name;
   Address = node.Address;
   GPA = node.GPA;
   Next = node.Next;
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
StudentNode *StudentNode::getNext() const
{
   return Next;
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
void StudentNode::setNext(StudentNode * next)
{
   Next = next;
}

//----------------------------------------------
void StudentNode::print() const
{
   cout << "Name: " << Name << "\n"
      << "Address: " << Address << "\n" << "GPA: " << GPA << "\n";
}
