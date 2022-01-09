package ru.samcold.classify.utils;

import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.springframework.stereotype.Component;

@Component
public class FieldBinder {

    public void bind(TextField field, Property<?> property) {

        if (property.toString().contains("String")) {
            field.textProperty().bindBidirectional((StringProperty) property);

        } else {
            field.textProperty().bindBidirectional((Property<Number>) property, new StringConverter<>() {
                @Override
                public String toString(Number number) {
                    return String.valueOf(number);
                }

                @Override
                public Number fromString(String s) {
                    if (property.toString().contains("Double")) {
                        return Double.parseDouble(s);
                    } else if (property.toString().contains("Integer")) {
                        return Integer.parseInt(s);
                    }
                    return null;
                }
            });
        }
    }
}
