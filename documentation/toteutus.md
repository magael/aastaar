## Toteutusdokumentti

Projekti toteuttaa polunetsintää kahden pisteen välillä pelikartoilla.

Ohjelma lukee resurssikansiosta [Moving AI Labs:in 2D Pathfinding Benchmark -karttoja](https://movingai.com/benchmarks/grids.html) ja muuntaa niistä polunetsintään soveltuvia kaksiulotteisia taulukoita.

Kartan, sekä lähtö- ja maalipisteet on tällä hetkellä kovakoodattuina ja vaihdettavissa Scenario-luokan init-medoteissa.

Karttoihin sovelletaan Dijkstran algoritmin Uniform cost search -variaatiota sekä A*-algoritmia. Ohjelma kertoo tulostuksilla konsoliin, kun aloritmi on suorittanut toimintonsa.

Lopuksi avataan GUI-ikkuna, jossa kartan maasto on visualisoitu kuvaavin värein. Eri algoritmien löytämät lyhyimmät polut, sekä alku- ja maalipisteet ovat meritty kirkkain värein.

Tarkoitus on vielä toteuttaa algoritmeista eri variaatioita, kuten sekä ilman "visited"-merkintää, että sen kanssa, viistosuuntainen liikkuminen ja eri heurestiikkoja.

Toivottavasti aikaa jää myös tekemään käyttöliittymästä joustavamman, ettei  pisteiden, karttojen ja variaatioiden muokkaamiseksi tarvitse muokata lähdekoodia.

Ohjelman tämänhetkinen tila vastaa pitkälti vaatimusmäärittelyssä määriteltyjä [perustoiminnallisuuksia](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md#perustoiminnallisuuksia).

Lisää suunnitelmia löytää [vatimusmäärittelyn "edistyneitä"](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md#edistyneit%C3%A4--jatkokehityksen-toiminnallisuuksia) kohdasta, sekä [TODO-muistiinpanoista](https://github.com/magael/aastaar/blob/master/documentation/todo.md).

[Lähteet](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md#l%C3%A4hteet) löytyvät myös vaatimusmäärittelystä.

#### Checkstyle

Projekti hyödyntää checkstyleä. Tyylivirheet voidaan tarkastaa komennolla <code>gradle check</code> ja ne löytyvät polulta <code>build/reports/checkstyle/test.html</code>.

#### Javadoc

Javadocin saa generoitua komennolla <code>gradle javadoc</code>. API löytyy polulta <code>build/docs/javadoc/index.html</code>.