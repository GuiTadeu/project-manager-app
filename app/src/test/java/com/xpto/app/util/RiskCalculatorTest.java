package com.xpto.app.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RiskCalculatorTest {

    @Test
    void testLowRisk() {
        assertEquals(ProjectRiskLevel.LOW_RISK, RiskCalculator.calculate(100000d, 3));
        assertEquals(ProjectRiskLevel.LOW_RISK, RiskCalculator.calculate(999d, 2));
        assertEquals(ProjectRiskLevel.LOW_RISK, RiskCalculator.calculate(10d, 1));
    }

    @Test
    void testMediumRisk() {
        assertEquals(ProjectRiskLevel.MEDIUM_RISK, RiskCalculator.calculate(100001d, 1));
        assertEquals(ProjectRiskLevel.MEDIUM_RISK, RiskCalculator.calculate(300000d, 2));
        assertEquals(ProjectRiskLevel.MEDIUM_RISK, RiskCalculator.calculate(900d, 6));
        assertEquals(ProjectRiskLevel.MEDIUM_RISK, RiskCalculator.calculate(1d, 6));
    }

    @Test
    void testHighRisk() {
        assertEquals(ProjectRiskLevel.HIGH_RISK, RiskCalculator.calculate(600000d, 2));
        assertEquals(ProjectRiskLevel.HIGH_RISK, RiskCalculator.calculate(900d, 7));
        assertEquals(ProjectRiskLevel.HIGH_RISK, RiskCalculator.calculate(501000d, 7));
    }

    @Test
    void testEdgeCases() {
        assertEquals(ProjectRiskLevel.LOW_RISK, RiskCalculator.calculate(0d, 0));
        assertEquals(ProjectRiskLevel.HIGH_RISK, RiskCalculator.calculate(Double.MAX_VALUE, Integer.MAX_VALUE));
    }
}