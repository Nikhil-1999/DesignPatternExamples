/*
Builder is a creational design pattern that lets you construct complex objects step by step. 
The pattern allows you to produce different types and representations of an object using the same construction code.
It uses a builder class that contains the construction steps to create an object.
Refs - https://www.scaler.com/topics/design-patterns/builder-design-pattern/
*/

// product
class Customer {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phoneNo;

    Customer(CustomerBuilder customerBuilder) {
        this.firstName = customerBuilder.getFirstName();
        this.lastName = customerBuilder.getLastName();
        this.age = customerBuilder.getAge();
        this.email = customerBuilder.getEmail();
        this.phoneNo = customerBuilder.getPhoneNo();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Age: " + age + "\n" +
                "Email: " + email + "\n" +
                "phoneNo: " + phoneNo + "\n";
    }

}

// builder interface
interface ICustomerBuilder {
    ICustomerBuilder firstName(String firstName);

    ICustomerBuilder lastName(String lastName);

    ICustomerBuilder age(int age);

    ICustomerBuilder email(String email);

    ICustomerBuilder phoneNo(String phoneNo);
}

// concrete builder class
class CustomerBuilder implements ICustomerBuilder {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phoneNo;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public CustomerBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Override
    public CustomerBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public CustomerBuilder age(int age) {
        this.age = age;
        return this;
    }

    @Override
    public CustomerBuilder email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public CustomerBuilder phoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
        return this;
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public Customer build() {
        return new Customer(this);
    }

}

// client
public class BuilderDemo {
    public static void main(String[] args) {
        Customer customer1 = CustomerBuilder.builder().firstName("Nikhil").lastName("Sharma").age(24).build();
        Customer customer2 = CustomerBuilder.builder().firstName("Ram").age(42).email("abc@gmail.com").phoneNo("9712766712").build();
        System.out.println(customer1);
        System.out.println(customer2);
    }
}

/* 
Steps to create a builder class ->
1. Create the product class with builder as input in constructor for which we want to create the object.
2. Create builder interface and concrete builder class with builder and build method.
3. All methods in builder class should return back the object.
4. Create a client class to build the object step by step in the order required.
*/