#ifndef MUSIC_OLD_H
#define MUSIC_OLD_H

//create a class that stores five attributes for a user provided song

//----------------------------------------------
// Purpose: Define Music class
// Author:  Gavin Edens (sorry didn't know we were going to be given a .h file so i created my own but after looking through the code i found the one in the lab was basically the same)
//----------------------------------------------

#include <iostream>
using namespace std;
class Music
{
public:
    //constructors
    Music();
    Music(string title, int releaseYear, string author, string album, string genre);
    Music(const Music & Music);
    ~Music();

    //getters and setters
    string getTitle() const;
    string getAuthor() const;
    int getReleaseYear() const;
    string getAlbum() const;
    string getGenre() const;

    void setTitle(string currTitle);
    void setAuthor(string currAuthor);
    void setReleaseYear(int currReleaseYear);
    void setAlbum(string currAlbum);
    void setGenre(string currGenre);
    //methods
    void print() const;
    void interesting() const;

private:
    string title;
    int releaseYear;
    string author;
    string album;
    string genre;
};

#endif