package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.group.Lesson;
import seedu.address.model.group.Student;
import seedu.address.model.group.StudentInfo;
import seedu.address.model.group.UniqueLessonList;
import seedu.address.model.group.UniqueStudentList;

/**
 * Jackson-friendly version of {@link Group}.
 */
class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedClass> lessons = new ArrayList<>();


    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        name = source.getName();
        students.addAll(source.getStudents().asUnmodifiableObservableList().stream()
            .map(JsonAdaptedStudent::new)
            .collect(Collectors.toList()));
        lessons.addAll(source.getSortedLessons().asUnmodifiableObservableList().stream()
            .map(JsonAdaptedClass::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Group} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted group.
     */
    public Group toModelType() throws IllegalValueException {

        String modelName = name;

        final List<Student> groupStudents = new ArrayList<>();
        for (JsonAdaptedStudent groupStudent : students) {
            groupStudents.add(groupStudent.toModelType());
        }
        final UniqueStudentList modelStudents = new UniqueStudentList();
        modelStudents.setStudents(new ArrayList<>(groupStudents));

        final Set<StudentInfo> studentsInfo = new HashSet<>();

        final List<Lesson> groupClasses = new ArrayList<>();
        for (JsonAdaptedClass groupClass : lessons) {
            Lesson classItem = new Lesson(groupClass.getName(), studentsInfo);
            groupClasses.add(classItem);
        }
        final UniqueLessonList modelClasses = new UniqueLessonList();
        modelClasses.setLessons(new ArrayList<>(groupClasses));

        return new Group(modelName, modelStudents, modelClasses);
    }
}
