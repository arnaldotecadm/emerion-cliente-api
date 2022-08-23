package br.com.arcasoftware.comercialapi.application.repository.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteData {

    private String cnpjEmpresa;
    private String codcli;
    private String nomcli;
    private String apecli;
    private String cgccli;
    private String inscli;
    private String regtrb;
    private Double clidev;
    private String cifcli;
    private String uffcli;
    private Integer codven;
    private String cnae;
    private String indicEstrangeiro;
    private Calendar dtncli;
    private String tipoIndicacaoIE;
    private String inscMunicipal;
    private String obsCli;
    private String cefcli;
    private String ceccli;
    private String ceacli;
    private String ceecli;
    private Integer indicIe;

    public Integer getIndicIe() {
        return indicIe;
    }

    public void setIndicIe(Integer indicIe) {
        this.indicIe = indicIe;
    }

    public String getCefcli() {
        return cefcli;
    }

    public void setCefcli(String cefcli) {
        this.cefcli = cefcli;
    }

    public String getCeccli() {
        return ceccli;
    }

    public void setCeccli(String ceccli) {
        this.ceccli = ceccli;
    }

    public String getCeacli() {
        return ceacli;
    }

    public void setCeacli(String ceacli) {
        this.ceacli = ceacli;
    }

    public String getCeecli() {
        return ceecli;
    }

    public void setCeecli(String ceecli) {
        this.ceecli = ceecli;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getCodcli() {
        return codcli;
    }

    public void setCodcli(String codcli) {
        this.codcli = codcli;
    }

    public String getNomcli() {
        return nomcli;
    }

    public void setNomcli(String nomcli) {
        this.nomcli = nomcli;
    }

    public String getApecli() {
        return apecli;
    }

    public void setApecli(String apecli) {
        this.apecli = apecli;
    }

    public String getCgccli() {
        return cgccli;
    }

    public void setCgccli(String cgccli) {
        this.cgccli = cgccli;
    }

    public String getInscli() {
        return inscli;
    }

    public void setInscli(String inscli) {
        this.inscli = inscli;
    }

    public String getRegtrb() {
        return regtrb;
    }

    public void setRegtrb(String regtrb) {
        this.regtrb = regtrb;
    }

    public Double getClidev() {
        return clidev;
    }

    public void setClidev(Double clidev) {
        this.clidev = clidev;
    }

    public String getCifcli() {
        return cifcli;
    }

    public void setCifcli(String cifcli) {
        this.cifcli = cifcli;
    }

    public String getUffcli() {
        return uffcli;
    }

    public void setUffcli(String uffcli) {
        this.uffcli = uffcli;
    }

    public Integer getCodven() {
        return codven;
    }

    public void setCodven(Integer codven) {
        this.codven = codven;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getIndicEstrangeiro() {
        return indicEstrangeiro;
    }

    public void setIndicEstrangeiro(String indicEstrangeiro) {
        this.indicEstrangeiro = indicEstrangeiro;
    }

    public Calendar getDtncli() {
        return dtncli;
    }

    public void setDtncli(Calendar dtncli) {
        this.dtncli = dtncli;
    }

    public String getTipoIndicacaoIE() {
        return tipoIndicacaoIE;
    }

    public void setTipoIndicacaoIE(String tipoIndicacaoIE) {
        this.tipoIndicacaoIE = tipoIndicacaoIE;
    }

    public String getInscMunicipal() {
        return inscMunicipal;
    }

    public void setInscMunicipal(String inscMunicipal) {
        this.inscMunicipal = inscMunicipal;
    }

    public String getObsCli() {
        return obsCli;
    }

    public void setObsCli(String obsCli) {
        this.obsCli = obsCli;
    }

    @Override
    public String toString() {
        return "ClienteData{" +
                "cnpjEmpresa='" + cnpjEmpresa + '\'' +
                ", codcli='" + codcli + '\'' +
                ", nomcli='" + nomcli + '\'' +
                ", apecli='" + apecli + '\'' +
                ", cgccli='" + cgccli + '\'' +
                ", inscli='" + inscli + '\'' +
                ", regtrb='" + regtrb + '\'' +
                ", clidev=" + clidev +
                ", cifcli='" + cifcli + '\'' +
                ", uffcli='" + uffcli + '\'' +
                ", codven=" + codven +
                ", cnae='" + cnae + '\'' +
                ", indicEstrangeiro='" + indicEstrangeiro + '\'' +
                ", dtncli='" + dtncli + '\'' +
                ", tipoIndicacaoIE='" + tipoIndicacaoIE + '\'' +
                ", inscMunicipal='" + inscMunicipal + '\'' +
                ", obsCli='" + obsCli + '\'' +
                '}';
    }
}
