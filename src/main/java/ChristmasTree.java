class ChristmasTree {
  // Methode als public aufrufen, damit diese auch in stem Methode läuft
  public static StringBuilder builder = new StringBuilder();

  public static void main(String[] args) {

    int hoehe;
    String baum = "";
    String art; //später candles, frame oder clone


    // Wenn "help" eingegeben wird
    if (args[0].equals("help")) {
      builder.append("Usage: java ChristmasTree <height> <option>\n")
        .append("Options: candles <number of candles> <candle character>\n")
        .append("         frame <frame character>\n")
        .append("         clone <number of trees (original + clones)>");

    // wenn eine Zahl > 2 eingegeben wird
    } else if (Integer.parseInt(args[0]) >= 3) {
      // Initialisierung optionale Kommandozeilenparameter
      hoehe = Integer.parseInt(args[0]);
      art = args[1];
      int spacesToStem; //= ((hoehe - (hoehe / 10)) - 2), bereits in stem Methode;
      int stemWidth; //= ((2 * (hoehe / 10)) + 1), bereits in stem Methode;

      switch (art) { // switch für versch. "Arten" (candles,frame,clone,default)
        case "candles":
          int anzahlSterne = 0;
          int counter = 0;

          // Initialisierung optionaler Kommandozeilenparameter wenn 4 Kommandozeilenparameter
          int kerzenAnzahl = Integer.parseInt(args[2]);
          String character = String.valueOf(args[3]);
          //convert String to character
          char kerzenArt = character.charAt(0);

          // Anzahl Sternchen
          for (int row = 0; row < (hoehe - 1); row++) {
            anzahlSterne = anzahlSterne + (2 * row + 1);
          }

          // Initialisierung und Zuweisung von Werten zur Berechnung Kerzen
          int leftOver = anzahlSterne - kerzenAnzahl;
          int abstandKs = leftOver / kerzenAnzahl;
          int rest = leftOver - kerzenAnzahl * abstandKs;

          // Schleife um Anzahl Kerzen anzupassen
          while (rest >= abstandKs || rest * (-1) >= abstandKs) {
            //2. Bdg. wenn mehr Kerzen als Baum und Betrag von Abstand genommen
            //werden muss, damit IMMER min 1 Abstand zwischen Kerze Und *
            kerzenAnzahl--;
            leftOver = anzahlSterne - kerzenAnzahl;
            abstandKs = leftOver / kerzenAnzahl;
            rest = leftOver - kerzenAnzahl * abstandKs;
          }

          // Amount of rows
          for (int row = 0; row < (hoehe - 1); row++) {

            // Space on left side of stars
            for (int i = 0; i < (hoehe - 2 - row); i++) {
              builder.append(" ");
            }
            // Branches
            for (int j = 0; j < (2 * row + 1); j++) {
              if (counter == abstandKs) {
                builder.append(kerzenArt);
                counter = -1; // counter zurücksetzen
              } else {
                builder.append("*");
              }
              counter = counter + 1;
            }
            builder.append("\n");
          }
          // Methode stem aufrufen
          stem(hoehe, baum);

          break; // end of case candles

        case "frame":

          // Initialisierung optionaler Kommandozeilenparameter frameCharacter
          character = String.valueOf(args[2]);
          char frameArt = character.charAt(0);
          spacesToStem = ((hoehe - (hoehe / 10)) - 1); //neue Berechnung wg Rahmen

          // First row frame
          int zeile = 0;
          while (zeile < (hoehe * 2) + 1) {
            builder.append(frameArt);
            zeile++;
          }
          builder.append("\n");

          int row = 0;
          // Amount of rows für *
          while (row < (hoehe - 1)) {
            builder.append(frameArt);

            // Space on left side of stars
            for (int i = 0; i < (hoehe - 1 - row); i++) {
              builder.append(" ");
            }
            // Branches
            for (int j = 0; j < (2 * row + 1); j++) {
              builder.append("*");
            }
            // Space on right side of stem
            for (int i = 0; i < (hoehe - 1 - row); i++) {
              builder.append(" ");
            }
            builder.append(frameArt);
            builder.append("\n");
            row++;
          }
          builder.append(frameArt);
          builder.append(" "); //Leerzeichen einfügen, da Methode vordefiniert ist
          stem(hoehe, baum); // Methode stem aufrufen
          //Space right side of stem
          for (int i = 0; i < spacesToStem; i++) {
            builder.append(" ");
          }
          builder.append(frameArt);
          builder.append("\n");

          // last row frame
          zeile = 0;
          while (zeile < (hoehe * 2) + 1) {
            builder.append(frameArt);
            zeile++;
          }

          break; // end of case frame

        case "clone":
          // Initialisierung optionaler Kommandozeilenparameter Anzahl clones
          int cloneAnzahl = Integer.parseInt(args[2]);
          spacesToStem = ((hoehe - (hoehe / 10)) - 1); //neue Berechnung wg Leerzeichen zw. Bäumen


          if (cloneAnzahl == 0) {
            builder.append("");
          } else {

            // Amount of rows
            for (row = 0; row < (hoehe - 1); row++) {

              int spaceToStars = (hoehe - 2 - row);

              // Space on left side of stars
              for (int j = 0; j < spaceToStars; j++) {
                builder.append(" ");
              }
              // Branches
              for (int j = 0; j < (2 * row + 1); j++) {
                builder.append("*");
              }

              for (int i = 1; i < cloneAnzahl; i++) {
                //space on right side of stars (Doppelt + 1)
                for (int j = 0; j < (2 * spaceToStars) + 1; j++) {
                  builder.append(" ");
                }
                // Branches
                for (int j = 0; j < (2 * row + 1); j++) {
                  builder.append("*");
                }
              }
              builder.append("\n");
            }

            // Methode stem aufrufen
            stem(hoehe, baum);

            for (int i = 1; i < cloneAnzahl; i++) {
              //space right side of stem
              for (int j = 0; j < spacesToStem; j++) {
                builder.append(" ");
              }
              stem(hoehe, baum);
            }
          } //end else

          break;

        //wird etwas anderes eingegeben außer candles, frame oder clone
        default:
          builder.append("Unknown option: " + art);
          break;

      } // End switch

      // wird eine Zahl kleiner als 3 eingegeben wird auch nichts ausgegeben
    } else if (Integer.parseInt(args[0]) < 3) {
      builder.append("too small");
    }
    System.out.println(builder);
    builder.setLength(0); // Damit builder resettet wird (für Test notwendig)
  } // end of main Methode

  // Seperate Methode für Stamm
  public static String stem(int hoehe, String baum) {
    int spacesToStem = ((hoehe - (hoehe / 10)) - 2);
    int stemWidth = ((2 * (hoehe / 10)) + 1);

    // Space on left side of Stem
    for (int i = 0; i < spacesToStem; i++) {
      builder.append(" ");
    }
    // Stem
    for (int i = 0; i < stemWidth; i++) {
      builder.append("I");
    }
    return baum;
  } // End stem Methode
} // End class
