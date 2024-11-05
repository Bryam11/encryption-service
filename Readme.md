# Encryption Service

## Symmetric Encryption
Symmetric encryption uses the same key for both encryption and decryption. It is efficient and suitable for encrypting large amounts of data. Common algorithms include AES and DES.

**Use Cases:**
- Encrypting data at rest (e.g., database encryption)
- Securing communication channels (e.g., TLS/SSL)

## Asymmetric Encryption
Asymmetric encryption uses a pair of keys: a public key for encryption and a private key for decryption. It is computationally more intensive than symmetric encryption but provides better security for key exchange.

**Use Cases:**
- Secure key exchange
- Digital signatures
- Encrypting small amounts of data (e.g., passwords)

## Hashing
Hashing converts data into a fixed-size string of characters, which is typically a digest that represents the data. It is a one-way function, meaning it cannot be reversed. Common algorithms include SHA-256 and MD5.

**Use Cases:**
- Data integrity verification
- Storing passwords securely
- Digital signatures

## Stream Cipher
Stream ciphers encrypt data one bit or byte at a time, making them suitable for real-time data encryption. They use a symmetric key and are often used in scenarios where data size is unknown or continuously streaming.

**Use Cases:**
- Encrypting real-time data streams (e.g., video or audio)
- Securing communication protocols (e.g., SSL/TLS)

## Hybrid Encryption
Hybrid encryption combines both symmetric and asymmetric encryption to leverage the strengths of each. Typically, asymmetric encryption is used to exchange a symmetric key, which is then used for encrypting the actual data.

**Use Cases:**
- Secure communication channels
- Encrypting large files securely