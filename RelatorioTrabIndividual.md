Métodos que passaram a propagar IOException (por classe)
(Foram propagados em cascata até às suas chamadas originais na Main)

CalculadoraOrcamento

Orcamento calcula(Projeto projeto)
Map<String, Double> getFuncionarioProduzEmHoras()
Map<String, Double> getFuncionariosProduzirEmUmaHora()
private static Map<String, Double> geraFuncionarioTempo() throws IOException{
Map<String, Double>gerarTempoFuncionarios()
Map<String, Double> getFileMapStringDouble(String path)
Projeto[] salvaOrcamentoPendenteAprovacao(Orcamento orcamento)
Projeto[] salvaOrcamentoOrcamentoRevisao(Orcamento orcamento)
Projeto[] salvaOrcamentoOrcamentoRevisao(Orcamento orcamento)
Projeto[] salvaOrcamentoAprovado(Orcamento orcamento)
Orcamento[] salvaOrcamento(Orcamento orcamento, SituacaoProjeto situacao)
Orcamento usaOrcamento(String dadoBusca)


GerenciadorCatalogo

Catalogo gerarCatalogo()
Funcionario[] identificarFuncionariosDisponiveis()
void vincularInsumoProjeto(Insumo insumo, Projeto projeto)
void vincularFuncionarioProjeto(Funcionario funcionario, Projeto projeto)
Catalogo atualizarCatalogo(Catalogo catalogo, Fornecedor fornecedorAlterado, Funcionario funcionarioAlterado)


GerenciadorCliente

Cliente getCliente()
Cliente cadastrar()
Cliente[] salvaCliente(Cliente cliente)
Cliente usaCliente(String dadoBusca)


GerenciadorProjeto

Projeto[] recuperaProjetos()
Projeto[] salvaProjetoEmEspera(Projeto projeto)
Projeto[] salvaProjetoPendenteAprovacao(Projeto projeto)
Projeto[] salvaProjetoOrcamentoRevisao(Projeto projeto)
Projeto[] salvaProjetoAprovado(Projeto projeto)
Projeto[] finalizaProjeto(Projeto projeto)
Projeto[] salvaProjeto(Projeto projeto, SituacaoProjeto situacao)


Projeto

void setFuncionarios(ArrayList<Funcionario> funcionarios)


Orcamento

void setFuncionarios(ArrayList<Funcionario> funcionarios)

Main

Orcamento validaProjetoGeraOrcamento (Catalogo catalogo, GerenciadorProjeto gerenciadorProjeto, Projeto projeto)



Item 2:

Foi criada a Interface Persistencia, com a assinatura dos métodos de persistência em bancos de dados.
