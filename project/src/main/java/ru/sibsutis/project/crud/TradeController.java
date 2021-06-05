package ru.sibsutis.project.crud;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sibsutis.project.Trade;
import ru.sibsutis.project.databases.Product;
import ru.sibsutis.project.dto.ProductDtoWithId;
import ru.sibsutis.project.dto.TradeDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trade")
public class TradeController {
    private final TradeService service;

    public TradeController(TradeService service) {
        this.service = service;
    }

    private TradeDto copyToDto(Trade trade) {
        return new TradeDto(
                trade.getSend().getOwner().getId(),
                trade.getSend().getId(),
                trade.getReceive().getId()
        );
    }

    private List<TradeDto> copyToDto(List<Trade> trades) {
        return trades.stream().map(this::copyToDto).collect(Collectors.toList());
    }

    @PostMapping("/")
    public List<TradeDto> getOffers() {
        return copyToDto(service.sendOffer());
    }
}
