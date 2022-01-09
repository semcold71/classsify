package ru.samcold.classify.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.samcold.classify.domain.OMProxy;
import ru.samcold.classify.domain.OperatingMode;
import ru.samcold.classify.utils.AnimationDirection;
import ru.samcold.classify.utils.ShowView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

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

        rorBtn.setOnAction(event -> {
            readFromFile();
        });
    }

    private void readFromFile() {
        OMProxy proxy = null;
        try {
            FileInputStream fis = new FileInputStream("operatingMode.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            proxy = (OMProxy) ois.readObject();
            ois.close();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        System.out.println(proxy.getPNom());
    }
}
