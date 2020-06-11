package AppDemo.Data;

public class App 
{
  public static void main(String... args) throws Exception {
    String kind = "Task";
    StoringService service = new StoringService("myapp-v01");
    
    service.addUser(new User("thiennguyen99","123"), kind);
   
    System.out.println(service.getUser("thiennguyen99",kind).toString());
  }
}