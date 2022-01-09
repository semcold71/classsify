package ru.samcold.classify.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.samcold.classify.utils.AnimationDirection;
import ru.samcold.classify.utils.ShowView;

@Component
@FxmlView(value = "/fxml/main.fxml")
public class MainController {

    @FXML
    private Button rorBtn;

    @FXML
    private Button groupBtn;

    @FXML
    private StackPane containerPane;

    private final FxWeaver fxWeaver;
    private final ShowView showView;
    private Parent groupParent;

    public MainController(FxWeaver fxWeaver, ShowView showView) {
        this.fxWeaver = fxWeaver;
        this.showView = showView;
    }

    @FXML
    public void initialize() {
        groupParent = fxWeaver.loadView(OperatingModeController.class);

        groupBtn.setOnAction(event -> showView.show(
                containerPane,
                groupParent,
                AnimationDirection.HORIZONTAL));
    }
}
