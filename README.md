
---
## 🛠️ Características Avanzadas Implementadas

Para cumplir con las exigencias de la rúbrica de evaluación de arquitectura de software, estos módulos incorporan los siguientes componentes de calidad:

1. **Encapsulamiento con DTOs (`SucursalDTO`):** Implementación de objetos de transferencia de datos para aislar el modelo de base de datos de las peticiones externas, utilizando `@JsonProperty` para asegurar un mapeo estricto.
2. **Validación Temprana de Datos:** Uso de anotaciones de restricción (`@NotBlank`, `@NotNull`, `@Size`) para bloquear peticiones con datos corruptos o vacíos antes de que lleguen a la capa de servicios.
3. **Manejo Global de Excepciones (`@RestControllerAdvice`):** Creación de un interceptor centralizado (`GlobalExceptionController`) encargado de atrapar los fallos de validación del sistema y normalizarlos en respuestas HTTP con estado `400 Bad Request` organizadas en formato JSON.
4. **Trazabilidad Mediante Logs (SLF4J Logger):** Incorporación de registros detallados en consola (`logger.info` y `logger.warn`) a lo largo del flujo del controlador y del servicio para garantizar la auditoría técnica de los endpoints.

---

## 💾 Semilla de Datos (DataLoader)

El sistema cuenta con un mecanismo automatizado de carga de datos iniciales (`DataLoader`). Al levantar el entorno local, se verifica de forma automática el estado de la base de datos MySQL; si el repositorio de sucursales se encuentra vacío, se inyectan registros semilla preconfigurados para acelerar los ciclos de pruebas en Postman sin generar duplicidad.

---

## 📬 Guía Rápida de Endpoints para Postman

* **Listar Todo:** `GET http://localhost:8080/api/sucursales` (Retorna estado `200 OK`).
* **Crear Registro Válido:** `POST http://localhost:8080/api/sucursales` (Retorna estado `201 Created` con el ID autogenerado).
* **Probar Interceptor de Errores:** `POST` enviando un JSON con campos vacíos. Verificará de forma inmediata la respuesta controlada con estado `400 Bad Request` y el desglose de errores por atributo.
* **Remover Registro:** `DELETE http://localhost:8080/api/sucursales/{id}` (Retorna `204 No Content` si tiene éxito o `404 Not Found` si el recurso ya no existe).