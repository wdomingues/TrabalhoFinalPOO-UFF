# Trabalho Final de POO - 2020.2
UFF - Universidade Federal Fluminense
IC - Instituto de Computação
Disciplina: Programação Orientada a Objetos - 2020.2
Professora: Vania De Oliveira Neves
Integrantes: Danilo Siqueira e Winne Domingues
Repositório: https://github.com/igorcordeiro08/TrabalhoFinal *(verificar nota ao final) 



# Relatório do Trabalho Final


## Descrição

Trata-se de um sistema de gerenciamento de orçamentos de uma construtora, a fim de receber pedidos de Clientes, entrar com as medidas do projeto e, considerando, dados de insumos necessários para 1 metro quadrado dos itens de construção padrão (Laje, Parede e Coluna), também os insumos  que cada fornecedor dispõe, é construída a edificação desejada em composições de classes através dos módulos gerenciadores, a fim de realizar o dimensionamento dos materiais, comparar os fornecedores, tomando o que tem menor custo e seja capaz de fornecer os materiais e, enfim, gerando o projeto e o orçamento.


## Atribuições:

**-Criação e Levantamento de Requisitos:** Danilo, Igor e Winne.
**-Modelagem e Diagramação do Projeto:** Danilo, Igor e Winne.
**-Classes de Domínio:** Danilo e Winne.
**-helper (Enums):** Danilo.
**-Helpers (métodos auxiliares):** Danilo e Winne.
**-Classes de service:**

    **-CalculadoraOrcamento, GerenciadorProjeto, ValidadorProjeto, GerenciadorCliente, GerenciadorCatalogo, GerenciadorContrato:** Danilo.
    **-GerenciadorAndar, GerenciadorEdificacao:** Winne.

**-mocks:** Danilo.
**-Apresentação:** Winne.
**-Revisão:** Danilo e Winne

Apesar das atribuições iniciais, houve jornadas de programação e revisões conjuntas.




## Itens de Java Utilizados


    - **Coleções utilizadas:** ArrayList, HashMap, Map


    - **Bibliotecas externas utilizadas:** Para a leitura dos JSONs utilizados para persistência de dados.
      com.google.gson.Gson



    - **Condicionais:** for, switch … case
    - **Interfaces:** PessoaFisica, PessoaJuridica, Interface Comparable
    - **Classes Abstratas:** Contrato, Pessoa
    - **Funções anônimas, Lambda expressions**
    - **Modificadores de de Acesso:** private, protected, public
    - **Interface Comparable e compareTo()** em Fornecedor
    - **BigDecimal:** para ter mais precisão
    - **Lançamento de exceções com throws**
    - **Tratamento de exceções**
    - **java.io.IOException**
    - **java.io.Reader**
    - **java.io.Writer**
    - **java.nio.file.Files**
    - **java.nio.file.Paths**
    - **Collectors**
    - **Sobrecarga de métodos**
    - **Sobrescrita de métodos**
    - **Polimorfismo**
    
    - JSON para armazenamento de instâncias de Clientes, Projetos, Funcionarios, Fornecedores e mocks de dados.
    
    - **Enum:** 
            - Enum_mocks 
            - NumerosOrdinais
            - SituacaoProjeto, para representar os possíveis estados do Projeto
        
    - Parâmetros no JSONS: 
        - funcionariosTempo: representando quanto tempo leva 1 funcionario para realizar cada tarefa de construção.
        - tempoFuncionarios: representando quantos funcionarios são necessarios para cada tarefa de construcao por unidade de tempo (hora).
        - metrosQuadrados:  representando o consumo de insumos para cada a execução de cada item da construção


    - **Helpers (métodos auxiliares criados - nomes bem descritivos):**
    ```java
    String getOrdinal(int i, boolean isMasculino)
    int validaInteiroPositivo()
    int minimoDiasPorHora(int horas)
    public static void clear() //limpa a tela de acordo com o S.O.
    Object[] getSliceOfArray(Object[] arr, int start, int end)
    ```



## Arquitetura da Interface com o usuário (CLI):


    Menu Principal:
        1 - Cadastrar Projeto
            Já existe Cliente Cadastrado?
                S:
                    busca por nome ou cpf
                        Cadastra o Projeto
                            //Criação da edificação
                            Quantos andares da primeira edificação?
                                //Criação do Andar
                                Quantos m² de laje (chão e teto)?
                                Quantos m² de paredes? 
                                Quantas colunas de Sustentação?
                            Se foi informado mais de um andar:
                                O próximo andar será igual à este?
                                    S: 
                                      cria um novo igual e adiciona ao projeto?
                                    N:
                                      repete os passos de criação do andar
                        Alguma Outra Edificação:
                            S: 
                              Repete passos de criação da edificação
                            N: 
                              Salva o projeto e volta ao Menu Principal
                    N:
                        Cadastra o Cliente
                        Cadastra o Projeto, conforme passos acima
        
        2 - Cadastrar Cliente
            Digite o nome do Cliente:
            Digite o CPF do Cliente: 
    
        3 - Selecionar Projeto
            Escolha a situação do(s) projeto(s) para exibi-los: 
              1 - Todos os Projetos
                    Selecione o projeto para avançar situação: 
                        Lista Projeto
                            Gera orçamento
              2 - Projeto aguardando liberação de insumos ou funcionários.
              3 - Projeto pendente de aprovação do orçamento para iniciar.
              4 - Orçamento esta sendo revisado.
              5 - Projeto iniciado.
              6 - Projeto finalizado.
              //Fluxos internos de 2 a 6 com resultados semelhantes ao do item 1, variando apenas o filtro por estado do projeto
        ENTER - Encerra   


                    
                    

*Obs: O repositório pertence ao ****Igor Cordeiro , que era integrante do grupo, contribuiu com a modelagem do Sistema, mas resolveu não continuar na disciplina neste momento.



