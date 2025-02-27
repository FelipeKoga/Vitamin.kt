import { useQuery } from '@tanstack/react-query'
import FruitCard from 'components/Fruit'
import Head from 'components/Head'
import LoadingOrError from 'components/LoadingOrError'
import { getFruitRepository } from 'core-shared'
import type { ReactElement } from 'react'

export default function GalleryPage(): ReactElement {
	const repository = getFruitRepository()

	const { isPending, isError, error, data } = useQuery({
		queryKey: ['fruits'],
		queryFn: async () => repository.getAll()
	})

	if (isPending || isError) {
		return <LoadingOrError error={error as Error} />
	}

	return (
		<>
			<Head title='Vitamin' />
			<div className='m-2 grid min-h-screen grid-cols-[minmax(0,384px)] place-content-center gap-2 md:m-0 md:grid-cols-[repeat(2,minmax(0,384px))] xl:grid-cols-[repeat(3,384px)]'>
				{data.map((fruit, index) => (
					<FruitCard
						key={`FruitCard-${fruit.name}`}
						fruit={fruit}
						index={index}
					/>
				))}
			</div>
		</>
	)
}
