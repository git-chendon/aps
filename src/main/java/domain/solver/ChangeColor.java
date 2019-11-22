package domain.solver;

import java.awt.*;

/**
 * @auther chen.don
 * @date 2019/10/24 14:22
 */
public class ChangeColor {

    public int changeColorPunish(Color color1,Color color2) {
        long rMean = ((long)color1.getRed() + (long)color2.getRed());
        long r = (long)color1.getRed() - (long)color2.getRed();
        long g = (long)color1.getGreen() - (long)color2.getGreen();
        long b = (long)color1.getBlue() - (long)color2.getBlue();
        double dist = Math.sqrt((((512 + rMean)*r*r)>>8) + 4*g*g + (((767 - rMean)*b*b)>>8));
        return (int)((dist/764)*2);
    }

}
