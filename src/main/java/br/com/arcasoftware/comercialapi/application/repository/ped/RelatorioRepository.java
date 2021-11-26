package br.com.arcasoftware.comercialapi.application.repository.ped;

import br.com.arcasoftware.comercialapi.controller.relatorio.interfaces.ICurvaABCCliente;
import br.com.arcasoftware.comercialapi.controller.relatorio.interfaces.ICurvaABCVendedor;
import br.com.arcasoftwares.model.Estpro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface RelatorioRepository extends CrudRepository<Estpro, Integer> {

    @Query(nativeQuery = true, value = "select codCli, nomCli, sum(TOTLIB) totalPedido, sum(TOTCST) totalCusto,\n" +
            "    case sum(TOTCST)\n" +
            "        when 0\n" +
            "            then 0\n" +
            "            else round(((sum(TOTLIB) - sum(TOTCST)) * 100)/sum(TOTCST),2)\n" +
            "    end markup,\n" +
            "    sum(QTDPED) qtdPedido\n" +
            "from relatorio_curva_abc_cliente\n" +
            "group by CODCLI, NOMCLI")
    List<ICurvaABCCliente> getCurvaAbcCliente();

    @Query(nativeQuery = true, value = "Select\n" +
            "    PedLib.CodVen,        FinVen.ApeVen,        Round(Sum(PedLb2.TotLb2 - (PedLb2.TotDsr + PedLb2.TotIpc)),2) as TotLiq,\n" +
            "    Round(Sum((PedLb2.UltQts * ( PedLb2.VluLb2 - PedLb2.VdrLb2 )) - (PedLb2.TotIpc + PedLb2.TotDsr)),2) as TotBrt,\n" +
            "    Round(Sum(PedLb2.TotCst),4) as CstPrt,        Round(Sum(PedLb2.UltQts * PedLb2.VpfCst),4) as CstPfi,\n" +
            "    Round(Sum(PedLb2.UltQts * PedLb2.VmeCst),4) as CstVme,\n" +
            "    Round(Sum(PedLb2.UltQts * (( PedLb2.VluLb2 * PedLb2.DscPer )/100)),2) as DscPer,        Round(Sum(PedLb2.TotDsc),2) as DscPra,\n" +
            "    case Round(Sum(PedLb2.UltQts * (( PedLb2.VluLb2 * PedLb2.DscPer )/100)),2)\n" +
            "        when 0\n" +
            "            then 0\n" +
            "            else ROUND(((Round(Sum(PedLb2.TotDsc),2) * 100)/    Round(Sum(PedLb2.UltQts * (( PedLb2.VluLb2 * PedLb2.DscPer )/100)),2)) - 100,2)\n" +
            "    end DIFDSC,\n" +
            "\n" +
            "    case Round(Sum(PedLb2.TotCst),4)\n" +
            "        when 0\n" +
            "            then 0\n" +
            "            else ROUND(((Round(Sum((PedLb2.UltQts * ( PedLb2.VluLb2 - PedLb2.VdrLb2 )) - (PedLb2.TotIpc + PedLb2.TotDsr)),2) - Round(Sum(PedLb2.TotCst),4)) * 100)/Round(Sum(PedLb2.TotCst),4),2)\n" +
            "    end MARPRE,\n" +
            "    case Round(Sum(PedLb2.TotCst),4)\n" +
            "        when 0\n" +
            "            then 0\n" +
            "            else ROUND(((Round(Sum(PedLb2.TotLb2 - (PedLb2.TotDsr + PedLb2.TotIpc)),2) - Round(Sum(PedLb2.TotCst),4)) * 100)/Round(Sum(PedLb2.TotCst),4),2)\n" +
            "    end MARPED,\n" +
            "    ROUND(Round(Sum(PedLb2.TotLb2 - (PedLb2.TotDsr + PedLb2.TotIpc)),2) - Round(Sum(PedLb2.TotCst),4),2) LUCROL,\n" +
            "    ROUND(Round(Sum((PedLb2.UltQts * ( PedLb2.VluLb2 - PedLb2.VdrLb2 )) - (PedLb2.TotIpc + PedLb2.TotDsr)),2) - Round(Sum(PedLb2.TotCst),4),2) LUCROP,\n" +
            "    Round(Sum(PedLb2.SldLb2 * ( PedLb2.VlqLb2 - PedLb2.VdrLb2 )),4) as TotNat,\n" +
            "    ROUND(Round(Sum((PedLb2.UltQts * ( PedLb2.VluLb2 - PedLb2.VdrLb2 )) - (PedLb2.TotIpc + PedLb2.TotDsr)),2) - Round(Sum(PedLb2.UltQts * (( PedLb2.VluLb2 * PedLb2.DscPer )/100)),2),2) TOTPER,\n" +
            "    '94711791.83' TOTFAT,\n" +
            "    '57849840.61' CSTFAT\n" +
            "\n" +
            "From  PedLib\n" +
            "left join PedLb2 on PedLb2.CodEmp = PedLib.CodEmp  and PedLb2.DteRes = PedLib.DteRes  and PedLb2.NumRes = PedLib.NumRes  and PedLb2.SeqLib = PedLib.SeqLib\n" +
            "left join FinVen on PedLib.CodVen = FinVen.CodVen\n" +
            "Where 1 = 1\n" +
            "    and PedLib.ModPfa = 'Vendas'\n" +
            "    and (\n" +
            "            ( PedLib.SitLib = 'Faturado' ) or\n" +
            "            ( PedLib.SitLib = 'Devolvido' and not MonthOfYear(PedLib.DteFat) = MonthOfYear(PedLib.DteDev) ) or\n" +
            "            ( PedLib.SitLib = 'Cancelado' and not MonthOfYear(PedLib.DteFat) = MonthOfYear(PedLib.DteDel) )\n" +
            "    )\n" +
            "Group by PedLib.CodVen,FinVen.ApeVen")
    List<ICurvaABCVendedor> getCurvaAbcVendedor();

    @Query(nativeQuery = true, value = "select Round(Sum(PedLb2.TotLb2 - (PedLb2.TotIpc + PedLb2.TotDsr)),2) From  PedLib\n" +
            "left join PedLb2 on PedLb2.CodEmp = PedLib.CodEmp  and PedLb2.DteRes = PedLib.DteRes  and PedLb2.NumRes = PedLib.NumRes  and PedLb2.SeqLib = PedLib.SeqLib\n" +
            "    where 1 = 1\n" +
            "    and PedLib.ModPfa = 'Vendas' and ( ( PedLib.SitLib = 'Faturado' ) or ( PedLib.SitLib = 'Devolvido' and not MonthOfYear(PedLib.DteFat) = MonthOfYear(PedLib.DteDev) ) or ( PedLib.SitLib = 'Cancelado' and not MonthOfYear(PedLib.DteFat) = MonthOfYear(PedLib.DteDel) ) )")
    BigDecimal getTotalGeralCurvaAbcVendedorFirstStep();

    @Query(nativeQuery = true, value = "select Round(Sum(PedLb2.TotCst),4)\n" +
            "    From  PedLib\n" +
            "    left join PedLb2 on PedLb2.CodEmp = PedLib.CodEmp  and PedLb2.DteRes = PedLib.DteRes  and PedLb2.NumRes = PedLib.NumRes  and PedLb2.SeqLib = PedLib.SeqLib\n" +
            "    where 1 = 1\n" +
            "    and PedLib.ModPfa = 'Vendas' and ( ( PedLib.SitLib = 'Faturado' ) or ( PedLib.SitLib = 'Devolvido' and not MonthOfYear(PedLib.DteFat) = MonthOfYear(PedLib.DteDev) ) or ( PedLib.SitLib = 'Cancelado' and not MonthOfYear(PedLib.DteFat) = MonthOfYear(PedLib.DteDel) ) )\n" +
            "    ")
    BigDecimal getTotalGeralCurvaAbcVendedorSecondStep();
}
