# Polunetsintäohjelma

## Aineopintojen harjoitustyö: Tietorakenteet ja algoritmit (loppukesä 2019)

### Dokumentaatio

[Vaatimusmäärittely](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md)

[TODO-muistiinpanoja](https://github.com/magael/aastaar/blob/master/documentation/todo.md)

#### Viikkoraportit
[Viikko 1](https://github.com/magael/aastaar/blob/master/documentation/viikkoraportit/viikkoraportti1.md)

[Viikko 2](https://github.com/magael/aastaar/blob/master/documentation/viikkoraportit/viikkoraportti2.md)

[Viikko 3](https://github.com/magael/aastaar/blob/master/documentation/viikkoraportit/viikkoraportti3.md)

[Viikko 4](https://github.com/magael/aastaar/blob/master/documentation/viikkoraportit/viikkoraportti4.md)

#### Testaus

Testikattavuus löytyy suorittamalla gradle-projektin juuressa, eli repositorion alihakemistossa <code>aastaar</code> komennon <code>gradle test jacocoTestReport</code> ja navigoimalla <code>build/reports/jacoco/test/html/index.html</code>.

#### Checkstyle

Projekti hyödyntää checkstyleä. Tyylivirheet voidaan tarkastaa komennolla <code>gradle check</code> ja ne löytyvät polulta <code>build/reports/checkstyle/test.html</code>.

#### Javadoc

Javadocin saa generoitua komennolla <code>gradle javadoc</code> ja ne löytyy polulta <code>build/docs/javadoc/index.html</code>.
