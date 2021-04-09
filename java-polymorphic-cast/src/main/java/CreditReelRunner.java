import java.util.ArrayList;
import java.util.List;

public class CreditReelRunner {

  public static void main(String[] args) {
    List<TeamMember> members = new ArrayList<TeamMember>();
    members.add(new CastMember("Molly", "Parker", "Maureen Robinson"));
    members.add(new CastMember("Toby", "Stephens", "John Robinson"));
    members.add(new CastMember("Maxwell", "Jenkins", "Will Robinson"));
    members.add(new Producer("Neil Marshall"));
    members.add(new Producer("Zack Estrin"));

    for (TeamMember member : members) {
      System.out.println(member.getCreditLine());
    }
  }
}
