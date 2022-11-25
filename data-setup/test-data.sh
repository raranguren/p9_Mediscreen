# Populate patient information

curl -d "family=TestNone&given=Test&dob=1966-12-31&sex=F&address=1 Brookside St&phone=100-222-3333" -X POST http://localhost:8081/patient/add
curl -d "family=TestBorderline&given=Test&dob=1945-06-24&sex=M&address=2 High St&phone=200-333-4444" -X POST http://localhost:8081/patient/add
curl -d "family=TestInDanger&given=Test&dob=2004-06-18&sex=M&address=3 Club Road&phone=300-444-5555" -X POST http://localhost:8081/patient/add
curl -d "family=TestEarlyOnset&given=Test&dob=2002-06-28&sex=F&address=4 Valley Dr&phone=400-555-6666" -X POST http://localhost:8081/patient/add

# Populate notes for patient history

curl -d "patId=1&e=Le patient déclare se sentir 'en pleine forme'. Poids égal ou inférieur au niveau recommandé." -X POST http://localhost:8082/patHistory/add

curl -d "patId=2&e=Le patient déclare qu'il ressent beaucoup de stress au travail \nLe patient se plaint également que son audition semble anormale depuis quelque temps." -X POST http://localhost:8082/patHistory/add
curl -d "patId=2&e=Le patient déclare avoir eu une réaction aux médicaments au cours des 3 derniers mois.\nLe patient se plaint également que son audition continue d'être problématique." -X POST http://localhost:8082/patHistory/add

curl -d "patId=3&e=Le patient déclare qu'il est un fumeur à court terme." -X POST http://localhost:8082/patHistory/add
curl -d "patId=3&e=Le patient déclare avoir arrêté de fumer l'année dernière \nLe patient se plaint également de périodes de respiration anormales.\nRapports de laboratoire Cholestérol LDL élevé" -X POST http://localhost:8082/patHistory/add

curl -d "patId=4&e=Le patient déclare que monter les escaliers est devenu difficile.\nLe patient se plaint également d'être essoufflé.\nLes résultats de laboratoire indiquent la présence d'anticorps, une réaction élevée aux médicaments." -X POST http://localhost:8082/patHistory/add
curl -d "patId=4&e=Le patient déclare avoir mal au dos lorsqu'il reste assis pendant une longue période." -X POST http://localhost:8082/patHistory/add
curl -d "patId=4&e=Le patient déclare qu'il est un fumeur à court terme\nHémoglobine A1C supérieure au niveau recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=4&e=Le patient déclare que sa taille, son poids, son taux de cholestérol, ses vertiges" -X POST http://localhost:8082/patHistory/add
