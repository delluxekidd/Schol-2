#ifndef STUDENT_H
#define STUDENT_H

//----------------------------------------------
// Purpose: Demonstrate student ADT
// Author:  John Gauch
//----------------------------------------------
#include <iostream>
using namespace std;

class Student
{
public:
   // Constructors
    Student();
    Student(string name, string address, float gpa);
    Student(const Student & student);
    ~Student();

   // Methods
    string getName() const;
    string getAddress() const;
    float getGPA() const;
    void setName(string name);
    void setAddress(string address);
    void setGPA(float gpa);
    void print() const;

private:
    string Name;
    string Address;
    float GPA;
};

#endif
