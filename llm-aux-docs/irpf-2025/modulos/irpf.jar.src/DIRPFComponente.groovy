import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

import br.gov.serpro.midas.config.Config
import br.gov.serpro.midas.exception.AmbienteException
import br.gov.serpro.midas.model.DeclaracaoDTO
import br.gov.serpro.midas.negocio.Solicitacao
import br.gov.serpro.midas.text.MidasTextResourceProcessor
import br.gov.serpro.midas.text.MidasTextResourceResponse
import br.gov.serpro.midas.text.MidasTextResourceResponseImpl

class DIRPFComponente implements MidasTextResourceProcessor {

	def static final RETORNO_OK = "00"
	def static final MENSAGEM_OK = "SUCESSO"

	def static final CODIGO_LAYOUT_DEC = "17"
	def static final CODIGO_LAYOUT_REC = "18"
	
	def static final DEFAULT_CHARSET = java.nio.charset.StandardCharsets.UTF_8

	@Override
	public MidasTextResourceResponse execute(DeclaracaoDTO declaracao, Solicitacao solicitacao, OutputStream out) throws AmbienteException {

		def response
		def arrayRetorno = new String[2]

		def decCompactado = solicitacao.textoDeclaracao
		def recCompactado = solicitacao.textoRecibo
		
		def dec =  decCompactado //new ByteArrayInputStream(descompactarArquivo(decCompactado))
		def rec = null

		if (recCompactado) {
			rec = new ByteArrayInputStream(descompactarArquivo(recCompactado))
		}

		def baos

		if (rec) {
			baos = concatenar(dec, rec)
			declaracao.codLayout = CODIGO_LAYOUT_REC
		} else {
			baos = new ByteArrayOutputStream()
			baos.write(dec.getBytes())
			declaracao.codLayout = CODIGO_LAYOUT_DEC
		}

                def arr = new ByteArrayInputStream(baos.toByteArray())

		def decTransformado = transformarDeclaracao(arr, out, declaracao.exercicio)

		arrayRetorno[0] = RETORNO_OK
		arrayRetorno[1] = MENSAGEM_OK
		response = new MidasTextResourceResponseImpl(arrayRetorno)

		return response
	}

	private ByteArrayOutputStream concatenar(ByteArrayInputStream dec, ByteArrayInputStream rec) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		outputStream.write(dec.getBytes());
		outputStream.write(rec.getBytes());

		return outputStream
	}

	private transformarDeclaracao(InputStream arquivoEntrada, OutputStream arquivoSaida, String exercicio) {

		def mapeamentoRegistros = obterMapeamentoRegistrosPorExercicio(exercicio)
		def mapeamentoCampos = obterMapeamentoCamposPorExercicio(exercicio)

		arquivoSaida.withWriter (DEFAULT_CHARSET.name(), { out ->
			arquivoEntrada.eachLine { linha ->
				def registro = linha[0..1]
            	
				if (mapeamentoRegistros.containsKey(registro)) {
					def sbLinha = new StringBuilder(linha)
					def offset = 0
					mapeamentoRegistros[registro].each { mapa ->

						def inicio = mapa['inicio'] + offset
						def fim = inicio + mapa['tamanhoCodigo'] - 1

                         
						def tamanhoDescricao = mapa['tamanhoDescricao']
						def temOffset = mapa['temOffset']
						
						def valor = mapeamentoCampos[mapa['campo']][sbLinha.toString()[inicio..fim].toString().trim()]                                                
						
						def beginIndex = fim + 1
						def endIndex = beginIndex + tamanhoDescricao
						def descricao = padOrSubstring(valor, tamanhoDescricao)

						if (temOffset) {
							sbLinha.insert(beginIndex, descricao)
							offset += tamanhoDescricao
						} else {
							sbLinha.replace(beginIndex, endIndex, descricao)
						}

					}
					out.println(sbLinha.toString())
					
				} else {
					out.println(linha)
				}
			}
		})
	}

	private obterMapeamentoRegistrosPorExercicio(exercicio) {
		Class clazz = obterClasseMapeamentoPorExercicio(exercicio)         
		Object instance = clazz.newInstance()

		return clazz.getMethod("getMapeamentoRegistros").invoke(instance)
	}

	private obterMapeamentoCamposPorExercicio(exercicio) {
		Class clazz = obterClasseMapeamentoPorExercicio(exercicio)
		Object instance = clazz.newInstance()

		return clazz.getMethod("getMapeamentoCampos").invoke(instance)
	}

	private Class obterClasseMapeamentoPorExercicio(exercicio) {
		GroovyClassLoader gcl = new GroovyClassLoader()
		
		String arqExercicio = "/" + exercicio + "/DIRPF" + exercicio + ".groovy"
		String arqGroovy = "/DIRPFIf.groovy"
		
		java.io.InputStream streamGroovy = getClass().getResourceAsStream(arqGroovy)
		java.io.BufferedReader brGroovy = new BufferedReader(new InputStreamReader(streamGroovy, DEFAULT_CHARSET))
		
		java.io.InputStream streamExercicio = getClass().getResourceAsStream(arqExercicio)
		java.io.BufferedReader brExercicio = new BufferedReader(new InputStreamReader(streamExercicio, DEFAULT_CHARSET))
		
		
		gcl.parseClass(brGroovy, arqGroovy)
		Class clazz = gcl.parseClass(brExercicio, arqExercicio)

		return clazz
	}

	private byte[] descompactarArquivo(String arquivoCompactado) {

		byte[] buffer = new byte[1024]

		ByteArrayOutputStream baos = new ByteArrayOutputStream()

		new ZipInputStream(new ByteArrayInputStream(arquivoCompactado.decodeBase64())).withStream { zis ->

			ZipEntry ze = zis.getNextEntry()

			while (ze != null) {

				int len;
				while ((len = zis.read(buffer)) > 0) {
					baos.write(buffer, 0, len)
				}

				ze = zis.getNextEntry()
			}

			zis.closeEntry()
		}

		return baos.toByteArray()
	}

	private String padOrSubstring(String s, int size) {
		def str = s == null ? "" : s
		return str.length() <= size ? str.padRight(size) : str[0..size - 1]
	}
}
