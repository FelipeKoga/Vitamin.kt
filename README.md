# Vitamin.kt

![Test workflow](https://github.com/FelipeKoga/Vitamin.kt/actions/workflows/test.yml/badge.svg) ![CodeQL workflow](https://github.com/FelipeKoga/Vitamin.kt/actions/workflows/codeql-analysis.yml/badge.svg) [![Vitamin](https://img.shields.io/endpoint?url=https://dashboard.cypress.io/badge/simple/etow1b&style=flat&logo=cypress)](https://dashboard.cypress.io/projects/etow1b/runs) [![code style: prettier](https://img.shields.io/badge/code_style-prettier-ff69b4.svg)](https://github.com/prettier/prettier) [![Commitizen friendly](https://img.shields.io/badge/commitizen-friendly-brightgreen.svg)](http://commitizen.github.io/cz-cli/) [![license](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/FelipeKoga/Vitamin.kt/blob/main/LICENSE)

### Vite and Kotlin/JS example

#### Motivation

This project is an experiment with Kotlin/JS in React apps, similar to how Kotlin can be integrated with Swift and SwiftUI for iOS development.

The Kotlin/JS code is located in the core folder, and it’s currently configured for the JS target. However, it’s easy to extend it to other platforms like Android, iOS, or JVM.

Thanks to [@wtchnm](https://github.com/wtchnm) for creating an amazing Vite starter template. I was able to easily integrate Kotlin/JS and run tests to ensure everything is working smoothly. You can check it out [here](https://github.com/wtchnm/Vitamin).

### Tech Stack

- **Vite** with **React**, **TypeScript**, **React Query**, and a lot of cool features that are described in the template repository.
- **Kotlin Multiplatform** with **Kotlin Coroutines**, **Koin**, and **Ktor**.

### Roadmap

- [ ] Add more functionalities and cover the Kotlin Multiplatform module with tests.
- [ ] Add more KMP targets, like **Android**, **iOS**, and **JVM**.
- [ ] Integrate with Kotlin Flow
- [ ] Explore how to integrate **Compose Multiplatform** into a React/JS app.
- [ ] Share a **Multiplatform ViewModel** between multiple apps – React, SwiftUI, and Compose.
- [ ] Add multiple modules to the KMP project
- [ ] Try out **Decompose**.


### Getting started

Install the dependencies:

```
pnpm build:core && pnpm install
```

Then, run:

```
pnpm run dev
```

### Scripts

- `pnpm dev` - start a development server with hot reload.
- `pnpm build` - build for production. The generated files will be on the `dist` folder.
- `pnpm preview` - locally preview the production build.
- `pnpm test` - run unit and integration tests related to changed files based on git.
- `pnpm test:ci` - run all unit and integration tests in CI mode.
- `pnpm test:e2e` - run all e2e tests with the Cypress Test Runner.
- `pnpm test:e2e:headless` - run all e2e tests headlessly.
- `pnpm format` - format all files with Prettier.
- `pnpm lint` - runs TypeScript, ESLint and Stylelint.
- `pnpm validate` - runs `lint`, `test:ci` and `test:e2e:ci`.
