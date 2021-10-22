package menu;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scans = new Scanner(System.in);
        EmployeeList list = new EmployeeList();
        String option;
        int choice;

        do{
            System.out.println("\n====== Welcome to Employee Portal ======");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Display All Employee");
            System.out.println("4. Get The Total Number of Employees");
            System.out.println("5. Get the Total Average of Salary");
            System.out.println("6. Delete an Employee based on the ID ");
            System.out.println("7. Display and count employee according to the salary range.");
            System.out.println("8. Exit");
            System.out.print("Enter answer: ");
            option = scans.nextLine();
            choice = Integer.parseInt(option);
            switch (choice){
                case 1:

                    System.out.println("Enter employee's ID number: ");
                    String tempNumber = scans.nextLine();
                    int idNumber = Integer.parseInt(tempNumber);
                    System.out.println("Enter employee's firstname: ");
                    String firstName = scans.nextLine();
                    System.out.println("Enter employee's lastname: ");
                    String lastName = scans.nextLine();
                    System.out.println("Enter employee's salary: ");
                    String tempSalary = scans.nextLine();
                    double salary = Double.parseDouble(tempSalary);
                    list.addEmployee(firstName, lastName, idNumber, salary);
                    break;

                case 2:
                    try{
                        System.out.println("Search with firstname or lastname: ");
                        String searchQuery = scans.nextLine();
                        list.searchEmployee(searchQuery);
                    }catch (NullPointerException e){
                        System.out.print("You have no employees!");
                    }
                    break;
                case 3:
                    list.displayAll();
                    break;
                case 4:
                    list.totalEmployee();
                    break;
                case 5:
                    list.getAverageSalary();
                    break;
                case 6:
                    System.out.println("Enter the ID to delete: ");
                    String tempIdToDelete = scans.nextLine();
                    int idToDelete = Integer.parseInt(tempIdToDelete);
                    list.deleteEmployeeByID(idToDelete);
                    break;
                case 7:
                    list.sortEmployeesBySalary();
                    list.displayAll();
                    break;
                case 8:
                    System.out.println("\nThis program will exit now!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nThis is not a valid answer!");
                    System.out.println();
                    break;
            }
        }while(choice != 8);
    }
}

class Node{
    String firstName;
    String lastName;
    int idNumber;
    double salary;
    Node next;
}

class EmployeeList{
    Node head;

    void addEmployee(String firstName, String lastName, int idNumber, double salary){
        Node node = new Node();
        node.firstName = firstName;
        node.lastName = lastName;
        node.idNumber = idNumber;
        node.salary = salary;

        if (head == null){
            head = node;
        }else{
            Node pointer = head;
            while(pointer.next != null){
                pointer = pointer.next;
            }
            pointer.next = node;
        }
    }

    int totalSize(){
        Node sizeNode = head;
        int counter = 0;
        while(sizeNode.next != null){
            counter++;
            sizeNode = sizeNode.next;
        }
        return counter;
    }

    void searchEmployee(String param) {
        Node currentNode = head;
        Node lastNode;
        String theFirstName = currentNode.firstName.toLowerCase();
        String theLastName = currentNode.lastName.toLowerCase();
        param = param.toLowerCase();
        System.out.print("ID\tLAST\tFIRST\tSALARY");
        System.out.println();

        while(currentNode != null){
            if (param.equals(theFirstName) || param.equals(theLastName)) {

                System.out.printf("\n%d\t%s\t%s\t%.2f", currentNode.idNumber, currentNode.lastName, currentNode.firstName, currentNode.salary);
            }
            lastNode = currentNode;
            currentNode = currentNode.next;
            if (currentNode == null){
                System.out.printf("\n%d\t%s\t%s\t%.2f", lastNode.idNumber, lastNode.lastName, lastNode.firstName, lastNode.salary);
            }
        }



    }



    void displayAll(){
        if(head == null){
            System.out.println("There is no employee!");
            System.out.println();
        }else{
            Node displayerNode = head;

            System.out.print("ID\tLAST\tFIRST\tSALARY");
            while(displayerNode.next != null){
                System.out.printf("\n%d\t%s\t%s\t%.2f", displayerNode.idNumber, displayerNode.lastName, displayerNode.firstName, displayerNode.salary);
                displayerNode = displayerNode.next;
            }
            System.out.printf("\n%d\t%s\t%s\t%.2f", displayerNode.idNumber, displayerNode.lastName, displayerNode.firstName, displayerNode.salary);
        }
    }

    void totalEmployee(){
        Node totalNode = head;
        int total = 1;
        if (totalNode == null){
            System.out.println("There is no employee!");
        }else{
            while(totalNode.next != null){
                total++;
                totalNode = totalNode.next;
            }
            System.out.println("The total number of employee is " + total);
        }
    }

    void getAverageSalary(){
        Node averageSalaryNode = head;
        double salaryCounter = 0.0, finalSalary;
        int listIndex = 1;
        if (head == null){
            System.out.println("There is no employee!");
        }else{
            while(averageSalaryNode.next != null){
                salaryCounter += averageSalaryNode.salary;
                listIndex++;
                averageSalaryNode = averageSalaryNode.next;
            }
            salaryCounter += averageSalaryNode.salary;
            finalSalary = salaryCounter/listIndex;
            System.out.printf("The average salary is %.2f", finalSalary);
        }
    }

    void deleteEmployeeByID(int id){
        Node ptr, preptr = new Node();
        ptr = head;

        while(ptr.idNumber != id) {
            preptr = ptr;
            ptr = ptr.next;
        }
        preptr.next = ptr.next;
        ptr = null;
    }

    void sortEmployeesBySalary(){
        Node employeeNode = head;
        Node index = null;
        double holder;
        if (head == null){
            System.out.println("There is no employee!");
        }else{
            while(employeeNode != null){
                index = employeeNode.next;
                while(index != null){
                    if(employeeNode.salary > index.salary){
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
}