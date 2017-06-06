package com.skb.authority.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ShiroPermissionInfo {


	String object_id();

	String object_name();

	String object_type();

	String parent_id() default "";

	String data_type();

	String description() default "";


}