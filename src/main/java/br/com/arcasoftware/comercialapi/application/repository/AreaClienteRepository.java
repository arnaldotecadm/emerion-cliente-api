package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.model.DashBoardClienteInfo;
import br.com.arcasoftware.comercialapi.model.DashBoardClienteInfoCompleto;
import br.com.arcasoftware.comercialapi.model.DashBoardCreditoDevolucao;
import br.com.arcasoftware.comercialapi.model.DashBoardCreditoInfo;
import br.com.arcasoftware.comercialapi.model.DashBoardEnderecoCompleto;
import br.com.arcasoftware.comercialapi.model.DashBoardEnderecoInfo;
import br.com.arcasoftware.comercialapi.model.Fincli;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AreaClienteRepository extends PagingAndSortingRepository<Fincli, Integer> {

    @Query(nativeQuery = true, value = "SELECT \n" +
            " cli.nomcli, cli.apecli, cli.CGCCLI, cli.INSCLI, f.NOMREGTRIB, cli.CLIDEV \n" +
            " FROM fincli cli\n" +
            " LEFT JOIN FINREGTRIB f ON f.NUMREGTRIB = cli.REGTRB" +
            " where codcli = :codcli")
    DashBoardClienteInfo getDashboardClienteInfo(@Param("codcli") Integer codcli);

    @Query(nativeQuery = true, value = "Select\n" +
            "\tcde.seqcde, cde.dtecde,\n" +
            "\tCASE \n" +
            "\t\tWHEN cde.USACDE <= 0\n" +
            "\t\tTHEN 'ENTRADA'\n" +
            "\t\tELSE 'SAIDA'\n" +
            "\tEND AS tipo,\n" +
            "\t\tCASE \n" +
            "\t\tWHEN cde.USACDE <= 0\n" +
            "\t\tTHEN cde.VALCDE \n" +
            "\t\tELSE cde.USACDE \n" +
            "\tEND AS valor\n" +
            "From FinCde cde\n" +
            "Where" +
            " cde.codcli = :codcli\n" +
            "order by cde.dtecde ")
    List<DashBoardCreditoInfo> getDashboardCreditoInfo(@Param("codcli") Integer codcli);

    @Query(nativeQuery = true, value = "SELECT cast('Faturamento' as varchar(20)) AS tipo, cefcli as cep FROM fincli WHERE codcli = :codcli\n" +
            "UNION all\n" +
            "SELECT cast('Cobrança' as varchar(20)), CECCLI FROM fincli WHERE codcli = :codcli\n" +
            "UNION all\n" +
            "SELECT cast('Compras' as varchar(20)), CEACLI FROM fincli WHERE codcli = :codcli\n" +
            "UNION all\n" +
            "SELECT cast('Entrega' as varchar(20)), CEECLI FROM fincli WHERE codcli = :codcli")
    List<DashBoardEnderecoInfo> getDashboardEnderecoInfo(@Param("codcli") Integer codcli);

    @Query(nativeQuery = true, value = "SELECT cast('Faturamento'as varchar(20)) AS tipo, cefcli, TEFCLI, ENFCLI, NRFCLI, RFFCLI, BAFCLI, CIFCLI, UFFCLI, PT1CLI, FO1CLI, COFCLI, PC1CLI, FC1CLI\n" +
            "FROM fincli WHERE codcli = :codcli\n" +
            "UNION all\n" +
            "SELECT cast('Cobrança' as varchar(20)), \tceccli, TECCLI, ENCCLI, NRCCLI, RFCCLI, BACCLI, CICCLI, UFCCLI, PT2CLI, FO2CLI, COCCLI, PC2CLI, FC2CLI\n" +
            "FROM fincli WHERE codcli = :codcli\n" +
            "UNION all\n" +
            "SELECT cast('Compras' as varchar(20)), \tCEACLI, TEACLI, ENACLI, NRACLI, RFACLI, BAACLI, CIACLI, UFACLI, PT3CLI, FO3CLI, COMCLI, PC3CLI, FC3CLI\n" +
            "FROM fincli WHERE codcli = :codcli\n" +
            "UNION all\n" +
            "SELECT cast('Entrega' as varchar(20)), CEECLI, TEECLI, ENECLI, NRECLI, RFECLI, BAECLI, CIECLI, UFECLI, PT4CLI, FO4CLI, COECLI, PC4CLI, FC4CLI\n" +
            "FROM fincli WHERE codcli = :codcli")
    List<DashBoardEnderecoCompleto> getDashboardEnderecoCompleto(@Param("codcli") Integer codcli);

    @Query(nativeQuery = true, value = "SELECT \n" +
            "\t codcli, apecli, nomcli, INDIC_ESTRANGEIRO as indicEstrangeiro, cgccli, dtncli, inscli, tii.TIPO, f.NOMREGTRIB, INSC_MUNICIPAL as inscMunicipal, CNAE, obscli\n" +
            "FROM fincli cli \n" +
            "LEFT JOIN TIP_INDIC_IE tii ON tii.ID = cli.INDIC_IE \n" +
            "LEFT JOIN FINREGTRIB f ON f.NUMREGTRIB = cli.REGTRB \n" +
            "WHERE codcli = :codcli")
    DashBoardClienteInfoCompleto getDashboardClienteInfoCompleto(@Param("codcli") Integer codcli);

    @Query(nativeQuery = true, value = "SELECT \n" +
            "\tseqcde, valcde, usacde, sldcde, dteped, obscde, dtecde, sitcde, \tCASE \n" +
            "\t\tWHEN cde.USACDE <= 0\n" +
            "\t\tTHEN 'ENTRADA'\n" +
            "\t\tELSE 'SAIDA'\n" +
            "\tEND AS tipo\n" +
            "FROM fincde cde" +
            " WHERE codcli = :codcli" +
            " order by sldcde DESC")
    List<DashBoardCreditoDevolucao> getDashboardCreditoDevolucao(@Param("codcli") Integer codcli);
}
