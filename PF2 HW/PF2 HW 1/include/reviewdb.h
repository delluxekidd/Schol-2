#ifndef REVIEWDB_H
#define REVIEWDB_H

#include <iostream>
#include "review.h"

//create a class that has an array that stores Review objects
//has to have the ability to store and retrieve Review objects
//insertReview - adds a new review to the end of array and increments the count
//printRestaurant - prints all reviews for a restaurant
//printCategory - prints all reviews for a food category
//printRecent - prints the last n reviews

class ReviewDB
{
public:
    ReviewDB();
    void insertReview(Review review);
    void printRestaurant(const string restaurant) const;
    void printCategory(const string food) const;
    void printRecent(const int n) const;
private:
//fixed size array of Review objects and a count of the number of reviews
    int count = 0;
    Review reviews[100];
};


#endif

