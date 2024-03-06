package com.clodi.validation;

import com.clodi.dto.SimpleUserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	private String message;
	private String password;

	@Override
	public void initialize(final PasswordMatches constraintAnnotation) {
		message = constraintAnnotation.message();
		password = constraintAnnotation.password();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		SimpleUserDTO user = (SimpleUserDTO) value;
		boolean equals = user.getPassword().equals(user.getMatchingPassword());

		if (!equals) {
			context.buildConstraintViolationWithTemplate(message).addPropertyNode(password).addConstraintViolation()
					.disableDefaultConstraintViolation();
		}

		return equals;
	}

}
