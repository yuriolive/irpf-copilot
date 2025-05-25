/*     */ package serpro.ppgd.irpf.declaracao.assistida.informerendimentos;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProcessoImportacaoInformeRendimentos
/*     */ {
/*     */   private boolean importacao = true;
/*     */   
/*     */   public ProcessoImportacaoInformeRendimentos() {}
/*     */   
/*     */   public ProcessoImportacaoInformeRendimentos(boolean importacao) {
/*  54 */     this.importacao = importacao;
/*     */   }
/*     */ 
/*     */   
/*     */   public String escolherArquivo() {
/*  59 */     String importacaoOuImpressao = this.importacao ? "Importação" : "Impressão";
/*  60 */     String imprimeOuImporta = this.importacao ? "Importa" : "Imprime";
/*     */     
/*  62 */     FileChooser fc = FileChooserFactory.getInstance().createFileChooser();
/*     */     
/*  64 */     fc.setDialogTitle(importacaoOuImpressao + " de Informe de Rendimentos para o IRPF " + importacaoOuImpressao);
/*  65 */     fc.setApproveButtonText("OK");
/*  66 */     fc.setApproveButtonToolTipText(imprimeOuImporta + " dados de Informe de Rendimentos do IRPF " + imprimeOuImporta);
/*  67 */     fc.setAcceptAllFileFilterUsed(false);
/*  68 */     fc.setFileFilter(new FiltroArquivoInformeRendimentos());
/*  69 */     fc.setMultiSelectionEnabled(false);
/*     */     
/*  71 */     FileChooserResponse ret = fc.showOpenDialog((Component)ControladorGui.getJanelaPrincipal());
/*     */     
/*  73 */     if (ret == FileChooserResponse.APPROVE_OPTION) {
/*  74 */       return fc.getSelectedFile().getAbsolutePath();
/*     */     }
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Exception> validarFormatoArquivoXML(String xsdFullFilename, String xmlFullFilename) throws SAXException, IOException {
/*  81 */     Source xmlFile = null;
/*     */     
/*  83 */     final List<Exception> erros = new ArrayList<>();
/*     */     try {
/*  85 */       xmlFile = new StreamSource(new File(xmlFullFilename));
/*  86 */       Schema schema = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(ProcessoImportacaoInformeRendimentos.class
/*  87 */           .getResource("/" + xsdFullFilename));
/*  88 */       Validator validator = schema.newValidator();
/*  89 */       validator.setErrorHandler(new ErrorHandler()
/*     */           {
/*     */             public void warning(SAXParseException exception) throws SAXException {
/*  92 */               erros.add(exception);
/*     */             }
/*     */ 
/*     */             
/*     */             public void error(SAXParseException exception) throws SAXException {
/*  97 */               erros.add(exception);
/*     */             }
/*     */ 
/*     */             
/*     */             public void fatalError(SAXParseException exception) throws SAXException {
/* 102 */               erros.add(exception);
/*     */             }
/*     */           });
/* 105 */       validator.validate(xmlFile);
/* 106 */     } catch (SAXException e) {
/* 107 */       System.out.println(xmlFile.getSystemId() + " is NOT valid");
/* 108 */       System.out.println("Reason: " + e.getLocalizedMessage());
/* 109 */       throw e;
/* 110 */     } catch (IOException e) {
/* 111 */       System.out.println(xmlFile.getSystemId() + " is NOT valid");
/* 112 */       System.out.println("Reason: " + e.getLocalizedMessage());
/* 113 */       throw e;
/*     */     } 
/* 115 */     return erros;
/*     */   }
/*     */   
/*     */   private void preVisualizar(InformeRendimentosPJ informeRendimentos) {
/* 119 */     PainelDemonstrativoIf painelAtualmenteExibido = ControladorGui.getPainelAtualmenteExibido();
/* 120 */     PainelPrevisualizadorDeclaracaoAssistidaInformeRendimentos painelPreVisualizacaoInformeRendimentos = new PainelPrevisualizadorDeclaracaoAssistidaInformeRendimentos(informeRendimentos, painelAtualmenteExibido);
/*     */     
/* 122 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)painelPreVisualizacaoInformeRendimentos);
/*     */   }
/*     */   
/*     */   public void importar() {
/* 126 */     PainelDemonstrativoIf painel = ControladorGui.getPainelAtualmenteExibido();
/* 127 */     if (painel == null || (
/* 128 */       !painel.getClass().isAssignableFrom(PainelPrevisualizadorDeclaracaoAssistidaInformeRendimentos.class) && 
/* 129 */       !painel.getClass().isAssignableFrom(PainelPrevisualizadorDeclaracaoAssistidaInformePlanoSaude.class))) {
/* 130 */       importar(escolherArquivo());
/*     */     }
/*     */   }
/*     */   
/*     */   public void importar(String arquivoImportacao) {
/* 135 */     PainelDemonstrativoIf painel = ControladorGui.getPainelAtualmenteExibido();
/* 136 */     if (painel == null || (
/* 137 */       !painel.getClass().isAssignableFrom(PainelPrevisualizadorDeclaracaoAssistidaInformeRendimentos.class) && 
/* 138 */       !painel.getClass().isAssignableFrom(PainelPrevisualizadorDeclaracaoAssistidaInformePlanoSaude.class))) {
/* 139 */       if (arquivoImportacao != null) {
/* 140 */         List<Exception> erros = new ArrayList<>();
/*     */         try {
/* 142 */           erros = validarFormatoArquivoXML("informe_fontes_pagadoras.xsd", arquivoImportacao);
/* 143 */         } catch (Exception ex) {
/* 144 */           erros.add(ex);
/*     */         } 
/*     */         try {
/* 147 */           if (erros.size() == 0) {
/* 148 */             InformeRendimentosPJ informeRendimentos = new InformeRendimentosPJ();
/* 149 */             (new RepositorioXMLDadosInformes()).importaDados(new File(arquivoImportacao), informeRendimentos);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 154 */             informeRendimentos.ajustar13PensoesAlimenticias();
/*     */             
/* 156 */             if (!informeRendimentos.getHeader().getAnoExercicio().naoFormatado().equals(ConstantesGlobais.EXERCICIO)) {
/* 157 */               GuiUtil.mostrarAviso("importar_informe_rendimentos_ano_invalido", new String[] { "Exercício" });
/* 158 */             } else if (!informeRendimentos.getHeader().getAnoCalendario().naoFormatado().equals(ConstantesGlobais.ANO_BASE)) {
/* 159 */               GuiUtil.mostrarAviso("importar_informe_rendimentos_ano_invalido", new String[] { "Ano-Calendário" });
/*     */             } else {
/* 161 */               if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().getCpf().naoFormatado()
/* 162 */                 .equals(informeRendimentos.getHeader().getCpfeBeneficiario().naoFormatado())) {
/* 163 */                 informeRendimentos.setTipoInforme(1);
/* 164 */               } else if (ControladorGui.getDemonstrativoAberto().getDependentes()
/* 165 */                 .isExisteCpf(informeRendimentos.getHeader().getCpfeBeneficiario().naoFormatado())) {
/* 166 */                 informeRendimentos.setTipoInforme(2);
/*     */               } 
/* 168 */               if (informeRendimentos.getTipoInforme() == 1 || informeRendimentos
/* 169 */                 .getTipoInforme() == 2) {
/* 170 */                 preVisualizar(informeRendimentos);
/*     */               } else {
/* 172 */                 GuiUtil.mostrarAviso("importar_informe_cpf_incorreto", new String[] { "Rendimentos", informeRendimentos
/* 173 */                       .getHeader().getCpfeBeneficiario().formatado() });
/*     */               } 
/*     */             } 
/*     */           } 
/* 177 */         } catch (SAXException ex) {
/* 178 */           erros.add(ex);
/*     */         }
/* 180 */         catch (IOException ex) {
/* 181 */           GuiUtil.mostrarErro("importar_informe_rendimentos_arquivo_erro");
/* 182 */         } catch (ParserConfigurationException ex) {
/* 183 */           GuiUtil.mostrarErro("importar_informe_rendimentos_arquivo_erro");
/*     */         } 
/* 185 */         if (erros.size() > 0) {
/* 186 */           StringBuilder mensagemErroCompleta = new StringBuilder();
/* 187 */           String nomeArquivo = (new File(arquivoImportacao)).getName();
/* 188 */           for (Exception exception : erros) {
/* 189 */             String complemento = "";
/* 190 */             if (exception instanceof SAXParseException) {
/* 191 */               SAXParseException e = (SAXParseException)exception;
/* 192 */               complemento = "(Linha " + e.getLineNumber() + ", Coluna " + e.getColumnNumber() + " do informe de rendimentos) ";
/*     */             } 
/* 194 */             mensagemErroCompleta.append(complemento + complemento + "\n");
/*     */           } 
/*     */           
/* 197 */           PainelMensagemDetalhada painelMensagemDetalhada = new PainelMensagemDetalhada("Falha na Importação do Informe de Rendimentos", MensagemUtil.getMensagem("importar_informe_rendimentos_arquivo_invalido_sem_parametro"), mensagemErroCompleta.toString(), nomeArquivo);
/*     */           
/* 199 */           GuiUtil.exibeDialog((JComponent)painelMensagemDetalhada, true, "Importação de Informe de Rendimentos", false);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 203 */       GuiUtil.mostrarAviso("acao_cancelar_acao_atual");
/*     */     } 
/*     */   }
/*     */   
/*     */   class FiltroArquivoInformeRendimentos implements FileFilter {
/* 208 */     private final String padraoNomeArquivoInformeRendimentos = "(?i)(.*)\\.xml";
/*     */ 
/*     */     
/*     */     public boolean accept(File f) {
/* 212 */       if (f.isDirectory()) {
/* 213 */         return true;
/*     */       }
/* 215 */       return Validador.validarString(f.getName(), "(?i)(.*)\\.xml");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String getDescription() {
/* 221 */       return "Arquivos de Informe de Rendimentos IRPF-" + ConstantesGlobais.EXERCICIO + "-" + ConstantesGlobais.EXERCICIO_ANTERIOR;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informerendimentos\ProcessoImportacaoInformeRendimentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */