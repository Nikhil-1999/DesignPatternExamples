/*
A Command Pattern says that "encapsulate a request under an object as a command and pass it to invoker object. 
Invoker object looks for the appropriate object which can handle this command and pass the command to the corresponding object and that object executes the command".
Refs - https://www.digitalocean.com/community/tutorials/command-design-pattern , https://www.geeksforgeeks.org/command-pattern/
*/

// receiver interface
interface FileService {
    public void openFile();

    public void closeFile();

    public void writeFile();
}

// concrete receiver class
class LinuxFileService implements FileService {
    @Override
    public void openFile() {
        System.out.println("Opening file in Linux OS");
    }

    @Override
    public void closeFile() {
        System.out.println("Closing file in Linux OS");
    }

    @Override
    public void writeFile() {
        System.out.println("Writing file in Linux OS");
    }
}

// concrete receiver class
class WindowsFileService implements FileService {
    @Override
    public void openFile() {
        System.out.println("Opening file in Windows OS");
    }

    @Override
    public void closeFile() {
        System.out.println("Closing file in Windows OS");
    }

    @Override
    public void writeFile() {
        System.out.println("Writing file in Windows OS");
    }
}

// command interface
interface Command {
    public void execute();
}

// concrete command class
class FileOpenCommand implements Command {
    private FileService fileService;

    public FileOpenCommand(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void execute() {
        this.fileService.openFile();
    }
}

// concrete command class
class FileCloseCommand implements Command {
    private FileService fileService;

    public FileCloseCommand(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void execute() {
        this.fileService.closeFile();
    }
}

// concrete command class
class FileWriteCommand implements Command {
    private FileService fileService;

    public FileWriteCommand(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void execute() {
        this.fileService.writeFile();
    }
}

// invoker
class FileInvoker {
    private Command command;

    public FileInvoker(Command command) {
        this.command = command;
    }

    public void execute() {
        this.command.execute();
    }
}

// client
class CommandDemo {
    public static void main(String[] args) {
        FileService fs = new LinuxFileService();
        Command openFileCmd = new FileOpenCommand(fs);
        Command closeFileCmd = new FileCloseCommand(fs);
        Command writeFileCmd = new FileWriteCommand(fs);
        FileInvoker fileInvoker = new FileInvoker(openFileCmd);
        fileInvoker.execute();
        fileInvoker = new FileInvoker(writeFileCmd);
        fileInvoker.execute();
        fileInvoker = new FileInvoker(closeFileCmd);
        fileInvoker.execute();
    }
}

/*
Steps to create command pattern class ->
1. Declare the command interface with a single execution method.
2. Create concrete implementation of command interface. Command classes should have a reference to the receiver object to which the request will be redirected.
3. Create a receiver interface and concrete implementation. These are the classes which will finally handle the request.
4. Create a invoker class with a single execution method and the request will be redirected to the respective command object.
*/
