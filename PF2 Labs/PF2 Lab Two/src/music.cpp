//----------------------------------------------
// Purpose: Implementation of Music class
// Author:  YOUR NAME HERE
//----------------------------------------------

#include "music_old.h"

//----------------------------------------------
Music::Music()
{
   title = "";
   releaseYear = 0;
   author = "";
   album = "";
   genre = "";
}

//----------------------------------------------
Music::Music(string title, int releaseYear, string author, string album, string genre)
{
   setTitle(title);
   setReleaseYear(releaseYear);
   setAuthor(author);
   setAlbum(album);
   setGenre(genre);
}

//----------------------------------------------
Music::Music(const Music & music)
{
   title = music.title;
   releaseYear = music.releaseYear;
   author = music.author;
   album = music.album;
   genre = music.genre;
}

//----------------------------------------------
Music::~Music()
{
}

//----------------------------------------------
string Music::getAlbum() const
{
   cout << "In getAlbum method" << endl;
   return album;
}

//----------------------------------------------
string Music::getAuthor() const
{
   cout << "In getAuthor method" << endl;
   return author;
}

//----------------------------------------------
string Music::getTitle() const
{
   cout << "In getTitle method" << endl;
   return title;
}

//----------------------------------------------
int Music::getReleaseYear() const
{
   cout << "In getReleaseYear method" << endl;
   return releaseYear;
}

//----------------------------------------------
string Music::getGenre() const
{
   cout << "In getGenre method" << endl;
   return genre;
}

//----------------------------------------------
void Music::setAlbum( string param1 )
{
   cout << "In setAlbum method" << endl;
   album = param1;
}

//----------------------------------------------
void Music::setAuthor( string param2 )
{
   cout << "In setAuthor method" << endl;
   author = param2;
}

//----------------------------------------------
void Music::setReleaseYear( int param3 )
{
   cout << "In setReleaseYear method" << endl;
   releaseYear = param3;
}

//----------------------------------------------
void Music::setTitle( string param4 )
{
   cout << "In setTitle method" << endl;
   title = param4;
}

//----------------------------------------------
void Music::setGenre( string param5 )
{
   cout << "In setGenre method" << endl;
   genre = param5;
}

//----------------------------------------------
void Music::print() const
{
   cout << "In print method" << endl;
   cout << "Title: " << title << endl;
   cout << "Release Year: " << releaseYear << endl;
   cout << "Author: " << author << endl;
   cout << "Album: " << album << endl;
   cout << "Genre: " << genre << endl;
}

void Music::interesting() const
{
   cout << "In interesting method" << endl;
   cout << "This song was created by " << author << " in " << releaseYear << endl;
}
