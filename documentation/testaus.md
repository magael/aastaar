
## Testausdokumentti

Ohjelmaa on tällä hetkellä testattu JUnit-yksikkötesteillä. Testit löytyvät kansiosta aastaar/src/test.

Testit kattavat tällä hetkellä suurimman osan koodista: esimerkiksi tiedostonlukijan, prioriteettijonon, kartan/ruudukon luokat ja algoritmien lyhyimmän polun löytämiseen käytettävän luokan. Osa käyttöliittymään ja algoritmeihin liittyvästä koodista on vielä yksikkötestaamatta.

Testit voidaan toistaa suorittamalla gradle-projektin juuressa, eli repositorion alihakemistossa <code>aastaar</code> komennon <code>gradle test</code>.

Testikattavuus löytyy komennolla <code>gradle test jacocoTestReport</code> ja navigoimalla <code>build/reports/jacoco/test/html/index.html</code>.
