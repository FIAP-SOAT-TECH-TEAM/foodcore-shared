# 📦 FoodCore Shared

Biblioteca compartilhada contendo componentes, interfaces, DTOs e configurações comuns aos microsserviços do ecossistema FoodCore. Desenvolvida como parte do curso de Arquitetura de Software da FIAP (Tech Challenge).

<div align="center">
  <a href="#visao-geral">Visão Geral</a> •
  <a href="#arquitetura">Arquitetura</a> •
  <a href="#repositorios">Repositórios</a> •
  <a href="#componentes">Componentes</a> •
  <a href="#tecnologias">Tecnologias</a> •
  <a href="#deploy">Fluxo de deploy</a> •
  <a href="#instalacao-e-uso">Instalação e Uso</a> •
  <a href="#contribuicao">Contribuição</a>
</div><br>

> 📽️ Vídeo de demonstração da arquitetura: [https://youtu.be/k3XbPRxmjCw](https://youtu.be/k3XbPRxmjCw)<br>

---

<h2 id="visao-geral">📋 Visão Geral</h2>

O **FoodCore Shared** é uma **biblioteca Java de recursos compartilhados** utilizada pelos microsserviços do ecossistema FoodCore.

Embora o sistema siga os princípios de **Arquitetura de Microsserviços**, onde serviços **não devem depender uns dos outros**, identificamos que todos os microsserviços são implementados em **Java** e compartilham um conjunto significativo de **estruturas técnicas e contratuais**, como DTOs, exceções, interfaces de gateway e configurações comuns.

Diante disso, optou-se por **extrair esses elementos transversais para um pacote compartilhado**, evitando duplicação de código e garantindo padronização, **sem violar o isolamento dos domínios de negócio**.

---
> ⚠️ **Importante:**
> O **FoodCore Shared não é um microsserviço**.
> Ele não possui lógica de negócio, banco de dados ou responsabilidades de domínio, atuando exclusivamente como uma **biblioteca reutilizável**.
> Mesmo sendo uma biblioteca, criamos testes unitários, mas não o integramos ao sonar, diferente dos **microserviços** que **possuem** essa integração.
---
> 📌 A dependência de uma biblioteca compartilhada **não configura acoplamento entre microsserviços**, desde que:
>
> - Não contenha regras de negócio
> - Não exponha detalhes internos de outros serviços
> - Seja versionada e consumida como dependência externa
>
---

O pacote **FoodCore Shared** nos ajuda promovendo:

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

O FoodCore Shared foi projetado seguindo as seguintes premissas:

- ❌ **Nenhuma regra de negócio**
- ❌ **Nenhum acoplamento entre bounded contexts**
- ❌ **Nenhuma dependência entre microsserviços**
- ✅ Apenas **contratos**, **infraestrutura comum** e **elementos técnicos reutilizáveis**
- ✅ Dependência unidirecional: **microsserviços → shared**

Dentro desse contexto, o FoodCore Shared atua como uma **camada de suporte técnico**, auxiliando na padronização e reutilização de código, sem comprometer a autonomia dos microsserviços.

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

<h2 id="deploy">⚙️ Fluxo de Deploy</h2>

<details>
<summary>Expandir para mais detalhes</summary>

### Pipeline

1. **Pull Request**
   - Preencher template de pull request adequadamente

2. **Revisão e Aprovação**
   - Mínimo 1 aprovação de CODEOWNER

3. **Merge para Main**

### Proteções

- Branch `main` protegida
- Nenhum push direto permitido
- Todos os checks devem passar

### Ordem de Provisionamento

```
1. foodcore-infra        (AKS, VNET)
2. foodcore-db           (Bancos de dados)
3. foodcore-auth           (Azure Function Authorizer)
4. foodcore-observability (Serviços de Observabilidade)
5. foodcore-order            (Microsserviço de pedido)
6. foodcore-payment            (Microsserviço de pagamento)
7. foodcore-catalog            (Microsserviço de catálogo)
```

> ⚠️ Opcionalmente, as pipelines do repositório `foodcore-shared` podem ser executadas para publicação de um novo package. Atualizar os microsserviços para utilazarem a nova versão do pacote.

</details>

---

<h2 id="instalacao-e-uso">🚀 Instalação e Uso</h2>

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
```

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
  Tech Challenge 4
</div>
