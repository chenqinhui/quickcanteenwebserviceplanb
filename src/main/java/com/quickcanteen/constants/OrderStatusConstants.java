package com.quickcanteen.constants;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderStatusConstants {
    private static Map<OrderStatus,List<OrderStatus>> orderStatusListMap;
    public static Map<OrderStatus,List<OrderStatus>> getMap()
    {
        if(orderStatusListMap==null)
        {
            orderStatusListMap = new HashMap<>();
            orderStatusListMap.put(OrderStatus.NOT_PAID,Lists.newArrayList(OrderStatus.WAITING));
            orderStatusListMap.put(OrderStatus.WAITING,Lists.newArrayList(OrderStatus.CANCELLED,OrderStatus.CLOSED,OrderStatus.PREPARING));
            orderStatusListMap.put(OrderStatus.PREPARING,Lists.newArrayList(OrderStatus.PEND_TO_TAKE,OrderStatus.PEND_TO_DISTRIBUTE));
            orderStatusListMap.put(OrderStatus.PEND_TO_TAKE,Lists.newArrayList(OrderStatus.NOT_COMMENT));
            orderStatusListMap.put(OrderStatus.PEND_TO_DISTRIBUTE,Lists.newArrayList(OrderStatus.DISTRIBUTING));
            orderStatusListMap.put(OrderStatus.DISTRIBUTING,Lists.newArrayList(OrderStatus.NOT_COMMENT));
            orderStatusListMap.put(OrderStatus.NOT_COMMENT,Lists.newArrayList(OrderStatus.COMPLETE));
        }
        return orderStatusListMap;
    }
    public static boolean judgeChanging(OrderStatus startStatus,OrderStatus terminateStatus,Map<OrderStatus,List<OrderStatus>> orderStatusListMap)
    {
        return orderStatusListMap.get(startStatus).contains(terminateStatus);
    }
}
