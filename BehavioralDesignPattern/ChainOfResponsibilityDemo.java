/*
Chain of Responsibility is a behavioral design pattern that lets you pass requests along a chain of handlers. 
Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.
Refs - https://www.javatpoint.com/chain-of-responsibility-pattern , https://www.geeksforgeeks.org/chain-responsibility-design-pattern/
*/

abstract class Logger {
    public static final int INFO_LEVEL = 1;
    public static final int ERROR_LEVEL = 2;
    public static final int DEBUG_LEVEL = 3;
    protected int level;
    protected Logger nextLevelLogger;

    public void setNextLevelLogger(Logger nextLevelLogger) {
        this.nextLevelLogger = nextLevelLogger;
    }

    public void logMessage(int level, String msg) {
        if (level >= this.level) {
            logMessageForCurrentLevel(msg);
        }
        if (nextLevelLogger != null) {
            nextLevelLogger.logMessage(level, msg);
        }
    }

    public abstract void logMessageForCurrentLevel(String msg);

}

class InfoLogger extends Logger {
    public InfoLogger(int level) {
        this.level = level;
    }

    @Override
    public void logMessageForCurrentLevel(String msg) {
        System.out.println("LOGGER INFO: " + msg);
    }
}

class ErrorLogger extends Logger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    public void logMessageForCurrentLevel(String msg) {
        System.out.println("LOGGER ERROR: " + msg);
    }
}

class DebugLogger extends Logger {
    public DebugLogger(int level) {
        this.level = level;
    }

    @Override
    public void logMessageForCurrentLevel(String msg) {
        System.out.println("LOGGER DEBUG: " + msg);
    }
}

public class ChainOfResponsibilityDemo {
    private static Logger doLoggerChaining() {
        Logger infoLogger = new InfoLogger(Logger.INFO_LEVEL);
        Logger errorLogger = new ErrorLogger(Logger.ERROR_LEVEL);
        infoLogger.setNextLevelLogger(errorLogger);
        Logger debugLogger = new DebugLogger(Logger.DEBUG_LEVEL);
        errorLogger.setNextLevelLogger(debugLogger);
        return infoLogger;
    }

    public static void main(String[] args) {
        Logger logger = doLoggerChaining();
        logger.logMessage(1, "Information Given");
        logger.logMessage(2, "Error Given");
        logger.logMessage(3, "Debug info Given");
    }
}

/*
Steps to create chain of responsibility class ->
1. Create an abstract handler class and concrete classes implementation of it. 
2. This can be an interface which will primarily receive the request and dispatches the request to a chain of handlers.
3. One by one create concrete handler subclasses and implement their handling methods. Each handler should process the request and pass the request along the chain.
4. The client may either assemble chains on its own or receive pre-built chains from other objects.
*/