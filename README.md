# Conway's Game of Life - HSLU ENLAB Übung

## Übersicht
Diese Übung ist Teil des Moduls ENLAB (Engineering Lab) an der Hochschule Luzern (HSLU). Sie implementiert Conway's Game of Life, ein zelluläres Automatenmodell, das 1970 von dem Mathematiker John Horton Conway entwickelt wurde.

## Was ist Conway's Game of Life?
Conway's Game of Life ist ein zelluläres Automatenmodell, das auf einem zweidimensionalen Raster basiert. Jede Zelle kann entweder lebendig oder tot sein und entwickelt sich nach bestimmten Regeln:

1. Eine lebendige Zelle mit weniger als zwei lebendigen Nachbarn stirbt (Unterbevölkerung).
2. Eine lebendige Zelle mit zwei oder drei lebendigen Nachbarn überlebt.
3. Eine lebendige Zelle mit mehr als drei lebendigen Nachbarn stirbt (Überbevölkerung).
4. Eine tote Zelle mit genau drei lebendigen Nachbarn wird lebendig (Reproduktion).

## Projektstruktur
Das Projekt verwendet Maven als Build-Tool und ist als Java-Anwendung mit Spring Boot implementiert.

- `src/main/java/ch/hslu/enlab/conway/World.java`: Hauptklasse, die die Welt und ihre Zellen repräsentiert
- `src/test/java/ch/hslu/enlab/conway/WorldTest.java`: Testklasse mit JUnit-Tests für die Implementierung

## Aufgaben für Studierende
1. **Verstehen der aktuellen Implementierung**: Analysieren Sie den bestehenden Code und verstehen Sie, wie die Welt und ihre Zellen modelliert werden.
2. **Korrigieren der Implementierung**: Die aktuelle Implementierung enthält Fehler in den Regeln des Spiels. Korrigieren Sie diese basierend auf den oben genannten Regeln.
3. **Implementieren der Web-API**: Entwickeln Sie eine Webanwendung, die das in `src/main/resources/gol.openapi.yaml` spezifizierte HTTP-Interface implementiert. Die API soll folgende Funktionalität bieten:
   - Einen Endpunkt `/generate_world`, der eine POST-Anfrage mit einer Welt und einer Generationsnummer akzeptiert
   - Die Welt für die angegebene Anzahl von Generationen simulieren
   - Die resultierende Welt als JSON-Antwort zurückgeben
   
   **Wichtig**: Die Implementierung soll durch Tests sichergestellt werden. Folgen Sie dem Test-Driven Development (TDD) Ansatz:
   - Schreiben Sie zuerst Tests für die API-Endpunkte
   - Implementieren Sie dann die Funktionalität, um die Tests zu bestehen
   - Refaktorisieren Sie den Code nach Bedarf, während Sie sicherstellen, dass alle Tests weiterhin bestehen


## Weiterführende Ressourcen
- [Conway's Game of Life auf Wikipedia](https://de.wikipedia.org/wiki/Conways_Spiel_des_Lebens)
- [Interaktive Demo](https://playgameoflife.com/)
- [Muster und Strukturen im Game of Life](https://conwaylife.com/wiki/Main_Page)
