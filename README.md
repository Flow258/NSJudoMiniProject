NSJudo Fees Calculator Manual

 Overview
This is a console based application to handle athlete registration and calulate training fees, private coaching fees, and competition fees for North Sussex Judo: 'NSJudo Fees Calculator'. Athletes are categorized by weight, training plans are assigned and final cost of the month calculated based on participation.

This manual is written to help you use the system step by step.

---

 Table of Contents
getting started
1. [System Menu](#2-system-menu)
2. But the registration of [an athlete](#3-athlete-registration)
3. 4. Displaying All Athletes (See All Athletes)

### Topic:
### Rows:
4
5. #5 exiting and saving data
6. Fee level overview (#6: [Understanding Fees](#6-understanding-fees))
7. Error, or exception handling is #7.

---

 1. Getting Started

 Prerequisites
Make sure you have Java scheduled on your computer.

1. The NSJudo Fees Calculator code can be downloaded or cloned.
2. Open a terminal.
3. Go to the directory where the code is stored.
4. Run the program with:
   ```
   java interaction/FeeCalculator.java
   interaction.fee calculator java

Or just use java -jar ./NSJudoMiniProject.jar
in the jar file
   ```

After that, you’ll be prompted to enter main menu.

---

 2. System Menu

Upon launching the application, you will see the following menu:

```
Menu Options:
1. Register a New Athlete
2. Display All Athletes
3. Exit
Enter your option:
```

- Option 1: Register a new athlete.
- Option 2: Show all athletes who have registered.
- Option 3: Save the data and then exit the application.

---

 3. Athlete Registration

 Step-by-Step Guide

To register a new athlete, follow these steps:

1. From the Main Menu select Option 1.
2. Enter the Athlete's Name 
   Example:
   ```
   Enter the athlete's Name: John Doe.
   ```

3. Go to the Athlete's weight category  
   Program informs the user with available weight categories. Type in in kilograms for the category.  
   Example:
   ```
   Enter the category(kg): 90
   ```

4. Athlete's Current Weight  
   Tell the athlete’s current weight.  
   Example:
   ```
   Enter current weight (kg): 88.5
   ```

5. Select a Training Plan
   Choose from one of the three available plans by entering the number or name of the plan:  
   - (1) Beginner  
   - (2) Intermediate  
   - (3) Elite  
   
   Example:
   ```
   Enter your plan: 2
   ```

6. If there is a Number of Competitions (if applicable) enter it.  
   If you are an Intermediate or Elite athlete, you’ll be prompted to enter how many competitions you’ve competed in this month (0 or 1).  
   Example:
   ```
   Enter the number of competitions entered this month (0 or 1): 1
   ```

7. Fill in Number of Private Coaching Hours  
   Specify the number of private coaching hours this month (between 0 and 5) the athlete has attended.  
   Example:
   ```
   Enter the number of hours of private coaching (0 to 5): 3
   ```

As soon as the data is entered, the system will tell the athlete that they are successfully registered.

---

 4. Displaying All Athletes

From the main menu choose Option 2 to view all registered athletes and their details. The program will display information for each athlete, including:

- Athlete ID
- Name
- Current weight
- Training plan
- Competition - Fees (Training, Coaching)
- Total cost for the month
- Under / over the weight limit (status), weight category

Example output:
```
========================================
Athlete ID No: 1
Athlete						                  : Dave
Current Weight				             : 88.5 kg
Training Plan					             : Intermediate
Training Fee For Month			      : $120.00
Private Coaching Fee For Month	: $108.00
Total Competition Fee		       	: $22.00
Total Cost for the Month	     	: $250.00
Weight Category			            	: Middleweight
Weight Status					             : 1.5 kg under Middleweight limit
========================================
```

---

5. Exiting and Saving Data

From the Main Menu select "Option 3" to exit the system and save all registered athletes. The system will display the message:

```
Exiting saving data...
```

When you save, you just save your data in a file called `athletes.dat`. The program will next start, and will load this file, restoring the athletes’ information.
---

6. Understanding Fees

The system calculates three types of fees:

Training Fees
The training fee is based on the selected training plan:
- Beginner (2 sessions/week): $25.00/week ($100.00/month)
- Intermediate (3 sessions/week): $30.00/week ($120.00/month)
- Elite (5 sessions/week): $35.00/week ($140.00/month)

Private Coaching Fees
The price per hour for the coaching is $9.00. It allows athletes a maximum of 5 hours per week.

Example:  
For 3 hours of private coaching per week:  
Total times = 3 hours x 4 weeks = 12 hours
Total expenses = $9.00 x 12 hours = $108.00

 Competition Fees
Entry to competitions cost $22.00 per entry. Word only gets out that Intermediate, and Elite athletes can compete. Athletes can compete in up 1 competition per month.

---
 7. Error Handling

If there is an error pass, the system will give the user error message and prompts for valid input.

- If an invalid menu option is selected, you will see:
  ```
  Invalid option. Please try again.
  ```

- If an invalid input is provided (e.g., entering text where a number is expected), the system will display:
  ```
  Invalid input. Please enter a valid number.
  ```

- If a value outside the allowed range is entered (e.g., more than 5 hours of private coaching), the system will show:
  ```
  The number that you can enter is only in between 0 and 5.
  ```
---
