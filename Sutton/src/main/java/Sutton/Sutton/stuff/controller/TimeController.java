package Sutton.Sutton.stuff.controller;

import Sutton.Sutton.stuff.Logger;
import Sutton.Sutton.stuff.domain.Location;
import Sutton.Sutton.stuff.domain.SunriseSunsetCalculator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@Component
public class TimeController {

    private Location location = new Location(42.4755, -91.1192);

    public Calendar calendar;

    private Path timePath = Paths.get("/home/pi/Desktop/time.txt");

    @PostConstruct
    private void init() {

        try {

            calendar = new GregorianCalendar();
            calendar.setTimeInMillis(Long.parseLong(Files.readAllLines(timePath).get(0).trim()));

        } catch (Exception e) {

            Logger.log(e.getMessage());

        }

    }


    public Boolean isSunrise() {

        TimeZone timeZone = calendar.getTimeZone();

        SunriseSunsetCalculator sunriseSunsetCalculator = new SunriseSunsetCalculator(location, timeZone);

        Calendar sunrise = sunriseSunsetCalculator.getOfficialSunriseCalendarForDate(calendar);

        return calendar.getTimeInMillis() > sunrise.getTimeInMillis();
    }

    public Boolean isSunset() {

        TimeZone timeZone = calendar.getTimeZone();

        SunriseSunsetCalculator sunriseSunsetCalculator = new SunriseSunsetCalculator(location, timeZone);

        Calendar sunset = sunriseSunsetCalculator.getOfficialSunsetCalendarForDate(calendar);

        return calendar.getTimeInMillis() > sunset.getTimeInMillis() + (15 * 60000);

    }

    public void updateTimeFile(String time) throws Exception {

        Files.write(timePath, time.getBytes());

    }

}
