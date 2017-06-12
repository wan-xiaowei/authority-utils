package com.skb.authority.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ShiroPermissionInfo {

	/**
	 * 如果没有指定，默认反射当前类名+方法名
	 *
	 * @return
	 */
	String itemId() default "";

	String itemName();
}