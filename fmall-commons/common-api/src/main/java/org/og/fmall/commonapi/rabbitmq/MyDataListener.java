package org.og.fmall.commonapi.rabbitmq;

import java.util.List;

/**
 * @description:
 * @author: OuGen
 * @create: 2020-04-13 10:33
 **/
public interface MyDataListener {

    void handOneAck(Long deliveryTag,String info);

    void handOneNack(Long deliveryTag,String info);

}
