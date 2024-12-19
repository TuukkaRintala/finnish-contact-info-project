# Finnish contact info project
This is a project for the introduction to programming course at Tampere University of Applied Sciences. The aim is to make a program that can read and modify a text file that contains contact information.
# Features
The program supports creating, reading, updating and deleting finnish contact info from CSV files using a command line user interface. The information you can store includes a finnish personal ID number, first name, last name, phone number, address and e-mail and each piece of information is validated when it is stored.
# Setup and running the program
## Installation
Download the code, unzip it and navigate to the /src folder in a terminal and use the command `javac ContactsApp.java`. You must have Java JDK 21 or later installed.
Now you can run the program from the /src folder in a terminal using the command `java ContactsApp`.
## Running the program
Upon opening the program, it will import data or open a new CSV-file at the path specified in the code. By default, it is contacts.csv and it can be changed here: ![](https://imgur.com/a/i26m226)
# Code style
I am using the Sun Code Convention for Java.
# Credits
Most of the regular expressions are modified of regular expressions from ihateregex.com and regex101.com.