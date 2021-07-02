package it.polimi.ingsw.psp12.view;

/**
 * Class ViewHelper
 * @author Group 12
 */
public class ViewHelper {
    /**
     * takes two views and arranges them horizontally
     * @param first left view
     * @param second right view
     * @return result view
     */
    public static String displayS2S(String first, String second){
        String[] afirst = first.split("\n");
        String[] asecond = second.split("\n");

        String result="";
        for(int i = 0; i < afirst.length || i < asecond.length; i++){
            String one = i < afirst.length && !afirst[i].equals("") ? afirst[i] : "\t\t";
            String two = i < asecond.length && !asecond[i].equals("") ? asecond[i] : "\t\t";

            result = result.concat(one+"\t\t"+two+"\n");
        }
        return result;
    }

    /**
     * takes n views and arranges them horizontally
     * @param strings n view
     * @return result view
     */
    public static String displayS2S(String[] strings){
        String result = strings[0];
        for(int i = 0; i < strings.length-1; i++){
            result = (displayS2S(result, strings[i+1]));
        }
        return result;
    }

    /**
     * takes n views and arranges them horizontally
     * @param a left view
     * @param b middle view
     * @param c right view
     * @return result view
     */
    public static  String displayS2S(String a, String b, String c){
        return displayS2S(displayS2S(a,b),c);

    }

}
