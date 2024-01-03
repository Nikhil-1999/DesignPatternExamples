/* 
Singleton is a creational design pattern that lets you ensure that a class has only one instance, 
while providing a global access point to this instance.
*/

class EmployeeSingleton {
    private static EmployeeSingleton emp = null;
    private EmployeeSingleton() {};

    public static EmployeeSingleton getEmployeeInstance() {
        if (emp == null) {
            synchronized(EmployeeSingleton.class) {
                if (emp == null) {
                    emp = new EmployeeSingleton();
                }
            }
        }
        return emp;
    }
}

class SingletonDemo {
    public static void main(String[] args) {
        EmployeeSingleton emp1 = EmployeeSingleton.getEmployeeInstance();
        EmployeeSingleton emp2 = EmployeeSingleton.getEmployeeInstance();

        if (emp1 == emp2) {
            System.out.println("Exactly same instance");
        }
    }
}

/*
Steps to create a singleton class ->
1. Make constructor as private so new method can't be used to initialize an object.
2. Now create an static getInstance method to get the object instance
3. We can do lazy initialization so it gets created only when the getInstance method is called and not when class is loaded.
4. In case multiple threads call getInstance method it can create multiple instances so we need to use synchronized keyword.
5. Using synchronized every time while creating the singleton object is expensive and may decrease the performance of your program.
6. Thus, we will instead use synchronized block only for code where new instance gets created.
7. Do null check again inside synchronized block as multiple threads can reach that statement.
*/