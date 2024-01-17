/*
Iterator is a behavioral design pattern that lets you traverse elements of a collection without exposing its underlying representation (list, stack, tree, etc.).
Refs - https://www.geeksforgeeks.org/iterator-pattern/
*/

import java.util.*;

// concrete product class
class Employee {
    private String name;     
    private String salary;
    public Employee(String name, String salary) {
        this.name = name;
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "Employee Details: " + name + " has a package of " + salary;
    }
}

// iterable interface
interface Iterable {
    public Iterator getIterator();
}

// iterator interface
interface Iterator {
    public boolean hasNext();
    public Object next();
}

// concrete iterator
class EmployeeIterator implements Iterator {
    List<Employee> employeeList;
    int currentIndex;
    public EmployeeIterator(List<Employee> employeeList) {
        this.employeeList = employeeList;
        this.currentIndex = -1;
    }
    @Override
    public boolean hasNext() {
        if(currentIndex < employeeList.size()-1) {
            return true;
        }
        return false;
    }
    @Override
    public Object next() {
        if(hasNext()) {
            this.currentIndex++;
            return employeeList.get(currentIndex);
        }
        return null;
    }
}

// concrete iterable
class EmployeeCollection implements Iterable {
    List<Employee> employeeList;
    public EmployeeCollection() {
        this.employeeList = new ArrayList<>();
    }
    public void addEmployee(String name, String salary){
        employeeList.add(new Employee(name, salary));
    }
    @Override
    public Iterator getIterator() {
        return new EmployeeIterator(employeeList);
    }
}

// client
class IteratorDemo {
    public static void main(String[] args) {
        EmployeeCollection empList = new EmployeeCollection();
        empList.addEmployee("A", "5LPA");
        empList.addEmployee("B", "9LPA");
        empList.addEmployee("C", "15LPA");
        empList.addEmployee("D", "22LPA");
        Iterator iterator = empList.getIterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }
}

/*
Steps to create iterator pattern class -> 
1. Declare iterable interface. This interface should provide a method to give reference to iterator.
2. Declare iterator interface. This interface should provide 2 methods to check whether next element exists and to get the next element.
3. Define concrete product class and it's collection class, this collection class should implement Iterable interface.
4. Declare concrete iterator class and implementation for those 2 methods.
5. Define a client class to frame a collection of product items and then uses iterator to iterate over the collection.
*/