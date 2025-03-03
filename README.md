# 📌 ms-filtro-unidade-atendimento

A API **ms-filtro-unidade-atendimento** tem como principal objetivo facilitar o direcionamento de pacientes para a Unidade de Pronto Atendimento (UPA) mais próxima e com menor tempo de espera. Com essa API, é possível acompanhar em tempo real a fila de atendimento das UPAs, registrar e liberar atendimentos, além de consultar as unidades disponíveis.

## 🚀 Funcionalidades

- 🔍 **Localizar a UPA mais próxima do paciente**
- 📉 **Identificar a UPA com menor fila de atendimento**
- 🏥 **Gerenciar UPAs (Cadastro, Atualização e Remoção)**
- ⏳ **Monitorar o fluxo de atendimento em tempo real**

## 🔗 Endpoints Principais

### 📊 Monitoramento e Fila de Atendimento
- `GET /upa/real-time-queue` → Acompanha a fila de atendimento em tempo real
- `GET /upa/lower-queue/state/{state}` → Retorna a UPA com menor fila de atendimento
- `GET /upa/near-upa` → Retorna a UPA mais próxima do paciente segundo sua localização

### 📌 Gestão de UPAs
- `POST /upa/create` → Cadastra uma nova UPA
- `GET /upa/{id}` → Busca uma UPA pelo ID
- `GET /upa/all` → Retorna todas as UPAs disponíveis
- `PATCH /upa/update/{id}` → Atualiza informações da UPA
- `DELETE /upa/delete/{id}` → Remove uma UPA do sistema

## 📦 Tecnologias Utilizadas
- Java com Spring Boot
- Banco de Dados PostgreSQL
- MapStruct para mapeamento de entidades
- Clean Architecture
- Webflux
- SSE - server sent events
- Docker
- flyway para controle de migrações
- Swagger
- Spring cloud netflix
- Algoritimo de haversine para cálculo de distâncias

## 🛠 Como Executar
1. Clone este repositório:
   ```bash
   git clone --recurse-submodules https://github.com/Felipewiiu/UPA_FACIL-MICROSSERVICO.git
   ```
2. Configure as variáveis de ambiente no arquivo `.env` ou `application.properties`.
3. Execute a aplicação com o Maven:
   ```bash
   mvn spring-boot:run
   ```
4. Acesse a documentação da API via Swagger:
   ```
   http://localhost:<PORTA>/swagger-ui/webjars/swagger-ui/index.html
   ```

## 📝 Licença
Este projeto está sob a licença MIT.

