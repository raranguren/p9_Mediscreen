package com.mediscreen.abernathy.risk;

public enum RiskLevel {
    RISK_NONE {
        public String toString() {
            return "None";
        }
    },
    RISK_BORDERLINE {
        public String toString() {
            return "Borderline";
        }
    },
    RISK_IN_DANGER {
        public String toString() {
            return "In Danger";
        }
    },
    RISK_EARLY_ONSET {
        public String toString() {
            return "Early onset";
        }
    }
}
