package br.com.arcasoftware.comercialapi.application.repository.est;

import br.com.arcasoftwares.model.Esticm;
import br.com.arcasoftwares.model.dto.IEstIcmDTO;
import br.com.arcasoftwares.model.interfaces.CnaeInterface;
import br.com.arcasoftwares.model.interfaces.FcpInterface;
import br.com.arcasoftwares.model.interfaces.GerIcmInterface;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstIcmRepository extends PagingAndSortingRepository<Esticm, Integer> {

	@Query(nativeQuery = true, value = "SELECT icm.codicm, icm.tipicm, icm.nomicm, icm.codst2, icm.pericm, icm.ufemitente, icm.codregtrib, icm.id_esticm AS idesticm FROM esticm icm")
	List<IEstIcmDTO> getAllDTO();

	@Cacheable(value = "esticm_identificador")
	@Query(nativeQuery = true, value = "select * from esticm icm where icm.tipicm = ?1 and icm.codicm = ?2 and icm.ufemitente = ?3 and icm.codregtrib = ?4")
	Optional<Esticm> getByIdentificador(String tipicm, String codicm, String ufeemitente, Integer codregtrib);

	@Query(nativeQuery = true, value = "select * from esticm icm where icm.tipicm = ?1 and icm.codicm = ?2")
	List<Esticm> getByTipoCodigo(String tipicm, String codicm);

	@Cacheable(value = "get_cnae")
	@Query(nativeQuery = true, value = "select CARGA_MEDIA from cnae_carga_media CNAE where CNAE.cnae = :cnae")
	Optional<CnaeInterface> getCnae(@Param("cnae") String cnae);

	@Cacheable(value = "get_fcp")
	@Query(nativeQuery = true, value = " select fcu.ALIQ_CFP as AliqFcp,    " + " fcp.ID_REGRA_FCP as IdRegraFcp " + " from regra_fcp fcp "
			+ " join regra_fcp_uf fcu on fcu.id_regra_fcp = fcp.id_regra_fcp and fcp.tip_fcp = 'Saida'"
			+ " where fcp.cod_regra_fcp = :codRegraFcp" + " and fcu.SIGUFE = :ufeCli")
	Optional<FcpInterface> getFcp(@Param("codRegraFcp") String codRegraFcp, @Param("ufeCli") String ufeCli);

	@Cacheable(value = "get_gericm")
	@Query(nativeQuery = true, value = "SELECT first 1 perIcm, redInt , ALIQ_INTERNA as aliqInterna FROM GERICM WHERE GERICM.SIGUFE = :sigUfe")
	Optional<GerIcmInterface> getGerIcm(@Param("sigUfe") String sigUfe);
}
