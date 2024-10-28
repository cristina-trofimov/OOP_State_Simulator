import controller.Input;
import models.State;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        Input input = new Input(new File("src/resources/alarm.pl"));

        input.run();

        for (State state : input.getStates().values()) {
            System.out.println(state);
        }

//        simulation.run();

    }
}