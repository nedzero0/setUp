import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import javax.crypto.spec.PSource;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Reader;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class a {

    public static void main(String[] args) {
        Integer  time[] = {1,5,8,11,16,17,29,34,39};
        Integer line[] = {3,7,12,17,19,31,40};
        List<Integer> list_time = new ArrayList<>(time.length);
            List<Integer> list_line = new ArrayList<>(line.length);
        Collections.addAll(list_time,time);
            HashMap<Integer, Integer> hashMap = new HashMap<>();

            for (int i=0;i<line.length;i++){
                for (int j=0;j<list_time.size();j++){
                    if (Math.abs(line[i]-list_time.get(j)) < Math.abs(line[i]-list_time.get(j+1))){
                        System.out.println("line:" +line[i] + ",time:"+list_time.get(j));
                        list_time.remove(j);
                        break;
                    }
                    if (Math.abs(line[i]-list_time.get(j)) == Math.abs(line[i]-list_time.get(j+1))){
                        System.out.println("line:" +line[i] + ",time:"+list_time.get(j+1));
                        list_time.remove(j+1);
                       break;
                    }
                    if (line[i]<list_time.get(0)){
                        System.out.println("line:" +line[i] + ",time:"+list_time.get(0));
                        list_time.remove(0);
                        break;
                    }
                    if (line[i]>=list_time.get(list_time.size()-1)){
                        System.out.println("line:" +line[i] + ",time:"+list_time.get(list_time.size()-1));
                        list_time.remove(list_time.size()-1);
                        break;
                    }
                }
            }

    }
}

class b{
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String string = null;

        String[] s = str.split(" ");
        String[] split = s[1].split(":");
        Integer hours = Integer.parseInt(split[0]);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(str);

        Date date1 = simpleDateFormat.parse("1900-01-01 00:00:00");
        Date date2 = simpleDateFormat.parse("2050-01-01 00:00:00");

        //判断范围
        if (date.getTime()<date1.getTime() || date.getTime()>date2.getTime()){
            System.out.println("该时间不在时间范围之类");
            System.exit(0);
        }
      if (hours < 8){
          split[0] = "11";
          split[1] = "00";
          split[2] ="00";
      }
      else if (hours>8 && hours<9 || s[1].equals("09:00:00")){
          split[0] = "12";
      }
      else if (hours==9){
          split[0] = "14";
      }
      else if (s[1].equals("12:00:00")){
          split[0] = "17";
      }
      else if (hours>9 && hours<12){
          split[0] =String.valueOf(14+(12-hours));
      }
      else if (hours>=14&hours<15){
          split[0] = "17";
      }
      else if (s[1].equals("15:00:00")){
          split[0] = "18";
      }
      else if (hours>15 && hours <18){
          split[0] =String.valueOf(8+(12-hours));
      }

      string = s[0]+" "+split[0]+":"+split[1]+":"+split[2];
        System.out.print("返回：");
        System.out.println(string);

    }
}


class C {
    public C(){
        System.out.println("ccc");
    }
    public static void eat(){
        System.out.println("111");
    }
    public void he(){
        System.out.println("222");
    }
}
class D extends C{
    static {
        System.out.println("staic");
    }

    public D(){
        System.out.println("ddd");
    }
    public static void eat(){
        System.out.println("333");
    }
    public void he(){
        System.out.println("444");
    }

    public static void main(String[] args) {
        System.out.println("DDmain");
    }
}
class f{
    public static void main(String[] args) {
        String s1 = "Hello!";
        String s2 = new String(new char[] {'H', 'e', 'l', 'l', 'o', '!'});
        System.out.println(s1==s2);

    }
}

class G{
    public static void main(String[] args) {
        Integer[] nums = {1,5,8,17,29,33,39,11,16,30,2};

        List<Integer> list = new ArrayList<Integer>(Arrays.asList(nums));
        List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(nums));


        int target = 33;
        List<Integer> dex = new ArrayList<Integer>();



        //有就直接输出
        if (list.contains(target)){
            System.out.println("结果："+list.indexOf(target));
        }

            Results(nums,list,target,dex,0,list1);

       /* for (int i=0;i<list.size();i++){
           int  after = target - list.get(i);
            if (list.contains(after)) {
                System.out.println("结果："  + i + "," + list.indexOf(after));
            }
        }*/




    }


    public static int Results(Integer[] nums,List<Integer> list, int target, List<Integer> dex,int j,List<Integer> list1) {
        int after = target;
        int af = target;
           while (j<list.size()) {
               for (int i = 0; i < list.size(); i++) {//从第一个数依次减去  直到找存在的
                   after = target - list.get(i);
                   if (list.contains(after)) {
                       if (dex.isEmpty())   {
                           System.out.println("结果："  + i + "," + list1.indexOf(after));
                       }else {
                           System.out.println("结果：" + dex + "," + i + "," + list1.indexOf(after));
                       }
                   }
               }
               list.remove(0);//移除   重新定义list
               dex.add(j);
               j+=1;
               return Results(nums,list, after, dex, j,list1);
           }
           return 0;
    }

}