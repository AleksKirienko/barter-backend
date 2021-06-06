package ru.sibsutis.project.databases;

import ru.sibsutis.project.databases.Product;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "trades")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Product send;

    @OneToOne
    private Product receive;

    private Instant dateAndTime;

    public Trade(Product send, Product receive, Instant dateAndTime) {
        this.send = send;
        this.receive = receive;
        this.dateAndTime = dateAndTime;
    }

    public Trade() {}

    public Product getSend() {
        return send;
    }

    public void setSend(Product send) {
        this.send = send;
    }

    public Product getReceive() {
        return receive;
    }

    public void setReceive(Product receive) {
        this.receive = receive;
    }

    public Instant getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Instant dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
