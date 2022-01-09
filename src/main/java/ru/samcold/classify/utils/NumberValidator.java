package ru.samcold.classify.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class NumberValidator {

    public static void DoubleValidator(TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("|[-\\+]?|[-\\+]?\\d+\\.?|[-\\+]?\\d+\\.?\\d+")) {
                tf.setText(oldValue);
            }
        });
    }

    public static void IntegerValidator(TextField tf, int min, int max) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                tf.setText(oldValue);
            }
            if (Integer.parseInt(newValue) > max || Integer.parseInt(newValue) < min) {
                tf.setText(oldValue);
            }
        });
    }

}
