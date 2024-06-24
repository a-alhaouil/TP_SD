# 3eme Partie
## Troisième Partie : Spring Angular

### Objectif : 
#### Développer une application qui permet de gérer le payement des étudiants. Chaque étudiants peut effectuer plusieurs payements
- Chaque étudiant est défini par son : id, firstName, lastName, email, sa filière, sa photo,
- Chaque Payement est défini par son id, son code, sa date, son type (CASH, CHECK,TRANSFER), son status (CREATED, VALIDATED, REJECTED), file (fichier pdf représentant le reçu de payement)

#### A: Développer et Tester la partie Backend avec Spring. :  https://www.youtube.com/watch?v=oTuAXfD2M1g
  ##### 1. Créer les entités JPA
  ##### 2. Créer les interfaces JPARepository basées sur Spring Data
  ##### 3 . Générer des données aléatoires concernant quelques étudiants et pour chaque étudiants des payements
  ##### 4. Créer une Web service RESTful (ResController) qui permet d'exposer les fonctionnalisés suivantes :
        - Consulter tous les payements
        - Consulter un payement sachant son id
        - Consulter les payemenst d'un type donné
        - Consulter les payements d'un status donné
        - Consulter les payements d'un étudiant donné
        - Consulter les payements d'une filière donnée
        - Consulter tous les étudiants
        - Consulter les étudiants d'une filière donnée
        - Consulter un étudiant sachant son code
        - Effectuer un nouveau payement avec les données et le reçu de payement au format pdf
        - Mettre à jour le status d'un payement
        - Consulter le reçu d'un payement  (fichier pdf) 
  ##### 5 - Tester le backend en utilisant un client REST (Postman) et avec SWAGGER UI
  ##### 6 - Faire un refactoring du code en utilisant la couche service, les DTOs et les Mappers
  spring securite:
  
  ![Capture d'écran 2024-06-22 130327](https://github.com/a-alhaouil/TP_SD/assets/152272492/7745a603-e7e9-4600-aa14-91057cd37cb0)
  
  after deactivate Spring securite:
  "localhost/8021/students"
  
  ![Capture d'écran 2024-06-24 060514](https://github.com/a-alhaouil/TP_SD/assets/152272492/435815b9-0057-42f9-a04f-c3112b05e432)
  
  "localhost/8021/students/112233"
  
  ![Capture d'écran 2024-06-24 060501](https://github.com/a-alhaouil/TP_SD/assets/152272492/3d16bcf9-5f16-4a8a-a904-04fb9e741f76)

  Adding the spring openapi dependency gives the following result:
  
  ![Capture d'écran 2024-06-24 060938](https://github.com/a-alhaouil/TP_SD/assets/152272492/6243d1af-e0c8-4bd8-9bb7-4bd329b54725)
  ![Capture d'écran 2024-06-24 061140](https://github.com/a-alhaouil/TP_SD/assets/152272492/930861af-d9b0-4a1c-b331-a4550bcbd731)
  ![Capture d'écran 2024-06-24 061231](https://github.com/a-alhaouil/TP_SD/assets/152272492/6fb0267c-ccf0-4c25-9055-4e1c449e6e9a)
  ![Capture d'écran 2024-06-24 060903](https://github.com/a-alhaouil/TP_SD/assets/152272492/f8cca421-862b-4eef-9bfc-4a48286e49b9)
  ![Capture d'écran 2024-06-24 060717](https://github.com/a-alhaouil/TP_SD/assets/152272492/7947eb07-86f7-43f6-8a52-661a7971560d)

  now for h2 database students:

  ![Capture d'écran 2024-06-24 061630](https://github.com/a-alhaouil/TP_SD/assets/152272492/76bf6301-2b49-4587-b21d-282fad4d1843)
  ![Capture d'écran 2024-06-24 061607](https://github.com/a-alhaouil/TP_SD/assets/152272492/f9a7aa2c-3c42-454d-bb88-c58762e620e9)


#### B : Développer la partie frontend en utilisant Angular avec Angular Material pour la partie design : https://www.youtube.com/watch?v=QqmoMDGr3Ww
 #####  1. Créer un projet angular 
 #####  2. Intégrer Angular Material
 #####  3. Créer une page template contenant un Toolbar avec une barre de menu et un Side Menu
 #####  4. Créer les différents component de l'application
 #####  5. Créer un système d'authentification simple qui oblige l'utilisateur à s'authentifier avant d'accéder à l'application. Dans un premier temps, les utilisateurs et les rôles qui ont le droit d'accéder à l'application sont stockés de manière statique dans le service d'authentification. Protéger les routes par Un Guard d'authentification et un Guard pour les autorisations
 #####  6. Créer les components fonctionnels de l'application :
           - Afficher les payements avec une Pagination frontend
           - Afficher et chercher les étudiants
           - Afficher le Dashboard d'un étudiant (Infos et Payements)
           - Ajouter un nouveau payement en uploadant le reçu de payement
           - Consulter le détail d'un payement
           - Mettre à jour le status d'un payement
  C : Sécurité avec Spring Security et Json Web Token
           - Sécuriser le backend
           - Sécuriser le front end

    Authentication interface for user1:
    
    ![Capture d'écran 2024-06-24 065521](https://github.com/a-alhaouil/TP_SD/assets/152272492/80420d9a-a80d-4898-86a4-3339a94b7cda)
    ![Capture d'écran 2024-06-24 065528](https://github.com/a-alhaouil/TP_SD/assets/152272492/d7421972-a96f-4761-be63-429abe71c8ba)

    Authentication interface for admin:
    
    ![Capture d'écran 2024-06-24 065545](https://github.com/a-alhaouil/TP_SD/assets/152272492/2a64887b-70a8-4ba1-ac2e-b999fdd2011e)

    ![Capture d'écran 2024-06-24 065539](https://github.com/a-alhaouil/TP_SD/assets/152272492/cb6612d8-18b2-4147-a8d3-c488b44a24f0)

    final interface with paginator and sort:

    ![Capture d'écran 2024-06-24 065759](https://github.com/a-alhaouil/TP_SD/assets/152272492/3bd7fe1a-95f4-47b1-9aab-5741796408ff)




