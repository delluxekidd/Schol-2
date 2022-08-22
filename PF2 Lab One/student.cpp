//----------------------------------------------
// Purpose: Demonstrate student ADT
// Author:  John Gauch
//----------------------------------------------

#include "/home/gavin/Documents/2022 Fall Semester/PF2/Schol 2/PF2 Lab One/student.h"

//----------------------------------------------
Student::Student()
{
   Name = "";
   Address = "";
   GPA = 0;
}

//----------------------------------------------
Student::Student(string name, string address, float gpa)
{
   Name = name;
   Address = address;
   GPA = gpa;
}

//----------------------------------------------
Student::Student(const Student & student)
{
   Name = student.Name;
   Address = student.Address;
   GPA = student.GPA;
}

//----------------------------------------------
Student::~Student()
{
}

//----------------------------------------------
string Student::getName() const
{
   return Name;
}

//----------------------------------------------
string Student::getAddress() const
{
   return Address;
}

//----------------------------------------------
float Student::getGPA() const
{
   return GPA;
}

//----------------------------------------------
void Student::setName(string name)
{
   Name = name;
}

//----------------------------------------------
void Student::setAddress(string address)
{
   Address = address;
}

//----------------------------------------------
void Student::setGPA(float gpa)
{
   GPA = gpa;
}

//----------------------------------------------
void Student::print() const
{
   cout << "Name: " << Name << "\n"
      << "Address: " << Address << "\n" << "GPA: " << GPA << "\n";
}

