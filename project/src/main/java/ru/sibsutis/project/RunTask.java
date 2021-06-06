package ru.sibsutis.project;

import org.springframework.stereotype.Component;
import ru.sibsutis.project.crud.TradeService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Timer;

@Component
public class RunTask{

    public RunTask(TradeService service) {
        Timer timer = new Timer();
        TradeTask tradeTask = new TradeTask(service);
        Instant instant = Instant.now();
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        int hour = ldt.getHour();
        int minute = ldt.getMinute();
        int second = ldt.getSecond();

        if (hour == 20 && minute == 0 && second == 0) {
            timer.schedule(tradeTask, 0, 24*60*60*1000);
        } else {
            Calendar dayAfter = Calendar.getInstance();
            dayAfter.set(ldt.getYear(),ldt.getMonth().getValue() - 1, ldt.getDayOfMonth() + 1, 20, 0, 0);
            timer.schedule(tradeTask, dayAfter.getTimeInMillis() - instant.toEpochMilli(), 24*60*60*1000);
        }
    }
}
