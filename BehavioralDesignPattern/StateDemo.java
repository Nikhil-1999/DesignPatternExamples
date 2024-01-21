/*
A State Pattern says that "the class behavior changes based on its state".
In State Pattern, we create objects which represent various states and a context object whose behavior varies as its state object changes.
Refs - https://www.geeksforgeeks.org/state-design-pattern/
*/

interface MobileState {
    public void doActionOnCall();
}

class SilentMobileState implements MobileState {
    @Override
    public void doActionOnCall() {
        System.out.println("Getting call in silent mode.");
    }
}

class VibrationMobileState implements MobileState {
    @Override
    public void doActionOnCall() {
        System.out.println("Getting call in vibration mode.");
    }
}

class MobileStateController {
    private MobileState state;

    MobileStateController() {
        // by default not on silent mode
        this.state = new VibrationMobileState();
    }

    public void setSilentMode() {
        this.state = new SilentMobileState();
    }

    public void setVibrationMode() {
        this.state = new VibrationMobileState();
    }

    public void onCall() {
        this.state.doActionOnCall();
    }
}

class StateDemo {
    public static void main(String[] args) {
        MobileStateController sideButton = new MobileStateController();
        sideButton.onCall();
        sideButton.setSilentMode();
        sideButton.onCall();
    }
}

/*
Steps to create state design pattern ->
1. Create state interface and different state implementations of it.
2. Create a state controller class that will handle which state is to be used at the given time.
3. Use the client to set the desired state and handle the action.
*/