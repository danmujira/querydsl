package prj.danmuji.querydsl.constant;

public enum UserState {
    
    ACTIVE("ACTIVE", "활성"),
    INACTIVE("INACTIVE", "비활성"),
    DELETE("DELETE", "삭제");
    
    private String value;
    private String description;
    
    UserState(String value, String description) {
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
