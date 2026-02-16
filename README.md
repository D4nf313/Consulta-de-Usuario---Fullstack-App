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

📦 Librerías Principales Utilizadas
Backend:
Spring Data JPA: Para persistencia de datos.

MapStruct (1.5.5.Final): Para el mapeo eficiente de DTOs a Entidades.

Lombok: Para reducir el código boilerplate (Getters, Setters).

Validation: Para validaciones de formularios desde el servidor.

Frontend:
PrimeNG & PrimeIcons: Para una interfaz de usuario moderna y profesional.

RxJS: Para el manejo de flujos de datos asíncronos.

📝 Notas Adicionales
Lombok + MapStruct: El proyecto está configurado para que ambos funcionen juntos mediante el lombok-mapstruct-binding en el pom.xml. Si tienes errores de compilación, realiza un mvn clean install.

Variables de Entorno: Si necesitas cambiar el puerto o la conexión, revisa el archivo backend/src/main/resources/application.properties.

⭐ ¡No olvides darle una estrella a este repo si te resultó útil!