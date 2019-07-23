# Vaatimusmäärittely

## Harjoitustyön tarkoitus

Harjoitustyön aihe on polunetsintä verkossa kahden pisteen välillä. Tarkoituksena on löytää nopein reitti kaksiuloitteisella pelikartalla, joka on mallinnettu koodissa ruudukoksi. Ruudukko sisältää läpäisemättömiä seiniä ja tekoälyagentin kuljettavaa tilaa. Työ mallintaa yleistä ongelmaa peleissä: miten agentti suunnistaa pelin ympäristössä tiettyyn kohteeseen, esimerkiksi kun vihollinen jahtaa pelaajaa.

Aikavaatimustavoite: $O(n\sqrt{2})$


## Käytettävät algoritmit ja tietorakenteet

Työssä toteutetaan ensisijaisesti A*-algoritmia. A*-algoritmista voidaan myös ajan salliessa toteuttaa erilaisia versioita, ja vertailla niiden tilankäyttöä ja suoritusnopeutta.

A* on yleisesti hyväksi toteutettu algoritmi harjoitustyön aiheen kaltaiseen tarkoitukseen, mutta tarkoitus on myös verrata sen suoritusta leveyssuuntaiseen läpikäyntiin (BFS) ja mahdollisesti muihin, kuten Dijkstran algoritmiin.

Löytämissäni esimerkeissä  A*-algoritmi käyttää prioriteettijonoa verkon solmuille, ja hajautustaulua läpikäytyihin solmuihin. Vaikuttaa kuitenkin mahdolliselta toteuttaa koko algoritmi (vähemmän tehokkaasti) käyttäen pelkästään tavallisia dynaamisia taulukoita (tai esimerkiksi käyttämällä prioriteettijonoa, mutta taulukkoa hajautustaulun sijaan).


## Toiminnallisuus

Ohjelma saa syötteenä kartan, jonka pohjalta luodaan algoritmin navigointiin käyttämä ruudukko. Polunetsintä kahden pisteen välillä visualisoidaan graafisen käyttöliittymän avulla.

Lisäksi, jos toteutukseen jää aikaa, tavoitepistettä voi muuttaa kesken suorituksen, jolloin algoritmi löytää lyhyellä viiveellä polun uuteen tavoitteeseen, ja agentti liikkuu kohteeseen.

Jatkokehitysideana polulle voitaisiin myös lisätä suoritusaikana uusia esteitä, jotka agentti onnistuisi kiertämään.


#### Perustoiminnallisuuksia:
* Käyttöliittymäikkunan muodostaminen ja sulkeminen
* Kartan piirtäminen ja päivittäminen
* Navigaatioruudukon luonti
* Kartan valinta
* Tavoitepisteiden ja polunetsinnän visualisointi
* BFS
* A* (mahdollisesti eri versioita)
* Eri algoritmien vertailu

#### Edistyneitä / jatkokehityksen toiminnallisuuksia:
* Polun "pehmennys" / tasapainotus (smoothing)
* Muita reitinhakualgoritmeja
* Navigaatioruudukon luonti eri kuvien (samassa formaatissa) perusteella
* Reitillä liikkuva tekoälyagentti
* Polunetsintä dynaamisesti muuttuvaan kohteeseen (käyttäjän syötteestä)
* Esteiden luonti dynaamisesti (käyttäjän syötteestä) ja törmäyksentarkistus
* Valikkonäkymä ym. graafisen käyttöliittymän hiomista
  
##  Lähteet
https://en.wikipedia.org/wiki/A*_search_algorithm