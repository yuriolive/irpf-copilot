/*     */ package serpro.ppgd.irpf.util;
/*     */ 
/*     */ import java.util.Arrays;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Chave
/*     */ {
/*     */   private String chave;
/*     */   private String[] args;
/*     */   
/*     */   public Chave(String pChave) {
/* 150 */     this.chave = pChave;
/* 151 */     this.args = null;
/*     */   }
/*     */   
/*     */   public Chave(String pChave, String[] pArgs) {
/* 155 */     this.chave = pChave;
/* 156 */     this.args = pArgs;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 161 */     int prime = 31;
/* 162 */     int result = 1;
/* 163 */     result = 31 * result + Arrays.hashCode((Object[])this.args);
/* 164 */     result = 31 * result + ((this.chave == null) ? 0 : this.chave.hashCode());
/* 165 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 170 */     if (this == obj) {
/* 171 */       return true;
/*     */     }
/* 173 */     if (obj == null) {
/* 174 */       return false;
/*     */     }
/* 176 */     if (getClass() != obj.getClass()) {
/* 177 */       return false;
/*     */     }
/* 179 */     Chave other = (Chave)obj;
/* 180 */     if (!Arrays.equals((Object[])this.args, (Object[])other.args)) {
/* 181 */       return false;
/*     */     }
/* 183 */     if (this.chave == null) {
/* 184 */       if (other.chave != null) {
/* 185 */         return false;
/*     */       }
/* 187 */     } else if (!this.chave.equals(other.chave)) {
/* 188 */       return false;
/*     */     } 
/* 190 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\Chave.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */