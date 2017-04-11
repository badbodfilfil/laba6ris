package stateless;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class InverterBean implements InverterBeanLocal {
    StringBuilder sb;
    public String invert(String string) {
        sb = new StringBuilder(string);
        return sb.reverse().toString();
    }

    public InverterBean() {
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
