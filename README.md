# Paginação de Arquivos de Texto

Uma aplicação Java com interface gráfica (Swing) que permite dividir arquivos de texto grandes em partes menores, facilitando o manuseio e processamento de
grandes volumes de dados textuais.

## 📋 Funcionalidades

- **Interface Gráfica Intuitiva**: Interface amigável construída com Java Swing
- **Seleção de Arquivos**: Seletor de arquivos com filtro para arquivos .txt
- **Divisão Configurável**: Defina quantas linhas cada arquivo dividido deve conter
- **Seleção de Destino**: Escolha o diretório onde os arquivos divididos serão salvos
- **Feedback em Tempo Real**: Área de status que mostra o progresso da operação
- **Nomenclatura Automática**: Arquivos são automaticamente nomeados como "Arquivo_Paginado_X.txt"

## 🚀 Como Usar

### Pré-requisitos

- Java 8 ou superior
- JDK instalado para compilação

### Execução

1. **Compilar o projeto**:
```bash
javac -cp . javaricci/com/br/PaginacaoArquivoTextoApp.java
```

2. **Executar a aplicação**:
```bash
java javaricci.com.br.PaginacaoArquivoTextoApp
```

### Passo a Passo

1. **Selecione o Arquivo**: Clique em "Selecione Arquivo" e escolha o arquivo .txt que deseja dividir
2. **Defina as Linhas**: Insira no campo "Linhas por arquivo dividido" quantas linhas cada parte deve conter
3. **Escolha o Destino**: Clique em "Selecione Diretório Destino" e escolha onde salvar os arquivos divididos
4. **Execute a Divisão**: Clique em "Dividir Arquivo" para iniciar o processo
5. **Acompanhe o Status**: Observe a área de status na parte inferior para ver o progresso

## 🖥️ Interface da Aplicação

A aplicação possui uma interface organizada em três seções principais:

- **Painel Superior**: Controles para seleção de arquivo, configuração de linhas e diretório destino
- **Painel Central**: Botão principal para executar a divisão do arquivo
- **Painel Inferior**: Área de status/log que mostra informações sobre o processo

## 📁 Estrutura do Projeto

```
projeto/
├── javaricci/
│   └── com/
│       └── br/
│           └── PaginacaoArquivoTextoApp.java
└── README.md
```

## 🔧 Detalhes Técnicos

### Classe Principal: `PaginacaoArquivoTextoApp`

A aplicação é construída como uma extensão de `JFrame` e utiliza os seguintes componentes:

- **JTextField**: Para entrada de dados (arquivo, linhas, diretório)
- **JButton**: Para ações (selecionar arquivo, diretório, dividir)
- **JTextArea**: Para exibir status e logs
- **JFileChooser**: Para seleção de arquivos e diretórios

### Métodos Principais

- `splitFile()`: Método principal que realiza a divisão do arquivo
- `writePartFile()`: Escreve cada parte dividida em um arquivo separado
- `SelectFileAction`: Action listener para seleção de arquivo
- `SelectDestDirAction`: Action listener para seleção de diretório
- `SplitFileAction`: Action listener para execução da divisão

### Tratamento de Erros

A aplicação inclui tratamento para:
- Arquivos não selecionados
- Diretórios não selecionados
- Números inválidos de linhas
- Erros de I/O durante a leitura/escrita

## 📝 Exemplo de Uso

Suponha que você tenha um arquivo `dados.txt` com 10.000 linhas e queira dividi-lo em arquivos de 1.000 linhas cada:

1. Selecione o arquivo `dados.txt`
2. Digite `1000` no campo "Linhas por arquivo dividido"
3. Selecione o diretório destino
4. Clique em "Dividir Arquivo"

**Resultado**: 10 arquivos serão criados:
- `Arquivo_Paginado_1.txt` (linhas 1-1000)
- `Arquivo_Paginado_2.txt` (linhas 1001-2000)
- ... e assim por diante

## ⚠️ Limitações

- Funciona apenas com arquivos de texto (.txt)
- Carrega o arquivo inteiro na memória durante o processamento
- Para arquivos muito grandes, pode ser necessário aumentar a memória da JVM

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para:

- Reportar bugs
- Sugerir melhorias
- Enviar pull requests
- Melhorar a documentação

## 📄 Licença

Este projeto está disponível sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 👨‍💻 Autor

**Javaricci**
- Package: `javaricci.com.br`

## 🔄 Versão

- **Versão Atual**: 1.0
- **Compatibilidade**: Java 8+
- **Tipo**: Aplicação Desktop (Swing)

---

*Desenvolvido com ❤️ em Java*
