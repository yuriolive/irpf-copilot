/*     */ package serpro.ppgd.irpf.doacoes;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ItemLimiteDeducaoIncentivo;
/*     */ import serpro.ppgd.irpf.calculos.CalculosDeducoesIncentivos;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ValidadorDeducoesDoacoes
/*     */   extends ValidadorDefault
/*     */ {
/*     */   private Valor imposto;
/*     */   private Valor totDoacoes;
/*     */   private Valor totIncentivo;
/*     */   private Valor limite;
/*     */   private Valor limiteIncentivo;
/*     */   private Valor limitePronasPronon;
/*     */   private Valor totAudioVisual;
/*     */   private Valor totCultura;
/*     */   private Valor totDesporto;
/*     */   private Valor totCrianca;
/*     */   private Valor totIdoso;
/*     */   private Valor totPronas;
/*     */   private Valor totPronon;
/*     */   private Valor totECA;
/*     */   private Valor totIDOSO;
/*     */   private Valor totReciclagem;
/*  44 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*     */   
/*  46 */   private Doacao doacao = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValidadorDeducoesDoacoes(byte severidade, DeclaracaoIRPF dec, Doacao doacao) {
/*  52 */     super(severidade);
/*  53 */     this.weakDec = new WeakReference<>(dec);
/*  54 */     this.doacao = doacao;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RetornoValidacao validarImplementado() {
/*  60 */     ItemLimiteDeducaoIncentivo lItemLimiteDeducaoIncentivo = null;
/*     */     
/*  62 */     setExibePopup(true);
/*     */     
/*  64 */     StringBuilder msg = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  69 */     boolean isIncentivo = (this.doacao.getCodigo().naoFormatado().equals("40") || this.doacao.getCodigo().naoFormatado().equals("41") || this.doacao.getCodigo().naoFormatado().equals("43") || this.doacao.getCodigo().naoFormatado().equals("42") || this.doacao.getCodigo().naoFormatado().equals("44") || this.doacao.getCodigo().naoFormatado().equals("47"));
/*     */ 
/*     */     
/*  72 */     boolean codigoValido = (isIncentivo || this.doacao.getCodigo().naoFormatado().equals("45") || this.doacao.getCodigo().naoFormatado().equals("46"));
/*     */     
/*  74 */     if (codigoValido) {
/*     */       
/*  76 */       this.totCrianca = CalculosDeducoesIncentivos.totalizarDoacoesGlosado(((DeclaracaoIRPF)this.weakDec.get()).getDoacoes(), new String[] { "40" }, true);
/*  77 */       this.totCultura = CalculosDeducoesIncentivos.totalizarDoacoesGlosado(((DeclaracaoIRPF)this.weakDec.get()).getDoacoes(), new String[] { "41" }, true);
/*  78 */       this.totDesporto = CalculosDeducoesIncentivos.totalizarDoacoesGlosado(((DeclaracaoIRPF)this.weakDec.get()).getDoacoes(), new String[] { "43" }, true);
/*  79 */       this.totAudioVisual = CalculosDeducoesIncentivos.totalizarDoacoesGlosado(((DeclaracaoIRPF)this.weakDec.get()).getDoacoes(), new String[] { "42" }, true);
/*  80 */       this.totIdoso = CalculosDeducoesIncentivos.totalizarDoacoesGlosado(((DeclaracaoIRPF)this.weakDec.get()).getDoacoes(), new String[] { "44" }, true);
/*  81 */       this.totPronas = CalculosDeducoesIncentivos.totalizarDoacoesGlosado(((DeclaracaoIRPF)this.weakDec.get()).getDoacoes(), new String[] { "45" }, true);
/*  82 */       this.totPronon = CalculosDeducoesIncentivos.totalizarDoacoesGlosado(((DeclaracaoIRPF)this.weakDec.get()).getDoacoes(), new String[] { "46" }, true);
/*  83 */       this.totReciclagem = CalculosDeducoesIncentivos.totalizarDoacoesGlosado(((DeclaracaoIRPF)this.weakDec.get()).getDoacoes(), new String[] { "47" }, true);
/*     */ 
/*     */       
/*  86 */       this.totECA = ((DeclaracaoIRPF)this.weakDec.get()).getColecaoEstatutoCriancaAdolescente().getTotalDeducaoIncentivoBruto();
/*  87 */       this.totIDOSO = ((DeclaracaoIRPF)this.weakDec.get()).getColecaoEstatutoIdoso().getTotalDeducaoIncentivoBruto();
/*  88 */       this.limite = ((DeclaracaoIRPF)this.weakDec.get()).getDoacoes().getTotalDeducaoIncentivo();
/*     */       
/*  90 */       this.imposto = new Valor();
/*  91 */       this.imposto.setConteudo(((DeclaracaoIRPF)this.weakDec.get()).getModeloCompleta().getImposto());
/*     */       
/*  93 */       Valor percentualIncentivo = new Valor();
/*  94 */       percentualIncentivo.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo.getValor());
/*  95 */       percentualIncentivo.append('/', "100,00");
/*     */       
/*  97 */       Valor percentualPrononPronas = new Valor();
/*  98 */       percentualPrononPronas.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncPronas.getValor());
/*  99 */       percentualPrononPronas.append('/', "100,00");
/*     */ 
/*     */       
/* 102 */       Valor percentualIdoso = new Valor();
/* 103 */       percentualIdoso.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncIdoso.getValor());
/* 104 */       percentualIdoso.append('/', "100,00");
/*     */ 
/*     */       
/* 107 */       Valor percentualDesporto = new Valor();
/* 108 */       percentualDesporto.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncDesporto.getValor());
/* 109 */       percentualDesporto.append('/', "100,00");
/*     */       
/* 111 */       Valor limiteIncentivoDesporto = new Valor();
/* 112 */       limiteIncentivoDesporto = this.imposto.operacao('*', percentualDesporto);
/*     */       
/* 114 */       Valor percentualComDesporto = new Valor();
/* 115 */       percentualComDesporto.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo.getValor());
/* 116 */       percentualComDesporto.append('+', TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncDesporto.getValor());
/* 117 */       percentualComDesporto.append('/', "100,00");
/*     */       
/* 119 */       Valor limiteIncentivoComDesporto = new Valor();
/* 120 */       limiteIncentivoComDesporto = this.imposto.operacao('*', percentualComDesporto);
/*     */       
/* 122 */       this.limiteIncentivo = this.imposto.operacao('*', percentualIncentivo);
/*     */ 
/*     */       
/* 125 */       Valor limiteDesporto = new Valor();
/* 126 */       limiteDesporto.setConteudo(this.imposto);
/* 127 */       limiteDesporto.append('*', percentualDesporto);
/*     */       
/* 129 */       if (this.totDesporto.comparacao("<", limiteIncentivoDesporto)) {
/* 130 */         limiteIncentivoDesporto.setConteudo(this.totDesporto);
/*     */       }
/*     */ 
/*     */       
/* 134 */       this.limitePronasPronon = this.imposto.operacao('*', percentualPrononPronas);
/*     */       
/* 136 */       this.totIncentivo = new Valor();
/* 137 */       Valor totIncentivoSemDesporto = new Valor();
/*     */       
/* 139 */       this.totIncentivo.append('+', this.totCrianca);
/* 140 */       this.totIncentivo.append('+', this.totCultura);
/* 141 */       this.totIncentivo.append('+', this.totAudioVisual);
/* 142 */       this.totIncentivo.append('+', this.totIdoso);
/* 143 */       this.totIncentivo.append('+', this.totReciclagem);
/* 144 */       totIncentivoSemDesporto.setConteudo(this.totIncentivo);
/* 145 */       this.totIncentivo.append('+', this.totDesporto);
/*     */       
/* 147 */       this.totDoacoes = new Valor();
/* 148 */       this.totDoacoes.append('+', this.totIncentivo);
/* 149 */       this.totDoacoes.append('+', this.totPronas);
/* 150 */       this.totDoacoes.append('+', this.totPronon);
/*     */       
/* 152 */       Valor incentivoExtra = new Valor();
/* 153 */       incentivoExtra.append('+', TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo.getValor());
/* 154 */       incentivoExtra.append('+', TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncDesporto.getValor());
/*     */ 
/*     */       
/* 157 */       if (totIncentivoSemDesporto.comparacao(">", this.limiteIncentivo) || this.totPronas.comparacao(">", this.limitePronasPronon) || this.totPronon.comparacao(">", this.limitePronasPronon) || (this.totIncentivo
/* 158 */         .comparacao(">", this.limiteIncentivo) && this.totDesporto.comparacao(">", limiteIncentivoDesporto))) {
/* 159 */         lItemLimiteDeducaoIncentivo = CalculosDeducoesIncentivos.calculaDeducaoIncentivo(this.weakDec.get(), this.imposto);
/* 160 */         lItemLimiteDeducaoIncentivo.getIncentivoCultura().setConteudo(this.totCultura.toString());
/* 161 */         lItemLimiteDeducaoIncentivo.getIncentivoAtividadeAudiovisual().setConteudo(this.totAudioVisual.toString());
/* 162 */         lItemLimiteDeducaoIncentivo.getIncentivoDesporto().setConteudo(this.totDesporto.toString());
/* 163 */         lItemLimiteDeducaoIncentivo.getIncentivoReciclagem().setConteudo(this.totReciclagem.toString());
/* 164 */         lItemLimiteDeducaoIncentivo.getEstatutoIdoso().setConteudo(this.totIdoso.toString());
/* 165 */         lItemLimiteDeducaoIncentivo.getPronas().setConteudo(this.totPronas.toString());
/* 166 */         lItemLimiteDeducaoIncentivo.getPronon().setConteudo(this.totPronon.toString());
/* 167 */         lItemLimiteDeducaoIncentivo.getSubtotalMenosCod39AnoCalendario().setConteudo(this.totDoacoes
/* 168 */             .operacao('-', lItemLimiteDeducaoIncentivo.getTotalDeducoesCod39()));
/* 169 */         lItemLimiteDeducaoIncentivo.getTotalDoacoes().setConteudo(this.totDoacoes);
/* 170 */         String anoCalendario = String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1);
/* 171 */         String parte1 = "";
/*     */         
/* 173 */         if (AplicacaoPropertiesUtil.isPronasPronon()) {
/* 174 */           parte1 = MensagemUtil.getMensagem("doacao_popup_excede_limite_deducao_texto_parte1_com_pronas_pronon", new String[] { TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo
/*     */                 
/* 176 */                 .getValor().formatado(), TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncIdoso
/* 177 */                 .getValor().formatado(), TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncPronas
/* 178 */                 .getValor().formatado(), TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncPronon
/* 179 */                 .getValor().formatado(), incentivoExtra
/* 180 */                 .formatado() });
/*     */         } else {
/*     */           
/* 183 */           parte1 = MensagemUtil.getMensagem("doacao_popup_excede_limite_deducao_texto_parte1_sem_pronas_pronon", new String[] { TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo
/*     */                 
/* 185 */                 .getValor().formatado(), TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncECA
/* 186 */                 .getValor().formatado(), incentivoExtra
/* 187 */                 .formatado() });
/*     */         } 
/*     */         
/* 190 */         String parte2 = MensagemUtil.getMensagem("doacao_popup_excede_limite_deducao_texto_parte2");
/*     */         
/* 192 */         Valor valorPassivelDeducaoIncentivo = new Valor();
/* 193 */         valorPassivelDeducaoIncentivo.setCasasDecimais(4);
/* 194 */         if (lItemLimiteDeducaoIncentivo.getLimite6porcento().comparacao(">", this.totIncentivo)) {
/* 195 */           valorPassivelDeducaoIncentivo.append('+', this.totIncentivo);
/*     */         } else {
/* 197 */           valorPassivelDeducaoIncentivo.append('+', lItemLimiteDeducaoIncentivo.getLimite6porcento());
/*     */         } 
/*     */         
/* 200 */         Valor valorPassivelDeducaoPronas = new Valor();
/* 201 */         valorPassivelDeducaoPronas.setCasasDecimais(4);
/* 202 */         if (lItemLimiteDeducaoIncentivo.getLimite1porcento().comparacao(">", lItemLimiteDeducaoIncentivo.getPronas())) {
/* 203 */           valorPassivelDeducaoPronas.append('+', lItemLimiteDeducaoIncentivo.getPronas());
/*     */         } else {
/* 205 */           valorPassivelDeducaoPronas.append('+', lItemLimiteDeducaoIncentivo.getLimite1porcento());
/*     */         } 
/*     */         
/* 208 */         Valor valorPassivelDeducaoPronon = new Valor();
/* 209 */         valorPassivelDeducaoPronon.setCasasDecimais(4);
/* 210 */         if (lItemLimiteDeducaoIncentivo.getLimite1porcento().comparacao(">", lItemLimiteDeducaoIncentivo.getPronon())) {
/* 211 */           valorPassivelDeducaoPronon.append('+', lItemLimiteDeducaoIncentivo.getPronon());
/*     */         } else {
/* 213 */           valorPassivelDeducaoPronon.append('+', lItemLimiteDeducaoIncentivo.getLimite1porcento());
/*     */         } 
/*     */         
/* 216 */         Valor limiteExercicio = new Valor();
/* 217 */         limiteExercicio.setCasasDecimais(4);
/* 218 */         limiteExercicio.append('+', valorPassivelDeducaoIncentivo);
/* 219 */         limiteExercicio.append('+', valorPassivelDeducaoPronas);
/* 220 */         limiteExercicio.append('+', valorPassivelDeducaoPronon);
/*     */         
/* 222 */         valorPassivelDeducaoIncentivo.converteQtdCasasDecimais(2);
/* 223 */         valorPassivelDeducaoPronas.converteQtdCasasDecimais(2);
/* 224 */         valorPassivelDeducaoPronon.converteQtdCasasDecimais(2);
/* 225 */         limiteExercicio.converteQtdCasasDecimais(2);
/* 226 */         lItemLimiteDeducaoIncentivo.converterCasasDecimais(2);
/*     */         
/* 228 */         String parte3_1 = MensagemUtil.getMensagem("doacao_popup_excede_limite_deducao_texto_parte3_1", new String[] { anoCalendario, lItemLimiteDeducaoIncentivo
/* 229 */               .getTotalDeducoesCod40().toString(), valorPassivelDeducaoIncentivo.toString(), lItemLimiteDeducaoIncentivo
/* 230 */               .getIncentivoCultura().toString(), lItemLimiteDeducaoIncentivo
/* 231 */               .getIncentivoAtividadeAudiovisual().toString(), lItemLimiteDeducaoIncentivo
/* 232 */               .getIncentivoDesporto().toString() });
/*     */         
/* 234 */         String parte3_2 = MensagemUtil.getMensagem("doacao_popup_excede_limite_deducao_texto_parte3_2", new String[] { ConstantesGlobais.ANO_BASE, lItemLimiteDeducaoIncentivo
/* 235 */               .getEstatutoIdoso().toString() });
/*     */         
/* 237 */         String parte3_2_1 = MensagemUtil.getMensagem("doacao_popup_excede_limite_deducao_texto_parte3_2_1", new String[] { lItemLimiteDeducaoIncentivo
/* 238 */               .getIncentivoReciclagem().toString() });
/*     */         
/* 240 */         String parte3_3 = MensagemUtil.getMensagem("doacao_popup_excede_limite_deducao_texto_parte3_3", new String[] { this.totIncentivo
/* 241 */               .toString(), valorPassivelDeducaoIncentivo.toString() });
/*     */         
/* 243 */         String parte3_4 = MensagemUtil.getMensagem("doacao_popup_excede_limite_deducao_texto_parte3_4", new String[] { lItemLimiteDeducaoIncentivo
/* 244 */               .getPronas().toString(), valorPassivelDeducaoPronas.toString(), lItemLimiteDeducaoIncentivo
/* 245 */               .getPronon().toString(), valorPassivelDeducaoPronon.toString() });
/*     */         
/* 247 */         String parte3_5 = MensagemUtil.getMensagem("doacao_popup_excede_limite_deducao_texto_parte3_5", new String[] { lItemLimiteDeducaoIncentivo
/* 248 */               .getTotalDoacoes().toString(), limiteExercicio.toString() });
/*     */         
/* 250 */         Valor limite1 = new Valor(totIncentivoSemDesporto.toString());
/*     */         
/* 252 */         if (limite1.comparacao(">", this.limiteIncentivo)) {
/* 253 */           limite1.setConteudo(this.limiteIncentivo);
/*     */         }
/*     */         
/* 256 */         Valor limite2 = new Valor(valorPassivelDeducaoIncentivo.toString());
/*     */         
/* 258 */         String obs_desporto = MensagemUtil.getMensagem("doacoes_obs_desporto", new String[] { valorPassivelDeducaoIncentivo
/* 259 */               .toString(), limite1.toString(), limite2.toString() });
/*     */         
/* 261 */         msg = new StringBuilder();
/* 262 */         msg.append("<html>");
/* 263 */         msg.append(parte1);
/* 264 */         msg.append("<br><br>");
/* 265 */         msg.append(parte2);
/* 266 */         msg.append("<br><br>");
/* 267 */         msg.append(parte3_1);
/* 268 */         msg.append(parte3_2);
/* 269 */         msg.append(parte3_2_1);
/*     */         
/* 271 */         if (AplicacaoPropertiesUtil.isPronasPronon()) {
/* 272 */           msg.append(parte3_3);
/* 273 */           msg.append(parte3_4);
/*     */         } 
/* 275 */         msg.append(parte3_5);
/* 276 */         msg.append("<br><br>");
/*     */         
/* 278 */         if (isIncentivo && this.totIncentivo.comparacao(">", this.limiteIncentivo) && (!this.totECA.isVazio() || !this.totIDOSO.isVazio())) {
/* 279 */           String parte4 = null;
/* 280 */           parte4 = MensagemUtil.getMensagem("doacao_popup_excede_limite_deducao_texto_parte4", new String[] { TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo
/*     */ 
/*     */                 
/* 283 */                 .getValor().formatado() });
/*     */           
/* 285 */           msg.append(parte4);
/*     */         } 
/*     */         
/* 288 */         msg.append("<BR>" + obs_desporto);
/*     */ 
/*     */ 
/*     */         
/* 292 */         msg.append("</html>");
/*     */       }
/* 294 */       else if (isIncentivo && this.totIncentivo.comparacao("<=", this.limiteIncentivo) && this.totIncentivo.operacao('+', this.totECA).comparacao(">", this.limiteIncentivo)) {
/*     */         
/* 296 */         msg = new StringBuilder();
/* 297 */         msg.append(MensagemUtil.getMensagem("doacao_popup_informativo_excedeu_eca", new String[] { ConstantesGlobais.ANO_BASE, TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncECA
/*     */ 
/*     */                 
/* 300 */                 .getValor().formatado(), TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo
/* 301 */                 .getValor().formatado(), percentualComDesporto
/* 302 */                 .operacao('*', "100").formatado() }));
/*     */       } 
/*     */     } 
/*     */     
/* 306 */     if (msg != null && !"".equals(msg.toString())) {
/* 307 */       RetornoValidacao retorno = new RetornoValidacao(msg.toString());
/* 308 */       retorno.setTituloMensagemValidacao("Limite da Dedução de Incentivo");
/* 309 */       return retorno;
/*     */     } 
/*     */     
/* 312 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\doacoes\ValidadorDeducoesDoacoes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */