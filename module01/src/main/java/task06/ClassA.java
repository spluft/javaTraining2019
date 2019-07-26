package task06;

public class ClassA {
    private String thisString;
    private int number;

    public ClassA(String thisString, int number) {
        this.thisString = thisString;
        this.number = number;
    }

    public String getThisString() {
        return thisString;
    }

    public void setThisString(String thisString) {
        this.thisString = thisString;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassA classA = (ClassA) o;

        if (number != classA.number) return false;
        return thisString != null ? thisString.equals(classA.thisString) : classA.thisString == null;
    }

    @Override
    public int hashCode() {
        int result = thisString != null ? thisString.hashCode() : 0;
        result = 31 * result + number;
        return result;
    }

    @Override
    public String toString() {
        return "ClassA{" +
                "thisString='" + thisString + '\'' +
                ", number=" + number +
                '}';
    }
}