package ru.sibsutis.project;

import ru.sibsutis.project.crud.TradeService;

import java.util.TimerTask;

public class TradeTask extends TimerTask {

    private final TradeService service;

    public TradeTask(TradeService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.sendOffer();
    }
}
