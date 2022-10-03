package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, description = "output format: [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish", paramLabel = "format")
    private String format;

    @Parameters(index = "0", description = "path to first file", paramLabel = "filePath1")
    private String filePath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filePath2")
    private String filePath2;

    @Override
    public Integer call() throws Exception {
        String difference = Differ.generate(filePath1, filePath2);
        System.out.println(difference);
        return 0;
    }


    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
