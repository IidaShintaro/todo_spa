package com.sample.todo.dataset;

import java.io.File;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sample.todo.annotation.CsvDatabaseSetup;

public class CsvDbUnitExtension implements BeforeEachCallback {

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    CsvDatabaseSetup setup = context.getRequiredTestMethod().getAnnotation(CsvDatabaseSetup.class);
    if (setup == null) {
      return;
    }
    String path = "src/test/resources/" + setup.value();
    // Spring の ApplicationContext を取得
    ApplicationContext appContext = SpringExtension.getApplicationContext(context);
    DataSource dataSource = appContext.getBean(DataSource.class);
    IDatabaseConnection connection = new DatabaseConnection(dataSource.getConnection());
    IDataSet dataSet = new CsvDataSet(new File(path));
    DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
  }
}
