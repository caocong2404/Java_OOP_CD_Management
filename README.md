<div align="center">
  <br>
  <h1>CD House Company 💿📀💿📀</h1>
</div>
<br>

## Background
The CD House Company has a collection of over 700 audio and video CD sets. Currently, the company wants to
introduce a system that automatically manages the list of existing CDs and new CDs.
Suppose you are a member of team to implement the solution for the above application problem, let's create an
application written in Java language to manage Real-time CD list by: adding new CDs with provided details,
searching some CD, showing the entire CD list with full details.❤️

## Table of Contents
- [Table of Contents](#table-of-contents)
- [Background](#Background)
- [Program Specifications](#program-specification)
- [Features](#Features)
    - [Function 1: Add CD to the catalog](#function1)
    - [Function 2: Search CD by CD title](#function2)
    - [Function 3: Display the catalog](#function3)
    - [Function 4: Update CD](#function4)
       - [Function 4.1: Update CD information](#function4-1)
       - [Function 4.2: Delete CD information](#function4-2)
    - [Function 5: Save to file](#function5)
    - [Function 6: Print all list CD from file](#function6)
## Program Specifications
<pre>
Build a user management program. With the following basic functions:
	1. Add CD to the catalog
	2. Search CD by CD title
	3. Display the catalog
	4. Update CD
		1. Update CD
		2. Delate CD
	5. Save account to file.
	6. Print list CDs from file.
	Others- Quit
 </pre>
## Features
This system contains the following functions:
<pre> 
This system contains the following functions:
The system will display a menu to ask users to select an option.
<strong>▪ Function 1: Add CD to the catalog</strong>
	o When the user selects “Add CD”, the program will check for free space in the CD-containing array.
	o If there is room, the program will enter detailed information about the CD. Otherwise, the
	message "unable to add CD" will be outputted.
	o The CD information include: CD collection name (game/movie/music), CD type (audio/video),
	title, unit price, CD ID and Publishing year. These details will be stored in the array containing the
	CDs, and the variable cdCounter – count the number of CDs – will be increased by 1 unit.
<strong>▪ Function 2: Search CD by CD title </strong>
	o When the user selects item 2 to search for a CD by title, the program will check how many there
	are any CDs in the archive list.
	o If so, the search will begin by matching the CD titles entered by the user with the saved CD titles.
	If found, will print the specific details of that CD. Otherwise, the system will give an error message.
<strong>▪ Function 3: Display the catalog</strong>
	o When the user selects item 3 to print out a list of CDs, the program will check how many there
	are any CDs in the list storage.
	o If so, the full details of those CDs will be printed.
<strong>▪ Function 4: Update CD</strong>
	<strong>o Function 4.1: Update CD information </strong>
		- The user can edit of the remaining information. If the inputted Information is blank, the old
		information will not be changed.
		− The result of the update action should be shown as success or fail status.
		− The application asks user to go back to the main menu.
	<strong>o Function 4.2: Delete CD information</strong>
		− Th user can delete a CD.
		− The result of the delete action should be shown as success or fail status.
		− The application asks user to go back to the main menu.
<strong>▪ Function 5: Save to file </strong>
	o The application allows to write list CD catalog to the CD.dat file.
	o The application asks user to go back to the main menu.
<strong>▪ Function 6: Print all list CD from file </strong>
	o The application allows to load the list of CDs from the file into Collection Type
	o The system can display the list CD order by first name.
	o The application asks user to go back to the main menu.
</pre>
  <p align="center">
  <img alt="Cat" width="250px" src="https://github.com/caocong2404/caocong2404/blob/main/cat-coding.png"/>
  <br>
  <strong>Happy Coding</strong> ❤️
</p>

