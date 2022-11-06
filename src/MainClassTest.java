import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetLocalNumber() {
        Assert.assertTrue("Метод должен вернуть 14",getLocalNumber() == 14);
    }
}
