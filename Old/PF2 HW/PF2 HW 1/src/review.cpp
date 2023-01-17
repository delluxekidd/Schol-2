#include "review.h"
#include <iostream>
#include <iomanip>

using namespace std;
//Default Constructor
Review::Review()
{
    reviewer = "";
    restaurant = "";
    food = "";
    cost = 0;
    rating = 0;
}
//Constructor
Review::Review(string Reviewer, string Restaurant, string Food, double Cost, int Rating)
{
    reviewer = Reviewer;
    restaurant = Restaurant;
    food = Food;
    cost = Cost;
    rating = Rating;
}
//copy constructor
Review::Review(const Review & review)
{
    reviewer = review.reviewer;
    restaurant = review.restaurant;
    food = review.food;
    cost = review.cost;
    rating = review.rating;
}
//destructor
Review::~Review()
{
}

//getters and setters

string Review::getReviewer() const
{
    return reviewer;
}

string Review::getRestaurant() const
{
    return restaurant;
}

string Review::getFood() const
{
    return food;
}

double Review::getCost() const
{
    return cost;
}

int Review::getRating() const
{
    return rating;
}

void Review::setReviewer(string currReviewer)
{
    reviewer = currReviewer;
}

void Review::setRestaurant(string currRestaurant)
{
    restaurant = currRestaurant;
}

void Review::setFood(string currFood)
{
    food = currFood;
}

void Review::setCost(double currCost)
{
    cost = currCost;
}

void Review::setRating(int currRating)
{
    if (currRating < 1 || currRating > 10)
    {
        cout << "Invalid rating. Rating must be between 1 and 10." << endl;
    }
    else
    {
        rating = currRating;
    }
}

//Print

void Review::print() const
{
    cout << "Reviewer: " << reviewer << endl;
    cout << "Restaurant: " << restaurant << endl;
    cout << "Food: " << food << endl;
    cout << setprecision(2) << "Cost: " << cost << endl;
    cout << "Rating: " << rating << endl;
}