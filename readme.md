<p align="center">
<img src="/preview/list_preview.png" width="45%"/>
<img src="/preview/detail_preview.png" width="45%"/>
</p>

# Librerías

- 100% basado en [Kotlin](https://kotlinlang.org/) + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
- Hilt para injección de dependencias.
- Jetpack
  - Lifecycle: Ciclos de vida.
  - ViewModel
  - Navigation: Para navegación.
  - Room.
  - [Hilt](https://dagger.dev/hilt/): Injección de dependencias.
- Architecture
  - MVVM Architecture: View - DataBinding - ViewModel - Model
  - MVVM Architecture: Compose View - ViewModel - Model
- [Material Design](https://material.io/design): Componentes de Google para la UI.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Cliente Rest.
- [Coil](https://github.com/coil-kt/coil): Para carga de imagenes en Views y Jetpack Compose.
- [Mockk](https://mockk.io/): Librería para mockear clases en Kotlin
- [Truth](https://github.com/google/truth): Librería de aserciones en Unit Test

# Arquitectura de la Aplicación

La aplicación se divide en 4 módulos: `data`, `domain`, `app` y `core`

En la capa de presentación se usa el patrón MVVM, para separar la lógica de negocio y la presentación de los datos en la interfaz de usuario.

<p align="center">
<img src="/preview/apparchitecture.png" width="45%"/>
</p>

---

## Módulo Data

- ### Repository
  Se usa este componente como mediador entre las diferentes fuentes de datos `data-source` (fuentes remota, preferencias locales, base de datos, etc) y los casos de uso donde se manejan la lógica de negocio.

- ### Data Source
  La clase de fuente de datos, tiene la responsabilidad de consultar una sola fuente de datos ya sea: lectura de archivos locales, una fuente de red o una base de datos local.

## Módulo Domain

- ### Casos de uso
  Responsable de manejar lógica de negocio compleja, entre sus dependencias son los repositorios que se injectan por el constructor, que son los contratos o interfaces definidas en la misma capa. Los casos de uso también no ayuda a combinar diferentes repositorios. En estas clases deben tener una sola responsablidad como función principal y no tener datos mutables.

  ```kotlin
  interface GetMoviesUseCase {
    operator fun invoke()
  }
  ```

## Módulo App (Presentation)

- ### ViewModels
  Componente del patrón MVVM, en este se maneja los eventos que realiza el usuario, también maneja la lógica empresarial y sirve de puente con los casos de uso.

  Ademas, este componente nos permite mantener el estado a nivel de una pantalla a través de los ciclos de vida de los views (fragments, activities).


- ### Views y Compose.
  El proyecto cuenta con implementación de vista con archivos XML y dataBinding, también con el kit de herramientas de Jetpack Compose para compilar UI Nativas, gracias a la API de interoperabilidad, dado que los viewmodels abstrae de la logica de negocio y eventos del usuario.

## Módulo Core
Módulo de utilitarios.

