package com.dimata.demo.audiobook.demo_audio_book.enums;

import lombok.Getter;

public enum StatusRating {
    ACTIVE("ACTIVE"),
    BLOCKED("BLOCKED");
   

    @Getter
    private final String code;

    public static StatusRating getStatus(String code){
        switch (code) {
            case "ACTIVED":
                return ACTIVE;
            
            default:
                return BLOCKED;
        }
    }

    StatusRating(String code) {
        this.code = code;
    }

    public String parseStatus(StatusRating ratingStatus){
        if (ratingStatus == StatusRating.ACTIVE) {
            return "active";
        }
        return "blocked";
    }

    public String parseSatus(String code) {
        if (getStatus(code) == StatusRating.ACTIVE) {
            return "active";
        }else
        return "blocked";
    }
}
