#ifndef REVIEW_H
#define REVIEW_H

#include <iostream>
#include <string>

using namespace std;

//create a class that stores reviewer name, restaurant name, food category, delivery cost, and the customer rating between 1 and 10
class Review
{
public:
//Constructors
    Review();
    Review(string Reviewer, string Restaurant, string Food, double Cost, int Rating);
    Review(const Review & review);
    ~Review();
//getters and setters
    string getReviewer() const;
    string getRestaurant() const;
    string getFood() const;
    double getCost() const;
    int getRating() const;
    void setReviewer(const string reviewer);
    void setRestaurant(const string restaurant);
    void setFood(const string food);
    void setCost(const double cost);
    void setRating(const int rating);
//Print
    void print() const;
private:
    string reviewer;
    string restaurant;
    string food;
    double cost;
    int rating;
};
#endif
