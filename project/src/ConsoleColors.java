public enum ConsoleColors {
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    CYAN("\u001B[36m"),
    YELLOW("\u001B[33m"),
    BLACK("\u001B[30m"),
    BLUE("\u001B[34m"),
    MAGENTA("\u001B[35m"),
    WHITE("\u001B[37m"),
    BACKGROUND_WHITE("\u001B[47m"),
    BACKGROUND_BLUE("\u001B[44m"),
    RESET("\u001B[0m");





    public final String code;
    ConsoleColors(String code) {
        this.code = code;
    }
}