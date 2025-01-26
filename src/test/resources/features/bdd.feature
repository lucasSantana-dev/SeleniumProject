Feature: Realizar uma compra

  Scenario: Adicionar produto no carrinho e realizar compra
    Given Estou acessando a home page
    And Acesso a aba Dresses
    And Adiciono produto no carrinho
    Then Finalizo a compra
