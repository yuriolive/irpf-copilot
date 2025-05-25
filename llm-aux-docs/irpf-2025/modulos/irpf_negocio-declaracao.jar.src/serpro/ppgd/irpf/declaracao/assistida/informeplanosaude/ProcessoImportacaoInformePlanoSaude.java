/*     */ package serpro.ppgd.irpf.declaracao.assistida.informeplanosaude;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.swing.JComponent;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import javax.xml.validation.Schema;
/*     */ import javax.xml.validation.SchemaFactory;
/*     */ import javax.xml.validation.Validator;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXParseException;
/*     */ import serpro.ppgd.gui.filechooser.FileChooser;
/*     */ import serpro.ppgd.gui.filechooser.FileChooserFactory;
/*     */ import serpro.ppgd.gui.filechooser.FileChooserResponse;
/*     */ import serpro.ppgd.gui.filechooser.FileFilter;
/*     */ import serpro.ppgd.irpf.dependentes.Dependente;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.declaracao.assistida.informeplanosaude.PainelPrevisualizadorDeclaracaoAssistidaInformePlanoSaude;
/*     */ import serpro.ppgd.irpf.gui.declaracao.assistida.informerendimentos.PainelPrevisualizadorDeclaracaoAssistidaInformeRendimentos;
/*     */ import serpro.ppgd.irpf.gui.dialogs.PainelMensagemDetalhada;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.txt.importacao.informerendimentospj.RepositorioXMLDadosInformes;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.util.Validador;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProcessoImportacaoInformePlanoSaude
/*     */ {
/*     */   private boolean importacao = true;
/*     */   
/*     */   public ProcessoImportacaoInformePlanoSaude() {}
/*     */   
/*     */   public ProcessoImportacaoInformePlanoSaude(boolean importacao) {
/*  48 */     this.importacao = importacao;
/*     */   }
/*     */   
/*     */   public void importar() {
/*  52 */     PainelDemonstrativoIf painel = ControladorGui.getPainelAtualmenteExibido();
/*  53 */     if (painel == null || (
/*  54 */       !painel.getClass().isAssignableFrom(PainelPrevisualizadorDeclaracaoAssistidaInformeRendimentos.class) && 
/*  55 */       !painel.getClass().isAssignableFrom(PainelPrevisualizadorDeclaracaoAssistidaInformePlanoSaude.class))) {
/*  56 */       importar(escolherArquivo());
/*     */     }
/*     */   }
/*     */   
/*     */   public void importar(String arquivoImportacao) {
/*  61 */     PainelDemonstrativoIf painel = ControladorGui.getPainelAtualmenteExibido();
/*  62 */     if (painel == null || (
/*  63 */       !painel.getClass().isAssignableFrom(PainelPrevisualizadorDeclaracaoAssistidaInformeRendimentos.class) && 
/*  64 */       !painel.getClass().isAssignableFrom(PainelPrevisualizadorDeclaracaoAssistidaInformePlanoSaude.class))) {
/*  65 */       if (arquivoImportacao != null) {
/*  66 */         List<Exception> erros = new ArrayList<>();
/*     */         try {
/*  68 */           erros = validarFormatoArquivoXML("informe_planos_saude.xsd", arquivoImportacao);
/*  69 */         } catch (Exception ex) {
/*  70 */           erros.add(ex);
/*     */         } 
/*     */         try {
/*  73 */           if (erros.size() == 0) {
/*  74 */             InformePagamentosPlanoSaude informePlanoSaude = new InformePagamentosPlanoSaude(ControladorGui.getDemonstrativoAberto());
/*  75 */             (new RepositorioXMLDadosInformes()).importaDados(new File(arquivoImportacao), informePlanoSaude);
/*     */             
/*  77 */             if (!informePlanoSaude.getHeader().getAnoExercicio().naoFormatado().equals(ConstantesGlobais.EXERCICIO)) {
/*  78 */               GuiUtil.mostrarErro("importar_informe_rendimentos_ano_invalido", new String[] { "Exercício" });
/*  79 */             } else if (!informePlanoSaude.getHeader().getAnoCalendario().naoFormatado().equals(ConstantesGlobais.ANO_BASE)) {
/*  80 */               GuiUtil.mostrarErro("importar_informe_rendimentos_ano_invalido", new String[] { "Ano-Calendário" });
/*     */             } else {
/*  82 */               if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().getCpf().naoFormatado()
/*  83 */                 .equals(informePlanoSaude.getHeader().getCpfContribuinte().naoFormatado())) {
/*  84 */                 informePlanoSaude.setTipoInforme(1);
/*  85 */               } else if (ControladorGui.getDemonstrativoAberto().getDependentes()
/*  86 */                 .isExisteCpf(informePlanoSaude.getHeader().getCpfContribuinte().naoFormatado())) {
/*  87 */                 informePlanoSaude.setTipoInforme(2);
/*     */               } 
/*     */               
/*  90 */               if (informePlanoSaude.getTipoInforme() == 1 || informePlanoSaude
/*  91 */                 .getTipoInforme() == 2) {
/*  92 */                 preVisualizar(informePlanoSaude);
/*     */               } else {
/*  94 */                 GuiUtil.mostrarAviso("importar_informe_cpf_incorreto", new String[] { "Plano de Saúde", informePlanoSaude
/*  95 */                       .getHeader().getCpfContribuinte().formatado() });
/*     */               } 
/*     */             } 
/*     */           } 
/*  99 */         } catch (ParserConfigurationException ex) {
/* 100 */           GuiUtil.mostrarErro("importar_informe_rendimentos_arquivo_erro");
/* 101 */         } catch (IOException ex) {
/* 102 */           GuiUtil.mostrarErro("importar_informe_rendimentos_arquivo_erro");
/* 103 */         } catch (SAXException ex) {
/* 104 */           erros.add(ex);
/*     */         } 
/*     */         
/* 107 */         if (erros.size() > 0) {
/* 108 */           StringBuilder mensagemErroCompleta = new StringBuilder();
/* 109 */           String nomeArquivo = (new File(arquivoImportacao)).getName();
/* 110 */           for (Exception exception : erros) {
/* 111 */             String complemento = "";
/* 112 */             if (exception instanceof SAXParseException) {
/* 113 */               SAXParseException e = (SAXParseException)exception;
/* 114 */               complemento = "(Linha " + e.getLineNumber() + ", Coluna " + e.getColumnNumber() + " do informe do Plano de Saúde) ";
/*     */             } 
/* 116 */             mensagemErroCompleta.append(complemento + complemento + "\n");
/*     */           } 
/*     */           
/* 119 */           PainelMensagemDetalhada painelMensagemDetalhada = new PainelMensagemDetalhada("Falha na Importação do Informe de Plano de Saúde", MensagemUtil.getMensagem("importar_informe_plano_saude_arquivo_invalido_sem_parametro"), mensagemErroCompleta.toString(), nomeArquivo);
/*     */           
/* 121 */           GuiUtil.exibeDialog((JComponent)painelMensagemDetalhada, true, "Importação de Informe de Plano de Saúde", false);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 125 */       GuiUtil.mostrarAviso("acao_cancelar_acao_atual");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void preVisualizar(InformePagamentosPlanoSaude informePagamentosPlanoSaude) {
/* 130 */     PainelDemonstrativoIf painelAtualmenteExibido = ControladorGui.getPainelAtualmenteExibido();
/* 131 */     PainelPrevisualizadorDeclaracaoAssistidaInformePlanoSaude painelPreVisualizacaoInformePlanoSaude = new PainelPrevisualizadorDeclaracaoAssistidaInformePlanoSaude(informePagamentosPlanoSaude, painelAtualmenteExibido);
/*     */     
/* 133 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)painelPreVisualizacaoInformePlanoSaude);
/*     */   }
/*     */   
/*     */   public List<Exception> validarFormatoArquivoXML(String xsdFullFilename, String xmlFullFilename) throws SAXException, IOException {
/* 137 */     Source xmlFile = null;
/* 138 */     Source xsdFile = null;
/*     */     
/* 140 */     final List<Exception> erros = new ArrayList<>();
/*     */     try {
/* 142 */       xmlFile = new StreamSource(new File(xmlFullFilename));
/* 143 */       Schema schema = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(ProcessoImportacaoInformePlanoSaude.class
/* 144 */           .getResource("/" + xsdFullFilename));
/* 145 */       Validator validator = schema.newValidator();
/* 146 */       validator.setErrorHandler(new ErrorHandler()
/*     */           {
/*     */             public void warning(SAXParseException exception) throws SAXException
/*     */             {
/* 150 */               erros.add(exception);
/*     */             }
/*     */ 
/*     */             
/*     */             public void error(SAXParseException exception) throws SAXException {
/* 155 */               erros.add(exception);
/*     */             }
/*     */ 
/*     */             
/*     */             public void fatalError(SAXParseException exception) throws SAXException {
/* 160 */               erros.add(exception);
/*     */             }
/*     */           });
/* 163 */       validator.validate(xmlFile);
/* 164 */     } catch (SAXException e) {
/* 165 */       System.out.println(xmlFile.getSystemId() + " is NOT valid");
/* 166 */       System.out.println("Reason: " + e.getLocalizedMessage());
/* 167 */       throw e;
/* 168 */     } catch (IOException e) {
/* 169 */       System.out.println(xmlFile.getSystemId() + " is NOT valid");
/* 170 */       System.out.println("Reason: " + e.getLocalizedMessage());
/* 171 */       throw e;
/*     */     } 
/* 173 */     return erros;
/*     */   }
/*     */ 
/*     */   
/*     */   public String escolherArquivo() {
/* 178 */     String importacaoOuImpressao = this.importacao ? "Importação" : "Impressão";
/* 179 */     String imprimeOuImporta = this.importacao ? "Importa" : "Imprime";
/*     */     
/* 181 */     FileChooser fc = FileChooserFactory.getInstance().createFileChooser();
/*     */     
/* 183 */     fc.setDialogTitle(importacaoOuImpressao + " de Informe de Plano de Saúde para o IRPF " + importacaoOuImpressao);
/* 184 */     fc.setApproveButtonText("OK");
/* 185 */     fc.setApproveButtonToolTipText(imprimeOuImporta + " dados de Informe de Plano de Saúde do IRPF " + imprimeOuImporta);
/* 186 */     fc.setAcceptAllFileFilterUsed(false);
/* 187 */     fc.setFileFilter(new FiltroArquivoInformeRendimentos());
/* 188 */     fc.setMultiSelectionEnabled(false);
/* 189 */     FileChooserResponse ret = fc.showOpenDialog((Component)ControladorGui.getJanelaPrincipal());
/*     */     
/* 191 */     if (ret == FileChooserResponse.APPROVE_OPTION) {
/* 192 */       return fc.getSelectedFile().getAbsolutePath();
/*     */     }
/* 194 */     return null;
/*     */   }
/*     */   
/*     */   class FiltroArquivoInformeRendimentos
/*     */     implements FileFilter
/*     */   {
/* 200 */     private final String padraoNomeArquivoInformePlanoSaude = "(?i)(.*)\\.xml";
/*     */ 
/*     */     
/*     */     public boolean accept(File f) {
/* 204 */       if (f.isDirectory()) {
/* 205 */         return true;
/*     */       }
/* 207 */       return Validador.validarString(f.getName(), "(?i)(.*)\\.xml");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String getDescription() {
/* 213 */       return "Arquivos de Informe de Plano de Saúde IRPF-" + ConstantesGlobais.EXERCICIO + "-" + ConstantesGlobais.EXERCICIO_ANTERIOR;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean verficarProprietarioInforme(String arquivoImportacao) {
/* 221 */     String cpfArquivo = (new File(arquivoImportacao)).getName().split("-")[0];
/*     */     
/* 223 */     if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfArquivo)) {
/* 224 */       return true;
/*     */     }
/* 226 */     Iterator<Dependente> itDependentes = ControladorGui.getDemonstrativoAberto().getDependentes().itens().iterator();
/* 227 */     while (itDependentes.hasNext()) {
/*     */       
/* 229 */       if (((Dependente)itDependentes.next()).getCpfDependente().naoFormatado().equals(cpfArquivo)) {
/* 230 */         return true;
/*     */       }
/*     */     } 
/* 233 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informeplanosaude\ProcessoImportacaoInformePlanoSaude.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */