package serpro.ppgd.irpf.util.calculos;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CampoInformacao {
  String[] dependencias() default {};
  
  String metodoAtualizacao() default "";
  
  Class<?> classeAtualizacao() default Object.class;
}


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\calculos\CampoInformacao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */