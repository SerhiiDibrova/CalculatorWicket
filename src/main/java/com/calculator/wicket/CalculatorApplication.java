package com.calculator.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Calculator Apache Wicket
 * 
 * @author Serhii Dibrova
 *
 */
public class CalculatorApplication extends WebApplication {
	
    public CalculatorApplication() {}


    @Override
    public Class<? extends Page> getHomePage() {
        return Calculator.class;
    }
}