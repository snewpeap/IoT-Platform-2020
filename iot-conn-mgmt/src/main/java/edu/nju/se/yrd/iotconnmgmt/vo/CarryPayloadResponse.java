package edu.nju.se.yrd.iotconnmgmt.vo;

public class CarryPayloadResponse<T> extends BasicResponse {
    private T payload;

    public CarryPayloadResponse(boolean success, String message, T payload) {
        super(success, message);
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public CarryPayloadResponse<T> message(String message) {
        this.message = message;
        return this;
    }

    public static <T> CarryPayloadResponse<T> ok(T payload) {
        return new CarryPayloadResponse<>(true, "", payload);
    }

    public static <T> CarryPayloadResponse<T> error(T payload) {
        return new CarryPayloadResponse<>(false, "", payload);
    }

}
