import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class ChristmasTreeTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }

  @Rule
  public Timeout globalTimeout = Timeout.seconds(600);

  // help

  @Test
  public void christmasTree_help() {
    String[] args = {"help"};
    ChristmasTree.main(args);
    assertEquals("Usage: java ChristmasTree <height> <option>\n"
          + "Options: candles <number of candles> <candle character>\n"
          + "         frame <frame character>\n"
          + "         clone <number of trees (original + clones)>\n",
        outContent.toString()
                  .replace("\r", ""));
  }

  // zu klein

  @Test
  public void christmasTree_0() {
    String[] args = {"0"};
    ChristmasTree.main(args);
    assertEquals("too small\n",
        outContent.toString()
                  .replace("\r", ""));
  }

  @Test
  public void christmasTree_1() {
    String[] args = {"1"};
    ChristmasTree.main(args);
    assertEquals("too small\n",
        outContent.toString()
                  .replace("\r", ""));
  }

  @Test
  public void christmasTree_2() {
    String[] args = {"2"};
    ChristmasTree.main(args);
    assertEquals("too small\n",
        outContent.toString()
                  .replace("\r", ""));
  }

  // Unbekannte Option

  @Test
  public void christmasTree_unbekannte_Option() {
    String[] args = {"3", "alles"};
    ChristmasTree.main(args);
    assertEquals("Unknown option: alles\n",
        outContent.toString()
                  .replace("\r", ""));
  }

  // Kerzen

  @Test
  public void christmasTree_8_with_15_candles() {
    String[] args = {"8", "candles", "15", "i"};
    ChristmasTree.main(args);
    assertEquals(String.join("\n",
        "      *",
        "     **i",
        "    ***i*",
        "   **i***i",
        "  ***i***i*",
        " **i***i***i",
        "***i***i***i*",
        "      I\n"),
        outContent.toString()
                  .replace("\r", ""));
  }

  @Test
  public void christmasTree_8_with_20_candles() {
    String[] args = {"8", "candles", "20", "i"};
    ChristmasTree.main(args);
    assertEquals(String.join("\n",
        "      *",
        "     *i*",
        "    *i**i",
        "   **i**i*",
        "  *i**i**i*",
        " *i**i**i**i",
        "**i**i**i**i*",
        "      I\n"),
        outContent.toString()
                  .replace("\r", ""));
  }


  @Test
  public void christmasTree_13_with_20_candles() {
    String[] args = {"13", "candles", "45", "i"};
    ChristmasTree.main(args);
    assertEquals(String.join("\n",
        "           *",
        "          **i",
        "         ***i*",
        "        **i***i",
        "       ***i***i*",
        "      **i***i***i",
        "     ***i***i***i*",
        "    **i***i***i***i",
        "   ***i***i***i***i*",
        "  **i***i***i***i***i",
        " ***i***i***i***i***i*",
        "**i***i***i***i***i***i",
        "          III\n"),
        outContent.toString()
                  .replace("\r", ""));
  }

  // Frames

  @Test
  public void christmasTree_5_with_frame_z() {
    String[] args = {"5", "frame", "z"};
    ChristmasTree.main(args);
    assertEquals(String.join("\n",
        "zzzzzzzzzzz",
        "z    *    z",
        "z   ***   z",
        "z  *****  z",
        "z ******* z",
        "z    I    z",
        "zzzzzzzzzzz\n"),
        outContent.toString()
                  .replace("\r", ""));
  }

  @Test
  public void christmasTree_13_with_frame_o() {
    String[] args = {"13", "frame", "o"};
    ChristmasTree.main(args);
    assertEquals(String.join("\n",
        "ooooooooooooooooooooooooooo",
        "o            *            o",
        "o           ***           o",
        "o          *****          o",
        "o         *******         o",
        "o        *********        o",
        "o       ***********       o",
        "o      *************      o",
        "o     ***************     o",
        "o    *****************    o",
        "o   *******************   o",
        "o  *********************  o",
        "o *********************** o",
        "o           III           o",
        "ooooooooooooooooooooooooooo\n"),
        outContent.toString()
                  .replace("\r", ""));
  }

  // Clones

  @Test
  public void christmasTree_7_with_clone_5() {
    String[] args = {"7", "clone", "5"};
    ChristmasTree.main(args);
    assertEquals(String.join("\n",
        "     *           *           *           *           *",
        "    ***         ***         ***         ***         ***",
        "   *****       *****       *****       *****       *****",
        "  *******     *******     *******     *******     *******",
        " *********   *********   *********   *********   *********",
        "*********** *********** *********** *********** ***********",
        "     I           I           I           I           I\n"),
        outContent.toString()
                  .replace("\r", ""));
  }


  @Test
  public void christmasTree_15_with_clone_3() {
    String[] args = {"15", "clone", "3"};
    ChristmasTree.main(args);
    assertEquals(String.join("\n",
        "             *                           *                           *",
        "            ***                         ***                         ***",
        "           *****                       *****                       *****",
        "          *******                     *******                     *******",
        "         *********                   *********                   *********",
        "        ***********                 ***********                 ***********",
        "       *************               *************               *************",
        "      ***************             ***************             ***************",
        "     *****************           *****************           *****************",
        "    *******************         *******************         *******************",
        "   *********************       *********************       *********************",
        "  ***********************     ***********************     ***********************",
        " *************************   *************************   *************************",
        "*************************** *************************** ***************************",
        "            III                         III                         III\n"),
        outContent.toString()
                  .replace("\r", ""));
  }
}
