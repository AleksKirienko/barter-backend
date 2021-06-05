package ru.sibsutis.project.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sibsutis.project.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
