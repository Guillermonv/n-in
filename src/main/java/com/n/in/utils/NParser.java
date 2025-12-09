package com.n.in.utils;

import com.n.in.model.NDto;

public class NParser {

    private static final String TITLE = "Title:";
    private static final String SHORT = "Short Description:";
    private static final String MESSAGE = "Message:";
    private static final String IMAGE = "Image Prompt:";

    public static void parse(String raw, NDto dto) {

        String title = extractBetween(raw, TITLE, SHORT);
        String shortDescription = extractBetween(raw, SHORT, MESSAGE);
        String message = extractBetween(raw, MESSAGE, IMAGE);
        String imagePrompt = extractBetween(raw, IMAGE, null);

        dto.setTitle(title);
        dto.setShortDescription(shortDescription);
        dto.setMessage(message);
        dto.setImagePrompt(imagePrompt.replace(" ", "-"));
    }

    private static String extractBetween(String text, String startKey, String endKey) {
        int start = text.indexOf(startKey);
        if (start == -1) return "";

        start += startKey.length();

        int end = (endKey != null)
                ? text.indexOf(endKey, start)
                : text.length();

        if (end == -1) {
            end = text.length();
        }

        return text.substring(start, end).trim();
    }
}
