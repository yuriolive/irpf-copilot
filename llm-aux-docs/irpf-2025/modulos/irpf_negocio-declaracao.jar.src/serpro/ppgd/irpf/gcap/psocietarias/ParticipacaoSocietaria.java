/*    */ package serpro.ppgd.irpf.gcap.psocietarias;
/*    */ import java.util.ArrayList;
/*    */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.CNPJ;
/*    */ import serpro.ppgd.negocio.Codigo;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ public class ParticipacaoSocietaria extends ObjetoNegocio {
/* 10 */   private Alfa nome = new Alfa(this, "Nome da sociedade", 60);
/* 11 */   private CNPJ cnpj = new CNPJ(this, "CNPJ");
/* 12 */   private Codigo especie = new Codigo(this, "Qual a Espécie de Participação?", CadastroTabelasIRPF.recuperarEspecieOperacaoPSocietaria());
/* 13 */   private Codigo uf = new Codigo(this, "UF", CadastroTabelasIRPF.recuperarUFs(0));
/* 14 */   private Codigo municipio = new Codigo(this, "Município", new ArrayList());
/*    */ 
/*    */   
/*    */   public ParticipacaoSocietaria() {
/* 18 */     getUf().setColunaFiltro(1);
/*    */     
/* 20 */     getUf().addObservador(new Observador()
/*    */         {
/*    */           
/*    */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*    */           {
/* 25 */             if (ParticipacaoSocietaria.this.uf.isVazio()) {
/* 26 */               ParticipacaoSocietaria.this.municipio.setColecaoElementoTabela(new ArrayList());
/*    */             } else {
/* 28 */               String strUf = ParticipacaoSocietaria.this.uf.getConteudoAtual(0);
/* 29 */               ParticipacaoSocietaria.this.municipio.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarMunicipios(strUf, 1));
/*    */             } 
/*    */           }
/*    */         });
/* 33 */     setReadOnlyParticipacaoSocietaria();
/*    */   }
/*    */   
/*    */   public void setReadOnlyParticipacaoSocietaria() {
/* 37 */     this.nome.setReadOnly(true);
/* 38 */     this.cnpj.setReadOnly(true);
/* 39 */     this.especie.setReadOnly(true);
/* 40 */     this.especie.setHabilitado(false);
/* 41 */     this.uf.setReadOnly(true);
/* 42 */     this.uf.setHabilitado(false);
/* 43 */     this.municipio.setReadOnly(true);
/* 44 */     this.municipio.setHabilitado(false);
/*    */   }
/*    */   
/*    */   public Alfa getNome() {
/* 48 */     return this.nome;
/*    */   }
/*    */   
/*    */   public CNPJ getCnpj() {
/* 52 */     return this.cnpj;
/*    */   }
/*    */   
/*    */   public Codigo getEspecie() {
/* 56 */     return this.especie;
/*    */   }
/*    */   
/*    */   public Codigo getUf() {
/* 60 */     return this.uf;
/*    */   }
/*    */   
/*    */   public Codigo getMunicipio() {
/* 64 */     return this.municipio;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\psocietarias\ParticipacaoSocietaria.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */