package ru.sibsutis.project.crud;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sibsutis.project.databases.Product;
import ru.sibsutis.project.databases.Trade;
import ru.sibsutis.project.dto.ProductDtoWithId;
import ru.sibsutis.project.dto.TradeDto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trade")
public class TradeController {
    private final TradeService service;

    public TradeController(TradeService service) {
        this.service = service;
    }

    private ProductDtoWithId copyToDto(Product product) {
        ProductDtoWithId productDto = new ProductDtoWithId();
        BeanUtils.copyProperties(product, productDto, "owner", "status", "userFaves", "productsForExchange");
        productDto.setOwnerId(product.getOwner().getId());
        return productDto;
    }

    private TradeDto copyToDto(Trade trade) {
        return new TradeDto(
                trade.getSend().getOwner().getId(),
                copyToDto(trade.getSend()),
                copyToDto(trade.getReceive())
        );
    }

    private List<TradeDto> copyToDto(List<Trade> trades) {
        return trades.stream().map(this::copyToDto).collect(Collectors.toList());
    }

    @PostMapping("/get_by_userId")
    public List<TradeDto> getOffersByUserId(@RequestParam Long userId) {
        return copyToDto(service.getByUserId(userId));
    }
}
