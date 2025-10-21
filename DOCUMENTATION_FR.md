# Documentation Fonctionnelle

## 1. Aperçu du projet
Ce projet Spring Boot expose un service de gestion de menus et de commandes généré à partir d’OpenAPI, puis étendu pour couvrir un scénario multi-restaurants.  
Les principaux objectifs sont :
- fournir l’accès aux plats via une API REST (`/api/v1/menus`),
- valider des commandes en vérifiant prix et disponibilité auprès du service Menu,
- exposer un nouvel endpoint `/api/v1/restaurants` qui retourne les restaurants et leurs plats,
- permettre la consommation des APIs depuis un client Node.js.

## 2. Architecture en couches
Le code respecte une organisation hexagonale (ports/adaptateurs) :

| Couche | Rôle | Package |
|--------|------|---------|
| **API (adapters web)** | Contrôleurs REST | `com.speed_liv.menu.api`, `org.openapitools.controllers` |
| **Application / Services** | Logique métier orchestrant les ports | `org.openapitools.services` |
| **Domaine** | Modèles métier manipulés par l’application | `org.openapitools.model.entity` |
| **Infrastructure** | Adaptateur de persistance lisant les données JSON | `org.openapitools.infrastructure.JPA` |

La classe `OpenApiGeneratorApplication` scanne l’ensemble des packages pour enregistrer automatiquement ces composants.

## 3. Source de données
- Fichier `src/main/resources/restaurants.json` : contient la liste des restaurants, chacun avec ses plats (`id`, `name`, `price`, `available`).  
- Le repository `JpaRestaurantRepository` charge ce fichier via Jackson, indexe les plats pour accélérer les recherches et expose un contrat `RestaurantRepository`.

## 4. Services applicatifs
- **RestaurantService** : fournit l’accès aux restaurants (`getAllRestaurants`, `getRestaurantById`, `getDishById`).  
- **MenuService** : agrège l’ensemble des plats exposés par le service REST généré (`menusGet`, `menusIdGet`). Il transforme les entités `Dish` en objets `Plat` issus du modèle OpenAPI.  
- **CommandeService** : vérifie la validité d’une commande avant de la valider. Les contrôles effectués :
  1. présence des identifiants restaurant/plat et du prix attendu,
  2. existence du restaurant et du plat associé,
  3. disponibilité du plat,
  4. concordance du prix (tolérance ±0,01 €).  
  En cas de succès, le total est calculé (`prix * quantité`) et renvoyé dans `OrderConfirmation`.

## 5. Endpoints REST
Tous les endpoints sont préfixés par `openapi.serviceMenu.base-path` (valeur par défaut `/api/v1`).

| Méthode | Chemin | Description | Réponse |
|---------|--------|-------------|---------|
| GET | `/menus` | Liste l’ensemble des plats disponibles. | `200` + `List<Plat>` |
| GET | `/menus/{id}` | Détail d’un plat (id numérique sous forme de chaîne). | `200` + `Plat` ou `404` |
| GET | `/restaurants` | Liste des restaurants et de leurs plats. | `200` + `List<Restaurant>` |
| POST | `/commandes` | Valide une commande. Corps attendu : `Order { restaurantId, platId, quantity, expectedUnitPrice }`. | `200` + `OrderConfirmation` si acceptée, `400` sinon |

Les modèles REST exposent :
- `Plat` (hérité de la spécification OpenAPI),
- `Restaurant` / `Dish` pour l’endpoint `/restaurants`,
- `Order` et `OrderConfirmation` pour le service commande.

## 6. Exécution et tests
### Lancement du service
```bash
mvn spring-boot:run
```
Par défaut, l’API écoute sur `http://localhost:8080/api/v1`.

### Batterie de tests
```bash
mvn test
```
Les tests `MenuServiceTest` et `CommandeServiceTest` valident le chargement des plats et les scénarios critiques de validation de commande.

## 7. Utilisation depuis un client Node.js
1. Lancer l’API Spring Boot comme indiqué ci-dessus.  
2. Configurer le client Node.js pour cibler `http://localhost:8080/api/v1`.  
3. Scénarios typiques :
   - Récupérer la carte : `GET /menus`.
   - Sélectionner un plat précis : `GET /menus/{id}`.
   - Visualiser les restaurants avec leurs plats : `GET /restaurants`.
   - Valider une commande :
     ```http
     POST /commandes
     Content-Type: application/json

     {
       "restaurantId": 1,
       "platId": 1,
       "quantity": 2,
       "expectedUnitPrice": 8.5
     }
     ```
   - Traiter la réponse du service : vérifier le champ `accepted`, interpréter `totalPrice` et `message`.

## 8. Extensions possibles
- Persistance : remplacer le chargement JSON par une base relationnelle (implémentation alternative de `RestaurantRepository`).  
- Sécurité : ajouter un filtre d’authentification HTTP ou OAuth2.  
- Couverture fonctionnelle : enrichir la spécification OpenAPI pour exposer la création/administration des plats.
