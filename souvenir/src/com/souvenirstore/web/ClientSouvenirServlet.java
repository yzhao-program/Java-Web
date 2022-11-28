package com.souvenirstore.web;

import com.souvenirstore.bean.Page;
import com.souvenirstore.bean.Souvenir;
import com.souvenirstore.service.SouvenirService;
import com.souvenirstore.service.impl.SouvenirServiceImpl;
import com.souvenirstore.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientSouvenirServlet extends BaseServlet {

    private SouvenirService souvenirService = new SouvenirServiceImpl();

    /**
     * Get the souvenirs in each page in home page
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 get pageNo and pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2 call souvenirService.queryPageSouvenirs(pageNo, pageSize)：Page
        Page<Souvenir> page = souvenirService.queryPageSouvenirs(pageNo, pageSize);

        page.setUrl("client/souvenirServlet?action=page");

        // 3 save page into Request
        req.setAttribute("page", page);
        // 4 dispatch request to pages/manager/manager_souvenir.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     * Get the souvenirs by price in order in each page in home page
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        Page<Souvenir> page = souvenirService.queryPageSouvenirsByPrice(pageNo,pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/souvenirServlet?action=pageByPrice");
        // set the min in the parameter in the url in page navigation
        if (req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        // set the max in the parameter in the url in page navigation
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());

        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
