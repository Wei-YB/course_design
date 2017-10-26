package main.java.Tools;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegExpForTextField extends PlainDocument {
    private Pattern pattern;
    private Matcher matcher;

    public RegExpForTextField(String pattern) {
        super();

        this.pattern = Pattern.compile(pattern);
    }

    @Override
    public void insertString (int offset, String str, AttributeSet attr)
            throws BadLocationException {

        if (str == null) { return; }

        String tmp = getText(0, offset).concat(str);

        matcher = pattern.matcher(tmp);

        if (matcher.matches()) {
            super.insertString(offset, str, attr);
        }
    }
}
