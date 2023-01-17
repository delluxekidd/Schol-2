#ifndef STUDENT_LIST_H
#define STUDENT_LIST_H

//-----------------------------------------------------------
//  Purpose:    Header file for the StudentList class.
//  Author:     John Gauch
//-----------------------------------------------------------
#include "student_node.h"
#include <time.h>
#include <iostream>
using namespace std;

//-----------------------------------------------------------
// Define the StudentList class interface
//-----------------------------------------------------------
class StudentList
{
public:
   // Constructors
  StudentList();
  StudentList(const StudentList & list);
  ~StudentList();

   // Methods
  void insertHead(string name, string address, float gpa);
  void insertTail(string name, string address, float gpa);
  StudentNode* searchGPA(float gpa);
  StudentNode* searchName(string name);
  void print() const;

private:
  StudentNode * Head;
};

#endif
