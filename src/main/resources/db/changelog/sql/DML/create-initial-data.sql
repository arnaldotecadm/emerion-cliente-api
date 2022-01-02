INSERT INTO public.geremp(codemp, baiemp, cepemp, cgcemp, cidemp, emaemp, endemp, fonemp, insemp, nomemp, numemp, prtemp, sigufe, tenemp, tipemp, webemp)
VALUES(1, 'SP', '00000-123', '00.000.000/0000-01', 'São Paulo', 'emailteste@teste.com', 'Endereço da empresa', '(11)0000.0000', '012347', 'Nome da Empresa', '5', 'prtemp', 'SP', 'tenemp', 'Normal', 'sitedaempresa.com.br');

insert into finven(codven, nomven, apeven, PRFVEN, fonven, emaven) values(1, 'Vendedor', 'Vendedor', '11', '1234-5678', 'emailvendedor@email.com');

INSERT INTO public.tipfrt(id, descfrt) VALUES(1, 'FOB');

insert into TIP_INDIC_IE(id, tipo) values (1, 'Consumidor final');

insert into finregtrib (numregtrib, nomregtrib) values (1, 'Simples Nacional');

insert into finatd(codatd, nomatd, apeatd) values(1, 'Atendente', 'Atendente');

INSERT INTO public.fintra
(codtra, apetra, nomtra, CGCTRA, CIDTRA, SIGUFE, CEPTRA, PRTTRA, FONTRA, TENTRA, ENDTRA, NUMTRA, BAITRA)
VALUES(1, 'Nosso Carro', 'Nosso Carro', '01.123.123/0001-12', 'Cidade Transportadora', 'SP', 'CEP Transp', '11', '1234-1234', 'TEN TRA', 'Endereço Transportadora', 'Num Transp', 'Bairro Transp');

INSERT INTO public.fincli
(codcli, INDIC_IE, REGTRB, apecli, baacli, baccli, baecli, bafcli, ceacli, ceccli, ceecli, cefcli, cest, cgccli, cgecli, ciacli, ciccli, ciecli, cifcli, clidev, clitab, cnae, coccli, codpal, codpfa, codrep, codusu, coecli, cofcli, comcli, cre_dev_dte, cre_dev_obs, cre_dev_usu, datlim, dcacli, dcalim, dteacm, dteatu, dtlcre, dtncli, dtvsuf, em1cli, em2cli, em3cli, email_interno_xml, enacli, enccli, enecli, enfcli, fa1cli, fa2cli, fa3cli, fa4cli, fc1cli, fc2cli, fc3cli, fc4cli, flbcli, flgca1, flgca2, flgca3, flginf, flgint, flgpro, flgpsq, flgtrg, fo1cli, fo2cli, fo3cli, fo4cli, hralim, hreatu, hrelim, id_fincia, id_fincic, id_finufa, id_finufc, incpag, incpal, indic_estrangeiro, inecli, insc_municipal, inscli, limcli, menbl1, menbl2, menbl3, menbl4, menbl5, mencr1, mencr2, mencr3, mencr4, mencr5, munsuf, nomcli, nracli, nrccli, nrecli, nrfcli, nrosuf, obscli, obsfin, pc1cli, pc2cli, pc3cli, pc4cli, pf1cli, pf2cli, pf3cli, pf4cli, pt1cli, pt2cli, pt3cli, pt4cli, qtdicl, rfacli, rfccli, rfecli, rffcli, teacli, teccli, teecli, tefcli, tencob, tencom, tenent, tipcli, tipo_pessoa, tippfa, totacm, ufacli, ufccli, ufecli, uffcli, usalim, usuatu, usulim, webcli, codban, codccl, codcom, codgcl, id_finhol, finmst_id, id_finpai, codtcl, codtra, id_finufe, id_finuff, codatd, codven)
VALUES(1, 1, 1, 'Cliente de teste', 'baacli', 'baccli', 'baecli', 'bafcli', 'ceacli', 'ceccli', 'ceecli', 'cefcli', 'cest', '00.000.000/0001-01', 'cgecli', 'ciacli', 'ciccli', 'ciecli', 'S?o Paulo', NULL, 'clitab', '00000', 'coccli', NULL, 'codpfa', NULL, NULL, 'coecli', 'codcli', 'comcli', '2021-12-15 00:00:00.000', 'cre_dev_obj', 1, '2021-12-15', '2021-12-15', '2021-12-15', '2021-12-15', '2021-12-15', '2021-12-15', NULL, '2021-12-15', 'em1cli', 'em2cli', 'em3cli', 'email_interno_xml', 'enacli', 'enccli', 'enecli', 'enfcli', 'fa1cli', 'fa2cli', 'fa3cli', 'fa4cli', 'fc1cli', 'fc2cli', 'fc3cli', 'fc4cli', 'flbcli', 'flgca1', 'flgca2', 'flgca3', 'flginf', 'flgint', 'flgpro', 'flgpsq', 'flgtrg', 'f01cli', 'fo2cli', 'fo3cli', 'fo4cli', 'hralim', 'hreatu', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Cliente de Teste', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SP', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);

insert into estclp (codclp, nomclp) values (1, 'Classificacao Fiscal');

insert into estpro (codclp, codgru, codsub, codpro) values (1, '001', '0001', '00001');

insert into estpro (codclp, codgru, codsub, codpro) values (1, '001', '0001', '00002');

insert into estpro (codclp, codgru, codsub, codpro) values (1, '001', '0001', '00003');

insert into estqte (codemp, codclp, codgru, codsub, codpro, codtam, codcor, qtdqte, qtdrma) values (1, 1, '001', '0001', '00001', 1, 1, 15, 0);

insert into estqte (codemp, codclp, codgru, codsub, codpro, codtam, codcor, qtdqte, qtdrma) values (1, 1, '001', '0001', '00002', 1, 1, 15, 0);

insert into estqte (codemp, codclp, codgru, codsub, codpro, codtam, codcor, qtdqte, qtdrma) values (1, 1, '001', '0001', '00003', 1, 1, 15, 0);

insert into pedres (CODEMP, DTERES, CODCLI, NUMRES, TOTRES, TOTIPI, TOTSUB, TOTDESCINC, TOTGER, TOTLIQ, TOTBRT, CODVEN,
    DTFRES, ID_FRETE, cgccli, INSCLI, TENCLI, ENDCLI, NUMCLI, BAICLI, CIDCLI, UFECLI, CEPCLI, CODTRA, TOTICM, OBSRES)
VALUES(1, '2021-12-30', 1, 1, 100, 10, 21, 15, 131, 85, 110, 1, '2021-12-31', 1,'00.123.456/0001-89', 'INSCRICAO EST',
    'TENCLI', 'Endereço Cliente', 'Numero Cliente', 'Bairro Cliente', 'Cidade Cliente','UF Cliente', 'CEP Cliente', 1, 10,
    'Observação do Pedido');

insert into pedre2 (codemp, dteres, numres, seqre2, CODCLP, CODGRU, CODSUB, CODPRO, DESRE2, QTPRE2, VLQRE2, ICMRE2, TOTRE2,
    totren, BASICM, TOTICM, CODST1, CODST2, REDICM, BASIPI, IPIRE2, TOTIPI, CSTIPI, TOTDSR, TOTFRT, BASSUB, TOTSUB,
    ICMSUB, MRGSUB, REDSUB, BASPIS, ALIQPIS, TOTPIS, CSTPIS, BASCOF, ALIQCOF, TOTCOF, CSTCOF, CODTAM, CODCOR, TOTSEG, TOTOUTDESP,
    CLSIPI, CODCFO, CODUND, VLURE2, DSCRE2, TOTGE2)
 values(1, '2021-12-30', 1, 1, 1, '001', '0001', '00001','Descricao do Primeiro Produto',2,2.5,7,5,9,10,11,12,13,14,15,16,
    17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,1,1, 3, 4, '12345678', '5.101', 'PC', 999.99, 11, 12);

 insert into pedre2 (codemp, dteres, numres, seqre2, CODCLP, CODGRU, CODSUB, CODPRO, DESRE2, QTPRE2, VLQRE2, ICMRE2, TOTRE2,
     totren, BASICM, TOTICM, CODST1, CODST2, REDICM, BASIPI, IPIRE2, TOTIPI, CSTIPI, TOTDSR, TOTFRT, BASSUB, TOTSUB,
     ICMSUB, MRGSUB, REDSUB, BASPIS, ALIQPIS, TOTPIS, CSTPIS, BASCOF, ALIQCOF, TOTCOF, CSTCOF, CODTAM, CODCOR, TOTSEG, TOTOUTDESP,
     CLSIPI, CODCFO, CODUND, VLURE2, DSCRE2, TOTGE2)
  values(1, '2021-12-30', 1, 2, 1, '001', '0001', '00002','Descricao do Segundo Produto',7,1,7,7,9,10,11,12,13,14,15,16,
    17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,1,1,5,6, '12345689', '5.201', 'UN', 789.99, 13, 14);

  insert into pedre2 (codemp, dteres, numres, seqre2, CODCLP, CODGRU, CODSUB, CODPRO, DESRE2, QTPRE2, VLQRE2, ICMRE2, TOTRE2,
      totren, BASICM, TOTICM, CODST1, CODST2, REDICM, BASIPI, IPIRE2, TOTIPI, CSTIPI, TOTDSR, TOTFRT, BASSUB, TOTSUB,
      ICMSUB, MRGSUB, REDSUB, BASPIS, ALIQPIS, TOTPIS, CSTPIS, BASCOF, ALIQCOF, TOTCOF, CSTCOF, CODTAM, CODCOR, TOTSEG, TOTOUTDESP,
      CLSIPI, CODCFO, CODUND, VLURE2, DSCRE2, TOTGE2)
   values(1, '2021-12-30', 1, 3, 1, '001', '0001', '00003','Descricao do Terceiro Produto',3,3,7,9,9,10,11,12,13,14,15,16,
    17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,1,1,7,8, '12345677', '5.301', 'KG', 555.99, 14, 15);