# Ailarm

Ailarm es una aplicación multiplataforma de alarmas inteligentes que combina la funcionalidad tradicional de alarmas con capacidades de inteligencia artificial para mejorar la experiencia del usuario. El proyecto está desarrollado como parte del curso **UX mejoramiento de la experiencia de usuario** y ofrece dos implementaciones: una aplicación web y una aplicación móvil nativa.

## 👤 Integrantes

* Daniela Suárez - cd.suarez@uniandes.edu.co
* Esteban Heredia - e.herediar@uniandes.edu.co


## 📱 Descripción del proyecto

### Arquitectura del Proyecto

El repositorio contiene dos soluciones independientes:

#### 🌐 Aplicación web (`/web`)
- **Framework**: Angular 19.2
- **UI Library**: Angular Material
- **Lenguaje**: TypeScript
- **Características**: SPA (Single Page Application) con componentes modulares

#### 📱 Aplicación móvil (`/mobile/app-ailarm`)
- **Platform**: Android
- **Lenguaje**: Kotlin
- **UI Framework**: Jetpack Compose
- **Arquitectura**: MVVM con componentes modernos de Android

## 🚀 Instalación y configuración

### 🌐 Aplicación web (Angular)

#### Prerrequisitos
- **Node.js**: Versión 18 o superior
- **npm**: Incluido con Node.js (versión 8 o superior)
- **Git**: Para clonar el repositorio

#### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/cdsuarezg/misw4302-2025-14-ailarm.git
   cd misw4302-2025-14-ailarm
   ```

2. **Navegar al directorio web**
   ```bash
   cd web
   ```

3. **Instalar dependencias**
   ```bash
   npm install
   ```

4. **Ejecutar la aplicación en modo desarrollo**
   ```bash
   npm start
   # o alternativamente
   ng serve
   ```

5. **Acceder a la aplicación**
   - Abrir navegador en `http://localhost:4200`
   - La aplicación se recargará automáticamente al hacer cambios


### 📱 Aplicación móvil (Android)

#### Prerrequisitos
- **Android Studio**: Flamingo o superior (2023.1.1+)
- **Java Development Kit (JDK)**: Versión 11 o superior
- **Android SDK**: API Level 24-35
- **Kotlin**: 2.0.21 (incluido en Android Studio)
- **Git**: Para clonar el repositorio

#### Configuración del Entorno Android

1. **Instalar Android Studio**
   - Descargar desde [developer.android.com](https://developer.android.com/studio)
   - Seguir el asistente de instalación
   - Configurar SDK Manager con las versiones necesarias

2. **Configurar SDK**
   ```
   - Android SDK Build-Tools: 35.0.0
   - Android SDK Platform: API 24-35
   - Android Emulator (opcional para testing)
   ```

#### Pasos de instalación

1. **Clonar el repositorio** (si no se ha hecho)
   ```bash
   git clone https://github.com/cdsuarezg/misw4302-2025-14-ailarm.git
   cd misw4302-2025-14-ailarm
   ```

2. **Navegar al directorio móvil**
   ```bash
   cd mobile/app-ailarm
   ```

3. **Abrir proyecto en Android Studio**
   - Abrir Android Studio
   - Seleccionar "Open an existing Android Studio project"
   - Navegar y seleccionar la carpeta `mobile/app-ailarm`

4. **Sincronizar proyecto**
   - Android Studio automáticamente descargará las dependencias
   - Esperar a que termine la sincronización de Gradle

5. **Ejecutar la aplicación**
   - Conectar dispositivo Android o configurar emulador
   - Hacer clic en "Run" (▶️) o usar `Shift + F10`

## 📂 Estructura del proyecto

```
misw4302-2025-14-ailarm/
├── web/                     # Aplicación web Angular
│   ├── src/app/
│   │   ├── components/      # Componentes reutilizables
│   │   ├── pages/           # Páginas principales
│   │   ├── services/        # Servicios y lógica de negocio
│   │   └── dialogs/         # Diálogos modales
│   └── ...
└── mobile/app-ailarm/       # Aplicación Android
    ├── app/src/main/
    └── gradle/
```

## 📄 Licencia

Proyecto académico desarrollado para MISW4302-2025-14.