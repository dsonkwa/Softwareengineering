package Praktikum_3_JavaFX;

import java.util.Random;
import javafx.concurrent.Task;

public class RadTask extends Task<Integer> {

    private int index;
    private int bildanzahl;

    RadTask(int bildanzahl) {
        this.index = 0;
        this.bildanzahl = bildanzahl;
    }

    @Override
    protected Integer call() thpackage Praktikum_3_JavaFX;

import java.util.Random;
import javafx.concurrent.Task;

public class RadTask extends Task<Integer> {

    private int index;
    private int bildanzahl;

    RadTask(int bildanzahl) {
        this.index = 0;
        this.bildanzahl = bildanzahl;
    }

    @Override
    protected Integer call() throws Exception {
        int maxBildwechsel = new Random().nextInt(50) + 50;
        for (int durchlauf = 0; durchlauf < maxBildwechsel; durchlauf++) {
            this.index++;
            if (this.index == this.bildanzahl)
                this.index = 0;
            updateValue(this.index);
            Thread.sleep(durchlauf * maxBildwechsel / 1000);
        }
        return this.index;
    }

}

