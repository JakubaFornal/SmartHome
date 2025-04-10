
package Design;

import java.awt.*;
import java.util.Vector;
import java.awt.Color;

public class colors {
    public static Vector<Color> colorVector = new Vector<>();

    static {
        colorVector.add(Color.decode("#D8D8D8"));       // 0. grey
        colorVector.add(Color.decode("#00BBFF"));       // 1. blue
        colorVector.add(Color.decode("#BCBCBC"));       // 2. silver
        colorVector.add(Color.decode("#FF6B27"));       // 3. orange
        colorVector.add(Color.decode("#B5EBFF"));       // 4. light blue

        colorVector.add(Color.GREEN);
        colorVector.add(Color.BLUE);
    }



}
