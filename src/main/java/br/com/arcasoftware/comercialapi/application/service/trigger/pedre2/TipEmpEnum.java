package br.com.arcasoftware.comercialapi.application.service.trigger.pedre2;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;

public enum TipEmpEnum {
    SIMPLES_NACIONAL("Simples Nacional"),
    NORMAL("Normal");

    private final String label;

    TipEmpEnum(String label) {
        this.label = label;
    }

    public static TipEmpEnum getEnumFromDescription(String description){
        for(TipEmpEnum cEnum : TipEmpEnum.values()){
            if(cEnum.getLabel().equalsIgnoreCase(description)){
                return cEnum;
            }
        }

        throw new ValidationException(EnumException.ITEM_NOT_FOUND);
    }

    public String getLabel(){
        return this.label;
    }
}
