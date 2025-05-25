Objetivo é criar um agente que possa adicionar ou atualizar dados em um arquivo DBK de declaração do Imposto de Renda Pessoa Física (IRPF) 2025. O agente deve ser capaz de interpretar e modificar o arquivo DBK, o agente poderá navegar pelo repositorio para buscar documentação ou na internet também. Os arquivos dbk's deverão ser salvos na pasta dbks ondem contém as sub-pastas `gerado` e `original`. O agente não deve solicitar ao usuário que faça a declaração manualmente, mas sim realizar as alterações necessárias diretamente no arquivo DBK. Para isso, o agente irá ler o arquivo original e usar arquivos PDFs ou imagens exportados por bancos, instituições financeiras, e etc, esses arquivos estarão localizados na pasta `informes`. O objetivo final é facilitar o processo de declaração do Imposto de Renda para o usuário, tornando-o mais rápido e menos propenso a erros.

A pasta `llm-aux-docs` contém conteudo auxiliar para auxiliar na criação do agente:
- A pasta `IRPF-master` contendo o código fonte de um programa open-source feito em C# que tenta fazer a engenharia reversa do programa de declaração do imposto de renda da receita federal do Brasil. O código fonte é bem documentado e pode ser útil para entender a estrutura do arquivo DBK, apesar de ser um pouco desatualizado pois não é referente a versão 2025. Contém bastante documentação também em markdown.
- A pasta `leiautes` contendo os leiautes dos arquivos de declaração do imposto de renda da receita federal do Brasil. Os leiautes são bem documentados e podem ser úteis para entender a estrutura do arquivo DBK, apesar da versão 2025 (`ir-2025.pdf`) não ser muito completa, a versão de 2023 (`ir-2023.pdf`) é a mais completa. As documentações são relativas ao formato .DEC mas o formato .DBK é semelhante, com algumas diferenças.
- Além disso, tem o arquivo `algoritmo_checksum.md` que contém o algoritmo de checksum utilizado no arquivo DBK, o algoritmo é bem documentado e pode ser útil para entender como funciona o checksum do arquivo DBK.

Gostaria de usar o langchain e uv para gestao de dependencias. O arquivo pyproject.toml ja tem alguma extrutura. Além disso, o código já tem uma estrutura inicial na pasta `agent`, nela tem a pasta `tools` que esta inicialmente so com o `__init__.py`, a pasta `utils` que tem o código python checksum.py que implementa o algoritmo de checksum, e a o arquivo `agent.py` que está inicialmente vazio.

Na pasta principal tem o `main.py` que é o ponto de entrada do programa mas também está vazio. O arquivo `pyproject.toml` tem as dependências iniciais do projeto, onde eu gostaria de usar o langchain e uv.

Para os modelos, gostaria de usar o modelo gemini 2.5 pro e o claude sonnet 4 hospedado na Vertex AI, considere que esses modelos já foram lançados e estão disponíveis. O modelo gemini 2.5 pro é um modelo de linguagem grande (LLM) desenvolvido pelo Google DeepMind, enquanto o Claude Sonnet 4 é um modelo de linguagem desenvolvido pela Anthropic.

Tem abaixo um exemplo de código.

Por favor planeje a implementação, adicionando uma lista de TO-DO's, adicionando uma lista de to-dos e considerando que eu estou usando o GitHub Copilot para realizar a implementação. Crie o arquivo `.github/copilot-instructions.md` com as instruções para o Copilot, e crie um arquivo `README.md` com as instruções de uso do projeto. O código deve ser bem documentado e seguir as boas práticas de programação. 

-- `main.py`:
```python

import os
from dotenv import load_dotenv
from agents.data_analyst_agent import get_agent_instance

# Load environment variables
load_dotenv()

def main():
    """Main entry point for the AI Data Analyst Bot."""
    print("Starting AI Data Analyst Bot...")
    print("=" * 50)
    
    # Set up agent
    agent = get_agent_instance()
    
    if not agent:
        print("Agent setup failed. Check your API keys and try again.")
        return
    
    print("Agent setup complete!")
    print("Type 'quit', 'exit', or 'bye' to end the conversation.")
    print("Type 'clear' to clear conversation history.")
    print("Type 'help' for available commands.")
    print("=" * 50)
    
    # Interactive conversation loop
    while True:
        try:
            # Get user input
            user_input = input("\nYou: ").strip()
            
            # Check for exit commands
            if user_input.lower() in ['quit', 'exit', 'bye', 'q']:
                print("\nGoodbye! Happy analyzing! 📊")
                break
            
            # Check for clear command
            if user_input.lower() == 'clear':
                agent.clear_history()
                print("\nConversation history cleared! 🧹")
                continue
            
            # Check for help command
            if user_input.lower() == 'help':
                print("\nAvailable commands:")
                print("  quit, exit, bye, q - End the conversation")
                print("  clear - Clear conversation history")
                print("  help - Show this help message")
                print("\nYou can ask questions about your data, and I'll remember our conversation context!")
                continue
            
            # Skip empty input
            if not user_input:
                continue
            
            # Get agent response
            print("\nAnalyst: ", end="")
            response = agent.ask(user_input)
            print(response.get('output', 'Sorry, I had trouble processing that request.'))
            
        except KeyboardInterrupt:
            print("\n\nGoodbye! Happy analyzing! 📊")
            break
        except Exception as e:
            print(f"\nError: {e}")
            print("Please try again or type 'quit' to exit.")

if __name__ == "__main__":
    main()
```
-- `agents/data_analyst_agent.py`:
```python
"""
AI Data Analyst Agent implementation using LangChain.
"""
from typing import List, Dict, Any, Optional
from langchain.agents import create_react_agent, AgentExecutor
from langchain.tools import BaseTool
from langchain_core.prompts import PromptTemplate
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_google_vertexai.model_garden import ChatAnthropicVertex
from langchain.memory import ConversationBufferWindowMemory
from langchain_core.messages import HumanMessage, AIMessage
import os
from tools.bigquery_tool import BigQueryTool

class DataAnalystAgent:
    """
    AI Data Analyst Agent powered by Google Gemini 1.5 Pro or Claude Sonnet 3.5.
    Uses LangChain for tool orchestration and reasoning.
    """
    def __init__(
        self, 
        tools: List[BaseTool] = None,
        verbose: bool = True
    ):
        """
        Initialize the Data Analyst Agent.
        
        Args:
            tools: List of LangChain tools to use
            verbose: Whether to print agent's thought process        """
        self.tools = tools or []
        self.verbose = verbose
        self.conversation_history = []
        self.agent_executor = self._setup_agent()
        
    def _setup_agent(self) -> Optional[AgentExecutor]:
        """Set up the agent with the appropriate LLM."""        # Define ReAct prompt template with conversation history context
        template = '''You are an AI Data Analyst helping users understand their data.
You have access to various data sources including BigQuery, dbt documentation, 
Google Sheets, and Notion. Always follow these rules:

1. Safety First
   - Never execute dangerous SQL operations (DROP, DELETE, UPDATE, INSERT, CREATE)
   - Always validate queries before execution
   - Respect data privacy and access controls

2. Business-Focused Communication
   - Use business language, not technical jargon
   - Provide context for all data insights
   - Ask clarifying questions when requests are ambiguous
   - Suggest next steps and related analyses

3. Data Accuracy
   - Verify data sources and freshness
   - Highlight data limitations or caveats
   - Document assumptions made during analysis

4. Conversation Context
   - Remember previous discussions and tables mentioned
   - Use context from earlier questions to provide better answers
   - If referring to "this table" or similar, use the most recently discussed table/dataset

CONVERSATION HISTORY:
{chat_history}

IMPORTANT: For general questions, greetings, or requests for information about your capabilities, 
respond directly with "Final Answer:" without using any tools or actions.

You have access to the following tools:

{tools}

Use the following format EXACTLY - do not deviate from this format:

Question: the input question you must answer
Thought: you should always think about what to do
Action: the action to take, should be one of [{tool_names}]
Action Input: the input to the action (as a JSON string)
Observation: the result of the action
... (this Thought/Action/Action Input/Observation can repeat N times)
Thought: I now know the final answer
Final Answer: the final answer to the original input question

CRITICAL FORMATTING RULES:
1. For Action Input, always use proper JSON format like this:
   Action Input: {{"operation": "get_table_schema", "dataset_id": "my_dataset", "table_id": "my_table"}}
2. After getting an Observation with the data you need, ALWAYS write:
   Thought: I now know the final answer
   Final Answer: [your complete answer]
3. Do NOT try to continue the conversation after Final Answer

Examples of correct format:
- Action Input: {{"operation": "list_datasets"}}
- Action Input: {{"operation": "get_table_schema", "dataset_id": "silver__army", "table_id": "applicants"}}
- Action Input: {{"operation": "execute_query", "sql_query": "SELECT MAX(date_column) FROM dataset.table"}}

If no tools are needed for simple questions or greetings, use this format:

Question: the input question you must answer
Thought: This is a general question that doesn't require data access
Final Answer: [your response]

Begin!

Question: {input}
Thought:{agent_scratchpad}'''
        
        prompt = PromptTemplate.from_template(template)
        
        try:
            # Try to set up Gemini (primary model)
            if os.environ.get("GOOGLE_API_KEY"):
                print("Setting up Gemini 2.5 Pro model...")
                llm = ChatGoogleGenerativeAI(
                    model="gemini-2.5-pro-preview-05-06",
                    temperature=0.1,
                    max_output_tokens=2000,
                    google_api_key=os.environ.get("GOOGLE_API_KEY")
                )
            # If Gemini fails, try Claude through Vertex AI
            elif os.environ.get("GOOGLE_CLOUD_PROJECT"):
                print("Gemini not available, setting up Claude Sonnet 4 on Vertex AI...")
                llm = ChatAnthropicVertex(
                    model="claude-sonnet-4@20250514",
                    temperature=0.1,
                    max_output_tokens=2000,
                    project=os.environ.get("GOOGLE_CLOUD_PROJECT"),
                    location=os.environ.get("GOOGLE_CLOUD_LOCATION", "us-central1")
                )
            else:
                raise ValueError("No API keys found for Gemini or Claude on Vertex AI. Please set GOOGLE_API_KEY or GOOGLE_CLOUD_PROJECT.")
            
            # Create agent
            agent = create_react_agent(
                llm=llm,
                tools=self.tools,
                prompt=prompt
            )            # Create agent executor with better error handling
            return AgentExecutor(
                agent=agent,
                tools=self.tools,
                verbose=self.verbose,
                max_iterations=5,
                handle_parsing_errors="Check your output and make sure it conforms to the expected format! When you have the information needed to answer the question, provide your final answer in this format:\n\nThought: I now know the final answer\nFinal Answer: [your complete answer here]",
                return_intermediate_steps=True
            )

        except Exception as e:
            print(f"Error setting up agent: {e}")
            return None

    def ask(self, query: str) -> Dict[str, Any]:
        """
        Ask the agent a question and get a response.
        
        Args:
            query: The user's query string
            
        Returns:
            Dict containing the agent's response and other metadata        """
        if not self.agent_executor:
            return {"output": "Agent not properly initialized. Check API keys and try again."}

        try:
            # Format conversation history for the prompt
            chat_history = self._format_conversation_history()
            
            # Create the input with conversation history
            agent_input = {
                "input": query,
                "chat_history": chat_history
            }
            
            if self.verbose:
                print(f"Processing query: {query}")
                if chat_history != "No previous conversation.":
                    print(f"Using conversation history: {len(self.conversation_history)} previous interactions")
            
            result = self.agent_executor.invoke(agent_input)
            
            # Extract the final output - handle cases where agent might not reach Final Answer
            output = result.get("output", "")
            if not output and "intermediate_steps" in result:
                # If no final output but we have intermediate steps, try to extract useful information
                steps = result["intermediate_steps"]
                if steps:
                    last_step = steps[-1]
                    if len(last_step) > 1:
                        # Use the last observation as the output
                        output = f"Based on the query results: {last_step[1]}"
            
            if not output:
                output = "I was unable to process your request. Please try rephrasing your question."
            
            # Store this interaction in conversation history
            self.conversation_history.append({
                "human": query,
                "ai": output
            })
            
            # Keep only the last 10 interactions to prevent context from getting too long
            if len(self.conversation_history) > 10:
                self.conversation_history = self.conversation_history[-10:]
            
            return {"output": output}
        except Exception as e:
            error_msg = f"Error processing query: {str(e)}"
            if self.verbose:
                print(f"Agent error: {error_msg}")
            return {"output": error_msg}

    def _format_conversation_history(self) -> str:
        """
        Format the conversation history for inclusion in the prompt.
        
        Returns:
            Formatted conversation history string
        """
        if not self.conversation_history:
            return "No previous conversation."
        
        formatted_history = []
        for interaction in self.conversation_history[-5:]:  # Show last 5 interactions
            formatted_history.append(f"Human: {interaction['human']}")
            formatted_history.append(f"Assistant: {interaction['ai']}")
        
        return "\n".join(formatted_history)

    def clear_history(self):
        """Clear the conversation history."""
        self.conversation_history = []


def get_agent_instance(tools: List[BaseTool] = None) -> DataAnalystAgent:
    """
    Get a singleton instance of the Data Analyst Agent.
    
    Args:
        tools: List of LangChain tools to use
        
    Returns:
        DataAnalystAgent instance
    """
    # Initialize with default tools if none provided
    if tools is None:
        tools = [BigQueryTool()]
    
    return DataAnalystAgent(tools=tools)
```

-- `agents/tools/bigquery_tool.py`:
```python
"""
BigQuery Tool for LangChain agent integration.
"""
from typing import Optional
from langchain.tools import BaseTool
from pydantic import Field
import os
import json
from enum import Enum

class BigQueryOperation(str, Enum):
    """Available BigQuery operations."""
    LIST_DATASETS = "list_datasets"
    LIST_TABLES = "list_tables"
    GET_TABLE_SCHEMA = "get_table_schema"
    EXECUTE_QUERY = "execute_query"

class BigQueryTool(BaseTool):
    """
    Tool for interacting with Google BigQuery.
    Provides comprehensive data exploration and analysis capabilities.
    """
    
    name: str = "bigquery_tool"
    description: str = """Comprehensive BigQuery tool for data exploration and analysis.
    
    Available operations:
    1. list_datasets - List all datasets in the project
    2. list_tables - List all tables in a specific dataset (basic info only)
    3. get_table_schema - Get detailed schema and metadata for a specific table
    4. execute_query - Execute SQL queries (SELECT only for safety)
    
    Examples:
    - To list datasets: {"operation": "list_datasets"}
    - To list tables: {"operation": "list_tables", "dataset_id": "my_dataset"}
    - To get table info: {"operation": "get_table_schema", "dataset_id": "my_dataset", "table_id": "my_table"}
    - To run query: {"operation": "execute_query", "sql_query": "SELECT * FROM dataset.table LIMIT 10"}
    
    Important notes:
    - list_tables shows basic table information only (for performance)
    - Use get_table_schema for detailed information including row counts, descriptions, and schema
    - Only SELECT, WITH, and DESCRIBE statements are allowed for security
    - All queries are validated before execution
    - Results are limited to prevent large data transfers
    """
    
    # Declare Pydantic fields for the client and project_id
    client: Optional[object] = Field(default=None, exclude=True)
    project_id: Optional[str] = Field(default=None, exclude=True)
    
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self._initialize_client()

    def _initialize_client(self):
        """Initialize BigQuery client if credentials are available."""
        try:
            from google.cloud import bigquery
            
            # Try to get project ID from environment
            project_id = os.environ.get("GOOGLE_CLOUD_PROJECT")
            
            if project_id:
                object.__setattr__(self, 'project_id', project_id)
                object.__setattr__(self, 'client', bigquery.Client(project=project_id))
                print(f"BigQuery client initialized for project: {project_id}")
            else:
                print("Warning: GOOGLE_CLOUD_PROJECT not set. BigQuery tool will use mock data.")
                object.__setattr__(self, 'project_id', None)
                object.__setattr__(self, 'client', None)
                
        except ImportError:
            print("Warning: google-cloud-bigquery not installed. BigQuery tool will use mock data.")
            object.__setattr__(self, 'project_id', None)
            object.__setattr__(self, 'client', None)
        except Exception as e:
            print(f"Warning: Could not initialize BigQuery client: {e}. Using mock data.")
            object.__setattr__(self, 'project_id', None)
            object.__setattr__(self, 'client', None)
    
    def _run(self, query: str) -> str:
        """Execute the specified BigQuery operation."""
        try:
            # Parse JSON input from LangChain agents
            try:
                input_data = json.loads(query)
                operation = input_data.get('operation')
                dataset_id = input_data.get('dataset_id')
                table_id = input_data.get('table_id')
                sql_query = input_data.get('sql_query')
                limit = input_data.get('limit', 100)
                dry_run = input_data.get('dry_run', False)
            except json.JSONDecodeError:
                # If it's not JSON, treat as operation string
                operation = query
                dataset_id = None
                table_id = None
                sql_query = None
                limit = 100
                dry_run = False
            
            # Convert string operation to enum if needed
            if isinstance(operation, str):
                try:
                    operation = BigQueryOperation(operation)
                except ValueError:
                    return f"Error: Unknown operation '{operation}'. Valid operations: list_datasets, list_tables, get_table_schema, execute_query"
            
            if operation == BigQueryOperation.LIST_DATASETS:
                return self._list_datasets()
            elif operation == BigQueryOperation.LIST_TABLES:
                if not dataset_id:
                    return "Error: dataset_id is required for list_tables operation"
                return self._list_tables(dataset_id)
            elif operation == BigQueryOperation.GET_TABLE_SCHEMA:
                if not dataset_id or not table_id:
                    return "Error: Both dataset_id and table_id are required for get_table_schema operation"
                return self._get_table_schema(dataset_id, table_id)
            elif operation == BigQueryOperation.EXECUTE_QUERY:
                if not sql_query:
                    return "Error: sql_query is required for execute_query operation"
                return self._execute_query(sql_query, limit, dry_run)
            else:
                return f"Error: Unknown operation '{operation}'"
                
        except Exception as e:
            return f"Error executing BigQuery operation: {str(e)}"

    def _list_datasets(self) -> str:
        """List all datasets in the project."""
        if not self.client:
            return self._mock_list_datasets()
        
        try:
            datasets = list(self.client.list_datasets())
            if not datasets:
                return f"No datasets found in project {self.project_id}"
            
            result = f"Datasets in project {self.project_id}:\n"
            for dataset in datasets:
                result += f"- {dataset.dataset_id}\n"
                if hasattr(dataset, 'friendly_name') and dataset.friendly_name:
                    result += f"  Name: {dataset.friendly_name}\n"
                if hasattr(dataset, 'labels') and dataset.labels:
                    result += f"  Labels: {dataset.labels}\n"
                result += f"  Project: {dataset.project}\n\n"            
            return result
        except Exception as e:
            return f"Error listing datasets: {str(e)}"
    
    def _list_tables(self, dataset_id: str) -> str:
        """List all tables in a dataset."""
        if not self.client:
            return self._mock_list_tables(dataset_id)
        
        try:
            dataset_ref = self.client.dataset(dataset_id)
            table_list_items = list(self.client.list_tables(dataset_ref))
            
            if not table_list_items:
                return f"No tables found in dataset {dataset_id}"
            
            result = f"Tables in dataset {dataset_id}:\n"
            
            # For better performance, we'll show basic info from TableListItem
            # and note that users can use get_table_schema for detailed info
            for table_item in table_list_items:
                result += f"- {table_item.table_id}"
                if hasattr(table_item, 'table_type') and table_item.table_type:
                    result += f" ({table_item.table_type})"
                result += "\n"
                
                if hasattr(table_item, 'friendly_name') and table_item.friendly_name:
                    result += f"  Name: {table_item.friendly_name}\n"
                    
                if hasattr(table_item, 'created') and table_item.created:
                    result += f"  Created: {table_item.created}\n"
                    
                if hasattr(table_item, 'labels') and table_item.labels:
                    result += f"  Labels: {table_item.labels}\n"
                    
                result += "\n"
            
            result += "\nNote: For detailed table information including row counts, schema, and descriptions, "
            result += "use the get_table_schema operation with specific table names."
            
            return result
        except Exception as e:
            return f"Error listing tables in dataset {dataset_id}: {str(e)}"
    
    def _get_table_schema(self, dataset_id: str, table_id: str) -> str:
        """Get detailed schema and metadata for a table."""
        if not self.client:
            return self._mock_get_table_schema(dataset_id, table_id)
        
        try:
            table_ref = self.client.dataset(dataset_id).table(table_id)
            table = self.client.get_table(table_ref)
            
            result = f"Table: {dataset_id}.{table_id}\n"
            result += f"Type: {table.table_type}\n"
            result += f"Description: {table.description or 'No description'}\n"
            result += f"Created: {table.created}\n"
            result += f"Modified: {table.modified}\n"
            result += f"Rows: {table.num_rows:,}\n"
            result += f"Size: {table.num_bytes:,} bytes\n"
            
            if table.labels:
                result += f"Labels: {table.labels}\n"
            
            if table.table_type == "VIEW":
                result += f"View SQL:\n{table.view_query}\n"
            
            result += "\nSchema:\n"
            for field in table.schema:
                result += f"- {field.name}: {field.field_type}"
                if field.mode != "NULLABLE":
                    result += f" ({field.mode})"
                if field.description:
                    result += f" - {field.description}"
                result += "\n"
            
            return result
        except Exception as e:
            return f"Error getting table schema for {dataset_id}.{table_id}: {str(e)}"
    
    def _execute_query(self, sql_query: str, limit: int, dry_run: bool) -> str:
        """Execute a SQL query."""
        # Validate query safety
        query_upper = sql_query.upper().strip()
        allowed_keywords = ["SELECT", "WITH", "DESCRIBE", "SHOW"]
        dangerous_keywords = ["DROP", "DELETE", "UPDATE", "INSERT", "CREATE", "ALTER", "TRUNCATE"]
        
        if not any(query_upper.startswith(keyword) for keyword in allowed_keywords):
            return "Error: Only SELECT, WITH, DESCRIBE, and SHOW statements are allowed for security reasons."
        
        if any(keyword in query_upper for keyword in dangerous_keywords):
            return f"Error: Query contains dangerous keywords. Only read-only operations are allowed."
        
        if not self.client:
            return self._mock_execute_query(sql_query, limit, dry_run)
        
        try:
            job_config = None
            if dry_run:
                from google.cloud import bigquery
                job_config = bigquery.QueryJobConfig(dry_run=True, use_query_cache=False)
            
            query_job = self.client.query(sql_query, job_config=job_config)
            
            if dry_run:
                return f"Query validation successful. Estimated bytes processed: {query_job.total_bytes_processed:,}"
            
            # Execute query and get results
            results = query_job.result(max_results=limit)
            
            if query_job.total_bytes_processed:
                cost_estimate = (query_job.total_bytes_processed / (1024**4)) * 5  # Rough estimate: $5 per TB
                cost_msg = f"Query processed {query_job.total_bytes_processed:,} bytes. Estimated cost: ${cost_estimate:.4f}\n\n"
            else:
                cost_msg = ""
            
            # Format results
            rows = list(results)
            if not rows:
                return cost_msg + "Query executed successfully but returned no results."
            
            # Get column names
            if hasattr(results, 'schema'):
                columns = [field.name for field in results.schema]
            else:
                columns = list(rows[0].keys()) if rows else []
            
            result = cost_msg + f"\nQuery Results ({len(rows)} rows):\n"
            
            # Create table header
            header = " | ".join(columns)
            separator = " | ".join("-" * len(col) for col in columns)
            result += f"{header}\n{separator}\n"
            
            # Add rows
            for row in rows:
                row_values = []
                for col in columns:
                    value = row[col] if col in row else row[columns.index(col)]
                    if value is None:
                        row_values.append("NULL")
                    else:
                        row_values.append(str(value))
                result += " | ".join(row_values) + "\n"
            
            if len(rows) >= limit:
                result += f"\n(Results limited to {limit} rows)"
            
            return result
            
        except Exception as e:
            return f"Error executing query: {str(e)}"
    
    # Mock methods for when BigQuery client is not available
    def _mock_list_datasets(self) -> str:
        return """Mock Datasets (BigQuery client not available):
- ecommerce_data
  Description: E-commerce transaction and customer data
  Created: 2024-01-15
  Modified: 2025-05-20

- marketing_analytics  
  Description: Marketing campaign performance data
  Created: 2024-03-10
  Modified: 2025-05-22

- financial_reports
  Description: Financial metrics and reporting data
  Created: 2024-02-01
  Modified: 2025-05-15"""
    
    def _mock_list_tables(self, dataset_id: str) -> str:
        if dataset_id == "ecommerce_data":
            return """Mock Tables in ecommerce_data:
- orders (TABLE)
  Description: Customer orders and transactions
  Created: 2024-01-15
  Modified: 2025-05-20
  Rows: 1,250,000

- customers (TABLE)
  Description: Customer information and profiles
  Created: 2024-01-15
  Modified: 2025-05-18
  Rows: 85,000

- products (TABLE)
  Description: Product catalog and inventory
  Created: 2024-01-15
  Modified: 2025-05-10
  Rows: 15,000

- order_items (TABLE)
  Description: Individual items within orders
  Created: 2024-01-15
  Modified: 2025-05-20
  Rows: 3,500,000"""
        else:
            return f"Mock Tables in {dataset_id}:\n- sample_table (TABLE)\n  Description: Sample data table\n  Rows: 100,000"
    
    def _mock_get_table_schema(self, dataset_id: str, table_id: str) -> str:
        if dataset_id == "ecommerce_data" and table_id == "orders":
            return """Table: ecommerce_data.orders
Type: TABLE
Description: Customer orders and transactions
Created: 2024-01-15
Modified: 2025-05-20
Rows: 1,250,000
Size: 450,000,000 bytes

Schema:
- order_id: STRING (REQUIRED) - Unique order identifier
- customer_id: STRING (REQUIRED) - Customer identifier
- order_date: DATE (REQUIRED) - Date when order was placed
- order_status: STRING - Current status of the order
- total_amount: NUMERIC - Total order amount in USD
- payment_method: STRING - Payment method used
- created_at: TIMESTAMP (REQUIRED) - Record creation timestamp
- updated_at: TIMESTAMP - Record last update timestamp"""
        else:
            return f"""Table: {dataset_id}.{table_id}
Type: TABLE
Description: Mock table
Created: 2024-01-01
Modified: 2025-05-20
Rows: 100,000
Size: 10,000,000 bytes

Schema:
- id: STRING (REQUIRED) - Primary key
- name: STRING - Record name
- value: NUMERIC - Numeric value
- created_at: TIMESTAMP (REQUIRED) - Creation timestamp"""
    
    def _mock_execute_query(self, sql_query: str, limit: int, dry_run: bool) -> str:
        if dry_run:
            return f"Mock validation successful. Query: {sql_query[:50]}... Estimated bytes: 1,024,000"
        
        return f"""Mock Query Results (BigQuery client not available):
Query: {sql_query[:100]}...

order_id | customer_id | order_date | total_amount | order_status
---------|-------------|------------|--------------|-------------
ORD001   | CUST123     | 2025-05-20 | 249.99       | completed
ORD002   | CUST456     | 2025-05-20 | 89.50        | processing  
ORD003   | CUST789     | 2025-05-19 | 159.99       | shipped

(Mock results - 3 rows returned, limited to {limit})
Estimated cost: $0.01"""
    
    async def _arun(self, query: str) -> str:
        """Async version of _run."""
        return self._run(query)

```