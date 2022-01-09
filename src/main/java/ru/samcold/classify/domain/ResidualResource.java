package ru.samcold.classify.domain;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ResidualResource {

    private OperatingMode operatingMode;

    private int shiftsCount; // кол-во смен
    private int nominalN; // характеристическое число Nn
    private double kFactor;
    private int nFactor;
    private double qFactor;
    private int cyclesTotal;
    private int currentN;
    private int cyclesResource;
    private int yearsResource;


}
