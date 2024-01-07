/*
A Bridge Pattern says that just "decouple the functional abstraction from the implementation so that the two can vary independently".
Refs - https://refactoring.guru/design-patterns/bridge
*/

class Color {
    private String color;
    private String hexCode;

    Color(String color, String hexCode) {
        this.color = color;
        this.hexCode = hexCode;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }
}

abstract class Shape {
    protected Color color;

    Shape(Color color) {
        this.color = color;
    }

    public abstract void calculateArea();
}

class Square extends Shape {
    int length;

    Square(Color color, int length) {
        super(color);
        this.length = length;
    }

    @Override
    public void calculateArea() {
        System.out.println("Area of this " + color.getColor() + " Square is " + Math.pow(length, 2));
    }
}

class Rectangle extends Shape {
    int length;
    int breadth;

    Rectangle(Color color, int length, int breadth) {
        super(color);
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    public void calculateArea() {
        System.out.println("Area of this " + color.getColor() + " Rectangle is " + length * breadth);
    }
}

class BridgeDemo {
    public static void main(String[] args) {
        Color redColor = new Color("red", "#FF0000");
        Color blueColor = new Color("blue", "#0000FF");
        Shape square = new Square(redColor, 5);
        square.calculateArea();
        Shape rectangle = new Rectangle(blueColor, 5, 6);
        rectangle.calculateArea();
    }
}

/*
Steps to create a bridge class -> 
1. Create interfaces or abstract classes and their concrete implementations.
2. Instead of using inheritance in such scenarios create link using composition.
3. For Eg for creating req square needed to extend red color and square shape, this way everytime new object comes we have to extend the existing abstractions.
4. Instead color can be linked directly in shape and no new classes has to be created to create new shape of any color. This creates a bridge between 2 different implementations.
*/