import { render, screen } from '@testing-library/react'
import { initCoreModule } from 'core-shared'
import LoadingOrError from '../LoadingOrError'

initCoreModule()

describe('<LoadingOrError />', () => {
	it('renders', () => {
		render(<LoadingOrError />)

		expect(screen.getByText('Loading...')).toBeInTheDocument()
	})
	it('renders with an error message', () => {
		render(<LoadingOrError error={new Error('Failed to fetch')} />)

		expect(screen.getByText('Failed to fetch')).toBeInTheDocument()
	})
})
