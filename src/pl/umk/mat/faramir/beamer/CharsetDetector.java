/*
 * Copyright (c) 2015, Marek Nowicki
 * All rights reserved.
 *
 * This file is distributable under the Simplified BSD license. See the terms
 * of the Simplified BSD license in the documentation provided with this file.
 */
package pl.umk.mat.faramir.beamer;

import com.ibm.icu.text.CharsetMatch;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author faramir
 */
public class CharsetDetector {

    public static Charset detectCharset(byte[] bytes) {
        com.ibm.icu.text.CharsetDetector charsetDetector
                = new com.ibm.icu.text.CharsetDetector();
        charsetDetector.setText(bytes);

        CharsetMatch charsetMatch = charsetDetector.detect();
        if (charsetMatch == null) {
            return StandardCharsets.UTF_8;
        }

        try {
            return Charset.forName(charsetMatch.getName()); // _ltr, _rtl?
        } catch (IllegalArgumentException ex) {
            return StandardCharsets.UTF_8;
        }
    }
}
