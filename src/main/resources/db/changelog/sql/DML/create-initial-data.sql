insert into finven(codven, nomven, apeven) values(1, 'Vendedor', 'Vendedor');

insert into finatd(codatd, nomatd, apeatd) values(1, 'Atendente', 'Atendente');

INSERT INTO public.fincli
(codcli, apecli, baacli, baccli, baecli, bafcli, ceacli, ceccli, ceecli, cefcli, cest, cgccli, cgecli, ciacli, ciccli, ciecli, cifcli, clidev, clitab, cnae, coccli, codpal, codpfa, codrep, codusu, coecli, cofcli, comcli, cre_dev_dte, cre_dev_obs, cre_dev_usu, datlim, dcacli, dcalim, dteacm, dteatu, dtlcre, dtncli, dtvsuf, em1cli, em2cli, em3cli, email_interno_xml, enacli, enccli, enecli, enfcli, fa1cli, fa2cli, fa3cli, fa4cli, fc1cli, fc2cli, fc3cli, fc4cli, flbcli, flgca1, flgca2, flgca3, flginf, flgint, flgpro, flgpsq, flgtrg, fo1cli, fo2cli, fo3cli, fo4cli, hralim, hreatu, hrelim, id_fincia, id_fincic, id_finufa, id_finufc, incpag, incpal, indic_estrangeiro, indic_ie, inecli, insc_municipal, inscli, limcli, menbl1, menbl2, menbl3, menbl4, menbl5, mencr1, mencr2, mencr3, mencr4, mencr5, munsuf, nomcli, nracli, nrccli, nrecli, nrfcli, nrosuf, obscli, obsfin, pc1cli, pc2cli, pc3cli, pc4cli, pf1cli, pf2cli, pf3cli, pf4cli, pt1cli, pt2cli, pt3cli, pt4cli, qtdicl, regtrb, rfacli, rfccli, rfecli, rffcli, teacli, teccli, teecli, tefcli, tencob, tencom, tenent, tipcli, tipo_pessoa, tippfa, totacm, ufacli, ufccli, ufecli, uffcli, usalim, usuatu, usulim, webcli, codban, codccl, codcom, codgcl, id_finhol, finmst_id, id_finpai, codtcl, codtra, id_finufe, id_finuff, codatd, codven)
VALUES(1, 'Cliente de teste', 'baacli', 'baccli', 'baecli', 'bafcli', 'ceacli', 'ceccli', 'ceecli', 'cefcli', 'cest', '00.000.000/0001-01', 'cgecli', 'ciacli', 'ciccli', 'ciecli', 'S?o Paulo', NULL, 'clitab', '00000', 'coccli', NULL, 'codpfa', NULL, NULL, 'coecli', 'codcli', 'comcli', '2021-12-15 00:00:00.000', 'cre_dev_obj', 1, '2021-12-15', '2021-12-15', '2021-12-15', '2021-12-15', '2021-12-15', '2021-12-15', NULL, '2021-12-15', 'em1cli', 'em2cli', 'em3cli', 'email_interno_xml', 'enacli', 'enccli', 'enecli', 'enfcli', 'fa1cli', 'fa2cli', 'fa3cli', 'fa4cli', 'fc1cli', 'fc2cli', 'fc3cli', 'fc4cli', 'flbcli', 'flgca1', 'flgca2', 'flgca3', 'flginf', 'flgint', 'flgpro', 'flgpsq', 'flgtrg', 'f01cli', 'fo2cli', 'fo3cli', 'fo4cli', 'hralim', 'hreatu', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Cliente de Teste', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SP', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);

insert into pedres (CODEMP, DTERES, CODCLI, NUMRES, TOTRES, TOTIPI, TOTSUB, TOTDESCINC, TOTGER, TOTLIQ, TOTBRT) VALUES(1, '2021-12-30', 1, 1, 100, 10, 21, 15, 131, 85, 110 );

insert into pedre2 (codemp, dteres, numres, seqre2, CODGRU, CODSUB, CODPRO, DESRE2, QTPRE2, VLQRE2, ICMRE2, TOTRE2,
    totren, BASICM, TOTICM, CODST1, CODST2, REDICM, BASIPI, IPIRE2, TOTIPI, CSTIPI, TOTDSR, TOTFRT, BASSUB, TOTSUB,
    ICMSUB, MRGSUB, REDSUB, BASPIS, ALIQPIS, TOTPIS, CSTPIS, BASCOF, ALIQCOF, TOTCOF, CSTCOF)
 values(1, '2021-12-30', 1, 1,'001', '0001', '00001','Descricao do Primeiro Produto',2,2.5,7,5,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33);

 insert into pedre2 (codemp, dteres, numres, seqre2, CODGRU, CODSUB, CODPRO, DESRE2, QTPRE2, VLQRE2, ICMRE2, TOTRE2,
     totren, BASICM, TOTICM, CODST1, CODST2, REDICM, BASIPI, IPIRE2, TOTIPI, CSTIPI, TOTDSR, TOTFRT, BASSUB, TOTSUB,
     ICMSUB, MRGSUB, REDSUB, BASPIS, ALIQPIS, TOTPIS, CSTPIS, BASCOF, ALIQCOF, TOTCOF, CSTCOF)
  values(1, '2021-12-30', 1, 2,'001', '0001', '00002','Descricao do Segundo Produto',7,1,7,7,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33);

  insert into pedre2 (codemp, dteres, numres, seqre2, CODGRU, CODSUB, CODPRO, DESRE2, QTPRE2, VLQRE2, ICMRE2, TOTRE2,
      totren, BASICM, TOTICM, CODST1, CODST2, REDICM, BASIPI, IPIRE2, TOTIPI, CSTIPI, TOTDSR, TOTFRT, BASSUB, TOTSUB,
      ICMSUB, MRGSUB, REDSUB, BASPIS, ALIQPIS, TOTPIS, CSTPIS, BASCOF, ALIQCOF, TOTCOF, CSTCOF)
   values(1, '2021-12-30', 1, 3,'001', '0001', '00003','Descricao do Terceiro Produto',3,3,7,9,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33);