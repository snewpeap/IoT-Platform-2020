package edu.nju.se.yrd.iotconnmgmt.util;

public class TopicTool {
    public static String generateNameFromUserInput(String inputName, String deviceTemplateId) {
        return "/" + deviceTemplateId + "/${deviceId}/user" + inputName;
    }

    public static String generateTopicFromTemplate(String deviceId, String template) {
        return template.replaceFirst("\\$\\{deviceId}", deviceId);
    }

    public static String generateName(String deviceTemplateId, String deviceId, String subName) {
        return String.format("/%s/%s/user%s", deviceTemplateId, deviceId, subName);
    }
}
