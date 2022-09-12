#include "UserNode.h"
#include "UserList.h"

//Default constructor
UserNode::UserNode()
{
    firstName = "";
    lastName = "";
    username = "";
    phoneNumber = "";
    messageCount = 0;
    next = NULL;
}

//Destructor

UserNode::~UserNode()
{
}

//Setters

void UserNode::setFirstName(string firstName)
{
    this->firstName = firstName;
}

void UserNode::setLastName(string lastName)
{
    this->lastName = lastName;
}

void UserNode::setUsername(string username)
{
    this->username = username;
}

void UserNode::setPhoneNumber(string phoneNumber)
{
    this->phoneNumber = phoneNumber;
}

void UserNode::setMessageCount(int messageCount)
{
    this->messageCount = messageCount;
}

void UserNode::setNext(UserNode* next)
{
    this->next = next;
}

//Getters

string UserNode::getFirstName() const
{
    return firstName;
}

string UserNode::getLastName() const
{
    return lastName;
}

string UserNode::getUsername() const
{
    return username;
}

string UserNode::getPhoneNumber() const
{
    return phoneNumber;
}

int UserNode::getMessageCount() const
{
    return messageCount;
}

UserNode* UserNode::getNext() const
{
    return next;
}

//print function

void UserNode::print() const
{
    cout << "First Name: " << firstName << endl;
    cout << "Last Name: " << lastName << endl;
    cout << "Username: " << username << endl;
    cout << "Phone Number: " << phoneNumber << endl;
    cout << "Message Count: " << messageCount << endl;
}
