package br.com.arcasoftware.comercialapi.application.repository.est;

import br.com.arcasoftwares.model.Estpro;
import br.com.arcasoftwares.model.dto.IEstProDTO;
import br.com.arcasoftwares.model.interfaces.EstProMainInfoInterface;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstProRepository extends PagingAndSortingRepository<Estpro, Integer> {

	@Query(nativeQuery = true, value = "select e.codclp, e.codgru, e.codsub, e.codpro, e.dscpro, e.icmsai, e.icment, e.ipisai, e.ipient from Estpro e")
	List<IEstProDTO> getAllDTO();

	@Query(nativeQuery = true, value = "select e.codclp, e.codgru, e.codsub, e.codpro, e.dscpro, e.icmsai, e.icment, e.ipisai, e.ipient from Estpro e" +
			" where codclp = :codclp and codsub = :codsub and codgru  = :codgru and codpro = :codpro")
	IEstProDTO getProdDTOByCodclpCodgruCodsubCodpro(String codclp, String codsub, String codgru, String codpro);
	
	@Cacheable(value = "estpro_regras_item")
	@Query(nativeQuery = true, 
			value = "select pro.icmsai,pro.icmtsd,pro.icment,pro.icmten,pro.codst1,pro.coduns, " + 
					"    pro.ipisai,pro.ipitsd,pro.ipient,pro.ipiten,pro.cbapro,pro.dscpro, ID_ESTICM_SAIDA as idEstIcmSaida, ID_ESTICM_ENTRADA as idEstIcmEntrada," + 
					"    pro.codsts,pro.tipsts,pro.codste,pro.tipste,pro.qtdemb,pro.flbpro,pro.codcom,pro.codncm, pro.cest, pro.COD_FCP_SAIDA as codFcpSaida " + 
					"    FROM estpro pro " + 
					"    where CodClp = :codClp " + 
					"    and CodGru = :codGru " + 
					"    and CodSub = :codSub" + 
					"    and CodPro = :codPro")
    EstProMainInfoInterface getRegrasItem(@Param("codClp") String codClp, @Param("codGru") String codGru, @Param("codSub") String codSub, @Param("codPro") String codPro);
}
