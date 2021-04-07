public class CoffeeSelection {
    public static String SMALL_SIZE = "Small";
    public static String MEDIUM_SIZE = "Medium";
    public static String LARGE_SIZE = "Large";

    public enum Size {
        s(SMALL_SIZE),
        m(MEDIUM_SIZE),
        l(LARGE_SIZE);

        private String sizeName;

        Size(String sizeName) {
            this.sizeName = sizeName;
        }
    }

    private Size size;

    public CoffeeSelection(Size size) {
        this.size = size;
    }

    public String toString() {
        return size.sizeName + " coffee";
    }

    public static String sizeOptionList() {
        String output = "";
        for(Size size : Size.values()) {
            output += size.name() + ". - " + size.sizeName + "\n";
        }
        return output;
    }
}