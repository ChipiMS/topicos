package espotifai;
public class Paises {
    private String Name;
    private String Invoices;
    private String Total;
    public Paises(String Name, String Invoices, String Total) {
        this.Name = Name;
        this.Invoices = Invoices;
        this.Total = Total;
    }
    public String getTotal() {
        return Total;
    }
    public void setTotal(String Total) {
        this.Total = Total;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getInvoices() {
        return Invoices;
    }
    public void setInvoices(String Invoices) {
        this.Invoices = Invoices;
    }
}
