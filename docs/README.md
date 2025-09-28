# Speed User Guide
![Product Screenshot](./images/1.png)


Speed is a command-line chatbot task manager designed to allow user to track and manage their daily
tasks efficiently. Through simple interactive interface, Speed lets user add different tasks that can be tagged as todo, 
deadline and events. These events can be marked as done and undone and can also be deleted. 
These tasks are saved automatically oncer every task is added to the list. With robust error handling and easy to 
understand prompts, Speed ensure task management is straight forward for anyone to easily follow.

## Adding Deadlines

Adds a new Deadline task with a description and a due date.

Example: `deadline Submit report /by Friday`

```
Now you have X tasks in the list
____________________________________________________________

```

## Adding Event

Adds a new Event task with a description, start time, and end time.

Example: `event Team meeting /from Mon 2pm /to Mon 4pm`

```
Now you have X tasks in the list
____________________________________________________________

```
## Adding Todo

Adds a new TODO task with a description.

Example: `todo Submit report`

```
Now you have X tasks in the list
____________________________________________________________

```

## Listing Tasks Feature

Displays the current list of tasks with their indexes and status.

Example: `list`

```
1. [D][ ] Submit report(by:Friday)
2. [E][ ] Team meeting(from:Mon 2pm|to:Mon 4pm)
3. [T][ ] Submit report
```

## Marking Tasks as Done

Marks the task at the given index as done.

Example: `mark 1`

```
1. [D][X] Submit report(by:Friday)
2. [E][ ] Team meeting(from:Mon 2pm|to:Mon 4pm)
3. [T][ ] Submit report
```

## Unmarking Marked Tasks as Undone

Marks the task at the given index as not done.

Example: `unmark 1`
``` 
1. [D][ ] Submit report(by:Friday)
2. [E][ ] Team meeting(from:Mon 2pm|to:Mon 4pm)
3. [T][ ] Submit report
```

## Deleting Tasks

Delete tasks that are not needed anymore.

Example: `delete 1`

## Find Tasks

Finds and displays tasks containing the provided keyword (case-insensitive).

Example: `find groceries`

```
[D][ ] Submit report(by:Friday)
[T][ ] Submit report
```

## Saving Tasks

Automatically saves the current list of tasks to the storage file.

Example: Not user-invoked directly, happens after each modifying command.

## Setting up in Intellij

1. Open Intellij (if you are not in the welcome screen, click `File > Close Project` to close the existing project first)
2. Open the project into Intellij as follows:\
   i. Click `Open`.\
   ii. Select the project directory, and click `OK`.\
   iii. If there are any further prompts, accept the defaults.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is 
   showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as 
   the output:
```
This Speed :)
          _____                    _____                    _____                    _____                    _____                  
         /\    \                  /\    \                  /\    \                  /\    \                  /\    \                 
        /::\    \                /::\    \                /::\    \                /::\    \                /::\    \                
       /::::\    \              /::::\    \              /::::\    \              /::::\    \              /::::\    \               
      /::::::\    \            /::::::\    \            /::::::\    \            /::::::\    \            /::::::\    \              
     /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \             
    /:::/__\:::\    \        /:::/__\:::\    \        /:::/__\:::\    \        /:::/__\:::\    \        /:::/  \:::\    \            
    \:::\   \:::\    \      /::::\   \:::\    \      /::::\   \:::\    \      /::::\   \:::\    \      /:::/    \:::\    \           
  ___\:::\   \:::\    \    /::::::\   \:::\    \    /::::::\   \:::\    \    /::::::\   \:::\    \    /:::/    / \:::\    \          
 /\   \:::\   \:::\    \  /:::/\:::\   \:::\____\  /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \  /:::/    /   \:::\ ___\         
/::\   \:::\   \:::\____\/:::/  \:::\   \:::|    |/:::/__\:::\   \:::\____\/:::/__\:::\   \:::\____\/:::/____/     \:::|    |        
\:::\   \:::\   \::/    /\::/    \:::\  /:::|____|\:::\   \:::\   \::/    /\:::\   \:::\   \::/    /\:::\    \     /:::|____|        
 \:::\   \:::\   \/____/  \/_____/\:::\/:::/    /  \:::\   \:::\   \/____/  \:::\   \:::\   \/____/  \:::\    \   /:::/    /         
  \:::\   \:::\    \               \::::::/    /    \:::\   \:::\    \       \:::\   \:::\    \       \:::\    \ /:::/    /          
   \:::\   \:::\____\               \::::/    /      \:::\   \:::\____\       \:::\   \:::\____\       \:::\    /:::/    /           
    \:::\  /:::/    /                \::/____/        \:::\   \::/    /        \:::\   \::/    /        \:::\  /:::/    /            
     \:::\/:::/    /                  ~~               \:::\   \/____/          \:::\   \/____/          \:::\/:::/    /             
      \::::::/    /                                     \:::\    \               \:::\    \               \::::::/    /              
       \::::/    /                                       \:::\____\               \:::\____\               \::::/    /               
        \::/    /                                         \::/    /                \::/    /                \::/____/                
         \/____/                                           \/____/                  \/____/                  ~~                      
                                                                                                                                     
How can i assist in your planning of the day?
____________________________________________________________

```