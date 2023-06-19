
package data;

import java.io.Serializable;


public class CD implements Serializable {
    
    private String CDid;
    private String CDname;
    private String CDtype;
    private String CDtitle;
    private double CDUnitPrice;
    private int CDpublish;

    public CD() {
    }

    public CD(String CDid, String CDname, String CDtype, String CDtitle, double CDUnitPrice, int CDpublish) {
        this.CDid = CDid;
        this.CDname = CDname;
        this.CDtype = CDtype;
        this.CDtitle = CDtitle;
        this.CDUnitPrice = CDUnitPrice;
        this.CDpublish = CDpublish;
    }

    public String getCDid() {
        return CDid;
    }

    public String getCDname() {
        return CDname;
    }

    public void setCDname(String CDname) {
        this.CDname = CDname;
    }

    public String getCDtype() {
        return CDtype;
    }

    public void setCDtype(String CDtype) {
        this.CDtype = CDtype;
    }

    public String getCDtitle() {
        return CDtitle;
    }

    public void setCDtitle(String CDtitle) {
        this.CDtitle = CDtitle;
    }

    public double getCDUnitPrice() {
        return CDUnitPrice;
    }

    public void setCDUnitPrice(double CDUnitPrice) {
        this.CDUnitPrice = CDUnitPrice;
    }

    public int getCDpublish() {
        return CDpublish;
    }

    public void setCDpublish(int CDpublish) {
        this.CDpublish = CDpublish;
    }



    @Override
    public String toString() {
        return String.format("|%-8s|%-8s|%-8s|%-20s|%-9.1f|%-6d\n", CDid, CDname, CDtype, CDtitle, CDUnitPrice, CDpublish);
    }

    
    
}
