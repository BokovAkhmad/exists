import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.*;

/**
 * Created by Akhmad Bokov on 22.03.2016.
 */
public class Main {

   public static void main(String[] args) {

       Scanner sc = new Scanner(System.in);
       System.out.println("Enter the path to file");
       while(true){
           HashMap<Integer,Integer> exists = getData(sc.nextLine());
           if(exists!=null){
               ArrayList<String> data= searchingData(exists);
               displayData(data);
               break;
           }
       }
       sc.close();
   }
    private static void displayData(ArrayList<String> strings) {
        if(strings.size()!=0){
            for(String s:strings){
                System.out.println(s);
            }
        }
        else {
            System.out.println("Circular dependencies not found");
        }
    }

    private static ArrayList<String> searchingData(HashMap<Integer, Integer> exists) {

        ArrayList<String> result=new ArrayList<String>();

        for(Iterator<Map.Entry<Integer, Integer>> it1 = exists.entrySet().iterator(); it1.hasNext(); ) {
            Map.Entry<Integer,Integer > entry1 = it1.next();
            for(Iterator<Map.Entry<Integer, Integer>> it = exists.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Integer,Integer> entry = it.next();
                if(entry1.getKey().equals(entry.getValue()) && entry.getKey().equals(entry1.getValue())){
                    result.add(String.format("%d %d %d",entry1.getKey(),entry1.getValue(),entry1.getKey()));
                    it1.remove();
                    break;
                }
            }
        }
        return  result;
    }

    private static HashMap<Integer,Integer> getData(String path){

        HashMap<Integer,Integer> numbers = new HashMap<Integer, Integer>();
        String str;

          try{
              BufferedReader reader = new BufferedReader(new FileReader(path));
              while ((str = reader.readLine())!=null){
                  String[] split = str.split(" ");
                  numbers.put(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
              }
              reader.close();
          }
          catch(Exception e){
              System.out.println(e.getMessage());
              System.out.println("Something is wrong, try again!(Check the filepath or data in the file.)");
              return null;
          }
        return numbers;
      }
}







