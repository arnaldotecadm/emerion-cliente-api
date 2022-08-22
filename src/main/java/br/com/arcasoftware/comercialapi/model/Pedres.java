
package br.com.arcasoftware.comercialapi.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the pedres database table.
 */
@Getter
@Setter
@Entity
@NamedQuery(name = "Pedres.findAll", query = "SELECT p FROM Pedres p")
public class Pedres {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PedresPK id;

    private BigDecimal acrcnd;

    @Column(name = "aliq_cred_sn")
    private BigDecimal aliqCredSn;

    private BigDecimal aliqcof;

    private BigDecimal aliqpis;

    private Integer anores;

    @Column(name = "app_ver")
    private Integer appVer;

    private String atuest;

    private String baicli;

    private BigDecimal bascat;

    private BigDecimal bascom;

    private BigDecimal basicm;

    private BigDecimal basipi;

    private BigDecimal basiss;

    private BigDecimal bassub;

    private BigDecimal bscicm;

    private BigDecimal bscipi;

    private String cepcli;

    private String cgccli;

    private String cgecli;

    private String cidcli;

    private String clitab;

    private Integer codatd;

    private String codcfo;

    private Integer codcli;

    private String codcom;

    private Integer coddesoneracao;

    private String codean;

    private Integer codfil;

    private Integer codgcl;

    private Integer codgus;

    private Integer codgve;

    private String codicm;

    private String codipi;

    private String codpfa;

    private Integer codtra;

    private Integer codusu;

    private Integer codven;

    private String comatd;

    private String comcli;

    private String comicli;

    private String consum;

    private String cstcof;

    private String cstipi;

    private String cstpis;

    private BigDecimal desicm;

    private BigDecimal desipi;

    private String desreg;

    private BigDecimal dessub;

    private BigDecimal devger;

    private BigDecimal devres;

    private BigDecimal dsccnd;

    private BigDecimal dsccom;

    private BigDecimal dscicm;

    private Double dscipi;

    private BigDecimal dscreg;

    private BigDecimal dscres;

    private BigDecimal dscsub;

    @Temporal(TemporalType.DATE)
    private Date dtecmp;

    @Temporal(TemporalType.DATE)
    private Date dtecom;

    @Temporal(TemporalType.DATE)
    private Date dtecon;

    @Temporal(TemporalType.DATE)
    private Date dtecta;

    @Temporal(TemporalType.DATE)
    private Date dtedel;

    @Temporal(TemporalType.DATE)
    private Date dtefin;

    @Temporal(TemporalType.DATE)
    private Date dtefpe;

    @Temporal(TemporalType.DATE)
    private Date dteped;

    @Temporal(TemporalType.DATE)
    private Date dtepro;

    @Temporal(TemporalType.DATE)
    private Date dterej;

    @Temporal(TemporalType.DATE)
    private Date dtfres;

    @Temporal(TemporalType.DATE)
    private Date dtlpsq;

    @Temporal(TemporalType.DATE)
    private Date dtvsuf;

    private String ecfemi;

    private Integer empcta;

    private Integer empped;

    private String endcli;

    private BigDecimal fatger;

    private BigDecimal fatres;

    private String flgant;

    private String flgatu;

    private String flgavi;

    private String flgcli;

    private String flgcmp;

    private String flgcom;

    private String flgcon;

    private String flgctb;

    private String flgdif;

    private String flgest;

    private String flgfec;

    private String flgfin;

    private String flgger;

    private String flgimp;

    private String flgipi;

    private String flgmobile;

    private String flgoco;

    private String flgope;

    private String flgpro;

    private String flgpsq;

    private String flgrej;

    private String flgreq;

    private String flgres;

    private String flgser;

    private String flgsld;

    private String flgtab;

    private BigDecimal frticm;

    private BigDecimal frtipi;

    private String frtres;

    private BigDecimal frtsub;

    private String hrecmp;

    private String hrecom;

    private String hrecon;

    private String hredel;

    private String hrefin;

    private String hrefpe;

    private String hrepro;

    private String hrerej;

    private String hreres;

    @Column(name = "id_esticm")
    private Integer idEsticm;

    @Column(name = "id_estipi")
    private Integer idEstipi;

    @Column(name = "id_estsip")
    private Integer idEstsip;

    @Column(name = "id_fincie")
    private Integer idFincie;

    @Column(name = "id_finufe")
    private Integer idFinufe;

    @Column(name = "id_frete")
    private Integer idFrete;

    @Column(name = "id_pedcnd")
    private Integer idPedcnd;

    @Column(name = "id_pedcta")
    private Integer idPedcta;

    @Column(name = "id_pedfe2")
    private Integer idPedfe2;

    @Column(name = "id_pedres")
    private Integer idPedres;

    @Column(name = "id_pedres_orig")
    private Integer idPedresOrig;

    @Column(name = "id_sepped")
    private Integer idSepped;

    private BigDecimal incfin;

    private BigDecimal increv;

    @Column(name = "indic_cf")
    private Integer indicCf;

    @Column(name = "indic_presenca")
    private Integer indicPresenca;

    private String inecli;

    private String inscli;

    private String intfin;

    @Column(name = "is_cta")
    private Integer isCta;

    private String lanest;

    private String libcli;

    private String libsld;

    private BigDecimal limcli;

    private Integer linres;

    private BigDecimal medacr;

    private BigDecimal medcat;

    private BigDecimal medcom;

    private BigDecimal meddco;

    private BigDecimal meddsc;

    private BigDecimal medprm;

    private String mesres;

    private String modpfa;

    private String noment;

    private String nrosuf;

    private String numcli;

    private Integer numcta;

    private Integer numped;

    private String ob1can;

    private String ob1fat;

    private String ob1res;

    private String ob2can;

    private String ob2fat;

    private String ob2res;

    private String ob3can;

    private String ob3fat;

    private String ob3res;

    private String ob4can;

    private String ob4fat;

    private String ob4res;

    private String ob5can;

    private String ob5fat;

    private String ob5res;

    private String ob6fat;

    private String ob6res;

    private String ob7fat;

    private String ob7res;

    private String ob8fat;

    private String ob8res;

    private String obrgve;

    private String obsant;

    private String obscmp;

    private String obscom;

    private String obscon;

    private String obsdel;

    private String obsfin;

    private String obsfpe;

    private String obspro;

    private String obsrej;

    private String obsres;

    private BigDecimal pcoatd;

    private BigDecimal pcores;

    private String pedant;

    private String pedpro;

    private BigDecimal percof;

    private BigDecimal perpis;

    private String prccta;

    private Integer prfres;

    private Integer qtdimp;

    private Integer qtfres;

    private Integer qtilib;

    private Integer qtire2;

    private Integer qtire3;

    private Integer qtire4;

    private Integer qtires;

    private Integer qtlres;

    private Integer qtpres;

    private BigDecimal redicm;

    private BigDecimal redipi;

    private String refcli;

    private Integer regtrb;

    private Integer regtrbemp;

    private BigDecimal renmin;

    private BigDecimal segicm;

    private BigDecimal segipi;

    private BigDecimal segsub;

    private Integer seqite;

    private Integer seqlib;

    private Integer seqpar;

    private Integer seqre3;

    private String seqres;

    private String sitres;

    private BigDecimal sldger;

    private BigDecimal sldres;

    @Column(name = "sufr_cofins")
    private String sufrCofins;

    @Column(name = "sufr_icms")
    private String sufrIcms;

    @Column(name = "sufr_ipi")
    private String sufrIpi;

    @Column(name = "sufr_pis")
    private String sufrPis;

    private String tencli;

    private String tipcom;

    private String tipcpa;

    private String tipfre;

    private String tipfrt;

    private String tipicm;

    private String tipipi;

    private BigDecimal totacp;

    private BigDecimal totacr;

    private BigDecimal totbrt;

    private BigDecimal totcat;

    private BigDecimal totcli;

    private BigDecimal totcof;

    private BigDecimal totcom;

    private BigDecimal totcst;

    private BigDecimal totcub;

    private BigDecimal totdco;

    private BigDecimal totdescinc;

    private BigDecimal totdsc;

    private BigDecimal totdsp;

    private BigDecimal totdsr;

    private BigDecimal totfrt;

    private BigDecimal totger;

    private BigDecimal totibpt;

    private BigDecimal toticm;

    private BigDecimal totipc;

    private BigDecimal totipi;

    private BigDecimal totiss;

    private BigDecimal totitetrb;

    private BigDecimal totliq;

    private BigDecimal totoutdesp;

    private BigDecimal totpis;

    private BigDecimal totren;

    private BigDecimal totres;

    private BigDecimal totseg;

    private BigDecimal totsub;

    private BigDecimal totven;

    private Integer totvol;

    private String trbcof;

    private String trbicm;

    private String trbipi;

    private String trbpis;

    private String txficm;

    private String txfipi;

    private String ufecli;

    private String uferes;

    private String updproc;

    private Integer usuant;

    private Integer usucmp;

    private Integer usucom;

    private Integer usucon;

    private Integer usudel;

    private Integer usufin;

    private Integer usufpe;

    private Integer usupro;

    private Integer usurej;

    private Integer usuimp;

}