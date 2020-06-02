package com.rs.po;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Documented
@Target({ ElementType.FIELD, ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
	/**
	 * 标识 指定sec时间段内的访问次数限制
	 * 
	 * @Title: limit
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年7月14日 下午9:28:20
	 * @return
	 */
	int limit() default 5;

	/**
	 * 标识 时间段
	 * 
	 * @Title: sec
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年7月14日 下午9:28:24
	 * @return
	 */
	int sec() default 5;

}
