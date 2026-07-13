#  ClinicCare

Système de gestion de dossiers médicaux (patients, médecins, consultations) développé en Java avec JPA/Hibernate et MySQL. Ce projet répond à un besoin réel des petites cliniques qui gèrent encore leurs dossiers sur papier ou Excel, en proposant une base de données relationnelle claire et une architecture DAO robuste.

## Fonctionnalités

- Gestion des **patients** (nom, prénom, date de naissance, groupe sanguin)
- Gestion des **médecins** (nom, prénom, spécialité)
- Gestion des **consultations** reliant un patient à un médecin (date, diagnostic)
- Recherche des consultations par patient ou par médecin (requêtes JPQL)
- CRUD complet sur chaque entité via le pattern DAO

##  Stack technique

| Technologies | Usage |
|---|---|
| Java 17 | Langage principal |
| JPA / Hibernate 7 | Spéc. et impl. ORM |
| MySQL  | Base de données (local) |
| Maven | Gestion des dépendances et build |
| Lombok | Réduction du code boilerplate |
| JUnit 5 + H2 | Tests unitaires en base mémoire |
| GitHub Actions | Intégration continue (build + tests automatiques) |

## Modèle de données

```
Patient (1) ──────< (N) Consultation (N) >────── (1) Medecin
   │                        │                          │
   id                       id                         id
   nom                      date                       nom
   prenom                   diagnostic                 prenom
   dateNaissance            patient_id (FK)            specialite
   groupeSanguin            medecin_id (FK)
```

- Un **patient** peut avoir plusieurs consultations, mais une consultation appartient à un seul patient.
- Un **médecin** peut avoir plusieurs consultations, mais une consultation est assurée par un seul médecin.
- Une **consultation** relie exactement un patient et un médecin.

## Architecture du projet

```
ClinicCareJPA/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── metier/        → Entités JPA (Patient, Medecin, Consultation)
│   │   │   └── dao/           → Interfaces + implémentations DAO
│   │   │   
│   │   └── resources/
│   │       └── META-INF/
│   │           └── persistence.xml   → Config MySQL (production)
│   └── test/
│       ├── java/
│       │   └── Main.java      → Point d'entrée / démonstration 
│       │   └── dao/           → Tests JUnit (PatientDAOTest, MedecinDAOTest, ConsultationDAOTest)
│       └── resources/
│           └── META-INF/
│               └── persistence.xml   → Config H2 (tests)
├── .github/workflows/ci.yml   → Pipeline CI (build + tests)
└── pom.xml
```

## Installation et lancement

### Prérequis
- Java 17
- Maven
- MySQL  installé et lancé localement

### 1. Cloner le projet
```bash
git clone https://github.com/Juleshounsavi/ClinicCare.git
cd ClinicCare
```

### 2. Créer la base de données
```sql
CREATE DATABASE clinicare_db;
```
Les tables sont générées automatiquement par Hibernate au premier lancement (`hibernate.hbm2ddl.auto=update`).

### 3. Configurer le mot de passe MySQL
Le mot de passe n'est **jamais stocké en dur** dans le code. Il est lu depuis une variable d'environnement :

```bash
# Windows (CMD)
set DB_PASSWORD=votre_mot_de_passe

# Windows (PowerShell)
$env:DB_PASSWORD="votre_mot_de_passe"

# Linux / macOS
export DB_PASSWORD=votre_mot_de_passe
```

### 4. Lancer l'application
```bash
mvn clean compile exec:java -Dexec.mainClass="Main"
```
Ou directement depuis IntelliJ IDEA en exécutant `Main.java`.

### 5. Lancer les tests
```bash
mvn test
```
Les tests utilisent une base H2 en mémoire — aucune configuration MySQL requise pour les exécuter.

## Sécurité

- Le mot de passe de la base de données est injecté via variable d'environnement (`System.getenv("DB_PASSWORD")`), jamais commité dans le dépôt.
- `persistence.xml` ne contient aucune information sensible.

## Tests

Chaque DAO dispose de sa propre classe de test couvrant les opérations principales (`save`, `findById`, `findAll`, `update`, `delete`) ainsi que les requêtes spécifiques (`findByPatientId`, `findByMedecinId`). Les tests s'exécutent automatiquement à chaque push via GitHub Actions.
