/*     */ package serpro.ppgd.irpf.alimentandos;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.swing.JOptionPane;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.alimentandos.PainelAlimentandosLista;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Pendencia;
/*     */ import serpro.ppgd.negocio.util.UtilitariosString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Alimentandos
/*     */   extends Colecao<Alimentando>
/*     */   implements ObjetoFicha
/*     */ {
/*     */   public static final String FICHA = "Alimentandos";
/*     */   public static final String CONFIRMADO_SIM = "S";
/*     */   public static final String CONFIRMADO_NAO = "N";
/*  32 */   private WeakReference<DeclaracaoIRPF> declaracaoRef = null;
/*     */   
/*  34 */   private Alfa confirmacao = new Alfa((ObjetoNegocio)this, "Os alimentandos atendem aos requisitos?", 1);
/*     */   
/*     */   public Alimentandos(DeclaracaoIRPF dec) {
/*  37 */     setFicha("Alimentandos");
/*     */     
/*  39 */     this.declaracaoRef = new WeakReference<>(dec);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void objetoInserido(Alimentando alimentando) {
/*  45 */     setFicha(getFicha());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alimentando instanciaNovoObjeto() {
/*  54 */     return new Alimentando(this.declaracaoRef.get());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomeAlimentandoByChave(String chave) {
/*  65 */     Iterator<Alimentando> it = itens().iterator();
/*  66 */     while (it.hasNext()) {
/*  67 */       Alimentando a = it.next();
/*  68 */       if (a.getChave().equals(chave)) {
/*  69 */         return a.getNome().formatado();
/*     */       }
/*     */     } 
/*     */     
/*  73 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getChaveAlimentandoByNome(String nome) {
/*  83 */     Iterator<Alimentando> it = itens().iterator();
/*  84 */     while (it.hasNext()) {
/*  85 */       Alimentando a = it.next();
/*  86 */       if (a.getNome().naoFormatado().equals(nome)) {
/*  87 */         return a.getChave();
/*     */       }
/*     */     } 
/*     */     
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Alimentando getAlimentandoByNome(String nome) {
/*  96 */     if (nome != null) {
/*  97 */       nome = nome.trim().toUpperCase();
/*  98 */       Iterator<Alimentando> it = itens().iterator();
/*  99 */       while (it.hasNext()) {
/* 100 */         Alimentando a = it.next();
/* 101 */         if (a.getNome().naoFormatado().toUpperCase().equals(nome)) {
/* 102 */           return a;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 107 */     return null;
/*     */   }
/*     */   
/*     */   public Alimentando getAlimentandoByCpf(String cpf) {
/* 111 */     Iterator<Alimentando> it = itens().iterator();
/* 112 */     while (it.hasNext()) {
/* 113 */       Alimentando a = it.next();
/* 114 */       if (a.getCpf().naoFormatado().equals(cpf)) {
/* 115 */         return a;
/*     */       }
/*     */     } 
/* 118 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isExisteNome(String nome) {
/* 122 */     Iterator<Alimentando> it = itens().iterator();
/* 123 */     while (it.hasNext()) {
/* 124 */       Alimentando a = it.next();
/* 125 */       if (a.getNome().naoFormatado().equals(nome)) {
/* 126 */         return true;
/*     */       }
/*     */     } 
/* 129 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isExisteCPF(String cpf) {
/* 133 */     Iterator<Alimentando> it = itens().iterator();
/* 134 */     while (it.hasNext()) {
/* 135 */       Alimentando a = it.next();
/* 136 */       if (a.getCpf().naoFormatado().equals(cpf)) {
/* 137 */         return true;
/*     */       }
/*     */     } 
/* 140 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isNomeDuplicado(String nome) {
/* 144 */     Iterator<Alimentando> it = itens().iterator();
/* 145 */     int count = 0;
/* 146 */     while (it.hasNext()) {
/* 147 */       Alimentando a = it.next();
/* 148 */       if (!a.getNome().naoFormatado().isEmpty() && a.getNome().naoFormatado().equals(nome)) {
/* 149 */         if (count > 0) {
/* 150 */           return true;
/*     */         }
/* 152 */         count++;
/*     */       } 
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */   
/*     */   public boolean existeAlimentandoComNome() {
/* 159 */     Iterator<Alimentando> it = itens().iterator();
/* 160 */     while (it.hasNext()) {
/* 161 */       Alimentando a = it.next();
/* 162 */       if (!a.getNome().isVazio()) {
/* 163 */         return true;
/*     */       }
/*     */     } 
/* 166 */     return false;
/*     */   }
/*     */   
/*     */   public boolean confirmaExclusaoRelacionadasAlimentando(String nomeAlimentando) {
/* 170 */     return confirmaExclusaoRelacionadasAlimentando(nomeAlimentando, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean confirmaExclusaoRelacionadasAlimentando(String nomeAlimentando, String cpfAlimentando) {
/* 175 */     boolean achouPagamentos = false;
/* 176 */     boolean achouRendAcmTitular = false;
/* 177 */     boolean achouRendAcmDependente = false;
/* 178 */     int qtdFichasUsam = 0;
/*     */     
/* 180 */     IRPFFacade facade = IRPFFacade.getInstancia();
/*     */     
/* 182 */     if (facade.getPagamentos().existePagamentosComAlimentando(nomeAlimentando)) {
/* 183 */       achouPagamentos = true;
/* 184 */       qtdFichasUsam++;
/*     */     } 
/*     */     
/* 187 */     if (facade.getColecaoRendAcmTitular().existeRendAcmComAlimentando(nomeAlimentando)) {
/* 188 */       achouRendAcmTitular = true;
/* 189 */       qtdFichasUsam++;
/*     */     } 
/*     */     
/* 192 */     if (facade.getColecaoRendAcmDependente().existeRendAcmComAlimentando(nomeAlimentando)) {
/* 193 */       achouRendAcmDependente = true;
/* 194 */       qtdFichasUsam++;
/*     */     } 
/*     */ 
/*     */     
/* 198 */     if (cpfAlimentando != null && !"".equals(cpfAlimentando)) {
/* 199 */       msgExcluirCascata = "Este alimentando (CPF: " + UtilitariosString.formataCPF(cpfAlimentando) + ") é utilizado na(s) ficha(s): \n";
/*     */     } else {
/* 201 */       msgExcluirCascata = "Este alimentando (" + nomeAlimentando + ") é utilizado na(s) ficha(s): \n";
/*     */     } 
/*     */     
/* 204 */     if (achouPagamentos) {
/* 205 */       msgExcluirCascata = msgExcluirCascata + "- Pagamentos \n";
/*     */     }
/*     */     
/* 208 */     if (achouRendAcmTitular) {
/* 209 */       msgExcluirCascata = msgExcluirCascata + "- Rendimentos Recebidos Acumuladamente pelo Titular \n";
/*     */     }
/*     */     
/* 212 */     if (achouRendAcmDependente) {
/* 213 */       msgExcluirCascata = msgExcluirCascata + "- Rendimentos Recebidos Acumuladamente pelos Dependentes \n";
/*     */     }
/*     */     
/* 216 */     boolean achouAlgumEmCascata = (achouPagamentos || achouRendAcmTitular || achouRendAcmDependente);
/*     */ 
/*     */ 
/*     */     
/* 220 */     String msgExcluirCascata = msgExcluirCascata + "\nOs dados vinculados ao alimentando serão excluídos.\nConfirma a alteração do alimentando e exclusão desses dados?";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     if (!achouAlgumEmCascata)
/* 226 */       return true; 
/* 227 */     if (JOptionPane.showConfirmDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), msgExcluirCascata, "Confirmação", 0) == 0) {
/*     */ 
/*     */       
/* 230 */       if (achouPagamentos) {
/* 231 */         facade.getPagamentos().excluirPagamentosComAlimentando(nomeAlimentando);
/*     */       }
/*     */       
/* 234 */       if (achouRendAcmTitular) {
/* 235 */         facade.getColecaoRendAcmTitular().excluirRendAcmComAlimentando(nomeAlimentando);
/*     */       }
/*     */       
/* 238 */       if (achouRendAcmDependente) {
/* 239 */         facade.getColecaoRendAcmDependente().excluirRendAcmComAlimentando(nomeAlimentando);
/*     */       }
/*     */       
/* 242 */       return true;
/*     */     } 
/* 244 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 249 */     List<Informacao> campos = super.recuperarListaCamposPendencia();
/*     */     
/* 251 */     campos.add(this.confirmacao);
/*     */     
/* 253 */     return campos;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Pendencia> verificarPendencias(int numeroItem) {
/* 259 */     List<Pendencia> retorno = super.verificarPendencias(numeroItem);
/*     */     
/* 261 */     if (!itens().isEmpty() && !getConfirmacao().naoFormatado().equals("S")) {
/*     */ 
/*     */       
/* 264 */       Pendencia p = new Pendencia((byte)3, (Informacao)getConfirmacao(), getConfirmacao().getNomeCampo(), MensagemUtil.getMensagem("alimentandos_nao_confirmado"), numeroItem);
/*     */       
/* 266 */       p.setClassePainel(getClasseFicha());
/*     */       
/* 268 */       retorno.add(p);
/*     */     } 
/*     */ 
/*     */     
/* 272 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 277 */     return PainelAlimentandosLista.class.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 283 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 288 */     return "Alimentandos";
/*     */   }
/*     */   
/*     */   public Alfa getConfirmacao() {
/* 292 */     return this.confirmacao;
/*     */   }
/*     */   
/*     */   public boolean possuiDependenteComCPF(String cpfDependente) {
/* 296 */     boolean achou = false;
/* 297 */     for (Alimentando alimentando : itens()) {
/* 298 */       if (alimentando.getCpfResponsavel().naoFormatado().equals(cpfDependente)) {
/* 299 */         achou = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 303 */     return achou;
/*     */   }
/*     */   
/*     */   public void excluirAlimentandosComDependente(String cpfDependente) {
/* 307 */     ArrayList<Alimentando> alimentandos = new ArrayList();
/* 308 */     for (Alimentando alimentando : itens()) {
/* 309 */       if (alimentando.getCpfResponsavel().naoFormatado().equals(cpfDependente)) {
/* 310 */         alimentandos.add(alimentando);
/*     */       }
/*     */     } 
/*     */     
/* 314 */     Iterator<Alimentando> itAlimentandos = alimentandos.iterator();
/*     */     
/* 316 */     while (itAlimentandos.hasNext())
/* 317 */       alimentandos.remove(itAlimentandos.next()); 
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\alimentandos\Alimentandos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */