# PDF Generator API - Spring Boot POC

## 📄 Description courte

> API Spring Boot permettant de générer des fichiers PDF (lettres) dynamiquement à partir de données structurées (adresse, contenu, logo et signature).

---

## 📘 Description complète

Ce projet est un **Proof of Concept (POC)** développé avec Spring Boot permettant de générer des documents PDF côté serveur via une API REST.

L’objectif est de démontrer la génération dynamique de lettres en PDF à partir d’un payload JSON contenant :

* une adresse structurée
* un contenu textuel
* un logo
* une signature (texte ou image)

Le PDF est généré à la volée en Java à l’aide de la bibliothèque iText.

---

## 🚀 Fonctionnalités

* Génération de PDF via endpoint REST
* Gestion d’une adresse structurée (`AddressDto`)
* Ajout de contenu texte libre
* Insertion d’un logo (image)
* Gestion d’une signature (texte ou image)
* Validation des données avec Jakarta Validation (`@NotNull`, `@NotBlank`, etc.)
* Logging des opérations

---

## 🧱 Stack technique

* Java
* Spring Boot
* iText
* Jakarta Validation
* Lombok

---

## 📡 API

### `POST /api/pdf`

Génère un fichier PDF à partir des données fournies.

### Exemple de requête

```json
{
  "address": {
    "houseNumber": 4,
    "street": "Brianne Lake",
    "city": "West Elroytown",
    "postalCode": 3717,
    "country": "Christmas Island"
  },
  "contenu": "Contenu de la lettre...",
  "logoUrl": "path/to/logo.png",
  "signature": {
    "firstName": "Michaela",
    "lastName": "Abernathy",
    "title": "Human Identity Administrator",
    "signatureImageUrl": "path/to/signature.png"
  }
}
```

### Exemple de réponse

```json
{
  "path": "/absolute/path/to/generated/letter.pdf"
}
```

---

## ⚠️ Limitations actuelles (POC)

* Gestion basique du layout PDF
* Pas de template avancé (HTML → PDF)
* Gestion des images dépendante du système de fichiers local
* Pas de stockage distant (S3, etc.)
* Pas de signature numérique

---

## 🔮 Améliorations possibles

* Utilisation de templates HTML/CSS pour le rendu PDF
* Upload de fichiers (logo/signature) via multipart
* Stockage cloud des documents
* Ajout de signature électronique (certificat)
* Mise en page avancée (positionnement précis, pagination)

---

## ⚖️ Licence

Attention : iText est sous licence AGPL.
Une utilisation en projet propriétaire nécessite une licence commerciale.

---

## 🎯 Objectif

Ce projet sert de base pour :

* comprendre la génération de PDF en Java
* expérimenter une API REST de génération de documents
* préparer une architecture extensible pour des cas métiers réels

---

Si tu veux, je peux aussi te faire :

* un **README plus “pro” (badges, sections clean, install, run, etc.)**
* ou une version orientée **portfolio / recrutement** 👍
