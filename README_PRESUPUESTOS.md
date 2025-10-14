# Sistema de Gestión de Presupuestos

## Funcionalidades implementadas

### ✅ Base de datos temporal en memoria
- **ViewModel** (`BudgetViewModel`) que almacena los presupuestos en una lista observable
- Persiste durante la sesión de la app
- 5 datos mock precargados al iniciar

### ✅ Lista de presupuestos
- Visualización en cards con toda la información
- Muestra: nombre, monto, categoría, notas y fecha de creación
- Card de resumen con el total de todos los presupuestos
- Contador de presupuestos registrados
- Categorías con colores distintivos

### ✅ Botón FAB (+) para agregar
- Botón flotante en la esquina inferior derecha
- Al presionar, muestra el formulario de creación

### ✅ Formulario completo
- Campos: Nombre, Monto, Categoría, Notas
- Validaciones en tiempo real
- Dropdown con categorías predefinidas
- Botones Guardar y Cancelar con feedback

### ✅ Funcionalidades adicionales
- **Eliminar presupuesto**: Botón de eliminar en cada card con confirmación
- **Formato de moneda**: Muestra los montos en formato de pesos mexicanos
- **Fecha de creación**: Registra cuándo se creó cada presupuesto
- **Categorías con colores**: Cada categoría tiene un color único
- **Cálculo automático**: Total de presupuestos se actualiza automáticamente

## Estructura del proyecto

```
app/src/main/java/com/example/composenavegacionapp/
├── data/
│   └── Budget.kt                    # Modelo de datos
├── viewmodel/
│   └── BudgetViewModel.kt          # Lógica de negocio y BD temporal
├── screens/
│   ├── BudgetListScreen.kt         # Lista principal con FAB
│   └── BudgetFormScreen_new.kt     # Formulario de creación
└── BudgetActivity.kt               # Activity para ejecutar la app
```

## Cómo usar

### Opción 1: Integrar en MainActivity existente
Reemplaza el contenido de `MainActivity.kt`:

```kotlin
package com.example.composenavegacionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composenavegacionapp.screens.BudgetListScreen
import com.example.composenavegacionapp.ui.theme.ComposeNavegacionAppTheme
import com.example.composenavegacionapp.viewmodel.BudgetViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNavegacionAppTheme {
                val viewModel: BudgetViewModel = viewModel()
                BudgetListScreen(viewModel = viewModel)
            }
        }
    }
}
```

### Opción 2: Crear activity separada
Ya está creada `BudgetActivity.kt`, solo agrega al `AndroidManifest.xml`:

```xml
<activity
    android:name=".BudgetActivity"
    android:exported="true"
    android:label="Presupuestos"
    android:theme="@style/Theme.ComposeNavegacionApp">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

## Datos Mock Precargados

1. **Presupuesto Mensual** - $3,500.00 (Ahorros)
2. **Supermercado Semanal** - $850.50 (Alimentación)
3. **Gasolina** - $600.00 (Transporte)
4. **Renta** - $5,000.00 (Vivienda)
5. **Entretenimiento** - $1,200.00 (Otros)

**Total inicial: $10,150.50**

## Categorías disponibles

- 🟢 **Ahorros**
- 🟠 **Alimentación**
- 🔵 **Transporte**
- 🟣 **Vivienda**
- ⚫ **Otros**

