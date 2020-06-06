package com.example.model.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.model.enumerate.CustomCellType;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColumnExcel {

	public int col() default 0;

	public String title() default "";

	public CustomCellType type() default CustomCellType._STRING;
}
