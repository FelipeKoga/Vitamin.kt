import { screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import App from 'App'
import { initCoreModule } from 'core-shared'
import renderWithProviders from 'testUtils'

initCoreModule()

describe('<App />', () => {
	it('renders', async () => {
		window.history.pushState({}, 'Home', '/')
		renderWithProviders(<App />, false)

		await expect(screen.findByText('Apple')).resolves.toBeInTheDocument()
		await userEvent.click(screen.getByText('Apple'))

		await expect(
			screen.findByText('Vitamins per 100 g (3.5 oz)')
		).resolves.toBeInTheDocument()
	})
})
