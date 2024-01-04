/*
 Abstract Factory Pattern says that just define an interface or abstract class for creating families of related (or dependent) objects
 but without specifying their concrete sub-classes.That means Abstract Factory lets a class returns a factory of classes. 
 So, this is the reason that Abstract Factory Pattern is one level higher than the Factory Pattern.
 Refs - https://www.geeksforgeeks.org/abstract-factory-pattern/
 */

abstract class Car {
    String fuelType;
    String countryOfOrigin;
    public String getFuelType() {
        return fuelType;
    }    
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }
}

class PetrolCar extends Car {
    public PetrolCar(String countryOfOrigin) {
        this.fuelType = "petrol";
        this.countryOfOrigin = countryOfOrigin;
    }
}

class DieselCar extends Car {
    public DieselCar(String countryOfOrigin) {
        this.fuelType = "diesel";
        this.countryOfOrigin = countryOfOrigin;
    }
}

abstract class CarFactory {
    String factoryLocation;
    public String getFactoryLocation() {
        return factoryLocation;
    }
    abstract Car getCar(String fuelType);
}

class IndianCarFactory extends CarFactory {
    public IndianCarFactory() {
        this.factoryLocation = "India";
    }
    @Override
    public Car getCar(String fuelType) {
        if(fuelType.equalsIgnoreCase("diesel")) {
            return new DieselCar(this.factoryLocation);
        }
        return new PetrolCar(this.factoryLocation);
    }
}

class USCarFactory extends CarFactory {
    public USCarFactory() {
        this.factoryLocation = "US";
    }
    @Override
    public Car getCar(String fuelType) {
        if(fuelType.equalsIgnoreCase("diesel")) {
            return new DieselCar(this.factoryLocation);
        }
        return new PetrolCar(this.factoryLocation);
    }
}

class FactoryCreator {
        public static CarFactory getCarFactory(String countryOfOrigin) {
        if(countryOfOrigin.equalsIgnoreCase("India")){
            return new IndianCarFactory();
        }
        return new USCarFactory();
    }
}

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        CarFactory carFactory1 = FactoryCreator.getCarFactory("India");
        Car c1 = carFactory1.getCar("petrol");
        CarFactory carFactory2 = FactoryCreator.getCarFactory("US");
        Car c2 = carFactory2.getCar("diesel");
        System.out.println(c1.getFuelType() + " car made in " + c1.getCountryOfOrigin());
        System.out.println(c2.getFuelType() + " car made in " + c2.getCountryOfOrigin());
    }
}

/*
Steps to create a Factory class ->
1. Make an abstract class or interface for the same product type and make all the concrete product classes (Car).
2. Make an abstract class or interface for the various factories and all concrete factory classes (CarFactory).
3. Make a factory creator class which chooses which factory instance to use to create the final product.
4. Create a client class which instatiaties the factory type at runtime and uses it to create desired product type at runtime.
*/