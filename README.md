# 🚀 Proyecto Backend: Sistema de Gestión Comercial de Reposición

Este proyecto consiste en una arquitectura de **microservicios independientes** desarrollada con **Spring Boot**. El sistema está diseñado bajo un enfoque modular, utilizando un motor de base de datos relacional unificado para garantizar el rendimiento, la consistencia y la facilidad de despliegue en entornos de evaluación.

---

## 👥 Equipo de Desarrollo y Responsabilidades

| Integrante | Microservicios a Cargo | Base de Datos |
| :--- | :--- | :--- |
| **Pavel Olmedo** | 🔐 Identidad / 📦 Inventario | `MySQL` (Local vía XAMPP) |
| **Javiera Valle** | 🏨 Sucursal / 💰 Ventas | `MySQL` (Local vía XAMPP) |

---

## 🛠️ Arquitectura Técnica

Cada componente del sistema implementa de forma estricta el patrón de diseño **CSR (Controller-Service-Repository)**, asegurando un desacoplamiento óptimo entre la exposición de APIs y la persistencia:

* **Lenguaje Principal:** Java 17+
* **Framework:** Spring Boot 3.x
* **Capa de Persistencia:** Spring Data JPA (Hibernate)
* **Motor de Base de Datos:** MySQL / MariaDB (Gestionado localmente mediante entorno XAMPP en puertos alternativos de desarrollo como el `3307`).
* **Comunicación Inter-service:** Feign Client / WebClient para peticiones REST sincrónicas entre módulos.

---

## 📂 Estructura Integral del Repositorio

```text
Proyecto_Reposicion/
├── MicroServicioIdentidad/      # Gestión de Autenticación y Trabajadores (Pavel)
├── MicroServicioInventario/     # Control y Gestión de Stock Global (Pavel)
├── MicroServicioSucursal/       # Administración de Sucursales Comerciales (Javiera)
└── MicroServicioVentas/         # Procesamiento y Registro de Transacciones (Javiera)