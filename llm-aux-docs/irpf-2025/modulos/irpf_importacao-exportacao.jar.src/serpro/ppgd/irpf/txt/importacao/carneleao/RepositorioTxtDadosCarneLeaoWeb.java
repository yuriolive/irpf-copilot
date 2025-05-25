/*     */ package serpro.ppgd.irpf.txt.importacao.carneleao;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.espolio.Espolio;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.nuvem.carneleao.CarneLeao;
/*     */ import serpro.ppgd.irpf.nuvem.carneleao.Registro01;
/*     */ import serpro.ppgd.irpf.nuvem.carneleao.Registro02;
/*     */ import serpro.ppgd.irpf.nuvem.carneleao.Registro03;
/*     */ import serpro.ppgd.irpf.nuvem.carneleao.Registro04;
/*     */ import serpro.ppgd.irpf.rendpf.Conta;
/*     */ import serpro.ppgd.irpf.rendpf.ContasMes;
/*     */ import serpro.ppgd.irpf.rendpf.MesRendPF;
/*     */ import serpro.ppgd.irpf.rendpf.RendPF;
/*     */ import serpro.ppgd.irpf.rendpj.ColecaoRendPJDependente;
/*     */ import serpro.ppgd.irpf.rendpj.ColecaoRendPJTitular;
/*     */ import serpro.ppgd.irpf.rendpj.RendPJDependente;
/*     */ import serpro.ppgd.irpf.rendpj.RendPJTitular;
/*     */ import serpro.ppgd.irpf.saida.Saida;
/*     */ import serpro.ppgd.irpf.txt.importacao.RelatorioRepositorioTxtDadosAb;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class RepositorioTxtDadosCarneLeaoWeb
/*     */   extends RelatorioRepositorioTxtDadosAb {
/*     */   private CarneLeao carneLeao;
/*     */   
/*     */   public RepositorioTxtDadosCarneLeaoWeb(CarneLeao carneLeao) {
/*  34 */     this.carneLeao = carneLeao;
/*  35 */     setContabilizarSucessosErros(false);
/*     */   }
/*     */   
/*     */   public void importaIdentificacao(DeclaracaoIRPF dec) {
/*  39 */     Registro01 registro = this.carneLeao.getDados().getRegistro01();
/*  40 */     if (!dec.getIdentificadorDeclaracao().isEspolio() && registro
/*  41 */       .getCodOcupacaoPrincipal() != null && registro.getCodOcupacaoPrincipal().matches("225|226|229|230|231|232|255|241")) {
/*  42 */       if (dec.getContribuinte().getOcupacaoPrincipal().isVazio()) {
/*  43 */         dec.getContribuinte().getOcupacaoPrincipal().setConteudo(registro.getCodOcupacaoPrincipal());
/*  44 */         dec.getContribuinte().getRegistroProfissional().setConteudo(registro.getNrRegProfissional());
/*  45 */       } else if (dec.getContribuinte().getOcupacaoPrincipal().naoFormatado().equals(registro.getCodOcupacaoPrincipal())) {
/*  46 */         dec.getContribuinte().getRegistroProfissional().setConteudo(registro.getNrRegProfissional());
/*     */       }
/*  48 */       else if (GuiUtil.mostrarConfirma("confirmacao_substituicao_ocupacao_principal")) {
/*  49 */         dec.getContribuinte().getOcupacaoPrincipal().setConteudo(registro.getCodOcupacaoPrincipal());
/*  50 */         dec.getContribuinte().getRegistroProfissional().setConteudo(registro.getNrRegProfissional());
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void importaDados(RendPF rendPF, CPF cpfContribuinte, boolean titular) {
/*  57 */     Iterator<Registro02> it_dados = this.carneLeao.getDados().getRegistros02().iterator();
/*  58 */     while (it_dados.hasNext()) {
/*  59 */       Registro02 reg = it_dados.next();
/*  60 */       MesRendPF mes = getMesRendPFTitular(reg.getMes(), rendPF);
/*  61 */       boolean importar = true;
/*     */       
/*  63 */       if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().isEspolio()) {
/*  64 */         Espolio espolio = IRPFFacade.getInstancia().getEspolio();
/*  65 */         String mesLimite = espolio.obterDataLimiteParaCalculos().getMes();
/*     */         
/*  67 */         if (mes.getMes().asInteger() + 1 > Integer.valueOf(mesLimite).intValue()) {
/*  68 */           importar = false;
/*     */         }
/*  70 */       } else if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().isSaida()) {
/*  71 */         Saida saida = IRPFFacade.getInstancia().getSaida();
/*  72 */         String mesCondicaoNaoResidente = saida.getDtCondicaoNaoResidente().isVazio() ? "12" : saida.getDtCondicaoNaoResidente().getMes();
/*  73 */         String mesCondicaoResidente = saida.getDtCondicaoResidente().isVazio() ? "1" : saida.getDtCondicaoResidente().getMes();
/*  74 */         if (mes.getMes().asInteger() + 1 < Integer.valueOf(mesCondicaoResidente).intValue() || mes
/*  75 */           .getMes().asInteger() + 1 > Integer.valueOf(mesCondicaoNaoResidente).intValue()) {
/*  76 */           importar = false;
/*     */         }
/*     */       } 
/*     */       
/*  80 */       if (importar) {
/*     */ 
/*     */         
/*  83 */         mes.getAlugueis().setConteudo(Valor.valueOf(reg.getAlugueis()));
/*     */ 
/*     */         
/*  86 */         mes.getOutros().setConteudo(Valor.valueOf(reg.getOutros()));
/*     */ 
/*     */         
/*  89 */         mes.getExterior().setConteudo(Valor.valueOf(reg.getExterior()));
/*     */ 
/*     */         
/*  92 */         mes.getPrevidencia().setConteudo(Valor.valueOf(reg.getPrevidenciaOficial()));
/*     */ 
/*     */         
/*  95 */         mes.getNumDependentes().setConteudo(Valor.valueOf(reg.getDependentes().intValue()));
/*     */ 
/*     */         
/*  98 */         mes.getPensao().setConteudo(Valor.valueOf(reg.getPensaoAlimenticia()));
/*     */ 
/*     */         
/* 101 */         mes.getLivroCaixa().setConteudo(Valor.valueOf(reg.getLivroCaixa()));
/*     */ 
/*     */         
/* 104 */         mes.getCarneLeao().setConteudo(Valor.valueOf(reg.getImpostoAPagar()));
/*     */ 
/*     */         
/* 107 */         mes.getDarfPago().setConteudo(Valor.valueOf(reg.getImpostoPago()));
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     for (int i = 0; i < 12; i++) {
/* 112 */       rendPF.getContasAno().getArrayMeses()[i].getTotalRendTrabNaoAssPF().clear();
/* 113 */       rendPF.getContasAno().getArrayMeses()[i].itens().clear();
/*     */     } 
/*     */ 
/*     */     
/* 117 */     boolean[] meses = new boolean[12];
/* 118 */     for (int j = 0; j < 12; j++) {
/* 119 */       meses[j] = IRPFFacade.getInstancia().getDeclaracao().permiteInformarRendimento(j + 1);
/*     */     }
/*     */     
/* 122 */     Iterator<Registro03> it_lanca = this.carneLeao.getDados().getRegistros03().iterator();
/* 123 */     while (it_lanca.hasNext()) {
/* 124 */       Registro03 reg = it_lanca.next();
/* 125 */       String strMes = reg.getData();
/* 126 */       int mes = Integer.valueOf(strMes.substring(0, 2)).intValue();
/* 127 */       if (meses[mes - 1]) {
/* 128 */         ContasMes contasMes = rendPF.getContasAno().getArrayMeses()[mes - 1];
/* 129 */         Conta conta = new Conta();
/* 130 */         conta.getCpfContribuinte().setConteudo(cpfContribuinte);
/* 131 */         conta.getDataMesAno().setConteudo(strMes);
/* 132 */         conta.getCpfTitularPagamento().setConteudo(reg.getCpfTitular());
/* 133 */         conta.getCpfBeneficiarioServico().setConteudo((reg.getCpfBeneficiario() != null) ? reg.getCpfBeneficiario() : "");
/* 134 */         conta.getValor().setConteudo(Valor.valueOf(reg.getValor()));
/* 135 */         if (conta.getCpfTitularPagamento().naoFormatado().equals(conta.getCpfBeneficiarioServico().naoFormatado())) {
/* 136 */           conta.getIndTitularEhBeneficiario().setConteudo("1");
/*     */         } else {
/* 138 */           conta.getIndTitularEhBeneficiario().setConteudo("0");
/*     */         } 
/* 140 */         if (conta.getCpfBeneficiarioServico().naoFormatado().length() != 11) {
/* 141 */           conta.getIndBeneficiarioNaoPossuiCPF().setConteudo("1");
/*     */         } else {
/* 143 */           conta.getIndBeneficiarioNaoPossuiCPF().setConteudo("0");
/*     */         } 
/* 145 */         contasMes.itens().add(conta);
/*     */       } 
/*     */     } 
/*     */     
/* 149 */     Iterator<Registro04> it_pj = this.carneLeao.getDados().getRegistros04().iterator();
/* 150 */     if (it_pj.hasNext()) {
/* 151 */       ColecaoRendPJDependente colecaoRendPJDependente; ColecaoRendPJTitular colecaoRendPJ = null;
/* 152 */       if (titular) {
/* 153 */         colecaoRendPJ = IRPFFacade.getInstancia().getColecaoRendPJTitular();
/*     */       } else {
/* 155 */         colecaoRendPJDependente = IRPFFacade.getInstancia().getColecaoRendPJDependente();
/*     */       } 
/*     */       
/* 158 */       boolean exibeMensagemRendPJNaoImportado = false;
/* 159 */       while (it_pj.hasNext()) {
/* 160 */         boolean existeRendPJ = false;
/* 161 */         Registro04 reg04 = it_pj.next();
/* 162 */         for (RendPJTitular rend : colecaoRendPJDependente.itens()) {
/* 163 */           if (rend.getNIFontePagadora().naoFormatado().equals(reg04.getCnpj())) {
/* 164 */             existeRendPJ = true;
/* 165 */             exibeMensagemRendPJNaoImportado = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 169 */         if (!existeRendPJ) {
/* 170 */           if (titular) {
/* 171 */             RendPJTitular rendTitular = new RendPJTitular(IRPFFacade.getInstancia().getIdDeclaracaoAberto());
/* 172 */             rendTitular.getNIFontePagadora().setConteudo(reg04.getCnpj());
/* 173 */             rendTitular.getNomeFontePagadora().setConteudo(reg04.getNomeFontePagadora());
/* 174 */             rendTitular.getRendRecebidoPJ().setConteudo(Valor.valueOf(reg04.getValor()));
/* 175 */             rendTitular.getImpostoRetidoFonte().setConteudo(Valor.valueOf(reg04.getValorIrrf()));
/* 176 */             IRPFFacade.getInstancia().getColecaoRendPJTitular().add((ObjetoNegocio)rendTitular); continue;
/*     */           } 
/* 178 */           RendPJDependente rendDependente = new RendPJDependente(IRPFFacade.getInstancia().getDeclaracao());
/* 179 */           rendDependente.getCpfDependente().setConteudo(cpfContribuinte);
/* 180 */           rendDependente.getNIFontePagadora().setConteudo(reg04.getCnpj());
/* 181 */           rendDependente.getNomeFontePagadora().setConteudo(reg04.getNomeFontePagadora());
/* 182 */           rendDependente.getRendRecebidoPJ().setConteudo(Valor.valueOf(reg04.getValor()));
/* 183 */           rendDependente.getImpostoRetidoFonte().setConteudo(Valor.valueOf(reg04.getValorIrrf()));
/* 184 */           IRPFFacade.getInstancia().getColecaoRendPJDependente().add((ObjetoNegocio)rendDependente);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 189 */       if (exibeMensagemRendPJNaoImportado) {
/* 190 */         GuiUtil.mostrarAviso(null, MensagemUtil.getMensagemComQuebraDeLinha("mensagem_carne_leao_pj_existente"));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 195 */     Valor impostoPagoExterior = Valor.valueOf(this.carneLeao.getDados().getRegistro01().getValorImpostoPagoExterior());
/* 196 */     if (!impostoPagoExterior.isVazio()) {
/* 197 */       if (IRPFFacade.getInstancia().getDeclaracao().getImpostoPago().getImpostoPagoExterior().isVazio()) {
/* 198 */         IRPFFacade.getInstancia().getDeclaracao().getImpostoPago().getImpostoPagoExterior().setConteudo(impostoPagoExterior);
/*     */       } else {
/* 200 */         GuiUtil.mostrarAviso(null, MensagemUtil.getMensagemComQuebraDeLinha("mensagem_carne_leao_imposto_pago_exterior_ja_preenchido"));
/*     */       } 
/*     */     }
/*     */     
/* 204 */     boolean heTitular = cpfContribuinte.naoFormatado().equals(IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().naoFormatado());
/* 205 */     String ocupacaoPrincipal = ControladorGui.getDemonstrativoAberto().getContribuinte().getOcupacaoPrincipal().getConteudoAtual(0);
/* 206 */     if (heTitular && ocupacaoPrincipal.equals("117")) {
/* 207 */       GuiUtil.mostrarAviso("titular_cartorio");
/*     */     }
/*     */   }
/*     */   
/*     */   private MesRendPF getMesRendPFTitular(String mes, RendPF aRendPF) {
/* 212 */     mes = mes.trim().toUpperCase();
/* 213 */     RendPF rendPF = aRendPF;
/* 214 */     MesRendPF retorno = null;
/*     */     
/* 216 */     if (mes.equals("JANEIRO")) {
/* 217 */       retorno = rendPF.getJaneiro();
/* 218 */       retorno.getMes().setConteudo(0);
/* 219 */       return retorno;
/* 220 */     }  if (mes.equals("FEVEREIRO")) {
/* 221 */       retorno = rendPF.getFevereiro();
/* 222 */       retorno.getMes().setConteudo(1);
/* 223 */       return retorno;
/* 224 */     }  if (mes.equals("MARCO") || mes.equals("MARÇO")) {
/* 225 */       retorno = rendPF.getMarco();
/* 226 */       retorno.getMes().setConteudo(2);
/* 227 */       return retorno;
/* 228 */     }  if (mes.equals("ABRIL")) {
/* 229 */       retorno = rendPF.getAbril();
/* 230 */       retorno.getMes().setConteudo(3);
/* 231 */       return retorno;
/* 232 */     }  if (mes.equals("MAIO")) {
/* 233 */       retorno = rendPF.getMaio();
/* 234 */       retorno.getMes().setConteudo(4);
/* 235 */       return retorno;
/* 236 */     }  if (mes.equals("JUNHO")) {
/* 237 */       retorno = rendPF.getJunho();
/* 238 */       retorno.getMes().setConteudo(5);
/* 239 */       return retorno;
/* 240 */     }  if (mes.equals("JULHO")) {
/* 241 */       retorno = rendPF.getJulho();
/* 242 */       retorno.getMes().setConteudo(6);
/* 243 */       return retorno;
/* 244 */     }  if (mes.equals("AGOSTO")) {
/* 245 */       retorno = rendPF.getAgosto();
/* 246 */       retorno.getMes().setConteudo(7);
/* 247 */       return retorno;
/* 248 */     }  if (mes.equals("SETEMBRO")) {
/* 249 */       retorno = rendPF.getSetembro();
/* 250 */       retorno.getMes().setConteudo(8);
/* 251 */       return retorno;
/* 252 */     }  if (mes.equals("OUTUBRO")) {
/* 253 */       retorno = rendPF.getOutubro();
/* 254 */       retorno.getMes().setConteudo(9);
/* 255 */       return retorno;
/* 256 */     }  if (mes.equals("NOVEMBRO")) {
/* 257 */       retorno = rendPF.getNovembro();
/* 258 */       retorno.getMes().setConteudo(10);
/* 259 */       return retorno;
/* 260 */     }  if (mes.equals("DEZEMBRO")) {
/* 261 */       retorno = rendPF.getDezembro();
/* 262 */       retorno.getMes().setConteudo(11);
/* 263 */       return retorno;
/*     */     } 
/* 265 */     throw new IllegalArgumentException("Mês inválido!");
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\carneleao\RepositorioTxtDadosCarneLeaoWeb.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */