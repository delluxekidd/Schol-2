#include <iostream>
#include "reviewdb.h"
#include "review.h"

using namespace std;

ReviewDB::ReviewDB()
{
}

void ReviewDB::insertReview(Review review)
{
    reviews[count++] = review;
}

void ReviewDB::printRestaurant(const string restaurant) const
{
    for (int i = 0; i < count; i++)
    {
        if (reviews[i].getRestaurant() == restaurant)
        {
            reviews[i].print();
        }
    }
}

void ReviewDB::printCategory(const string food) const
{
    for (int i = 0; i < count; i++)
    {
        if (reviews[i].getFood() == food)
        {
            reviews[i].print();
        }
    }
}

void ReviewDB::printRecent(int lastReviews) const
{
    if (lastReviews > count)
    {
        cout << "There are not that many reviews." << endl;
        cout << "Showing all reviews instead." << endl;
        lastReviews = count;
    }
    for (int i = count -1; i >= count - lastReviews; i--)
    {
        reviews[i].print();
    }
}


