# Vaatimusmäärittely

## Harjoitustyön tarkoitus

Harjoitustyön aihe on polunetsintä verkossa kahden pisteen välillä ja ohjelmointikielenä on Java. Tarkoituksena on löytää nopein reitti kaksiuloitteisella, ylhäältä päin kuvatulla pelikartalla, joka on mallinnettu koodissa ruudukoksi. Ruudukko sisältää läpäisemättömiä seiniä ja tekoälyagentin kuljettavaa tilaa. Työ mallintaa yleistä ongelmaa peleissä: miten yksikkö suunnistaa pelin ympäristössä tiettyyn kohteeseen, esimerkiksi kun vihollinen jahtaa pelaajaa.


## Käytettävät algoritmit ja tietorakenteet

Työssä sovelletaan ensisijaisesti __A*__-algoritmia. A*-algoritmista voidaan myös ajan salliessa toteuttaa erilaisia versioita, ja vertailla niiden tilankäyttöä ja suoritusnopeutta.

A* on oletettavsti hyvä lähtökohta harjoitustyön aiheen kaltaiseen tarkoitukseen, mutta tarkoitus on myös verrata sen suoritusta leveyssuuntaiseen läpikäyntiin (__BFS__) ja mahdollisesti muihin, kuten esimerkiksi Dijkstran algoritmiin. A*:n pohjalta on hyvä lähteä myöhemmin kokeilemaan vielä erilaisia optimointeja reitinhakuun, riippuen tarkemmasta käyttötarkoituksesta.

BFS:n aikavaativuus on O(|V| + |E|) ja tilavaativuus O(|V|), missä |V| on verkon solmujen ja |E| kaarten lukumäärä. A*:n aikavaativuus riippuu käytetystä heurestiikasta. Ilmeisesti aikavaativuus on pahimmassa tapauksessa eksponentiaalinen ja parhaimmassa tapauksessa polynominen ratkaisun, eli lyhyimmän polun pituuteen nähden.

Tarkoitukseni on toteuttaa algoritmit käyttäen __prioriteettijonoa__ verkon solmuille, ja __hajautustaulua__ läpikäytyihin solmuihin.


## Toiminnallisuus

Ohjelma saa syötteenä yhden tai useamman kartan, jonka pohjalta luodaan algoritmin navigointiin käyttämä ruudukko. Työssä käytetään Moving AI Labin 2D Pathfinding Benchmark -formaatin karttoja. Polunetsintä kahden pisteen välillä visualisoidaan Java FX graafisen käyttöliittymän avulla.

Lisäksi, jos toteutukseen jää aikaa, aloitus- ja tavoitepistettä voi muuttaa kesken suorituksen, jolloin algoritmi löytää lyhyellä viiveellä polun uuteen kohteeseen, jonne agentti liikkuu (esim. suoritetaan reitinhakualgoritmi kahdesti sekunnissa).

Jatkokehitysideana polulle voitaisiin myös lisätä suoritusaikana uusia esteitä, jotka agentti onnistuisi kiertämään. Muita ideoita on polun tasapainotus, verkon painot ja oma tasogeneraattori.

#### Perustoiminnallisuuksia:

* Käyttöliittymäikkunan muodostaminen ja sulkeminen
* Kartan piirtäminen
* Algoritmin navigoitavan ruudukon luonti
* BFS
* Polunetsinnän visualisointi (alku- ja loppupisteet, polku, ehkä algoritmin tarkastelemat ruudut)
* A* (mahdollisesti eri versioita)
* Itse toteutetut tietorakenteet
* Eri algoritmien tai variaatioiden vertailu

#### Edistyneitä / jatkokehityksen toiminnallisuuksia:

* Kartan valinta käyttäjän syötteestä
* Kartat konfiguraatiotiedossa määritellyistä poluista
* Liikkumissuuntien määrittely (esim. 4 sallittua suuntaa ja/tai diagonaalit)
* Polunetsintä dynaamisesti muuttuvaan kohteeseen, käyttäjän syötteestä
* Polun "pehmennys" / tasapainotus (smoothing)
* Muita reitinhakualgoritmeja
* Reitillä liikkuva tekoälyagentti
* Esteiden luonti dynaamisesti, käyttäjän syötteestä
* Verkon painot: esim. hiekkapolku, nurmikko, lumi (movingai-benchmarkeissa ilmeisesti "matala vesi")
* Oma kartta-/tasogeneraattori (esim. huoneverkosto, labyrintti tai korkeuskartan perusteella)
* Kartan ruutujen skaalaus kartan koon tai käyttäjän syötteen mukaan
* Aloitus- ja loppupisteiden lukeminen ".scen"-suorituskykytestitiedostoista


##  Lähteet

https://en.wikipedia.org/wiki/A*_search_algorithm

https://movingai.com

https://www.redblobgames.com

