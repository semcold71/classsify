package ru.samcold.classify.domain;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.springframework.stereotype.Component;
import ru.samcold.classify.utils.A2Matrix;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Component
public class ResidualResource {

    private OMProxy proxy;
    private final A2Matrix matrix;

    private final IntegerProperty age = new SimpleIntegerProperty();
    private final IntegerProperty daysCount = new SimpleIntegerProperty();

    private final IntegerProperty shiftsCount = new SimpleIntegerProperty(); // кол-во смен
    private final IntegerProperty nominalN = new SimpleIntegerProperty(); // характеристическое число Nn

    private final DoubleProperty kFactor = new SimpleDoubleProperty();
    private final IntegerProperty nFactor = new SimpleIntegerProperty();
    private final DoubleProperty qFactor = new SimpleDoubleProperty();

    private final IntegerProperty cyclesTotal = new SimpleIntegerProperty();
    private final IntegerProperty currentN = new SimpleIntegerProperty();

    private final IntegerProperty cyclesResource = new SimpleIntegerProperty();
    private final IntegerProperty yearsResource = new SimpleIntegerProperty();

    public IntegerProperty ageProperty() {
        return age;
    }

    public IntegerProperty daysCountProperty() {
        return daysCount;
    }

    public IntegerProperty shiftsCountProperty() {
        return shiftsCount;
    }

    public IntegerProperty nominalNProperty() {
        return nominalN;
    }

    public DoubleProperty kFactorProperty() {
        return kFactor;
    }

    public IntegerProperty nFactorProperty() {
        return nFactor;
    }

    public DoubleProperty qFactorProperty() {
        return qFactor;
    }

    public IntegerProperty cyclesTotalProperty() {
        return cyclesTotal;
    }

    public IntegerProperty currentNProperty() {
        return currentN;
    }

    public IntegerProperty cyclesResourceProperty() {
        return cyclesResource;
    }

    public IntegerProperty yearsResourceProperty() {
        return yearsResource;
    }

    public ResidualResource(A2Matrix matrix) {
        this.matrix = matrix;

        getInputData();

        setAge();
        setDaysCount();
        setShiftsCount();
        setNominalN();
    }

    private void getInputData() {
        try {
            FileInputStream fis = new FileInputStream("operatingMode.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.proxy = (OMProxy) ois.readObject();
            ois.close();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void setAge() {
        ageProperty().set(proxy.getAge());

    }

    private void setDaysCount() {
        daysCountProperty().set(proxy.getDaysCount());
    }

    public void setShiftsCount() {
        shiftsCountProperty().set(daysCount.get() * age.get());
    }

    public void setNominalN() {
        nominalNProperty().set(matrix.getN(proxy.getAIso()));
    }

}
