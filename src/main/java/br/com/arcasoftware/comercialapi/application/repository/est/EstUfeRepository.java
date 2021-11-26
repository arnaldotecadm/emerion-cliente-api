package br.com.arcasoftware.comercialapi.application.repository.est;

import br.com.arcasoftwares.model.Estufe;
import br.com.arcasoftwares.model.dto.IEstUfeDTO;
import br.com.arcasoftwares.model.interfaces.EstUfeBasicInfo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstUfeRepository extends PagingAndSortingRepository<Estufe, Integer> {

	@Cacheable(value = "estufe_identificador")
	@Query(nativeQuery = true, value = "select ufe.codstr, ufe.tipstr, ufe.sigufe, ufe.icmsub,"
			+ "      CASE WHEN(:origem"
			+ "      in (1,2,3,8) AND (ufe.UFEMITENTE <> ufe.sigufe) )THEN MRGMVA_SINIEF ELSE MRGMVA END mrgmva,"
			+ "      ufe.basesb, ufe.codcfo, ufe.redsub, ufe.codst2, ufe.codcfo_dev, ufe.codtxf, ufe.MRGMVA_SN, ufe.MRGMVA_SN_SINIEF,"
			+ " codregtrib, ufemitente " + "      from estufe ufe " + "      where ufe.codstr = :codstr"
			+ "      and ufe.tipstr = :tipstr " + "      and ufe.sigufe = :sigufe "
			+ "      AND ufe.UFEMITENTE = :ufEmitente" + "      and ufe.codregtrib = :codRegTrib")
	Optional<EstUfeBasicInfo> getByIdentificador(@Param("origem") Integer origem, @Param("codstr") String codstr,
			@Param("tipstr") String tipstr, @Param("sigufe") String sigufe, @Param("ufEmitente") String ufEmitente,
			@Param("codRegTrib") Integer codRegTrib);

	@Query(nativeQuery = true, value = "Select \n" +
			"\tufe.codstr, ufe.tipstr,ufe.sigufe, ufe.ufemitente, ufe.codregtrib,\n" +
			"\tufe.icmsub, ufe.redsub, ufe.basesb, ufe.mrgmva, ufe.MRGMVA_SINIEF AS MRGMVASINIEF, ufe.codcfo,\n" +
			"\tufe.MRGMVA_SN AS MRGMVASN, ufe.MRGMVA_SN_SINIEF AS MRGMVASNSINIEF, ufe.CODST2, ufe.CODTXF\n" +
			"From EstUfe ufe LEFT JOIN GerUfe ON (EstUfe.SigUfe = GerUfe.SigUfe)\n" +
			"Order by EstUfe.SigUfe")
	List<IEstUfeDTO> getAllDTO();

	@Query(nativeQuery = true, value = "Select \n" +
			"\tufe.codstr, ufe.tipstr,ufe.sigufe, ufe.ufemitente, ufe.codregtrib,\n" +
			"\tufe.icmsub, ufe.redsub, ufe.basesb, ufe.mrgmva, ufe.MRGMVA_SINIEF AS MRGMVASINIEF, ufe.codcfo,\n" +
			"\tufe.MRGMVA_SN AS MRGMVASN, ufe.MRGMVA_SN_SINIEF AS MRGMVASNSINIEF, ufe.CODST2, ufe.CODTXF\n" +
			"From EstUfe ufe LEFT JOIN GerUfe ON (EstUfe.SigUfe = GerUfe.SigUfe)\n" +
			" where  ufe.CodStr = :codigo\n" +
			"    and ufe.TipStr = :tipo" +
			" Order by EstUfe.SigUfe")
	List<IEstUfeDTO> getAllByTipoAndCodigo(@Param("tipo") String tipo, @Param("codigo") String codigo);
}
