# 🎵 SpotiHome

**SpotiHome** est une application web de diffusion musicale destinée à une association culturelle. Elle permet aux utilisateurs d'écouter et de gérer des morceaux de musique tout en garantissant la protection des données et l’hébergement local sur serveurs Linux.

---

## 📌 Objectifs du projet

- Offrir une alternative aux grandes plateformes musicales.
- Garantir l’hébergement en interne et la maîtrise des données utilisateurs.

---

## 👥 Profils utilisateurs

### 👨‍💼 Administrateur
- Gère les types et genres musicaux.
- Ajoute / modifie / supprime des morceaux.

### 🎧 Auditeur
- Écoute les musiques.
- Crée, modifie, supprime des playlists personnalisées.
- Partage des morceaux via des liens générés automatiquement.

---

## ⚙️ Fonctionnalités techniques

- Application full-stack avec **Vue.js** (front) et **Java EE / WildFly** (back).
- Communication via **API REST**.
- Hébergement :
  - **Debian 12** (serveur applicatif)
  - **Ubuntu Server** (serveur BDD MySQL / MariaDB)
- Serveur web : **Apache2**
- Architecture 3-tiers : front / back / base de données

---

## 🧠 Compétences mobilisées

- Conception d’un MCD et base de données relationnelle.
- Développement front-end (Vue.js)
- Développement back-end (Java EE, REST)
- Sécurisation des accès selon les rôles
- Déploiement sur Linux

---

## 🔐 Connexions de démonstration

| Rôle         | Login                | Mot de passe      |
|--------------|----------------------|-------------------|
| Administrateur | `admin@test.com`     | `/:Passwd123_`     |
| Auditeur       | `auditeur@test.com`  | `/:Passwd123_`     |

---

## 🛠️ Technologies utilisées

| Catégorie             | Technologie         |
|-----------------------|---------------------|
| Front-end             | Vue.js              |
| Back-end              | Java EE (WildFly)   |
| BDD                   | MySQL / MariaDB     |
| Serveur web           | Apache2             |
| Système               | Debian / Ubuntu     |
| Versioning            | Git (GitHub)        |

---

## 🧩 Accès au projet

- 🌐 **Site en ligne** : [https://spotihome.d4cd13ger.fr](https://spotihome.d4cd13ger.fr)
- 📁 **Code source** : [GitHub - SpotiHome VUE](https://github.com/Zacharie-Julien/SpotiHome-VUE)

---

## 📅 Période de réalisation
📍 *Du 10 avril 2025 au 10 juin 2025 – Réalisé à Marseille dans le cadre du BTS SIO SLAM*

---

## 📷 Aperçu de l’application
- Authentification sécurisée
- Gestion de musiques, playlists et partage
- Interfaces dédiées administrateurs et auditeurs
- Maquettes modernes et responsives

---

## 📄 Licence
Projet pédagogique réalisé dans le cadre du BTS SIO SLAM — usage libre à des fins éducatives.

---



# 📦 SpotiHome – Services Front-End (JavaScript)

Ce dossier regroupe les services JavaScript utilisés pour communiquer avec l’API REST de l’application **SpotiHome**, plateforme musicale à usage associatif.  
Chaque fichier est dédié à un domaine fonctionnel spécifique (authentification, musique, genres, playlists, etc.).

---

## 📁 Fichiers inclus

| Fichier               | Description                                                             |
|------------------------|-------------------------------------------------------------------------|
| `loginServices.js`     | Authentification des utilisateurs et redirection selon le rôle          |
| `musicsServices.js`    | Récupération, ajout, et upload de musiques                              |
| `genreServices.js`     | Gestion des genres musicaux (CRUD)                                      |
| `typeServices.js`      | Gestion des types musicaux (CRUD)                                       |
| `playListServices.js`  | Gestion des playlists et de leur contenu                                |

---

## 📡 Fonctionnement

Chaque service utilise `fetch()` pour communiquer avec l'API `http://localhost:8080/spotiHome/webresources/`.  
Les appels sont **asynchrones** (`async/await`) et incluent une **gestion d’erreur basique** (throw d’une `Error` en cas d’échec réseau).

---

## 🧩 Détail par service

### 🔐 `loginServices.js`
```js
loginUser(login, password)
```
- Méthode `GET`
- Authentifie l'utilisateur avec ses identifiants.
- Redirige vers `/homeAdmin` ou `/home` selon le rôle contenu dans le JWT.

---

### 🎵 `musicsServices.js`
- `getAllMusics()` – Liste toutes les musiques.
- `getAllMusicByPlayListId(id)` – Liste les musiques d'une playlist.
- `addMusic(...)` – Ajoute une musique (métadonnées uniquement).
- `uploadMusicFile(file)` – Upload du fichier MP3 via `FormData`.

---

### 🎼 `genreServices.js`
- `getAllGenre()` – Liste tous les genres.
- `addGenre(label)` – Ajoute un genre.
- `deleteGenreById(id)` – Supprime un genre par ID.
- `upDateGenre(label, id)` – Met à jour un genre.

---

### 🧾 `typeServices.js`
Fonctionne de manière identique à `genreServices.js`, mais pour les **types musicaux** (ex. : Instrumental, Acoustique, etc.)

---

### 📂 `playListServices.js`
- `getPlayListById(id)` – Récupère la ou les playlists d’un auditeur.
- `deletePlayListById(id)` – Supprime une playlist.
- `addPlaylist(label, id_auditor)` – Crée une nouvelle playlist.
- `updatePlaylist(label, id)` – Met à jour le nom d'une playlist.
- `addMusicToPlaylist(id_music, id_playlist)` – Associe une musique à une playlist.

---

## ✅ Sécurité & Authentification

- Toutes les requêtes critiques utilisent `credentials: 'include'` pour inclure les cookies d’authentification.
- Aucune donnée sensible n’est stockée côté front.

---

## 🔧 À améliorer

- Ajouter une vraie **gestion centralisée des erreurs** (ex. interception 401/403).
- Extraire l’URL de base (`http://localhost:8080/...`) dans une variable de configuration.
- Ajouter des `try/catch` dans les composants Vue pour traiter les erreurs côté utilisateur.

---

## 📝 Auteur

Développé par **Zacharie Julien** – Projet présenté dans le cadre du **BTS SIO SLAM 2025**

---

## 📄 Licence

Usage pédagogique uniquement. Reproduction interdite sans autorisation explicite.

