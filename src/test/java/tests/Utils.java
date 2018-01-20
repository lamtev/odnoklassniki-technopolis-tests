package tests;

import model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

final class Utils {

    private static final Path PATH = Paths.get("./user.txt");

    @NotNull
    static final User BOT = readUser();

    @NotNull
    private static User readUser() {
        final String[] tokens;
        try {
            tokens = new String(Files.readAllBytes(PATH)).split(" ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new User(tokens[0], tokens[1]);
    }

}
