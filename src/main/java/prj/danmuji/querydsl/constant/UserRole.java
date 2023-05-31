package prj.danmuji.querydsl.constant;

public enum UserRole {

    USER("USER", "일반"),
    ADMIN("ADMIN", "관리자");

    private String value;
    private String description;

    UserRole(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }
    public String getDescription() {
        return description;
    }
}
