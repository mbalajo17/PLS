package Apache.PLS.stringText;

public class Differnt_Text_Case {
    public static void main(String[] args) {
//      1 . only first letter only UpperCase
        String firstLetterUpperCase="Hii lets cheCk for, BEING String ";
        String result=capitalizeString(firstLetterUpperCase);
        System.out.println(result);

        // outPut :Hii lets check for, being string

//      2.any word string start letter UpperCase

        String newString="";
        String test="Hii lets cheCk for, BEING String";
        String[] splitString = test.split(" ");
        for(int i=0; i<splitString.length; i++){
            newString= newString+ splitString[i].substring(0,1).toUpperCase()
                    + splitString[i].substring(1,splitString[i].length()).toLowerCase()+" ";
        }
        System.out.println(newString);

        // output: Hii Lets Check For, Being String


    }


    public static String capitalizeString(String string) {

        String trimmedString = string.trim().toLowerCase();
        StringBuilder stringBuilder = new StringBuilder(trimmedString);
        stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
        return stringBuilder.toString();

    }
}
