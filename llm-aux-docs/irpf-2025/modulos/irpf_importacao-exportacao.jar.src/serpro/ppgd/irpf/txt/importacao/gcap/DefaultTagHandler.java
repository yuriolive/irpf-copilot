package serpro.ppgd.irpf.txt.importacao.gcap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public abstract class DefaultTagHandler {
  public abstract void startElement(String paramString, Attributes paramAttributes) throws SAXException;
  
  public abstract void endElement(String paramString1, String paramString2) throws SAXException;
  
  public abstract void characters(String paramString1, String paramString2) throws SAXException;
}


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\gcap\DefaultTagHandler.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */