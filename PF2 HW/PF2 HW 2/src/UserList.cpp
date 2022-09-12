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
    //delete all nodes from the linked list
    UserNode* temp = head;
    while (temp)
    {
        head = head->getNext();
        delete temp;
        temp = head;
    }
}

//addUser - insert userNode into the end of the linked list and increment the number of users in the linked list

void UserList::addUser(UserNode* user)
{
    if (head == NULL)
    {
        head = user;
    }
    else
    {
        UserNode* temp = head;
        while (temp->getNext())
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
    UserNode* temp = head;
    while (temp)
    {
        if (temp->getUsername() == username)
        {
            temp->setMessageCount(temp->getMessageCount() + 1);
            totalMessages++;
        }
        temp = temp->getNext();
    }
}

//Print - print information about all users in the list

void UserList::print() const
{
    UserNode* temp = head;
    while (temp)
    {
        temp->print();
        temp = temp->getNext();
    }
}