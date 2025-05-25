/*     */ package serpro.ppgd.irpf;
/*     */ 
/*     */ import serpro.ppgd.irpf.impressao.Relatorio;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PreenchedorFichasImpressao
/*     */ {
/*     */   public static void preencheRelatorioGeral(Relatorio novaImpressao, DeclaracaoIRPF dec) {
/*  15 */     String decEspolio = dec.getIdentificadorDeclaracao().isEspolio() ? "true" : "false";
/*  16 */     String decSaida = dec.getIdentificadorDeclaracao().isSaida() ? "true" : "false";
/*  17 */     String decAjuste = dec.getIdentificadorDeclaracao().isAjuste() ? "true" : "false";
/*     */     
/*  19 */     novaImpressao.addParametro("cpfContribuinte", dec.getIdentificadorDeclaracao().getCpf().formatado());
/*  20 */     novaImpressao.addParametro("nomeContribuinte", dec.getIdentificadorDeclaracao().getNome().formatado());
/*  21 */     novaImpressao.addParametro("anobase", ConstantesGlobais.ANO_BASE);
/*  22 */     novaImpressao.addParametro("exercicio", ConstantesGlobais.EXERCICIO);
/*  23 */     novaImpressao.addParametro("DEC_ESPOLIO", decEspolio);
/*  24 */     novaImpressao.addParametro("DEC_SAIDA", decSaida);
/*  25 */     novaImpressao.addParametro("DEC_AJUSTE", decAjuste);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheContribuinte(Relatorio novaImpressao, DeclaracaoIRPF dec, boolean primeiroRelatorio) {
/*  34 */     if (primeiroRelatorio == true) {
/*  35 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */ 
/*     */     
/*  39 */     novaImpressao.addParametro("tipoLogradouro", dec.getContribuinte().getTipoLogradouro().getConteudoAtual(1));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  44 */     if (dec.getContribuinte().getExterior().naoFormatado().equals("1")) {
/*  45 */       novaImpressao.addParametro("municipio", dec.getContribuinte().getCidade().formatado());
/*     */     } else {
/*  47 */       novaImpressao.addParametro("municipio", dec.getContribuinte().getMunicipio().getConteudoAtual(1));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  54 */     novaImpressao.addParametro("descNaturezaOcupacao", dec.getContribuinte().getNaturezaOcupacao().getConteudoAtual(1));
/*     */     
/*  56 */     novaImpressao.addParametro("descOcupacaoPrincipal", dec.getContribuinte().getOcupacaoPrincipal().getConteudoAtual(3));
/*     */     
/*  58 */     novaImpressao.addParametro("registroProfissional", dec.getContribuinte().getRegistroProfissional().naoFormatado());
/*  59 */     if (dec.getContribuinte().isOcupacaoComRegistroProfissionalObrigatorio(dec.getIdentificadorDeclaracao().getTipoDeclaracaoAES().naoFormatado())) {
/*  60 */       novaImpressao.addParametro("mostrarRegistroProfissional", "1");
/*     */     } else {
/*  62 */       novaImpressao.addParametro("mostrarRegistroProfissional", "0");
/*     */     } 
/*  64 */     boolean ehRetif = false;
/*     */     
/*  66 */     if (dec.getContribuinte().getDeclaracaoRetificadora().formatado().equals("1")) {
/*  67 */       ehRetif = true;
/*     */     }
/*     */     
/*  70 */     novaImpressao.addParametro("retificadora", Boolean.toString(ehRetif));
/*  71 */     if (!dec.getContribuinte().getEnderecoDiferente().isVazio()) {
/*  72 */       novaImpressao.addParametro("endDiferente", dec.getContribuinte().getEnderecoDiferente().formatado().equals("0") ? "Não" : "Sim");
/*     */     }
/*     */     
/*  75 */     if (dec.getContribuinte().getDeclaracaoRetificadora().formatado().equals("1")) {
/*  76 */       novaImpressao.addParametro("txtRecibo", "Nº do recibo da declaração anterior do exercício de " + ConstantesGlobais.EXERCICIO + ":");
/*  77 */       novaImpressao.addParametro("numeroRecibo", dec.getContribuinte().getNumReciboDecRetif().formatado());
/*     */     } else {
/*  79 */       novaImpressao.addParametro("txtRecibo", "Nº do recibo da última declaração entregue do exercício de " + ConstantesGlobais.EXERCICIO_ANTERIOR + ":");
/*  80 */       novaImpressao.addParametro("numeroRecibo", dec.getContribuinte().getNumeroReciboDecAnterior().formatado());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheRendPJTitular(Relatorio novaImpressao, DeclaracaoIRPF dec, boolean primeiroRelatorio) {
/*  89 */     if (primeiroRelatorio == true) {
/*  90 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheRendPJComExigibilidadeTitular(Relatorio novaImpressao, DeclaracaoIRPF dec, boolean primeiroRelatorio) {
/*  99 */     if (primeiroRelatorio == true) {
/* 100 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheRendAcumuladamenteTitular(Relatorio novaImpressao, DeclaracaoIRPF dec, boolean primeiroRelatorio) {
/* 109 */     if (primeiroRelatorio == true) {
/* 110 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheRendaVariavel(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 119 */     if (primeiroRelatorio == true) {
/* 120 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheARBrasil(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 129 */     if (primeiroRelatorio == true) {
/* 130 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/* 132 */     novaImpressao.addParametro("ARLocal", "BRASIL");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheARApuracaoBrasil(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 140 */     if (primeiroRelatorio == true) {
/* 141 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/* 143 */     novaImpressao.addParametro("ARLocal", "BRASIL");
/* 144 */     novaImpressao.addParametro("ANO_RECEITA_RECEBIDA", ConstantesGlobais.ANO_BASE);
/* 145 */     novaImpressao.addParametro("ANO_ADIANTAMENTO", ConstantesGlobais.ANO_BASE_ANTERIOR);
/* 146 */     novaImpressao.addParametro("ANO_PRODUTOS_ENTREGUES", ConstantesGlobais.ANO_BASE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheARExterior(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 155 */     if (primeiroRelatorio == true) {
/* 156 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/* 158 */     novaImpressao.addParametro("ARLocal", "EXTERIOR");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheARApuracaoExterior(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 167 */     if (primeiroRelatorio == true) {
/* 168 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/* 170 */     novaImpressao.addParametro("ARLocal", "EXTERIOR");
/* 171 */     novaImpressao.addParametro("ANO_RECEITA_RECEBIDA", ConstantesGlobais.ANO_BASE);
/* 172 */     novaImpressao.addParametro("ANO_ADIANTAMENTO", ConstantesGlobais.ANO_BASE_ANTERIOR);
/* 173 */     novaImpressao.addParametro("ANO_PRODUTOS_ENTREGUES", ConstantesGlobais.ANO_BASE);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void preencheGCAP(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 178 */     if (primeiroRelatorio == true) {
/* 179 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheResumo(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 189 */     if (primeiroRelatorio == true) {
/* 190 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/* 192 */     novaImpressao.addParametro("ANO_BASE_ANTERIOR", ConstantesGlobais.ANO_BASE_ANTERIOR);
/* 193 */     novaImpressao.addParametro("ANO_BASE", ConstantesGlobais.ANO_BASE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheDividasOnus(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 202 */     if (primeiroRelatorio == true) {
/* 203 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheInventariante(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 213 */     if (primeiroRelatorio == true) {
/* 214 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheSaida(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 224 */     if (primeiroRelatorio == true) {
/* 225 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheDoacoesCampanha(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 235 */     if (primeiroRelatorio == true) {
/* 236 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheBensDireitos(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 246 */     if (primeiroRelatorio == true) {
/* 247 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preenchePagamentos(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 257 */     if (primeiroRelatorio == true) {
/* 258 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/* 260 */     novaImpressao.addParametro("PENSAO_ALIMENTICIA_BR", "30");
/* 261 */     novaImpressao.addParametro("PENSAO_ALIMENTICIA_EX", "31");
/* 262 */     novaImpressao.addParametro("PENSAO_DIVORCIO_BR", "33");
/* 263 */     novaImpressao.addParametro("PENSAO_DIVORCIO_EX", "34");
/*     */     
/* 265 */     novaImpressao.addParametro("FUNPRESP", "37");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheDoacoes(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 274 */     if (primeiroRelatorio == true) {
/* 275 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheDependentes(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 285 */     if (primeiroRelatorio == true) {
/* 286 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/* 288 */     novaImpressao.addParametro("totDeducoesDependentes", dec.getDependentes().getTotalDeducaoDependentes().formatado());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheAlimentandos(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 297 */     if (primeiroRelatorio == true) {
/* 298 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheHerdeiros(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 308 */     if (primeiroRelatorio == true) {
/* 309 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheImpostoPago(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 319 */     if (primeiroRelatorio == true) {
/* 320 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheRendTribExcl(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 330 */     if (primeiroRelatorio == true) {
/* 331 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheRendIsentos(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 341 */     if (primeiroRelatorio == true) {
/* 342 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheRendPFDependente(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 351 */     if (primeiroRelatorio == true) {
/* 352 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheRendPFTitular(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 362 */     if (primeiroRelatorio == true) {
/* 363 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheRendPJDependente(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 373 */     if (primeiroRelatorio == true) {
/* 374 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheRendPJComExigibilidadeDependente(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 384 */     if (primeiroRelatorio == true) {
/* 385 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preencheRendAcumuladamenteDependente(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 395 */     if (primeiroRelatorio == true) {
/* 396 */       preencheRelatorioGeral(novaImpressao, dec);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void preencheGCME(DeclaracaoIRPF dec, Relatorio novaImpressao, boolean primeiroRelatorio) {
/* 401 */     if (primeiroRelatorio == true)
/* 402 */       preencheRelatorioGeral(novaImpressao, dec); 
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\PreenchedorFichasImpressao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */