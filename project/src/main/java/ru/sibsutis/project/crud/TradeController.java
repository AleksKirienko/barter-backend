package ru.sibsutis.project.crud;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sibsutis.project.Trade;

import java.util.List;

@RestController
@RequestMapping("/api/trade")
public class TradeController {
    private final TradeService service;

    public TradeController(TradeService service) {
        this.service = service;
    }

    @PostMapping("/")
    public List<Trade> getOffers() {
        return service.sendOffer();
    }
}
