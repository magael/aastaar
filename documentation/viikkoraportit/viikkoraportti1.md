## Viikko 1

Työtunteja kertyi yhteensä noin 14.

#### Maanantai 22.7.

Kurssin aloitustilaisuus, jossa käytiin selkeästi läpi kurssin asiat. Tilaisuuden jälkeen oli helpompi lähteä tekemään päätöstä työn aiheesta.

Ihan saman tien ei kuitenkaan vielä syntynyt tarkkaa suunnitelmaa, vaan piti vielä tutustua lisää materiaaleihin ja tutkia eri aiheita netistä. Tutustuin muun muassa erilaisiin proseduraalisen generoinnin (procedural generation) tekniikoihin ja sovelluksiin, kuten maaston (terrain) luontiin erilaisen kohinan (noise) ja korkeuskarttojen (height maps) avulla. Törmäsin myös ideaan hyödyntää muun muassa leveyssuuntaista hakualgoritmia pulmapelien (puzzle games) tasojen (level) generoinnissa. Suunnittelin aika pitkälle työn aiheeksi Sokoban-pelin tasojen generaattoria, jota voisi mahdollisesti laajentaa erilaisiin pulmapeleihin.

A*-algoritmin parempi ymmärtäminen niin, että sen soveltaminen olisi helppoa, on ollut pitkään tavoitteissani, joten en lyönyt lukkoon Sokoban-ideaa. Olen myös kiinnostunut tekemään pelejä 3D-pelimoottoreilla, joten ajattelin, että olisi mielenkiintoista toteuttaa polunetsintää kolmiulotteisessa ympäristössä.

Tutustuin jMonkeyEngine-pelimoottoriin (game engine), joka käyttää Javaa ohjelmointikielenä. Dokumentaation perusteella jME:n hyödyntäminen vaikutti varsin hyvältä vaihtoehdolta tämän kurssin työlle. Minua kuitenkin arvelutti muun muassa, sujuisiko esimerkiksi valmiin kolmiulotteisen vektorin toteutuksen korvaaminen omalla tietorakenteella mutkattomasti.

Tutustuin myös kahteen toiseen 3D-Java-kirjastoon: Lightweight Java Gaming Library:n (LWJGL) ja Java OpenGL:n (JOGL). Molemmat vaikuttivat hyvin aloittelijaystävällisiltä ja mielenkiintoisilta. Arvioin kuitenkin, että näistä jompaa kumpaa kirjastoa hyödyntämällä suuri osa kurssista menisi käyttöliittymän ja ympäristön toteutukseen, jolloin ei välttämättä jäisi tarpeeksi aikaa algoritmeille ja tietorakenteille.

#### Tiistai 23.7.

En ollut tarkistanut, saako työssä käyttää C#:ää, mutta otin jo etukäteen selvää, miten Unity 3D -pelimoottorilla, jota olen hieman aiemmin käyttänyt, tehdyille ohjelmistoille voidaan kirjoittaa testejä. Päältä päin testaaminen vaikutti ihan sujuvalta, mutta aiempien kokemusten mukaan (muilla kielillä toteutetuissa ei-peli-projekteissa) uuden testikirjaston käyttöönottoon saattaa kulua paljon aikaa.

Päädyin lopulta ratkaisuun, että on parempi aloittaa perinteisemmästä 2D-ympäristöstä ja melko tutulla Java FX:llä toteutetusta käyttöliittymästä. Oman 3D-pelimoottorin rakentaminen ei olisi tämän kurssin aikarajoitteissa millään tapaa järkevä tavoite, ja valmiiden moottorien hyödyntämiseen liittyy paljon tuntemattomia riskejä: en ole käyttänyt jME-moottoria ennen lainkaan, enkä kirjoittanut ainuttakaan testiä Unity-peleille.

Tutustuin myös muun muassa Moving AI Lab-sivuston resursseihin sekä yleisesti reitinhakualgoritmeihin ym. Pelkästään A*-algoritmista näyttää olevan aika erilaisia toteutuksia, ja vähän askarruttaa, millaisen saan itse aikaiseksi. Erilaisia optimointejakin tuntui olevan paljon, ja toisaalta minua kiinnostaa myös tapaukset (peleissä), joissa A* ei ole paras ratkaisu.

Aiheen selkeytyttyä laadin projektille suunnitelman ja kirjoitin [vaatimusmäärittelyn](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md). __Olisi kiva saada palautetta, arviolta kuinka laaja työ olisi vaatimusmäärittelyyssä
listaamillani "perustoiminnallisuuksilla", ja esim. mitä "edistyneistä" olisi hyvä ehtiä toteuttamaan, jos tavoittelee vitosta?__ Pyrin laatimaan bulletpointit toiminnallisuuksien toteutusjärjestyksessä, mutta suunnitelmat saattavat toki vielä hieman muuttua.

#### Torstai 25.7.

Kirjoitin tämän viikkoraportin ja otin projektissa Gradlen käyttöön. Repossa oli valmiiksi issuet sallittu. Seuraavaksi rekisteröidyn ja teen palautuksen Labtooliin ja katson mitä toisen viikon palautukseen vaaditaan.

#### Perjantai 26.7.

Tutustuin tarkemmin Moving AI Labin 2D-benchmark-materiaaleihin. Olisi kyllä hienoa käyttää aineistoa omassa työssä ainakin jollain tasolla.

Voisin esim. aloittaa toteuttamalla tiedostonlukijaluokan, joka lukee .map-tiedostojen ASCII-muotoista dataa, ja toisen luokan säilyttämään ja hallinnoimaan sitä. Mielellään tarvitsisin myös GUI-luokan piirtämään ensialkuun eri väriset ruudut datan mukaan. En tiedä pitäisikö sitä ennen jo aloittaa itse algoritmin toteutus.

Vilkaisin myös muun muassa Jump Point Searchia, ja tuli mieleen kysyä, __olisiko ehdotuksia tietyistä algoritmeista tai variaatioista "perus" A*:n lisäksi, joita olisi hyvä toteuttaa?__ Ajattelin BFS:ää lähtökohtana siihen, että pääsee alkuun visualisoinnin kanssa, mutta muuten ei ole mitään kummempia suunnitelmia. 
