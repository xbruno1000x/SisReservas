# 📅 SisReservas - Sistema de Reservas

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen)
![License](https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-blue)
![Status](https://img.shields.io/badge/Status-Active-success)

Sistema genérico de reservas desenvolvido com Spring Boot, ideal para restaurantes, salões de beleza, barbearias e outros estabelecimentos que necessitam gerenciar agendamentos.

## 🚀 Funcionalidades

### Clientes
- ✅ Cadastro completo de clientes
- ✅ Consulta por ID ou listagem completa
- ✅ Atualização de dados cadastrais
- ✅ Exclusão de clientes
- ✅ Validação de email único
- ✅ Timestamp automático de criação

### Reservas
- ✅ Criação de reservas vinculadas a clientes
- ✅ Controle de status (Pendente, Confirmada, Cancelada, Concluída)
- ✅ Validação de datas (não permite reservas no passado)
- ✅ Filtragem por cliente, status ou intervalo de datas
- ✅ Atualização de reservas existentes
- ✅ Cancelamento e conclusão de reservas
- ✅ Timestamps automáticos de criação e atualização
- ✅ Observações personalizadas

## 🛠️ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Spring Boot 3.5.7** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Hibernate** - ORM (Object-Relational Mapping)
- **H2 Database** - Banco de dados em memória
- **Maven** - Gerenciador de dependências
- **Jackson** - Serialização JSON
- **Spring DevTools** - Hot reload em desenvolvimento

## 📋 Pré-requisitos

- JDK 17 ou superior
- Maven 3.6+
- VS Code (recomendado) ou IDE de sua preferência

## ⚙️ Instalação e Execução

### 1. Clone o repositório
```bash
git clone https://github.com/xbruno1000x/SisReservas.git
cd SisReservas
```

### 2. Execute a aplicação
```bash
mvn spring-boot:run
```

Ou se estiver usando VS Code com as tasks configuradas:
- Pressione `Ctrl+Alt+M` para compilar e executar

### 3. Acesse a aplicação
- **API:** http://localhost:8080
- **Console H2:** http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:sisreservas`
  - Username: `sa`
  - Password: _(deixe em branco)_

## 📡 Endpoints da API

### Clientes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/clientes` | Lista todos os clientes |
| GET | `/api/clientes/{id}` | Busca cliente por ID |
| POST | `/api/clientes` | Cria novo cliente |
| PUT | `/api/clientes/{id}` | Atualiza cliente |
| DELETE | `/api/clientes/{id}` | Remove cliente |

#### Exemplo de Cliente (JSON)
```json
{
  "nome": "João Silva",
  "telefone": "(11) 98765-4321",
  "email": "joao.silva@email.com"
}
```

### Reservas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/reservas` | Lista todas as reservas |
| GET | `/api/reservas/{id}` | Busca reserva por ID |
| GET | `/api/reservas/cliente/{clienteId}` | Lista reservas de um cliente |
| GET | `/api/reservas/status/{status}` | Filtra por status |
| GET | `/api/reservas/periodo?inicio=YYYY-MM-DD&fim=YYYY-MM-DD` | Filtra por período |
| POST | `/api/reservas` | Cria nova reserva |
| PUT | `/api/reservas/{id}` | Atualiza reserva |
| PATCH | `/api/reservas/{id}/status?status=CONFIRMADA` | Atualiza apenas o status |
| DELETE | `/api/reservas/{id}` | Cancela reserva |

#### Exemplo de Reserva (JSON)
```json
{
  "clienteId": 1,
  "data": "2025-11-15",
  "hora": "14:30:00",
  "observacoes": "Corte de cabelo e barba",
  "status": "PENDENTE"
}
```

#### Status Disponíveis
- `PENDENTE` - Reserva criada, aguardando confirmação
- `CONFIRMADA` - Reserva confirmada pelo estabelecimento
- `CANCELADA` - Reserva cancelada
- `CONCLUIDA` - Serviço realizado

## 🗄️ Banco de Dados

O projeto utiliza H2 Database em memória com dados pré-populados:
- **10 clientes** cadastrados
- **20 reservas** com diferentes status e datas

Os dados são recriados a cada inicialização da aplicação (arquivo `data.sql`).

### Estrutura das Tabelas

#### Tabela: clientes
| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | BIGINT | Chave primária |
| nome | VARCHAR(100) | Nome do cliente |
| telefone | VARCHAR(20) | Telefone |
| email | VARCHAR(100) | Email (único) |
| criado_em | TIMESTAMP | Data de cadastro |

#### Tabela: reservas
| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | BIGINT | Chave primária |
| cliente_id | BIGINT | FK para clientes |
| data | DATE | Data da reserva |
| hora | TIME | Horário da reserva |
| status | VARCHAR(20) | Status da reserva |
| observacoes | VARCHAR(500) | Observações |
| criado_em | TIMESTAMP | Data de criação |
| atualizado_em | TIMESTAMP | Última atualização |

## 🔧 Configuração de Desenvolvimento

### Hot Reload com DevTools
O projeto está configurado com Spring DevTools para reiniciar automaticamente ao salvar arquivos:

1. Certifique-se de que o auto-save está habilitado no VS Code
2. Ao modificar arquivos `.java`, a aplicação recompila automaticamente
3. Use `Ctrl+Alt+M` para recompilar manualmente

### Configurações do VS Code
O projeto inclui configurações em `.vscode/`:
- `tasks.json` - Task de compilação Maven
- `keybindings.json` - Atalho `Ctrl+Alt+M`
- `settings.json` - Autobuild e autosave
- `extensions.json` - Extensões recomendadas

## 📁 Estrutura do Projeto

```
SisReservas/
├── src/main/java/com/SisReservas/
│   ├── controller/          # Controladores REST
│   │   ├── ClienteController.java
│   │   ├── ReservaController.java
│   │   └── HelloController.java
│   ├── model/              # Entidades JPA
│   │   ├── Cliente.java
│   │   └── Reserva.java
│   ├── repository/         # Repositórios JPA
│   │   ├── ClienteRepository.java
│   │   └── ReservaRepository.java
│   ├── service/           # Lógica de negócio
│   │   ├── ClienteService.java
│   │   └── ReservaService.java
│   └── SisReservasApplication.java
├── src/main/resources/
│   ├── application.properties
│   └── data.sql           # Dados iniciais
├── pom.xml
└── README.md
```

## 🤝 Contribuindo

Este projeto está sob licença não comercial. Contribuições são bem-vindas para fins educacionais e não lucrativos!

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está licenciado sob a **Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International** (CC BY-NC-SA 4.0).

**Você pode:**
- ✅ Compartilhar e adaptar o código
- ✅ Usar para fins educacionais
- ✅ Usar para projetos pessoais não comerciais

**Você NÃO pode:**
- ❌ Usar para fins comerciais
- ❌ Vender ou lucrar com este código

Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**xbruno1000x**
- GitHub: [@xbruno1000x](https://github.com/xbruno1000x)

## 📞 Suporte

Para reportar bugs ou sugerir melhorias, abra uma [issue](https://github.com/xbruno1000x/SisReservas/issues).

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!
