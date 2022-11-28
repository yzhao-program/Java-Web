package com.souvenirstore.web;

import com.souvenirstore.bean.*;
import com.souvenirstore.service.OrderService;
import com.souvenirstore.service.impl.OrderServiceImpl;
import com.souvenirstore.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClientOrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * Create order
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = loginUser.getId();

        String orderId = orderService.createOrder(cart, userId);

        // req.setAttribute("orderId", orderId);
        // to /pages/cart/checkout.jsp
        // req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);

        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    /**
     * Get the orders in each page in My Order
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 get pageNo, pageSize and userId
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = loginUser.getId();

        // 2 call orderService.queryAllPageOrders(pageNo, pageSize)：Page
        Page<Order> page = orderService.queryMyPageOrders(pageNo, pageSize, userId);

        page.setUrl("client/orderServlet?action=page");

        // 3 save page into Request
        req.setAttribute("page", page);
        // 4 dispatch request to pages/manager/manager_order.jsp
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
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

        // 4 dispatch request to pages/order/order_items.jsp
        req.getRequestDispatcher("/pages/order/order_items.jsp").forward(req, resp);
    }
}
