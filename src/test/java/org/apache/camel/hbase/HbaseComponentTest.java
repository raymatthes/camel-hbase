package org.apache.camel.hbase;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;

public class HbaseComponentTest extends CamelTestSupport {

  @Produce(uri = "direct:start")
  protected ProducerTemplate directStart;

  @EndpointInject(uri = "mock:result")
  protected MockEndpoint mockResult;

  @Test
  public void testCreate() throws Exception {

    Assert.assertNotNull(directStart);
    Assert.assertNotNull(mockResult);

    mockResult.expectedMinimumMessageCount(1);

    directStart.sendBody("start test");

    assertMockEndpointsSatisfied();
  }

  @Override
  protected RouteBuilder createRouteBuilder() throws Exception {
    return new RouteBuilder() {
      @Override
      public void configure() {

        // send a message
        from("direct:start")

        // prints message to stdout
            .to("hbase:test")

            // test that a message arrives
            .to("mock:result");

      }
    };
  }
}
