package cn.uestc.pojo;

public class Account {

    private Integer id;
    private String accno;
    private Integer password;
    private Double balance;
    private String name;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accno='" + accno + '\'' +
                ", password=" + password +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
