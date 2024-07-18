// package com.example.demo.xss;

// import org.apache.commons.text.StringEscapeUtils;

// import com.fasterxml.jackson.core.SerializableString;
// import com.fasterxml.jackson.core.io.CharacterEscapes;
// import com.fasterxml.jackson.core.io.SerializedString;

// public class XSSCharacterEscapes extends CharacterEscapes {

//     private final int[] asciiEscapes;

//     public XSSCharacterEscapes() {
//         asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
//         asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
//         asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
//         asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
//         asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
//         asciiEscapes['('] = CharacterEscapes.ESCAPE_CUSTOM;
//         asciiEscapes[')'] = CharacterEscapes.ESCAPE_CUSTOM;
//         asciiEscapes['#'] = CharacterEscapes.ESCAPE_CUSTOM;
//         asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;
//     }

//     @Override
//     public int[] getEscapeCodesForAscii() {
//         return asciiEscapes;
//     }

//     // @Override
//     // public SerializableString getEscapeSequence(int ch) {
//     //     return new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString((char)ch)));
//     // }
//     /**
//      * 이모지 에러 관련으로 아래 메소드로 변경
//      */
//     @Override
//     public SerializableString getEscapeSequence(int ch) {
//         SerializedString serializedString = null;
//         char charAt = (char) ch;

//         //emoji jackson parse 오류에 따른 예외 처리
//         if (Character.isHighSurrogate(charAt) || Character.isLowSurrogate(charAt)) {
//             StringBuilder sb = new StringBuilder();
//             sb.append("\\u");
//             sb.append(String.format("%04x",ch));
//             serializedString = new SerializedString(sb.toString());
//         } else {
//             serializedString = new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString(charAt)));
//         }

//         return serializedString;
//     }
// }
