package jtest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author AliasQli
 * 2023/4/9
 */
public class Runner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(JunitTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}