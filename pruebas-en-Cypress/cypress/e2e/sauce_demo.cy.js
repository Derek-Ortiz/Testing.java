describe('Pruebas en SauceDemo con Cypress', () => {
  it('Debe loguearse y agregar 4 artículos al carrito', () => {
    cy.visit('https://www.saucedemo.com/')

    // Login
    cy.get('#user-name').type('standard_user')
    cy.get('#password').type('secret_sauce')
    cy.get('#login-button').click()

    // Validar entrada
    cy.url().should('include', '/inventory.html')

    // Agregar 4 artículos (usando el índice de los botones)
    cy.get('.btn_inventory').then($btns => {
      for (let i = 0; i < 4; i++) {
        cy.wrap($btns[i]).click()
      }
    })

    // Validar badge y mostrar en consola
    cy.get('.shopping_cart_badge').should('have.text', '4').then(($badge) => {
      const cantidad = $badge.text()
      cy.log('Artículos agregados: ' + cantidad)
      console.log('Resultado en consola: ' + cantidad + ' artículos detectados.')
    })
  })
})