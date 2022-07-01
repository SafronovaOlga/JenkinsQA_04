import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _UserAdminTest extends BaseTest {

    private static final String TEXT = "JavaForQas";

    @Test
    public void testAddDescription() {
        String descriptionText = new HomePage(getDriver())
                .clickAdminLink()
                .clickDescriptionLink()
                .setDescriptionTextarea(TEXT)
                .clickSaveButton()
                .getDescriptionText();

        Assert.assertEquals(descriptionText, TEXT);
    }

//    @Test (dependsOnMethods = "testAddDescription")
//    public void testEditDescription() {
//        UserAdminPage a = new UserAdminPage(getDriver())
//                .clickDescriptionLink()
//                .setDescriptionTextarea(TEXT);
//    }
//
//    @Test (dependsOnMethods = {"testAddDescription", "testEditDescription"})
//    public void testDeleteDescription() {
//        UserAdminPage a = new UserAdminPage(getDriver())
//                .clickDescriptionLink()
//                .setDescriptionTextarea(TEXT);
//    }
}
