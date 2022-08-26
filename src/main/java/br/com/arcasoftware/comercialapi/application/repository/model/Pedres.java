package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "pedres")
public class Pedres extends BaseEntity {
    private long codcli;
    private Integer codven;
    private Integer codatd;
    private long numres;
    private String codpfa;
    private Double totger;
    private Double totres;
    private Double totren;
    private Double totipi;
    private Double totsub;
    private Double TOTDESCINC;
    private String pedant;
    private Double fatger;
    private Double devger;
    private Double sldger;
    private String dteres;
    private String dtfres;
    private String uferes;
    private String flgimp;
    private String sitres;
    private String obsres;
    private String obspro;
    private int qtdimp;

    public long getCodcli() {
        return codcli;
    }

    public void setCodcli(final long codcli) {
        this.codcli = codcli;
    }

    public long getNumres() {
        return numres;
    }

    public void setNumres(final long numres) {
        this.numres = numres;
    }

    public String getCodpfa() {
        return codpfa;
    }

    public void setCodpfa(final String codpfa) {
        this.codpfa = codpfa;
    }

    public Double getTotger() {
        return totger;
    }

    public void setTotger(final Double totger) {
        this.totger = totger;
    }

    public Double getTotres() {
        return totres;
    }

    public void setTotres(final Double totres) {
        this.totres = totres;
    }

    public Double getTotren() {
        return totren;
    }

    public void setTotren(final Double totren) {
        this.totren = totren;
    }

    public Double getFatger() {
        return fatger;
    }

    public void setFatger(final Double fatger) {
        this.fatger = fatger;
    }

    public Double getDevger() {
        return devger;
    }

    public void setDevger(final Double devger) {
        this.devger = devger;
    }

    public Double getSldger() {
        return sldger;
    }

    public void setSldger(final Double sldger) {
        this.sldger = sldger;
    }

    public String getDteres() {
        return dteres;
    }

    public void setDteres(final String dteres) {
        this.dteres = dteres;
    }

    public String getDtfres() {
        return dtfres;
    }

    public void setDtfres(final String dtfres) {
        this.dtfres = dtfres;
    }

    public String getUferes() {
        return uferes;
    }

    public void setUferes(final String uferes) {
        this.uferes = uferes;
    }

    public String getFlgimp() {
        return flgimp;
    }

    public void setFlgimp(final String flgimp) {
        this.flgimp = flgimp;
    }

    public String getSitres() {
        return sitres;
    }

    public void setSitres(final String sitres) {
        this.sitres = sitres;
    }

    public String getObsres() {
        return obsres;
    }

    public void setObsres(final String obsres) {
        this.obsres = obsres;
    }

    public String getObspro() {
        return obspro;
    }

    public void setObspro(final String obspro) {
        this.obspro = obspro;
    }

    public int getQtdimp() {
        return qtdimp;
    }

    public void setQtdimp(final int qtdimp) {
        this.qtdimp = qtdimp;
    }

    public Double getTotipi() {
        return totipi;
    }

    public void setTotipi(final Double totipi) {
        this.totipi = totipi;
    }

    public Integer getCodven() {
        return codven;
    }

    public void setCodven(final Integer codven) {
        this.codven = codven;
    }

    public Integer getCodatd() {
        return codatd;
    }

    public void setCodatd(final Integer codatd) {
        this.codatd = codatd;
    }

    public Double getTotsub() {
        return totsub;
    }

    public void setTotsub(final Double totsub) {
        this.totsub = totsub;
    }

    public Double getTOTDESCINC() {
        return TOTDESCINC;
    }

    public void setTOTDESCINC(final Double TOTDESCINC) {
        this.TOTDESCINC = TOTDESCINC;
    }

    public String getPedant() {
        return pedant;
    }

    public void setPedant(final String pedant) {
        this.pedant = pedant;
    }

    @Override
    public String getCnpjEmpresa() {
        return super.getCnpjEmpresa();
    }

    @Override
    public void setCnpjEmpresa(final String cnpjEmpresa) {
        super.setCnpjEmpresa(cnpjEmpresa);
    }
}
