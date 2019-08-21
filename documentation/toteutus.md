## Toteutusdokumentti

Projekti toteuttaa polunetsintää kahden pisteen välillä pelikartoilla. Ohjelmointikieli on Java ja graafinen käyttöliittymä on tehty Java FX:llä.

Ohjelma lukee resurssikansiosta [Moving AI Labs:in 2D Pathfinding Benchmark -karttoja](https://movingai.com/benchmarks/grids.html) ja muuntaa niistä polunetsintään soveltuvia kaksiulotteisia taulukoita.

Kartta, sekä lähtö- ja maalipisteet on tällä hetkellä kovakoodattuina ja vaihdettavissa Scenario-luokan init-medoteissa.

Karttoihin sovelletaan Dijkstran algoritmin Uniform cost search -variaatiota sekä A*-algoritmia. Ohjelma kertoo tulostuksilla konsoliin, kun algoritmi on suorittanut toimintonsa.

Lopuksi avataan GUI-ikkuna, jossa kartan maasto on visualisoitu kuvaavin värein. Eri algoritmien löytämät lyhyimmät polut, sekä alku- ja maalipisteet ovat meritty kirkkain värein.

Tarkoitus on vielä toteuttaa algoritmeista eri variaatioita ja optimointeja: Esimerkiksi sekä ilman "visited"-merkintää, että sen kanssa, viistosuuntainen liikkuminen ja eri heuristiikkoja.

Toivottavasti aikaa jää myös tekemään käyttöliittymästä joustavamman, ettei  pisteiden, karttojen ja variaatioiden muokkaamiseksi tarvitse muokata lähdekoodia.

Ohjelman tämänhetkinen tila vastaa pitkälti vaatimusmäärittelyssä määriteltyjä [perustoiminnallisuuksia](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md#perustoiminnallisuuksia).

Lisää suunnitelmia löytyy [vatimusmäärittelyn "edistyneitä"](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md#edistyneit%C3%A4--jatkokehityksen-toiminnallisuuksia) kohdasta. Niistäkin on jo pari kohtaa toteutettu, kuten polun painot.

Koodin seasta on siistitty pois erilliseen tiedostoon tiettyihin metodeihin liittyviä [TODO-muistiinpanoja](https://github.com/magael/aastaar/blob/master/documentation/todo.md).

#### Lähteet

https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/

https://en.wikipedia.org/wiki/A*_search_algorithm

https://en.wikipedia.org/wiki/Binary_heap

https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm

https://movingai.com

https://www.redblobgames.com

#### Checkstyle

Projekti hyödyntää checkstyleä. Tyylivirheet voidaan tarkastaa komennolla <code>gradle check</code> ja ne löytyvät polulta <code>build/reports/checkstyle/main.html</code>.

#### Javadoc

Javadocin saa generoitua komennolla <code>gradle javadoc</code>. API löytyy polulta <code>build/docs/javadoc/index.html</code>.