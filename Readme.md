# Servicio de Cifrado

## Cifrado Simétrico
El cifrado simétrico utiliza la misma clave tanto para cifrar como para descifrar datos. Es eficiente y adecuado para cifrar grandes cantidades de datos. Los algoritmos comunes incluyen AES y DES.

**Casos de Uso:**
- Cifrado de datos en reposo (por ejemplo, cifrado de bases de datos)
- Seguridad en canales de comunicación (por ejemplo, TLS/SSL)

## Cifrado Asimétrico
El cifrado asimétrico usa un par de claves: una clave pública para cifrado y una clave privada para descifrado. Es más intensivo en términos de cálculo que el cifrado simétrico, pero proporciona mejor seguridad para el intercambio de claves.

**Casos de Uso:**
- Intercambio seguro de claves
- Firmas digitales
- Cifrado de pequeñas cantidades de datos (por ejemplo, contraseñas)

## Hashing
El hashing convierte los datos en una cadena de caracteres de tamaño fijo, generalmente un resumen que representa los datos. Es una función unidireccional, lo que significa que no se puede revertir. Algoritmos comunes incluyen SHA-256 y MD5.

**Casos de Uso:**
- Verificación de la integridad de datos
- Almacenamiento seguro de contraseñas
- Firmas digitales

## Cifrado de Flujo
Los cifrados de flujo cifran los datos un bit o byte a la vez, lo que los hace adecuados para el cifrado en tiempo real. Utilizan una clave simétrica y suelen aplicarse en escenarios donde el tamaño de los datos es desconocido o se transmiten de manera continua.

**Casos de Uso:**
- Cifrado de transmisiones de datos en tiempo real (por ejemplo, video o audio)
- Seguridad en protocolos de comunicación (por ejemplo, SSL/TLS)

## Cifrado Híbrido
El cifrado híbrido combina tanto el cifrado simétrico como el asimétrico para aprovechar las ventajas de ambos. Típicamente, se utiliza el cifrado asimétrico para intercambiar una clave simétrica, la cual se usa luego para cifrar los datos reales.

**Casos de Uso:**
- Canales de comunicación seguros
- Cifrado seguro de archivos grandes

## Presentación Personal
Mi nombre es [Bryam Xavier Chuchuca Guzman], y estoy en la materia de Aplicaciones Seguras. En este proyecto, desarrollé un servicio de cifrado, y se escribieron pruebas unitarias para los tamaños de clave de 128, 192 y 256 bits para garantizar su seguridad y funcionalidad.