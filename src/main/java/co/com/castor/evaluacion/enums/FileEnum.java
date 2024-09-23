package co.com.castor.evaluacion.enums;

import lombok.Getter;

@Getter
public enum FileEnum {
    SAVE("save"), UPDATE("update");

    private final String operation;

    private FileEnum(String operation) {
        this.operation = operation;
    }
}
