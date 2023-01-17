#include "UserNode.h"
#include "UserList.h"


//Default constructor
UserList::UserList()
{
    head = NULL;
    numUsers = 0;
    totalMessages = 0;
}

//Destructor

UserList::~UserList()
{
    cout << "Deleting list" << endl;
    //delete all nodes from the linked list
    UserNode* temp = head;
    while (temp != NULL)
    {
        head = head->getNext();
        delete temp;
        temp = head;
    }
}

//addUser - insert userNode into the end of the linked list and increment the number of users in the linked list

void UserList::addUser(UserNode* user)
{
    cout << "Adding user" << endl;
    if (head == NULL)
    {
        head = user;
    }

    else
    {
        UserNode* temp = head;
        while (temp->getNext() != NULL)
        {
            temp = temp->getNext();
        }
        temp->setNext(user);
    }

    numUsers++;
}

//IncrementCount - search the linked list for the user with the given username and increment the message count for that user as well as the group message count

void UserList::incrementCount(string username)
{
    cout << "Incrementing count" << endl;
    UserNode* temp = head;
    while (temp != NULL)
    {
        if (temp->getUsername() == username)
        {
            temp->setMessageCount(temp->getMessageCount() + 1);
            totalMessages++;
        }
        temp = temp->getNext();
    }
}

//LargestCount - search the linked list for the user with the largest message count and and print information for that user

void UserList::largestCount()
{
    cout << "Finding largest count" << endl;
    UserNode* temp = head;
    UserNode* largest = head;
    while (temp != NULL)
    {
        if (temp->getMessageCount() > largest->getMessageCount())
        {
            largest = temp;
        }
        temp = temp->getNext();
    }
    cout << "User with largest message count: \n";
    largest->print();
    cout << endl;
}

//Print - print information about all users in the list

void UserList::print()
{
    cout << "Printing list" << endl;
    UserNode* temp = head;
    while (temp != NULL)
    {
        temp->print();
        temp = temp->getNext();
        cout << endl;
    }
    cout << "Total number of users: " << numUsers << endl;
    cout << "Total number of messages: " << totalMessages << endl;
}