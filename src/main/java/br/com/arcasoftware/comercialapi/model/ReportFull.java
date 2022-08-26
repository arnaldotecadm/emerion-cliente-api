package br.com.arcasoftware.comercialapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ReportFull {
    IReportPedResHead pedres;
    List<IReportPedRe2Detail> pedre2;
}
