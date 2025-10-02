package com.xpto.app.util;

public class RiskCalculator {

    private static final Double LOW_LIMIT = 100000d; // 100k
    private static final int LOW_TERM_MONTHS = 3;

    private static final Double MEDIUM_LIMIT = 500000d; // 500k
    private static final int MEDIUM_TERM_MONTHS = 6;

    private static final Double HIGH_LIMIT = MEDIUM_LIMIT + 1;
    private static final int HIGH_TERM_MONTHS = MEDIUM_TERM_MONTHS + 1;

    public static ProjectRiskLevel calculate(Double budget, int months) {

        if (budget <= LOW_LIMIT && months <= LOW_TERM_MONTHS) {
            return ProjectRiskLevel.LOW_RISK;
        }

        if ((budget <= MEDIUM_LIMIT && months <= MEDIUM_TERM_MONTHS) || (months >= LOW_TERM_MONTHS && months <= MEDIUM_TERM_MONTHS)) {
            return ProjectRiskLevel.MEDIUM_RISK;
        }

        if (budget >= HIGH_LIMIT || months >= HIGH_TERM_MONTHS) {
            return ProjectRiskLevel.HIGH_RISK;
        }

        return null;
    }
}
