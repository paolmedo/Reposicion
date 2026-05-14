# \# 🚀 Proyecto Backend: Sistema de Gestión Comercial

# 

# Este proyecto consiste en una arquitectura de \*\*microservicios independientes\*\* desarrollada con \*\*Spring Boot\*\*. El sistema está diseñado bajo el enfoque de \*\*Persistencia Políglota\*\*, donde cada microservicio utiliza el motor de base de datos que mejor se adapta a sus necesidades operativas.

# 

# \---

# 

# \## 👥 Equipo de Desarrollo y Responsabilidades

# 

# | Integrante | Microservicios a Cargo | Base de Datos |

# | :--- | :--- | :--- |

# | \*\*Pavel Olmedo\*\* | 🔐 Identidad / 📦 Inventario | `Oracle Cloud` (SQL Developer) |

# | \*\*Javiera Valle\*\* | 🏨 Sucursal / 💰 Ventas | `MySQL` (Local vía XAMPP) |

# 

# \---

# 

# \## 🛠️ Arquitectura Técnica

# 

# El sistema implementa el patrón de diseño \*\*CSR (Controller-Service-Repository)\*\*:

# 

# \* \*\*Lenguaje:\*\* Java 17+

# \* \*\*Framework:\*\* Spring Boot 3.x

# \* \*\*Capa de Persistencia:\*\* Spring Data JPA (Hibernate).

# \* \*\*Gestión de Entornos:\*\* \* \*\*MySQL:\*\* Utilizado para la gestión local de sucursales y transacciones de ventas.

# &#x20;   \* \*\*Oracle Cloud:\*\* Utilizado para el manejo centralizado de usuarios y stock global.

# \* \*\*Comunicación Inter-service:\*\* Feign Client / WebClient para peticiones REST sincrónicas.

# 

# \---

# 

# \## 📂 Estructura del Repositorio (Branch: sucursal-ventas-JaviValle)

# 

# ```text

# Proyecto\_Reposicion/

# ├── identidad-inventario/   # Microservicios en Oracle Cloud (Pavel)

# ├── sucursal-service/       # Gestión de Sucursales en MySQL (Javiera)

# └── ventas-service/         # Gestión de Ventas en MySQL (Javiera)

