/*
Memento is a behavioral design pattern that lets you save and restore the previous state of an object without revealing the details of its implementation.
Refs - https://www.javatpoint.com/memento-pattern
*/

import java.util.*;

class EmployeeInfo {
    private String employer;
    private String salary;
    private int joiningYear;

    public EmployeeInfo(String employer, String salary, int joiningYear) {
        this.employer = employer;
        this.salary = salary;
        this.joiningYear = joiningYear;
    }

    public String getEmployer() {
        return this.employer;
    }

    public String getSalary() {
        return this.salary;
    }

    public int getJoiningYear() {
        return this.joiningYear;
    }
}

// originator : the object for which the state is to be saved. It creates the memento and uses it in future to undo.
class EmployeeOriginator {
    private EmployeeInfo currentEmpInfo;

    public void setEmployeeState(EmployeeInfo currentEmpInfo) {
        this.currentEmpInfo = currentEmpInfo;
    }

    public EmployeeInfo getEmployeeState() {
        return this.currentEmpInfo;
    }

    public EmployeeMemento addEmployeeInfoToMemento() {
        return new EmployeeMemento(this.currentEmpInfo);
    }

    public void getEmployeeInfoFromMemento(EmployeeMemento memento) {
        this.currentEmpInfo = memento.getEmployeeInfo();
    }
}

// memento : the object that is going to maintain the state of originator. Its just a POJO.
class EmployeeMemento {
    private EmployeeInfo pastEmployeeInfo;

    public EmployeeMemento(EmployeeInfo empInfo) {
        this.pastEmployeeInfo = empInfo;
    }

    public EmployeeInfo getEmployeeInfo() {
        return this.pastEmployeeInfo;
    }
}

// caretaker : the object that keeps track of multiple memento. Like maintaining savepoints.
class EmployeeCaretaker {
    private List<EmployeeMemento> savedEmployeeMemento;

    public EmployeeCaretaker() {
        this.savedEmployeeMemento = new ArrayList<>();
    }

    public void addEmployeeMemento(EmployeeMemento empMemento) {
        this.savedEmployeeMemento.add(empMemento);
    }

    public EmployeeMemento getEmployeeMemento(int index) {
        return savedEmployeeMemento.get(index);
    }

    public List<EmployeeMemento> getSavedEmployeeMementos() {
        return this.savedEmployeeMemento;
    }
}

class MementoDemo {
    public static void main(String[] args) {
        EmployeeCaretaker caretaker = new EmployeeCaretaker();
        EmployeeOriginator originator = new EmployeeOriginator();
        originator.setEmployeeState(new EmployeeInfo("TCS", "4LPA", 2020));
        caretaker.addEmployeeMemento(originator.addEmployeeInfoToMemento());
        originator.setEmployeeState(new EmployeeInfo("Amazon", "24LPA", 2021));
        caretaker.addEmployeeMemento(originator.addEmployeeInfoToMemento());
        originator.setEmployeeState(new EmployeeInfo("Google", "40LPA", 2024));
        caretaker.addEmployeeMemento(originator.addEmployeeInfoToMemento());

        List<EmployeeMemento> savedEmployeeMemento = caretaker.getSavedEmployeeMementos();
        for (EmployeeMemento empMemento : savedEmployeeMemento) {
            originator.getEmployeeInfoFromMemento(empMemento);
            EmployeeInfo empInfo = originator.getEmployeeState();
            System.out.println("Employee joined " + empInfo.getEmployer() + " in Year: " + empInfo.getJoiningYear()
                    + " at the salary: " + empInfo.getSalary());
        }
    }
}

/*
Steps to create memento design pattern ->
1. Create the originator class, this class will contain the current state of an object.
2. Create the memento class. One by one, declare a set of fields that mirror the fields declared inside the originator class.
3. Make the memento class immutable. A memento should accept the data just once, via the constructor. The class should have no setters.
4. Add methods in originator that uses state and saves it to memento and one that restores state from memento.
5. Create a caretaker class that stores all the mementos, it provides a method to add the memento and get it from the existing list.
*/