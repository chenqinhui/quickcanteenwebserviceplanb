package com.quickcanteen.constants;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderStatusConstants {
    private static Map<OrderStatus,List<OrderStatus>> userStatusMap = new HashMap<>();
    private static Map<OrderStatus,List<OrderStatus>> distributingStatusMap = new HashMap<>();
    private static Map<OrderStatus,List<OrderStatus>> takingStatusMap = new HashMap<>();
    public static Map<OrderStatus,List<OrderStatus>> getUserStatusMap()
    {
        if(userStatusMap.size()==0)
        {
            userStatusMap.put(OrderStatus.NEW, Lists.newArrayList(OrderStatus.PEND_TO_TAKE,OrderStatus.CHECKING,OrderStatus.CANCELLED));
            userStatusMap.put(OrderStatus.PEND_TO_TAKE,Lists.newArrayList(OrderStatus.CHECKING,OrderStatus.CANCELLED));
            userStatusMap.put(OrderStatus.PEND_TO_TAKE,Lists.newArrayList(OrderStatus.NOT_COMMENT));
            userStatusMap.put(OrderStatus.DISTRIBUTING,Lists.newArrayList(OrderStatus.NOT_COMMENT));
            userStatusMap.put(OrderStatus.NOT_COMMENT,Lists.newArrayList(OrderStatus.COMPLETE));
        }
        return userStatusMap;
    }

    public static Map<OrderStatus,List<OrderStatus>> getDistributingStatusMap()
    {
        if(distributingStatusMap.size()==0)
        {
            distributingStatusMap.put(OrderStatus.CHECKING, Lists.newArrayList(OrderStatus.CLOSED,OrderStatus.PREPARING));
            //distributingStatusMap.put(OrderStatus.PREPARING,Lists.newArrayList(OrderStatus.DISTRIBUTING));
            distributingStatusMap.put(OrderStatus.PREPARING,Lists.newArrayList(OrderStatus.PEND_TO_DISTRIBUTE));
            distributingStatusMap.put(OrderStatus.PEND_TO_DISTRIBUTE,Lists.newArrayList(OrderStatus.DISTRIBUTING));
            distributingStatusMap.put(OrderStatus.DISTRIBUTING,Lists.newArrayList(OrderStatus.NOT_COMMENT));
        }
        return distributingStatusMap;
    }

    public static Map<OrderStatus,List<OrderStatus>> getTakingStatusMap()
    {
        if(takingStatusMap.size()==0)
        {
            takingStatusMap.put(OrderStatus.CHECKING, Lists.newArrayList(OrderStatus.CLOSED,OrderStatus.PREPARING));
            takingStatusMap.put(OrderStatus.PREPARING,Lists.newArrayList(OrderStatus.PEND_TO_TAKE));
            takingStatusMap.put(OrderStatus.PEND_TO_TAKE,Lists.newArrayList(OrderStatus.NOT_COMMENT));
        }
        return takingStatusMap;
    }
/*
    public static Map<String,List<String>> getStringStatusListMap(Map<OrderStatus,List<OrderStatus>> orderStatusListMap)
    {
        Map<String,List<String>> stringListMap = new HashMap<>();
        for(Map.Entry<OrderStatus,List<OrderStatus>> entry :orderStatusListMap.entrySet())
        {
            List<String> stringList = new ArrayList<>();
            for(OrderStatus orderStatus:entry.getValue())
            {
                stringList.add(orderStatus.getTerminal());
            }
            stringListMap.put(entry.getKey().getValue().toString(),stringList);
        }
        return stringListMap;
    }
    */
}
