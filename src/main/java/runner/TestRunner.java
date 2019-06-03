package runner;

import util.Cli;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import parser.ClassParser;
import parser.ProjectProperties;

import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) {
        Cli cli = new Cli(args);
        cli.parse();
        getTest().run();
    }

    private static List<String> getSuite() {
        List<String> file = new ArrayList<>();
        String[] list = ProjectProperties.getProperties().getProperty("suite").split(",");
        for (String path : list) {
            file.add("src/main/resources/" + path + ".xml");
        }
        return file;
    }

    private static TestNG getTest() {
        TestNG testNG = new TestNG();
        ITestNGListener listener = ClassParser.getClassFromString(ProjectProperties.getProperties().getProperty("listener"));
        if (listener != null) {
            testNG.addListener(listener);
        }
        testNG.setSuiteThreadPoolSize(Integer.parseInt(ProjectProperties.getProperties().getProperty("thread")));
        testNG.setTestSuites(getSuite());
        return testNG;
    }

}
