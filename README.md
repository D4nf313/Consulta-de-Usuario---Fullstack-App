Consulta de Usuario - Fullstack App
Este proyecto es una aplicación completa para la consulta de usuarios, desarrollada con una arquitectura desacoplada utilizando Spring Boot 3 para el backend y Angular 17 para el frontend.

🛠️ Tecnologías y Versiones Necesarias
Para ejecutar este proyecto, necesitas tener instaladas las siguientes herramientas:

1. Backend (Spring Boot)
Java JDK 17: Descargar aquí (Es la versión definida en tu pom.xml).

Maven 3.6+: (Normalmente incluido en tu IDE, o puedes usar el mvnw incluido).

Lombok: Asegúrate de habilitar "Annotation Processing" en tu IDE (IntelliJ o VS Code).

2. Frontend (Angular)
Node.js: v18.13.0 o v20.x (LTS recomendada). Descargar aquí.

Angular CLI: v17.3.17.

PrimeNG: v17.18.0 (Componentes de UI).

📂 Estructura del Repositorio
Plaintext
consulta-usuario-root/
├── backend/           # Proyecto Spring Boot (consulta-usuario)
└── frontend/          # Proyecto Angular (consulta-usuario-front)

🔧 Configuración e Instalación

Sigue estos pasos para levantar el proyecto localmente:
1. Clonar el repositorio
Bash
git clone https://github.com/D4nf313/Consulta-de-Usuario---Fullstack-App
cd tu-repositorio
2. Levantar el Backend (Spring Boot)
El backend utiliza una base de datos H2 en memoria, por lo que no necesitas instalar bases de datos externas.

Bash
cd backend
# Para Windows:
mvnw.cmd spring-boot:run
# Para Mac/Linux:
./mvnw spring-boot:run
API Base URL: http://localhost:8080

Consola H2: http://localhost:8080/h2-console

3. Levantar el Frontend (Angular)
Abre una nueva terminal:

Bash
cd frontend
npm install
npm start
URL de la App: http://localhost:4200

## 🧠 Decisiones Técnicas y Arquitectura

### 🏗️ Backend (Spring Boot 3.4.2)
* **Arquitectura en Capas:** Organización dividida en `Controller`, `Service`, `Repository` y `Entity` para asegurar la separación de responsabilidades.
* **Uso de Lombok:** Implementado para reducir el código repetitivo, mejorando la legibilidad de las entidades y DTOs.
* **MapStruct:** Utilizado para el mapeo automático entre Entidades y DTOs, garantizando una transferencia de datos segura y eficiente.
* **Manejo Global de Excepciones:** Se diseñó un `GlobalExceptionHandler` con `@ControllerAdvice` que estandariza las respuestas de error. Esto permite que el Frontend reciba mensajes claros y estructurados ante cualquier fallo de validación o negocio (ej. Usuario no encontrado).

### 🎨 Frontend (Angular 17)
* **PrimeNG:** Se seleccionó esta librería de componentes por su robustez y rapidez para crear interfaces profesionales y reactivas.
* **Consumo de API REST:** Uso de servicios desacoplados y centralizados para la comunicación con el backend mediante `HttpClient`.
* **Validaciones Dinámicas:** El frontend reacciona a las excepciones controladas del backend para mostrar alertas precisas al usuario, mejorando la experiencia de uso (UX).