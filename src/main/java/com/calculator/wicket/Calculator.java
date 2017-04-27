package com.calculator.wicket;


import java.text.DecimalFormat;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

/**
 * 
 * @author Serhii Dibrova
 */

public class Calculator extends WebPage {

	private static final long serialVersionUID = 2422578309784934318L;

	public Calculator() {

		// binding form elements
		final TextField<String> valueShowField = new TextField<String>("valueshow", Model.of(""));

		Form<?> form = new Form<Object>("form") {

			// "="-button action
			@Override
			public void onSubmit() {

				String valueshow = (String) valueShowField.getModelObject();
				String result = "";

				try {
					result = calculate(valueshow);
				} catch (IllegalArgumentException e) {
					result = e.getMessage();
				}

				valueshow = String.format("%s%n= %s", valueshow, result);

				valueShowField.setModel(Model.of(result));

			}
		};

		form.add(valueShowField);

		Button clearButton = new Button("clearButton") {

			@Override
			public void onSubmit() {
				valueShowField.setModel(Model.of(""));
			}
		};

		clearButton.setDefaultFormProcessing(false);
		form.add(clearButton);

		add(form);
	}

	private String calculate(String valueshow) {

		String[] operations = valueshow.split(" ");

		Double result = Double.parseDouble(operations[0]);
		for (int i = 1; i + 1 < operations.length; i += 2) {

			Double operation;
			try {

				operation = Double.parseDouble(operations[i + 1]);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(String.format("Error"));
			}
			if (operations[i].equals("+")) {
				result += operation;
			} else if (operations[i].equals("-")) {
				result -= operation;
			} else if (operations[i].equals("*")) {
				result *= operation;
			} else if (operations[i].equals("/")) {
				result /= operation;
			}

		}

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(10);
		df.setMinimumFractionDigits(0);
		df.setGroupingUsed(true);

		return df.format(result);
	}
}
