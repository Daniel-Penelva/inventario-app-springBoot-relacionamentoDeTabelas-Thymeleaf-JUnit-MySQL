# Anotação `@DataJpaTest`

A anotação `@DataJpaTest` é uma anotação específica do Spring Boot que é usada para testar componentes de acesso a dados em um aplicativo Spring, em particular, com foco no mapeamento objeto-relacional (ORM) fornecido pelo Spring Data JPA. Ela fornece uma configuração de teste pronta para uso, permite a execução de testes de integração em seu repositório JPA e garante que somente os componentes JPA relevantes sejam carregados.

A anotação `@DataJpaTest` pode ser usada em classes de teste para testar seus repositórios JPA sem carregar a aplicação inteira. Isso é útil para isolar os testes de acesso a dados, garantindo que as configurações de banco de dados, transações e outros componentes específicos do JPA sejam configurados corretamente para seus testes.

Com `@DataJpaTest`, o Spring Boot configura automaticamente um banco de dados em memória, inicializa o EntityManager, configura transações e injeta o repositório JPA para você. Isso permite que você se concentre nos testes de seus métodos de repositório sem se preocupar com a configuração do ambiente de teste.

Lembre-se de que o `@DataJpaTest` se destina a testes de integração de acesso a dados e requer uma configuração adequada do banco de dados e do contexto do Spring.

# Anotações `@AutoConfigureTestDatabase` e `@Rollback`

As anotações `@AutoConfigureTestDatabase` e `@Rollback` são usadas em testes unitários e de integração em Java, principalmente com a estrutura de teste Spring Boot. Elas permitem personalizar o comportamento dos testes em relação ao banco de dados e à transação. Aqui está uma explicação detalhada de cada uma:

## 1. `@AutoConfigureTestDatabase`:

A anotação `@AutoConfigureTestDatabase` é usada em testes com o framework Spring Boot para configurar o banco de dados a ser usado nos testes. Ela tem um atributo chamado `replace`, que define como o banco de dados deve ser configurado. Há três opções para o atributo `replace`:

   - `Replace.NONE` (valor padrão): Isso indica que o banco de dados de teste não deve ser substituído. Você usará o banco de dados de produção ou o que estiver configurado em sua aplicação.

   - `Replace.AUTO`: Isso instrui o Spring Boot a substituir automaticamente o banco de dados de produção pelo banco de dados de teste. Essa opção é útil quando você deseja usar um banco de dados em memória para seus testes, como H2.

   - `Replace.NONE`: Isso significa que o banco de dados de teste não deve ser substituído, semelhante a `Replace.NONE`.

## 2. `@Rollback`:

A anotação `@Rollback` é usada em testes com o Spring Framework para controlar o comportamento de transação nos testes. Por padrão, todos os testes transacionais com o Spring são revertidos (rollback) após a conclusão do teste, o que significa que as mudanças no banco de dados durante o teste não são persistidas. No entanto, você pode personalizar esse comportamento usando `@Rollback`.

   - `@Rollback(true)` (valor padrão): Isso faz com que o teste seja revertido após sua conclusão. Ou seja, as mudanças no banco de dados durante o teste são desfeitas.

   - `@Rollback(false)`: Isso indica que as mudanças no banco de dados não serão revertidas após o teste. Útil quando você deseja realizar verificações em um banco de dados real durante o teste.

Portanto, `@AutoConfigureTestDatabase` define como o banco de dados é configurado para os testes e `@Rollback` controla se as transações devem ser revertidas após os testes. 

# Teste unitário criarCategoriaTest

 Este teste está criando uma instância de uma classe `Categoria` e a salva em um repositório JPA usando o método `save`. Em seguida, verifica se o ID da categoria criada é maior que zero usando a biblioteca de afirmação (assertion) do AssertJ. 
 
 ```java
    @Test
    public void testCriarCategoria() {
        Categoria criarCategoria = categoriaRepository.save(new Categoria("Eletrônicos"));
        Assertions.assertThat(criarCategoria.getId()).isGreaterThan(0);
    }
 ```
 
 Vou explicar o código passo a passo:

1. `@Test`:
   - Esta é uma anotação do JUnit que marca um método como um método de teste. Os métodos marcados com `@Test` são executados quando executa os testes unitários.

2. `public void testCriarCategoria()`:
   - Este é o método de teste em si. Ele é público e não retorna nenhum valor. O nome do método é descritivo e geralmente segue uma convenção em que "test" é usado para indicar que é um método de teste, seguido de uma descrição do que o teste faz.

3. `Categoria criarCategoria = categoriaRepository.save(new Categoria("Eletrônicos"))`:
   - Neste trecho, uma nova instância da classe `Categoria` é criada com o nome "Eletrônicos". Em seguida, essa instância é salva no repositório JPA usando o método `save` do repositório `categoriaRepository`. O objeto `criarCategoria` é usado para armazenar a categoria criada.

4. `Assertions.assertThat(criarCategoria.getId()).isGreaterThan(0)`:
   - Esta linha de código verifica se o ID da categoria criada é maior que zero. Ela usa a biblioteca AssertJ para realizar a asserção (verificação) e verificar se a condição é verdadeira.
   - `Assertions.assertThat(criarCategoria.getId())` obtém o ID da categoria criada.
   - `.isGreaterThan(0)` verifica se o ID é maior do que zero.

Em resumo, este teste verifica se é possível criar uma instância da classe `Categoria`, salvá-la em um repositório JPA e garantir que o ID atribuído à categoria seja maior que zero, o que indica que a categoria foi persistida com sucesso no banco de dados. Esse teste é útil para verificar o funcionamento da criação de categorias no contexto de um aplicativo que utiliza o Spring Data JPA e um repositório JPA chamado `categoriaRepository`. 