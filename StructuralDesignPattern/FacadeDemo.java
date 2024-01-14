/*
A Facade Pattern says that just "just provide a unified and simplified interface to a set of interfaces in a subsystem, 
therefore it hides the complexities of the subsystem from the client".
Refs - https://www.geeksforgeeks.org/facade-design-pattern-introduction/ 
*/

import java.util.*;

// facade interface
interface HostelWarden {
    public void getVegMenu();

    public void getNonVegMenu();
}

interface HostelMess {
    public String getBreakfastMenu();

    public String getLunchMenu();

    public String getDinnerMenu();
}

class VegMess implements HostelMess {
    @Override
    public String getBreakfastMenu() {
        Set<String> breakfastMenu = Set.of("Tea", "Samosa", "Sandwich");
        return String.join(", ", breakfastMenu);
    }

    @Override
    public String getLunchMenu() {
        Set<String> lunchMenu = Set.of("PaneerThali", "VegThali", "Dosa");
        return String.join(", ", lunchMenu);
    }

    @Override
    public String getDinnerMenu() {
        Set<String> dinnerMenu = Set.of("Pizza", "Pasta", "Khichdi");
        return String.join(", ", dinnerMenu);
    }
}

class NonVegMess implements HostelMess {
    @Override
    public String getBreakfastMenu() {
        Set<String> breakfastMenu = Set.of("BoiledEggs", "omelette", "EggSandwich");
        return String.join(", ", breakfastMenu);
    }

    @Override
    public String getLunchMenu() {
        Set<String> lunchMenu = Set.of("ChickenThali", "NonVegThali", "ChickenBurger");
        return String.join(", ", lunchMenu);
    }

    @Override
    public String getDinnerMenu() {
        Set<String> dinnerMenu = Set.of("NonVegPizza", "MuttonThali", "NonVegLasgna");
        return String.join(", ", dinnerMenu);
    }
}

// facade concrete class
class HostelWardenService implements HostelWarden {
    HostelMess vegMess;
    HostelMess nonVegMess;

    public HostelWardenService() {
        this.vegMess = new VegMess();
        this.nonVegMess = new NonVegMess();
    }

    @Override
    public void getVegMenu() {
        System.out.println("----VEG MENU----");
        System.out.println("Breakfast - " + vegMess.getBreakfastMenu());
        System.out.println("Lunch - " + vegMess.getLunchMenu());
        System.out.println("Dinner - " + vegMess.getDinnerMenu() + "\n");
    }

    @Override
    public void getNonVegMenu() {
        System.out.println("----NON-VEG MENU----");
        System.out.println("Breakfast - " + nonVegMess.getBreakfastMenu());
        System.out.println("Lunch - " + nonVegMess.getLunchMenu());
        System.out.println("Dinner - " + nonVegMess.getDinnerMenu() + "\n");
    }
}

// client 
class FacadeDemo {
    public static void main(String[] args) {
        HostelWarden hostelWarden = new HostelWardenService();
        hostelWarden.getVegMenu();
        hostelWarden.getNonVegMenu();
    }
}

/*
Steps to create facade design pattern ->
1. We can implement this if a interface can make the client code independent from many of the subsystem’s classes and expose only the required implementation details.
2. The facade should redirect the calls from the client code to appropriate objects of the subsystem. 
3. The facade should be responsible for initializing the subsystem and managing its further life cycle.
*/