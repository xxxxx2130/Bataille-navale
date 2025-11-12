# 🚢 Bataille Navale

**Technologies :** Java • MVC • Swing • Design Patterns • IA  
**Contexte :** Projet universitaire • Licence 2 Informatique • Université de Caen  
**Date :** Avril 2025  
**Équipe :** 4 développeurs

## 🎮 Description

Implémentation complète du jeu de Bataille Navale avec une architecture **MVC rigoureuse**, interface graphique **Swing** et **IA intelligente**. Développé dans le cadre du module "Design Patterns & Interfaces Graphiques".

## 🏗️ Architecture

### Pattern MVC
- **Modèle** : `Game`, `Mer`, `Navire`, `Player`, `Tir`, `Position`
- **Vue** : `GameView`, `MerView`, `StatusPanel` 
- **Contrôleur** : Gestion des événements et coordination modèle-vue

### Design Patterns Implémentés
- **Observer** : Synchronisation modèle-vue
- **Strategy** : Stratégies d'IA (Random vs Intelligente)
- **Factory** : Création des joueurs
- **Template Method** : Classe `AbstractPlayer`

## 👥 Mes Contributions

### 🎯 Modélisation & Conception
- Conception **UML complète** du système
- Modélisation des **diagrammes de classes et de séquences**
- Architecture **MVC rigoureuse**

### 📐 Règles du Jeu & Logique Métier
- Implémentation complète des **règles de la bataille navale**
- Système de **validation des tirs et des placements**
- **Détection des navires coulés**
- Gestion des **états de jeu** (en cours, terminé, gagnant)

### 🤖 Intelligence Artificielle
- Implémentation de l'**IA Random** : stratégie aléatoire basique
- Développement de l'**IA Avancée** : algorithme de chasse intelligent
- Système de **mémorisation des tirs réussis**
- Stratégie de **concentration des attaques** autour des cibles touchées

### ⚙️ Algorithmes de Jeu
- **Placement aléatoire des navires** avec contraintes
- **Vérification des collisions** et débordements
- **Orientation aléatoire** (horizontal/vertical)
- **Optimisation du placement** des grands navires

## 🚀 Fonctionnalités Implémentées

### 🎯 Règles du Jeu
- ✅ Validation des placements de navires
- ✅ Détection des tirs touchés/coulés
- ✅ Gestion des tours de jeu
- ✅ Vérification des conditions de victoire
- ✅ Système de positions et coordonnées

### 🤖 Stratégies IA
- **IA Random** : Sélection aléatoire parmi cases disponibles
- **IA Avancée** : 
  - Mémorisation des tirs réussis
  - Concentration des attaques autour des cibles
  - Éviction des cases déjà attaquées
  - Stratégie de chasse progressive

### 🎨 Interface Utilisateur
- Double grille interactive (joueur + adversaire)
- Feedback visuel immédiat (tirs ratés/réussis)
- Indication des navires coulés
- Messages de statut en temps réel

### ⚙️ Personnalisation
- Taille de grille configurable
- Choix des types de joueurs (Humain, IA Random, IA Intelligente)
- Système de noms personnalisés

## 🛠️ Installation et Exécution

### Prérequis
- Java JDK 8 ou supérieur
- Apache Ant (pour le build)

### Commandes de Build
```bash
ant init      # Initialisation du projet
ant compile   # Compilation
ant run       # Exécution (console ou graphique)
ant javadoc   # Génération documentation
ant packaging # Création du JAR
ant clean     # Nettoyage
