package in.grastone.dating.main.utility;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Random;

@Component
public class Util {

    private Random lRandom = new Random();
    private DecimalFormat lDecimalFormat = new DecimalFormat("000000");

    public String generateOTP(){
        return lDecimalFormat.format(lRandom.nextInt(999999));
    }

}
