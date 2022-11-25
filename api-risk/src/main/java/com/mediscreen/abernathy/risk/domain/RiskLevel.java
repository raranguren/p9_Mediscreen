package com.mediscreen.abernathy.risk.domain;

public enum RiskLevel {
    NONE {
        public String toString() {
            return "None";
        }
    },
    BORDERLINE {
        public String toString() {
            return "Borderline";
        }
    },
    IN_DANGER {
        public String toString() {
            return "In Danger";
        }
    },
    EARLY_ONSET {
        public String toString() {
            return "Early onset";
        }
    }
}
