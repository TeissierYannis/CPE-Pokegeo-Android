package fr.cpe.wolodiayannis.pokemongeo.data;

public class BasicResponse {

    /**
     * Message.
     */
    private final String message;

    /**
     * Constructor.
     *
     * @param message message
     */
    public BasicResponse(String message) {
        this.message = message;
    }

    /**
     * Get message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }
}
