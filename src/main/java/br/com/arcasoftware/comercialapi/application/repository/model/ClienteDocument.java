package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;

@AllArgsConstructor
@Entity
@Table(name = "fincli")
public class ClienteDocument extends BaseEntity {

    private long codcli;
    private String nomcli;
    private String apecli;
    private String cgccli;
    private String inscli;
    private Integer regtrb;
    private Double clidev;
    private String cifcli;
    private String uffcli;
    private Integer codven;
    private String cnae;
    private String indicEstrangeiro;
    private Calendar dtncli;
    private String tipoIndicacaoIE;
    private String inscMunicipal;
    private String obscli;

    private String cefcli;
    private String ceccli;
    private String ceacli;
    private String ceecli;

    @Column(name = "INDIC_IE")
    private Integer indicIe;

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

    public ClienteDocument() {
    }

    public ClienteDocument(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public long getCodcli() {
        return codcli;
    }

    public void setCodcli(long codcli) {
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

    public Integer getRegtrb() {
        return regtrb;
    }

    public void setRegtrb(Integer regtrb) {
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

    public String getObscli() {
        return obscli;
    }

    public void setObscli(String obscli) {
        this.obscli = obscli;
    }
}
