/*
Decorator is a structural design pattern that lets you attach new behaviors to objects by placing these objects 
inside special wrapper objects that contain the behaviors. It is used to modify the functionality of an object at runtime. 
Refs - https://www.digitalocean.com/community/tutorials/decorator-design-pattern-in-java-example , https://refactoring.guru/design-patterns/decorator
*/

interface Car {
    public void assemble();
}

class BasicCar implements Car {
    @Override
    public void assemble() {
        System.out.print("Added Basic car features. ");
    }
}

class CarDecorator implements Car {
    protected Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public void assemble() {
        this.car.assemble();
    }
}

class SportsCar extends CarDecorator {
    public SportsCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print("Added Sports car features. ");
    }
}

class LuxuryCar extends CarDecorator {
    public LuxuryCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print("Added LuxuryCar car features. ");
    }
}

class DecoratorDemo {
    public static void main(String[] args) {
        Car basicCar = new BasicCar();
        basicCar.assemble();
        System.out.println();
        Car sportsCarWithBasic = new SportsCar(new BasicCar());
        sportsCarWithBasic.assemble();
        System.out.println();
        Car sportsAndLuxuryCarWithBasic = new LuxuryCar(new SportsCar(new BasicCar()));
        sportsAndLuxuryCarWithBasic.assemble();
    }
}

/*
Steps to create decorator class ->
1. Create an interface and concrete classes implementing the same interface.
2. Create an decorator class implementing the above same interface.
3. Create concrete classes extending decorator class.
4. Create objects in any order using the wrapper pattern.
*/