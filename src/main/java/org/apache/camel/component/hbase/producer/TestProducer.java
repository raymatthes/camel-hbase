package org.apache.camel.component.hbase.producer;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.component.hbase.HbaseEndpoint;
import org.apache.camel.impl.DefaultProducer;
import org.apache.log4j.Logger;

/**
 * A Producer just for unit test.
 * <p>
 * Created: Oct 6, 2011 3:45:33 PM
 * </p>
 *
 * @author raymatthes
 *
 */
public class TestProducer extends DefaultProducer {

  private static Logger logger = Logger.getLogger(TestProducer.class);

  public TestProducer(Endpoint endpoint) {
    super(endpoint);
  }

  @Override
  public void process(Exchange exchange) throws Exception {
    HbaseEndpoint endpoint = (HbaseEndpoint) getEndpoint();
    logger.info("message body: " + exchange.getIn().getBody());
    logger.info("endpoint: " + endpoint);
  }

}
