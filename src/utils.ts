import type { FlowObserver } from 'core-shared'
import { useEffect, useLayoutEffect, useState } from 'react'

// eslint-disable-next-line import/prefer-default-export
export function useMediaQuery(query: string): boolean {
	const [matches, setMatches] = useState(() => matchMedia(query).matches)

	useLayoutEffect(() => {
		const mediaQuery = matchMedia(query)

		function onMediaQueryChange(): void {
			setMatches(mediaQuery.matches)
		}

		mediaQuery.addEventListener('change', onMediaQueryChange)

		return (): void => {
			mediaQuery.removeEventListener('change', onMediaQueryChange)
		}
	}, [query])

	return matches
}

export function useFlowState<T>(property: FlowObserver<T>): T | undefined {
	const [state, setState] = useState<T>()
	useEffect(() => {
		property.startObserving((value: T) => setState(value))
		return () => property.stopObserving()
	}, [property])
	return state
}
