/**
 * Cebu Technological University - Main Campus
 * File: A CLI Employee Portal in Java
 * Date: October 23, 2021
 * Programmer: Rian Rey Barriga
 * Section: BSIT-II-2
 */

package menu;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Scanner object to scan inputs
        Scanner scans = new Scanner(System.in);

        // List object of the Linkedlist
        EmployeeList list = new EmployeeList();

        // String option to Fetch the data using nextLine()
        String option;
        // The final variable that will parse option to Integer
        int choice = 0;


        // The do-while loop to have a menu-driven options, that will loop until
        // decided to exit by the user
        do {
            System.out.println("\n====== Welcome to Employee Portal ======");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Display All Employee");
            System.out.println("4. Get The Total Number of Employees");
            System.out.println("5. Get the Total Average of Salary");
            System.out.println("6. Delete an Employee based on the ID ");
            System.out.println("7. Display and count employee according to the salary range.");
            System.out.println("8. Exit");

            // A try-catch block to fetch an exception if there is any
            try {
                System.out.print("Enter answer: ");
                option = scans.nextLine();
                choice = Integer.parseInt(option);
            } catch (Exception e) {
                System.out.print("\nPlease enter an Integer!");
            }

            // A switch statement that will check the value of choice to execute
            // the function depending on the choice
            switch (choice) {
                // if the value of choice is 1, it will execute the list.addEmployee method
                case 1:
                    // variable initialization
                    int idNumber = 0;
                    double salary = 0.0;
                    boolean isUnique = true;

                    // a while loop to ask unser input until the the input value is corret
                    while (idNumber < 1) {
                        // a try catch block to fetch any error if there is any
                        try {
                            // loop until the ID is unique
                            while (isUnique) {
                                System.out.println("\nEnter employee's ID number: ");
                                String tempNumber = scans.nextLine();
                                idNumber = Integer.parseInt(tempNumber);
                                isUnique = list.iDChecker(idNumber);
                                // check for uniqueness of ID
                                if (isUnique) {
                                    System.out.print("\nThis ID already exists!");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("\nPlease enter an Integer!");
                        }
                    }
                    // asking for user input
                    System.out.println("\nEnter employee's firstname: ");
                    String firstName = scans.nextLine();
                    System.out.println("Enter employee's lastname: ");
                    String lastName = scans.nextLine();
                    // another while loop to ask for the value of salary in double format
                    // this will run until a correct data type is entered
                    while (salary < 1.0) {
                        // a try catch block to fetch potential exceptions
                        try {
                            System.out.println("\nEnter employee's salary: ");
                            String tempSalary = scans.nextLine();
                            salary = Double.parseDouble(tempSalary);
                        } catch (Exception e) {
                            System.out.print("Please integer a floating point number!");
                        }
                        if (salary == 0){
                            System.out.print("Please enter salary above 0!");
                        }
                    }

                    // invoking addEmployee method of class EmployeeList
                    list.addEmployee(firstName, lastName, idNumber, salary);
                    // break statement so it won't continue to the next case.
                    break;

                case 2:
                    // a try catch block to check whether there is a node in the linkedlist
                    try {
                        System.out.println("Search with firstname or lastname: ");
                        String searchQuery = scans.nextLine();
                        list.searchEmployee(searchQuery);
                    } catch (NullPointerException e) {
                        System.out.print("You have no employees!");
                    }
                    break;
                case 3:
                    // just a normal function call for displayAll method
                    list.displayAll();
                    break;
                case 4:
                    // function invocation for totalEmployee method
                    list.totalEmployee();
                    break;
                case 5:
                    // the function invocation to get the average salary of all employee
                    list.getAverageSalary();
                    break;
                case 6:
                    // variable initialization
                    int idToDelete = 0;
                    // a loop to continuously ask the user to input data type
                    while (idToDelete < 1) {
                        // a try catch block to in case of error
                        try {
                            System.out.println("\nEnter the ID to delete: ");
                            String tempIdToDelete = scans.nextLine();
                            idToDelete = Integer.parseInt(tempIdToDelete);
                            list.deleteEmployeeByID(idToDelete);
                            System.out.println("\n===== The new list =====");
                            list.displayAll();
                            // warning if there is no record
                        } catch (NullPointerException e) {
                            System.out.print("You don't have an employee!");
                        } catch (NumberFormatException e) { // warning if the data entered is not int
                            System.out.print("Please enter an Integer!");
                        }
                    }
                    break;
                case 7:
                    // a simple function call to sort in ascending order
                    list.sortEmployeesBySalary();
                    // after sorting, I'll dislpay it.
                    list.displayAll();
                    break;
                case 8:
                    // exit the menu if 8 is choosen
                    System.out.println("\nThis program will exit now!");
                    System.exit(0);
                    break;
                default:
                    // if you press something else other than the choices
                    // this will execute
                    System.out.println("\nThis is not a valid answer!");
                    System.out.println();
                    break;
            }
        } while (choice != 8); // defining the exit value which is 8
    }
}

// LinkedList blueprint
class Node {
    String firstName;
    String lastName;
    int idNumber;
    double salary;
    Node next;
}

// the actual implementation of LinkList
class EmployeeList {
    // The head node that will be the start
    Node head;

    // a method to add new employees to the LinkedList
    void addEmployee(String firstName, String lastName, int idNumber, double salary) {
        Node node = new Node();
        node.firstName = firstName;
        node.lastName = lastName;
        node.idNumber = idNumber;
        node.salary = salary;
        // check if this is the first node
        if (head == null) {
            head = node;
        } else { // check if it is not the first node
            Node pointer = head;
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = node;
        }
    }

    // a method that will search for the value in the linkedlist
    void searchEmployee(String param) {
        Node currentNode = head;
        Node lastNode = null;
        // just having a temporary variable that will be lowercased
        // to be more flexing in accepting user input
        String theFirstName = currentNode.firstName.toLowerCase();
        String theLastName = currentNode.lastName.toLowerCase();
        param = param.toLowerCase();
        // Printing out the detaiils
        System.out.print("ID\tLAST\tFIRST\tSALARY");

        while (currentNode != null) {
            if (param.equals(theFirstName) || param.equals(theLastName)) {
                System.out.printf("\n%d\t%s\t%s\t%.2f", currentNode.idNumber, currentNode.lastName, currentNode.firstName, currentNode.salary);
                break;
            }

            lastNode = currentNode;
            currentNode = currentNode.next;
            if (currentNode == null) {
                System.out.printf("\n%d\t%s\t%s\t%.2f", lastNode.idNumber, lastNode.lastName, lastNode.firstName, lastNode.salary);
            }
        }
    }


    // A method that will display all the employees
    void displayAll() {
        if (head == null) {
            System.out.println("There is no employee!");
            System.out.println();
        } else {
            Node displayerNode = head;

            System.out.print("ID\tLAST\tFIRST\tSALARY");
            while (displayerNode.next != null) {
                System.out.printf("\n%d\t%s\t%s\t%.2f", displayerNode.idNumber, displayerNode.lastName, displayerNode.firstName, displayerNode.salary);
                displayerNode = displayerNode.next;
            }
            System.out.printf("\n%d\t%s\t%s\t%.2f", displayerNode.idNumber, displayerNode.lastName, displayerNode.firstName, displayerNode.salary);
        }
    }

    // a method that keeps track of the total employees
    void totalEmployee() {
        Node totalNode = head;
        int total = 1;
        if (totalNode == null) {
            System.out.println("There is no employee!");
        } else {
            while (totalNode.next != null) {
                total++;
                totalNode = totalNode.next;
            }
            System.out.println("The total number of employee is " + total);
        }
    }

    // a method to get the average salary of each employee
    void getAverageSalary() {
        Node averageSalaryNode = head;
        double salaryCounter = 0.0, finalSalary;
        int listIndex = 1;
        if (head == null) {
            System.out.println("There is no employee!");
        } else {
            while (averageSalaryNode.next != null) {
                salaryCounter += averageSalaryNode.salary;
                listIndex++;
                averageSalaryNode = averageSalaryNode.next;
            }
            salaryCounter += averageSalaryNode.salary;
            finalSalary = salaryCounter / listIndex;
            System.out.printf("The average salary is %.2f", finalSalary);
        }
    }

    // a method that will delete a node depending on the passed ID
    void deleteEmployeeByID(int id) {
        Node ptr, preptr = new Node();
        ptr = head;
        if (id == head.idNumber) {
            head = head.next;
        } else {
            while (ptr.idNumber != id) {
                preptr = ptr;
                ptr = ptr.next;
            }
            preptr.next = ptr.next;
            ptr = null;
        }
    }

    // A method to sort employees by salary in descending order
    void sortEmployeesBySalary() {
        Node employeeNode = head;
        Node index = null;
        double holder;
        if (head == null) {
            System.out.println("There is no employee!");
        } else {
            while (employeeNode != null) {
                index = employeeNode.next;
                while (index != null) {
                    if (employeeNode.salary > index.salary) {
                        holder = employeeNode.salary;
                        employeeNode.salary = index.salary;
                        index.salary = holder;
                    }
                    index = index.next;
                }
                employeeNode = employeeNode.next;
            }
        }
    }

    // a method that keeps track of the length of the linkedlist
    int linkListLength() {
        int index = 1;
        Node counterNode = head;
        while (counterNode.next != null) {
            index++;
        }
        return index;
    }

    // a method to check if the ID already exists
    boolean iDChecker(int id) {
        Node checkerNode = head;
        while (checkerNode != null) {
            if (id == checkerNode.idNumber) {
                return true;
            }
            checkerNode = checkerNode.next;
        }
        return false;
    }
}