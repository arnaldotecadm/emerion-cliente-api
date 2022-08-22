package br.com.arcasoftware.comercialapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the fincli database table.
 */
@Getter
@Setter
@Entity
@NamedQuery(name = "Fincli.findAll", query = "SELECT f FROM Fincli f")
public class Fincli implements BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codcli;

    private String apecli;

    private String baacli;

    private String baccli;

    private String baecli;

    private String bafcli;

    private String ceacli;

    private String ceccli;

    private String ceecli;

    private String cefcli;

    private String cest;

    private String cgccli;

    private String cgecli;

    private String ciacli;

    private String ciccli;

    private String ciecli;

    private String cifcli;

    private BigDecimal clidev;

    private String clitab;

    private String cnae;

    private String coccli;

    private Integer codpal;

    private String codpfa;

    private Integer codrep;

    private Integer codusu;

    private String coecli;

    private String cofcli;

    private String comcli;

    @JsonFormat(pattern = "MMM dd, yyyy HH:mm:ss a", locale = "us")
    @Column(name = "cre_dev_dte")
    private Date creDevDte;

    @Column(name = "cre_dev_obs")
    private String creDevObs;

    @Column(name = "cre_dev_usu")
    private Integer creDevUsu;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "MMM dd, yyyy HH:mm:ss a", locale = "us")
    private Date datlim;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "MMM dd, yyyy HH:mm:ss a", locale = "us")
    private Date dcacli;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "MMM dd, yyyy HH:mm:ss a", locale = "us")
    private Date dcalim;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "MMM dd, yyyy HH:mm:ss a", locale = "us")
    private Date dteacm;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "MMM dd, yyyy HH:mm:ss a", locale = "us")
    private Date dteatu;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "MMM dd, yyyy HH:mm:ss a", locale = "us")
    private Date dtlcre;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "MMM dd, yyyy HH:mm:ss a", locale = "us")
    private Date dtncli;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "MMM dd, yyyy HH:mm:ss a", locale = "us")
    private Date dtvsuf;

    private String em1cli;

    private String em2cli;

    private String em3cli;

    @Column(name = "email_interno_xml")
    private String emailInternoXml;

    private String enacli;

    private String enccli;

    private String enecli;

    private String enfcli;

    private String fa1cli;

    private String fa2cli;

    private String fa3cli;

    private String fa4cli;

    private String fc1cli;

    private String fc2cli;

    private String fc3cli;

    private String fc4cli;

    private String flbcli;

    private String flgca1;

    private String flgca2;

    private String flgca3;

    private String flginf;

    private String flgint;

    private String flgpro;

    private String flgpsq;

    private String flgtrg;

    private String fo1cli;

    private String fo2cli;

    private String fo3cli;

    private String fo4cli;

    private String hralim;

    private String hreatu;

    private String hrelim;

    @Column(name = "id_fincia")
    private Integer idFincia;

    @Column(name = "id_fincic")
    private Integer idFincic;

    @Column(name = "id_finufa")
    private Integer idFinufa;

    @Column(name = "id_finufc")
    private Integer idFinufc;

    private String incpag;

    private String incpal;

    @Column(name = "indic_estrangeiro")
    private String indicEstrangeiro;

    @Column(name = "indic_ie")
    private Integer indicIe;

    private String inecli;

    @Column(name = "insc_municipal")
    private String inscMunicipal;

    private String inscli;

    private BigDecimal limcli;

    private String menbl1;

    private String menbl2;

    private String menbl3;

    private String menbl4;

    private String menbl5;

    private String mencr1;

    private String mencr2;

    private String mencr3;

    private String mencr4;

    private String mencr5;

    private Integer munsuf;

    private String nomcli;

    private String nracli;

    private String nrccli;

    private String nrecli;

    private String nrfcli;

    private String nrosuf;

    private String obscli;

    private String obsfin;

    private String pc1cli;

    private String pc2cli;

    private String pc3cli;

    private String pc4cli;

    private String pf1cli;

    private String pf2cli;

    private String pf3cli;

    private String pf4cli;

    private String pt1cli;

    private String pt2cli;

    private String pt3cli;

    private String pt4cli;

    private Integer qtdicl;

    private Integer regtrb;

    private String rfacli;

    private String rfccli;

    private String rfecli;

    private String rffcli;

    private String teacli;

    private String teccli;

    private String teecli;

    private String tefcli;

    private String tencob;

    private String tencom;

    private String tenent;

    private String tipcli;

    @Column(name = "tipo_pessoa")
    private String tipoPessoa;

    private String tippfa;

    private BigDecimal totacm;

    private String ufacli;

    private String ufccli;

    private String ufecli;

    private String uffcli;

    private Integer usalim;

    private Integer usuatu;

    private Integer usulim;

    private String webcli;

    private Integer codmcr;
    private Integer codmrg;
    private Integer codset;
    private Integer codmst;
    @Column(name = "ID_FINCIF")
    private Integer idFincif;
    @Column(name = "ID_FINCIE")
    private Integer idFincie;
    @Column(name = "ID_FINCTB")
    private Integer idFinctb;
    @Column(name = "ID_FINCT2")
    private Integer idFinct2;
    @Column(name = "ID_FINCT3")
    private Integer idFinct3;
    @Column(name = "ID_FINCT4")
    private Integer idFinct4;
    @Column(name = "ID_FINCT5")
    private Integer idFinct5;
    @Column(name = "ID_FINCT6")
    private Integer idFinct6;

    @Transient
    @Override
    public Integer getId() {
        return this.getCodcli();
    }
}
