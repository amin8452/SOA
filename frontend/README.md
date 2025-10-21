# SpeedLiv Frontend

Interface React moderne qui consomme le backend Spring Boot du projet.

## Lancement en developpement

```bash
cd frontend
npm install
npm run dev
```

L'application est disponible sur http://localhost:5173. Un proxy Vite redirige automatiquement les requetes `http://localhost:5173/api/v1` vers `http://localhost:8080`, ce qui correspond au port par defaut du backend Spring Boot.

## Variables denvironnement

- `VITE_API_BASE_URL` : URL absolue de lAPI (ex: `http://localhost:8080/api/v1`). Laisser vide pour utiliser un chemin relatif (`/api/v1`) qui fonctionne avec le proxy Vite et les assets seris par Spring Boot.
- `VITE_PROXY_TARGET` : cible du proxy Vite en developpement (par defaut `http://localhost:8080`).

Creez un fichier `.env.local` a la racine du dossier `frontend` si vous souhaitez personnaliser ces valeurs.

> Astuce : si vous voyez une erreur "Network Error", assurez-vous que le backend Spring Boot tourne bien sur le port cible (par defaut 8080).

## Construction production

```bash
npm run build
```

Les fichiers optimises sont generes dans `frontend/dist`. Le bundle peut etre servi par nimporte quel serveur HTTP statique ou integre a lapplication Spring Boot.
