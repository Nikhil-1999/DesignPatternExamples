/*
Observer is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing.
Refs - https://www.geeksforgeeks.org/observer-pattern-set-2-implementation/ 
*/

import java.util.*;
import java.time.*;
import java.time.format.*;

// event source
interface Observable {
    public void registerObserver(Observer obv);

    public void removeObserver(Observer obv);

    public void updateObserver();
}

class ProductInventory implements Observable {
    private String brandName;
    private String productName;
    private int itemsLeftInStock;
    private String nextRestockingDate;
    private List<Observer> observerList;

    public ProductInventory(String brandName, String productName) {
        this.brandName = brandName;
        this.productName = productName;
        this.observerList = new ArrayList<>();
    }

    public void setItemsLeftInStock(int itemsLeftInStock) {
        this.itemsLeftInStock = itemsLeftInStock;
    }

    public void setNextRestockingDate(String nextRestockingDate) {
        this.nextRestockingDate = nextRestockingDate;
    }

    @Override
    public void registerObserver(Observer obv) {
        observerList.add(obv);
    }

    @Override
    public void removeObserver(Observer obv) {
        observerList.remove(obv);
    }

    @Override
    public void updateObserver() {
        for (Observer obv : observerList) {
            String itemName = this.brandName + " " + this.productName;
            obv.update(itemName, itemsLeftInStock, nextRestockingDate);
        }
    }
}

// event listener
interface Observer {
    public void update(String itemName, int itemsLeftInStock, String nextRestockingDate);
}

class OfflineStore implements Observer {
    private String itemName;
    private int itemsLeftInStock;
    private String nextRestockingDate;

    @Override
    public void update(String itemName, int itemsLeftInStock, String nextRestockingDate) {
        this.itemName = itemName;
        this.itemsLeftInStock = itemsLeftInStock;
        this.nextRestockingDate = nextRestockingDate;
        displayOfflineStoreInventoryStatus();
    }

    public void displayOfflineStoreInventoryStatus() {
        System.out.println("Offline Store Inventory Status: ");
        System.out.println(itemsLeftInStock + " items left in stock for " + itemName);
        System.out.println("Next restocking date is: " + nextRestockingDate);
    }
}

class OnlineWebsite implements Observer {
    private String itemName;
    private int itemsLeftInStock;
    private String nextRestockingDate;

    @Override
    public void update(String itemName, int itemsLeftInStock, String nextRestockingDate) {
        this.itemName = itemName;
        this.itemsLeftInStock = itemsLeftInStock;
        this.nextRestockingDate = nextRestockingDate;
        displayOnlineWebsiteInventoryStatus();
    }

    public void displayOnlineWebsiteInventoryStatus() {
        System.out.println("Offline Store Inventory Status: ");
        System.out.println(itemsLeftInStock + " items left in stock for " + itemName);
        System.out.println("Next restocking date is: " + nextRestockingDate);
    }
}

class ObserverDemo {
    public static void main(String[] args) {
        ProductInventory macbookProInventory = new ProductInventory("Apple", "macbook Pro 2023");
        macbookProInventory.setItemsLeftInStock(3);
        LocalDateTime ldt = LocalDateTime.now().plusDays(3);
        macbookProInventory.setNextRestockingDate(ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        OfflineStore store = new OfflineStore();
        OnlineWebsite site = new OnlineWebsite();
        macbookProInventory.registerObserver(store);
        macbookProInventory.registerObserver(site);
        macbookProInventory.updateObserver();
    }
}

/*
Steps to create observer design pattern -> 
1. Create an interface observable which will provide methods to add/remove observers and update observer.
2. Create an interface observer which will have a method update to update it's internal state based on event source's call.
3. Create concrete implementations of observer and observable interface.
4. Create client where we can add/remove observer and call update on event source that will trigger update on all the observers.
*/