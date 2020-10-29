--Fonctions
-----------------------------------------------------------------------
CREATE OR REPLACE FUNCTION livreAncien(dateMaximum date)
RETURNS varchar AS 
$$ DECLARE ok varchar;
BEGIN
        -- Effectuer le travail sécurisé de la fonction.
        --execute FORMAT('SELECT %I FROM %I WHERE   %I < %I', Livre.id, Livre, dateSortie, date) INTO ok;
        SELECT Livre.id INTO ok FROM Livre limit 1;
        RETURN ok;
END;
$$  LANGUAGE plpgsql
    SECURITY DEFINER
    -- Configure un search_path sécurisée : les schémas de confiance, puis 'pg_temp'.
    SET search_path = admin, pg_temp;

----------------------------------------------------------------------

CREATE OR REPLACE FUNCTION factorielle(n integer)
returns integer as
$$
declare i integer; fact integer;
begin
fact=1;
for i in 2..n loop
	fact = fact * i;
end loop;
return fact;
end;
$$ language plpgsql;

-------------------------------------------------------------------

create or replace function check_mp(u varchar(30), m varchar(30))
returns boolean as
$$
declare nombres int; ok boolean;
begin
select count(*) into nombres from utilisateurs WHERE username= u ;
if nombres>0 then ok := true;
else ok := false;
end if;
return ok;
end;
$$
language plpgsql;

---------------------------------------------------------------------
CREATE TABLE truc (id_truc INT, sousid_truc INT, nom_truc TEXT);
INSERT INTO truc VALUES (1, 2, 'trois');
INSERT INTO truc VALUES (4, 5, 'six');

CREATE OR REPLACE FUNCTION obtenir_tous_les_trucs() RETURNS SETOF truc AS
$BODY$
DECLARE
    r truc%rowtype;
BEGIN
    FOR r IN
        SELECT * FROM truc WHERE id_truc > 0
    LOOP
        -- quelques traitements
        RETURN NEXT r; -- renvoie la ligne courante du SELECT
    END LOOP;
    RETURN;
END
$BODY$
LANGUAGE plpgsql;

SELECT * FROM obtenir_tous_les_trucs();

-----------------------------------------------------------------
CREATE OR REPLACE FUNCTION setNullToZero(q bigint)
returns bigint as
--DECLARE
$$
DECLARE resultat bigint:=q;
begin
if q is NULL then resultat:=0; end if;
return resultat;
end;
$$ language plpgsql;

---------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION selectNombre(idLivre varchar)
returns bigint as
$$ DECLARE
	nombre bigint := -100;
begin
	SELECT stockLivre.nombre INTO nombre FROM stockLivre WHERE stockLivre.id = $1;
	if nombre<=0 then nombre := -1; else nombre:=1;
	end if;
	return nombre;
end
$$ language plpgsql;

CREATE OR REPLACE FUNCTION remiseLivre(idClient varchar)
returns boolean as
$$ DECLARE 
	resultat boolean := true;
	nombre bigint := -100;
begin
	SELECT count(livreNonRemis1.*) INTO nombre FROM livreNonRemis1 WHERE livrenonremis1.idClient = $1;
	if nombre > 0 then resultat:=false;
	end if;
	return resultat;
end
$$ language plpgsql;

create or replace function insertEmprunt(idClient varchar, idLivre varchar) 
returns varchar as
$$
DECLARE
	nombre bigint := -100;
	insertion varchar := 'Tsy misy ambara';
	empruntable varchar := 'false';
begin
	if selectNombre($2) = -1 then 
		insertion := 'exemplaire';
		return insertion;
	end if;
	if remiseLivre($1=false) then
		insertion := 'livreNonRemis';
		return insertion;
	end if;
	SELECT livre.empruntable INTO empruntable FROM livre WHERE livre.id = $2;
	if empruntable = 'false' then 
		insertion := 'nonEmpruntable'; 
		return insertion;
	else
		
		INSERT INTO Emprunt VALUES (nextval('idEmprunt'), $1, $2, NOW());
		insertion := true;
	end if;
	return insertion;
end
$$ language plpgsql;



------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION remiseLivre2(idEmprunt varchar)
returns boolean as
$$ DECLARE 
	resultat boolean := true;
	nombre bigint := -100;
begin
	SELECT count(livreNonRemis1.*) INTO nombre FROM livreNonRemis1 WHERE livrenonremis1.id = $1;
	if nombre > 0 then resultat:=false;
	end if;
	return resultat;
end
$$ language plpgsql;

----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION remiseLivre2(idClient varchar, idLivre varchar)
returns boolean as
$$ DECLARE 
	resultat boolean := true;
	nombre bigint := -100;
begin
	SELECT count(livreNonRemis1.*) INTO nombre FROM livreNonRemis1 WHERE livrenonremis1.idClient = $1 and livrenonremis1.idLivre = $2;
	if nombre > 0 then resultat:=false;
	end if;
	return resultat;
end
$$ language plpgsql;

-----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION insertRemise(idEmprunt varchar)
returns boolean as
$$ declare 
resultat boolean := true;
returned boolean := true;
begin
	resultat := remiseLivre2($1);
	if resultat = false then 
		INSERT INTO remiseLivre VALUES (nextval('idRemiseLivre'), $1, now());
	else
		returned := false;
	end if;
	return returned;
end
$$ language plpgsql

-----------------------------------------------------------------------------
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
$$ language plpgsql



