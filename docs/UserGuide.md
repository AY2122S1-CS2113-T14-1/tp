# User Guide

_The Great Detective_ is an application that allows players to enjoy the fun of role-playing and logical reasoning to
find out the truth of a murder case. The player gets the chance to investigate in the case by gathering information
about the events that lead to the murder and clues about the suspects. A great journey awaits.

>Symbols used in this guide:
>* 💡 denotes important information.
>* ❗ denotes a warning.


## Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
  * [Changing narrative number of lines: `/narrative-lines NUM`](#changing-narrative-number-of-lines-narrative-lines-num)
  * [Taking notes: `/note`](#taking-notes-note)
  * [Searching notes with keywords](#searching-notes-with-keywords-keywords)
  * [Searching notes with scene index](#searching-notes-with-scene-index-index)
  * [Shortcuts for note-taking](#shortcuts-for-note-taking)
  * [Quit note function: `/quit`](#quit-note-function-quit)
  * [Moving to the next scene: `/next`](#moving-to-the-next-scene-next)
  * [Exiting the game: `/exit`](#exiting-the-game-exit)
  * [Going back to the previous scene: `/back`](#going-back-to-the-previous-scene-back)
  * [Viewing the list of commands available: `/help`](#viewing-the-list-of-commands-available-help)
  * [Restarting the game: `/restart`](#restarting-the-game-restart)
  * [Choosing suspect: `KEYWORD` or `INDEX`](#choosing-a-suspect-keyword-or-index)
  * [Investigating clue: `INDEX`](#investigating-a-clue-index)
  * [Viewing checked clues: `/view`](#viewing-checked-clues-view)
* [FAQ](#faq)
* [Command Summary](#command-summary)


## Quick Start
1. Ensure that you have Java `11` or above installed.
2. Download the latest version of `TheGreatDetective` from [here](https://github.com/AY2122S1-CS2113-T14-1/tp/releases).
3. Copy the `.jar` file to the folder you want to use as the home folder for your game.
4. To launch the app, run the command `java -jar TheGreatDetective.jar`. Here are the first few lines you should see in the output when you start _The Great Detective_ for the first time.

```
Welcome to the Classic Adventure Text Game!


------------------
| Who Killed Me? |
------------------

I woke up and found myself dead. The Spirit Guide from the Hell told me that the only way to revive my soul is for me to find the murderer, eliminating the grudge in my soul. So I have to go back 24 hours ago and find the murderer from the perspective of my soul.
```

>❗ The new files created in the `data` folder are used to store the data of your progress. Please refrain from deleting/modifying these files in order to preserve the data.

5. Type the command and press Enter to execute it. e.g. typing `/help` and pressing Enter will show you the list of commands you can enter.
6. Refer to the `Features` below for details of each command.

## Features 

>**Notes about the command format:**
>* Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `/view [NAME]...`, `NAME` is a parameter which can be used as `/view Father`.
>* Items in square brackets are optional.
  e.g `/view [NAME]...` can be used as `/view` or `/view Father`.
>* Items with `...` after them can be used multiple times including zero times. 
  e.g. `[NAME]...` can be used as ` ` (i.e. 0 times), `Father`, `Father Ling` etc.

### Changing narrative number of lines: `/narrative-lines NUM`
Changes the number of lines to be printed each time during story-telling narrative at the start of each scene.

Format: `/narrative-lines NUM`

Example of usage:

```
$ /narrative-lines 10
Successfully changed number of narrative lines to print each time to 10
```

### Taking notes: `/note`
Allows user to take note with title and content whenever they want.

Example of usage:

```
$ /note
```

### Searching notes with keywords: `KEYWORDS`
Allows user to search the notes using keywords in title

Example of usage:
```
$ /note

Do you want to create a new note or open a existing note or delete a note?
Please type in:
'1' for create a new note.
'2' for open an existing note.
'3' for delete notes.


$ 2


Here are the list of notes available to you.
1. BOOKSHELF
2. CAR
3. LIVING ROOM WITH BLOOD
Do you want to search a note (type in 'search') or directly open a note (type in 'open')?


$ search


Do you want to search by keyword (type 'keyword') or scene index (type 'index')?


$ keyword


Please enter keywords


$ BLOOD


Here are the list of notes found given keywords:
1. scene 2
LIVING ROOM WITH BLOOD
There is blood in living room, so I think suspect is Wendy.

==============================
```

### Searching notes with scene index: `INDEX`
Allows user to search notes with scene index.

Example of usage:
```
$ /note


Do you want to create a new note or open a existing note or delete a note?
Please type in:
'1' for create a new note.
'2' for open an existing note.
'3' for delete notes.


$ 2


Here are the list of notes available to you.
1. BOOKSHELF
2. CAR
3. LIVING ROOM WITH BLOOD
   Do you want to search a note (type in 'search') or directly open a note (type in 'open')?
 

$ search

   Do you want to search by keyword (type 'keyword') or scene index (type 'index')?


$ index
   

   Please enter scene index:
  

$ 2


   Here are the list of notes found given keywords:
1. scene 2
   BOOKSHELF
   There are many books on the bookshelf.
2. scene 2
   CAR

3. scene 2
   LIVING ROOM WITH BLOOD
   There is blood in living room, so I think suspect is Wendy.

==============================
```

### Shortcuts for note-taking

An available shortcut is to type the command after `/note`.
For example, you can input `/note create` to bring up the note creation prompt.
Example of usage:

* Typing `/note search` would immediately bring up the prompt:

```
Do you want to search by keyword (type 'keyword') or scene index (type 'index')?
```

* Typing `/note open` would immediately bring up the prompt:

```
Here are the list of notes available to you.
1. BOOKSHELF
2. CAR
3. LIVING ROOM WITH BLOOD
   Do you want to search a note (type in 'search') or directly open a note (type in 'open')?
```


### Moving to the next scene: `/next`
This allows the user to go the next scene.

Example of usage: 

```
----------------
| Instructions |
----------------

Here are the commands that you can enter:
"/narrative-lines #NUM" - change number of narrative lines print each time to #NUM
"/help" - view this command list
"/next" - move on to the next scene or the next stage of a scene
"/note" - create a new note / open a note / delete a note
"/view" - view all the clues that you have gathered
"/restart" - restart the game from beginning
"/exit" - exit the game

Now, enter "/next" to start your journey to the truth.

$ /next

------------
| Scene #1 |
------------

"Om... Om... Om..." The alarm clock on the head of the bed rang on time as usual, March 1, 2020, at 8 o'clock in the morning, every minute and second. I woke up in a daze, stretched out a lot, feeling extremely tired, and my bones were crushed.

```

### Quit note function: `/quit`
Allows user to quit note function.


Example of usage:
$ /note 1
```
Please enter the title for this note (if you do not need title, type a spacing or press enter instead):

$ APPLE ON THE GROUND


Please enter your note:

$ /quit

Ok! You have successfully quit note process!
```
>💡 Users can quit note function at any time they choose.


### Exiting the game: `/exit`
Exits the game.


```
-------------------------
| Scene 1 Investigation |
-------------------------
Who do you want to investigate?
1. Father

$ /exit

Goodbye.
```

>💡 Users can exit the game at any time they choose.

### Going back to the previous scene: `/back`
Returns to the previous scene.

Example of usage:

```
Scene 1 Investigation
Who do you want to investigate?
1. Father

$ /back 

------------------
| Who Killed Me? |
------------------

I woke up and found myself dead.

The Spirit Guide from the Hell told me that the only way to revive my soul is for me to find the murderer, eliminating the grudge in my soul.

So I have to go back 24 hours ago and find the murderer from the perspective of my soul.

----------------
| Instructions |
----------------

Here are the commands that you can enter:
"/narrative-lines #NUM" - change number of narrative lines print each time to #NUM
"/help" - view this command list
"/next" - move on to the next scene or the next stage of a scene
"/note" - create a new note / open a note / delete a note
"/view" - view all the clues that you have gathered
"/restart" - restart the game from beginning
"/exit" - exit the game

Now, enter "/next" to start your journey to the truth.
```

>💡 Users can use /back at any scene

### Viewing the list of commands available: `/help`
Views the list of commands available.

Format: `/help`

Example of usage:

```
$ /help

Here are the list of commands available to you.
"/narrative-lines #NUM" - change number of narrative lines print each time to #NUM
"/help" - view this command list
"/next" - move on to the next scene or the next stage of a scene
"/back" - go back to previous scene
"/note" - create a new note / open a note / delete a note
"/view" - view all the clues that you have gathered
"/restart" - restart the game from beginning
Key in the index (e.g. 1, 2) in front of the suspect/clue you want to investigate
To investigate suspects or clues, please input their corresponding number.
"/exit" - exit the game
```

### Restarting the game: `/restart`
Restarts the game.

Example of usage:

```
Scene 1 Investigation
Who do you want to investigate?
1. Father

$ /restart

------------------
| Who Killed Me? |
------------------

I woke up and found myself dead.

The Spirit Guide from the Hell told me that the only way to revive my soul is for me to find the murderer, eliminating the grudge in my soul.

So I have to go back 24 hours ago and find the murderer from the perspective of my soul.

----------------
| Instructions |
----------------

Here are the commands that you can enter:
"/narrative-lines #NUM" - change number of narrative lines print each time to #NUM
"/help" - view this command list
"/next" - move on to the next scene or the next stage of a scene
"/note" - create a new note / open a note / delete a note
"/view" - view all the clues that you have gathered
"/restart" - restart the game from beginning
"/exit" - exit the game

Now, enter "/next" to start your journey to the truth.
```

>💡 Users can restart the game at any point.


### Choosing a suspect: `KEYWORD` or `INDEX`
Chooses a suspect using either the suspect's name or the suspect number.

Format: `[/investigate] KEYWORD` or `[/investigate] INDEX`

Examples of usage: 

* To investigate the suspect father, all the commands below are valid.
  * `1`
  * `father`
  * `/investigate father`

```
Scene 1 Investigation
Who do you want to investigate?
1. Father

$ /investigate father

Scene 1 Investigation
 - Father
0. Go back to list of suspects
1. Insurance Documents
2. Map
3. Phone Call
4. Text Message
Enter "/next" to go to the next scene.
```

* To choose father as the killer, all commands below are valid.
  * `1`
  * `father`
  * `/investigate father`

```
------------
| Scene #4 |
------------

It is now time for you to choose your killer.

Here are all the suspects
1. Father
2. Kevin
3. Wendy
4. Ling
5. Zack

Who do you think killed you?
$ father

-----------
| The End |
-----------

I'm back on the current timeline.
```

>💡`/investigate` is an optional command for the user.
>
> 💡Suspect name is not case-sensitive.
>
> ❗The user has to enter a valid suspect name or the suspect number.
> 
> ❗ Users are not allowed to go to the next scene before guessing the killer.


### Investigating a clue: `INDEX`
Investigates the clue based on the index.

Format: `INDEX`

- The index has to be a number based on the clue number given to the users to choose.

Example of usage:

```
Scene 1 Investigation
 - Father
0. Go back to list of suspects
1. Insurance Documents
2. Map
3. Phone Call
4. Text Message
Enter "/next" to go to the next scene.

$ 1

------------------------------------------------
              Insurance Documents
                __________
               ()_________)
                \ ~~~~~~~~ \
                  \ ~~~~~~   \
                    \__________\
                     ()__________)
I went to the room and asked my father to have
lunch. He hurriedly put away the paper on his
hand. I recognized it from the perspective of
my soul that it was a few insurance documents.
It seemed that my father bought insurance for
our family members a few years ago, amount
insured more than ten thousand.

Scene 1 Investigation
 - Father
0. Go back to list of suspects
1. Insurance Documents
2. Map
3. Phone Call
4. Text Message
Enter "/next" to go to the next scene.
```

>❗ Users can only use index to select the clue to investigate using index.

### Viewing checked clues: `/view`

Views the clues that have been gathered from investigations.

Format: `/view [NAME]...`

* `NAME(s)` provided must be one/more of the suspects' names.
* If valid names are provided, only clues gathered that are specific to those suspects will be shown.

Examples of usage:

> 💡 To avoid spoiling the plot of the game, both of the examples provided below describe the scenario where no clues have been gathered by the user yet.

 
* `/view` Displays all clues that have been gathered.

```
$ /view
Preparing the clues that you have gathered...

You have not gathered any clues for anyone.
```

* `/view father ling` Displays clues that have been gathered and are specific to Father and Ling respectively.

```
$ /view father ling
Preparing the clues that you have gathered...

<Father>
You have not gathered any clues for Father.
<Ling>
You have not gathered any clues for Ling.
```

>💡Suspect name is not case-sensitive.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Launch the app once on the new computer and exit. Afterwards, replace the data folder in the same folder of your new computer with that of your previous computer.

## Command Summary

|Action| Format, Examples |
|--------|----------|
| Changes the number of lines to be printed during narrative | `/narrative-lines NUM` e.g., `10` |
| Next | `/next` |
| Restart | `/restart` |
| Exit | `/exit` |
| Back | `/back` |
| Investigate/Choose Killer | `[/investigate] INDEX or KEYWORD` e.g., `/investigate father`, `father`, `1` |
| View Clues | `/view [NAME]...` e.g., `/view` `/view Father` `/view Father Ling` |
| Take Note | `/note <Command index>(optional)` |
| Quit note | `/quit` |
