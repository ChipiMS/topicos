package espotifai;
public class Clientes {
    String LastName, FirstName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email;
    int CustomerId,SupportRepId;    

    public Clientes( int CustomerId, String LastName, String FirstName, String Company, String Address, String City, String State, String Country, String PostalCode, String Phone, String Fax, String Email, int SupportRepId) {
                if(LastName != null)
            this.LastName = LastName;
        else
            this.LastName = "";
        if(FirstName != null)
            this.FirstName = FirstName;
        else
            this.FirstName = "";
        if(Company != null)
            this.Company = Company;
        else
            this.Company = "";
        if(Address != null)
            this.Address = Address;
        else
            this.Address = "";
        if(City != null)
            this.City = City;
        else
            this.City = "";
        if(State != null)
            this.State = State;
        else
            this.State = "";
        if(Country != null)
            this.Country = Country;
        else
            this.Country = "";
        if(PostalCode != null)
            this.PostalCode = PostalCode;
        else
            this.PostalCode = "";
        if(Phone != null)
            this.Phone = Phone;
        else
            this.Phone = "";
        if(Fax != null)
            this.Fax = Fax;
        else
            this.Fax = "";
        if(Email != null)
            this.Email = Email;
        else
            this.Email = "";
        this.CustomerId = CustomerId;
        this.SupportRepId = SupportRepId;
    }

    public Clientes(String LastName, String FirstName, String Company, String Address, String City, String State, String Country, String PostalCode, String Phone, String Fax, String Email, int SupportRepId) {
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.Company = Company;
        this.Address = Address;
        this.City = City;
        this.State = State;
        this.Country = Country;
        this.PostalCode = PostalCode;
        this.Phone = Phone;
        this.Fax = Fax;
        this.Email = Email;
        this.SupportRepId = SupportRepId;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String PostalCode) {
        this.PostalCode = PostalCode;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    public int getSupportRepId() {
        return SupportRepId;
    }

    public void setSupportRepId(int SupportRepId) {
        this.SupportRepId = SupportRepId;
    }
    
    @Override
    public String toString() {
        return FirstName +" "+ LastName;
    }
    
    
}
