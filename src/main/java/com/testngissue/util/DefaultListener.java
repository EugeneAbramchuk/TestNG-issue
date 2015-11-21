package com.testngissue.util;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import org.testng.log4testng.Logger;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class DefaultListener implements IAnnotationTransformer {
    private static final String IGNORE_TESTS_FOR_THIS_TEAM = "Team2";

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        if (testMethod != null) {
            disableIfRequired(annotation, testMethod);
        }
    }

    private void disableIfRequired(ITestAnnotation annotation, Method testMethod) {
        Team team = testMethod.getAnnotation(Team.class);
        if(team == null) {
            return;
        }
        Logger logger = Logger.getLogger(testMethod.getDeclaringClass());

        if(!isEnabledForExecution(team)) {
            annotation.setEnabled(false);
            logger.info(String.format("Test %s skipped.", testMethod.getDeclaringClass() + "." +
                    testMethod));
        }
    }

    private boolean isEnabledForExecution(Team team) {
        return !team.name().equals(IGNORE_TESTS_FOR_THIS_TEAM);
        }
}