package org.apache.camel.component.hbase.producer;

import static org.apache.camel.component.hbase.HbaseConstants.BUFFER_SIZE;
import static org.apache.camel.component.hbase.HbaseConstants.HADOOP_SOCKS_HOSTNAME;
import static org.apache.camel.component.hbase.HbaseConstants.HADOOP_SOCKS_PORT;
import static org.apache.camel.component.hbase.HbaseConstants.ZOOKEEPER_HOSTNAME;
import static org.apache.camel.component.hbase.HbaseConstants.ZOOKEEPER_PORT;

import java.io.IOException;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.component.hbase.HbaseEndpoint;
import org.apache.camel.impl.DefaultProducer;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.log4j.Logger;

/**
 * The HBase parent producer.
 */
public class HbaseProducer extends DefaultProducer {

  private static Logger logger = Logger.getLogger(HbaseProducer.class);

  private HTable table;

  public HbaseProducer(Endpoint endpoint) throws IOException {
    super(endpoint);

    Configuration conf = HBaseConfiguration.create();
    conf.set("hbase.zookeeper.quorum", ZOOKEEPER_HOSTNAME + ":"
        + ZOOKEEPER_PORT);
    conf.set("hbase.zookeeper.property.clientPort", ZOOKEEPER_PORT);
    conf.setInt("hbase.client.write.buffer", BUFFER_SIZE);
    conf.setInt("hbase.client.pause", 4000);
    conf.setInt("hbase.regionserver.lease.period", 30000);
    conf.set("hadoop.socks.server", HADOOP_SOCKS_HOSTNAME + ":"
        + HADOOP_SOCKS_PORT);
    conf.set("hadoop.rpc.socket.factory.class.default",
        "org.apache.hadoop.net.SocksSocketFactory");
    conf.set("org.apache.hadoop.net.SocksSocketFactory", "");

    String tablename = parseTablename(endpoint);

    table = (new HTable(conf, tablename));
    logger.info("connected to htable: " + tablename);

  }

  private String parseTablename(Endpoint endpoint) {
    HbaseEndpoint hbaseEndpoint = (HbaseEndpoint) getEndpoint();
    String remaining = hbaseEndpoint.getRemaining();
    String tablename = StringUtils.substringAfter(remaining, "/");
    return tablename;
  }

  @Override
  public void process(Exchange exchange) throws Exception {
    HbaseEndpoint endpoint = (HbaseEndpoint) getEndpoint();
    logger.info("body: " + exchange.getIn().getBody());
    logger.info("endpoint: " + endpoint);
  }

  public HTable getTable() {
    return table;
  }

}
