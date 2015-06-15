/*
 * Copyright (c) 2015, Marek Nowicki
 * All rights reserved.
 *
 * This file is distributable under the Simplified BSD license. See the terms
 * of the Simplified BSD license in the documentation provided with this file.
 */
package pl.umk.mat.faramir.beamer;

/**
 *
 * @author faramir
 */
public class SlideNote {

    final private String text;
    final private long seconds;

    public SlideNote(String text, long seconds) {
        this.text = text;
        this.seconds = seconds;
    }

    public String getText() {
        return text;
    }

    public long getSeconds() {
        return seconds;
    }

    @Override
    public String toString() {
        if (seconds != 0) {
            return String.format("%d:%02d: %s", seconds / 60, seconds % 60, text);
        } else {
            return String.format("undef: %s", text);
        }
    }

}
