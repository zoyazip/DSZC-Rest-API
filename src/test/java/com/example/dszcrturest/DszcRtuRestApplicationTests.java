package com.example.dszcrturest;

import com.example.dszcrturest.ExcelService.ExcelFileService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;


import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class DszcRtuRestApplicationTests {

    private ExcelFileService excelFileService = new ExcelFileService();
    @Test
    void contextLoads() {
    }

    //Unit tests are off, because theese methods in ExcelFileService are private
    //While testing I have made them public

    /*
    @Test
    void getLessonInstructorTest() {
        String resultEmpty = excelFileService.getLessonInstructor("");
        String result1 = excelFileService.getLessonInstructor("Diskrētās struktūras datorzinātnēs lk. doc. R. Smirnova 408");
        String result2 = excelFileService.getLessonInstructor("Datorgrafikas un attēlu apstr. pam. lk. lekt. A. Sisojevs Zoom");
        String result3 = excelFileService.getLessonInstructor("Materiālu pret.[1/2] lk. prof. O.Kononova Zoom");

        assertEquals("", resultEmpty);
        assertEquals("R. Smirnova", result1);
        assertEquals("lekt. A. Sisojevs", result2);
        assertEquals("prof. O.Kononova", result3);

    }
    @Test
    void canBeParsedToIntTest() {
        boolean result1 = excelFileService.canBeParsedToInt("1");
        boolean result2 = excelFileService.canBeParsedToInt("4");
        boolean result3 = excelFileService.canBeParsedToInt("");
        boolean result4 = excelFileService.canBeParsedToInt("test");

        assertEquals(true, result1);
        assertEquals(true, result2);
        assertEquals(false, result3);
        assertEquals(false, result4);
    }

    @Test
    void getClassRoomTest() {
        String result1 = excelFileService.getClassRoom("Diskrētās struktūras datorzinātnēs lk. doc. R. Smirnova 408");
        String result2 = excelFileService.getClassRoom("Datorgrafikas un attēlu apstr. pam. lk. lekt. A. Sisojevs Zoom");
        String result3 = excelFileService.getClassRoom("Elektroapgādes pamati, lk., pr.d., asoc.prof.L.Zemīte, plkst. 10:15-11:50, no 3.-14.ned. MS Teams");
        String result4 = excelFileService.getClassRoom("");

        assertEquals("408", result1);
        assertEquals("Zoom", result2);
        assertEquals("MS Teams", result3);
        assertEquals("Unknown", result4);
    }
*/
}
