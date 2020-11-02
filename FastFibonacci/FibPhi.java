import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.*;
import java.util.Scanner;


public class FibPhi {

   public static void main(String[] args) {
      final int numEntries = 1000;
      
      File phi = new File("C:\\Users\\sethj\\Documents\\phi.txt");
      File sqrt5 = new File("C:\\Users\\sethj\\Documents\\squareRootFive.txt");
      File outFile = new File("C:\\Users\\sethj\\Documents\\output.txt");
      try {
         Scanner readPhi = new Scanner(phi);
         Scanner readSQRT5 = new Scanner(sqrt5);
         
         final String PHI = readPhi.next();
         final String SQRT5 = readSQRT5.next();
         final MathContext mc = new MathContext(1000, RoundingMode.HALF_DOWN);
         final BigDecimal HALF = new BigDecimal("0.5", mc); 
         final BigDecimal BASE = new BigDecimal(PHI, mc);
         final BigDecimal SQRT_5 = new BigDecimal(SQRT5, mc);
         
         BigInteger a = BigInteger.ZERO;
         BigInteger b = BigInteger.ONE;
         BigInteger c = BigInteger.ZERO;
         
         boolean flag = false;
         
         FileWriter fw = new FileWriter(outFile);
         fw.write("0\t0\n1\t1\n");
         for(int i = 2; i < numEntries; i++) {
            BigDecimal fib_i = BASE.pow(i).divide(SQRT_5, 1000, RoundingMode.HALF_DOWN).add(HALF, new MathContext(0, RoundingMode.DOWN));
            BigInteger answer = fib_i.toBigInteger();
            
            c = a.add(b);
            a = b;
            b = c;
            
            if(c.equals(answer) || i <= 2) {
               fw.write(i + "\t" + answer + "\n");
            }else {
               System.out.println(i+"\n"+c.toString()+"\n"+answer.toString());
               flag = true;
            }
            if(flag) {
               fw.close();
               readPhi.close();
               readSQRT5.close();
               System.exit(0);
            }
         }
         
      }catch(FileNotFoundException e) {
         System.out.println(e);
      } catch (IOException e) {
         System.out.println(e);
      }
   }
}
