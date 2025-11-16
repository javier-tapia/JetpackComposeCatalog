# Catálogo de componentes de ***Jetpack Compose***

#### *Notas tomadas del curso de Aris Guimerá, disponible en [Udemy](https://www.udemy.com/course/jetpack-compose-desde-0-a-profesional/)

### Índice
<!-- TOC -->
* [Catálogo de componentes de ***Jetpack Compose***](#catálogo-de-componentes-de-jetpack-compose)
      * [*Notas tomadas del curso de Aris Guimerá, disponible en Udemy](#notas-tomadas-del-curso-de-aris-guimerá-disponible-en-udemy)
    * [Índice](#índice)
    * [2. Componentes de texto](#2-componentes-de-texto)
    * [4. Componente ***Button***](#4-componente-button)
    * [5. Componente de imagen](#5-componente-de-imagen)
    * [6. Componente ***ProgressBar***](#6-componente-progressbar)
    * [7. Componentes de selección](#7-componentes-de-selección)
    * [8. Otros componentes](#8-otros-componentes)
    * [9. ***RecyclerView*** en *Compose*](#9-recyclerview-en-compose)
    * [10. ***Scaffold***](#10-scaffold)
    * [12. Animaciones en *Compose*](#12-animaciones-en-compose)
      * [Animaciones *as state*](#animaciones-as-state)
      * [Animaciones de visibilidad](#animaciones-de-visibilidad)
      * [Animaciones de cambio de componentes](#animaciones-de-cambio-de-componentes)
      * [Animaciones de contenido](#animaciones-de-contenido)
      * [_InfiniteTransition_](#_infinitetransition_)
    * [13. _InteractionSource_](#13-_interactionsource_)
<!-- TOC -->





### 4. Componente ***Button***

***Button*** es el ``Composable`` que renderiza un botón. Tiene la particularidad de que debe
implementar el ``onClick`` obligatoriamente.  
Este componente tiene otras variantes, como el ***OutlineButton*** o el ***TextButton***.

### 5. Componente de imagen

Se pueden visualizar todos los íconos disponibles [aquí](https://fonts.google.com/icons).  
Pero para utilizar todos esos íconos en un proyecto, es necesario agregar la siguiente dependencia
al ``build.gradle(:app)``:

````kotlin
implementation "androidx.compose.material:material-icons-extended:$compose_version"
````

### 6. Componente ***ProgressBar***

El ***ProgressBar*** se usa para dar feedback al usuario cuando la aplicación está realizando
operaciones por detrás.  
Hay dos tipos de indicadores: ***LinearProgressIndicator*** y ***CircularProgressIndicator***.

### 7. Componentes de selección

***Switch***, ***CheckBox*** y ***RadioButton***  
Los tres componentes son similares en cuanto a que se les debe ***indicar un estado*** y la ***
acción a realizar*** cuando el estado del componente cambia.  
El ***CheckBox*** tiene la particularidad de que ***puede tener un tercer estado***, además
de ``on`` y ``off``, llamado ``indeterminate``.

### 8. Otros componentes

***Card*** vs ***Surface***  
Una ***Card*** es simplemente una ***Surface*** con valores por defecto (``elevation``, ``shape``,
etc).

***BadgedBox***  
Se utiliza para mostrar información dinámica, como puede ser el número de mensajes sin leer de una *
app* de *mail* o mensajería.

***DropDownMenu***  
Se utiliza para desplegar un menú de opciones.

***Slider***  
Se utiliza para permitir a los usuarios hacer selecciones entre un rango de valores.  
También está disponible un tipo especial llamado ***RangeSlider***, que amplía la funcionalidad
del ***Slider*** utilizando los mismos conceptos, pero permite al usuario seleccionar dos valores.

***Dialogs*** y ***AlertDialogs***
Permite mostrar un diálogo en base al diseño que se especifique.  
El ***AlertDialog*** ya implementa por defecto un título, una descripción y dos botones.

### 9. ***RecyclerView*** en *Compose*

Para crear listas dinámicas se usan las funciones ``Composables`` ``LazyColumn`` (vertical) o ``LazyRow`` (horizontal).  
Para añadir elementos a estas listas, se utilizan las funciones ``item`` o ``items`` (para más de un elemento).

``LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {...}``: La función ``spacedBy`` permite agregar un espacio entre las vistas hijas del componente a través del eje principal (en este caso, vertical). Si el espacio es negativo, los hijos van a superponerse.

La función ``rememberLazyListState`` crea y recuerda el estado de la lista a través de las diferentes composiciones.

***Sticky headers*** **(cabeceras)**: La función ``stickyHeader`` permite agregar una cabecera que permanece fija hasta que la próxima cabecera ocupe su lugar.

### 10. ***Scaffold***

Es un _layout_ ``Composable`` creado para montar vistas de una forma más sencilla, ya que funciona como un "esqueleto" de una pantalla que sigue la estructura propuesta por _Material Design_.


### 12. Animaciones en *Compose*

#### Animaciones *as state*
La función ``animateColorAsState`` recibe **un color** (``targetValue``), **una animación** que se utilizará para cambiar el valor a través del tiempo (``animationSpec``), **un _listener_ opcional** que se ejecutará cuando finalice la animación (``finishedListener``) y **un ``label``** opcional para diferenciarla de otras animaciones en Android Studio.  
Como animación se puede utilizar por ejemplo la función ``tween``. _Tweening_ en animación es una abreviatura de _inbetweening_ (interpolación) y es el proceso de generar imágenes que van entre fotogramas clave.  
Cuando se cambia el ``targetValue`` proporcionado, la animación se ejecutará automáticamente. Si ya hay una animación en curso cuando cambia el color, la animación en curso se ajustará para animarse hacia el nuevo _target_.  
``animateColorAsState`` devuelve un objeto ``State``. La animación actualizará continuamente el valor de dicho objeto hasta que finalice.  
Si para las animaciones de color existe la función ``animateColorAsState``, para las animaciones de tamaño está ``animateDpAsState``. Recibe los mismos parámetros, con la salvedad de que el _target_ será un tamaño en vez de un color.  
A modo informativo, también existen las funciones ``animateOffsetAsState`` y ``animateFloatAsState``.

#### Animaciones de visibilidad
La función ``composable`` ``AnimatedVisibility`` permite realizar animaciones de aparición/desaparición de un componente de forma simple y rápida.  
Entre los parámetros que recibe, tiene un ``enter`` y un ``exit``, que pueden sobreescribirse a gusto para lograr el efecto de animación deseado. Y en ``content``, irá el objeto que se quiere mostrar/ocultar.

#### Animaciones de cambio de componentes
La función ``composable`` ``Crossfade`` permite cambiar entre dos componentes con una animación de fundido encadenado. Cada vez que cambia el estado del argumento ``targetState``, se dispara la animación, ocultando el componente "viejo" y mostrando el componente "nuevo".

#### Animaciones de contenido
En este apartado se pueden mencionar al componente ``AnimatedContent`` y al modificador ``animateContentSize``: 
- ``AnimatedContent``: Un contenedor que anima automáticamente su contenido cuando cambia ``targetState``. Su ``content`` para diferentes _target states_ se define en un mapeo entre un _target state_ y una función ``composable``.
- ``animateContentSize``: Anima su propio tamaño cuando su modificador hijo (o el elemento ``composable`` hijo si ya está al final de la cadena) cambia de tamaño. Esto permite que el modificador padre observe un cambio de tamaño suave, lo que resulta en un cambio visual continuo.

#### _InfiniteTransition_
La función ``rememberInfiniteTransition()`` permite obtener un objeto de tipo ``InfiniteTransition``, el cual se encarga de ejecutar las animaciones secundarias o hijas. Estas animaciones se pueden agregar mediante ``InfiniteTransition.animateColor``, ``InfiniteTransition.animateFloat`` o ``InfiniteTransition.animateValue``. Las animaciones secundarias comenzarán a ejecutarse en cuanto entren en la composición y no se detendrán hasta que se eliminen de ella.

### 13. _InteractionSource_
Permite **conocer el estado transitorio de un ``composable``** (presionado, arrastrado, en foco, _hovered_).  
Por ejemplo, para saber si un componente está siendo presionado, se puede usar ``MutableInteractionSource`` de esta forma: 

```kotlin
val interaction = remember { MutableInteractionSource() }
val isPressed by interaction.collectIsPressedAsState()
Text(
  if (isPressed) "Pulsado" else "Sin pulsar"
)
```
