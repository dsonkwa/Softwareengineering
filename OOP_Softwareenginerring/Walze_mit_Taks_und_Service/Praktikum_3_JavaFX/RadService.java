package Praktikum_3_JavaFX;

import javafx.concurrent.Service;

public class RadService extends Service<Integer> {

    private int bildanzahl;

    RadService(int bildanzahl) {
        this.bildanzahl = bildanzahl;
    }

    @Override
    protected RadTask createTask() {
        return new RadTask(this.bildanzahl);
    }
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            package Praktikum_3_JavaFX;

import javafx.concurrent.Service;

public class RadService extends Service<Integer> {

    private int bildanzahl;

    RadService(int bildanzahl) {
        this.bildanzahl = bildanzahl;
    }

    @Override
    protected RadTask createTask() {
        return new RadTask(this.bildanzahl);
    }
}
