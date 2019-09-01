## Käyttöohje

Ohjelma voidaan suorittaa kloonaamalla repositorio ja käynnistämällä pääohjelma (<code>aastaar/src/main/java/mj/aastaar/Main.java</code>) esimerkiksi IDE:n avulla (Esim. NetBeans).

#### Buildaus

Projektin saa buildattua suorittamalla gradle-projektin juuressa, eli repositorion alihakemistossa <code>aastaar</code> komennon <code>gradle build</code>, mikä generoi samalla testikattavuuden tiedostoon <code>build/reports/jacoco/test/html/index.html</code>.

#### Jar:in generointi

Suoritettavan <code>.jar</code>-tiedoston saa generoitua komennolla <code>gradle jar</code>, jonka jälkeen se löytyy hakemistosta <code>aastaar/build/libs/</code>.

#### Ohjelman suoritus

Projekti toteuttaa polunetsintää kahden pisteen välillä pelikartoilla. Ohjelmointikieli on Java ja graafinen käyttöliittymä on tehty Java FX:llä.

Pääohjelman käynnistyttyä luetaan  resuirssikansiosta pelikarttoja ja luodaan niitä vastaavat ruudukot. Alustavat lähtö- ja maalipisteet generoidaan joka suorituskerralla satunnaisesti.

Karttoihin sovelletaan Dijkstran algoritmin Uniform cost search -variaatiota (jonoon alustetaan vain lähtösolmu) sekä A*-algoritmia. Algoritmien löydettyä lyhyimmät polut ensimmäisen kartan alustavien pisteiden välillä, avataan graafisen käyttöliittymän ikkuna. Kartojen maasto on visualisoitu kuvaavin värein: "Tavallinen maa" on hiekan väristä, puut vihreitä, syvä vesi sinistä ja matala vesi sekoitus hiekan ja veden väriä.

Eri algoritmien löytämät lyhyimmät polut, sekä alku- ja maalipisteet ovat meritty kirkkain värein. Käyttöliittymän oikeassa reunassa on työkalupalkki, jossa kerrotaan käytetyt algoritmit ja värit.

Alku- ja lähtöpisteiden koordinaatit ovat tarkasti nähtävissä ja muunneltavissa palkin yläosan tekstikentissä. Polut päivittyvät painikkeesta "New positions".

Palkissaa on myös nappi, jonka avulla käyttäjä voi alustaa uudet satunnaiset lähtö- ja maalipisteet. Uusien pisteiden valinta pyyhkii välittömästi vanhat pisteet ja reitit pois, suorittaa algoritmit uudelleen ja piirtää uudet pisteet ja polut.

Palkkiin listataan algoritmien löytämät lyhyimmät polut, ja niiden painot. Matalan veden läpi kulkeminen on raskaampaa.

Otsikon "Visualize explored nodes" alla olevasta valikosta voidaan piirtää kartalle esitys kunkin algoritmin tarkastelemista ruuduista.

Käyttäjä voi valita useasta eri kartasta, joko "Previous" ja "Next", tai numeroiduista napeista.

Varoitustekstiä ("WARNING") seuraa painike, josta voidaan suorittaa suorituskykytestejä. Testit jäädyttävät sovelluksen toiminnallisuuden testien ajaksi. Testien suoriuduttua, tulokset kirjataan työkalupalkkiin testausnapin alle.

Alimmaisesta valikosta käyttäjä voi vaihtaa ruudukon skaalausta sopivammaksi oman näyttönsä resoluutioon.

Käyttöliittymäikkunan sulkeminen, esimerkiksi yläkulman ruksipainikkeesta, lopettaa sovelluksen suorituksen.

#### Testit

Yksikkötestit löytyvät hakemistosta <code>aastaar/src/test/java/aastaar/</code> ja niiden käyttämä testikartta <code>aastaar/src/test/resources/testmaps/</code>. Lisää testauksesta voi lukea [testausdokumentista](https://github.com/magael/aastaar/blob/master/documentation/testaus.md).

#### Javadoc

Javadocin saa generoitua komennolla <code>gradle javadoc</code>. API löytyy polulta <code>build/docs/javadoc/index.html</code>.

#### Muokkaus

Pelikarttojen tiedostot löytyvät hakemistosta <code>aastaar/src/main/resources</code>. Uusia saman formaatin toteuttavia karttoja voidaan lisätä kansioon, ja ohjelman lukevat tiedostopolut määritellä haluamikseen <code>Main</code>-luokan <code>initMaps</code>-metodissa.

Tällä hetkellä suorituskykytesteille annetaan suoritettavaksi kovakoodatun joukon kierroksia {10, 10, 20}. Kierrosjoukkoa voidaan muokata <code>Main</code>-luokan metodista <code>runPerformanceTests</code>. Tulokset voidaan myös esimerkiksi tulostaa konsoliin komennolla <code>System.out.println(runPerformanceTests(scenario.getAlgorithmVisuals()));</code>.