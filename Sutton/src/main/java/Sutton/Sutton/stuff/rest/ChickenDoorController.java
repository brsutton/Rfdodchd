package Sutton.Sutton.stuff.rest;

import Sutton.Sutton.stuff.controller.TimeController;
import Sutton.Sutton.stuff.controller.DoorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ChickenDoorController {

    @Autowired
    private DoorController doorController;

    @Autowired
    private TimeController timeController;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/open", method = RequestMethod.GET)
    public Boolean openDoorLittleBit() throws InterruptedException {

        doorController.openDoorALittleBit();

        return true;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/close", method = RequestMethod.GET)
    public Boolean closeDoorLittleBit() throws InterruptedException {

        doorController.closeDoorALittleBit();

        return true;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/openFully", method = RequestMethod.GET)
    public Boolean openDoorFully() throws InterruptedException {

        try {

            doorController.openDoor();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return true;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/closeFully", method = RequestMethod.GET)
    public Boolean closeFully() throws InterruptedException {

        try {

            doorController.closeDoor();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return true;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getCloseRate", method = RequestMethod.GET)
    public Integer getCloseRate() {

        return doorController.closeRate;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/setCloseRate/{closeRate}", method = RequestMethod.GET)
    public Boolean setCloseRate(@PathVariable Integer closeRate) {

        doorController.setCloseRate(closeRate);

        return true;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getOpenRate", method = RequestMethod.GET)
    public Integer getOpenRate() {

        return doorController.openRate;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/setOpenRate/{openRate}", method = RequestMethod.GET)
    public Boolean setOpenRate(@PathVariable Integer openRate) {

        doorController.setOpenRate(openRate);

        return true;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getTime", method = RequestMethod.GET)
    public String getTime() {

        return timeController.calendar.getTime().toString();

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/setClock/{time}", method = RequestMethod.GET)
    public Boolean setClock(@PathVariable Long time) {

        timeController.calendar.setTimeInMillis(time);

        return true;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/disableScheduler", method = RequestMethod.GET)
    public String disable() {

        doorController.schedulerIsEnabled = false;

        return "disabled";

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/enableScheduler", method = RequestMethod.GET)
    public String enable() {

        doorController.schedulerIsEnabled = true;

        return "enabled";

    }
}
