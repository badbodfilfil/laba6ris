package stateful;

import java.util.Vector;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

@Stateful
public class SpliterBean implements SpliterBeanRemote {

    private String x = "йьъ";
    private String g = "аеёиоуыэюя";
    private String s = "бвгджзклмнпрстфхцчшщ";
    private Vector rules = new Vector();

    public String split(String string) {
        return hyphenateWord(string);
    }

    public SpliterBean() {
        rules.addElement(new HyphenPair("xgg", 1));
        rules.addElement(new HyphenPair("xgs", 1));
        rules.addElement(new HyphenPair("xsg", 1));
        rules.addElement(new HyphenPair("xss", 1));
        rules.addElement(new HyphenPair("gssssg", 3));
        rules.addElement(new HyphenPair("gsssg", 3));
        rules.addElement(new HyphenPair("gsssg", 2));
        rules.addElement(new HyphenPair("sgsg", 2));
        rules.addElement(new HyphenPair("gssg", 2));
        rules.addElement(new HyphenPair("sggg", 2));
        rules.addElement(new HyphenPair("sggs", 2));
    }

    public String hyphenateWord(String text) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (x.indexOf(c) != -1) {
                sb.append("x");
            } else if (g.indexOf(c) != -1) {
                sb.append("g");
            } else if (s.indexOf(c) != -1) {
                sb.append("s");
            } else {
                sb.append(c);
            }
        }
        String hyphenatedText = sb.toString();
        sb=new StringBuffer();
        for (int i = 0; i < rules.size(); i++) {
            HyphenPair hp = (HyphenPair) rules.elementAt(i);
            int index = hyphenatedText.indexOf(hp.pattern);
            while (index != -1) {
                int actualIndex = index + hp.position;
                hyphenatedText = hyphenatedText.substring(0, actualIndex) + "-" + hyphenatedText.substring(actualIndex);
                text = text.substring(0, actualIndex) + "-" + text.substring(actualIndex);
                index = hyphenatedText.indexOf(hp.pattern);
            }
        }
        String[] parts = split(text, "-");
        for (String s : parts) {
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString();
    }

    private String[] split(String original, String separator) {
        original = original.trim();
        Vector nodes = new Vector();
        int index = original.indexOf(separator);
        int i = 0;
        while (index >= 0) {
            nodes.addElement(original.substring(0, index));
            original = original.substring(index + separator.length());
            index = original.indexOf(separator);
            i++;
        }
        nodes.addElement(original);
        String[] result = new String[nodes.size()];
        if (nodes.size() > 0) {
            for (int loop = 0; loop < nodes.size(); loop++) {
                result[loop] = (String) nodes.elementAt(loop);
            }

        }
        return result;
    }

    class HyphenPair {

        public String pattern;
        public int position;

        public HyphenPair(String pattern, int position) {
            this.pattern = pattern;
            this.position = position;
        }
    }

    public void ejbCreate() {
    }

    public void ejbRemove() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }

    public void setSessionContext(SessionContext sc) {
    }

}
