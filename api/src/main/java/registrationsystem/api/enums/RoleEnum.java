package registrationsystem.api.enums;

public enum RoleEnum {
    ADMIN("admin"),
    STUDENT("student"),
    FACULTY("faculty");

    private final String text;


    RoleEnum(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
