//----------------------------------------------
// Purpose: Test the Music class
// Author:  John Gauch
//----------------------------------------------

#include "music_old.h"

//----------------------------------------------
// Main program
//----------------------------------------------
int main()
{
   // Test default constructor
   Music music1;

   // Test set methods
   music1.setTitle("My Favorite Song");
   music1.setReleaseYear(2000);
   music1.setAuthor("John Doe");
   music1.setAlbum("My Favorite Album");
   music1.setGenre("Pop");
   // Test copy constructor
   Music music2(music1);
   // Test get/set methods
   cout << "Title: " << music2.getTitle() << endl;
   cout << "Release Year: " << music2.getReleaseYear() << endl;
   cout << "Author: " << music2.getAuthor() << endl;
   cout << "Album: " << music2.getAlbum() << endl;
   cout << "Genre: " << music2.getGenre() << endl;
   // Test print method
   music2.print();
   music2.interesting();
}
