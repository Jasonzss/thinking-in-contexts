package com.jason.tics.api.content.bo;

/**
 * @author Jason
 */
public enum ContentType {
    VIDEO("v", "video"),
    AUDIO("a", "audio"),
    ESSAY("e", "essay");

    private String idPrefix;
    private String name;

    ContentType(String idPrefix, String name) {
        this.idPrefix = idPrefix;
        this.name = name;
    }

    public String getIdPrefix() {
        return idPrefix;
    }

    public void setIdPrefix(String idPrefix) {
        this.idPrefix = idPrefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getPrefix(String name){
        return ContentType.valueOf(name).idPrefix;
    }
}
