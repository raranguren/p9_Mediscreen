# Sample data from the clinic

curl -d "family=Ferguson&given=Lucas&dob=1968-06-22&sex=M&address=2 Warren Street &phone=387-866-1399" -X POST http://localhost:8081/patient/add
curl -d "family=Rees&given=Pippa&dob=1952-09-27&sex=F&address=745 West Valley Farms Drive&phone=628-423-0993" -X POST http://localhost:8081/patient/add
curl -d "family=Arnold &given=Edward &dob=1952-11-11&sex=M&address=599 East Garden Ave &phone=123-727-2779" -X POST http://localhost:8081/patient/add
curl -d "family=Sharp &given=Anthony&dob=1946-11-26&sex=M&address=894 Hall Street&phone=451-761-8383" -X POST http://localhost:8081/patient/add
curl -d "family=Ince &given=Wendy &dob=1958-06-29&sex=F&address=4 Southampton Road &phone=802-911-9975" -X POST http://localhost:8081/patient/add
curl -d "family=Ross&given=Tracey&dob=1949-12-07&sex=F&address=40 Sulphur Springs Dr&phone=131-396-5049" -X POST http://localhost:8081/patient/add
curl -d "family=Wilson&given=Claire&dob=1966-12-31&sex=F&address=12 Cobblestone St &phone=300-452-1091" -X POST http://localhost:8081/patient/add
curl -d "family=Buckland &given=Max &dob=1945-06-24&sex=M&address=193 Vale St &phone=833-534-0864" -X POST http://localhost:8081/patient/add
curl -d "family=Clark &given=Natalie &dob=1964-06-18&sex=F&address=12 Beechwood Road&phone=241-467-9197" -X POST http://localhost:8081/patient/add
curl -d "family=Bailey &given=Piers &dob=1959-06-28&sex=M&address=1202 Bumble Dr &phone=747-815-0557" -X POST http://localhost:8081/patient/add

curl -d "patId=1&e=Le patient déclare qu'il « se sent très bien »\nPoids égal ou inférieur au poids recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=1&e=Le patient déclare qu'il se sent fatigué pendant la journée\nIl se plaint également de douleurs musculaires\nTests de laboratoire indiquant une microalbumine élevée" -X POST http://localhost:8082/patHistory/add
curl -d "patId=1&e=Le patient déclare qu'il ne se sent pas si fatigué que ça\nFumeur, il a arrêté dans les 12 mois précédents\nTests de laboratoire indiquant que les anticorps sont élevés" -X POST http://localhost:8082/patHistory/add
curl -d "patId=2&e=Le patient déclare qu'il ressent beaucoup de stress au travail\nIl se plaint également que son audition est anormale dernièrement" -X POST http://localhost:8082/patHistory/add
curl -d "patId=2&e=Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois\nIl remarque également que son audition continue d'être anormale" -X POST http://localhost:8082/patHistory/add
curl -d "patId=2&e=Tests de laboratoire indiquant une microalbumine élevée" -X POST http://localhost:8082/patHistory/add
curl -d "patId=2&e=Le patient déclare que tout semble aller bien\nLe laboratoire rapporte que l'hémoglobine A1C dépasse le niveau recommandé\nLe patient déclare qu’il fume depuis longtemps" -X POST http://localhost:8082/patHistory/add
curl -d "patId=3&e=Le patient déclare qu'il fume depuis peu" -X POST http://localhost:8082/patHistory/add
curl -d "patId=3&e=Tests de laboratoire indiquant une microalbumine élevée" -X POST http://localhost:8082/patHistory/add
curl -d "patId=3&e=Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière\nIl se plaint également de crises d’apnée respiratoire anormales\nTests de laboratoire indiquant un taux de cholestérol LDL élevé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=3&e=Tests de laboratoire indiquant un taux de cholestérol LDL élevé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=4&e=Le patient déclare qu'il lui est devenu difficile de monter les escaliers\nIl se plaint également d’être essoufflé\nTests de laboratoire indiquant que les anticorps sont élevés\nRéaction aux médicaments" -X POST http://localhost:8082/patHistory/add
curl -d "patId=4&e=Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps" -X POST http://localhost:8082/patHistory/add
curl -d "patId=4&e=Le patient déclare avoir commencé à fumer depuis peu\nHémoglobine A1C supérieure au niveau recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=5&e=Le patient déclare avoir des douleurs au cou occasionnellement\nLe patient remarque également que certains aliments ont un goût différent\nRéaction apparente aux médicaments\nPoids corporel supérieur au poids recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=5&e=Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite.\nTaille incluse dans la fourchette concernée" -X POST http://localhost:8082/patHistory/add
curl -d "patId=5&e=Le patient déclare qu'il souffre encore de douleurs cervicales occasionnelles\nTests de laboratoire indiquant une microalbumine élevée\nFumeur, il a arrêté dans les 12 mois précédents" -X POST http://localhost:8082/patHistory/add
curl -d "patId=5&e=Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite.\nTests de laboratoire indiquant que les anticorps sont élevés" -X POST http://localhost:8082/patHistory/add
curl -d "patId=6&e=Le patient déclare qu'il se sent bien\nPoids corporel supérieur au poids recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=6&e=Le patient déclare qu'il se sent bien" -X POST http://localhost:8082/patHistory/add
curl -d "patId=7&e=Le patient déclare qu'il se réveille souvent avec une raideur articulaire\nIl se plaint également de difficultés pour s’endormir\nPoids corporel supérieur au poids recommandé\nTests de laboratoire indiquant un taux de cholestérol LDL élevé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=8&e=Les tests de laboratoire indiquent que les anticorps sont élevés\nHémoglobine A1C supérieure au niveau recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=9&e=Le patient déclare avoir de la difficulté à se concentrer sur ses devoirs scolaires\nHémoglobine A1C supérieure au niveau recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=9&e=Le patient déclare qu'il s’impatiente facilement en cas d’attente prolongée\nIl signale également que les produits du distributeur automatique ne sont pas bons\nTests de laboratoire signalant des taux anormaux de cellules sanguines" -X POST http://localhost:8082/patHistory/add
curl -d "patId=9&e=Le patient signale qu'il est facilement irrité par des broutilles\nIl déclare également que l'aspirateur des voisins fait trop de bruit\nTests de laboratoire indiquant que les anticorps sont élevés" -X POST http://localhost:8082/patHistory/add
curl -d "patId=10&e=Le patient déclare qu'il n'a aucun problème" -X POST http://localhost:8082/patHistory/add
curl -d "patId=10&e=Le patient déclare qu'il n'a aucun problème\nTaille incluse dans la fourchette concernée\nHémoglobine A1C supérieure au niveau recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=10&e=Le patient déclare qu'il n'a aucun problème\nPoids corporel supérieur au poids recommandé\nLe patient a signalé plusieurs épisodes de vertige depuis sa dernière visite" -X POST http://localhost:8082/patHistory/add
curl -d "patId=10&e=Le patient déclare qu'il n'a aucun problème\nTests de laboratoire indiquant une microalbumine élevée" -X POST http://localhost:8082/patHistory/add