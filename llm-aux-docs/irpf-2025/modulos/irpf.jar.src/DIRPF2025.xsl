<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="iso-8859-1" />

	<xsl:key name="rendpf-por-dependente" match="_22[E_DEPENDENTE='S']"
		use="NR_CPF_DEPEN" />
	
	<xsl:key name="rendavar-por-dependente" match="_40[E_DEPENDENTE='S']"
		use="NR_CPF_DEPEN" />
	
	<xsl:key name="rendfundos-por-titular" match="_42[E_DEPENDENTE='N']"
		use="NR_CPF_DEPEN" />
			
	<xsl:key name="rendfundos-por-dependente" match="_42[E_DEPENDENTE='S']"
		use="NR_CPF_DEPEN" />

	<xsl:template match="result">
		<result>
			
			<xsl:for-each
				select="FR | IR | _01 | _16 | _17 | _18 | _19 | _20 | _21 | _25 | _28 | _29 | _30 | _31 | _32 | _33 | _34 | _35 | _36 | _38 | _39 | _41 | _43 | _51 | _52 | _53 | _54 | _55 | _56 | _58 | _59 | _78 | _80 | _81 | _82 | _83 | _84 | _85 | _86 | _87 | _88 | _89 | _90 | _91 | _92 | _93 | _94 | _95 | _96 | _97 | _98 | _99 | HR | DR | HC | RC | NC | VC | MC | TC">
				<xsl:copy-of select="." />
			</xsl:for-each>

			<xsl:apply-templates select="_22" />
			
			<_23>
				<NR_REG>23</NR_REG>
				<NR_CPF><xsl:value-of select="_23/NR_CPF" /></NR_CPF>
				<VR_BOLSA><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0001]/VR_VALOR[number(.) = .])"/></VR_BOLSA>
				<VR_MEDICOS_RESIDENTES><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0002]/VR_VALOR[number(.) = .])"/></VR_MEDICOS_RESIDENTES>
				<VR_PREVI><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0003]/VR_VALOR[number(.) = .])"/></VR_PREVI>
				<VR_FGTS><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0004]/VR_VALOR[number(.) = .])"/></VR_FGTS>
				<VR_PEQUENO><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0005]/VR_VALOR[number(.) = .])"/></VR_PEQUENO>
				<VR_UNICO><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0006]/VR_VALOR[number(.) = .])"/></VR_UNICO>
				<VR_REDUCAO><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0007]/VR_VALOR[number(.) = .])"/></VR_REDUCAO>
				<VR_GCMOEDAEST><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0008]/VR_VALOR[number(.) = .])"/></VR_GCMOEDAEST>
				<VR_LUCROS><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0009]/VR_VALOR[number(.) = .])"/></VR_LUCROS>
				<VR_65ANOS><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0010]/VR_VALOR[number(.) = .])"/></VR_65ANOS>
				<VR_INVALIDEZ><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0011]/VR_VALOR[number(.) = .])"/></VR_INVALIDEZ>
				<VR_POUPANCA><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0012]/VR_VALOR[number(.) = .])"/></VR_POUPANCA>
				<VR_SOCIO><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0013]/VR_VALOR[number(.) = .])"/></VR_SOCIO>
				<VR_HERANCA><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0014]/VR_VALOR[number(.) = .])"/></VR_HERANCA>
				<VR_RURAL><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0015]/VR_VALOR[number(.) = .])"/></VR_RURAL>
				<VR_IR_COMPENSADO_JUDICIAL><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0016]/VR_VALOR[number(.) = .])"/></VR_IR_COMPENSADO_JUDICIAL>
				<VR_REND_ASSAL_RECEB_MOEDA_ESTRANG><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0017]/VR_VALOR[number(.) = .])"/></VR_REND_ASSAL_RECEB_MOEDA_ESTRANG>
				<VR_INCORP_RESERVACAPITAL_BONIFICACOESACOES><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0018]/VR_VALOR[number(.) = .])"/></VR_INCORP_RESERVACAPITAL_BONIFICACOESACOES>
				<VR_MEACAO_DISSOLUCAO><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0019]/VR_VALOR[number(.) = .])"/></VR_MEACAO_DISSOLUCAO>
				<VR_GANHOS_LIQUIDOS_ACOES><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0020]/VR_VALOR[number(.) = .])"/></VR_GANHOS_LIQUIDOS_ACOES>
				<VR_GANHOS_CAPITAL_OURO><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0021]/VR_VALOR[number(.) = .])"/></VR_GANHOS_CAPITAL_OURO>
				<VR_RECUPERACAO_PREJUIZOS_BOLSA><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0022]/VR_VALOR[number(.) = .])"/></VR_RECUPERACAO_PREJUIZOS_BOLSA>
				<VR_TRANSPORTADOR_CARGAS><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0023]/VR_VALOR[number(.) = .])"/></VR_TRANSPORTADOR_CARGAS>
				<VR_TRANSPORTADOR_PASSAGEIROS><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0024]/VR_VALOR[number(.) = .])"/></VR_TRANSPORTADOR_PASSAGEIROS>
				<VR_RESTITUICAO_IMPOSTO><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0025]/VR_VALOR[number(.) = .])"/></VR_RESTITUICAO_IMPOSTO>
				<VR_OUTROS><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0026]/VR_VALOR[number(.) = .])"/></VR_OUTROS>
				<VR_JUROS_RRA><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0027]/VR_VALOR[number(.) = .])"/></VR_JUROS_RRA>
				<VR_PENSAO_ALIMENTICIA><xsl:value-of select="sum(//_23[NR_COD_ISENTO = 0028]/VR_VALOR[number(.) = .])"/></VR_PENSAO_ALIMENTICIA>
			</_23>

			<_24>
				<NR_REG>24</NR_REG>
				<NR_CPF><xsl:value-of select="_24/NR_CPF" /></NR_CPF>
				<VR_13SAL><xsl:value-of select="sum(//_24[NR_COD_EXCLUSIVO = 0001]/VR_VALOR[number(.) = .])"/></VR_13SAL>
				<VR_GC><xsl:value-of select="sum(//_24[NR_COD_EXCLUSIVO = 0002]/VR_VALOR[number(.) = .])"/></VR_GC>
				<VR_GCBENSMOEDAEST><xsl:value-of select="sum(//_24[NR_COD_EXCLUSIVO = 0003]/VR_VALOR[number(.) = .])"/></VR_GCBENSMOEDAEST>
				<VR_GCALIENMOEDAEST><xsl:value-of select="sum(//_24[NR_COD_EXCLUSIVO = 0004]/VR_VALOR[number(.) = .])"/></VR_GCALIENMOEDAEST>
				<VR_RENDAVAR><xsl:value-of select="sum(//_24[NR_COD_EXCLUSIVO = 0005]/VR_VALOR[number(.) = .])"/></VR_RENDAVAR>
				<VR_FINANCEIRAS><xsl:value-of select="sum(//_24[NR_COD_EXCLUSIVO = 0006]/VR_VALOR[number(.) = .])"/></VR_FINANCEIRAS>
				<VR_RRA><xsl:value-of select="sum(//_24[NR_COD_EXCLUSIVO = 0007]/VR_VALOR[number(.) = .])"/></VR_RRA>
				<VR_13SALDEPENDENTES><xsl:value-of select="sum(//_24[NR_COD_EXCLUSIVO = 0008]/VR_VALOR[number(.) = .])"/></VR_13SALDEPENDENTES>
				<VR_RRADEPENDENTES><xsl:value-of select="sum(//_24[NR_COD_EXCLUSIVO = 0009]/VR_VALOR[number(.) = .])"/></VR_RRADEPENDENTES>
				<VR_JUROS_CAPITAL_PROPRIO><xsl:value-of select="sum(//_24[NR_COD_EXCLUSIVO = 0010]/VR_VALOR[number(.) = .])"/></VR_JUROS_CAPITAL_PROPRIO>
				<VR_PART_LUCROS_RESULT><xsl:value-of select="sum(//_24[NR_COD_EXCLUSIVO = 0011]/VR_VALOR[number(.) = .])"/></VR_PART_LUCROS_RESULT>
                                <VR_LEI14754><xsl:value-of select="sum(//_24[NR_COD_EXCLUSIVO = 0013]/VR_VALOR[number(.) = .])"/></VR_LEI14754>
				<VR_OUTROS><xsl:value-of select="sum(//_24[NR_COD_EXCLUSIVO = 0012]/VR_VALOR[number(.) = .])"/></VR_OUTROS>
			</_24>

			<xsl:apply-templates select="_26" />

			<xsl:apply-templates select="_27" />
			
			<xsl:apply-templates select="_37" />
			
			<xsl:apply-templates select="_40" />
			
			<xsl:apply-templates select="_42" />
			
			<xsl:apply-templates select="_45" />
			
			<xsl:apply-templates select="_47" />

			<xsl:apply-templates select="_50" />
			
			<xsl:apply-templates select="_61" />

			<xsl:apply-templates select="_62" />

			<xsl:apply-templates select="_63" />

			<xsl:apply-templates select="_60" />
                        
			<xsl:for-each select="T9 | HR | DR | R9">
				<xsl:copy-of select="." />
			</xsl:for-each>

			<!-- efetua alguns somatorios que nao estao no xml... -->

			<xsl:variable name="varTotalImpostoPagoBRIMOVEL" select="sum(//_61[IN_BRASIL_EXTERIOR = 1]/VR_IMPOSTO_PAGO_CI[number(.) = .])" />
			<xsl:variable name="varTotalImpostoPagoBRMOVEL" select="sum(//_62[IN_BRASIL_EXTERIOR = 1]/VR_IMPOSTO_PAGO_CI[number(.) = .])" />
			<xsl:variable name="varTotalImpostoPagoBRPSOC" select="sum(//_63/VR_IMPOSTO_PAGO_CI[number(.) = .])" />
			
			<totalImpostoPagoGCAP>
				<xsl:value-of select="$varTotalImpostoPagoBRIMOVEL + $varTotalImpostoPagoBRMOVEL + $varTotalImpostoPagoBRPSOC" />
			</totalImpostoPagoGCAP>

			<xsl:variable name="varTotalImpostoPagoMEIMOVEL" select="sum(//_61[IN_BRASIL_EXTERIOR = 0]/VR_IMPOSTO_PAGO_CI[number(.) = .])" />
			<xsl:variable name="varTotalImpostoPagoMEMOVEL" select="sum(//_62[IN_BRASIL_EXTERIOR = 0]/VR_IMPOSTO_PAGO_CI[number(.) = .])" />
			
			<totalImpostoPagoME>
				<xsl:value-of select="$varTotalImpostoPagoMEIMOVEL + $varTotalImpostoPagoMEMOVEL" />
			</totalImpostoPagoME>

			<xsl:variable name="totalImpostoPagoRendaVar" select="sum(_40/VR_IMPOSTOPAGO)" />
			<xsl:variable name="totalImpostoPagoRendaVarFII" select="sum(_42/VR_IMPOSTOPAGO)" />

			<totalImpostoPagoRendaVariavel>
				<xsl:value-of
					select="$totalImpostoPagoRendaVar + $totalImpostoPagoRendaVarFII" />
			</totalImpostoPagoRendaVariavel>

			<!-- Bens -->
			
			<xsl:for-each select="//_27">
				<xsl:sort select="concat(CD_GRUPO_BEM,CD_BEM)"/>
				<_27>
				<xsl:copy-of select="child::*" />
						
				<xsl:variable name="chaveBem" select="NR_CHAVE_BEM" />
				<xsl:variable name="inTipoDeclaracao" select="//_16/IN_TIPODECLARACAO" />
	
				<xsl:choose>
					<xsl:when test="$inTipoDeclaracao='E'">
						<xsl:for-each select="//_59[NR_CHAVE_BEM=$chaveBem]">
							<xsl:variable name="chaveHerdeiro" select="NR_CHAVE_HERDEIRO" />
							<PERCENTUAL_BEM>
								<NI_HERDEIRO>
									<xsl:value-of
										select="../_58[NR_CHAVE_HERDEIRO=$chaveHerdeiro]/NR_CPF_CNPJ" />
								</NI_HERDEIRO>
								<NOME_HERDEIRO>
									<xsl:value-of select="../_58[NR_CHAVE_HERDEIRO=$chaveHerdeiro]/NM_NOME" />
								</NOME_HERDEIRO>
								<VR_PERCENTUAL>
									<xsl:value-of select="VR_PERCENTUAL" />
								</VR_PERCENTUAL>
							</PERCENTUAL_BEM>
						</xsl:for-each>
					</xsl:when>
				</xsl:choose>
				
				<!--xsl:choose>
					<xsl:when test="$inTipoDeclaracao='E'" -->
						<xsl:for-each select="//_36[NR_CHAVE_BEM=$chaveBem]">
							<USUFRUTUARIO>
								<CPF_CNPJ>
									<xsl:value-of select="NR_CPF_CNPJ" />
								</CPF_CNPJ>

							</USUFRUTUARIO>
						</xsl:for-each>
					<!--/xsl:when>
				</xsl:choose -->
				
				</_27>
			</xsl:for-each>
			
			<xsl:for-each select="//_37">
				<xsl:sort select="NR_ORDEM"/>
				<_37>
				<xsl:copy-of select="child::*" />
				</_37>
			</xsl:for-each>
			<!-- xsl:for-each select="//_27">
				<xsl:sort select="concat(CD_GRUPO_BEM,CD_BEM)"/>
				<_27>
				<xsl:copy-of select="child::*" />
						
				<xsl:variable name="chaveBem" select="NR_CHAVE_BEM" />
				<xsl:variable name="inTipoDeclaracao" select="//_16/IN_TIPODECLARACAO" />
	
				<xsl:choose>
					<xsl:when test="$inTipoDeclaracao='E'">
						<xsl:for-each select="//_59[NR_CHAVE_BEM=$chaveBem]">
							<xsl:variable name="chaveHerdeiro" select="NR_CHAVE_HERDEIRO" />
							<PERCENTUAL_BEM>
								<NI_HERDEIRO>
									<xsl:value-of
										select="../_58[NR_CHAVE_HERDEIRO=$chaveHerdeiro]/NR_CPF_CNPJ" />
								</NI_HERDEIRO>
								<NOME_HERDEIRO>
									<xsl:value-of select="../_58[NR_CHAVE_HERDEIRO=$chaveHerdeiro]/NM_NOME" />
								</NOME_HERDEIRO>
								<VR_PERCENTUAL>
									<xsl:value-of select="VR_PERCENTUAL" />
								</VR_PERCENTUAL>
							</PERCENTUAL_BEM>
						</xsl:for-each>
					</xsl:when>
					<xsl:for-each select="//_36[NR_CHAVE_BEM=$chaveBem]">
						<PROPRIETARIO_USUFRUTUARIO_BEM>>
							<CPF_CNPJ>
								<xsl:value-of select="NR_CPF_CNPJ" />
							</CPF_CNPJ>
						</PROPRIETARIO_USUFRUTUARIO_BEM>
					</xsl:for-each>
				</xsl:choose>				
				</_27>
			</xsl:for-each -->
			
			
			
			
			<!--xsl:for-each select="//_27">
				<xsl:sort select="concat(CD_GRUPO_BEM,CD_BEM)"/>
				<_27>
				<xsl:copy-of select="child::*" />
						
				<xsl:variable name="chaveBem" select="NR_CHAVE_BEM" />
	
				<xsl:choose>
					<xsl:for-each select="//_36[NR_CHAVE_BEM=$chaveBem]">
						<PROPRIETARIO_USUFRUTUARIO_BEM>>
							<CPF_CNPJ>
								<xsl:value-of select="NR_CPF_CNPJ" />
							</CPF_CNPJ>
						</PROPRIETARIO_USUFRUTUARIO_BEM>
					</xsl:for-each>
				</xsl:choose>
				</_27>
			</xsl:for-each-->			

		</result>

	</xsl:template>

	<xsl:template match="_22[E_DEPENDENTE='N']">
		<xsl:copy-of select="." />
	</xsl:template>

	<xsl:template match="_22[E_DEPENDENTE='S']">
		<xsl:for-each
			select="self::node()[count(. | key('rendpf-por-dependente', NR_CPF_DEPEN)[1]) = 1]">
			<REND_TRIB_PF_DEPENDENTE>
				<xsl:for-each select="key('rendpf-por-dependente', NR_CPF_DEPEN)">
					<xsl:element name="{name(.)}">
					<xsl:copy-of select="child::*" />
					<NR_NITPISPASEP>
						<xsl:variable name="cpfDependente" select="NR_CPF_DEPEN" />
							<xsl:value-of
								select="//_25[NI_DEPEND=$cpfDependente]/NR_NITPISPASEP" />
					</NR_NITPISPASEP>
					</xsl:element>
				</xsl:for-each>
			</REND_TRIB_PF_DEPENDENTE>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="_26">

		<xsl:element name="{name(.)}">
			<xsl:copy-of select="child::*" />
			<xsl:variable name="tipoPagamento" select="IN_TIPO_PGTO" />

			<TIPO_PAGTO_MIDAS>
				<xsl:choose>
					<xsl:when test="$tipoPagamento = ''">
						<xsl:text>V</xsl:text>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$tipoPagamento"/>
					</xsl:otherwise>
				</xsl:choose>
			</TIPO_PAGTO_MIDAS>

			<NOME_DEPENDENTE_ALIMENTANDO>
				<xsl:variable name="chaveDependenteAlimentando" select="NR_CHAVE_DEPEND" />

				<xsl:choose>
					<xsl:when test="$tipoPagamento = 'D'">
						<xsl:value-of
							select="//_25[NR_CHAVE=$chaveDependenteAlimentando]/NM_DEPEND" />
					</xsl:when>
					<xsl:when test="$tipoPagamento = 'A'">
						<xsl:value-of
							select="//_35[NR_CHAVE=$chaveDependenteAlimentando]/NM_NOME" />
					</xsl:when>
				</xsl:choose>
			</NOME_DEPENDENTE_ALIMENTANDO>
		</xsl:element>

	</xsl:template>

	<!--xsl:template match="_27">
						<xsl:for-each select="//_27">
							<xsl:sort select="CD_GRUPO_BEM"/>
							<_27>
							<NR_REG><xsl:value-of select="NR_REG" /></NR_REG>
							<NR_CPF><xsl:value-of select="NR_CPF" /></NR_CPF>
							<CD_BEM><xsl:value-of select="CD_BEM" /></CD_BEM>
							<IN_EXTERIOR><xsl:value-of select="IN_EXTERIOR" /></IN_EXTERIOR>
							<CD_PAIS><xsl:value-of select="CD_PAIS" /></CD_PAIS>
							<NM_PAIS><xsl:value-of select="NM_PAIS" /></NM_PAIS>
							<TX_BEM><xsl:value-of select="TX_BEM" /></TX_BEM>
							<VR_ANTER><xsl:value-of select="VR_ANTER" /></VR_ANTER>
							<VR_ATUAL><xsl:value-of select="VR_ATUAL" /></VR_ATUAL>
							<NM_LOGRA><xsl:value-of select="NM_LOGRA" /></NM_LOGRA>
							<NR_NUMERO><xsl:value-of select="NR_NUMERO" /></NR_NUMERO>
							<NM_COMPLEM><xsl:value-of select="NM_COMPLEM" /></NM_COMPLEM>
							<NM_BAIRRO><xsl:value-of select="NM_BAIRRO" /></NM_BAIRRO>
							<NR_CEP><xsl:value-of select="NR_CEP" /></NR_CEP>
							<SG_UF><xsl:value-of select="SG_UF" /></SG_UF>
							<CD_MUNICIP><xsl:value-of select="CD_MUNICIP" /></CD_MUNICIP>
							<NM_MUNICIP><xsl:value-of select="NM_MUNICIP" /></NM_MUNICIP>
							<NM_IND_REG_IMOV><xsl:value-of select="NM_IND_REG_IMOV" /></NM_IND_REG_IMOV>
							<MATRIC_IMOV><xsl:value-of select="MATRIC_IMOV" /></MATRIC_IMOV>
							<FILLER1><xsl:value-of select="FILLER1" /></FILLER1>
							<AREA><xsl:value-of select="AREA" /></AREA>
							<NM_UNID><xsl:value-of select="NM_UNID" /></NM_UNID>
							<NM_CARTORIO><xsl:value-of select="NM_CARTORIO" /></NM_CARTORIO>
							<NR_CHAVE_BEM><xsl:value-of select="NR_CHAVE_BEM" /></NR_CHAVE_BEM>
							<DT_AQUISICAO><xsl:value-of select="DT_AQUISICAO" /></DT_AQUISICAO>
							<FILLER2><xsl:value-of select="FILLER2" /></FILLER2>
							<FILLER3><xsl:value-of select="FILLER3" /></FILLER3>
							<NR_RENAVAN><xsl:value-of select="NR_RENAVAN" /></NR_RENAVAN>
							<NR_DEP_AVIACAO_CIVIL><xsl:value-of select="NR_DEP_AVIACAO_CIVIL" /></NR_DEP_AVIACAO_CIVIL>
							<NR_CAPITANIA_PORTOS><xsl:value-of select="NR_CAPITANIA_PORTOS" /></NR_CAPITANIA_PORTOS>
							<NR_AGENCIA><xsl:value-of select="NR_AGENCIA" /></NR_AGENCIA>
							<FILLER4><xsl:value-of select="FILLER4" /></FILLER4>
							<NR_DV_CONTA><xsl:value-of select="NR_DV_CONTA" /></NR_DV_CONTA>
							<NM_CPFCNPJ><xsl:value-of select="NM_CPFCNPJ" /></NM_CPFCNPJ>
							<NR_IPTU><xsl:value-of select="NR_IPTU" /></NR_IPTU>
							<NR_BANCO><xsl:value-of select="NR_BANCO" /></NR_BANCO>
							<IN_TIPO_BENEFIC><xsl:value-of select="IN_TIPO_BENEFIC" /></IN_TIPO_BENEFIC>
							<NR_CPF_BENEFIC><xsl:value-of select="NR_CPF_BENEFIC" /></NR_CPF_BENEFIC>
							<CD_GRUPO_BEM><xsl:value-of select="CD_GRUPO_BEM" /></CD_GRUPO_BEM>
							<IN_BEM_INVENTARIAR><xsl:value-of select="IN_BEM_INVENTARIAR" /></IN_BEM_INVENTARIAR>
							<NR_CONTA><xsl:value-of select="NR_CONTA" /></NR_CONTA>
							<NR_CIB><xsl:value-of select="NR_CIB" /></NR_CIB>
							<NR_CEI_CNO><xsl:value-of select="NR_CEI_CNO" /></NR_CEI_CNO>
							<NR_CONTROLE><xsl:value-of select="NR_CONTROLE" /></NR_CONTROLE>
									
							<xsl:variable name="chaveBem" select="NR_CHAVE_BEM" />
							<xsl:variable name="inTipoDeclaracao" select="//_16/IN_TIPODECLARACAO" />
				
							<xsl:choose>
								<xsl:when test="$inTipoDeclaracao='E'">
									<xsl:for-each select="//_59[NR_CHAVE_BEM=$chaveBem]">
										<xsl:variable name="chaveHerdeiro" select="NR_CHAVE_HERDEIRO" />
										<PERCENTUAL_BEM>
											<NI_HERDEIRO>
												<xsl:value-of
													select="../_58[NR_CHAVE_HERDEIRO=$chaveHerdeiro]/NR_CPF_CNPJ" />
											</NI_HERDEIRO>
											<NOME_HERDEIRO>
												<xsl:value-of select="../_58[NR_CHAVE_HERDEIRO=$chaveHerdeiro]/NM_NOME" />
											</NOME_HERDEIRO>
											<VR_PERCENTUAL>
												<xsl:value-of select="VR_PERCENTUAL" />
											</VR_PERCENTUAL>
										</PERCENTUAL_BEM>
									</xsl:for-each>
								</xsl:when>
							</xsl:choose>
							</_27>
						</xsl:for-each>	
	</xsl:template-->
	
	<!--xsl:template match="_27">

		<xsl:element name="{name(.)}">
			<xsl:copy-of select="child::*" />
			<xsl:variable name="chaveBem" select="NR_CHAVE_BEM" />
			<xsl:variable name="inTipoDeclaracao" select="//_16/IN_TIPODECLARACAO" />

			<xsl:choose>
				<xsl:when test="$inTipoDeclaracao='E'">
					<xsl:for-each select="//_59[NR_CHAVE_BEM=$chaveBem]">
						<xsl:variable name="chaveHerdeiro" select="NR_CHAVE_HERDEIRO" />
						<PERCENTUAL_BEM>
							<NI_HERDEIRO>
								<xsl:value-of
									select="../_58[NR_CHAVE_HERDEIRO=$chaveHerdeiro]/NR_CPF_CNPJ" />
							</NI_HERDEIRO>
							<NOME_HERDEIRO>
								<xsl:value-of select="../_58[NR_CHAVE_HERDEIRO=$chaveHerdeiro]/NM_NOME" />
							</NOME_HERDEIRO>
							<VR_PERCENTUAL>
								<xsl:value-of select="VR_PERCENTUAL" />
							</VR_PERCENTUAL>
						</PERCENTUAL_BEM>
					</xsl:for-each>
				</xsl:when>
			</xsl:choose>
		</xsl:element>
	</xsl:template-->
	
	<xsl:template match="_40[E_DEPENDENTE='N']">
		<xsl:copy-of select="." />
	</xsl:template>

	<xsl:template match="_40[E_DEPENDENTE='S']">
		<xsl:for-each
			select="self::node()[count(. | key('rendavar-por-dependente', NR_CPF_DEPEN)[1]) = 1]">
			<RENDAVAR_DEPENDENTE>
				<xsl:for-each select="key('rendavar-por-dependente', NR_CPF_DEPEN)">
					<xsl:copy-of select="." />
				</xsl:for-each>
			</RENDAVAR_DEPENDENTE>
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template match="_42[E_DEPENDENTE='N']">
		<xsl:for-each
			select="self::node()[count(. | key('rendfundos-por-titular', NR_CPF_DEPEN)[1]) = 1]">
			<REND_FUNDOS_TITULAR>
				<xsl:for-each select="key('rendfundos-por-titular', NR_CPF_DEPEN)">
					<xsl:copy-of select="." />
				</xsl:for-each>
			</REND_FUNDOS_TITULAR>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="_42[E_DEPENDENTE='S']">
		<xsl:for-each
			select="self::node()[count(. | key('rendfundos-por-dependente', NR_CPF_DEPEN)[1]) = 1]">
			<REND_FUNDOS_DEPENDENTE>
				<xsl:for-each select="key('rendfundos-por-dependente', NR_CPF_DEPEN)">
					<xsl:copy-of select="." />
				</xsl:for-each>
			</REND_FUNDOS_DEPENDENTE>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="_45">

		<xsl:element name="{name(.)}">
			<xsl:copy-of select="child::*" />
			<xsl:variable name="codigoRRA" select="CD_RRA_TITULAR" />

			<xsl:for-each select="//_46[CD_RRA_TITULAR=$codigoRRA]">
				<xsl:variable name="chaveAlimentando" select="NR_CHAVE_ALIMENT" />
				<PENSAO_ALIMENTICIA>
					<NOME_ALIMENTANDO>
						<xsl:value-of select="//_35[NR_CHAVE=$chaveAlimentando]/NM_NOME" />
					</NOME_ALIMENTANDO>
					<VALOR_ALIMENTANDO>
						<xsl:value-of select="VR_PAGTO" />
					</VALOR_ALIMENTANDO>
				</PENSAO_ALIMENTICIA>
			</xsl:for-each>
		</xsl:element>

	</xsl:template>
	
	<xsl:template match="_47">

		<xsl:element name="{name(.)}">
			<xsl:copy-of select="child::*" />
			<xsl:variable name="codigoRRA" select="CD_RRA_DEPENDENTE" />

			<xsl:for-each select="//_48[CD_RRA_DEPENDENTE=$codigoRRA]">
				<xsl:variable name="chaveAlimentando" select="NR_CHAVE_ALIMENT" />
				<PENSAO_ALIMENTICIA>
					<NOME_ALIMENTANDO>
						<xsl:value-of select="//_35[NR_CHAVE=$chaveAlimentando]/NM_NOME" />
					</NOME_ALIMENTANDO>
					<VALOR_ALIMENTANDO>
						<xsl:value-of select="VR_PAGTO" />
					</VALOR_ALIMENTANDO>
				</PENSAO_ALIMENTICIA>
			</xsl:for-each>
		</xsl:element>

	</xsl:template>
	
	<xsl:template match="_50">

		<xsl:element name="{name(.)}">
			<xsl:copy-of select="child::*" />
			<xsl:variable name="indice" select="NR_CHAVE_AR" />
	
			<xsl:for-each select="//_57[NR_CHAVE_AR=$indice]">
				<PARTICIPANTE>
					<NR_CPF_CNPJ_PROPRIETARIO>
						<xsl:value-of select="NR_CPF_CNPJ_PROPRIETARIO" />
					</NR_CPF_CNPJ_PROPRIETARIO>
					<NM_NOME_PROPRIETARIO>
						<xsl:value-of select="NM_NOME_PROPRIETARIO" />
					</NM_NOME_PROPRIETARIO>
					<IN_EXTERIOR>
						<xsl:value-of select="IN_EXTERIOR" />
					</IN_EXTERIOR>
				</PARTICIPANTE>
			</xsl:for-each>
		</xsl:element>

	</xsl:template>
	
	<xsl:template match="_61">
		<xsl:for-each select=".">
			<IMOVEL>
				<xsl:copy-of select="." />

				<xsl:variable name="nr_operacao" select="NR_OPERACAO" />
				
				<xsl:variable name="nr_cpf_beneficiario" select="NR_CPF_BENEFICIARIO" />

				<xsl:variable name="nr_identificacao" select="NR_IDENTIFICACAO" />

				<xsl:for-each select="//_60[NR_CPF_BENEFICIARIO=$nr_cpf_beneficiario and NR_IDENTIFICACAO=$nr_identificacao]">
				     <RESIDENCIA>
					<xsl:copy-of select="NR_CPF_BENEFICIARIO" />
					<xsl:copy-of select="CD_PAIS" />
					<xsl:copy-of select="NM_PAIS" />
				     </RESIDENCIA>
				</xsl:for-each>	

				<xsl:for-each select="//_64[NR_OPERACAO=$nr_operacao]">
					<xsl:copy-of select="." />
				</xsl:for-each>

				<xsl:for-each select="//_65[NR_OPERACAO=$nr_operacao]">
					<xsl:copy-of select="." />
				</xsl:for-each>

				<xsl:for-each select="//_66[NR_OPERACAO=$nr_operacao]">
					<xsl:copy-of select="." />
				</xsl:for-each>

				<xsl:for-each select="//_67[NR_OPERACAO=$nr_operacao]">
					<xsl:copy-of select="." />
				</xsl:for-each>

				<xsl:for-each select="//_68[NR_OPERACAO=$nr_operacao and NR_TIPO_APURACAO='1']">
				<APURACAO_BRASIL>
					<xsl:copy-of select="." />
				</APURACAO_BRASIL> 
				</xsl:for-each>	

				<xsl:for-each select="//_68[NR_OPERACAO=$nr_operacao and (NR_TIPO_APURACAO='2' or NR_TIPO_APURACAO='4')]">
				<APURACAO_MOEDA_NACIONAL>
					<xsl:copy-of select="." />
				</APURACAO_MOEDA_NACIONAL> 
				</xsl:for-each>	
				
				<xsl:for-each select="//_68[NR_OPERACAO=$nr_operacao and (NR_TIPO_APURACAO='3' or NR_TIPO_APURACAO='5')]">
				<APURACAO_MOEDA_ESTRANGEIRA>
					<xsl:copy-of select="." />
				</APURACAO_MOEDA_ESTRANGEIRA> 
				</xsl:for-each>	

				<xsl:for-each select="//_68[NR_OPERACAO=$nr_operacao and (NR_TIPO_APURACAO='6' or NR_TIPO_APURACAO='8')]">
			    	<APURACAOFINAL_MOEDA_NACIONAL>	
					<xsl:copy-of select="." />
				</APURACAOFINAL_MOEDA_NACIONAL> 
				</xsl:for-each>	

				<xsl:for-each select="//_68[NR_OPERACAO=$nr_operacao and (NR_TIPO_APURACAO='7' or NR_TIPO_APURACAO='9')]">
			    	<APURACAOFINAL_MOEDA_ESTRANGEIRA>	
					<xsl:copy-of select="." />
				</APURACAOFINAL_MOEDA_ESTRANGEIRA> 
				</xsl:for-each>	
				
				<xsl:for-each select="//_71[NR_OPERACAO=$nr_operacao and NR_TIPO_PARCELA='1']">
				<PARCELA>
					<xsl:copy-of select="." />
				</PARCELA>
				</xsl:for-each>
				<xsl:for-each select="//_71[NR_OPERACAO=$nr_operacao and NR_TIPO_PARCELA='2']">
				<PARCELA>
					<MOEDA_NACIONAL>
					<xsl:copy-of select="." />
					</MOEDA_NACIONAL>
				</PARCELA>
				</xsl:for-each>
				<xsl:for-each select="//_71[NR_OPERACAO=$nr_operacao and NR_TIPO_PARCELA='3']">
				<PARCELA>
					<MOEDA_ESTRANGEIRA>
					<xsl:copy-of select="." />
					</MOEDA_ESTRANGEIRA>
				</PARCELA>
				</xsl:for-each>
				<xsl:for-each select="//_71[NR_OPERACAO=$nr_operacao and NR_TIPO_PARCELA='4']">
					<xsl:variable name="dt_parcela" select="DT_PARCELA" />
				<PARCELA>
					<MOEDA_NACIONAL>
					<xsl:copy-of select="." />
					</MOEDA_NACIONAL>
					<xsl:for-each select="//_71[NR_OPERACAO=$nr_operacao and DT_PARCELA=$dt_parcela and NR_TIPO_PARCELA='5']">
					<MOEDA_ESTRANGEIRA>
					<xsl:copy-of select="." />
					</MOEDA_ESTRANGEIRA>
					</xsl:for-each>
					<xsl:for-each select="//_70[NR_OPERACAO=$nr_operacao and DT_PARCELA=$dt_parcela]">
					<AMBAS_MOEDAS>
					<xsl:copy-of select="." />
					</AMBAS_MOEDAS>
					</xsl:for-each>
				</PARCELA>
				</xsl:for-each>

				<xsl:for-each select="//_75[NR_OPERACAO=$nr_operacao]">
					<xsl:copy-of select="." />
				</xsl:for-each>

			</IMOVEL>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="_62">
		<xsl:for-each select=".">
			<MOVEL>
				<xsl:copy-of select="." />

				<xsl:variable name="nr_operacao" select="NR_OPERACAO" />
				
				<xsl:variable name="nr_cpf_beneficiario" select="NR_CPF_BENEFICIARIO" />

				<xsl:variable name="nr_identificacao" select="NR_IDENTIFICACAO" />

				<xsl:for-each select="//_60[NR_CPF_BENEFICIARIO=$nr_cpf_beneficiario and NR_IDENTIFICACAO=$nr_identificacao]">
				     <RESIDENCIA>
					<xsl:copy-of select="NR_CPF_BENEFICIARIO" />
					<xsl:copy-of select="CD_PAIS" />
					<xsl:copy-of select="NM_PAIS" />
				     </RESIDENCIA>
				</xsl:for-each>			

				<xsl:for-each select="//_64[NR_OPERACAO=$nr_operacao]">
					<xsl:copy-of select="." />
				</xsl:for-each>

				<xsl:for-each select="//_65[NR_OPERACAO=$nr_operacao]">
					<xsl:copy-of select="." />
				</xsl:for-each>

				<xsl:for-each select="//_69[NR_OPERACAO=$nr_operacao and NR_TIPO_APURACAO='1']">
				<APURACAO_BRASIL>
					<xsl:copy-of select="." />
				</APURACAO_BRASIL> 
				</xsl:for-each>	

				<xsl:for-each select="//_69[NR_OPERACAO=$nr_operacao and (NR_TIPO_APURACAO='2' or NR_TIPO_APURACAO='4')]">
				<APURACAO_MOEDA_NACIONAL>
					<xsl:copy-of select="." />
				</APURACAO_MOEDA_NACIONAL> 
				</xsl:for-each>	
				
				<xsl:for-each select="//_69[NR_OPERACAO=$nr_operacao and (NR_TIPO_APURACAO='3' or NR_TIPO_APURACAO='5')]">
				<APURACAO_MOEDA_ESTRANGEIRA>
					<xsl:copy-of select="." />
				</APURACAO_MOEDA_ESTRANGEIRA> 
				</xsl:for-each>	

				<xsl:for-each select="//_69[NR_OPERACAO=$nr_operacao and (NR_TIPO_APURACAO='6' or NR_TIPO_APURACAO='8')]">
			    	<APURACAOFINAL_MOEDA_NACIONAL>	
					<xsl:copy-of select="." />
				</APURACAOFINAL_MOEDA_NACIONAL> 
				</xsl:for-each>	

				<xsl:for-each select="//_69[NR_OPERACAO=$nr_operacao and (NR_TIPO_APURACAO='7' or NR_TIPO_APURACAO='9')]">
			    	<APURACAOFINAL_MOEDA_ESTRANGEIRA>	
					<xsl:copy-of select="." />
				</APURACAOFINAL_MOEDA_ESTRANGEIRA> 
				</xsl:for-each>	

				<xsl:for-each select="//_72[NR_OPERACAO=$nr_operacao and NR_TIPO_PARCELA='1']">
				<PARCELA>
					<xsl:copy-of select="." />
				</PARCELA>
				</xsl:for-each>
				<xsl:for-each select="//_72[NR_OPERACAO=$nr_operacao and NR_TIPO_PARCELA='2']">
				<PARCELA>
					<MOEDA_NACIONAL>
					<xsl:copy-of select="." />
					</MOEDA_NACIONAL>
				</PARCELA>
				</xsl:for-each>
				<xsl:for-each select="//_72[NR_OPERACAO=$nr_operacao and NR_TIPO_PARCELA='3']">
				<PARCELA>
					<MOEDA_ESTRANGEIRA>
					<xsl:copy-of select="." />
					</MOEDA_ESTRANGEIRA>
				</PARCELA>
				</xsl:for-each>
				<xsl:for-each select="//_72[NR_OPERACAO=$nr_operacao and NR_TIPO_PARCELA='4']">
					<xsl:variable name="dt_parcela" select="DT_PARCELA" />
				<PARCELA>
					<MOEDA_NACIONAL>
					<xsl:copy-of select="." />
					</MOEDA_NACIONAL>
					<xsl:for-each select="//_72[NR_OPERACAO=$nr_operacao and DT_PARCELA=$dt_parcela and NR_TIPO_PARCELA='5']">
					<MOEDA_ESTRANGEIRA>
					<xsl:copy-of select="." />
					</MOEDA_ESTRANGEIRA>
					</xsl:for-each>
					<xsl:for-each select="//_70[NR_OPERACAO=$nr_operacao and DT_PARCELA=$dt_parcela]">
					<AMBAS_MOEDAS>
					<xsl:copy-of select="." />
					</AMBAS_MOEDAS>
					</xsl:for-each>
				</PARCELA>
				</xsl:for-each>

				<xsl:for-each select="//_75[NR_OPERACAO=$nr_operacao]">
					<xsl:copy-of select="." />
				</xsl:for-each>

			</MOVEL>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="_63">
		<xsl:for-each select=".">
			<PARTICIPACAO>
				<xsl:copy-of select="." />

				<xsl:variable name="nr_operacao" select="NR_OPERACAO" />
				<xsl:variable name="nr_cpf_beneficiario" select="NR_CPF_BENEFICIARIO" />

				<xsl:variable name="nr_identificacao" select="NR_IDENTIFICACAO" />

				<xsl:for-each select="//_60[NR_CPF_BENEFICIARIO=$nr_cpf_beneficiario and NR_IDENTIFICACAO=$nr_identificacao]">
				     <RESIDENCIA>
					<xsl:copy-of select="NR_CPF_BENEFICIARIO" />
					<xsl:copy-of select="CD_PAIS" />
					<xsl:copy-of select="NM_PAIS" />
				     </RESIDENCIA>
				</xsl:for-each>			

				<xsl:for-each select="//_65[NR_OPERACAO=$nr_operacao]">
					<xsl:copy-of select="." />
				</xsl:for-each>

				<xsl:for-each select="//_72[NR_OPERACAO=$nr_operacao and NR_TIPO_PARCELA='1']">
				<PARCELA>
					<xsl:copy-of select="." />
				</PARCELA>
				</xsl:for-each>

				<xsl:for-each select="//_73[NR_OPERACAO=$nr_operacao]">
					<xsl:copy-of select="." />
				</xsl:for-each>

				<xsl:for-each select="//_75[NR_OPERACAO=$nr_operacao]">
					<xsl:copy-of select="." />
				</xsl:for-each>

			</PARTICIPACAO>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="_60">
	<xsl:variable name="nr_cpf_beneficiario" select="NR_CPF_BENEFICIARIO" />
	<xsl:variable name="nr_identificacao" select="NR_IDENTIFICACAO" />
	<xsl:if test="//_74[NR_CPF_BENEFICIARIO=$nr_cpf_beneficiario and NR_IDENTIFICACAO=$nr_identificacao and TIPO_OPERACAO='1']">
	<ESPECIE>
		<xsl:copy-of select="NR_CPF_BENEFICIARIO" />
		<xsl:copy-of select="NR_IDENTIFICACAO" />
		<MOEDAS>
		<xsl:for-each select="//_74[NR_CPF_BENEFICIARIO=$nr_cpf_beneficiario and NR_IDENTIFICACAO=$nr_identificacao and TIPO_OPERACAO='1']">
		<xsl:variable name="moeda" select="CD_MOEDA" />
			<MOEDA>
			<xsl:copy-of select="NM_MOEDA" />
			<xsl:copy-of select="VR_SALDO_ME" />
			<xsl:copy-of select="VR_SALDO_REAIS" />
				<OPERACOES>
				<xsl:for-each select="//_74[NR_CPF_BENEFICIARIO=$nr_cpf_beneficiario and NR_IDENTIFICACAO=$nr_identificacao and CD_MOEDA=$moeda and TIPO_OPERACAO!='1']">
					<OPERACAO>
					<xsl:copy-of select="child::*" />
					</OPERACAO>
				</xsl:for-each>
				</OPERACOES>
			</MOEDA>
		</xsl:for-each>
		</MOEDAS>
                <xsl:if test="//_76[NR_CPF_BENEFICIARIO=$nr_cpf_beneficiario and NR_IDENTIFICACAO=$nr_identificacao]">
                <TOTALIZACAO>
                    <xsl:for-each select="//_76[NR_CPF_BENEFICIARIO=$nr_cpf_beneficiario and NR_IDENTIFICACAO=$nr_identificacao]">
                    <MES>
                        <xsl:copy-of select="child::*" />
                    </MES>    
                    </xsl:for-each>    
                </TOTALIZACAO>
                </xsl:if>
		<CONSOLIDACAO>
			<xsl:copy-of select="GC_GCAP_MOEDA" />
			<xsl:copy-of select="GC_IMPOSTO_DEVIDO_MOEDA" />
			<xsl:copy-of select="GC_MOEDA_ALIQUOTA_MEDIA" />
			<xsl:copy-of select="CD_PAIS" />
			<xsl:copy-of select="NM_PAIS" />
		</CONSOLIDACAO>
	</ESPECIE>
	</xsl:if>
	</xsl:template>

</xsl:stylesheet>

