CREATE database projets2021;
CREATE USER bibliotheque password '123456';
ALTER DATABASE projets2021 OWNER to bibliotheque;

SELECT * FROM pg_catalog.pg_tables WHERE schemaname != 'pg_catalog' AND schemaname != 'information_schema';
CREATE SEQUENCE idCatEcrivain START 4; 
CREATE TABLE catEcrivain(
	id INT PRIMARY key,
	nom VARCHAR(20)
);

CREATE SEQUENCE idCatLivre START 4;
CREATE TABLE catLivre(
	id INT PRIMARY KEY,
	nom VARCHAR(20)
);

CREATE SEQUENCE idEcrivain START 1;
CREATE TABLE ecrivain(
	id int PRIMARY KEY,
	idCategorie INT references catEcrivain(id),
	nom VARCHAR(20),
	prenom VARCHAR(30),
	dateNaissance DATE
);


CREATE TABLE client(
id int PRIMARY KEY,
nom varchar(20),
prenom varchar(30),
dateNaissance date,
adresse varchar(20),
mdp varchar(40),
mail varchar(40)
);
CREATE SEQUENCE idClient START 1;

INSERT INTO CLIENT VALUES (nextval('idClient'), 'Rakotoson', 'Fabien', DATE '2001-01-02', '123456', 'rakotosonfabien@gmail.com');

/*
CREATE SEQUENCE idDepotClient START  1;
CREATE TABLE DepotClient(
id int PRIMARY KEY,
idClient int references Client(id),
valeur float,
validite boolean
);


CREATE SEQUENCE idAdmin START 1;
CREATE TABLE Admin(
id int PRIMARY KEY,
nom varchar(20),
mdp varchar(40)
);

*/
CREATE SEQUENCE idLivre START 1;
CREATE TABLE Livre(
id int PRIMARY KEY,
idEcrivain INT constraint fk_livreEcrivain references Ecrivain(id),
idCategorie INT constraint fk_livreCategorie references catLivre(id),
titre VARCHAR(40),
description varchar(255),
dateSortie date,
prix float,
nombre int
);
CREATE SEQUENCE idFourniseur START 1;
CREATE TABLE Fournisseur(
id int PRIMARY KEY,
nom VARCHAR(20)
);

CREATE SEQUENCE idLivreImp START 1;
CREATE TABLE LivreImp(
id int PRIMARY KEY,
idEcrivain INT constraint fk_livreEcrivain references Ecrivain(id),
idCategorie INT constraint fk_livreCategorie references catLivre(id),
idFourniseur int constraint fk_fournisseur references Fournisseur(id),
titre VARCHAR(20), 
description varchar(255),
dateSortie date,
prix float,
nombre int
);
/*

CREATE SEQUENCE idEmprunt START 1;
CREATE TABLE Emprunt(
id VARCHAR(10) PRIMARY KEY,
idClient varchar (10)CONSTRAINT fk_clientEmprunt REFERENCES client(id),
idLivre varchar(10) CONSTRAINT fk_livreEmprunt REFERENCES livre(id),
dateEmprunt date
);


CREATE  SEQUENCE idRemiseLivre START 1;
CREATE TABLE RemiseLivre(
id VARCHAR(10),
idEmprunt VARCHAR(10) REFERENCES emprunt(id),
dateRemise date
);
*/
/*-------------------------------Functions-------------------*/
CREATE OR REPLACE FUNCTION deleteEcrivain(idEcrivain int)--Si 0 reussi, Si <0 non reussi, Si >0 livres foreign key non respecte
returns varchar as
$$ declare
resultat boolean := true;
nombreLivres int := null;
nombreEcrivain int := 0;
begin
	SELECT count(*) FROM ecrivain WHERE id = $1 INTO nombreEcrivain;
	if nombreEcrivain > 0 then
		SELECT count(livre.*)  FROM livre where livre.idEcrivain = $1 INTO nombreLivres;
		if nombreLivres=0 then
			DELETE FROM ecrivain WHERE id=$1;
		end if;
	else
		nombreLivres := -100;
	end if;
	return nombreLivres;
end
$$ language plpgsql;

/*--------------------Views---------------------------------*/
/*--Liste des livres non remis par les clients */
/*CREATE OR REPLACE VIEW livreNonRemis1 AS SELECT emprunt.* FROM emprunt WHERE emprunt.id NOT IN (SELECT remiseLivre.idEmprunt FROM remiseLivre);
CREATE OR REPLACE VIEW livreNonRemis2 AS SELECT idLivre, count(*) as nombreNonRemis FROM livreNonRemis GROUP BY idLivre;*/
/*CREATE OR REPLACE VIEW stockLivre AS SELECT Livre.id, Livre.titre, Livre.auteur, livre.description, livre.dateSortie, livre.prix,livre.nombre-setNullToZero(livreNonRemis2.nombreNonRemis) as nombre, image, empruntable FROM Livre LEFT JOIN livreNonRemis2 ON livre.id = livreNonRemis2.idLivre;*/

CREATE OR REPLACE VIEW ecrivainComplet AS SELECT ecrivain.*, catEcrivain.nom as nomCategorie FROM ecrivain LEFT JOIN catEcrivain ON ecrivain.idCategorie = catEcrivain.id;


CREATE OR REPLACE VIEW livreComplet AS SELECT livre.*, catLivre.nom as nomCategorie, ecrivainComplet.nom as nomEcrivain, ecrivainComplet.prenom as prenomEcrivain, ecrivainComplet.dateNaissance, ecrivainComplet.idCategorie as idCatEcrivain, ecrivainComplet.nomCategorie as nomCatEcrivain FROM livre LEFT JOIN catLivre ON catLivre.id = livre.idCategorie JOIN ecrivainComplet ON livre.idEcrivain = ecrivainComplet.id;

/*------------------------------Donnees de test-----------------------------*/
INSERT INTO catEcrivain VALUES (1, 'debutant');
INSERT INTO catEcrivain VALUES (2, 'amateur');
INSERT INTO catEcrivain VALUES (3, 'professionnel');
INSERT INTO catLivre VALUES (1, 'romance');
INSERT INTO catLivre VALUES (2, 'science-fiction');
INSERT INTO catLivre VALUES (3, 'action');

INSERT INTO Fournisseur VALUES (nextval('idFourniseur'), 'Global Groupe');
INSERT INTO Fournisseur VALUES (nextval('idFourniseur'), 'MacForwarding');
INSERT INTO Fournisseur VALUES (nextval('idFourniseur'), 'SisiEnterprise');


INSERT INTO ecrivain VALUES (nextval('idEcrivain'), '3', 'Rakotoson', 'Fabien', DATE '2001-1-2');
	INSERT INTO livre values (nextval('idLivre'), currval('idEcrivain'), '3', 'Action-pour-jeunes', 'Pas pour les enfants alors ne regarde pas', DATE '2020-3-15', 100000, 2);
	INSERT INTO livre values (nextval('idLivre'), currval('idEcrivain'), '3', 'Action-pour-jeunes s2', 'What is this? Not for normal persons!', DATE '2020-8-19', 110000, 4);
	INSERT INTO livre values (nextval('idLivre'), currval('idEcrivain'), '2', 'Question pour les noobs', 'Elu le livre le plus drole en 2018', DATE '2018-2-15', 200000, 10);
	INSERT INTO livre values (nextval('idLivre'), currval('idEcrivain'), '1', 'Question pour les noobs 2', 'Elu le livre le plus drole en 2019', DATE '2019-4-18', 200000, 10);
	INSERT INTO livre values (nextval('idLivre'), currval('idEcrivain'), '2', 'La mort la plus atroce', 'Histoire d un gamin qui meurt', DATE '2019-4-18', 200000, 10);
	
	

INSERT INTO ecrivain VALUES (nextval('idEcrivain'), '3', 'Ranaivoson', 'Antonio', DATE '2000-10-21');
	INSERT INTO livre values (nextval('idLivre'), currval('idEcrivain'), '2', 'Dictionnaire java','Pas de description1', DATE '2019-4-12', 20000, 12);
	INSERT INTO livre values (nextval('idLivre'), currval('idEcrivain'), '1', 'Liste des gg', 'Pas de description2', DATE '2019-1-5', 25000, 10);
	INSERT INTO livre values (nextval('idLivre'), currval('idEcrivain'), '1', 'Your lie in January', 'Histoire d une fille qui meurt a la fin', DATE '2020-1-5', 200000, 10);
	INSERT INTO livre values (nextval('idLivre'), currval('idEcrivain'), '1', 'I want to eat your soul', 'La mort, comme d habitude', DATE '2016-3-8', 140000, 5);
	INSERT INTO livre values (nextval('idLivre'), currval('idEcrivain'), '3', 'Action ou verite', 'Histoire qui va vous faire douter de tout', DATE '2016-3-8', 120000, 5);
	


	






