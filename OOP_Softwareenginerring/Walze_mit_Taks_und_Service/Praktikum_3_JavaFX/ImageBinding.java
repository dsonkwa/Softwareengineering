package Praktikum_3_JavaFX;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.image.Image;

public class ImageBinding extends ObjectBinding<Image> {

    private static Image[] bilder = new Image[6];
    private ReadOnlyObjectProperty<Integer> property;
    private int offset;
    private int bildanzahl;

    ImageBinding(ReadOnlyObjectProperty<Integer> property, int offset, int bildanzahl) {
        super.bind(property);
        this.property = property;
        this.offset = offset;
        this.bildanzahl = bildanzahl;
        bilderLaden();
    }

    private void bilderLaden() {
        for (int i = 0; i < bildanzahl; i++) {
            bilder[i] = new Image("\\img\\pic" + i + ".png");
        }
    }

    @Override
    protected Image computeValue() {
        if (property.getValue() == null)
            return this.getImage(0);
        int index = property.getValue();
        return this.getImage(index);
    }

    private Image getImage(int index) {
        if (index + this.offset < 0)
            return bilder[bildanzahl + this.offset];
        if (index + this.offset >= bildanzahl)
            return bilder[this.offset-1];
        else
            return bilder[index + this.offset];
    }
}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           package Praktikum_3_JavaFX;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.image.Image;

public class ImageBinding extends ObjectBinding<Image> {

    private static Image[] bilder = new Image[6];
    private ReadOnlyObjectProperty<Integer> property;
    private int offset;
    private int bildanzahl;

    ImageBinding(ReadOnlyObjectProperty<Integer> property, int offset, int bildanzahl) {
        super.bind(property);
        this.property = property;
        this.offset = offset;
        this.bildanzahl = bildanzahl;
        bilderLaden();
    }

    private void bilderLaden() {
        for (int i = 0; i < bildanzahl; i++) {
            bilder[i] = new Image("\\img\\pic" + i + ".png");
        }
    }

    @Override
    protected Image computeValue() {
        if (property.getValue() == null)
            return this.getImage(0);
        int index = property.getValue();
        return this.getImage(index);
    }

    private Image getImage(int index) {
        if (index + this.offset < 0)
            return bilder[bildanzahl + this.offset];
        if (index + this.offset >= bildanzahl)
            return bilder[this.offset-1];
        else
            return bilder[index + this.offset];
    }
}

