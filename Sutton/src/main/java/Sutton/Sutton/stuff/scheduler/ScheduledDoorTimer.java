package Sutton.Sutton.stuff.scheduler;

import Sutton.Sutton.stuff.Logger;
import Sutton.Sutton.stuff.controller.DoorController;
import Sutton.Sutton.stuff.controller.TimeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledDoorTimer {

    @Autowired
    private DoorController doorController;

    @Autowired
    private TimeController timeController;

    @Scheduled(fixedRate = 60000)
    public void run() {

        try {

            long time = timeController.calendar.getTimeInMillis() + 60000;

            timeController.updateTimeFile(String.valueOf(time));

            timeController.calendar.setTimeInMillis(time);

            if (doorController.schedulerIsEnabled) {

                if (timeController.isSunset() && doorController.isOpen) {

                    doorController.closeDoor();

                } else if (timeController.isSunrise() && !doorController.isOpen) {

                    doorController.openDoor();

                }

            }

        } catch (Exception e) {

            Logger.log(e.getMessage());

        }
    }
}
