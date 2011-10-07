package org.apache.camel.component.hbase;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

/**
 * Represents the component that manages an {@link HbaseEndpoint}.
 * <p>
 * The following endpoint specifications are supported:
 * <p>
 * <ul>
 * <li>hbase:test
 * <p>
 * Prints message to stdout<br/>
 * <br/></li>
 * <li>hbase:put/table-name
 * <p>
 * Put data into the table named "table-name". The body of the message is a
 * org.apache.hadoop.hbase.client.Put object.<br/>
 * <br/></li>
 * <li>hbase:get/table-name
 * <p>
 * Get data from the table named "table-name". The body of the message is a
 * org.apache.hadoop.hbase.client.Put object. A
 * org.apache.hadoop.hbase.KeyValue[] is returned.<br/>
 * <br/></li>
 * </ul>
 */
public class HbaseComponent extends DefaultComponent {

  @Override
  protected Endpoint createEndpoint(String uri, String remaining,
      Map<String, Object> parameters) throws Exception {

    HbaseEndpoint endpoint = new HbaseEndpoint(uri, remaining, parameters, this);

    return endpoint;
  }

}
