# 🏦 SWIFT Banking Service API

SWIFT Banking Service API to aplikacja RESTful stworzona w **Spring Boot**, która zarządza kodami SWIFT banków.  
Aplikacja pozwala na:  
✅ Dodawanie kodów SWIFT  
✅ Pobieranie informacji o kodach SWIFT  
✅ Pobieranie wszystkich kodów SWIFT w danym kraju  
✅ Usuwanie kodów SWIFT
---
## 🚀 **Jak uruchomić aplikację?**

### 🔹 **Z Dockerem**

1️⃣ Zbuduj i uruchom kontenery
```sh
docker-compose up --build
```
📌 Aplikacja będzie dostępna pod adresem: `http://localhost:8080`

---
## 📖 **Dostępne endpointy**

| Metoda  | Endpoint                                   | Opis |
|---------|-------------------------------------------|------|
| **POST**   | `/v1/swift-codes`                       | Dodaje nowy kod SWIFT |
| **GET**    | `/v1/swift-codes/{swiftCode}`           | Pobiera informacje o danym kodzie SWIFT |
| **GET**    | `/v1/swift-codes/country/{countryISO2}` | Pobiera wszystkie kody SWIFT w danym kraju |
| **DELETE** | `/v1/swift-codes/{swiftCode}`           | Usuwa kod SWIFT |

---

## 📌 Przykłady użycia

🔹 1. Dodanie nowego kodu SWIFT
```sh
curl -X POST http://localhost:8080/v1/swift-codes \
     -H "Content-Type: application/json" \
     -d '{
         "address": "1 Wall St, NY",
         "bankName": "Bank of America",
         "countryISO2": "US",
         "countryName": "United States",
         "isHeadquarter": true,
         "swiftCode": "BOFAUS3NXXX"
     }'
```
🔹 2. Pobranie kodu SWIFT
```sh
curl -X GET http://localhost:8080/v1/swift-codes/BOFAUS3NXXX
```
🔹 3. Pobranie wszystkich kodów SWIFT w kraju
```sh
curl -X GET http://localhost:8080/v1/swift-codes/country/US

```
🔹 4. Usunięcie kodu SWIFT
```sh
curl -X DELETE http://localhost:8080/v1/swift-codes/BOFAUS3NXXX
```
---
## 🔍 Testowanie
Możesz uruchomić testy, aby sprawdzić, czy API działa poprawnie.
```sh
./mvnw test
```