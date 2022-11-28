package com.souvenirstore.web;

import com.souvenirstore.bean.Order;
import com.souvenirstore.bean.OrderItem;
import com.souvenirstore.bean.Page;
import com.souvenirstore.service.OrderService;
import com.souvenirstore.service.impl.OrderServiceImpl;
import com.souvenirstore.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * Get the orders in each page in order management
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 get pageNo and pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2 call orderService.queryAllPageOrders(pageNo, pageSize)：Page
        Page<Order> page = orderService.queryAllPageOrders(pageNo, pageSize);

        page.setUrl("manager/orderServlet?action=page");

        // 3 save page into Request
        req.setAttribute("page", page);
        // 4 dispatch request to pages/manager/manager_order.jsp
        req.getRequestDispatcher("/pages/manager/manager_order.jsp").forward(req, resp);
    }

    /**
     * Update the order status in order management
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 get orderId and status
        String orderId = req.getParameter("orderId");
        String statusType = req.getParameter("status");
        Integer newStatus;
        if ("0".equals(statusType)){
            newStatus = 1;
        } else if ("1".equals(statusType)){
            newStatus = 2;
        } else {
            newStatus = 0;
        }
        // 2 call orderService.updateOrderStatus(status, orderId)：void
        orderService.updateOrderStatus(newStatus, orderId);

        // 3 redirect to the order management page
        resp.sendRedirect(req.getContextPath() + "/manager/orderServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * Check the order items in detail
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void queryDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 get orderId
        String orderId = req.getParameter("orderId");

        // 2 call orderService.queryOrderDetails(orderId)：List<OrderItem>
        List<OrderItem> orderItems = orderService.queryOrderDetails(orderId);

        // 3 save orderItems and pageNo into request
        req.setAttribute("orderItems", orderItems);

        // 4 dispatch request to pages/manager/order_items.jsp
        req.getRequestDispatcher("/pages/manager/order_items.jsp").forward(req, resp);
    }
}
