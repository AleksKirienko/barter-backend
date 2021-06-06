package ru.sibsutis.project.dto;

public class TradeDto {
    private final Long userId;
    private final ProductDtoWithId sendProduct;
    private final ProductDtoWithId receiveProduct;

    public TradeDto(Long userId, ProductDtoWithId sendProduct, ProductDtoWithId receiveProduct) {
        this.userId = userId;
        this.sendProduct = sendProduct;
        this.receiveProduct = receiveProduct;
    }

    public Long getUserId() {
        return userId;
    }

    public ProductDtoWithId getSendProduct() {
        return sendProduct;
    }

    public ProductDtoWithId getReceiveProduct() {
        return receiveProduct;
    }

}
