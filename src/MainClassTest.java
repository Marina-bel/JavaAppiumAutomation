import com.sun.deploy.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    //ТЕСТ1
    @Test
    public void testGetLocalNumber() {
        Assert.assertTrue("Метод должен вернуть 14",getLocalNumber() == 14);
    }

    //ТЕСТ2
    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("getClassNumber() !> 45",getClassNumber() > 45);
    }

    //ТЕСТ3
    @Test
    public void testGetClassString() {
        Assert.assertTrue("Строка содержит подстроку \"hello\" или \"Hello\"",getClassString().toLowerCase().contains("hello".toLowerCase()));

    }
}
