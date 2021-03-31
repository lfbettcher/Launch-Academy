import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;

public class FileCalculator {

  private static final String PROBLEMS_FILE = "problems.txt";
  private static final String RESULTS_FILE = "results.txt";
  private static final Set<String> OPERATIONS = new HashSet<>(Arrays.asList("+", "*", "-", "/"));

  public static void main(String[] args) throws IOException {
    String fileName = PROBLEMS_FILE;
    if (args.length != 0) {
      fileName = args[0];
    }

    File problemsFile = new File(fileName);
    if (!problemsFile.exists()) {
      System.out.println("File does not exist.");
      return;
    }

    Scanner scFile = new Scanner(problemsFile);
    while (scFile.hasNextLine()) {
      String c = scFile.nextLine();
      if (OPERATIONS.contains(c)) {
        ArrayList<Integer> nums = getNumbers(scFile);
        String end = scFile.nextLine();
        writeFile("=".equals(end) ? calculate(c, nums) : "Invalid end to problem: " + end);
      }
    }
    scFile.close();
    System.out.println("Your math homework is done!");
  }

  private static ArrayList<Integer> getNumbers(Scanner sc) {
    ArrayList<Integer> nums = new ArrayList<>();
    while (sc.hasNextInt()) {
      nums.add(sc.nextInt());
    }
    sc.nextLine(); // consumes "\n" from last int read
    return nums;
  }

  private static String calculate(String operation, ArrayList<Integer> numbers) {
    if (numbers.isEmpty()) {
      return "No numbers to " + operation;
    }
    StringJoiner equationSJ = new StringJoiner(" " + operation + " ", "", " = ");
    equationSJ.add(String.valueOf(numbers.get(0)));

    double result = numbers.get(0);
    for (int i = 1; i < numbers.size(); ++i) {
      int num = numbers.get(i);
      result = arithmetic(result, num, operation);
      equationSJ.add(String.valueOf(num));
    }
    String equation = equationSJ.toString();
    String resultString;
    if (result % 1 == 0) {
      resultString = String.valueOf((int) result);
    } else if (Double.isInfinite(result)) {
      resultString = "undefined";
    } else {
      equation = equation.substring(0, equation.length() - 2);
      String rounded = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).toString();
      resultString = "\u2248 " + rounded;
    }
    return equation + resultString;
  }

  private static double arithmetic(double a, double b, String operation) {
    switch (operation) {
      case "*":
        return a * b;
      case "+":
        return a + b;
      case "-":
        return a - b;
      case "/":
        return a / b;
      default:
        return 0;
    }
  }

  private static void writeFile(String text) throws IOException {
    FileWriter fw = new FileWriter(RESULTS_FILE, true);
    fw.write(text + "\n");
    fw.close();
  }
}
