/*
 A Factory Method Pattern says that just define an interface or abstract class for creating an object
 but let the subclasses decide which class to instantiate.
 Refs - https://www.javatpoint.com/factory-method-design-pattern
 */

 // product 
abstract class Shipment {
    protected int shippingPricePerKg;
    protected int daysTakenToShip;

    public abstract int getshippingPricePerKg();

    public abstract int getDaysTakenToShip();

    public int getTotalShipmentPrice(int kg) {
        return kg * shippingPricePerKg;
    }
}

 // concrete product 
class FlightShipment extends Shipment {
    public FlightShipment() {
        shippingPricePerKg = 2000;
        daysTakenToShip = 3;
    }

    @Override
    public int getshippingPricePerKg() {
        return shippingPricePerKg;
    }

    @Override
    public int getDaysTakenToShip() {
        return daysTakenToShip;
    }
}

class CruiseShipment extends Shipment {
    public CruiseShipment() {
        shippingPricePerKg = 1000;
        daysTakenToShip = 5;
    }

    @Override
    public int getshippingPricePerKg() {
        return shippingPricePerKg;
    }

    @Override
    public int getDaysTakenToShip() {
        return daysTakenToShip;
    }
}

class ShipmentFactory {
    public Shipment getShipment(String desiredShippingType) {
        if (desiredShippingType.equalsIgnoreCase("Flight")) {
            return new FlightShipment();
        }
        if (desiredShippingType.equalsIgnoreCase("Ship")) {
            return new CruiseShipment();
        }
        return null;
    }
}

// client
class FactoryDemo {
    public static void main(String[] args) {
        ShipmentFactory sf = new ShipmentFactory();
        System.out
                .println("Price of shipping 5kg parcel via Cruise: " + sf.getShipment("Ship").getTotalShipmentPrice(5));
        System.out.println(
                "Price of shipping 5kg parcel via Flight: " + sf.getShipment("Flight").getTotalShipmentPrice(5));
    }
}

/*
Steps to create a Factory class ->
1. Make an abstract class or interface for the same product type and make all the concrete product classes.
2. Make a factory class with factory method in it, which returns the required product instance based on some logic.
3. Create a client class which uses the instance of factory class to get the required instance of product at runtime instead of hard coding the product type.
*/