package com.clodi.aspect;

import java.util.Optional;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.clodi.model.Product;

/**
 * This could have a useful application in seeing what products are accessed
 * more often, store this data in db, and make a 'recommendations' section /
 * page.
 */
@Aspect
@Component
public class ProductAnalyticsAspect {

	private Logger logger = Logger.getLogger(ProductAnalyticsAspect.class.getName());

	@Around("@annotation(com.clodi.annotation.ProductAnalyticsLog)")
	public Object log(ProceedingJoinPoint joinPoint) {
		try {
			Object returned = joinPoint.proceed();
			if (returned instanceof Optional) {
				Optional<?> opt = (Optional<?>) returned;
				opt.ifPresent(o -> {
					if (o instanceof Product) {
						logger.info("Accessed " + o);
					}
				});
				return returned;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

}
