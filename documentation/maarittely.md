# Vaatimusmäärittely

## Harjoitustyön tarkoitus

Harjoitustyön aihe on polunetsintä verkossa kahden pisteen välillä ja toteutuskielenä on Java. Tarkoituksena on löytää nopein reitti kaksiuloitteisella, ylhäältä päin kuvatulla pelikartalla, joka on mallinnettu koodissa ruudukoksi. Ruudukko sisältää läpäisemättömiä seiniä ja tekoälyagentin kuljettavaa tilaa. Työ mallintaa yleistä ongelmaa peleissä: miten agentti suunnistaa pelin ympäristössä tiettyyn kohteeseen, esimerkiksi kun vihollinen jahtaa pelaajaa.

Aikavaatimustavoite: O(n^2)


## Käytettävät algoritmit ja tietorakenteet

Työssä sovelletaan ensisijaisesti __A*__-algoritmia. A*-algoritmista voidaan myös ajan salliessa toteuttaa erilaisia versioita, ja vertailla niiden tilankäyttöä ja suoritusnopeutta.

A* on yleisesti hyväksi toteutettu algoritmi harjoitustyön aiheen kaltaiseen tarkoitukseen, mutta tarkoitus on myös verrata sen suoritusta leveyssuuntaiseen läpikäyntiin (__BFS__) ja mahdollisesti muihin, kuten Dijkstran algoritmiin.

Löytämissäni esimerkeissä  A*-algoritmi käyttää __prioriteettijonoa__ verkon solmuille, ja __hajautustaulua__ läpikäytyihin solmuihin. Vaikuttaa kuitenkin mahdolliselta toteuttaa koko algoritmi (vähemmän tehokkaasti) käyttäen pelkästään tavallisia dynaamisia taulukoita (tai esimerkiksi käyttämällä prioriteettijonoa, mutta taulukkoa hajautustaulun sijaan).


## Toiminnallisuus

Ohjelma saa syötteenä kartan, jonka pohjalta luodaan algoritmin navigointiin käyttämä ruudukko. Polunetsintä kahden pisteen välillä visualisoidaan Java FX graafisen käyttöliittymän avulla.

Lisäksi, jos toteutukseen jää aikaa, tavoitepistettä voi muuttaa kesken suorituksen, jolloin algoritmi löytää lyhyellä viiveellä polun uuteen tavoitteeseen, ja agentti liikkuu kohteeseen.

Jatkokehitysideana polulle voitaisiin myös lisätä suoritusaikana uusia esteitä, jotka agentti onnistuisi kiertämään.


#### Perustoiminnallisuuksia:
* Käyttöliittymäikkunan muodostaminen ja sulkeminen
* Kartan ja tavoitepisteiden piirtäminen ja päivittäminen
* Navigaatioruudukon luonti
* BFS
* Polunetsinnän visualisointi
* A* (mahdollisesti eri versioita)
* Itse toteutetut tietorakenteet
* Eri algoritmien vertailu

#### Edistyneitä / jatkokehityksen toiminnallisuuksia:
* Kartan valinta useista vaihtoehdoista
* Polun "pehmennys" / tasapainotus (smoothing)
* Muita reitinhakualgoritmeja
* Navigaatioruudukon luonti eri kuvien (samassa formaatissa) perusteella
* Reitillä liikkuva tekoälyagentti
* Polunetsintä dynaamisesti muuttuvaan kohteeseen (käyttäjän syötteestä)
* Esteiden luonti dynaamisesti (käyttäjän syötteestä) ja törmäyksentarkistus
  
##  Lähteet
https://en.wikipedia.org/wiki/A*_search_algorithm
