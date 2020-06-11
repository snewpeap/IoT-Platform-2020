package edu.nju.se.yrd.iotconnmgmt.util;

import org.eclipse.paho.client.mqttv3.MqttTopic;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TopicValidator implements ConstraintValidator<TopicType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            isValid(value, true);
            return true;
        } catch (Exception e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Topic格式不合法: " + e.getLocalizedMessage()).addConstraintViolation();
            return false;
        }
    }

    public static void isValid(String topic, boolean wildcardAllowed) throws Exception {
        MqttTopic.validate(topic, wildcardAllowed);
        for (char c : topic.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                throw new IllegalArgumentException("含有非字母、数字或下划线(_)的字符");
            }
        }
    }
}
