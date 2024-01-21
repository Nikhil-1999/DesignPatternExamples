/*
Strategy (policy) is a behavioral design pattern that lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable.
Refs - https://www.javatpoint.com/strategy-pattern 
*/

interface Strategy {
    public int execute(int a, int b);
}

class Addition implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a + b;
    }
}

class Multiplication implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a * b;
    }
}

class StrategyController {
    private Strategy strategy;
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int calculate(int a, int b) {
        return strategy.execute(a, b);
    }
}

public class StrategyDemo {
    public static void main(String[] args) {
        StrategyController sc = new StrategyController();
        sc.setStrategy(new Addition());
        System.out.println(sc.calculate(5, 10));
        sc.setStrategy(new Multiplication());
        System.out.println(sc.calculate(5, 10));
    }
}

/*
Steps to create strategy design pattern ->
1. Create strategy interface and various algo implementations of it.
2. Create controller class that provides a way to set the strategy.
3. Client will initiate controller and the strategy to follow. Controller will delegate the execution further to strategy.
 */