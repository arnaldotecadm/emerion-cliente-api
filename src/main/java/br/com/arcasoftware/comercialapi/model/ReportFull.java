package br.com.arcasoftware.comercialapi.model;

import br.com.arcasoftwares.model.dto.IReportPedRe2Detail;
import br.com.arcasoftwares.model.dto.IReportPedResHead;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ReportFull {
    IReportPedResHead pedres;
    List<IReportPedRe2Detail> pedre2;
}
