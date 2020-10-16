package seedu.address.testutil;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlySerenity;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.group.Lesson;
import seedu.address.model.group.Question;
import seedu.address.model.group.Student;
import seedu.address.model.group.StudentInfo;
import seedu.address.model.person.Person;

public class ModelStub implements Model {

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getSerenityFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSerenityFilePath(Path serenityFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSerenity(ReadOnlySerenity serenity) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlySerenity getSerenity() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasGroup(Group group) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteGroup(Group target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addGroup(Group group) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredGroupList(Predicate<Group> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateStudentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateLessonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredLessonList(Predicate<Lesson> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateStudentInfoList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateQuestionList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Group> getFilteredGroupList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addStudentToGroup(Student student, Predicate<Group> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeStudentFromGroup(Student student, Predicate<Group> predicate) {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public ObservableList<Student> getStudentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Lesson> getLessonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Lesson> getFilteredLessonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<StudentInfo> getStudentInfoList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Question> getQuestionList() {
        throw new AssertionError("This method should not be called.");
    }

}
