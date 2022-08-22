package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Entity
@Table(name = "fincli")
public class ClienteDocument {

    @Id
    @GeneratedValue
    private Long id;

    private String cnpjEmpresa;
    private String codcli;
    private String nomcli;
    private String apecli;
    private String cgccli;
    private String inscli;
    private String nomregtrib;
    private Double clidev;
    private String cifcli;
    private String uffcli;
    private Integer codven;
    private String cnae;
    private String indicEstrangeiro;
    private String dtncli;
    private String tipoIndicacaoIE;
    private String inscMunicipal;
    private String obsCli;

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

    public String getNomregtrib() {
        return nomregtrib;
    }

    public void setNomregtrib(String nomregtrib) {
        this.nomregtrib = nomregtrib;
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

    public String getDtncli() {
        return dtncli;
    }

    public void setDtncli(String dtncli) {
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
        return "ClienteDocument{" +
                "cnpjEmpresa='" + cnpjEmpresa + '\'' +
                ", codcli='" + codcli + '\'' +
                ", nomcli='" + nomcli + '\'' +
                ", apecli='" + apecli + '\'' +
                ", cgccli='" + cgccli + '\'' +
                ", inscli='" + inscli + '\'' +
                ", nomregtrib='" + nomregtrib + '\'' +
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
