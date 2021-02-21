package com.sacane.manager.database;

import com.sacane.manager.Month;
import com.sacane.manager.gui.TableInitializer;

import javax.xml.crypto.Data;
import java.sql.SQLException;

public class SqlTest {

    public static void main(String[] args) throws SQLException {
        var test = new DataBuild();
        test.connection();

        test.close();
        System.out.println(Month.formattedDate(1, Month.JANUARY, 2012));
    }
}
