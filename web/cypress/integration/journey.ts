describe("JourneyTest", () => {
    before(() => {
        cy.server()
        // cy.route('GET', 'api/todo').as('getTodos')

        cy.visit('')
        // cy.wait('@getTodos')
        // cy.contains('See All Todos').click()
    })

    it('loads', () => {
        expect(cy.contains('Learn React'))
    })
})
