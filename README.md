# Automação de Testes de API - Dog API

## Visão Geral

Este projeto consiste em um framework de automação de testes de API desenvolvido para validar os endpoints da Dog API.

O objetivo é garantir a qualidade da API por meio da validação de:

* Comportamento funcional
* Estrutura das respostas
* Regras de negócio
* Tratamento de erros

O projeto foi desenvolvido seguindo boas práticas de automação, priorizando legibilidade, manutenibilidade e escalabilidade.

---

## Tecnologias Utilizadas

* Java 17
* Maven
* RestAssured
* JUnit 5
* Allure Report
* JSON Schema Validator

---

## Estrutura do Projeto

```text
src
├── main
│   └── java
│       ├── client          # Camada de acesso à API (encapsula endpoints)
│       ├── config          # Gerenciamento de configuração (ConfigManager)
│       └── specifications  # Request e Response specifications reutilizáveis
│
├── test
│   ├── java
│   │   ├── contract        # Testes de contrato (validação de schema)
│   │   ├── functional      # Testes funcionais da API
│   │   └── hooks           # Setup base dos testes
│   │
│   └── resources
│       ├── config          # Arquivos de configuração por ambiente
│       └── schemas         # JSON Schemas para validação de contrato
```

---

## Configuração

O projeto suporta execução por ambiente.

Exemplo de arquivo:

```text
config/prod-data.properties
```

```properties
base.url=https://dog.ceo/api
```

A execução é controlada via parâmetro:

```bash
-Denv=prod
```

---

## Execução dos Testes

### Executar todos os testes

```bash
mvn clean test -Denv=prod
```

### Executar por grupo (tags)

```bash
mvn test -Dgroups=functional -Denv=prod
mvn test -Dgroups=contract -Denv=prod
```
## Execução via GitHub Actions

O projeto possui uma pipeline manual no GitHub Actions para facilitar a execução dos testes.

### Como executar
1. Acesse a aba **Actions** no GitHub
2. Selecione a workflow **API Tests**
3. Clique em **Run workflow**
4. Escolha:
    - ambiente (`dev`, `stage` ou `prod`)
    - grupo de testes (`functional` ou `contract`)

### Artefato gerado
Ao final da execução, dois artefatos ficam disponíveis para download:

allure-report
allure-results
Como visualizar o relatório
- Opção 1: usar o allure-report

Após baixar e descompactar o artefato allure-report, execute um servidor local simples na pasta do relatório.

Exemplo com Python:

cd allure-report
python3 -m http.server 8080

Depois acesse no navegador:
http://localhost:8080

- Opção 2: usar o allure-results

Caso prefira gerar o relatório localmente, baixe o artefato allure-results e execute:

allure serve allure-results(Mais detalhes na próxima sessão -> **Relatórios(Execução Local)**)
Ao final da execução, o artefato `allure-report` ficará disponível para download.

Após baixar e descompactar, abra o arquivo `index.html` para visualizar o relatório.

---

## Relatórios(Execução Local)

O projeto utiliza Allure para geração de relatórios.

1 - Instalar o Allure CLI

No Mac/Linux com Homebrew:
```
brew install allure
```
No Linux:
```
sudo apt install allure
```
Ou baixar:
https://docs.qameta.io/allure/

2 - Gerar o report
```bash
allure serve target/allure-results
```

---

## Cobertura de Testes

### Testes Funcionais

Endpoints cobertos:

* `GET /breeds/list/all`
* `GET /breed/{breed}/images`
* `GET /breeds/image/random`

#### Cenários validados

**Breeds List**

* Retorno com sucesso
* Sub-raças representadas como listas

**Breed Images**

* Retorno de imagens para raça válida
* Tratamento de erro para raça inválida (404)

**Random Image**

* Retorno com sucesso
* URL válida no payload

---

### Testes de Contrato

* Validação de schema JSON para todos os endpoints
* Garantia de consistência estrutural das respostas

---

## Estratégia de Testes

O projeto foi construído com base nos seguintes princípios:

### Separação de responsabilidades

* Camada `client` abstrai chamadas HTTP
* `specifications` centralizam validações comuns
* Testes focam apenas em validações

### Reutilização

* Request/Response specs reutilizáveis
* Configuração centralizada via singleton (`ConfigManager`)

### Legibilidade

* Uso de assertions fluentes com `.body()`
* Nomes de testes descritivos

### Estabilidade

* Evita testes frágeis (ex: validações de aleatoriedade não determinísticas)

---

## Decisões Técnicas

### Request/Response Specifications

Centralização de:

* Status code
* Content-type
* Regras comuns

---

### ConfigManager (Singleton)

* Gerenciamento centralizado de propriedades
* Suporte a múltiplos ambientes

---

### Camada de Client

* Encapsula endpoints da API
* Reduz duplicação
* Melhora legibilidade dos testes

---

### Integração com Allure

* Logs completos de request/response
* Melhor rastreabilidade e debugging

---

