package ru.samcold.classify.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class OMProxy implements Serializable {
    private double pNom;
    private double pMax;
    private int daysCount;
    private double pDay;
    private int age;
    private int p025;
    private int p05;
    private int p075;
    private int p1;

    public OMProxy(OperatingMode om) {
        this.pNom = om.pNomProperty().get();
        this.pMax = om.pMaxProperty().get();
        this.daysCount = om.daysCountProperty().get();
        this.age = om.ageProperty().get();
        this.p025 = p025;
        this.p05 = p05;
        this.p075 = p075;
        this.p1 = p1;
    }

}
