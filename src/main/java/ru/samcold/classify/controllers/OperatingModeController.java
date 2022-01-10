package ru.samcold.classify.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import net.rgielen.fxweaver.core.FxmlView;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.springframework.stereotype.Component;
import ru.samcold.classify.FormApplication;
import ru.samcold.classify.domain.OperatingMode;
import ru.samcold.classify.domain.OMProxy;
import ru.samcold.classify.utils.Decor;
import ru.samcold.classify.utils.FieldBinder;
import ru.samcold.classify.utils.NumberValidator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Objects;

@Component
@FxmlView(value = "/fxml/groupView.fxml")
public class OperatingModeController {

    // region FXML
    @FXML
    private Label warningLbl;

    @FXML
    private TitledPane inputPane;

    @FXML
    private TitledPane outputPane;

    @FXML
    private TextField pNomTxt;

    @FXML
    private TextField pMaxTxt;

    @FXML
    private TextField daysCountTxt;

    @FXML
    private TextField pDayTxt;

    @FXML
    private TextField ageTxt;

    @FXML
    private TextField p025Txt;

    @FXML
    private TextField p05Txt;

    @FXML
    private TextField p075Txt;

    @FXML
    private TextField p1Txt;

    @FXML
    private TextField pYearTxt;

    @FXML
    private TextField pMidTxt;

    @FXML
    private TextField nTxt;

    @FXML
    private TextField uTxt;

    @FXML
    private TextField kpTxt;

    @FXML
    private TextField qTxt;

    @FXML
    private TextField aIsoTxt;

    @FXML
    private TextField aGgtnTxt;

    @FXML
    private Button calcBtn;

    @FXML
    private Button wordBtn;
    // endregion

    private final OperatingMode operatingMode;
    private final FieldBinder binder;
    private final Decor decor;

    public OperatingModeController(OperatingMode operatingMode, FieldBinder binder, Decor decor) {
        this.operatingMode = operatingMode;
        this.binder = binder;
        this.decor = decor;
    }

    @FXML
    void initialize() {
        // Test
        operatingMode.pNomProperty().set(16.0);
        operatingMode.pNomProperty().set(16);
        operatingMode.pMaxProperty().set(16);
        operatingMode.daysCountProperty().set(180);
        operatingMode.pDayProperty().set(70);
        operatingMode.ageProperty().set(30);
        operatingMode.p025Property().set(50);
        operatingMode.p05Property().set(30);
        operatingMode.p075Property().set(15);
        operatingMode.p1Property().set(5);

        initFields();
        initValidators();
        initButtons();
    }

    private void initFields() {
        binder.bind(pNomTxt, operatingMode.pNomProperty());
        binder.bind(pMaxTxt, operatingMode.pMaxProperty());
        binder.bind(daysCountTxt, operatingMode.daysCountProperty());
        binder.bind(pDayTxt, operatingMode.pDayProperty());
        binder.bind(ageTxt, operatingMode.ageProperty());
        binder.bind(p025Txt, operatingMode.p025Property());
        binder.bind(p05Txt, operatingMode.p05Property());
        binder.bind(p075Txt, operatingMode.p075Property());
        binder.bind(p1Txt, operatingMode.p1Property());

        binder.bind(pYearTxt, operatingMode.pYearProperty());
        binder.bind(pMidTxt, operatingMode.pMidProperty());
        binder.bind(nTxt, operatingMode.nProperty());
        binder.bind(uTxt, operatingMode.uProperty());
        binder.bind(kpTxt, operatingMode.kpProperty());
        binder.bind(qTxt, operatingMode.qProperty());
        binder.bind(aIsoTxt, operatingMode.aIsoProperty());
        binder.bind(aGgtnTxt, operatingMode.aGgtnProperty());

        // set tooltip for best view
        nTxt.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.isEmpty()) {
                Tooltip tooltip = new Tooltip();
                tooltip.setShowDelay(Duration.seconds(0.3));
                tooltip.setHideDelay(Duration.seconds(2));
                tooltip.setStyle("-fx-font-size: 18");
                tooltip.setText(decor.decoration(operatingMode.nProperty().get()));
                nTxt.setTooltip(tooltip);
            }
        });

        // tracking changes in form
        setCalculatedListener(((Pane) inputPane.getContent()).getChildren());

        // (optional) clear output field for best view
        clearOutputFields(((Pane) outputPane.getContent()).getChildren());
    }

    private void initButtons() {
        calcBtn.setOnAction(event -> {
            if (!check100()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Сообщение программы");
                alert.setHeaderText("Сумма долей циклов должна быть равна 100");
                alert.setContentText("Нажмите ОК для продолжения");

                DialogPane pane = alert.getDialogPane();
                pane.getStylesheets().add(Objects.requireNonNull(
                        FormApplication.class.getResource("/styles/style.css")).toExternalForm());

                alert.showAndWait();

                return;
            }

            operatingMode.calculation();
            writeToFile();
        });
    }

    private void writeToFile() {
        OMProxy proxy = new OMProxy(operatingMode);

        try {
            FileOutputStream fos = new FileOutputStream("operatingMode.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(proxy);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initValidators() {

        // set support
        ValidationSupport vs = new ValidationSupport();
        vs.setErrorDecorationEnabled(true);

        // number validation
        NumberValidator.DoubleValidator(pNomTxt);
        NumberValidator.DoubleValidator(pMaxTxt);
        NumberValidator.IntegerValidator(daysCountTxt, 1, 365);
        NumberValidator.DoubleValidator(pDayTxt);
        NumberValidator.IntegerValidator(ageTxt, 1, 100);
        NumberValidator.IntegerValidator(p025Txt, 0, 100);
        NumberValidator.IntegerValidator(p05Txt, 0, 100);
        NumberValidator.IntegerValidator(p075Txt, 0, 100);
        NumberValidator.IntegerValidator(p1Txt, 0, 100);

        // empty or 0 validations
        Validator<String> emptyValidator = Validator.createEmptyValidator("Это поле по может быть пустым", Severity.ERROR);
        Validator<String> not0Validator = (control, value) -> {
            boolean condition = value != null && (value.equals("0.0") || value.equals("0"));
            return ValidationResult.fromMessageIf(control, "Значение в этом поле должно быть больше 0", Severity.ERROR, condition);
        };

        // set validators
        vs.registerValidator(pNomTxt, Validator.combine(emptyValidator, not0Validator));
        vs.registerValidator(pMaxTxt, Validator.combine(emptyValidator, not0Validator));
        vs.registerValidator(daysCountTxt, Validator.combine(emptyValidator, not0Validator));
        vs.registerValidator(pDayTxt, Validator.combine(emptyValidator, not0Validator));
        vs.registerValidator(ageTxt, Validator.combine(emptyValidator, not0Validator));
        vs.registerValidator(p025Txt, Validator.combine(emptyValidator));
        vs.registerValidator(p05Txt, Validator.combine(emptyValidator));
        vs.registerValidator(p075Txt, Validator.combine(emptyValidator));
        vs.registerValidator(p1Txt, Validator.combine(emptyValidator));

        // action validation
        calcBtn.disableProperty().bind(vs.invalidProperty());
    }

    private void setCalculatedListener(List<Node> nodeList) {
        for (Node node : nodeList) {
            if (node instanceof TextField) {
                final String sOld = ((TextField) node).getText();
                ((TextField) node).textProperty().addListener(
                        (observableValue, s, t1) -> warningLbl.setVisible(!t1.equals(sOld)));
            }

            if (node instanceof Pane) {
                setCalculatedListener(((Pane) node).getChildren());
            }
        }
    }

    private void clearOutputFields(List<Node> nodeList) {
        for (Node node : nodeList) {
            if (node instanceof TextField) {
                ((TextField) node).clear();
            }

            // recursive
            if (node instanceof Pane) {
                clearOutputFields(((Pane) node).getChildren());
            }
        }
    }

    private boolean check100() {
        int res =
                operatingMode.p025Property().get()
                        + operatingMode.p05Property().get()
                        + operatingMode.p075Property().get()
                        + operatingMode.p1Property().get();

        return res == 100;
    }
}
