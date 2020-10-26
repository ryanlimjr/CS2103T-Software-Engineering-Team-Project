package team.serenity.logic.commands;

import static java.util.Objects.requireNonNull;
import static team.serenity.logic.parser.CliSyntax.PREFIX_GRP;
import static team.serenity.logic.parser.CliSyntax.PREFIX_PATH;

import team.serenity.logic.commands.exceptions.CommandException;
import team.serenity.model.Model;
import team.serenity.model.group.Group;

public class AddGrpCommand extends Command {

    public static final String COMMAND_WORD = "addgrp";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Adds a new tutorial group. "
        + "Parameters: "
        + PREFIX_GRP + "GROUP "
        + PREFIX_PATH + "PATH\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_GRP + "G04 "
        + PREFIX_PATH + "LUMINUS_GROUP_A.csv\n";

    public static final String MESSAGE_SUCCESS = "New tutorial group added: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This tutorial group already exists.";

    private final Group toAdd;

    /**
     * Creates an AddGrpCommand to add the specified {@code Group}.
     */
    public AddGrpCommand(Group group) {
        //requireNonNull(group);
        assert group != null;
        this.toAdd = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasGroup(this.toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.addGroup(this.toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.toAdd), false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddGrpCommand // instanceof handles nulls
            && this.toAdd.equals(((AddGrpCommand) other).toAdd));
    }

}
