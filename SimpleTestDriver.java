import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class SimpleTestDriver {

    // Test driver method to check even and greater than 100 conditions
    private static void testDriver(Integer a) {
        int valueOfA = a & 0xFF;

        // Check even condition
        boolean isEven = valueOfA % 2 == 0;
        if (isEven) {
            System.out.println("Even");
            // Add assertion for the even condition
            assert (valueOfA % 2 == 0);
        } else {
            System.out.println("Odd");
            // Add assertion for the odd condition
            assert (valueOfA % 2 != 0);
        }

        // Check greater than 100 condition
        boolean isGreaterThan100 = valueOfA > 100;
        if (isGreaterThan100) {
            System.out.println("Greater than 100");
            // Add assertion for greater than 100 condition
            assert (valueOfA > 100);
        } else {
            System.out.println("Less than or equal to 100");
            // Add assertion for less than or equal to 100 condition
            assert (valueOfA <= 100);
        }
    }

    // Fuzzing entry point
    @Fuzz
    public void testSimpleTest(Integer a) {
        System.out.println("------ Generated input: " + a + " ------");

        // Call the test driver method
        testDriver(a);
    }
}
