package edu.nju.se.yrd.iotconnmgmt.util;

import org.eclipse.paho.client.mqttv3.MqttTopic;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.StringJoiner;

public class TopicValidator implements ConstraintValidator<TopicType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            MqttTopic.validate(value, true);
            return true;
        } catch (Exception e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Topic格式不合法: " + e.getLocalizedMessage()).addConstraintViolation();
            return false;
        }
    }

    public static void isValid(String topic, boolean inbound, boolean outbound, StringJoiner stringJoiner) {
        if (!inbound && !outbound) {
            stringJoiner.add("至少选择一个通信方向");
        } else if (outbound) {
            try {
                MqttTopic.validate(topic, false);
            } catch (Exception e) {
                stringJoiner.add("出向不允许使用通配符");
            }
            for (char c : topic.toCharArray()) {
                if (!Character.isLetterOrDigit(c) && c != '_' && c != '/') {
                    stringJoiner.add("含有非字母、数字或下划线(_)的字符");
                    break;
                }
            }
        }
    }
}
