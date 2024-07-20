public class Car {
    private String mark;
    private int years;
    private double price;

    public Car() {
    }

    public Car(String mark, int years, double price) {
        this.mark = mark;
        this.years = years;
        this.price = price;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
