/*
Composite is a structural design pattern that lets you compose objects into tree structures and 
then work with these structures as if they were individual objects. It allows treating complex and primitive objects uniformly.
Refs - https://refactoring.guru/design-patterns/composite , https://www.geeksforgeeks.org/composite-design-pattern-in-java/ 
*/

import java.util.stream.*;
import java.util.*;

// Component
interface Item {
    public int getItemSellingPrice();

    public List<String> getItemNameList();

    public void showItemDetails();
}

// Composite
class Product implements Item {
    private String name;
    private int retailPrice;

    public Product(String name, int retailPrice) {
        this.name = name;
        this.retailPrice = retailPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public void setPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Override
    public int getItemSellingPrice() {
        // selling 10% above mrp
        return (int) (1.1 * (retailPrice));
    }

    @Override
    public List<String> getItemNameList() {
        List<String> itemListName = new ArrayList<>();
        itemListName.add(name);
        return itemListName;
    }

    @Override
    public void showItemDetails() {
        String detail = String.format("Price for item list: %s is Rs.%s", this.getItemNameList(),
                this.getItemSellingPrice());
        System.out.println(detail);
    }
}

// Leaf
class Box implements Item {
    private List<Item> itemList;

    public Box() {
        itemList = new ArrayList<>();
    }

    public Box(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void removeItem(Item item) {
        itemList.remove(item);
    }

    @Override
    public List<String> getItemNameList() {
        List<String> itemListName = new ArrayList<>();
        for (Item item : itemList) {
            itemListName.addAll(item.getItemNameList());
        }
        return itemListName;
    }

    @Override
    public int getItemSellingPrice() {
        int boxSellingPrice = 0;
        for (Item item : itemList) {
            boxSellingPrice += item.getItemSellingPrice();
        }
        return boxSellingPrice;
    }

    @Override
    public void showItemDetails() {
        String detail = String.format("Price for item list: %s is Rs.%s", this.getItemNameList(),
                this.getItemSellingPrice());
        System.out.println(detail);
    }
}

// Client
class CompositeDemo {
    public static void main(String[] args) {
        Product p1 = new Product("soap", 60);
        Product p2 = new Product("handwash", 100);
        Product p3 = new Product("perfume", 250);
        Product p4 = new Product("watch", 2500);
        p1.showItemDetails();
        Box box1 = new Box();
        box1.addItem(p1);
        box1.addItem(p2);
        box1.addItem(p3);
        box1.showItemDetails();
        Box box2 = new Box();
        box2.addItem(box1);
        box2.addItem(p4);
        box2.showItemDetails();
    }
}

/*
Steps to create a composite class -> 
1. Create a component interface that tells the general structure / methods that both leaf and composite follows.
2. Create a leaf class to represent simple elements. A program may have multiple different leaf classes.
3. Create a composite class to represent complex elements. In this class, provide an array field for storing references to sub-elements. 
4. The array must be able to store both leaves and composites, so make sure it’s declared with the component interface type.
5. Define the methods for adding and removal of child elements in the composite class.
6. While implementing the methods of the component interface, remember that a composite is supposed to be delegating most of the work to sub-elements.
*/