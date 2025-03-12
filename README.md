# Exam Management Application

## Introduction
Ce projet à été développé dans le cadre de mes études à l'université d'Aix-Marseille en Master MIAGE. Il a été réalisé dans le cadre de l'UE **"Architecture et Programmation par Composants"**.
Cette application est une solution développée avec Spring Boot, conçue pour "aider la Faculté d'Économie et de Gestion (FEG) dans l'organisation et la gestion des examens". 
L'objectif est de simplifier la gestion des examens, des cours, des quiz, des questions et des inscriptions des étudiants, afin de réduire les redondances et les erreurs humaines dans le processus administratif.

## Contexte du Projet
Les objectifs du projet sont les suivants :
- La création, la consultation, la modification et la suppression (CRUD) des entités telles que les **Cours**, **Examens**, **Questions**, **Quiz** et **Utilisateurs**.
- La gestion des relations entre ces entités à l'aide des annotations JPA appropriées (par exemple, @ManyToMany, @OneToMany, @ManyToOne) pour modéliser les associations bidirectionnelles.
- L'exposition de ces fonctionnalités via une API REST afin que les différents acteurs (enseignants, administrateurs, étudiants) puissent interagir avec le système.

Le projet s'appuie sur le modèle relationnel (fourni dans la consigne) et sur les cas d'usage définis plus bas, permettant aux utilisateurs de :
- Créer et gérer des examens.
- Composer des quiz en y ajoutant des questions.
- Associer des étudiants à des examens pour qu'ils puissent consulter les évaluations dans leur espace personnel.

## Technologies Utilisées
- **Spring Boot** pour la création de l'API REST et pour l'accès à la base de données et la gestion des entités.
- **Python (avec la bibliothèque Requests)** pour simuler des scénarios d'utilisation via des scripts de test.

## Cas d'Usage et Scripts de Test
### 1. Gestion des Examens par l'Enseignant (GestionExamen.py)
- **But :** Un enseignant souhaite créer un nouvel examen. Une fois l’examen enregistré, il peut le modifier ou le supprimer si nécessaire. L’administration et les étudiants concernés peuvent ensuite consulter l’examen via l’application.
- **Étapes :**
  1.	L’enseignant se connecte
  2.	Il accède à la section "Gestion des examens"
  3.	Il crée un examen en remplissant un formulaire, dans lequel il sélectionne la classe concernée par cet examen
  4.	L’examen est enregistré et est visible dans son listing d’examen
  5.	L’examen est visible par les étudiants concernés, dans la section  "Mes examens"


**Script :**
```bash
python3 GestionExamen.py
```

### 2. Gestion des Questions et Quiz (GestionQuestionQuiz.py)
- **But :** Un enseignant ajoute des questions à un quiz pour un examen. Il accède au module de gestion des questions, saisit l’intitulé, les options de réponse et la bonne réponse, puis associe la question à un quiz. Une fois le quiz validé, il sera intégré à l’examen associé.
- **Étapes :**
  1.	L’enseignant se connecte
  2.	Il accède à la page "Gérer mes quizs" pour créer un quiz
  3.	Il crée plusieurs questions qu’il associe à son quiz précédemment créé
  4.	Une fois le quiz complété, il le  rattache à un examen



**Script**
```bash
python3 GestionQuestionQuiz.py
```

### 3. Attribution des Étudiants à un Examen par l’Administrateur
- **But :** Un administrateur attribue des étudiants à un examen. Il sélectionne l’examen concerné, recherche les étudiants dans la base de données et les associe à l’évaluation. Les étudiants peuvent consulter les détails de l’examen dans la section  "Mes examens".
- **Étapes :**
  1.	L’administrateur se connecte
  2.	Il accède à la section "Gestion des étudiants et examens"
  3.	Il sélectionne un examen
  4.	Il sélectionne les étudiants concernés
  5.	Les étudiants voient l’examen apparaître dans leur espace personnel


**Script**
```bash
python3 GestionEtudian.py
```

