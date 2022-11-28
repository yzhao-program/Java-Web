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
import java.util.List;

public class SouvenirServlet extends BaseServlet {

    private SouvenirService souvenirService = new SouvenirServiceImpl();

    /**
     * Get the souvenirs in each page in souvenir management
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

        page.setUrl("manager/souvenirServlet?action=page");

        // 3 save page into Request
        req.setAttribute("page", page);
        // 4 dispatch request to pages/manager/manager_souvenir.jsp
        req.getRequestDispatcher("/pages/manager/manager_souvenir.jsp").forward(req, resp);
    }

    /**
     * Add new souvenir in souvenir management
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        // get the request parameters and convert them to Souvenir
        Souvenir souvenir = WebUtils.copyParamToBean(req.getParameterMap(), new Souvenir());

        souvenirService.addSouvenir(souvenir);
        // redirect to the souvenir management page
        resp.sendRedirect(req.getContextPath() + "/manager/souvenirServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * Delete the souvenir in souvenir management
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        souvenirService.deleteSouvenirById(id);
        // redirect to the souvenir management page
        resp.sendRedirect(req.getContextPath() + "/manager/souvenirServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * Update the souvenir in souvenir management
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get the request parameters and convert them to Souvenir
        Souvenir souvenir = WebUtils.copyParamToBean(req.getParameterMap(), new Souvenir());
        souvenirService.updateSouvenir(souvenir);
        // redirect to the souvenir management page
        resp.sendRedirect(req.getContextPath() + "/manager/souvenirServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * Find the souvenir id to do update
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getSouvenir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Souvenir souvenir = souvenirService.querySouvenirById(id);
        req.setAttribute("souvenir", souvenir);
        req.getRequestDispatcher("/pages/manager/souvenir_edit.jsp").forward(req, resp);
    }

    /**
     * Get the souvenirs in souvenir management
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Souvenir> souvenirs = souvenirService.querySouvenirs();
        req.setAttribute("souvenirs", souvenirs);
        req.getRequestDispatcher("/pages/manager/manager_souvenir.jsp").forward(req, resp);
    }
}
