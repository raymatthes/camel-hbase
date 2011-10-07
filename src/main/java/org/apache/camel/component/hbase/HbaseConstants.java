package org.apache.camel.component.hbase;

/**
 *
 * HBase Constants
 * <p>
 * Created: Oct 6, 2011 9:22:53 AM
 * </p>
 *
 * @author raymatthes
 *
 */
public class HbaseConstants {

  public static final String HADOOP_SOCKS_HOSTNAME = "localhost";
  public static final String HADOOP_SOCKS_PORT = "2600";
  public static final String ZOOKEEPER_HOSTNAME = "localhost";
  public static final String ZOOKEEPER_PORT = "2181";
  public static final int BUFFER_SIZE = 8388608;

  private HbaseConstants() {
    // Utility class
  }
}
