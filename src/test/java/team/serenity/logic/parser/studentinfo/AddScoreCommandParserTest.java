package team.serenity.logic.parser.studentinfo;

import static team.serenity.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.serenity.logic.commands.CommandTestUtil.ADD_SCORE_DESC;
import static team.serenity.logic.commands.CommandTestUtil.EMPTY_PREAMBLE;
import static team.serenity.logic.commands.CommandTestUtil.INVALID_STUDENT_WITHOUT_NAME;
import static team.serenity.logic.commands.CommandTestUtil.INVALID_STUDENT_WITHOUT_NUMBER;
import static team.serenity.logic.commands.CommandTestUtil.NEG_NUMBER_ADD_SCORE;
import static team.serenity.logic.commands.CommandTestUtil.NON_INTEGER;
import static team.serenity.logic.commands.CommandTestUtil.NON_INTEGER_ADD_SCORE;
import static team.serenity.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static team.serenity.logic.commands.CommandTestUtil.STUDENT_DESC_AARON;
import static team.serenity.logic.commands.CommandTestUtil.STUDENT_NAME_DESC;
import static team.serenity.logic.commands.CommandTestUtil.STUDENT_NUMBER_DESC;
import static team.serenity.logic.commands.CommandTestUtil.VALID_INDEX;
import static team.serenity.logic.commands.CommandTestUtil.VALID_SCORE;
import static team.serenity.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.serenity.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import team.serenity.commons.core.index.Index;
import team.serenity.logic.commands.exceptions.CommandException;
import team.serenity.logic.commands.studentinfo.AddScoreCommand;
import team.serenity.model.group.student.Student;
import team.serenity.model.group.studentinfo.Participation;
import team.serenity.testutil.StudentBuilder;

class AddScoreCommandParserTest {

    private AddScoreCommandParser parser = new AddScoreCommandParser();

    @Test
    public void parse_missingStudentName_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE);
        String userInput = PREAMBLE_WHITESPACE + INVALID_STUDENT_WITHOUT_NAME + " " + ADD_SCORE_DESC;

        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_missingStudentId_throwsCommandException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE);
        String userInput = PREAMBLE_WHITESPACE + INVALID_STUDENT_WITHOUT_NUMBER + " " + ADD_SCORE_DESC;

        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_missingScore_throwsCommandException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE);

        assertParseFailure(parser, PREAMBLE_WHITESPACE + STUDENT_DESC_AARON, expectedMessage);
    }

    @Test
    public void parse_invalidIndex_throwsCommandException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE);

        assertParseFailure(parser, NON_INTEGER + " " + ADD_SCORE_DESC, expectedMessage);
        assertParseFailure(parser, EMPTY_PREAMBLE + ADD_SCORE_DESC, expectedMessage);
    }

    @Test
    public void parse_invalidScore_throwsCommandException() {
        String expectedMessageOne = String.format(Participation.MESSAGE_CONSTRAINTS);
        String expectedMessageTwo = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE);

        String userInputOne = PREAMBLE_WHITESPACE + VALID_INDEX + " " + NON_INTEGER_ADD_SCORE;
        String userInputTwo = PREAMBLE_WHITESPACE + STUDENT_DESC_AARON + " " + NON_INTEGER_ADD_SCORE;
        String userInputThree = PREAMBLE_WHITESPACE + VALID_INDEX + " " + NEG_NUMBER_ADD_SCORE;
        String userInputFour = PREAMBLE_WHITESPACE + STUDENT_DESC_AARON + " " + NEG_NUMBER_ADD_SCORE;

        assertParseFailure(parser, userInputOne, expectedMessageOne);
        assertParseFailure(parser, userInputTwo, expectedMessageOne);
        assertParseFailure(parser, userInputThree, expectedMessageTwo);
        assertParseFailure(parser, userInputFour, expectedMessageTwo);
    }

    @Test
    public void parse_validStudentAndNumberParameter_returnsAddScoreCommand() throws CommandException {
        Student student = new StudentBuilder().build();
        int score = Integer.parseInt(VALID_SCORE);
        String userInput = PREAMBLE_WHITESPACE + STUDENT_DESC_AARON + " " + ADD_SCORE_DESC;

        assertParseSuccess(parser, userInput, new AddScoreCommand(student, score));
    }

    @Test
    public void parse_studentAndIndex_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE);

        assertParseFailure(parser, VALID_INDEX + STUDENT_DESC_AARON + ADD_SCORE_DESC , expectedMessage);
    }

    @Test
    public void parse_studentNameAndIndex_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE);

        assertParseFailure(parser, VALID_INDEX + STUDENT_NAME_DESC + ADD_SCORE_DESC, expectedMessage);
    }

    @Test
    public void parse_studentNumberAndIndex_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE);

        assertParseFailure(parser, VALID_INDEX + STUDENT_NUMBER_DESC + ADD_SCORE_DESC, expectedMessage);
    }

    @Test
    public void parse_validIndexParameter_returnsAddScoreCommand() throws CommandException {
        Index index = Index.fromOneBased(Integer.parseInt(VALID_INDEX));
        int score = Integer.parseInt(VALID_SCORE);

        assertParseSuccess(parser, VALID_INDEX + " " + ADD_SCORE_DESC, new AddScoreCommand(index, score));
    }

}
