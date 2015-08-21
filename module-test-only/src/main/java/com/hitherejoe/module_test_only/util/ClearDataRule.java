package com.hitherejoe.module_test_only.util;

import com.hitherejoe.module_test_only.injection.TestComponentRule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Test rule that given a TestComponentRule clears the local data such as databases or
 * shared preferences
 */
public class ClearDataRule implements TestRule {

    private TestComponentRule mTestComponentRule;

    public ClearDataRule(TestComponentRule testComponentRule) {
        mTestComponentRule = testComponentRule;
    }

    public void clearData() {
        mTestComponentRule.getDatabaseHelper().clearTables().subscribe();
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    clearData();
                    base.evaluate();
                } finally {
                    mTestComponentRule = null;
                }
            }
        };
    }
}
