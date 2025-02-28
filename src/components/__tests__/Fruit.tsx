import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import {
	Author,
	Fruit,
	Image,
	initCoreModule,
	listOf,
	Metadata
} from 'core-shared'
import type ReactRouterDOM from 'react-router-dom'
import FruitCard from '../Fruit'

initCoreModule()

const mockNavigate = vi.fn()
vi.mock('react-router-dom', async () => ({
	...(await vi.importActual<typeof ReactRouterDOM>('react-router-dom')),
	useNavigate: (): typeof mockNavigate => mockNavigate
}))

const fruit = new Fruit(
	'Apple',
	new Image(
		new Author('Matheus Cenali', 'https://unsplash.com/@cenali'),
		'#bb3335',
		'https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?fit=crop&q=60&auto=format'
	),
	listOf([new Metadata('Vitamin A', '3 μg')])
)

function renderFruit(): void {
	render(<FruitCard fruit={fruit} index={0} />)
}

describe('<Fruit />', () => {
	it('renders', () => {
		renderFruit()

		expect(screen.getByText('Photo by')).toBeInTheDocument()
		expect(
			screen.getByRole('link', { name: 'Matheus Cenali' })
		).toBeInTheDocument()
		expect(screen.getByText('on')).toBeInTheDocument()
		expect(screen.getByRole('link', { name: 'Unsplash' })).toBeInTheDocument()

		expect(screen.getByText('Apple')).toBeInTheDocument()
	})
	it('redirect to fruit details page on enter', async () => {
		renderFruit()

		screen.getByTestId('FruitCard').focus()
		// No action should be performed
		await userEvent.keyboard('a')
		await userEvent.keyboard('[Enter]')

		// eslint-disable-next-line @typescript-eslint/no-magic-numbers
		expect(mockNavigate).toHaveBeenCalledTimes(1)
		expect(mockNavigate).toHaveBeenCalledWith('apple')
	})
	it('redirect to photographer profile page on image attribute link click', async () => {
		renderFruit()

		await userEvent.click(screen.getByRole('link', { name: 'Matheus Cenali' }))

		expect(mockNavigate).toHaveBeenCalledTimes(0)
	})
})
