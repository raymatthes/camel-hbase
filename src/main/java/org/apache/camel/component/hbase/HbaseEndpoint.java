package org.apache.camel.component.hbase;

import java.io.IOException;
import java.util.Map;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.component.hbase.producer.GetProducer;
import org.apache.camel.component.hbase.producer.PutProducer;
import org.apache.camel.component.hbase.producer.TestProducer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.log4j.Logger;

/**
 * Represents an HBase endpoint.
 */
public class HbaseEndpoint extends DefaultEndpoint {

  private static Logger logger = Logger.getLogger(HbaseEndpoint.class);
  private String remaining;
  private Map<String, Object> parameters;
  private HbaseComponent component;

  public String getRemaining() {
    return remaining;
  }

  public void setRemaining(String remaining) {
    this.remaining = remaining;
  }

  public Map<String, Object> getParameters() {
    return parameters;
  }

  public void setParameters(Map<String, Object> parameters) {
    this.parameters = parameters;
  }

  @Override
  public HbaseComponent getComponent() {
    return component;
  }

  public void setComponent(HbaseComponent component) {
    this.component = component;
  }

  public HbaseEndpoint(String uri, String remaining,
      Map<String, Object> parameters, HbaseComponent component)
      throws IOException {

    this.remaining = remaining;
    this.parameters = parameters;
    this.component = component;

  }

  @Override
  public Producer createProducer() throws Exception {

    logger.info("creating producer for: " + remaining);

    if (remaining.startsWith("test")) {
      return new TestProducer(this);
    } else if (remaining.startsWith("put/")) {
      return new PutProducer(this);
    } else if (remaining.startsWith("get/")) {
      return new GetProducer(this);
    }

    throw new UnsupportedOperationException(
        "Unknown operation in endpoint uri: " + remaining);
  }

  @Override
  public Consumer createConsumer(Processor processor) throws Exception {
    throw new UnsupportedOperationException(
        "You cannot consume from this endpoint.");
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  @Override
  protected String createEndpointUri() {
    return "hbase";
  }

}
