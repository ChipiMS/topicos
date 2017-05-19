package espotifai;

import java.sql.Date;

public class Empleados {
    String LastName, FirstName, Title, Address, City, State, Country, PostalCode, Phone, Fax, Email;
    int EmployeeId,ReportsTo;
    Date BirthDay, HireDate;

    public Empleados(int EmployeeId, String LastName, String FirstName, String Title, String Address, String City, String State, String Country, String PostalCode, String Phone, String Fax, String Email, int ReportsTo, Date BirthDay, Date HireDate) {
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.Title = Title;
        this.Address = Address;
        this.City = City;
        this.State = State;
        this.Country = Country;
        this.PostalCode = PostalCode;
        this.Phone = Phone;
        this.Fax = Fax;
        this.Email = Email;
        this.EmployeeId = EmployeeId;
        this.ReportsTo = ReportsTo;
        this.BirthDay = BirthDay;
        this.HireDate = HireDate;
    }

    public Empleados(String LastName, String FirstName, String Title, String Address, String City, String State, String Country, String PostalCode, String Phone, String Fax, String Email, int ReportsTo, Date BirthDay, Date HireDate) {
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.Title = Title;
        this.Address = Address;
        this.City = City;
        this.State = State;
        this.Country = Country;
        this.PostalCode = PostalCode;
        this.Phone = Phone;
        this.Fax = Fax;
        this.Email = Email;
        this.ReportsTo = ReportsTo;
        this.BirthDay = BirthDay;
        this.HireDate = HireDate;
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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
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

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    public int getReportsTo() {
        return ReportsTo;
    }

    public void setReportsTo(int ReportsTo) {
        this.ReportsTo = ReportsTo;
    }

    public Date getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(Date BirthDay) {
        this.BirthDay = BirthDay;
    }

    public Date getHireDate() {
        return HireDate;
    }

    public void setHireDate(Date HireDate) {
        this.HireDate = HireDate;
    }
    @Override
    public String toString() {
        return FirstName;
    }
}
