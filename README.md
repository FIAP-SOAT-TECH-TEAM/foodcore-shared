# 📦 FoodCore Shared
 
<div align="center">
 
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=FIAP-SOAT-TECH-TEAM_foodcore-shared&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=FIAP-SOAT-TECH-TEAM_foodcore-shared)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=FIAP-SOAT-TECH-TEAM_foodcore-shared&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=FIAP-SOAT-TECH-TEAM_foodcore-shared)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=FIAP-SOAT-TECH-TEAM_foodcore-shared&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=FIAP-SOAT-TECH-TEAM_foodcore-shared)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=FIAP-SOAT-TECH-TEAM_foodcore-shared&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=FIAP-SOAT-TECH-TEAM_foodcore-shared)
 
</div>

Biblioteca compartilhada contendo componentes, interfaces, DTOs e configurações comuns aos microsserviços do ecossistema FoodCore. Desenvolvida como parte do curso de Arquitetura de Software da FIAP (Tech Challenge).

<div align="center">
  <a href="#visao-geral">Visão Geral</a> •
  <a href="#arquitetura-geral">Arquitetura Geral</a> •
  <a href="#repositorios">Repositórios</a> •
  <a href="#componentes">Componentes</a> •
  <a href="#tecnologias">Tecnologias</a> •
  <a href="#instalacao">Instalação</a> •
  <a href="#dicionario">Dicionário de Linguagem Ubíqua</a> •
  <a href="#contribuicao">Contribuição</a>
</div><br>

> 📽️ Vídeo de demonstração da arquitetura: [https://www.youtube.com/watch?v=XgUpOKJjqak](https://www.youtube.com/watch?v=XgUpOKJjqak)<br>

---

<h2 id="visao-geral">📋 Visão Geral</h2>

O **FoodCore Shared** é uma biblioteca Java que centraliza código reutilizável entre os microsserviços do sistema FoodCore. Esta abordagem promove:

- **Consistência**: Mesmas interfaces e DTOs em todos os microsserviços
- **Reutilização**: Evita duplicação de código comum
- **Manutenibilidade**: Alterações centralizadas propagam para todos os serviços
- **Padronização**: Exceções, mappers e configurações padronizadas

### Principais Componentes

| Componente | Descrição |
|------------|-----------|
| **Exceptions** | Exceções de domínio padronizadas (`BusinessException`, `ResourceNotFoundException`, `ResourceConflictException`) |
| **Value Objects** | Objetos de valor comuns (`AuditInfo`) |
| **DTOs** | Data Transfer Objects compartilhados (`UserDTO`, `FileUploadDTO`) |
| **Gateways** | Interfaces de gateway para integrações externas (`AccessManagerGateway`, `AuthenticatedUserGateway`, `EmailGateway`, `ImageStorageGateway`) |
| **Mappers** | Configurações e utilitários de mapeamento (`AuditInfoMapper`, `JacksonConfig`) |
| **Sources** | Interfaces de fonte de dados (`UserSource`, `ImageDataSource`, `EmailDataSource`) |

---

<h2 id="arquitetura">🧱 Arquitetura</h2>

O sistema FoodCore é composto por microsserviços independentes que seguem os princípios de:

### 🎯 Padrões Arquiteturais

- **Arquitetura de Microsserviços**: Serviços independentes e especializados
- **Clean Architecture**: Domínio independente de frameworks e infraestrutura
- **Domain-Driven Design (DDD)**: Bounded contexts bem definidos
- **SAGA Coreografada**: Comunicação assíncrona entre microsserviços via eventos
- **Service Discovery**: Descoberta de serviços via API Gateway
- **Circuit Breaker**: Resiliência na comunicação entre serviços
- **Lei de Demeter**: Baixo acoplamento entre componentes
- **Webhooks**: Integração com serviços externos (Mercado Pago)

### 🌐 Infraestrutura

| Recurso | Descrição |
|---------|-----------|
| **AKS** | AGIC (Application Gateway Ingress Controller), Azure Key Vault Provider para secrets |
| **Observabilidade** | Grafana, Prometheus (Métricas), EFK (Logs), Zipkin (Tracing) |
| **Alta Disponibilidade** | Redundância zonal/regional (vide limitações de assinatura) |
| **Disaster Recovery** | Backup geográfico de banco de dados |
| **Scaling** | Particionamento Azure Service Bus, Autoscaling AKS, HPAs, partições físicas CosmosDB |
| **Segurança** | OAuth2, Lambda Authorizer (Cognito), APIM Rate Limiting e Caching |
| **Kubernetes** | HPAs, StatefulSets, LivenessProbes, ReadinessProbes, ConfigMaps |

---

<h2 id="repositorios">📁 Repositórios do Ecossistema</h2>

| Repositório | Responsabilidade | Tecnologias |
|-------------|------------------|-------------|
| **[foodcore-shared](https://github.com/FIAP-SOAT-TECH-TEAM/foodcore-shared)** | Biblioteca compartilhada (este repositório) | Java 21, Spring Boot |
| **[foodcore-order](https://github.com/FIAP-SOAT-TECH-TEAM/foodcore-order)** | Microsserviço de pedidos | Java 21, Spring Boot, PostgreSQL |
| **[foodcore-payment](https://github.com/FIAP-SOAT-TECH-TEAM/foodcore-payment)** | Microsserviço de pagamentos | Java 21, Spring Boot, CosmosDB |
| **[foodcore-catalog](https://github.com/FIAP-SOAT-TECH-TEAM/foodcore-catalog)** | Microsserviço de catálogo de produtos | Java 21, Spring Boot, PostgreSQL |
| **[foodcore-auth](https://github.com/FIAP-SOAT-TECH-TEAM/foodcore-auth)** | Azure Function de autenticação | .NET 9, AWS Cognito |
| **[foodcore-infra](https://github.com/FIAP-SOAT-TECH-TEAM/foodcore-infra)** | Infraestrutura base (AKS, VNET, APIM) | Terraform, Azure |
| **[foodcore-db](https://github.com/FIAP-SOAT-TECH-TEAM/foodcore-db)** | Provisionamento de bancos de dados | Terraform, PostgreSQL, CosmosDB |
| **[foodcore-observability](https://github.com/FIAP-SOAT-TECH-TEAM/foodcore-observability)** | Stack de observabilidade | Kubernetes, Helm, EFK, Prometheus |

---

<h2 id="componentes">🧩 Componentes da Biblioteca</h2>

<details>
<summary>Expandir para mais detalhes</summary>

### Exceções de Domínio

```
core/domain/exceptions/
├── BusinessException.java          # Exceção base para erros de negócio
├── ResourceNotFoundException.java  # Recurso não encontrado (404)
└── ResourceConflictException.java  # Conflito de recursos (409)
```

### Value Objects

```
core/domain/vo/
└── AuditInfo.java  # Informações de auditoria (createdAt, updatedAt, etc.)
```

### DTOs Compartilhados

```
core/interfaceadapters/dto/
├── UserDTO.java        # Dados de usuário
└── FileUploadDTO.java  # Dados de upload de arquivo
```

### Interfaces de Gateway

```
core/interfaceadapters/gateways/
├── AccessManagerGateway.java      # Gerenciamento de acesso
├── AuthenticatedUserGateway.java  # Usuário autenticado
├── EmailGateway.java              # Envio de e-mails
└── ImageStorageGateway.java       # Armazenamento de imagens
```

### Configurações Comuns

```
infrastructure/common/
├── config/
│   ├── CognitoConfig.java  # Configuração AWS Cognito
│   └── Serializable.java   # Interface de serialização
├── mapper/
│   ├── AuditInfoMapper.java             # Mapper de auditoria
│   ├── CycleAvoidingMappingContext.java # Evita ciclos em mapeamento
│   ├── DoIgnore.java                    # Anotação para ignorar campos
│   └── JacksonConfig.java               # Configuração Jackson
└── source/
    ├── AccessManagerSource.java     # Fonte de dados de acesso
    ├── AuthenticatedUserSource.java # Fonte de usuário autenticado
    ├── EmailDataSource.java         # Fonte de dados de e-mail
    ├── ImageDataSource.java         # Fonte de dados de imagem
    └── UserSource.java              # Fonte de dados de usuário
```

</details>

---

<h2 id="tecnologias">🔧 Tecnologias</h2>

| Categoria | Tecnologia |
|-----------|------------|
| **Linguagem** | Java 21 |
| **Framework** | Spring Boot 3.4 |
| **Mapeamento** | MapStruct |
| **Utilitários** | Lombok |
| **Build** | Gradle |
| **CI/CD** | GitHub Actions |
| **Qualidade** | SonarCloud |

---

<h2 id="instalacao">� Instalação e Uso</h2>

### Uso como Dependência

Adicione no `build.gradle` do seu microsserviço:

```groovy
dependencies {
    implementation 'com.soat.fiap.food.core:shared:1.0.0'
}
```

### Desenvolvimento Local

```bash
# Clonar repositório
git clone https://github.com/FIAP-SOAT-TECH-TEAM/foodcore-shared.git

# Navegar para o diretório
cd foodcore-shared

# Compilar
./gradlew build

# Publicar localmente
./gradlew publishToMavenLocal

# Executar testes
./gradlew test
```

---

<h2 id="dicionario">📖 Dicionário de Linguagem Ubíqua</h2>

<details>
<summary>Expandir para mais detalhes</summary>

| Termo | Descrição |
|-------|-----------|
| **Admin** | Usuário com privilégios elevados para gestão do sistema |
| **Adquirente** | Instituição financeira que processa pagamentos (Mercado Pago) |
| **Authentication** | Validação da identidade do usuário |
| **Authorization** | Controle de acesso baseado em roles |
| **Catalog** | Conjunto de produtos disponíveis |
| **Category** | Classificação de produtos (lanches, bebidas, sobremesas) |
| **Combo** | Conjunto personalizado: lanche + acompanhamento + bebida + sobremesa |
| **Customer** | Cliente que realiza pedidos |
| **Guest** | Cliente não identificado |
| **Order** | Pedido com itens selecionados |
| **Order Item** | Produto específico dentro de um pedido |
| **Payment** | Processamento de pagamento via Mercado Pago |
| **Product** | Item disponível para venda |
| **Role** | Papel do usuário (ADMIN, ATENDENTE, GUEST) |

</details>

---

<h2 id="contribuicao">🤝 Contribuição</h2>

### Fluxo de Contribuição

1. Crie uma branch a partir de `main`
2. Implemente suas alterações
3. Execute os testes: `./gradlew test`
4. Abra um Pull Request
5. Aguarde aprovação de um CODEOWNER

### Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

<div align="center">
  <strong>FIAP - Pós-graduação em Arquitetura de Software</strong><br>
  Tech Challenge
</div>
