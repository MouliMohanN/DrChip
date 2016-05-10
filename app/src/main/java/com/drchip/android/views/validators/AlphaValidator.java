package com.drchip.android.views.validators;

public class AlphaValidator extends RegexpValidator {
	public AlphaValidator(String message) {
		super(message, "[A-z\u00C0-\u00ff \\./-]*");
	}
}
