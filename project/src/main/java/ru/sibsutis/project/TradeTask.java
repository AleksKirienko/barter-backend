package ru.sibsutis.project;

import ru.sibsutis.project.crud.ProductService;
import ru.sibsutis.project.crud.TradeService;
import ru.sibsutis.project.databases.Trade;

import java.util.List;
import java.util.TimerTask;

public class TradeTask extends TimerTask {

    private final TradeService service;
    private final ProductService productService;

    public TradeTask(TradeService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
    }

    @Override
    public void run() {
        List<Trade> trades = service.sendOffer();
        trades.forEach(trade -> productService.deleteProductAfterExchange(trade.getSend().getId()));
    }
}
