package com.uwaytech;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:cucumber/test.feature", glue = {"com.uwaytech", "cucumber.api.spring"})
public class RunCukesTest {
}
