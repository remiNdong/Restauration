# Restauration
***
* Site qui propose de recuperer une liste de restaurants au format json à l'url "https://opendata.paris.fr/api/records/1.0/search/?dataset=restaurants-casvp&q=&facet=code&facet=nom_restaurant&facet=type.json" et de les enregistrer dans la base de données de l'application.
* Les utilisateurs peuvent alors rechercher des restaurants par ville ou par standing. Les utilisateurs peuvent s'inscrire pour mettre des notes et des commentaires ainsi qu'ajouter des retaurants à leur favoris.

***

## Informations generales
*Le projet a été conçu avec spring tools (Eclipse avec serveur web intégré) , hibernate , mysql  et  thymeleaf , jquery, bootstrap pour les vues


## Installation

1. Créez une nouvelle base de données dans mysql en important le fichier restauration.sql présent dans src/main/resources/static/baseDeDonnees/restauration.sql 
2. Dans le fichier src/main/resources/application.properties  , modifier les propriétés url, username et password avec les informations correspondantes à votre base de données fraichement crée.
3. Télecharger le fichier war du projet et importez le dans votre IDE. (Initialement il a été crée avec Eclipse)
4. Démarrer le projet dans votre IDE avec votre serveur. 

