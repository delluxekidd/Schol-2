//----------------------------------------------
// Purpose: Numbers class with basic operations
// Author:  John Gauch
//----------------------------------------------

#include "numbers.h"

//----------------------------------------------
template <class DataType>
Numbers<DataType>::Numbers()
{
   // Initialize variables
   Count = 0;
   for (int i = 0; i < SIZE; i++)
      Data[i] = 0;
}

//----------------------------------------------
template <class DataType>
Numbers<DataType>::~Numbers()
{
}

//----------------------------------------------
template <class DataType>
void Numbers<DataType>::readFile(string filename)
{
   // Open input file
   ifstream din;
   din.open(filename.c_str());
   if (din.fail())
      return;

   // Read integers into Data array
   Count = 0;
   int num;
   din >> num;
   while (!din.eof() && Count < SIZE)
   {
      Data[Count++] = num;
      din >> num;
   }
   din.close();
}

//----------------------------------------------
template <class DataType>
DataType Numbers<DataType>::getCount()
{
   return Count;
}

//----------------------------------------------
template <class DataType>
DataType Numbers<DataType>::getValue(int index)
{
   // Return specified value
   if ((index >= 0) && (index < Count))
      return Data[index];
   else
      return 0;
}

//----------------------------------------------
template <class DataType>
DataType Numbers<DataType>::findMin()
{
   // Search array for min
   int min = Data[0];
   for (int index = 0; index < Count; index++)
      if (min > Data[index])
	 min = Data[index];
   return min;
}

//----------------------------------------------
template <class DataType>
DataType Numbers<DataType>::findMax()
{
   // Search array for max
   int max = Data[0];
   for (int index = 0; index < Count; index++)
      if (max < Data[index])
	 max = Data[index];
   return max;
}

//----------------------------------------------
template <class DataType>
DataType Numbers<DataType>::findMean()
{
   // Calculate mean value of array
   int total = 0;
   for (int index = 0; index < Count; index++)
      total += Data[index];
   if (Count > 0)
      return total / Count;
   else
      return 0;
}

template <class DataType>
DataType Numbers<DataType>::findVariance()
{
   // Calculate variance of array
   int mean = findMean();
   int total = 0;
   for (int index = 0; index < Count; index++)
      total += (Data[index] - mean) * (Data[index] - mean);
   if (Count > 0)
      return total / Count;
   else
      return 0;
}

template <class DataType>
DataType Numbers<DataType>::findStandardDeviation()
{
   // Calculate standard deviation of array
   int variance = findVariance();
   return sqrt(variance);
}
