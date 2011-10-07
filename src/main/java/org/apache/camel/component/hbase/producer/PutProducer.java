package org.apache.camel.component.hbase.producer;

import java.io.IOException;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.hadoop.hbase.client.Put;
import org.apache.log4j.Logger;

/**
 * The HBase Put producer.
 */
public class PutProducer extends HbaseProducer {

  private static Logger logger = Logger.getLogger(PutProducer.class);

  public PutProducer(Endpoint endpoint) throws IOException {
    super(endpoint);
  }

  @Override
  public void process(Exchange exchange) throws Exception {
    super.process(exchange);

    logger.info("start put");

    Object object = exchange.getIn().getBody();
    if (!(object instanceof Put)) {
      throw new UnsupportedOperationException(
          "The put endpoint requires a Put object as the message body.");
    }

    Put put = (Put) object;
    getTable().put(put);

    logger.info("end put");
  }

}
