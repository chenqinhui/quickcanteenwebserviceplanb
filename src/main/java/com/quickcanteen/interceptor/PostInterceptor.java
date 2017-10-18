package com.quickcanteen.interceptor;

import com.google.common.collect.Maps;
import com.quickcanteen.constants.OrderStatus;
import com.quickcanteen.dto.Token;
import com.quickcanteen.mapper.OrderMapper;
import com.quickcanteen.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service("postInterceptor")
public class PostInterceptor extends BaseInterceptor {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            Token token = getToken(request);
            if (token == null) return;
            int companyId = token.getId();
            Map<String, Integer> orderCountByStatus = Maps.newHashMap();
            for (OrderStatus orderStatus : OrderStatus.values()) {
                orderCountByStatus.put(orderStatus.getDesc(), orderMapper.countByStatusAndCompanyId(orderStatus.getValue(), companyId));
            }
            modelAndView.addObject("orderCountByStatus", orderCountByStatus);
            modelAndView.addObject("statusList", OrderStatus.values());
        }
    }


}