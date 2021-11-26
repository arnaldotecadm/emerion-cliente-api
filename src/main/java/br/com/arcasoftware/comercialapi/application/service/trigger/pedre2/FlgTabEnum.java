package br.com.arcasoftware.comercialapi.application.service.trigger.pedre2;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;

public enum FlgTabEnum {
    ULTIMO_PRECO("Ultimo Preco"),
    CUSTO_HISTORICO("Custo Historico"),
    CUSTO_PONDERADO("Custo Ponderado"),
    CUSTO_REFERENCIAL("Custo Referencial"),
    ULTIMO_CUSTO_CIF("Ultimo Custo(Cif)"),
    CUSTO_HISTORICO_G("Custo Historico (G)"),
    CUSTO_PONDERADO_G("Custo Ponderado (G)"),
    MEDIA_PONDERADA_E("Media Ponderada (E)"),
    CUSTO_ULTIMA_COMPRA("Custo da Ultima Compra");

    private final String label;

    FlgTabEnum(String label) {
        this.label = label;
    }

    public static FlgTabEnum getEnumFromDescription(String description){
        for(FlgTabEnum cEnum : FlgTabEnum.values()){
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
