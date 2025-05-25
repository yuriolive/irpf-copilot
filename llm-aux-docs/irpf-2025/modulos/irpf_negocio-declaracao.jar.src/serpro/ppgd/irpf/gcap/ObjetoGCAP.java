package serpro.ppgd.irpf.gcap;

import serpro.ppgd.negocio.CPF;
import serpro.ppgd.negocio.Data;

public interface ObjetoGCAP {
  CPF getCpf();
  
  Data getDataInicioPermanencia();
  
  Data getDataFimPermanencia();
}


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\ObjetoGCAP.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */