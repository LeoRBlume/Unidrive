# Unidrive

Nesta documentação sera explicado o design pattern e padrão de arquitetura utilizado para desenvolver o sistema

# Projeto DDD (Domain-Driven Design)

Este projeto utiliza a arquitetura Domain-Driven Design (DDD) para desenvolver software baseado em um profundo entendimento do domínio do problema. O DDD enfoca a modelagem e a reflexão do domínio complexo e das regras de negócio em uma estrutura de software.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- **Domain**: Esta pasta contém o núcleo do domínio do problema. Aqui você encontrará as entidades principais, agregados, objetos de valor e interfaces de repositório. Essas construções encapsulam o conhecimento e o comportamento essenciais para resolver os problemas do domínio.

- **Application**: A pasta "Application" abriga os casos de uso (UseCases) do sistema. Cada caso de uso é uma unidade de funcionalidade que define uma ação específica no domínio. A lógica de aplicação é implementada aqui, orquestrando as interações entre as camadas do sistema e as entidades do domínio.

- **Infrastructure**: A pasta "Infrastructure" trata da implementação técnica do sistema e lida com detalhes de baixo nível, como persistência de dados, comunicação externa e infraestrutura de suporte. Aqui você encontrará implementações de repositórios, adaptadores para frameworks externos, serviços de infraestrutura e outras camadas de suporte técnico.

- **Interfaces**: A pasta "Interfaces" contém as interfaces de usuário ou os pontos de entrada do sistema. Isso pode incluir interfaces web, APIs, interfaces de linha de comando ou qualquer outra forma de interação com o usuário. A separação das preocupações de interface é mantida nesta pasta.

- **Infrastructure Shared**: A pasta "Infrastructure Shared" é usada para compartilhar componentes comuns e utilitários entre os diferentes módulos de infraestrutura. Aqui você encontrará ferramentas de persistência de dados genéricas, bibliotecas de comunicação externa ou outras funcionalidades compartilhadas que são usadas por diferentes partes do sistema.

## Benefícios do DDD

- Maior clareza e expressividade do modelo de domínio.
- Flexibilidade para evolução e adaptação do sistema.
- Melhor colaboração entre especialistas no domínio e desenvolvedores.
- Reutilização de código e modularidade do sistema.
- Separação clara de responsabilidades.
- Facilidade de manutenção e teste.

# Projeto Facade com UseCase

Este projeto utiliza o padrão de design Facade implementado pelo UseCase para simplificar a interação com um subsistema complexo. A combinação desses padrões visa fornecer uma interface coesa e fácil de usar, ocultando a complexidade subjacente do subsistema.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- **UseCases**: Esta pasta contém os casos de uso que representam as operações específicas do subsistema. Cada caso de uso é uma unidade de funcionalidade encapsulada em uma classe, que é responsável por orquestrar a interação entre os componentes internos do subsistema.

## Benefícios do Facade com UseCase

- **Simplicidade de uso**: A fachada simplifica a interação com o subsistema, fornecendo uma interface coesa e fácil de usar para os clientes externos.

- **Desacoplamento**: O padrão Facade reduz o acoplamento entre os clientes externos e os componentes internos do subsistema, isolando a complexidade interna.

- **Legibilidade e manutenibilidade**: A fachada e os casos de uso centralizam a lógica de interação e tornam o código mais legível e fácil de manter.

- **Reutilização de código**: O subsistema encapsulado pela fachada pode ser reutilizado em diferentes partes do sistema, promovendo a modularidade e a reutilização de código.

## Links

- Repositório Oficial: https://github.com/weslleyrichardc/unidrive
- Site: https://unidrive.works ou https://www.unidrive.works
- Swagger: https://api.unidrive.works ou https://api.unidrive.works/swagger-ui.html
