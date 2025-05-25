#!/usr/bin/env python3
"""
Teste para demonstrar que o agente agora extrai automaticamente
CPF e ano calendário do DBK e passa para a ferramenta LLM PDF.
"""

import os
import sys
from pathlib import Path

# Add the project root to the Python path
project_root = Path(__file__).parent
sys.path.insert(0, str(project_root))

from agent.agent import IRPFAgent

def test_dbk_context_extraction():
    """Testa se o agente extrai corretamente o contexto do DBK."""
    
    # Inicializar o agente
    agent = IRPFAgent()
    
    # Verificar se há arquivos DBK disponíveis
    dbk_files = list(Path("dbks").rglob("*.DBK"))
    if not dbk_files:
        print("❌ Nenhum arquivo DBK encontrado para teste")
        return False
    
    # Usar o primeiro arquivo DBK encontrado
    dbk_file = str(dbk_files[0])
    print(f"📁 Usando arquivo DBK: {dbk_file}")
    
    # Definir o arquivo DBK atual
    agent.set_current_dbk_file(dbk_file)
    
    # Verificar se as informações foram extraídas
    if agent.current_dbk_info:
        cpf = agent.current_dbk_info.get('cpf_declarante', '')
        ano = agent.current_dbk_info.get('ano_calendario', '')
        
        print(f"✅ CPF extraído: {cpf}")
        print(f"✅ Ano calendário extraído: {ano}")
        
        if cpf and ano:
            print("✅ SUCESSO: Informações do DBK extraídas corretamente!")
            print("\nAhora quando o agente processar um informe do Mercado Pago,")
            print("ele automaticamente usará essas informações sem pedir ao usuário.")
            return True
        else:
            print("❌ ERRO: CPF ou ano calendário não foram extraídos")
            return False
    else:
        print("❌ ERRO: Não foi possível extrair informações do DBK")
        return False

def test_enhanced_query():
    """Testa se o contexto é incluído na query."""
    
    agent = IRPFAgent()
    
    # Simular um DBK carregado
    agent.current_dbk_file = "dbks/exemplo.DBK"
    agent.current_dbk_info = {
        'cpf_declarante': '15499258732',
        'ano_calendario': '2025',
        'ano_exercicio': '2024'
    }
    
    # Testar o enhancement da query
    original_query = "Processar informe do Mercado Pago"
    enhanced_query = agent._enhance_query_with_context(original_query)
    
    print("🔍 Query original:")
    print(original_query)
    print("\n🚀 Query aprimorada com contexto:")
    print(enhanced_query)
    
    # Verificar se as informações estão incluídas
    if '15499258732' in enhanced_query and '2025' in enhanced_query:
        print("\n✅ SUCESSO: Query foi aprimorada com informações do DBK!")
        return True
    else:
        print("\n❌ ERRO: Query não foi aprimorada corretamente")
        return False

if __name__ == "__main__":
    print("🧪 Testando extração automática de contexto do DBK\n")
    
    print("=" * 60)
    print("TESTE 1: Extração de informações do DBK")
    print("=" * 60)
    test1_passed = test_dbk_context_extraction()
    
    print("\n" + "=" * 60)
    print("TESTE 2: Enhancement da query com contexto")
    print("=" * 60)
    test2_passed = test_enhanced_query()
    
    print("\n" + "=" * 60)
    print("RESULTADO FINAL")
    print("=" * 60)
    
    if test1_passed and test2_passed:
        print("🎉 TODOS OS TESTES PASSARAM!")
        print("\nO problema foi RESOLVIDO:")
        print("- O agente agora extrai automaticamente CPF e ano calendário do DBK")
        print("- A LLM não precisa mais pedir essas informações ao usuário")
        print("- O contexto é automaticamente incluído nas queries")
    else:
        print("❌ Alguns testes falharam. Verifique a implementação.")
    
    print(f"\nResultados: Teste 1: {'✅' if test1_passed else '❌'} | Teste 2: {'✅' if test2_passed else '❌'}")
