package com.celularmod.client.ui;

/** Campo de texto simples (o cursor fica sempre no fim: digitar, apagar e colar). */
public final class TextField {
    public String text = "";
    public final int max;
    public final String hint;
    public final boolean multiline;
    public Runnable onEnter;

    public TextField(String hint, int max, boolean multiline) {
        this.hint = hint;
        this.max = max;
        this.multiline = multiline;
    }

    /** Mesma regra do chat do Minecraft: sem controles, sem '§' e sem DEL. */
    private static boolean allowed(char c) {
        return c != '§' && c >= ' ' && c != '\u007f';
    }

    public void type(char c) {
        if (allowed(c) && text.length() < max) text += c;
    }

    public void backspace() { if (!text.isEmpty()) text = text.substring(0, text.length() - 1); }

    public void paste(String s) {
        for (char c : s.toCharArray()) {
            if (c == '\n' || c == '\r') c = ' ';
            type(c);
        }
    }

    public String take() {
        String t = text.trim();
        text = "";
        return t;
    }
}
