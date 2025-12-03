package com.n.in.utils;

import com.n.in.model.NDto;

public class NParser {

    public static void parse(String raw, NDto dto) {

        String tittle = extract(raw, "Título:");
        String message = extract(raw, "Mensaje:");
        String prompt = extract(raw, "Prompt-imagen:").replace( " ","-");

        dto.setTitle(tittle.trim());
        dto.setImagePrompt(prompt.trim());
        dto.setDescription(message.trim());
    }

    private static String extract(String text, String key) {
        int start = text.indexOf(key);
        if (start == -1) return "";

        start += key.length();

        int end = text.indexOf("\n", start);
        if (end == -1) end = text.length();

        return text.substring(start, end).trim();
    }


}
