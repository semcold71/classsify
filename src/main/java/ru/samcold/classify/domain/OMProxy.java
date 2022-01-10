package ru.samcold.classify.domain;

import javafx.beans.property.*;
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

    private double pYear;
    private double pMid;
    private int n;
    private String u;
    private double kp;
    private String q;
    private String aIso;
    private String aGgtn;

    public OMProxy(OperatingMode om) {
        pNom = om.pNomProperty().get();
        pMax = om.pMaxProperty().get();
        daysCount = om.daysCountProperty().get();
        age = om.ageProperty().get();
        p025 = om.p025Property().get();
        p05 = om.p05Property().get();
        p075 = om.p075Property().get();
        p1 = om.p1Property().get();

        pYear = om.pYearProperty().get();
        pMid = om.pMidProperty().get();
        n = om.nProperty().get();
        u = om.uProperty().get();
        kp = om.kpProperty().get();
        q = om.qProperty().get();
        aIso = om.aIsoProperty().get();
        aGgtn = om.aGgtnProperty().get();
    }

}
