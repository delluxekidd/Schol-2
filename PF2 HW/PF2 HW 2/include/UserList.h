#ifndef USERLIST_H
#define USERLIST_H

#include <iostream>
#include "UserNode.h"

using namespace std;

//the UserList class should store a linked list of UserNode objects,
//the number of users in the linked list, and the total number of messages posted by this group.
//the userList class should implement the following methods:
//Constructor - create an empty linked list
//destructor - delete all nodes from the linked list
//addUser - insert userNode into the end of the linked list and increment the number of users in the linked list
//IncrementCount - search the linked list for the user with the given username and increment the message count for that user as well as the group message count
//LargestCount - search the linked list for the user with the largest message count and and print information for that user
//Print - print information about all users in the list


class UserList
{
public:
    UserList();
    ~UserList();
    void addUser(UserNode* user);
    void incrementCount(string username);
    void largestCount();
    void print() const;

private:
    UserNode* head;
    int numUsers;
    int totalMessages;
};

#endif