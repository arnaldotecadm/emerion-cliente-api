package br.com.arcasoftware.comercialapi.application.repository.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteData {

    private long id;
    private String cnpjEmpresa;
    private long codcli;
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
    private Date dtncli;
    private String tipoIndicacaoIE;
    private String inscMunicipal;
    private String obsCli;
    private String cefcli;
    private String ceccli;
    private String ceacli;
    private String ceecli;
    private Integer indicIe;

    private String TEFCLI;
    private String ENFCLI;
    private String NRFCLI;
    private String RFFCLI;
    private String BAFCLI;
    private String PT1CLI;
    private String FO1CLI;
    private String COFCLI;
    private String PC1CLI;
    private String FC1CLI;

    private String TECCLI;
    private String ENCCLI;
    private String NRCCLI;
    private String RFCCLI;
    private String BACCLI;
    private String CICCLI;
    private String UFCCLI;
    private String PT2CLI;
    private String FO2CLI;
    private String COCCLI;
    private String PC2CLI;
    private String FC2CLI;

    private String TEACLI;
    private String ENACLI;
    private String NRACLI;
    private String RFACLI;
    private String BAACLI;
    private String CIACLI;
    private String UFACLI;
    private String PT3CLI;
    private String FO3CLI;
    private String COMCLI;
    private String PC3CLI;
    private String FC3CLI;

    private String TEECLI;
    private String ENECLI;
    private String NRECLI;
    private String RFECLI;
    private String BAECLI;
    private String CIECLI;
    private String UFECLI;
    private String PT4CLI;
    private String FO4CLI;
    private String COECLI;
    private String PC4CLI;
    private String FC4CLI;

    public String getTEFCLI() {
        return TEFCLI;
    }

    public void setTEFCLI(final String TEFCLI) {
        this.TEFCLI = TEFCLI;
    }

    public String getENFCLI() {
        return ENFCLI;
    }

    public void setENFCLI(final String ENFCLI) {
        this.ENFCLI = ENFCLI;
    }

    public String getNRFCLI() {
        return NRFCLI;
    }

    public void setNRFCLI(final String NRFCLI) {
        this.NRFCLI = NRFCLI;
    }

    public String getRFFCLI() {
        return RFFCLI;
    }

    public void setRFFCLI(final String RFFCLI) {
        this.RFFCLI = RFFCLI;
    }

    public String getBAFCLI() {
        return BAFCLI;
    }

    public void setBAFCLI(final String BAFCLI) {
        this.BAFCLI = BAFCLI;
    }

    public String getPT1CLI() {
        return PT1CLI;
    }

    public void setPT1CLI(final String PT1CLI) {
        this.PT1CLI = PT1CLI;
    }

    public String getFO1CLI() {
        return FO1CLI;
    }

    public void setFO1CLI(final String FO1CLI) {
        this.FO1CLI = FO1CLI;
    }

    public String getCOFCLI() {
        return COFCLI;
    }

    public void setCOFCLI(final String COFCLI) {
        this.COFCLI = COFCLI;
    }

    public String getPC1CLI() {
        return PC1CLI;
    }

    public void setPC1CLI(final String PC1CLI) {
        this.PC1CLI = PC1CLI;
    }

    public String getFC1CLI() {
        return FC1CLI;
    }

    public void setFC1CLI(final String FC1CLI) {
        this.FC1CLI = FC1CLI;
    }

    public String getTECCLI() {
        return TECCLI;
    }

    public void setTECCLI(final String TECCLI) {
        this.TECCLI = TECCLI;
    }

    public String getENCCLI() {
        return ENCCLI;
    }

    public void setENCCLI(final String ENCCLI) {
        this.ENCCLI = ENCCLI;
    }

    public String getNRCCLI() {
        return NRCCLI;
    }

    public void setNRCCLI(final String NRCCLI) {
        this.NRCCLI = NRCCLI;
    }

    public String getRFCCLI() {
        return RFCCLI;
    }

    public void setRFCCLI(final String RFCCLI) {
        this.RFCCLI = RFCCLI;
    }

    public String getBACCLI() {
        return BACCLI;
    }

    public void setBACCLI(final String BACCLI) {
        this.BACCLI = BACCLI;
    }

    public String getCICCLI() {
        return CICCLI;
    }

    public void setCICCLI(final String CICCLI) {
        this.CICCLI = CICCLI;
    }

    public String getUFCCLI() {
        return UFCCLI;
    }

    public void setUFCCLI(final String UFCCLI) {
        this.UFCCLI = UFCCLI;
    }

    public String getPT2CLI() {
        return PT2CLI;
    }

    public void setPT2CLI(final String PT2CLI) {
        this.PT2CLI = PT2CLI;
    }

    public String getFO2CLI() {
        return FO2CLI;
    }

    public void setFO2CLI(final String FO2CLI) {
        this.FO2CLI = FO2CLI;
    }

    public String getCOCCLI() {
        return COCCLI;
    }

    public void setCOCCLI(final String COCCLI) {
        this.COCCLI = COCCLI;
    }

    public String getPC2CLI() {
        return PC2CLI;
    }

    public void setPC2CLI(final String PC2CLI) {
        this.PC2CLI = PC2CLI;
    }

    public String getFC2CLI() {
        return FC2CLI;
    }

    public void setFC2CLI(final String FC2CLI) {
        this.FC2CLI = FC2CLI;
    }

    public String getTEACLI() {
        return TEACLI;
    }

    public void setTEACLI(final String TEACLI) {
        this.TEACLI = TEACLI;
    }

    public String getENACLI() {
        return ENACLI;
    }

    public void setENACLI(final String ENACLI) {
        this.ENACLI = ENACLI;
    }

    public String getNRACLI() {
        return NRACLI;
    }

    public void setNRACLI(final String NRACLI) {
        this.NRACLI = NRACLI;
    }

    public String getRFACLI() {
        return RFACLI;
    }

    public void setRFACLI(final String RFACLI) {
        this.RFACLI = RFACLI;
    }

    public String getBAACLI() {
        return BAACLI;
    }

    public void setBAACLI(final String BAACLI) {
        this.BAACLI = BAACLI;
    }

    public String getCIACLI() {
        return CIACLI;
    }

    public void setCIACLI(final String CIACLI) {
        this.CIACLI = CIACLI;
    }

    public String getUFACLI() {
        return UFACLI;
    }

    public void setUFACLI(final String UFACLI) {
        this.UFACLI = UFACLI;
    }

    public String getPT3CLI() {
        return PT3CLI;
    }

    public void setPT3CLI(final String PT3CLI) {
        this.PT3CLI = PT3CLI;
    }

    public String getFO3CLI() {
        return FO3CLI;
    }

    public void setFO3CLI(final String FO3CLI) {
        this.FO3CLI = FO3CLI;
    }

    public String getCOMCLI() {
        return COMCLI;
    }

    public void setCOMCLI(final String COMCLI) {
        this.COMCLI = COMCLI;
    }

    public String getPC3CLI() {
        return PC3CLI;
    }

    public void setPC3CLI(final String PC3CLI) {
        this.PC3CLI = PC3CLI;
    }

    public String getFC3CLI() {
        return FC3CLI;
    }

    public void setFC3CLI(final String FC3CLI) {
        this.FC3CLI = FC3CLI;
    }

    public String getTEECLI() {
        return TEECLI;
    }

    public void setTEECLI(final String TEECLI) {
        this.TEECLI = TEECLI;
    }

    public String getENECLI() {
        return ENECLI;
    }

    public void setENECLI(final String ENECLI) {
        this.ENECLI = ENECLI;
    }

    public String getNRECLI() {
        return NRECLI;
    }

    public void setNRECLI(final String NRECLI) {
        this.NRECLI = NRECLI;
    }

    public String getRFECLI() {
        return RFECLI;
    }

    public void setRFECLI(final String RFECLI) {
        this.RFECLI = RFECLI;
    }

    public String getBAECLI() {
        return BAECLI;
    }

    public void setBAECLI(final String BAECLI) {
        this.BAECLI = BAECLI;
    }

    public String getCIECLI() {
        return CIECLI;
    }

    public void setCIECLI(final String CIECLI) {
        this.CIECLI = CIECLI;
    }

    public String getUFECLI() {
        return UFECLI;
    }

    public void setUFECLI(final String UFECLI) {
        this.UFECLI = UFECLI;
    }

    public String getPT4CLI() {
        return PT4CLI;
    }

    public void setPT4CLI(final String PT4CLI) {
        this.PT4CLI = PT4CLI;
    }

    public String getFO4CLI() {
        return FO4CLI;
    }

    public void setFO4CLI(final String FO4CLI) {
        this.FO4CLI = FO4CLI;
    }

    public String getCOECLI() {
        return COECLI;
    }

    public void setCOECLI(final String COECLI) {
        this.COECLI = COECLI;
    }

    public String getPC4CLI() {
        return PC4CLI;
    }

    public void setPC4CLI(final String PC4CLI) {
        this.PC4CLI = PC4CLI;
    }

    public String getFC4CLI() {
        return FC4CLI;
    }

    public void setFC4CLI(final String FC4CLI) {
        this.FC4CLI = FC4CLI;
    }

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

    public Date getDtncli() {
        return dtncli;
    }

    public void setDtncli(Date dtncli) {
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

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
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
