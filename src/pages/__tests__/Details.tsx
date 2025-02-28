import { screen } from '@testing-library/react'
import { initCoreModule } from 'core-shared'
import Gallery from 'pages/Gallery'
import type ReactRouterDOM from 'react-router-dom'
import { Route, Routes } from 'react-router-dom'
import renderWithProviders, {
	MOBILE_RESOLUTION_HEIGHT,
	MOBILE_RESOLUTION_WIDTH
} from 'testUtils'
import Details from '../Details'

initCoreModule()

vi.mock('react-router-dom', async () => ({
	...(await vi.importActual<typeof ReactRouterDOM>('react-router-dom')),
	Navigate: vi.fn()
}))

async function renderDetailsPage(route: string): Promise<void> {
	window.history.pushState({}, '', route)
	renderWithProviders(
		<Routes>
			<Route path='/' element={<Gallery />} />
			<Route path=':fruitName' element={<Details />} />
		</Routes>
	)
}

describe('<Details />', () => {
	it('renders', async () => {
		await renderDetailsPage('apple')

		await expect(
			screen.findByRole('link', { name: 'Back' })
		).resolves.toBeInTheDocument()
		expect(screen.getByText('Apple')).toBeInTheDocument()
		expect(screen.getByText('Vitamins per 100 g (3.5 oz)')).toBeInTheDocument()
		expect(screen.getByText('Vitamin')).toBeInTheDocument()
		expect(screen.getByText('Quantity')).toBeInTheDocument()
		expect(screen.getByText('Vitamin B1')).toBeInTheDocument()
		expect(screen.getByText('0.017 mg')).toBeInTheDocument()

		const image = screen.getByRole('img', { name: 'Apple' })
		expect(image).toHaveAttribute('width', '512')
		expect(image).toHaveAttribute('height', '800')
	})
	it('renders with mobile resolution', async () => {
		window.resizeTo(MOBILE_RESOLUTION_WIDTH, MOBILE_RESOLUTION_HEIGHT)
		await renderDetailsPage('apple')

		const image = await screen.findByRole('img', { name: 'Apple' })
		expect(image).toHaveAttribute('width', '414')
		expect(image).toHaveAttribute('height', '268.8')
	})
})
