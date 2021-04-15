import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JobsMain {

  public static void main(String[] args) {
    File positionsJson = new File("src/main/resources/positions.json");
    ObjectMapper mapper = new ObjectMapper();
//    List<HashMap<String, String>> positionsList = new ArrayList<HashMap<String, String>>();
    List<Job> jobList = new ArrayList<Job>();
    try {
//      positionsList = mapper.readValue(positionsJson, ArrayList.class);
      jobList = mapper.readValue(positionsJson, new TypeReference<ArrayList<Job>>(){});
    } catch (IOException io) {
      io.printStackTrace();
    }

    // Location, Location, Location!
    calculateJobPostingsPerLocation(jobList);
    // Companies
    calculateJobPostingsPerCompany(jobList);
    // Write a GitHub Job
    writeJob();

    // Only Jobs From June
    // Word Frequency
    wordFrequency(jobList, 10);

  }

  private static void calculateJobPostingsPerLocation(List<Job> jobList) {
    Map<String, Integer> locationsTotals = new HashMap<>();
    for (Job job : jobList) {
      String loc = job.getLocation();
      if (locationsTotals.containsKey(loc)) {
        locationsTotals.put(loc, locationsTotals.get(loc) + 1);
      } else {
        locationsTotals.put(loc, 1);
      }
    }

    ArrayList<Map.Entry<String, Integer>> unsortedMap = new ArrayList<>();
    for (Map.Entry<String, Integer> loc : locationsTotals.entrySet()) {
      unsortedMap.add(loc);
    }

    Comparator<Entry<String, Integer>> locCompare = new Comparator<Entry<String, Integer>>() {
      @Override
      public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
        Integer v1 = e1.getValue();
        Integer v2 = e2.getValue();
        return v2.compareTo(v1);
      }
    };

    /*
      Comparator<Entry<String, Integer>> locCompare = (e1, e2) -> {
      Integer v1 = e1.getValue();
      Integer v2 = e2.getValue();
      return v2.compareTo(v1);
    };
     */

    Collections.sort(unsortedMap, locCompare);

    for (Map.Entry<String, Integer> e : unsortedMap) {
      System.out.println(e.getKey() + " " + locationsTotals.get(e.getKey()));
    }
  }


  private static void calculateJobPostingsPerCompany(List<Job> jobsList) {
    Map<String, Integer> jobsPostingsMap = new HashMap<>();

    for (Job job : jobsList) {
      String companyName = job.getCompany();
      if (jobsPostingsMap.containsKey(companyName)) {
        jobsPostingsMap.put(companyName, jobsPostingsMap.get(companyName) + 1);
      } else {
        jobsPostingsMap.put(companyName, 1);
      }
    }

    for (Map.Entry<String, Integer> displayJob : jobsPostingsMap.entrySet()) {
      System.out
          .printf("Company: %s, Num postings: %s\n", displayJob.getKey(), displayJob.getValue());
    }
  }

  private static void writeJob() {
    Scanner scan = new Scanner(System.in);
    File newJob = new File("job.json");
    ObjectMapper mapper = new ObjectMapper();
  }

  private static void wordFrequency(List<Job> positionsList, int words) {
    Map<String, Integer> wordMap = new HashMap<>();
    for (Job position : positionsList) {
      Document doc = Jsoup.parse(position.getDescription());
      Scanner scanDesc = new Scanner(doc.text());
      while (scanDesc.hasNext()) {
        String word = scanDesc.next();
        word = word.replaceAll("\\p{Punct}", "");
        if (wordMap.containsKey(word)) {
          wordMap.put(word, wordMap.get(word) + 1);
        } else {
          wordMap.put(word, 1);
        }
      }
      scanDesc.close();
    }
    String[] commonWords = {"the", "and", "a", "we", "you"};
    for (String word : commonWords) {
      wordMap.remove(word);
    }
    Map<String, Integer> sortedWordMap = sortByValue(wordMap, true);

    int count = 1;
    for (Map.Entry<String, Integer> wordCount : sortedWordMap.entrySet()) {
      System.out.printf("%d Word: %s Count: %d\n", count, wordCount.getKey(), wordCount.getValue());
      if (++count > words) {
        break;
      }
    }
  }

  private static Map<String, Integer> sortByValue(Map<String, Integer> unsortedMap,
      boolean reversed) {
    // Create a list from elements of HashMap
    List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortedMap.entrySet());

    // Sort the list
    list.sort((o1, o2) -> {
      if (reversed) {
        return (o2.getValue()).compareTo(o1.getValue());
      } else {
        return (o1.getValue()).compareTo(o2.getValue());
      }
    });

    // put data from sorted list to hashmap
    Map<String, Integer> sortedMap = new LinkedHashMap<>();
    for (Map.Entry<String, Integer> entry : list) {
      sortedMap.put(entry.getKey(), entry.getValue());
    }
    return sortedMap;
  }
}
