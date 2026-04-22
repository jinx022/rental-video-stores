# Rental Video Stores - Backend API

![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.4.2-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

Un sistema backend RESTful sviluppato in Java e Spring Boot per la gestione di un network di videonoleggi (multi-store). L'applicativo gestisce l'inventario dei film, i clienti, i noleggi attivi e lo storico delle transazioni, includendo logiche di business avanzate come il tracciamento delle date di restituzione tramite chiavi primarie composite.

Questo progetto è stato realizzato come prova finale del percorso di formazione intensiva Java Enterprise (Forma.temp).

## Funzionalità Principali

* **Gestione Inventario Multi-Store:** Endpoint dedicati per aggiungere film agli inventari di specifici negozi fisici.
* **Gestione Noleggi (Rentals):** API per emettere nuovi noleggi o aggiornare lo stato di restituzione di un film.
* **Query Analitiche e Reportistica:** Estrazione di KPI come i film più noleggiati in assoluto, conteggio dei noleggi per negozio in un dato periodo e ricerca di film in base al cast di attori.
* **Persistenza Dati Complessa:** Mappatura di 13 Entità relazionali (tra cui `Customer`, `Film`, `Rental`, `Inventory`, `Store`) utilizzando Spring Data JPA e Hibernate.

## Stack Tecnologico

* **Linguaggio:** Java 21
* **Framework:** Spring Boot 3.4.2, Spring Data JPA, Spring Validation
* **Database:** MariaDB / MySQL
* **Utility:** Lombok
* **Build Tool:** Maven

## Struttura Architetturale (Pattern MVC)

Il progetto implementa una rigorosa separazione delle responsabilità:
* I `Controller` gestiscono le richieste HTTP in ingresso mappate sotto il path `/api`.
* I `Service` contengono la logica di business.
* I `Payload` (Request/Response) incapsulano i dati in ingresso e uscita (es. `FilmResponse`, `RentalResponse`).

## Come avviare il progetto in locale

### Prerequisiti
* Java JDK 21+ installato
* MariaDB o MySQL Server in esecuzione
* Maven

### Configurazione
1. Clona il repository.
2. Il progetto utilizza variabili d'ambiente per la connessione al database. Assicurati di impostare le seguenti variabili nel tuo ambiente di sviluppo o IDE:
   * `DB_VENDOR` (es. `mariadb` o `mysql`)
   * `DB_HOSTNAME` (es. `localhost`)
   * `DB_PORT` (es. `3306`)
   * `DB_NAME` (es. `video_store_exam`)
   * `DB_USERNAME`
   * `DB_PASSWORD` (se richiesta dal tuo DB, ricordati di decommentare la riga in `application.yml`)
3. Il server si avvierà di default sulla porta **8081**.
4. Esegui l'applicazione tramite Maven:
   ```bash
   mvn spring-boot:run

## 🔌 Elenco degli Endpoint API Principali

Di seguito gli endpoint esposti dall'applicativo (base URL: `http://localhost:8081/api`):

### Gestione Film e Inventario
| Metodo | Endpoint | Descrizione |
| :--- | :--- | :--- |
| `PUT` | `/update-film/{filmId}` | Aggiorna le informazioni di un film esistente. |
| `POST` | `/add-film-to-store/{storeId}/{filmId}` | Aggiunge una copia del film all'inventario di un negozio. |
| `GET` | `/find-films-by-language/{languageId}` | Restituisce la lista dei film filtrati per lingua. |
| `GET` | `/find-rentable-films?title={title}` | Trova i film disponibili per il noleggio cercandoli per titolo. |
| `GET` | `/find-films-by-actors?actorIds={id}` | Trova i film in cui hanno partecipato tutti gli attori specificati. |

### Gestione Noleggi
| Metodo | Endpoint | Descrizione |
| :--- | :--- | :--- |
| `PUT` | `/add-update-rental/{customerId}/{inventoryId}` | Crea un nuovo noleggio o registra la restituzione di un film. |
| `GET` | `/find-all-films-rent-by-one-customer/{customerId}` | Ottiene lo storico di tutti i film noleggiati da uno specifico cliente. |

### Statistiche e Reportistica
| Metodo | Endpoint | Descrizione |
| :--- | :--- | :--- |
| `GET` | `/find-film-with-max-number-of-rent` | Restituisce la classifica dei film più noleggiati in assoluto. |
| `GET` | `/count-rentals-in-date-range-by-store/{storeId}` | Conta i noleggi effettuati in un negozio in un dato range di date. |
| `GET` | `/count-customers-by-store/{storeName}` | Restituisce il numero di clienti unici che hanno noleggiato in un negozio. |
