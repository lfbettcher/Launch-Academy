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
        ArrayList<Integer> nums = new ArrayList<>();
        while (scFile.hasNextInt()) {
          nums.add(scFile.nextInt());
        }
        if (nums.isEmpty()) {
          writeFile("No numbers to " + c);
        } else {
          double result = arithmetic(c, nums);
          if (result % 1 == 0) {
            writeFile(Integer.toString((int) result));
          } else if (Double.isInfinite(result)) {
            writeFile("undefined");
          } else {
            String rounded = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).toString();
            writeFile("\u2248 " + rounded);
          }
        }
      }
    }
    scFile.close();
    System.out.println("Your math homework is done!");
  }

  private static double arithmetic(String operation, ArrayList<Integer> numbers) {
    if (numbers.isEmpty()) {
      return 0;
    }
    double result = numbers.get(0);
    for (int i = 1; i < numbers.size(); ++i) {
      int num = numbers.get(i);
      switch (operation) {
        case "*":
          result *= num;
          break;
        case "+":
          result += num;
          break;
        case "-":
          result -= num;
          break;
        case "/":
          result /= num;  // no Exception when double / 0
          break;
      }
    }
    return result;
  }

  private static void writeFile(String text) throws IOException {
    FileWriter fw = new FileWriter(RESULTS_FILE, true);
    fw.write(text + "\n");
    fw.close();
  }
}
