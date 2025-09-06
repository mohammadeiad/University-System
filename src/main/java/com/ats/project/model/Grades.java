package com.ats.project.model;

public enum Grades {
    A_plus , A , B_plus , B , C_plus , C , D , F ;

    public double toGpaPoints() {
        return switch (this) {
            case A_plus -> 4.0;
            case A ->3.5;
            case B_plus -> 3.0;
            case B -> 2.5;
            case C_plus -> 2.0;
            case C -> 1.5;
            case D -> 1.0;
            case F -> 0;
        };
    }
    public boolean isPassing(){
        return this!=F;
    }
}