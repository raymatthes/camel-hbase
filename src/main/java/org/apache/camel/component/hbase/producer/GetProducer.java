package org.apache.camel.component.hbase.producer;

import java.io.IOException;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.log4j.Logger;

/**
 * The HBase Get producer.
 */
public class GetProducer extends HbaseProducer {

  private static Logger logger = Logger.getLogger(GetProducer.class);

  public GetProducer(Endpoint endpoint) throws IOException {
    super(endpoint);
  }

  @Override
  public void process(Exchange exchange) throws Exception {
    super.process(exchange);

    logger.info("start get");

    Object object = exchange.getIn().getBody();
    if (!(object instanceof Get)) {
      throw new UnsupportedOperationException(
          "The get endpoint requires a Get object as the message body.");
    }

    Get get = (Get) object;
    Result result = getTable().get(get);

    exchange.getOut().setBody(result.raw());

    logger.info("end get");
  }

}
