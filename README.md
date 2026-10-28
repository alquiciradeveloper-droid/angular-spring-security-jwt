# angular-spring-security-jwt
# Spring Boot 3 & Angular JWT Authentication System

Sistema de autenticación y autorización *stateless* desarrollado con **Spring Boot 3 (Spring Security 6)** en el backend y **Angular 17+** en el frontend, utilizando tokens JWT (**JSON Web Tokens**) para la gestión segura de sesiones.

---

## 🚀 Características Principales

* **Autenticación Stateless:** Control de acceso mediante tokens de acceso firmados con HMAC-SHA.
* **Seguridad Robusta:** Encriptación de contraseñas con **BCrypt** (costo configurable) y protección CORS/CSRF configurada para API REST.
* **Filtros Personalizados:** Intercepción de solicitudes HTTP en el backend (`OncePerRequestFilter`) para validación de tokens.
* **Proxy de Desarrollo:** Configuración de `proxy.conf.json` en Angular para evitar bloqueos de origen en entorno local.
* **Manejo de Inactividad:** Cierre de sesión automático por falta de interacción del usuario en el frontend.
* **Consola H2:** Base de datos en memoria para pruebas rápidas con acceso frame-origin habilitado.

---

## 🛠️ Tecnologías Utilizadas

### Backend
* **Java 21**
* **Spring Boot 3**
* **Spring Security 6** (Spring Security Lambda DSL)
* **JPA / Hibernate**
* **H2 Database / PostgreSQL**
* **JJWT** (Java JWT Library)

### Frontend
* **Angular 17+** (Standalone Components)
* **TypeScript**
* **RxJS**
* **HTML5 / CSS3**

---

## 🔧 Configuración e Instalación

### Prerrequisitos
* **JDK 21** instalado.
* **Node.js** (v18+) y **npm**.
* **Angular CLI** (`npm install -g @angular/cli`).

---

### 1. Backend (Spring Boot)

1. Clona el repositorio:
   ```bash
   git clone [https://github.com/tu-usuario/nombre-de-tu-repo.git](https://github.com/tu-usuario/nombre-de-tu-repo.git)
   cd nombre-de-tu-repo/backend
