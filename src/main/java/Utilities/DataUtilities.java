package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtilities {

    public final static  String TestData_PATH="src/test/resources/TestData/"; //عملت حفظ للpath بتاع الفولدر اللى اسمه testdata
    //  بدل ما اعمل ادخال للpath بتاع الفايل بتاعى تحت عند الفايل ريدير بس انا عامل الفانكشن ستاتيك وعايزها جينيرال علشان لو عوزت اقرا من اى فايل تانى غير ده هروح اعدل فى الفانكشن لا هخليها استاتيك اقوله هاتلى ال test data folder ذات نفسه هاتلى ال path بتاعه وناخد ال relative path مش لازم ل absolute
    //هو كلاس كل الداتا اللى فيه هي عباره عن جينرال فانكشن هستخدمها اى وقت عايز اقرا من فايلز بس فاصلها فى حته لوحدها غير ال utility
    //TODO:reading data from json file
    public static String getJsonData(String jsonfileName,String filed) throws FileNotFoundException {  //فى البارمتر بتاعتها هابصيلها حاجه هابصيلها جزئين مهمين اول حاجه هو الفايل بتاعى اللى هقرا منه اصلا هقرا منين اى الفايل بتاعى والحاجه التانيه ال filed بتاعى اللى عايزه من جوه الفايل ده

        //اول حاجه اقوله جيب لفايل بتاعى واعمله save فى الكود كفايل
        FileReader reader=new FileReader(TestData_PATH+jsonfileName+".json");
        //file name هى الحاجه الديناميك اللى بتتغير كل شويه
        //src/test/resources/TestData/ValidLoginData.json دى النتيجه النهائيه
        //بعد كده محتاج اعمل parsing لل json file علشان ابقى عارف ال key وال value فانت لما تقولى هاتلى ال key الفلانى اقدر ارجعهولك
        JsonElement jsonElement= JsonParser.parseReader(reader);
        return jsonElement.getAsJsonObject().get(filed).getAsString();
        //كده فاضل انى استخدم الفانكشن
    }
    //TODO:reading data from property file
    public static String getPropertyValue(String fileName,String key) throws IOException {
        Properties properties=new Properties();
        properties.load(new FileInputStream(TestData_PATH+fileName+".properties"));
        return properties.getProperty(key);
    }

}