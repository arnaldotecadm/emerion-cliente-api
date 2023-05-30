INSERT INTO public.geremp(codemp, baiemp, cepemp, cgcemp, cidemp, emaemp, endemp, fonemp, insemp, nomemp, numemp, prtemp, sigufe, tenemp, tipemp, webemp)
VALUES(1, 'SP', '00000-123', '00.000.000/0000-01', 'S�o Paulo', 'emailteste@teste.com', 'Endere�o da empresa', '(11)0000.0000', '012347', 'Nome da Empresa', '5', 'prtemp', 'SP', 'tenemp', 'Normal', 'sitedaempresa.com.br');

insert into finven(codven, nomven, apeven, PRFVEN, fonven, emaven) values(1, 'Vendedor', 'Vendedor', '11', '1234-5678', 'emailvendedor@email.com');

INSERT INTO public.tipfrt(id, descfrt) VALUES(1, 'FOB');

insert into TIP_INDIC_IE(id, tipo) values (1, 'Consumidor final');

insert into finregtrib (numregtrib, nomregtrib) values (1, 'Simples Nacional');

insert into finatd(codatd, nomatd, apeatd) values(1, 'Atendente', 'Atendente');

INSERT INTO public.fintra
(codtra, apetra, nomtra, CGCTRA, CIDTRA, SIGUFE, CEPTRA, PRTTRA, FONTRA, TENTRA, ENDTRA, NUMTRA, BAITRA)
VALUES(1, 'Nosso Carro', 'Nosso Carro', '01.123.123/0001-12', 'Cidade Transportadora', 'SP', 'CEP Transp', '11', '1234-1234', 'TEN TRA', 'Endere�o Transportadora', 'Num Transp', 'Bairro Transp');

INSERT INTO public.fincli
(codcli, apecli, baacli, baccli, baecli, bafcli, ceacli, ceccli, ceecli, cefcli, cest, cgccli, cgecli, ciacli, ciccli, ciecli, cifcli, clidev, clitab, cnae, coccli, codpal)
VALUES(1, 'Cliente de teste', 'baacli', 'baccli', 'baecli', 'bafcli', 'ceacli', 'ceccli', 'ceecli', 'cefcli', 'cest', '00.000.000/0001-01', 'cgecli', 'ciacli', 'ciccli', 'ciecli', 'S?o Paulo', NULL, 'clitab', '00000', 'coccli', NULL);

insert into fincde (codemp, dtecde, seqcde, codcli, usacde, valcde, sldcde, sitcde, obscde) values (1, '2021-12-01', 1, 1, 0, 15, 15, 'Utilizado', 'Entrada de Credito por Devolucao');

insert into fincde (codemp, dtecde, seqcde, codcli, usacde, valcde, sldcde, sitcde, obscde) values (1, '2021-12-02', 1, 1, 5, 0, 10, 'Utilizado', 'Saida de Credito por Devolucao');

insert into fincde (codemp, dtecde, seqcde, codcli, usacde, valcde, sldcde, sitcde, obscde) values (1, '2021-12-03', 1, 1, 0, 10, 25, 'Utilizado', 'Entrada de Credito por Devolucao');

insert into fincde (codemp, dtecde, seqcde, codcli, usacde, valcde, sldcde, sitcde, obscde) values (1, '2021-12-04', 1, 1, 20, 0, 5, 'Utilizado', 'Saida de Credito por Devolucao');

insert into fincde (codemp, dtecde, seqcde, codcli, usacde, valcde, sldcde, sitcde, obscde) values (1, '2021-12-05', 1, 1, 0, 45, 50, 'Disponivel', 'Entrada de Credito por Devolucao');

--insert into estclp (codclp, nomclp) values (1, 'Classificacao Fiscal');

insert into estpro (codclp, codgru, codsub, codpro) values (1, '001', '0001', '00001');

insert into estpro (codclp, codgru, codsub, codpro) values (1, '001', '0001', '00002');

insert into estpro (codclp, codgru, codsub, codpro) values (1, '001', '0001', '00003');

--insert into estqte (codemp, codclp, codgru, codsub, codpro, codtam, codcor, qtdqte, qtdrma) values (1, 1, '001', '0001', '00001', 1, 1, 15, 0);

--insert into estqte (codemp, codclp, codgru, codsub, codpro, codtam, codcor, qtdqte, qtdrma) values (1, 1, '001', '0001', '00002', 1, 1, 15, 0);

--insert into estqte (codemp, codclp, codgru, codsub, codpro, codtam, codcor, qtdqte, qtdrma) values (1, 1, '001', '0001', '00003', 1, 1, 15, 0);

insert into pedres (CODEMP, DTERES, CODCLI, NUMRES, TOTRES, TOTIPI, TOTSUB, TOTDESCINC, TOTGER, TOTLIQ, TOTBRT, CODVEN,
    DTFRES, ID_FRETE, cgccli, INSCLI, TENCLI, ENDCLI, NUMCLI, BAICLI, CIDCLI, UFECLI, CEPCLI, CODTRA, TOTICM, OBSRES)
VALUES(1, '2021-12-30', 1, 1, 100, 10, 21, 15, 131, 85, 110, 1, '2021-12-31', 1,'00.123.456/0001-89', 'INSCRICAO EST',
    'TENCLI', 'Endere�o Cliente', 'Numero Cliente', 'Bairro Cliente', 'Cidade Cliente','UF Cliente', 'CEP Cliente', 1, 10,
    'Observa��o do Pedido');

insert into pedre2 (codemp, dteres, numres, seqre2, CODCLP, CODGRU, CODSUB, CODPRO, DESRE2, QTPRE2, VLQRE2, ICMRE2, TOTRE2,
    totren, BASICM, TOTICM, CODST1, CODST2, REDICM, BASIPI, IPIRE2, TOTIPI, CSTIPI, TOTDSR, TOTFRT, BASSUB, TOTSUB,
    ICMSUB, MRGSUB, REDSUB, BASPIS, ALIQPIS, TOTPIS, CSTPIS, BASCOF, ALIQCOF, TOTCOF, CSTCOF)
 values(1, '2021-12-30', 1, 1, 1, '001', '0001', '00001','Descricao do Primeiro Produto',2,2.5,7,5,9,10,11,12,13,14,15,16,
    17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33);

 insert into pedre2 (codemp, dteres, numres, seqre2, CODCLP, CODGRU, CODSUB, CODPRO, DESRE2, QTPRE2, VLQRE2, ICMRE2, TOTRE2,
     totren, BASICM, TOTICM, CODST1, CODST2, REDICM, BASIPI, IPIRE2, TOTIPI, CSTIPI, TOTDSR, TOTFRT, BASSUB, TOTSUB,
     ICMSUB, MRGSUB, REDSUB, BASPIS, ALIQPIS, TOTPIS, CSTPIS, BASCOF, ALIQCOF, TOTCOF, CSTCOF)
  values(1, '2021-12-30', 1, 2, 1, '001', '0001', '00002','Descricao do Segundo Produto',7,1,7,7,9,10,11,12,13,14,15,16,
    17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33);

  insert into pedre2 (codemp, dteres, numres, seqre2, CODCLP, CODGRU, CODSUB, CODPRO, DESRE2, QTPRE2, VLQRE2, ICMRE2, TOTRE2,
      totren, BASICM, TOTICM, CODST1, CODST2, REDICM, BASIPI, IPIRE2, TOTIPI, CSTIPI, TOTDSR, TOTFRT, BASSUB, TOTSUB,
      ICMSUB, MRGSUB, REDSUB, BASPIS, ALIQPIS, TOTPIS, CSTPIS, BASCOF, ALIQCOF, TOTCOF, CSTCOF)
   values(1, '2021-12-30', 1, 3, 1, '001', '0001', '00003','Descricao do Terceiro Produto',3,3,7,9,9,10,11,12,13,14,15,16,
    17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33);