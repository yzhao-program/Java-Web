package com.souvenirstore.web;

import com.google.gson.Gson;
import com.souvenirstore.bean.Cart;
import com.souvenirstore.bean.CartItem;
import com.souvenirstore.bean.Souvenir;
import com.souvenirstore.service.SouvenirService;
import com.souvenirstore.service.impl.SouvenirServiceImpl;
import com.souvenirstore.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {

    private SouvenirService souvenirService = new SouvenirServiceImpl();

    /**
     * Update the count of items in the cart
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        if (count <= 0) {
            count = 1;
        }

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * Clear the cart
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {

            cart.clear();
            // redirect to the previous cart page
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * Delete the items in the cart
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {

            cart.deleteItem(id);
            // redirect to the previous cart page
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    /**
     * Add item into the cart
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        Souvenir souvenir = souvenirService.querySouvenirById(id);
        // Convert souvenir to cartItem
        CartItem cartItem = new CartItem(souvenir.getId(),souvenir.getName(),1,souvenir.getPrice(),souvenir.getPrice());

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        // System.out.println(cart);
        // System.out.println("Referer: " + req.getHeader("Referer"));

        // redirect to the previous items display page
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * Add item into the cart by using ajax
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        Souvenir souvenir = souvenirService.querySouvenirById(id);
        // Convert souvenir to cartItem
        CartItem cartItem = new CartItem(souvenir.getId(),souvenir.getName(),1,souvenir.getPrice(),souvenir.getPrice());

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        // System.out.println(cart);

        Map<String,Object> resultMap = new HashMap<String,Object>();

        resultMap.put("totalCount", cart.getTotalCount());

        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);

        resp.getWriter().write(resultMapJsonString);
    }
}
