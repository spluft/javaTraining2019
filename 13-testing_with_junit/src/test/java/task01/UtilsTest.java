package task01;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("Setting up..");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tearing down..");
    }

    @BeforeClass
    public static void beforeClass(){
        System.out.println("beforeClass");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass");
    }

    @Test
    public void testConcatenateWords() {
        String s = Utils.concatenateWords("first", "second");
        assertEquals("firstsecond", s);
    }

    @Test
    public void testComputeFactorial() {
        Long factorial = Utils.computeFactorial(3);
        assertEquals(Long.valueOf(6), factorial);
    }

    @Test(timeout = 10L)
    public void testFactorialWithTimeout() {
        Long factorial = Utils.computeFactorial(25);
        assertEquals(Long.valueOf(7034535277573963776L), factorial);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpectedException() {
        Utils.computeFactorial(-5);
    }

    @Ignore
    @Test
    public void testNormalizeWord(){
        Utils.normalizeWord("foo");
    }
}
