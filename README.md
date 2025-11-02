# 💰 FinPer - Aplicación de Finanzas Personales

Una aplicación moderna de Android desarrollada con Jetpack Compose para gestionar tus finanzas personales de manera eficiente y elegante.

## ✨ Características Principales

### 📊 Gestión de Transacciones
- **25+ transacciones predefinidas** con datos realistas
- **Cada transacción incluye**:
  - 📅 Fecha y hora de la transacción
  - 💰 Monto (ingresos y gastos)
  - 🏷️ Categoría (Alimentación, Transporte, Vivienda, etc.)
  - 📝 Descripción detallada
  - 💳 Método de pago
  - 🆔 ID único de transacción
  - 🎨 Icono personalizado

### 🎯 Pantallas de la Aplicación

#### 1. **Splash Screen Animado** 🚀
- Animación de entrada con efecto de rebote
- Gradiente azul moderno
- Indicador de carga circular
- Transición suave al home

#### 2. **Pantalla de Inicio** 🏠
- Saludo dinámico según la hora del día (☀️🌤️🌙)
- Tarjetas de resumen financiero:
  - Balance total
  - Gastos del mes
- Transacciones recientes destacadas
- Diseño con cards elevados y colores vibrantes

#### 3. **Lista de Transacciones** 💳
- Vista expandible para cada transacción
- Al expandir muestra:
  - Descripción completa
  - Método de pago
  - Fecha y hora exacta
  - ID de transacción
  - Botones de acción (Eliminar/Ver detalles)
- Tarjeta de resumen con:
  - Balance actual
  - Total de ingresos (verde)
  - Total de gastos (rojo)
- Búsqueda y filtrado intuitivo
- Botón flotante para agregar nuevas transacciones

#### 4. **Formulario de Transacciones** ✍️
- Campos mejorados:
  - Título de la transacción
  - Monto con validación
  - Descripción opcional (multilínea)
  - Categoría (dropdown con 11 opciones)
  - Icono personalizable (16 opciones)
  - Método de pago (8 opciones)
- Selector de tipo: Ingreso/Gasto
- Validación en tiempo real
- Mensajes de confirmación con snackbar

#### 5. **Estadísticas** 📊 ⭐ NUEVA
- Resumen general:
  - Total de transacciones
  - Balance actual
  - Total ingresos/gastos
- Gastos por categoría con:
  - Porcentaje del total
  - Número de transacciones
  - Barra de progreso visual
- Promedios:
  - Gasto promedio
  - Gasto más alto identificado

#### 6. **Gestión de Presupuestos** 💼
- Crear y administrar presupuestos
- Seguimiento de objetivos financieros
- Visualización clara del progreso

#### 7. **Detalles de Transacción** 🔍
- Vista completa de la transacción seleccionada
- Información adicional:
  - Estado (Completado)
  - Tipo de transacción
  - Divisa (PEN - Soles)
- Diseño limpio con icono grande
- Navegación con botón de retorno

## 🎨 Mejoras de UI/UX

### Tema y Colores
- **Paleta de colores azul** profesional para finanzas
- Soporte para modo claro y oscuro
- Colores semánticos:
  - 🟢 Verde para ingresos
  - 🔴 Rojo para gastos
  - 🔵 Azul para información
- Elevaciones y sombras consistentes

### Animaciones
- Transiciones suaves entre pantallas
- Animación del splash screen
- Efectos de rebote en interacciones
- Feedback visual inmediato

### Navegación
- **Bottom Navigation Bar** con 4 secciones:
  - 🏠 Inicio
  - 💳 Transacciones
  - 📊 Estadísticas
  - 💰 Presupuestos
- Top Bar con título y acciones contextuales
- Navegación intuitiva y fluida

## 🛠️ Tecnologías Utilizadas

- **Kotlin** - Lenguaje de programación
- **Jetpack Compose** - UI moderna declarativa
- **Material Design 3** - Sistema de diseño
- **MVVM Architecture** - Arquitectura limpia
- **ViewModel** - Gestión de estado
- **State Management** - MutableStateList para reactividad
- **Coroutines** - Programación asíncrona

## 📱 Estructura del Proyecto

```
app/
├── data/
│   ├── Transaction.kt      # Modelo de transacción (con 4+ campos)
│   └── Budget.kt           # Modelo de presupuesto
├── viewmodel/
│   ├── TransactionViewModel.kt  # 25+ transacciones mock
│   └── BudgetViewModel.kt
├── screens/
│   ├── SplashScreen.kt     # ⭐ Mejorado con animaciones
│   ├── HomeScreen.kt       # ⭐ Rediseñado completamente
│   ├── TransactionsScreen.kt    # ⭐ Vista expandible
│   ├── TransactionFormScreen.kt # ⭐ Campos adicionales
│   ├── StatisticsScreen.kt # ⭐ NUEVA pantalla
│   ├── DetailScreen.kt     # ⭐ Mejorado
│   ├── BudgetFormScreen.kt
│   └── BudgetListScreen.kt
├── navigation/
│   └── AppNavHost.kt       # ⭐ Navegación mejorada
└── ui/theme/
    ├── Color.kt            # ⭐ Nuevos colores
    ├── Theme.kt            # ⭐ Tema mejorado
    └── Type.kt

```

## 🎯 Datos de Ejemplo

La aplicación incluye **25 transacciones** de ejemplo con datos realistas:

### Categorías Incluidas:
- 💰 Ingresos (Salario, Freelance, Ventas)
- 🍔 Alimentación (Supermercado, Restaurantes, Café)
- 🏠 Vivienda (Renta, Servicios)
- ⛽ Transporte (Gasolina, Uber)
- 🎬 Entretenimiento (Cine, Streaming, Gaming)
- 🏥 Salud (Médico, Farmacia, Gimnasio)
- 📚 Educación (Cursos online)
- 🛒 Compras (Ropa, Regalos)
- ✈️ Viajes (Boletos de avión)

### Métodos de Pago:
- 💵 Efectivo
- 💳 Tarjeta de débito/crédito
- 🏦 Transferencia bancaria
- 💻 PayPal
- 🌐 Pago online
- ⚡ Cargo automático
- 🔗 Tarjeta vinculada

## 🚀 Cómo Ejecutar

1. Clona el repositorio
2. Abre el proyecto en Android Studio
3. Sincroniza las dependencias de Gradle
4. Ejecuta la aplicación en un emulador o dispositivo físico

## 📈 Funcionalidades Futuras

- [ ] Persistencia de datos con Room Database
- [ ] Gráficos interactivos con MPAndroidChart
- [ ] Exportación de reportes PDF
- [ ] Notificaciones de recordatorio
- [ ] Widgets de home screen
- [ ] Sincronización en la nube
- [ ] Reconocimiento de voz para agregar transacciones
- [ ] Modo offline completo

## 👨‍💻 Autor

Desarrollado con ❤️ usando Jetpack Compose

## 📄 Licencia

Este proyecto es de código abierto y está disponible para uso educativo.

---

⭐ **Actualización de Octubre 2025**
- ✅ 25+ transacciones con 4+ campos cada una
- ✅ Nueva pantalla de Estadísticas
- ✅ Mejoras visuales en todas las pantallas
- ✅ Animaciones y transiciones suaves
- ✅ Tema de colores profesional
- ✅ Tarjetas expandibles con detalles completos
