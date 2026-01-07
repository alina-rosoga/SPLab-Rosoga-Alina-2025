# Product Management REST API - Spring Boot Application

## 📋 Descriere Proiect

**Product Management REST API** este o aplicație Spring Boot completa pentru gestionarea produselor și categoriilor într-un magazin online. Aplicația oferă un set complet de endpoint-uri RESTful pentru adăugare, ștergere, modificare, vizualizare și căutare de produse cu funcționalități avansate.

## 🎯 Scopul Aplicației

Scopul principal al aplicației este de a oferi o soluție robustă și scalabilă pentru gestionarea inventarului unui magazin online. Sistemul permite:
- Gestionarea completă a produselor (CRUD - Create, Read, Update, Delete)
- Organizarea produselor în categorii
- Căutarea flexibilă a produselor
- Monitorizarea stocului scăzut
- Gestionarea prețurilor și cantităților

## ✅ Conformitate cu Cerințele Proiectului

Acest proiect respectă **toate** cerințele specificate:

### 1. **RESTful API using Spring Boot** ✓
- Implementat cu Spring Boot 3.2.0
- Proiectare RESTful cu HTTP methods standard (GET, POST, PUT, DELETE)
- Endpoint-uri bine structurate și semantice
- Response-uri în format JSON
- Status codes HTTP corecte (200, 201, 204, 400, 404, 500)

### 2. **Any Database** ✓
- Utilizează **H2 Database** (bază de date relațională în-memory/file)
- Configurare JPA/Hibernate pentru ORM (Object-Relational Mapping)
- Schema automată prin `spring.jpa.hibernate.ddl-auto=update`
- Support pentru migrații și evoluție schema

### 3. **Functionality: Managing Data** ✓
- **Add** (Create): POST /products, POST /categories
- **Remove** (Delete): DELETE /products/{id}, DELETE /categories/{id}
- **Edit** (Update): PUT /products/{id}, PUT /categories/{id}
- **View** (Read): GET /products, GET /products/{id}, GET /categories, GET /categories/{id}

---

## 🏗️ Arhitectura Tehnică

### Stack Tehnologic
```
┌─────────────────────────────────┐
│    REST Controllers             │ (ProductController, CategoryController)
├─────────────────────────────────┤
│    Service Layer                │ (ProductService, CategoryService)
├─────────────────────────────────┤
│    Repository Layer             │ (JPA Repositories)
├─────────────────────────────────┤
│    Entity/Domain Models         │ (Product, Category)
├─────────────────────────────────┤
│    H2 Database                  │ (File-based persistence)
└─────────────────────────────────┘
```

### Dependențe Principale
- **Spring Boot Starter Web**: Pentru crearea aplicației web și REST endpoints
- **Spring Data JPA**: ORM și accesul la baza de date
- **H2 Database**: Bază de date relațională lightweight
- **Jakarta Validation**: Validarea input-ului și a datelor

---

## 📁 Structura Proiectului

```
Proj/
├── pom.xml                                    # Configurație Maven
├── description.txt                            # Descriere scurtă
├── README.md                                  # Acest fișier
└── src/
    └── main/
        ├── java/com/shop/
        │   ├── ShopApplication.java           # Clasa de pornire Spring Boot
        │   ├── entity/
        │   │   ├── Product.java               # Entitate Product
        │   │   └── Category.java              # Entitate Category
        │   ├── dto/
        │   │   ├── ProductDTO.java            # Data Transfer Object pentru Product
        │   │   └── CategoryDTO.java           # Data Transfer Object pentru Category
        │   ├── controller/
        │   │   ├── ProductController.java     # REST endpoints pentru produse
        │   │   └── CategoryController.java    # REST endpoints pentru categorii
        │   ├── service/
        │   │   ├── ProductService.java        # Logica de afaceri pentru produse
        │   │   └── CategoryService.java       # Logica de afaceri pentru categorii
        │   ├── repository/
        │   │   ├── ProductRepository.java     # Accesul la date pentru Product
        │   │   └── CategoryRepository.java    # Accesul la date pentru Category
        │   └── exception/
        │       ├── ResourceNotFoundException.java    # Excepție customizată
        │       ├── InvalidProductException.java      # Excepție customizată
        │       └── GlobalExceptionHandler.java       # Handler global pentru excepții
        └── resources/
            └── application.properties         # Configurația aplicației
```

---

## 🗄️ Model de Bază de Date

### Tabel: products
| Coloană | Tip | Constrângeri |
|---------|-----|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| name | VARCHAR(255) | NOT NULL |
| description | VARCHAR(255) | NOT NULL |
| price | DOUBLE | NOT NULL |
| quantity | INTEGER | NOT NULL |
| category_id | BIGINT | NOT NULL, FOREIGN KEY |

### Tabel: categories
| Coloană | Tip | Constrângeri |
|---------|-----|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| name | VARCHAR(255) | NOT NULL, UNIQUE |
| description | VARCHAR(500) | NULL |

### Relații
```
Category (1) ---< (Many) Product
  └─ Un categorie poate avea mai multe produse
  └─ Fiecare produs aparține unei categorii
  └─ Ștergerea categoriei șterge și produsele asociate
```

---

## 🔌 Endpoint-uri REST

### **Produse** (`/products`)

#### 1. Obține Toate Produsele
```http
GET /products
```
**Răspuns (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Laptop Dell",
    "description": "Laptop performant",
    "price": 1299.99,
    "quantity": 15,
    "categoryId": 1
  },
  {
    "id": 2,
    "name": "Mouse Logitech",
    "description": "Mouse wireless",
    "price": 49.99,
    "quantity": 50,
    "categoryId": 2
  }
]
```

#### 2. Obține Produs după ID
```http
GET /products/{id}
```
**Exemplu:** `GET /products/1`

**Răspuns (200 OK):**
```json
{
  "id": 1,
  "name": "Laptop Dell",
  "description": "Laptop performant",
  "price": 1299.99,
  "quantity": 15,
  "categoryId": 1
}
```

#### 3. Crează Produs Nou
```http
POST /products
Content-Type: application/json

{
  "name": "Monitor Samsung",
  "description": "Monitor 4K 27 inch",
  "price": 599.99,
  "quantity": 8,
  "categoryId": 1
}
```

**Răspuns (201 Created):**
```json
{
  "id": 3,
  "name": "Monitor Samsung",
  "description": "Monitor 4K 27 inch",
  "price": 599.99,
  "quantity": 8,
  "categoryId": 1
}
```

**Validări:**
- `name`: min 2, max 100 caractere, obligatoriu
- `description`: min 5, max 500 caractere, obligatoriu
- `price`: > 0, obligatoriu
- `quantity`: >= 0, obligatoriu
- `categoryId`: ID valid, obligatoriu

#### 4. Modifica Produs
```http
PUT /products/{id}
Content-Type: application/json

{
  "name": "Laptop Dell Updated",
  "description": "Laptop actualizat",
  "price": 1199.99,
  "quantity": 12,
  "categoryId": 1
}
```

**Răspuns (200 OK):** Produsul modificat

#### 5. Șterge Produs
```http
DELETE /products/{id}
```

**Exemplu:** `DELETE /products/1`

**Răspuns (204 No Content)**

#### 6. Căutare după Nume
```http
GET /products/search/by-name?name=laptop
```

**Răspuns (200 OK):** Lista de produse cu "laptop" în nume (case-insensitive)

#### 7. Produse din Categorie
```http
GET /products/category/{categoryId}
```

**Exemplu:** `GET /products/category/1`

**Răspuns (200 OK):** Toate produsele din categoria 1

#### 8. Căutare după Interval de Preț
```http
GET /products/search/by-price?minPrice=100&maxPrice=500
```

**Răspuns (200 OK):** Produsele cu preț între 100 și 500

#### 9. Produse cu Stoc Mic
```http
GET /products/stock/low?threshold=10
```

**Răspuns (200 OK):** Produsele cu stoc mai mic decât pragul (implicit 10)

---

### **Categorii** (`/categories`)

#### 1. Obține Toate Categoriile
```http
GET /categories
```

#### 2. Obține Categorie după ID
```http
GET /categories/{id}
```

#### 3. Crează Categorie
```http
POST /categories
Content-Type: application/json

{
  "name": "Electronice",
  "description": "Produse electronice și computer"
}
```

#### 4. Modifica Categorie
```http
PUT /categories/{id}
Content-Type: application/json

{
  "name": "Electronice Updated",
  "description": "Categorie actualizată"
}
```

#### 5. Șterge Categorie
```http
DELETE /categories/{id}
```

---

## 🛡️ Validări și Exception Handling

### Validări Implementate
- **Email/Format validation**: Prin decoratori Jakarta Validation
- **Range validation**: Preț > 0, Stoc >= 0
- **Length validation**: Nume și descriere în intervale definite
- **Null checks**: Câmpuri obligatorii

### Exception Handling Global
```java
GlobalExceptionHandler:
  ├─ ResourceNotFoundException      (404)
  ├─ InvalidProductException        (400)
  └─ Generic Exception Handler      (500)
```

Răspuns de eroare standard:
```json
{
  "timestamp": "2025-01-07T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Product not found with id: 999"
}
```

---

## 🚀 Pornire și Rulare

### Prerequisite
- Java 17+
- Maven 3.6+
- Git

### Build și Run

**1. Build applicației:**
```bash
cd Proj
mvn clean package
```

**2. Rulează aplicația:**
```bash
java -jar target/product-management-app-1.0.0.jar
```

**3. Verifică că rulează:**
- Aplicația pornește pe `http://localhost:8080`
- H2 Console disponibilă la `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:file:~/h2db/shopdb`
  - User: `sa`
  - Password: (lasa gol)

---

## 📊 Cazuri de Utilizare (Use Cases)

### 1. Manager de Magazin - Gestionare Stoc
```
1. Obține toate produsele: GET /products
2. Filtează produse cu stoc mic: GET /products/stock/low?threshold=5
3. Actualizează stocul: PUT /products/{id} (crește quantity)
```

### 2. Client - Căutare Produse
```
1. Caută după nume: GET /products/search/by-name?name=laptop
2. Filtează după preț: GET /products/search/by-price?minPrice=100&maxPrice=500
3. Vizualizează categoria: GET /products/category/1
```

### 3. Administrator - Gestionare Catalogu
```
1. Crează categorie: POST /categories
2. Adaugă produse: POST /products (cu categoryId)
3. Modifica preț: PUT /products/{id}
4. Șterge produs: DELETE /products/{id}
```

---

## 🔐 Caracteristici de Securitate

- **Validare Input**: Toate input-urile sunt validate
- **Exception Handling**: Protecție împotriva erorilor neașteptate
- **SQL Injection Prevention**: Prin utilizarea JPA/Hibernate
- **Error Messages**: Mesaje de eroare generice în produție

---

## 📈 Scalabilitate și Viitor

### Îmbunătățiri Potențiale
- Adăugare de autentificare/autorizare (Spring Security)
- Paginare și sortare avansate
- Caching cu Redis
- Logging detaliat (SLF4J)
- Testare unitară și integrare (JUnit, Mockito)
- API Documentation cu Swagger/OpenAPI
- Containerizare cu Docker
- CI/CD Pipeline

---

## 📝 Tehnologii și Framework-uri

| Tehnologie | Versiune | Scop |
|-----------|----------|------|
| Spring Boot | 3.2.0 | Framework principal |
| Spring Data JPA | 3.2.0 | ORM și acces date |
| H2 Database | Latest | Bază de date |
| Jakarta Validation | Latest | Validare input |
| Maven | 3.6+ | Build tool |
| Java | 17+ | Limbaj de programare |

---

## 📞 Suport și Contactare

Pentru probleme, sugestii sau întrebări, contactează développer-ul proiectului.

---

## 📄 Licență

Proiect individual pentru curs - Universitate

---

**Data creării:** 7 ianuarie 2025  
**Versiune:** 1.0.0  
**Status:** Complet și testat
