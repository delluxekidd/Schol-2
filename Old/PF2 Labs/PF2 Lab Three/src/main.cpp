//----------------------------------------------
// Purpose: Numbers class with basic operations
// Author:  John Gauch
//----------------------------------------------

// For templates we MUST compile the numbers.h and numbers.cpp code
// at the same time as main.cpp in order for the compiler to resolve
// what data types you are using in Numbers objects.  This is done
// by including "numbers.cpp" below.

// When compiling on the Linux command line use g++ -Wall main.cpp
// When compiling using an IDE, you may need to rename "numbers.cpp"
// to "numbers.cpp.txt" and change the include line below accordingly
// If you do not rename the file, it may be compiled twice causing
// a large number of confusing errors.

#include "numbers.cpp"

int main()
{
   string filename;
   cout << "Enter filename:";
   cin >> filename;

   // Process int numbers
   Numbers <int> num;
   num.readFile(filename);
   cout << "min = " << num.findMin() << endl;
   cout << "max = " << num.findMax() << endl;
   cout << "mean = " << num.findMean() << endl;

   // Process float numbers
   Numbers <float> num2;
   num2.readFile(filename);
   cout << "min = " << num2.findMin() << endl;
   cout << "max = " << num2.findMax() << endl;
   cout << "mean = " << num2.findMean() << endl;

   // Process double numbers
   Numbers <double> num3;
   num3.readFile(filename);
   cout << "min = " << num3.findMin() << endl;
   cout << "max = " << num3.findMax() << endl;
   cout << "mean = " << num3.findMean() << endl;

   // Process long numbers
   Numbers <long> num4;
   num4.readFile(filename);
   cout << "min = " << num4.findMin() << endl;
   cout << "max = " << num4.findMax() << endl;
   cout << "mean = " << num4.findMean() << endl;

   // Process short numbers
   Numbers <short> num5;
   num5.readFile(filename);
   cout << "min = " << num5.findMin() << endl;
   cout << "max = " << num5.findMax() << endl;
   cout << "mean = " << num5.findMean() << endl;
}
