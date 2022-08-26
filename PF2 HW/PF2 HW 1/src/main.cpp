#include "review.h"
#include "reviewdb.h"

using namespace std;

/*void TestReviews()
{
    Review r1("John", "McDonalds", "Burger", 2.50, 5);
    Review r2("Jane", "Arbys", "Sammich", 67.00, 3);
    Review r3("John", "Bill's Butter House", "Butter", 27.50, 7);
    Review r4("John", "Gutterball", "Rat Sandwich", 21.69, 2);
    Review r5("John", "Ur Mom's House", "butt", 96.69, 4);
    Review r6("John", "Ur Dad's House", "sex", 420.69, 8);

    ReviewDB db;
    db.insertReview(r1);
    db.insertReview(r2);
    db.insertReview(r3);
    db.insertReview(r4);
    db.insertReview(r5);
    db.insertReview(r6);

    string whatRestaurant = "McDonalds";
    string whatFood = "Burger";
    int numRecentReviews = 4;

    //print all reviews for a restaurant
    db.printRestaurant(whatRestaurant);

    //print n recent reviews
    db.printRecent(numRecentReviews);

    //print all reviews for a category of food
    db.printCategory(whatFood);
} */


void submitReview(ReviewDB & db)
{
    Review r;
    string Reviewer, Food, Restaurant;
    double Cost;
    int Rating;
    cout << "Enter the name of the restaurant: \n";
    cin >> Restaurant;
    r.setRestaurant(Restaurant);
    cout << "Enter the name of the food: \n";
    cin >> Food;
    r.setFood(Food);
    cout << "Enter the name of the reviewer: \n";
    cin >> Reviewer;
    r.setReviewer(Reviewer);
    cout << "Enter the cost of the food: \n";
    cin >> Cost;
    r.setCost(Cost);
    cout << "Enter the rating of the food: \n";
    cin >> Rating;
    while (Rating > 10 || Rating < 1)
    {
        cout << "Please enter a valid rating '1-10'\n";
        cin >> Rating;
        }
    r.setRating(Rating);
    db.insertReview(r);
}

void viewReviewsRestaurant(ReviewDB & db)
{
    string Restaurant;
    cout << "Enter the name of the restaurant: \n";
    cin >> Restaurant;
    db.printRestaurant(Restaurant);
}

void viewReviewsCategory(ReviewDB & db)
{
    string Food;
    cout << "Enter the name of the food: \n";
    cin >> Food;
    db.printCategory(Food);
}

void viewRecentReviews(ReviewDB & db)
{
    int numRecentReviews;
    cout << "Enter the number of recent reviews you would like to view: \n";
    cin >> numRecentReviews;
    db.printRecent(numRecentReviews);
}

void showInterface(bool & cont, ReviewDB & db)
{
    int input;
    cout << "Hello, and welcome to the Gavin Edens restaurant review tool\n";
    cout << "1. Leave a review.\n";
    cout << "2. View reviews by restaurant.\n";
    cout << "3. View recent reviews\n";
    cout << "4. View reviews by food category\n";
    cout << "5. Exit\n";
    cin >> input;
    while (input > 5 || input < 1)
    {
        cout << "Please enter a valid choice '1-5'\n";
        cin >> input;
    }
    switch (input)
    {
        case 1:
            submitReview(db);
            break;
        case 2:
            viewReviewsRestaurant(db);
            break;
        case 3:
            viewRecentReviews(db);
            break;
        case 4:
            viewReviewsCategory(db);
            break;
        case 5:
            cont = false;
            break;
    }
}

int main()
{
    ReviewDB db;
    //boolean for while loop
    bool cont = true;
    while (cont == true)
    {
        showInterface(cont, db);
    }
}