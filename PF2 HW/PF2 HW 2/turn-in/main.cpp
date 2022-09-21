//Author: Gavin Edens

# include "UserNode.h"
# include "UserList.h"
# include <stdlib.h>
# include <cstdlib>

void testUserList ()
{
    //Creating random integer between 1 and 20 for use within program, the values for message count should change each time as i am seeding the random number generator
    //with the current time, this should change the output of largest count each time the program is run
    srand (time (NULL));
    int random = rand () % 20 + 1;
    int random2 = rand () % 20 + 1;
    int random3 = rand () % 20 + 1;

    //Creating my UserList object
    UserList list;

    //Creating my UserNode objects
    UserNode* user1 = new UserNode();
    user1->setFirstName("Gavin");
    user1->setLastName("Edens");
    user1->setUsername("gedens");
    user1->setPhoneNumber("864-555-5555");
    user1->setMessageCount(0);

    //Printing user1 using the UserNode print() function
    cout << "Printing user1 using the UserNode print() function." << endl;
    user1->print();
    cout << endl;

    list.addUser(user1);


    //Creating second test user

    UserNode* user2 = new UserNode();
    user2->setFirstName("John");
    user2->setLastName("Doe");
    user2->setUsername("jdoe");
    user2->setPhoneNumber("5555555555");

    list.addUser(user2);

    //Creating third test user
    UserNode* user3 = new UserNode();
    user3->setFirstName("Jane");
    user3->setLastName("Doe");
    user3->setUsername("jdoe2");
    user3->setPhoneNumber("4798732415");

    list.addUser(user3);

    //Incrementing message count for user1 a random number of times
    while(random > 0)
    {
        list.incrementCount("gedens");
        random--;
    }

    //Incrementing message count for user2 a random number of times
    while(random2 > 0)
    {
        list.incrementCount("jdoe");
        random2--;
    }

    //Incrementing message count for user3 a random number of times
    while(random3 > 0)
    {
        list.incrementCount("jdoe2");
        random3--;
    }
    cout << endl;

    //Printing the user list
    cout << "Printing the user list with the Userlist Print() function" << endl;
    list.print();
    cout << endl;

    //Printing the user with the most messages
    list.largestCount();
    cout << endl;

    //Printing user1 next pointer
    cout << user1->getNext() << endl;
    cout << endl;

    //Printing user2 next pointer
    cout << user2->getNext() << endl;
    cout << endl;

    //Printing user3 next pointer
    cout << user3->getNext() << endl;
    cout << endl;
}

int main()
{
    testUserList();

    return 0;
}
