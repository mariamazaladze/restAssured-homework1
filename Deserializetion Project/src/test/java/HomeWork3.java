import Deserialization.Step;
import org.testng.annotations.Test;

public class HomeWork3 {

    Step step = new Step();

    @Test
    public void succsessful() {
        step.
                callAPI().SuccsesfulParams();
    }

    @Test
    public void unsuccsessful() {
        step.
                callAPI().
                badRequest();
    }
    @Test
    public void lasttest(){

        step.lastcase();
    }
}
