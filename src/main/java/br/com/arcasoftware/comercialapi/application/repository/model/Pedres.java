package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(
        name = "pedres",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"cnpjEmpresa", "numres"})},
        indexes = {
                @Index(name = "pedres_numres", columnList = "numres"),
                @Index(name = "pedres_codcli", columnList = "codcli")
        })
public class Pedres extends BaseEntity {
    private long codcli;
    private Integer codemp;
    private Integer codtra;
    private String hreres;
    @Column(name = "ID_FRETE")
    private Integer idFrete;
    private String cgccli;
    private String noment;
    private String dtefin;
    private String hrefin;
    private Double qtires;
    private Double qtpres;
    private String lanest;
    private Integer codfil;
    private Integer codtip;
    private String tippfa;
    private Double toticm;
    private Double totcst;
    private String meddsc;
    private Double totdsc;
    private Double medacr;
    private Double totacr;
    private Double medprm;
    private Double totdsp;
    private Double medcom;
    private Double totcom;
    private Double meddco;
    private Double totdco;
    private Double dscreg;
    private Double totdsr;
    private Integer usurej;
    private String flgoco;
    private String atuest;
    private String cidcli;
    private String ufecli;
    private String cepcli;
    private Double totfrt;
    private Double totseg;
    private Double totoutdesp;
    private String flgfec;
    private String inscli;
    private String tencli;
    private String endcli;
    private String numcli;
    private String baicli;
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
    private Double totliq;
    private Double totbrt;
    @Temporal(TemporalType.DATE)
    private Date dteres;
    private Date dtfres;
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

    public Date getDteres() {
        return dteres;
    }

    public void setDteres(final Date dteres) {
        this.dteres = dteres;
    }

    public Date getDtfres() {
        return dtfres;
    }

    public void setDtfres(final Date dtfres) {
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

    public Double getTotliq() {
        return totliq;
    }

    public void setTotliq(final Double totliq) {
        this.totliq = totliq;
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
