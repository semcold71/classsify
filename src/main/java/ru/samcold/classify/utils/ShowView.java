package ru.samcold.classify.utils;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

@Component
public class ShowView {

    public void show(StackPane parentContainer, Parent root, AnimationDirection direction) {

        if (!parentContainer.getChildren().get(0).equals(root)) {

            KeyValue kv;

            parentContainer.getChildren().add(root);

            if (direction == AnimationDirection.HORIZONTAL) {
                root.translateXProperty().set(parentContainer.getWidth());
                kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            } else {
                root.translateYProperty().set(parentContainer.getHeight());
                kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            }

            KeyFrame kf = new KeyFrame(Duration.millis(750), kv);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(actionEvent -> parentContainer.getChildren().remove(0));
            timeline.play();
        }
    }
}
