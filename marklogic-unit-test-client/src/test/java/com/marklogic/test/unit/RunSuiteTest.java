package com.marklogic.test.unit;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Example of how all the test suites can be run by themselves, e.g. as part of a custom Gradle task.
 */
public class RunSuiteTest {

  @Test
  public void test() {
    DatabaseClient databaseClient = DatabaseClientFactory.newClient("localhost", 8008,
      new DatabaseClientFactory.DigestAuthContext("admin", "admin"));

    try {
      List<JUnitTestSuite> suites = new TestManager(databaseClient).runAllSuites();
      String report = new DefaultJUnitTestReporter().reportOnJUnitTestSuites(suites);
      System.out.println(report);
    } finally {
      databaseClient.release();
    }
  }
}
