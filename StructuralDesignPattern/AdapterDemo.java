/*
An Adapter Pattern says that just "converts the interface of a class into another interface that a client wants".
Refs - https://www.javatpoint.com/adapter-pattern
*/

// target interface
interface LightingCableCharger {
    public void onCharge();
}

// Adaptee - already existing implementation 
class TypeCCharger {
    public void charge() {
        System.out.println("Charging your phone");
    }
}

// Adapter - uses adaptee class to meet target requirements
class TypeCToLightningAdapter implements LightingCableCharger {
    private TypeCCharger typeCCharger;
    TypeCToLightningAdapter() {
        typeCCharger = new TypeCCharger();
    };
    @Override
    public void onCharge() {
        typeCCharger.charge();
    }
}

// client
class AdapterDemo {
    public static void main(String[] args) {
        LightingCableCharger appleCharger = new TypeCToLightningAdapter();
        appleCharger.onCharge();
    }
}

/*
Steps to create a adapter class -> 
1. It can be used where you have at least two classes with incompatible interfaces.
2. Make an adapter class that follows the target interface.
3. Extend adaptee class or make use of an instance and forward the service logic to adaptee class.
4. Thus client can now use target interface which internally uses adaptee class with the help of adapter class.
*/