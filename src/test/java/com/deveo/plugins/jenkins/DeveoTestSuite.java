package com.deveo.plugins.jenkins;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DeveoEventTest.class, DeveoRepositoryTest.class})
public class DeveoTestSuite {
}
