package team.serenity.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

import team.serenity.commons.core.LogsCenter;
import team.serenity.commons.exceptions.DataConversionException;
import team.serenity.model.ReadOnlySerenity;
import team.serenity.model.group.Group;
import team.serenity.model.managers.ReadOnlyQuestionManager;
import team.serenity.model.userprefs.ReadOnlyUserPrefs;
import team.serenity.model.userprefs.UserPrefs;
import team.serenity.storage.question.QuestionStorage;
import team.serenity.storage.userprefs.UserPrefsStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SerenityStorage serenityStorage;
    private QuestionStorage questionStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Instantiates a new Storage manager.
     *
     * @param serenityStorage   the serenity manager storage
     * @param questionStorage   the question manager storage
     * @param userPrefsStorage  the user pref storage
     */
    public StorageManager(SerenityStorage serenityStorage,
                          QuestionStorage questionStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.serenityStorage = serenityStorage;
        this.questionStorage = questionStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return this.userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return this.userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        this.userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ Serenity methods ==============================

    @Override
    public Path getSerenityFilePath() {
        return this.serenityStorage.getSerenityFilePath();
    }

    @Override
    public Optional<ReadOnlySerenity> readSerenity() throws DataConversionException, IOException {
        return readSerenity(this.serenityStorage.getSerenityFilePath());
    }

    @Override
    public Optional<ReadOnlySerenity> readSerenity(Path filePath)
        throws DataConversionException, IOException {
        this.logger.fine("Attempting to read data from file: " + filePath);
        return this.serenityStorage.readSerenity(filePath);
    }

    @Override
    public void saveSerenity(ReadOnlySerenity serenity) throws IOException {
        saveSerenity(serenity, this.serenityStorage.getSerenityFilePath());
    }

    public void saveSerenity(Stream<Group> groups) throws IOException {
        this.serenityStorage.saveSerenity(groups);
    }

    @Override
    public void saveSerenity(ReadOnlySerenity serenity, Path filePath) throws IOException {
        this.logger.fine("Attempting to write to data file: " + filePath);
        this.serenityStorage.saveSerenity(serenity, filePath);
    }

    // ================ Question Manager methods ==============================

    @Override
    public Path getQuestionManagerStorageFilePath() {
        return this.questionStorage.getQuestionManagerStorageFilePath();
    }

    @Override
    public Optional<ReadOnlyQuestionManager> readQuestionManager() throws DataConversionException,
            IOException {
        return readQuestionManager(this.questionStorage.getQuestionManagerStorageFilePath());
    }

    @Override
    public Optional<ReadOnlyQuestionManager> readQuestionManager(
            Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return this.questionStorage.readQuestionManager(filePath);
    }

    @Override
    public void saveQuestionManager(ReadOnlyQuestionManager questionManager) throws IOException {
        saveQuestionManager(questionManager, this.questionStorage.getQuestionManagerStorageFilePath());
    }

    @Override
    public void saveQuestionManager(
            ReadOnlyQuestionManager questionManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to question data file: " + filePath);
        this.questionStorage.saveQuestionManager(questionManager, filePath);
    }

}