#ifndef USERNODE_H
#define USERNODE_H

#include <iostream>

//the UserNode class should store a first name, last name, username, phone number, message count, and a pointer to the next node in the list
//the UserNode class should have a constructor that initializes the data members, and a destructor, Getters and Setters for the data members,
//and a print function that prints the data members

using namespace std;

class UserNode
{
public:
    //Constructors
    UserNode();
    ~UserNode();

    //Setters
    void setFirstName(string firstName);
    void setLastName(string lastName);
    void setUsername(string username);
    void setPhoneNumber(string phoneNumber);
    void setMessageCount(int messageCount);
    void setNext(UserNode* next);

    //Getters
    string getFirstName() const;
    string getLastName() const;
    string getUsername() const;
    string getPhoneNumber() const;
    int getMessageCount() const;
    UserNode* getNext() const;

    //print function
    void print() const;

private:
    string firstName;
    string lastName;
    string username;
    string phoneNumber;
    int messageCount;
    UserNode* next;
};

#endif