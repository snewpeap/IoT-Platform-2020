package edu.nju.se.yrd.iotconnmgmt.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TopicValidator.class)
public @interface TopicType {
    String message() default "Topic格式不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
