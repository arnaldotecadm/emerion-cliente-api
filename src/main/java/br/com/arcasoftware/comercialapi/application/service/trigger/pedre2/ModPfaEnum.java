package br.com.arcasoftware.comercialapi.application.service.trigger.pedre2;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;

public enum ModPfaEnum {
    VENDAS("Vendas"),
    RAM("RMA"),
    COMPRAS("Compras"),
    RETORNOS("Retornos"),
    REMESSAS("Remessas"),
    IMPORTACAO("Importacao"),
    DEVELUCOES("Devolucoes"),
    COMPLEMENTO("Complemento"),
    TRANSFERENCIAS("Transferencias"),
    AJUSTE("Ajuste"),
    OUTROS("Outros");


    private final String label;

    ModPfaEnum(String label) {
        this.label = label;
    }

    public static ModPfaEnum getEnumFromDescription(String description) {
        for (ModPfaEnum cEnum : ModPfaEnum.values()) {
            if (cEnum.getLabel().equalsIgnoreCase(description)) {
                return cEnum;
            }
        }

        throw new ValidationException(EnumException.ENUM_NOT_FOUND);
    }

    public String getLabel() {
        return this.label;
    }
}
