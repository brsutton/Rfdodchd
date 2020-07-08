package Sutton.Sutton.stuff.controller;

import Sutton.Sutton.stuff.Logger;
import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class DoorController {

    public Boolean isOpen = false;

    public Boolean schedulerIsEnabled = true;

    public Integer closeRate = 8955;
    public Integer openRate = 10000;


    private Path doorPath = Paths.get("/home/pi/Desktop/door.txt");
    private Path openRatePath = Paths.get("/home/pi/Desktop/openRate.txt");
    private Path closeRatePath = Paths.get("/home/pi/Desktop/closeRate.txt");

    private GpioPinDigitalOutput openDoor;
    private GpioPinDigitalOutput closeDoor;

    @PostConstruct
    private void init() {

        try {

            String openState = Files.readAllLines(doorPath).get(0);

            if (openState.equals("open")) this.isOpen = true;

            this.openRate = Integer.valueOf(Files.readAllLines(openRatePath).get(0));

            this.closeRate = Integer.valueOf(Files.readAllLines(closeRatePath).get(0));

        } catch (Exception e) {

            Logger.log(e.getMessage());

        }

        GpioController gpioController = GpioFactory.getInstance();

        openDoor = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01, "openDoor", PinState.LOW);

        closeDoor = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, "closeDoor", PinState.LOW);

    }

    public void openDoorALittleBit() throws InterruptedException {

        openDoor.high();

        Thread.sleep(100);

        openDoor.low();

    }

    public void closeDoorALittleBit() throws InterruptedException {

        closeDoor.high();

        Thread.sleep(100);

        closeDoor.low();

    }

    public void openDoor() throws InterruptedException {

        if (openDoor.getState().isLow() && !isOpen) {

            openDoor.high();

            Thread.sleep(openRate);

            openDoor.low();

            isOpen = true;

            updateDoorState("open");

        }
    }

    public void closeDoor() throws InterruptedException {

        if (closeDoor.getState().isLow() && isOpen) {

            closeDoor.high();

            Thread.sleep(closeRate);

            closeDoor.low();

            isOpen = false;

            updateDoorState("closed");

        }
    }

    public void setOpenRate(Integer rate) {

        try {
            this.openRate = rate;

            Files.write(openRatePath, rate.toString().getBytes());

        } catch (Exception e) {

            Logger.log(e.getMessage());

        }

    }

    public void setCloseRate(Integer rate) {

        try {
            this.closeRate = rate;

            Files.write(closeRatePath, rate.toString().getBytes());

        } catch (Exception e) {

            Logger.log(e.getMessage());

        }

    }

    private void updateDoorState(String state) {

        try {

            Files.write(doorPath, state.getBytes());

        } catch (Exception e) {

            Logger.log(e.getMessage());

        }
    }
}
