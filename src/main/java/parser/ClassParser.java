package parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestNGListener;


public class ClassParser {
    private static final Logger logger = LogManager.getLogger(ClassParser.class);
    public static ITestNGListener getClassFromString(String className) {
        Class myClass;
        try {
            myClass = Class.forName("listener." + className);
            Object object=myClass.newInstance();
            return (ITestNGListener)object;
        } catch (ClassNotFoundException|IllegalAccessException |InstantiationException e) {
            logger.log(Level.ERROR, "wrong listener name");
        }
        return null;
    }
}
