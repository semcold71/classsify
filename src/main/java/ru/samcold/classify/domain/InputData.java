package ru.samcold.classify.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
public class InputData {

    private double pNom;
    private double pMax;
    private int daysCount;
    private double pDay;
    private int age;
    private int p025;
    private int p05;
    private int p075;
    private int p1;

    public InputData(InputDataProxy proxy) {
        pNom = proxy.pNomProperty().get();
        pMax = proxy.pMaxProperty().get();
        daysCount = proxy.daysCountProperty().get();
        pDay = proxy.pDayProperty().get();
        age = proxy.ageProperty().get();
        p025 = proxy.p025Property().get();
        p05 = proxy.p05Property().get();
        p075 = proxy.p075Property().get();
        p1 = proxy.p1Property().get();
    }
}
