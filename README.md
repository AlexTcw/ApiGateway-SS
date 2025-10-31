🛡️ API Gateway con Spring Cloud Gateway y Spring Security 🔒

✨ Resumen del Proyecto

Este proyecto implementa un API Gateway robusto utilizando Spring Cloud Gateway para el enrutamiento y Spring Security para la gestión de seguridad.

📝 Descripción Detallada

Este proyecto de arquitectura de microservicios, desarrollado con fines académicos, se enfoca en crear un punto de entrada centralizado para múltiples microservicios. Las principales funcionalidades son:

    Enrutamiento Centralizado: Dirige de manera eficiente las solicitudes a los servicios internos correspondientes.

    Filtrado de Solicitudes: Permite la aplicación de lógica pre y post-solicitud.

    Acceso Seguro: Protege los microservicios subyacentes mediante autenticación JWT, gestionada de forma centralizada por el Gateway.

🚀 Instalación y Ejecución

Sigue estos pasos para poner en marcha el proyecto:

    Clonar el Repositorio:
    Bash

git clone https://github.com/AlexTcw/ApiGateway-SS.git
cd ApiGateway-SS

Compilar y Empaquetar:
Bash

mvn clean install
mvn clean package

Ejecutar la Aplicación:
Bash

    java -jar ApiGateway-SS.jar

⚙️ Configuración del Entorno

🔑 Gestión de Variables de Entorno

IMPORTANTE: Para garantizar la seguridad y la flexibilidad en diferentes entornos, todos los datos sensibles y las URLs de los microservicios se cargan dinámicamente a través de variables de entorno. Esto es una mejora clave que desacopla la configuración de la base de código.

Asegúrate de definir las siguientes variables de entorno antes de ejecutar la aplicación:
Variable	Descripción	Ejemplo de Uso (dentro del código)
JDBC_URL	URL de conexión de la base de datos (PostgreSQL).	spring.datasource.url: ${JDBC_URL}
JDBC_USER	Nombre de usuario de la base de datos.	spring.datasource.username: ${JDBC_USER}
JDBC_PASSWORD	Contraseña de la base de datos.	spring.datasource.password: ${JDBC_PASSWORD}
SSL_FILE	Ruta al archivo keystore SSL (e.g., .p12).	server.ssl.key-store: ${SSL_FILE}
SSL_PASS	Contraseña del keystore SSL.	server.ssl.key-store-password: ${SSL_PASS}
JWT_SECRET	Clave secreta para la firma y verificación de JWTs.	jwt.secret: ${JWT_SECRET}
GATEWAY_ROUTES	Ruta al archivo de configuración de rutas dinámicas (e.g., routes.yml).	spring.config.import: optional:file:${GATEWAY_ROUTES}

🛠️ Configuración application.yml

A continuación, se muestra un fragmento de la configuración principal, donde se visualiza el uso de las variables de entorno:
YAML

spring:
  application:
    name: Api-gw
  datasource:
    url: ${JDBC_URL}
    username: ${JDBC_USER}
    password: ${JDBC_PASSWORD}
    driver-class-name: org.postgresql.Driver
    # ... (Otras configuraciones de Hikari y JPA) ...
  config:
    # Carga la configuración de rutas de forma dinámica
    import: optional:file:${GATEWAY_ROUTES}
server:
  port: 8200
  ssl:
    enabled: true
    key-store: ${SSL_FILE}
    key-store-password: ${SSL_PASS}
    # ...
jwt:
  secret: ${JWT_SECRET}
# ... (Otras configuraciones) ...

📚 Documentación

La documentación de la API (Swagger UI) está disponible en la siguiente ruta:

    Ruta de Documentación: /docs

👩‍💻 Autor

    AlexTcw (GitHub: AlexTcw)

Espero que este README cumpla con tus expectativas. ¿Hay alguna sección o detalle adicional que te gustaría incluir o modificar?
