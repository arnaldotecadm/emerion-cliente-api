package br.com.arcasoftware.comercialapi.application.service.trigger.pedre2;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;

public enum TipComEnum {
    VALOR_MARKUP("Valor de Markup"),
    TABELA_PRECO("Tabela de Preco"),
    ITEM("Item"),
    CLIENTE("Cliente"),
    VENDEDOR("Vendedor");


    private final String label;

    TipComEnum(String label) {
        this.label = label;
    }

    public static TipComEnum getEnumFromDescription(String description){
        for(TipComEnum cEnum : TipComEnum.values()){
            if(cEnum.getLabel().equalsIgnoreCase(description)){
                return cEnum;
            }
        }

        throw new ValidationException(EnumException.ENUM_NOT_FOUND);
    }

    public String getLabel(){
        return this.label;
    }
}
