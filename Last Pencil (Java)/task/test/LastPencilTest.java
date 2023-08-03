import org.hyperskill.hstest.dynamic.DynamicTest;

import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.util.Arrays;
import java.util.List;

public class LastPencilTest extends StageTest<String> {

    @DynamicTest
    CheckResult CheckOutput() {
        TestedProgram testedProgram = new TestedProgram();
        String output = testedProgram.start().toLowerCase().trim();
        //Checking the number of output lines
        String[] lines = output.strip().split("\\n");
        //Create a list with empty strings if any
        List<String> linesNonEmpty = Arrays.stream(lines).filter(s -> s.length() != 0).toList();
        //Checking for string requirements before starting the main testing.
        if (linesNonEmpty.size() != 2) {
            throw new WrongAnswer("Your program should print 2 non-empty lines.");
        }
        //We place lines containing | for testing
        List<String> checkPencils = Arrays.stream(lines).filter(s -> s.contains("|")).toList();

        //Checking for an empty string
        if (checkPencils.isEmpty()) {
            throw new WrongAnswer("The output should include one line with several vertical bar "
                    + "symbols ('|') representing pencils.");
        }
        // Checking a string for more than 1 string with |
        if (checkPencils.size() > 1) {
            throw new WrongAnswer("The output should include only one line with several vertical bar "
                    + "symbols ('|') representing pencils.");
        }
        // distinct() makes it possible to get the number of non-unique elements, thus getting 2, the test fails
        if (checkPencils.get(0).chars().distinct().count() != 1) {
            throw new WrongAnswer("The line with pencils should not contain any " +
                    "symbols other than the '|' symbol.");
        }

        boolean checkTurn = Arrays.stream(lines).anyMatch(s -> s.toLowerCase().contains("your turn"));
        //Checking for the content in the line of one of the requirements "Your turn"
        if (!checkTurn) {
            throw new WrongAnswer("The output should include one line with the" +
                    " \"Your turn\" string");
        }
        //NEW TEST
        //easy
        if (!lines[1].toLowerCase().trim().contains("your turn")) {
            throw new WrongAnswer("The second output should include one line with " +
                    "the \"Your turn\" string");
        }
        if (!lines[0].toLowerCase().trim().contains("|")) {
            throw new WrongAnswer("The first output must include one line with at " +
                    "least one \"|\" character");
        }

        //Can be made harder
//        if (!lines.get(1).toLowerCase().trim().contains("your turn")){
//            throw new WrongAnswer("The order of the lines is not respected");
//        }
//        if (!lines.get(0).toLowerCase().trim().contains("|")){
//            throw new WrongAnswer("The order of the lines is not respected");
//        }
        return CheckResult.correct();
    }

}