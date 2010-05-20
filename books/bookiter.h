/* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License version 2
* as published by the Free Software Foundation.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*
* Author: Moshe Wagner. <moshe.wagner@gmail.com>
*/

#ifndef BOOKITER_H
#define BOOKITER_H

#include "functions.h"

//This class represents a location within a book;
// The location is given by 5 level values, (corresponding to symbols in the book files)
// When:
// ! = Level 1
// ~ = Level 2
// @ = Level 3
// # = Level 4
// ^ = Level 5
//
// Each value holds a hebrew string representing it's position
// A location may have all levels, but usually does not.
//
// The lowest values that are 1 apart from each other, are used for the links and name points.
// ( values that are 1 level apart, are related - like perek and pasuk )
// But, if a value is 2 or more levels apart from the last one still conected to the lowest one
// it is not related (like perek and daf) - And therfore is not used for the links.

class BookIter
{
public:
    BookIter();
    ~BookIter();

    //Copy constructor, making this itr equivalent to the given one
    BookIter(BookIter * other_book);

    //Resets all levels related to this one (see the source file for detailed explanation).
    // The levels are zerod to "0" (as oppesed to ""), so we can tell between emptied and never used values.
    void ZeroLevel(int level);

    //Moves the level symboled by the first char of the string, to the value written
    // in the rest of the string.
    void SetLevelFromLine(QString level_line);

    //Returns a string representing the current position
    // (for html links and name points)
    QString toStringForLinks(int from_level = 1);

    //Returns a string representing the current position ,
    // In the way it should be shown to the user
    // (Showing as a hebrew gematria string, only of values related to the lowest one)
    QString toHumanString();

    //Returns a string representing the current position of gmara pages only,
    // In the way it should be shown to the user (such as "טז:" ).
    QString toGmaraString();

protected:

    //Holds the name of the current position of each level
    QString mLevelName[5];

};

#endif // BOOKITER_H
