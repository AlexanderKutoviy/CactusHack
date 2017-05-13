package com.anykeyapp;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static final Pattern regex = Pattern.compile(".*^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");

    @Test
    public void addition_isCorrect() throws Exception {
        String s = "Text detected! 10/01/2017 10/01/2020 556/2016";
        String[] str = s.split(" ");
        String s2 = "10/01/2017";
        String s3 = "10.01.2017";
        String s4 = "10-01-2017";
        for (int i = 0; i < str.length; i++) {
            Matcher match = regex.matcher(str[i]);
            if (match.find()) {
                System.out.println("Found");
            }
        }
    }
}