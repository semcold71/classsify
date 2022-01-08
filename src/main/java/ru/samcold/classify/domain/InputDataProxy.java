package ru.samcold.classify.domain;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InputDataProxy {
    private final DoubleProperty pNom = new SimpleDoubleProperty();
    private final DoubleProperty pMax = new SimpleDoubleProperty();
    private final IntegerProperty daysCount = new SimpleIntegerProperty();
    private final DoubleProperty pDay = new SimpleDoubleProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final IntegerProperty p025 = new SimpleIntegerProperty();
    private final IntegerProperty p05 = new SimpleIntegerProperty();
    private final IntegerProperty p075 = new SimpleIntegerProperty();
    private final IntegerProperty p1 = new SimpleIntegerProperty();

    public InputDataProxy(InputData data) {
        pNomProperty().set(data.getPNom());
        pMaxProperty().set(data.getPMax());
        daysCountProperty().set(data.getDaysCount());
        pDayProperty().set(data.getPDay());
        ageProperty().set(data.getAge());
        p025Property().set(data.getP025());
        p05Property().set(data.getP05());
        p075Property().set(data.getP075());
        p1Property().set(data.getP1());
    }


    public DoubleProperty pNomProperty() {
        return pNom;
    }

    public DoubleProperty pMaxProperty() {
        return pMax;
    }

    public IntegerProperty daysCountProperty() {
        return daysCount;
    }

    public DoubleProperty pDayProperty() {
        return pDay;
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public IntegerProperty p025Property() {
        return p025;
    }

    public IntegerProperty p05Property() {
        return p05;
    }

    public IntegerProperty p075Property() {
        return p075;
    }

    public IntegerProperty p1Property() {
        return p1;
    }

}
