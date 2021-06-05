package ru.sibsutis.project.dto;

public class TradeDto {
    private final Long userId;
    private final Long sendId;
    private final Long receiveId;

    public TradeDto(Long userId, Long sendId, Long receiveId) {
        this.userId = userId;
        this.sendId = sendId;
        this.receiveId = receiveId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSendId() {
        return sendId;
    }

    public Long getReceiveId() {
        return receiveId;
    }

}
