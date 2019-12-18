import java.util.ArrayList;
import java.util.Scanner;

class CryptorStorage {

    // This list stores characters

    public ArrayList<Character> list = new ArrayList<Character>();

    // costructor adds characters in list  random order

    CryptorStorage() {
        for (int i = 0, c = 33; i < 90; i++, c += 10) {

            list.add((char) c);
            if (c > 110)
                c -= 89;
        }
        list.add((char) 124);
        list.add((char) 125);
        list.add((char) 126);
    }

    //this funcion taks key character and key value as arguments and returns encrypted character
    int encrypt(int key, int value){
        int ki = list.indexOf((char)key);
        int vi = list.indexOf((char)value);

        if(ki <0 || vi<0)
            return value;

        int ei = (ki+vi) % 93;
        return list.get(ei);
    }

    //this funcion taks key encrypted character and key value as arguments and returns decrypted character
    int decrypt(int key, int value){
        int ki = list.indexOf((char)key);
        int vi = list.indexOf((char)value);

        if(ki <0 || vi<0)
            return value;

        int ei = 0;
        if((ei = vi-ki) < 0 )
            ei += 93;
        return list.get(ei);
    }

}








public class ProjectBody {

    public static void main(String[] args)  {

        Scanner s = new Scanner(System.in);
        StringBuffer bf = new StringBuffer();


        String keys = "Kala_Nag";
        char[] key ;

        //gets key from user
        System.out.print("Enter Key : ");
        s.nextLine();

        //converts keys string in to array of chars
        key = keys.toCharArray();


        System.out.println("\n\nEnter Data :");

        //reads input from console till file end
        while(s.hasNextLine()){
            bf.append(s.nextLine());
            bf.append('\n');
        }

        //converts data string in to array of chars
        char[] data = new char[bf.length()];
        bf.getChars(0,bf.length(),data,0);



        // ceats object of CryptorStorage class
        CryptorStorage c = new CryptorStorage();

        char[] encryptedData = new char[bf.length()];
        char[] decryptedData = new char[bf.length()];



        //encrypts data character by character
        for (int i = 0,j=0; i <data.length ; i++, j++) {
            encryptedData[i] = (char)c.encrypt(key[j] , data[i]);
            if(j == keys.length() - 1)
                j = 0;
        }

        //decrypts data character by characters
        for (int i = 0,j=0; i <data.length ; i++, j++) {
            decryptedData[i] = (char)c.decrypt(key[j] , encryptedData[i]);
            if(j == keys.length() - 1)
                j = 0;
        }


        //prints data

        System.out.println("Encrypted Data :\n\n");

        System.out.println(encryptedData);

        System.out.println("Decrypted Data :\n\n");

        System.out.println(decryptedData);
    }
}
