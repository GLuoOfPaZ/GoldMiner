package jtest;

import minegame.HookTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author AliasQli
 * 2023/4/9
 */
public class Runner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(HookTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.print("HookTest successful: ");
        System.out.println(result.wasSuccessful());

        result = JUnitCore.runClasses(JunitTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.print("JunitTest successful: ");
        System.out.println(result.wasSuccessful());
    }
}