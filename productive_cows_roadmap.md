# Productive Cows — Roadmapa

> Mod w stylu Productive Bees, ale z krowami i własnym sercem: **przetwórczość mleka**.
> Krowy są tylko wejściem. Przetwórczość (mleko → materiał + produkty uboczne → buffy) jest duszą moda.

## Zasada nadrzędna
Nigdy nie buduj „całego moda". Buduj jedną warstwę, która kończy się czymś **grywalnym**. Wypuść MVP wcześnie, rośnij wersja po wersji. To jedyna droga, którą solo mody nie giną — porzucone projekty to prawie zawsze ludzie, którzy chcieli zrobić wszystko przed wypuszczeniem czegokolwiek.

Kolejność warstw:
1. **MVP** — grywalny rdzeń, do wypuszczenia.
2. **Głębia** — genetyka, obora, warianty na skalę.
3. **ATM / integracja** — fluidy, rury, data-driven.

---

## ✅ Zrobione (fundament już stoi)
- [x] Środowisko deweloperskie (NeoForge 1.21.1, JDK 21, IntelliJ) — na dwóch maszynach
- [x] Rejestracja itemu — pełny rurociąg: rejestracja → tekstura → model → nazwa → kreatyw
- [x] Warstwowe tekstury (baza + fill) jako wzorzec na wszystkie buckety
- [x] Własna zakładka kreatywu „Productive Cows"
- [x] Klasa `ModItems` — czysta organizacja rejestracji
- [x] Wszystkie 13 buketów w zakładce
- [x] Sprzątanie: usunięty przykładowy kod MDK (example block/item, config) — czysty projekt

---

## 🧭 Decyzja architektoniczna (USTALONA): Wariant B
**Jeden `EntityType` (`ProductiveCow`) + pole `variant`.** Krowa nosi w sobie wartość mówiącą, kim jest, i odczytuje z tabeli wariantów swoje trzy rzeczy: **teksturę, produkt (mleko), biom spawnu**.

Dlaczego B, a nie osobna klasa na krowę:
- Mniej kodu — jedna klasa zamiast trzynastu.
- Realizuje cel od ręki: różne tekstury / mleko / spawn = trzy kolumny w tabeli wariantów.
- Skaluje się i jest jedynym wejściem do data-driven / ATM (Warstwa 3) — bo datapack potrafi dodać wpis w tabeli, ale nie potrafi stworzyć klasy Javy.
- Na przyszłość: krowy o innym *zachowaniu* (blaze cow podpala, slime cow się odbija) podepniesz jako hak w danych wariantu — dalej bez klasy na wariant.

---

## Warstwa 1 — MVP (grywalny rdzeń → pierwszy pokazywalny punkt)

### Faza 1: Pętla dojenia  `[łatwe-średnie]`
- [ ] Event interakcji z encją: dojenie krowy pustym wiadrem → odpowiedni bukket
- [ ] Na start na **waniliowej** krowie (nauka eventów, zero własnej encji)

### Faza 2: Własna encja krowy `ProductiveCow` (Wariant B)  `[duże — pocięte na kawałki]`
- [ ] (a) Rejestracja jednego `EntityType` + pole `variant` (synchronizowane serwer↔klient)
- [ ] (b) Tabela wariantów: `variant → { tekstura, produkt, biom }`
- [ ] (c) Renderer wybiera teksturę wg wariantu (retekstura modelu waniliowej krowy)
- [ ] (d) Spawn egg
- [ ] (e) Dojenie czyta wariant → wydaje odpowiedni bukket

### Faza 3: Pierwsza maszyna przetwórcza  `[duże — SERCE MODA]`
- [ ] Block + block entity (logika ticka)
- [ ] Proste GUI / menu (kontener)
- [ ] System receptury: mleko → materiał (np. iron milk → sztabka żelaza) + śmietanka (produkt uboczny)
- [ ] Stacja paszowa (zautomatyzowana) — droższy wariant stołu paszowego, auto-pobieranie paszy z sąsiednich inwentarzy/rur, bez ręcznego wkładania. WCHODZI po tym, jak podstawowy stół paszowy działa ręcznie.

> To jest Twój punkt 9. Tu mod przestaje być „zestawem itemów", a staje się mechaniką.

### Faza 4: Standalone-fun (powód do grania bez modpacka)  `[średnie]`
- [ ] Pijalne „sample" mleka → efekty per wariant (jak waniliowe mleko, ale z efektem)
- [ ] „Creamery": śmietanka buffuje dowolne jedzenie (saturacja / mały efekt)

> 🎯 **PUNKT „GRYWALNE I POKAZYWALNE".** Tu masz pierwszy kompletny, grający rdzeń. Strategia release'u to **Twoja decyzja**, nie obowiązek:
> - **Wczesny release** — feedback i motywacja od ludzi, ale ryzyko cienkiego pierwszego wrażenia.
> - **Budowa do „głównej odsłony"** — mocniejszy plusk na premierę, ale długa, samotna droga bez echa z zewnątrz.
> Kompromis: nie musisz robić pełnego release'u, żeby ktoś to widział — devlog / gify postępów / znajomi / mały Discord dają dawkę „ktoś to widzi i mówi że fajne" bez palenia premiery.

---

## Warstwa 2 — Głębia

### Warianty na skalę  `[łatwe — architektura już gotowa]`
- [ ] Dodanie wszystkich 13 wariantów jako wpisy w tabeli (tekstura + mleko + biom)
- [ ] (Wariant B z Warstwy 1 już to umożliwia — to tylko dosypanie danych, nie nowy kod)
> Uwaga o grafice: każdy wariant = **ręczna robota plastyczna** — fill wiadra (baza wspólna) + skórka krowy. Data-driven oszczędza KOD, nie GRAFIKĘ. Krowy dodawane przez twórców paczek to ich grafika, nie Twoja.

### Genetyka i hodowla  `[duże — „głębia" jak w Productive Bees]`
- [ ] Geny: **Yield** (wydajność), **Purity** (szansa na produkt uboczny), **Fertility** (szybkość rozmnażania), **Stability** (odporność na mutację vs szansa na nowy wariant)
- [ ] Krzyżowanie → mieszanie genów (mendlowsko); rzadkie recesywne „prime" geny odblokowujące wyższy tier
- [ ] Krowy hybrydowe/stopowe (np. copper + tin → bronze milk) — materiał dostępny tylko z krzyżówki
- [ ] Item do rozmnażania: domyślnie trzcina; konfigurowalny (pod modpacki)

### Multiblok obory  `[najtrudniejsze]`
- [ ] Krowa „pod pachę" — item przenoszący encję (bez męczenia się z leashem)
- [ ] Multiblok z wykrywaniem struktury
- [ ] Pojemność krów zależna od rozmiaru; rozbudowa blokami (jak portale w Occultism)
- [ ] Komfort (trawa / miejsce / czystość) = mnożnik wydajności

### Spawny  `[średnie]`
- [ ] Krowy spawnują w różnych biomach w zależności od wariantu

---

## Warstwa 3 — ATM / integracja (na koniec; rób, jeśli się opłaca)

### Fluidy  `[duże]`
- [ ] Mleko jako ciecz — **zacznij od generycznej, nie 13 osobnych płynów**
- [ ] Wiadro ↔ fluid

### Kompatybilność z tech modami  `[średnie — łatwiejsze niż myślisz]`
- [ ] Implementacja standardowej capability `FluidHandler` NeoForge
- [ ] Efekt: rury Mekanismu / Thermalu / itd. podłączają się **same** — zero kodu pod konkretny mod

### Data-driven (killer feature pod ATM)  `[zaawansowane]`
- [ ] Definicje krów przez datapack/JSON (custom registry + codeki)
- [ ] Twórcy paczek dodają własne krowy → dowolny zasób, też z innych modów, bez pisania kodu
- [ ] Konfigurowalne breeding items pod modpacki

### Nasze krowy/buckety pod zasoby z modów (ATM)  `[średnie — zasilane przez data-driven]`
> Oprócz bazowych 13 (vanilla materiały) chcemy **gotowy zestaw** krów + buketów dla zasobów modowych z ATM.
- [ ] Krowy + buckety dla popularnych zasobów ATM (np. allthemodium, vibranium, unobtainium, draconium)
- [ ] Ładowane **warunkowo** — tylko gdy dany mod jest obecny (load conditions NeoForge), inaczej cicho pomijane
- [ ] Produkt wariantu wskazuje na item z obcego moda (po id/tagu) — czyli to po prostu kolejne wpisy w tabeli wariantów, nie nowy kod
> To jest dokładnie zysk z Wariantu B + data-driven: „krowa na draconium" = wpis w danych, a nie nowa klasa.

### Maszyny i ulepszenia  `[duże]`
- [ ] Maszyny ekstraktujące
- [ ] Maszyny mieszające geny
- [ ] Ulepszenia do maszyn (szybkość, wydajność, automatyzacja)

---

## Przekrojowe (przez cały czas trwania)
- [ ] JEI/EMI — przepisy widoczne w grze (podstawa)
- [ ] Config
- [ ] Balans
- [ ] Dokumentacja datapacków dla twórców paczek (gdy data-driven gotowe)

---

## Notka strategiczna
- **Wróg numer jeden:** próba zrobienia wszystkiego naraz. Trzymaj się warstw, kończ kawałki.
- **Po Warstwie 1 masz coś grywalnego.** Release teraz czy budowa do „głównej odsłony" — Twój wybór. Ważne tylko, żeby na tej długiej drodze KTOŚ to widział (choćby devlog), bo samotny rok bez echa zabija zapał szybciej niż cokolwiek.
- „Skończony mod" nie istnieje — jest tylko „grywalny i rosnący".
- Każda sesja powinna kończyć się czymś, co działa w grze. Nie zaczynaj rzeczy, której nie domkniesz.
