/*
Prototype is a creational design pattern that lets you copy existing objects without making your code dependent on their classes.
Refs - https://www.codecademy.com/resources/docs/general/creational-design-patterns/prototype-pattern
*/

import java.lang.*;

interface Prototype extends Cloneable {
    public Prototype clone();
}

class EmployeePrototype implements Prototype {
    private String empId;
    private String name;
    private int age;

    EmployeePrototype(String empId, String name, int age) {
        this.empId = empId;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean hasSameValueAs(EmployeePrototype emp) {
        if (this.empId == emp.empId && this.name == emp.name && this.age == emp.age) {
            return true;
        }
        return false;
    }

    @Override
    public EmployeePrototype clone() {
        try {
            return (EmployeePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class PrototypeDemo {
    public static void main(String[] args) {
        EmployeePrototype emp1 = new EmployeePrototype("1", "Ram", 24);
        EmployeePrototype emp2 = emp1.clone();
        if (emp1 != emp2 && emp1.hasSameValueAs(emp2)) {
            System.out.println("Exactly same value instance");
        }
    }
}

/* 
Steps to create a prototype class ->
1. Create a interface prototype which extends Cloneable marker interface and override the given clone method.
2. This clone method does a shallow copy of objects and in case we need deep copy we can define our own method in prototype interface and that we can clone via new keyword.
3. Shallow copy: Copies the immediate property values and Deep copy: Copies the immediate values, plus any recursive referenced object.
*/